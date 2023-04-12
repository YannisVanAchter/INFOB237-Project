import java.util.*;
import java.io.*;

public class Main {
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
                    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
                    String[] mapSize = br.readLine().strip().split(" ");
                    int nbLines = Integer.parseInt(mapSize[0]);
                    int nbColumn = Integer.parseInt(mapSize[1]);
                    int beerLimit = Integer.parseInt(mapSize[2]);
                    System.out.println("nbLines: " + nbLines + "\nnbColumn: " + nbColumn + "\nbeerLimit: " + beerLimit);

                    for (int lineId = 0; lineId < nbLines; lineId++) {
                        String[] line = br.readLine().strip().split(" ");
                        ArrayList<Integer> cells = new ArrayList<>();
                        for (String cell : line) {
                            cells.add(Integer.parseInt(cell));
                        }
                        map.put(lineId, cells);
                    }

                    System.out.println(findBestPath(map, beerLimit));
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
     * Find the best path to the end of the map
     * 
     * @param map       The map of N x M cells where each cell is a quantity of beer
     * @param beerLimit The maximum quantity of beer that can be carried
     * @return The minimum of beer that will be drinked, if there is no correct path
     *         return -1
     */
    public static int findBestPath(HashMap<Integer, ArrayList<Integer>> map, int beerLimit) {
        int best = findBestPath(map, beerLimit, 0, 0, 0);
        if (best > beerLimit || best == 0)
            return -1;
        return best;
    }

    public static int findBestPath(
            HashMap<Integer, ArrayList<Integer>> map,
            int beerLimit,
            int currentColumnId,
            int currentRowId,
            int currentBeer) {

        // check if we are at the end of the map
        if (currentColumnId == map.get(0).size() - 1 && currentRowId == map.size() - 1) {
            return currentBeer + map.get(currentRowId).get(currentColumnId);
        }

        // new horizontal position
        int beerHorizontal = Integer.MAX_VALUE;
        if (currentColumnId + 1 < map.get(0).size())
        {
            beerHorizontal = currentBeer + map.get(currentRowId).get(currentColumnId + 1);
            beerHorizontal = findBestPath(map, beerLimit, currentColumnId + 1, currentRowId, beerHorizontal);
        }

        // new vertical position
        int beerVertical = Integer.MAX_VALUE;
        if (currentRowId + 1 < map.size())
        {
            beerVertical = currentBeer + map.get(currentRowId + 1).get(currentColumnId);
            beerVertical = findBestPath(map, beerLimit, currentColumnId, currentRowId + 1, beerVertical);
        }

        // new diagonal position
        int beerDiagonal = Integer.MAX_VALUE;
        if (currentColumnId + 1 < map.get(0).size() && currentRowId + 1 < map.size())
        {
            beerDiagonal = currentBeer + map.get(currentRowId + 1).get(currentColumnId + 1);
            beerDiagonal = findBestPath(map, beerLimit, currentColumnId + 1, currentRowId + 1, beerDiagonal);
        }

        // get best option
        return Math.min(
            beerVertical,
            Math.min(
                beerHorizontal,
                beerDiagonal
            )
        );
    }
}