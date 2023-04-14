public class Specifications {

    /**
     * @public exceptional behavior;
     * @signals (FileNotFoundException) file not found;
     */
    public static void main(String[] args) throws FileNotFoundException{
        return 0
    }

    /**
     * @public exceptional behavior;
     * @signals (FileNotFoundException) file not found;
     */
    public static void read_file(String nom_fichier) throws FileNotFoundException {

    }

    /**
     * @public normal behavior; 
     * @requires items != null && nb_items > 0 && poids_max > 0;
     * @requires (\forall int i; 0 <= i && i < nb_items; items[i] != null);
     * @ensures \result >= 0;
     * @ensures (\forall int i; 0 <= i && i < nb_items; items[i] != null ==> items[i].valeur >= 0 && items[i].poids >= 0);
     * @ensures (\forall int i, j; 0 <= i && i < nb_items && 0 <= j && j < nb_items && i != j; items[i] != items[j]); //pas de doublons dans items
     * @ensures (\forall int i; 0 <= i && i < nb_items; \old(items[i].valeur) == items[i].valeur && \old(items[i].poids) == items[i].poids); //les differents items ne peuvent pas etre modifier durant cette methode
     */
    public static int find_max_value(Items[] items, int nb_items, int poids_max) {

    }

