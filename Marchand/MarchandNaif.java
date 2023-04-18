//Question 3

//Cette solution naive a une complexite de O(2^n), ce qui la rend impraticable pour des ensembles de données de grande taille.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MarchandNaif {

    public static void main(String[] args) {
        
        read_file("Marchand.txt"); 
            
    }


    //lecture de fichier
    public static void read_file(String nom_fichier) { 
        try {
            File fichier = new File(nom_fichier);
            Scanner myReader = new Scanner(fichier);
            int n = 0; 
            int i = 0; 
            int j = 0; 


                String line = myReader.nextLine().strip();
                n = Integer.parseInt(line);

            while (i < n){
                line = myReader.nextLine().strip();
                String[] parts = line.split(" ");
                int nb_items = Integer.parseInt(parts[0]);
                int poids_max = Integer.parseInt(parts[1]);
                Items[] sac = new Items[nb_items];

                while(j < nb_items){
                    line = myReader.nextLine().strip();
                    String[] tab = line.split(" ");
                    int valeur = Integer.parseInt(tab[0]);
                    int poids = Integer.parseInt(tab[1]);
                    sac[j] = new Items(valeur, poids);
                    j++;
                }

                System.out.println(find_max_value(sac, nb_items, poids_max));
                i++;
                j = 0;


            }
        } catch (FileNotFoundException f) {
            System.out.println(f);
        }

        
    }

    public static int find_max_value(Items[] items, int nb_items, int poids_max) {
        int max_value = 0;
        for (int i = 0; i < (1 << nb_items); i++) { // boucle sur toutes les combinaisons possibles et (1 << nb_items) <=> 2^nb_items
            int value = 0;
            int weight = 0;
            for (int j = 0; j < nb_items; j++) {
                if ((i & (1 << j)) > 0) { // si l'objet est dans la combinaison
                    value += items[j].getValeur();
                    weight += items[j].getPoids();
                }
            }
            if (weight <= poids_max && value > max_value) { // si la combinaison respecte le poids maximal et que la valeur est plus élevée que la valeur maximale actuelle
                max_value = value;
            }
        }
        System.out.println(max_value);
        return max_value;
    }

}

/*
L'operateur << est l'operateur de decalage a gauche en Java. L'expression (1 << nb_items) signifie que l'on decale le nombre binaire 1 de nb_items bits vers la gauche. 
En d'autres termes, cela revient a elever 2 a la puissance nb_items. Par exemple, si nb_items vaut 3, alors (1 << nb_items) vaut 8
car 1 (en binaire: 0001) decale de 3 bits vers la gauche donne 1000 (en binaire: 1000), qui represente le nombre 8 en base 10.
 */
