//Question 3


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class MarchandNaif {

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
                System.out.println("Ce sac peux contenir: " + value + " en valeur au maximum");
            }
        } catch (FileNotFoundException f) {
            System.out.println(f);
        }
    }

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

