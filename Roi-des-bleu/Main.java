import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            File file = new File("maps.txt");
            FileReader fr = new FileReader(file);
            try 
            { 
                BufferedReader br = new BufferedReader(fr);
                String nbMapsString = br.readLine().strip();
                int nbMaps = Integer.parseInt(nbMapsString);

                for (int mapId = 0; mapId < nbMaps; mapId++)
                {
                    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
                    String[] mapSize = br.readLine().split(" ");
                    int nbLines = Integer.parseInt(mapSize[0]);
                    int nbColumn = Integer.parseInt(mapSize[1]);
                    int beerLimit = Integer.parseInt(mapSize[2]);
                    System.out.println("nbLines: " + nbLines + "\nnbColumn: " + nbColumn + "\nbeerLimit: " + beerLimit);

                    for (int lineId = 0; lineId < nbLines; lineId++)
                    {
                        String[] line = br.readLine().split(" ");
                        ArrayList<Integer> cells = new ArrayList<>();
                        for (String cell: line)
                        {
                            cells.add(Integer.parseInt(cell));
                        }
                        map.put(lineId, cells);
                    }

                    System.out.println(findBestPath(map, beerLimit));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Find the best path to the end of the map
     * @param map The map of N x M cells where each cell is a quantity of beer
     * @param beerLimit The maximum quantity of beer that can be carried
     * @return The minimum of beer that will be drinked, if there is no correct path return -1
     */
    public static int findBestPath(HashMap<Integer, ArrayList<Integer>> map, int beerLimit)
    {
        int totalBeer = -1;
        int currentLineId = 0;
        int currentColumnId = 0;

        System.out.println(map);
        System.out.println(beerLimit);

        return totalBeer;
    }
}