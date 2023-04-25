
// import java.util.*;
import java.io.*;

public class Solution {

    public static void print(Object s)
    {
        System.out.println(s.toString());
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

                    System.out.println(findBestPath(map, beerLimit, nbColumns - 1, nbLines -1 ));
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
        print("Beer limit: " + beerLimit);
        print("Maximum lines: " + maxLines);
        print("Maximum column: " + maxColumns);

        int[][] sum = new int[maxColumns][maxLines];
        // sum = ExploreGraph(map, beerLimit, maxColumns, maxLines, sum, maxColumns, maxLines);
        for (int currentLine = maxLines; currentLine > 0; currentLine--) {
            print("Current line: " + currentLine);
            for (int currentColumn = maxColumns; currentColumn > 0; currentColumn--) {
                print("Current column: " + currentColumn);
                int previousVertical = Integer.MAX_VALUE;
                if (currentColumn < maxColumns)
                {
                    previousVertical = sum[currentLine][currentColumn + 1];
                }

                int previousHorizontal = Integer.MAX_VALUE;
                if (currentLine < maxLines)
                {
                    previousHorizontal = sum[currentLine + 1][currentColumn];
                }

                int previousDiagonal = Integer.MAX_VALUE;
                if (currentLine < maxLines && currentColumn < maxColumns)
                {
                    previousDiagonal = sum[currentLine + 1][currentColumn + 1];
                }

                sum[currentLine][currentColumn] = map[currentLine][currentColumn];
                if (currentLine < maxLines && currentColumn < maxColumns)
                // if not in last line: add minimum
                    sum[currentLine][currentColumn] += Math.min(previousDiagonal, Math.min(previousHorizontal, previousVertical));
            }
        }

        if (sum[0][0] > beerLimit)
            return -1;
        // by using the minimum value we can consider the maximum value to be acceptable is already computed
        if (sum[0][0] == beerLimit)
            return sum[0][0];
        
        // get path where we drink the most beer and stay under the limit
        
        System.out.println("TOBE IMPLEMENT");
        return sum[0][0];
    }

    // private static int[][] ExploreGraph(int[][] map, int beerLimit, int maxColumn, int maxLine, int[][] sum,
    //         int currentColumn, int currentLines) {
        
    //     if (currentLines == maxLine && currentColumn == maxColumn)
    //     {
    //         sum[currentLines][currentColumn] = map[currentLines][currentColumn];
    //     }
    //     else if (currentLines >= 0 && currentColumn >= 0)
    //     {
    //         int previousVertical = Integer.MAX_VALUE;
    //         if (currentColumn < maxLine)
    //         {
    //             previousVertical = sum[currentLines][currentColumn + 1];
    //         }
    //         int previousHorizontal = Integer.MAX_VALUE;
    //         if (currentLines < maxLine)
    //         {
    //             previousHorizontal = sum[currentLines + 1][currentColumn];
    //         }
    //         int previousDiagonal = Integer.MAX_VALUE;
    //         if (currentLines < maxLine && currentColumn < maxLine)
    //         {
    //             previousDiagonal = sum[currentLines + 1][currentColumn + 1];
    //         }

    //         sum[currentLines][currentColumn] = map[currentLines][currentColumn] + Math.min(previousDiagonal, Math.min(previousHorizontal, previousVertical));
    //     }

    //     return sum;
    // }
}
