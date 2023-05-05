
// import java.util.*;
import java.io.*;
import java.util.*;

public class Optimized {
    public static void main(String[] args) {
        boolean test = false;
        if (args.length > 0 && args[0].equals("test")) 
            test = true;
        
        try {
            // File file = new File("./Roi-des-bleu/maps.txt");
            File file = new File("./maps.txt");
            FileReader fr = new FileReader(file);
            try {
                BufferedReader br = new BufferedReader(fr);
                String nbMapsString = br.readLine().strip();
                int nbMaps = Integer.parseInt(nbMapsString);

                for (int mapId = 0; mapId < nbMaps; mapId++) {
                    String[] mapSize = br.readLine().strip().split(" ");
                    int nbLines = Integer.parseInt(mapSize[0]);
                    int nbColumns = Integer.parseInt(mapSize[1]);
                    int beerLimit = Integer.parseInt(mapSize[2]);
                    
                    // support for test file, if no result expected, return -2
                    int resultExpected = -2;
                    if (mapSize.length >= 4)
                    {
                        resultExpected = Integer.parseInt(mapSize[3]);
                    }

                    HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

                    for (int lineId = 0; lineId < nbLines; lineId++) {
                        String[] line = br.readLine().strip().split(" ");
                        for (int columnId = 0; columnId < nbColumns; columnId++) {
                            String key = getKey(lineId, columnId);
                            if (test)
                                System.out.print(line[columnId] + "-");
                            map.put(key, new ArrayList<Integer>());
                            map.get(key).add(Integer.parseInt(line[columnId]));
                        }
                        if (test)
                            System.out.println();
                    }

                    long startTime = System.nanoTime();
                    int result = findBestPath(map, beerLimit, nbColumns, nbLines);
                    long endTime = System.nanoTime();

                    // print results
                    if (result == -2)
                        System.out.println("Error in final value (NullPointerException)");
                    else
                        System.out.println("Final value: " + result);

                    if (test && mapSize.length >= 4 && resultExpected != -2)
                        System.out.println("Expected: " + resultExpected + " actual: " + result + "\nSuccess: " +  (result == resultExpected));
                    
                    if (test && result != -2)
                        System.out.println("Time: " + (endTime - startTime) / 1000000 + "ms");
                }

                br.close();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get key for a map
     * @param lineId line id where to get values
     * @param columnId column id where to get values
     * @return key for a map
     */
    public static String getKey(int lineId, int columnId) {
        return "(" + lineId + ", " + columnId + ")";
    }

    /**
     * Find the best path in a map
     * JAVA SPEC:
     * @param map key="(x, y)", value=[current value] && value.size() == 1
     * @param beerLimit beer limit imposed by file definition
     * @param maxColumns max number of columns in the map
     * @param maxLines max number of lines in the map
     * @return -2 if no value found, -1 if no path found, best value otherwise
     * 
     * JML SPEC:
     * @requires map != null && beerLimit >= 0 && maxColumns > 0 && maxLines > 0;
     * @requires map.size() == maxColumns * maxLines;
     * @requires \forall int i; 0 <= i < maxLines; \forall int j; 0 <= j < maxColumns; map.containsKey(getKey(i, j));
     * @requires \forall int i; 0 <= i < maxLines; \forall int j; 0 <= j < maxColumns; map.get(getKey(i, j)).size() == 1;
     * 
     * @assignable bestValue;
     * @ensures 0 <= bestValue <= beerLimit || bestValue == -1 || bestValue == -2;
     * @assignable \result == bestValue;
     * @ensures \result >= -1 || \result <= beerLimit;
     */
    public static int findBestPath(HashMap<String, ArrayList<Integer>> map, int beerLimit, int maxColumns,
            int maxLines) {
        // check first and last value to save time
        String firstKey = getKey(0, 0);
        String lastKey = getKey(maxLines - 1, maxColumns - 1);
        int firstValues = map.get(firstKey).get(0);
        int lastValues = map.get(lastKey).get(0);

        // check if first or last value is too high
        if (firstValues > beerLimit || lastValues > beerLimit)
            // first or last value too high ==> stop process
            return -1;

        // generate a list of summed values
        for (int lineId = maxLines - 1; lineId >= 0; lineId--) {
            for (int columnId = maxColumns - 1; columnId >= 0; columnId--) {
                // get values of current position
                String key = getKey(lineId, columnId);
                ArrayList<Integer> values = map.get(key);
                int currentValue = values.get(0);

                if (lineId == maxLines - 1 && columnId == maxColumns - 1) {
                    // we already check the value is not too high
                    // We add the value to the list at last index because we pass the first element before adding 
                    values.add(currentValue);
                }
                if (lineId < maxLines - 1) {
                    // We sum with previous line
                    values = addValues(map, lineId + 1, columnId, values, beerLimit);
                    map.put(key, values);
                }

                if (columnId < maxColumns - 1) {
                    // We sum with previous column
                    values = addValues(map, lineId, columnId + 1, values, beerLimit);
                    map.put(key, values);
                }

                if (lineId < maxLines - 1 && columnId < maxColumns - 1) {
                    // We sum with previous diagonal
                    values = addValues(map, lineId + 1, columnId + 1, values, beerLimit);
                    map.put(key, values);
                }
            }
        }

        // get best value
        ArrayList<Integer> values = map.get(firstKey);
        if (values == null)
            // no value found
            return -2;

        if (values.size() == 1 && values.get(0) > beerLimit)
            // contain only his own value
            return -1;

        // find best value in final possible set
        int bestValue = -1;
        for (int valueId = 1; valueId < values.size(); valueId++) {
            int value = values.get(valueId);
            if (value > bestValue && value <= beerLimit)
                bestValue = value;
        }
        return bestValue;
    }

    /**
     * add previous value to current value list
     * @param map key="(x, y)", value=[current value at this coord, sum of previous values...]
     * @param lineId line id where to get values
     * @param columnId column id where to get values 
     * / / Line and column are used to define key
     * @reqires map.containsKey(getKey(lineId, columnId));
     * 
     * @param valueToAdd list of values at current coord
     * @param beerLimit beer limit imposed by file definition
     * @param currentValue current value 
     * @effect update values at current coord (give by valueToAdd)
     * @return new list of updated values at current coord
     */
    public static ArrayList<Integer> addValues(HashMap<String, ArrayList<Integer>> map, int lineId, int columnId,
            ArrayList<Integer> valueToAdd, int beerLimit)  {
        String key = getKey(lineId, columnId);
        return addValues(map, key, valueToAdd, beerLimit);
    }

    /**
     * add previous value to current value list
     * @param map key="(x, y)", value=[current value at this coord, sum of previous values...]
     * @param key key to get values of previous coord
     * @reqires map.containsKey(key);
     * 
     * @param valueToAdd list of values at current coord
     * @param beerLimit beer limit imposed by file definition
     * @param currentValue current value 
     * @effect update values at current coord (give by valueToAdd)
     * @return new list of updated values at current coord
     */
    public static ArrayList<Integer> addValues(HashMap<String, ArrayList<Integer>> map, String key,
            ArrayList<Integer> valueToAdd, int beerLimit)  {
        // get previous values and current values to add
        ArrayList<Integer> previousValues = map.get(key);
        int currentValue = valueToAdd.get(0);

        // add previous values to current value
        if (previousValues != null) {
            for (int valueId = 1; valueId < previousValues.size(); valueId++) {
                int value = previousValues.get(valueId);
                if ((((value + currentValue) <= beerLimit) && (!valueToAdd.contains(value + currentValue))) || valueToAdd.size() == 1) {
                    valueToAdd.add(value + currentValue);
                }
            }
        }
        return valueToAdd;
    }
}
