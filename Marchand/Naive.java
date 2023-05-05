import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class Naive {

    /**
     * @public normal_behavior;
     * @also
     * @public exceptional_behavior;
     * @signals (FileNotFoundException) file not found;
     */
    public static void main(String[] args) {
        
        //lecture de fichier
        try {
            File fichier = new File("./Marchand.txt"); //pathname du fichier 
            Scanner myReader = new Scanner(fichier);
            int n = 0; 

            // lecture du nombre de sacs dont on doit trouver la solution
            String line = myReader.nextLine().strip();
            n = Integer.parseInt(line);

            for (int i = 0; i < n; i++){
                line = myReader.nextLine().strip();
                String[] parts = line.split(" ");

                // paramettre du sac i
                int nb_items = Integer.parseInt(parts[0]);
                int poids_max = Integer.parseInt(parts[1]);
                ArrayList<Items> sac = new ArrayList<Items>(nb_items);

                // création du sac i
                for (int j = 0; j < nb_items; j++){
                    line = myReader.nextLine().strip();
                    String[] tab = line.split(" ");
                    // paramettre d'un Item 
                    int valeur = Integer.parseInt(tab[0]);
                    int poids = Integer.parseInt(tab[1]);

                    sac.add(new Items(valeur, poids));
                }

                int value = findMaxValueNaif(sac, nb_items, poids_max);
                System.out.println(value);
            }
        } catch (FileNotFoundException f) {
            System.out.println(f);
        }
    }

    /**
     * @public normal_behavior; 
     * @requires bag != null && nb_items > 0 && poids_max > 0;
     * @requires (\forall int i; 0 <= i && i < nb_items; bag[i] != null);
     * @requires (\forall int i; 0 <= i && i < nb_items; bag[i] != null ==> bag[i].valeur >= 0 && bag[i].poids >= 0);
     * @requires (\forall int i, j; 0 <= i && i < nb_items && 0 <= j && j < nb_items && i != j; bag[i] != bag[j]); 
     * @ensures \result == \value >= 0 && \value <= poids_max
     */
    public static int findMaxValueNaif(ArrayList<Items> bag, int nb_items, int poids_max) {    
        // Trie le sac par ordre décroissant de valeur/poids
        Collections.sort(bag, Items.VperWcomparator);
        int value = 0;
        for (Items item : bag) {
            if (poids_max - item.getPoids() >= 0) {
                poids_max -= item.getPoids();
                value += item.getValeur();
            }
        }
        return value;
    }

}


/**
 * Complexité :
 * En ce qui concerne la complexité de l'algorithme naïf, elle est de O(n) car nous parcourons une fois une ArrayList. 
 * En effet, ne sachant comment est implémenté \textit{"collection.sort"}, nous ne pouvons pas en connaitre la complexité
 * 
 * Invariant de la boucle for (Items item : bag) {} :
 * Invariant ==> item ⊂ bag && item ∈ Items
 * Variant ==> Passage à l'item suivant 
 * Terminaison ==> lorsque tous les item E Items ont été parcouru
 * 
 * Symboles utilisés pour les preuves :
 * ∈ signifie "appartient"
 * ⊂ signifie "se trouvant"
 * && signifie "et"
 * 
 */

