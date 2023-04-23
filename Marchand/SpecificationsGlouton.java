public class SpecificationsGlouton {

    /**
     * @public normal_behavior;
     * @also
     * @public exceptional_behavior;
     * @signals (FileNotFoundException) file not found;
     */
    public static void main(String[] args) throws FileNotFoundException {
        //return 0
    }


    /**
     * @public normal_behavior; 
     * @requires bag != null && nb_items > 0 && poids_max > 0;
     * @ensures \valeur_totale >= 0;
     * @ensures (\forall int i; 0 <= i && i < nb_items; bag[i] != null);
     * @ensures (\forall int i; 0 <= i && i < nb_items; bag[i] != null ==> bag[i].valeur >= 0 && bag[i].poids >= 0);
     * @ensures (\forall int i, j; 0 <= i && i < nb_items && 0 <= j && j < nb_items && i != j; bag[i] != bag[j]); //pas de doublons dans bag
     */
    public static int find_max_value(Items[] bag, int nbritems, int maxpoids) {
        //return 0
    }



}
