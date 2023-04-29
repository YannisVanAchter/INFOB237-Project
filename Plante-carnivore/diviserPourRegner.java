import java.util.ArrayList;
import java.io.*; 

//  To find a invasive flower thanks to "Divide and conquer"

public class diviserPourRegner {

    public static void main(String args[]) {
        lectureFichier(args);
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

            // Lit les 2*n lignes suivantes du fichier
            while (i < n) {
                br.readLine();
                String m_plantes = br.readLine();
                // Crée le tableau contenant les plantes
                String[] plantes = m_plantes.split(", ");
                i += 1;

                try {
                    EnvahisseursChamp.add(trouver_envahisseur(plantes, 0, plantes.length-1));
                } catch (Exception e){
                    System.err.println(e);
                }
               
            }

            fr.close();
            return (EnvahisseursChamp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (new ArrayList<String>());
    }

    public static String trouver_envahisseur(String[] fleurs, int debut, int fin) {
        // Cas de base : 1 seule fleur dans le tableau
        if (debut == fin) { 
            return fleurs[debut];
        }
       
        // Sinon diviser pour régner
        int milieu = (debut + fin) / 2;
        String partieGauche = trouver_envahisseur(fleurs, debut, milieu);

        String partieDroite = trouver_envahisseur(fleurs, milieu + 1, fin);


        // Comparer les résultats pour trouver l'envahisseur final
        if (partieGauche.equals(partieDroite)) {
            return partieDroite;
        } else {
            return partieGauche;
        }
    }

}
