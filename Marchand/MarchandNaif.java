//Question 3


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarchandNaif {

    public static void main(String[] args) {
        
        //lecture de fichier
        try {
            File fichier = new File("Marchand.txt"); //pathname du fichier 
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
                    sac[j] = new ItemsNaif(valeur, poids);
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

    public static int find_max_value(Items[] bag, int nb_items, int poids_max) {
        int valeurMax = 0; 
        int subtab = (int) Math.pow(2, nb_items); //création d'une sous-liste et Math.pow = fct en java qui permet de calculer une puissance à l'aide d'une base et d'un exposant 
        for (int i = 0; i < subtab; i++) {
            int current_pd = 0;
            int current_val = 0; 
            for (int j = 0; j < nb_items; j++){
                if (((i >> j) & 1) == 1) { // si on rajoute un élément 
                    current_pd += bag[j].getPoids();
                    current_val += bag[j].getValeur();
                } 
            }
            if (current_pd <= poids_max && current_val > valeurMax){
                return current_val = valeurMax;
            }
        }      
        return 0;
    }

}

