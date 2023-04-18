import java.util.ArrayList;
import java.io.*; 

// Sol récursive 
// force brute: l'idée est de trouver une fleur envahissante dans le tableau grâce à la méthode diviser pour régner

public class Solrecurs {

  public static void main(String args[])
  {
    lectureFichier(args);  
  }
    
  public static ArrayList<String> lectureFichier (String args[])
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

      ArrayList <String> EnvahisseursChamp = new ArrayList <>();
    
      // Lit les 2*n lignes suivantes du fichier
      while (i < n){
        br.readLine(); 
        String m_plantes = br.readLine(); 
        // Crée le tableau contenant les plantes
        String[]plantes = m_plantes.split(", ");
        i+=1; 
        EnvahisseursChamp.add(trouver_envahisseur(plantes, 0, plantes.length)); 
      }
 

      fr.close();
      return(EnvahisseursChamp);
    }

    catch(IOException e)
    {
      e.printStackTrace();
    }
  
  return(new ArrayList<String>());
 
  }

  public static String trouver_envahisseur(String[] fleurs, int debut, int fin)
  { 
    // Cas de base : 1 seule fleur dans le tableau  
    if (debut == fin){
      return fleurs[debut]; 
    }

    // Sinon diviser pour régner 
    String partieGauche = trouver_envahisseur(fleurs, debut, (fin - debut)/ 2);
    String partieDroite = trouver_envahisseur(fleurs, (fin - debut)/2, fin);
    // Dans le cas d'un tab contenant un nombre impair de fleurs, partieDroite prendra en compte un élément supplémentaire que partieGauche

    // int EnvahisseurGauche = compterOccurence(fleurs, partieGauche, 0, fleurs.length/2);
    // int EnvahisseurDroite = compterOccurence(fleurs, partieDroite, fleurs.length/2, fleurs.length);


    // Comparer les résultats pour trouver l'envahisseur final 
    if (partieGauche == partieDroite) {
        return partieGauche;
    }
    else{
      return partieDroite; // Car partieDroite prend en compte un élement en plus que PartieGauche
    } 
  }

}
