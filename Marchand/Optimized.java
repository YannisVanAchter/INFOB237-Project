import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Optimized {

    /**
     * @public normal_behavior;
     * @also
     * @public exceptional_behavior;
     * @signals (FileNotFoundException) file not found;
     */
    public static void main(String[] args) throws FileNotFoundException {
        File fichier = new File("./Marchand.txt");//attention pathname 
        Scanner myReader = new Scanner(fichier);
        int n = 0;
        int i = 0;
        int j = 0;

        if (myReader.hasNextLine()) {
            String line = myReader.nextLine().strip();
            n = Integer.parseInt(line);
        }

        while (i < n){
            String line = myReader.nextLine().strip();
            String[] parts = line.split(" ");
            int nb_items = Integer.parseInt(parts[0]);
            int poids_max = Integer.parseInt(parts[1]);
            Items[] sac = new Items[nb_items];

            while(j < nb_items){
                line = myReader.nextLine().strip();
                String[] tab = line.split(" ");
                int valeur = Integer.parseInt(tab[0]);
                int poids = Integer.parseInt(tab[1]);
                float VperW = (float) valeur/poids;
                sac[j] = new Items(valeur, poids, VperW);
                j++;
            }
        
        // System.out.println(sac);
            System.out.println(findMaxValue(sac, nb_items, poids_max));
            i++;
            j = 0;

        }
    }

    /**
     * @public normal_behavior; 
     * @requires bag != null && nb_items > 0 && poids_max > 0;
     * @ensures \result == \valeur_totale >= 0;
     * @ensures (\forall int i; 0 <= i && i < nb_items; bag[i] != null);
     * @ensures (\forall int i; 0 <= i && i < nb_items; bag[i] != null ==> bag[i].valeur >= 0 && bag[i].poids >= 0);
     * @ensures (\forall int i, j; 0 <= i && i < nb_items && 0 <= j && j < nb_items && i != j; bag[i] != bag[j]); //pas de doublons dans bag
     */
    public static int findMaxValue(Items[] bag, int nbritems, int maxpoids) {

        ArrayList<Items> products = new ArrayList<Items>();
        for (int i = 0; i < nbritems; i++) { //boucle pour ajouter un élément du sac dans la liste 
            products.add(bag[i]);
        }

        Collections.sort(products, Items.VperWcomparator); //trier les objets dans l'ordre décroissant

        // for (Items str : products) {
        //     System.out.println(str);
        // }

        // initialisation 
        int current_poids = 0;
        int current_valeur = 0;
        int poids_total = 0;
        int valeur_totale = 0;

        for (int j = 0; j < nbritems; j++) { 
            Items current_item = products.get(j); // l'objet actuel a une valeur et un poids actuel 
            current_poids = current_item.getPoids();
            current_valeur = current_item.getValeur();
            if (poids_total + current_poids <= maxpoids) { // si le poids total + le poids actuel sont plus petit ou égal au poids maximum 
                valeur_totale += current_valeur; // la valeur totale est incrémentée 
                poids_total += current_poids; // la poids total est incrémenté 
            } 
            else {
                float reste = ((float)maxpoids /current_poids); //dans le cas où on dépasserait le poid max du sac 
                valeur_totale += (current_valeur * reste);
                break;
            }
        }

        return valeur_totale;
    }

}

/**
 * Le glouton ne trouve pas tjrs la solution optimale sinon
 * 
 * 
 * Complexité : 
 * 
 * O(nbritems) = complexité de la boucle for
 * (O(n) = complexité de la boucle for each)
 * O(nbritems) = complexité de la boucle for et du if imbriqué 
 * ==> complexité algo Glouton = O(nbritems)
 * 
 */
