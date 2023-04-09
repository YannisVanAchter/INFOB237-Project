import java.util.Arrays; 
import java.util.ArrayList;

// A spécifier
// Faire fct pr fichier -> dico
// Sol naive 


public class test {
    public static void main(String args[])
    {   String[] fleurs = {"Rose", "Pissenlit", "Tulipe", "Paquerette", "Rose",  "Pissenlit"};
        Arrays.sort(fleurs);  // Tri du tableau 
        ArrayList<String> fleurEnvahissante = new ArrayList<>();
    
        for (String fleur : fleurs){
            int compteur = compterOccurence(fleurs, fleur);
            if ( compteur >= fleurs.length/2){
                if (!fleurEnvahissante.contains(fleur)){
                    fleurEnvahissante.add(fleur); 
                }
            }
        }

        if (fleurEnvahissante.size() == 0) {
            System.out.println("null");
        }
        else{
            System.out.println(fleurEnvahissante);
        }  
    }

    public static int compterOccurence (String[] tab, String elem){
        int compteur = 0; 
        for (int i=0; i< tab.length; i++){
            if (tab[i].equals(elem)){
                compteur++;
            }
        }
        return(compteur); 
    }
}
