import java.util.ArrayList;
import java.io.*; 

// Sol récursive 
// force brute: l'idée est de trouver une fleur envahissante dans le tableau grâce à la méthode diviser pour régner

public class Solrecurs {
  public static String main(String args[])
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
    
      // Lit les 2*n lignes suivantes du fichier
      while (i < n){
        br.readLine(); 
        String m_plantes = br.readLine(); 
        // Crée le tableau contenant les plantes
        String[]plantes = m_plantes.split(", ");
        i+=1;  
        return(trouver_envahisseur(plantes, 0, plantes.length)); 
      }

      fr.close();

    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }



  public static String trouver_envahisseur(String[] fleurs, int debut, int fin)
  { 
    // Cas de base : 1 seule fleur dans le tableau  
    if (fleurs.length == 1){
      return fleurs[0]; 
    }

    // Sinon 
    String partieGauche = trouver_envahisseur(String[] fleurs, 0, fleurs.length/2 )
    String partieDroite = trouver_envahisseur(String[] fleurs, fleurs.length/2, fleur.length) 
    // Dans le cas d'un tab contenant un nombre impair de fleurs, partieDroite prendra en compte un élément supplémentaire que partieGauche

    
    // Comparer les résultats 
    if (partieGauche = partieDroite) {
        return partieGauche;
    }
    else{
      return partieDroite; // Car partieDroite prend en compte un élement en plus que PartieGauche
    } 

    int EnvahisseurGauche = compterOccurence(partieGauche[], partieGauche )
    int EnvahisseurDroite = compterOccurence(partie[], partieDroite)
    
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
