
// import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Solution {

    public static void print(Object s)
    {
        // System.out.println(s.toString());
    }
    public static void main(String[] args) {
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
                    print("N° Line: " + nbLines);
                    int nbColumns = Integer.parseInt(mapSize[1]);
                    print("N° Column: " + nbColumns);
                    int beerLimit = Integer.parseInt(mapSize[2]);
                    print("N° Beer: " + beerLimit);

                    int[][] map = new int[nbLines][nbColumns];

                    for (int lineId = 0; lineId < nbLines; lineId++) {
                        String[] line = br.readLine().strip().split(" ");
                        for (String l: line)
                            System.out.print(l + "-");
                        System.out.println();
                        for (int columnId = 0; columnId < nbColumns; columnId++) {
                            map[lineId][columnId] = Integer.parseInt(line[columnId]);
                        }
                        ;
                    }
                    print("Map.length = " + map.length);
                    print("Map[0].length = " + map[0].length);

                    System.out.println("Final value: " + findBestPath(map, beerLimit, nbColumns, nbLines));
                }

                br.close();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findBestPath(int[][] map, int beerLimit, int maxColumns, int maxLines) {
        System.out.println("Processing");
        print("Beer limit: " + beerLimit);
        print("Maximum lines: " + maxLines);
        print("Maximum column: " + maxColumns);

        // build summed map
        ArrayList<ArrayList<Integer>> sum = new ArrayList<>(maxLines);
        for (int i = 0;  i < maxLines + 1; i++) {
            sum.add(new ArrayList<Integer>(maxColumns));
            for (int j = 0; j < maxColumns + 1; j++)
                sum.get(i).add(0);
        }
        
        // add values from the map to the sum, take  the minimum at first.
        for (int currentLine = maxLines - 1; currentLine >= 0; currentLine--) {
            print("Current line: " + currentLine);
            for (int currentColumn = maxColumns - 1; currentColumn >= 0; currentColumn--) {
                print("Current column: " + currentColumn);
                print("Processing coordinates: (" + currentLine + ", " + currentColumn + ")");
                print("Value: " + map[currentLine][currentColumn]);
                int previousVertical = Integer.MAX_VALUE;
                if (currentColumn < maxColumns - 1)
                {
                    previousVertical = sum.get(currentLine ).get(currentColumn + 1);
                }

                int previousHorizontal = Integer.MAX_VALUE;
                if (currentLine < maxLines - 1)
                {
                    previousHorizontal = sum.get(currentLine + 1).get(currentColumn);
                }

                int previousDiagonal = Integer.MAX_VALUE;
                if (currentLine < maxLines - 1 && currentColumn < maxColumns - 1)
                {
                    previousDiagonal = sum.get(currentLine + 1).get(currentColumn + 1);
                }

                int currentTemp = map[currentLine][currentColumn];
                if (currentLine < maxLines - 1 || currentColumn < maxColumns - 1)
                    // if not in last line and column: add minimum
                    currentTemp += Math.min(previousDiagonal, Math.min(previousHorizontal, previousVertical));
                sum.get(currentLine).set(currentColumn, currentTemp);

                // print int the oposit order because we start from the end and go to the beginning
                System.out.print(sum.get(currentLine).get(currentColumn) + "-");
            }
            System.out.println();
        }

        if (sum.get(0).get(0) > beerLimit)
            return -1;
        // by using the minimum value we can consider the maximum value to be acceptable is already computed
        if (sum.get(0).get(0) == beerLimit)
            return sum.get(0).get(0);
        
        // get path where we drink the most beer and stay under the limit
        for (int currentLine = 0; currentLine < maxLines; currentLine++) {
            print("Current line: " + currentLine);
            for (int currentColumn = 0; currentColumn < maxColumns; currentColumn++) {
                print("Current column: " + currentColumn);
                print("Processing coordinates: (" + currentLine + ", " + currentColumn + ")");
                print("Value: " + map[currentLine][currentColumn]);

                int currentTemp = map[currentLine][currentColumn];
                
                if (currentTemp > beerLimit)
                    sum.get(currentLine).set(currentColumn, Integer.MIN_VALUE);
                else {

                    int previousVertical = Integer.MIN_VALUE;
                    if (currentColumn > 0)
                        previousVertical = sum.get(currentLine ).get(currentColumn - 1);
                    if (previousVertical + currentTemp > beerLimit || previousVertical < 0)
                        previousVertical = Integer.MIN_VALUE;

                    int previousHorizontal = Integer.MIN_VALUE;
                    if (currentLine > 0)
                        previousHorizontal = sum.get(currentLine - 1).get(currentColumn);
                    if (previousHorizontal + currentTemp > beerLimit || previousHorizontal < 0)
                        previousHorizontal = Integer.MIN_VALUE;

                    int previousDiagonal = Integer.MIN_VALUE;
                    if (currentLine > 0 && currentColumn > 0)
                        previousDiagonal = sum.get(currentLine - 1).get(currentColumn - 1);
                    if (previousDiagonal + currentTemp > beerLimit || previousDiagonal < 0)
                        previousDiagonal = Integer.MIN_VALUE;

                    if (currentLine > 0 || currentColumn > 0)
                        // if not in last line and column: add minimum
                        currentTemp += Math.max(previousDiagonal, Math.max(previousHorizontal, previousVertical));
                    sum.get(currentLine).set(currentColumn, currentTemp);
                }
                print("Current sum: " + sum.get(currentLine).get(currentColumn));
                // print int the opposit order because we start from the end and go to the beginning
                System.out.print(sum.get(currentLine).get(currentColumn) + "-");
            }
            System.out.println();
        }
        
        if (sum.get(0).get(0) > beerLimit || sum.get(0).get(0) < 0)
            return -1;
        return sum.get(maxLines - 1).get(maxColumns - 1);
    }
}
