import java.util.ArrayList;
import java.io.*; 

//  To find a invasive flower thanks to "Divide and conquer"

public class DiviserPourRegner {

    public static void main(String args[]) {
        ArrayList<String> Envahisseurs = lectureFichier(args);
        for (String envahisseur: Envahisseurs){
            System.out.println(envahisseur);
        }
    }

    public static ArrayList<String> lectureFichier(String args[]) {
        try {
           
            File file = new File("champ.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String nombreLignes = br.readLine().strip();
            int n = Integer.parseInt(nombreLignes);

            int i = 0;

            ArrayList<String> EnvahisseursChamp = new ArrayList<>();

            while (i < n) {
                br.readLine();
                String m_plantes = br.readLine();
                // Create an array with the plants of the row
                String[] plantes = m_plantes.split(", ");
                EnvahisseursChamp.add(trouverEnvahisseur(plantes, 0, plantes.length-1)); 
                i += 1;
            }

            fr.close();
            return (EnvahisseursChamp);

        } catch (IOException e) {
            System.out.println(e);
        }

        return (new ArrayList<String>());
    }

    public static String trouverEnvahisseur(String[] fleurs, int debut, int fin) {
        // If there is only one flower in the array (base case)
        if (debut == fin) { 
            return fleurs[debut];
        }
       
        // Else divide and conquer
        int milieu = (debut + fin) / 2;
        String partieGauche = trouverEnvahisseur(fleurs, debut, milieu);
        String partieDroite = trouverEnvahisseur(fleurs, milieu + 1, fin);


        // Compare results obtained to find the final invasive plant if it exists
        if (partieGauche.equals(partieDroite)) {
            return partieGauche;
        } else{
            // If the two sides didn't find the same invasive plant, we have to count the occurence of the flowers
            int compteurPartieGauche = 0; 
            int compteurPartieDroite = 0; 
            for (int i = debut; i<= fin; i++){
                if (fleurs[i].equals(partieGauche)){
                    compteurPartieGauche += 1;
                }  

                if (fleurs[i].equals(partieDroite)){
                    compteurPartieDroite +=1; 
                }
            }

            // Look if the plant is invasive
            if (compteurPartieGauche > (milieu+1)){
                return partieGauche;
            }
            if (compteurPartieDroite > (milieu+1)){
                return partieDroite; 
            }  
        } 
        // If there is no invasive plant 
        return("null");
    
    }

}
