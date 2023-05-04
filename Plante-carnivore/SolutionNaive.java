import java.util.ArrayList;
import java.io.*; 


// Goal : to see if a plant is invasive (for each row of M plants, if a plant appears > M/2 times )
// If there is only a plant, it is invasive (because it appears > M/2 times)


public class solutionNaive {

    /*@ public normal_behavior;
    *@also 
    *@exceptional_behavior                                    
    *@signals IOException; 
    @*/
    public static void main(String args[])
  {
    try
    {
      // File contained all flowers
      File file = new File("champ.txt");
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      // See how many rows of flowers there are in the field  
      String numberRows = br.readLine();
      int n = Integer.parseInt(numberRows);

      int i = 0; 
    
      // Read the 2*n rows following in the file
      while (i < n){
        br.readLine(); 
        String m_plantes = br.readLine(); 
        String[]plantes = m_plantes.split(", "); 
        trouverEnvahisseur(plantes);
        i+=1; 
      }

      fr.close();

    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }


    /*@ public normal_behavior;  
    *@requires fleurs != null && fleurs.length > 0; 
    *@invariant compteur >= 0;  
    *@assignable compteur, fleurEnvahissante; 
    @*/
    public static void trouverEnvahisseur(String[] fleurs)
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


   /*@ public normal_behavior;
    *@requires tab[] != null && elem != null;
    *@invariant compteur >= 0;
    *@assignable compteur; 
    *@ensure \result == compteur
    @*/  
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


// Complexité de O(n^2) 


/* Boucles - invariants - preuves : 

for (String fleur : fleurs) : au sein de trouver_envahisseur(String[] fleurs)):
 Variant de boucle: passage à l'élément suivant 
 Invariant de boucle: fleur ∈ fleurs 
 Terminaison : quand tous les éléments fleur ∈ fleurs ont été parcouru

for (int i=0; i< tab.length; i++) : au sein de compterOccurence(String[] tab, String elem):
 Variant de boucle: i (i++)
 Invariant de boucle: 0<=i<tab.length
 Terminaison : i < tab.length 
*/