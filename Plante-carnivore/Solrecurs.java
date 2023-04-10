import java.util.ArrayList;
import java.io.*; 

// Sol récursive 

public class Solrecurs {
    public static void main(String args[])
  {
    try
    {
      // Le fichier du champ contenant toutes les fleurs 
      File file = new File("champ.txt");
      // Créer l'objet File Reader
      FileReader fr = new FileReader(file);
      // Créer l'objet BufferedReader
      BufferedReader br = new BufferedReader(fr);
      // Voit combien il y a de lignes dans le champ
      String nombreLignes = br.readLine();
      int n = Integer.parseInt(nombreLignes);

      int i = 0; 
    
        // Lit les nx2 lignes suivantes du fichier
      while (i < n){
        br.readLine(); 
        String m_plantes = br.readLine(); 
        // Crée le tableau contenant les plantes
        String[]plantes = m_plantes.split(", "); 
        trouver_envahisseur(plantes);
        i+=1; 
      }

      fr.close();

    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

    public static void trouver_envahisseur(String[] fleurs)
    {  
        ArrayList<String> fleurEnvahissante = new ArrayList<>();
    
        for (String fleur : fleurs){
            int compteur = compterOccurence(fleurs, fleur);
            if ( compteur > fleurs.length/2){
                if (!fleurEnvahissante.contains(fleur)){
                    fleurEnvahissante.add(fleur); 
                }
            }
        }

        if (fleurEnvahissante.size() == 0) {
            System.out.println("null");
        }
        else{
            System.out.println(fleurEnvahissante.get(0));
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
