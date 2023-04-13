import java.util.*;

public class Spec {
    public static void main(String[] args)
    {

    }

    /**
     * @public normal behavior;
     * @requires map != null;
     * @requires map.get(0) != null;
     * @requires 0 <= currentRowId < map.size;
     * @requires 0 <= currentColumnId < map.get(0).size;
     * @requires 0 <= beerLimit;
     * @requires 0 <= currentBeer <= beerLimit;
     * 
     * @ensure \result = \min(\new(currentBeer), )
     */
    public static int findBestPath(
        HashMap<Integer, ArrayList<Integer>> map,
        int beerLimit,
        int currentColumnId,
        int currentRowId,
        int currentBeer)
        {
            return -1;
        }
}
