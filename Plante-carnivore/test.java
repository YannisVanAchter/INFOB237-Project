import java.util.*;
import java.io.*; 

// Sol récursive 
// force brute: l'idée est de trouver une fleur envahissante dans le tableau grâce à la méthode diviser pour régner

public class test {
    
  public static void main(String args[])
  {
    String[] plantes = {"Rose", "Tulipe", "Marguerite", "Rose", "Tulipe", "Tulipe", "Tulipe", "Tulipe"};
    HashMap <String, Integer> EnvahisseursChamp = trouver_envahisseur(plantes);
    System.out.println(EnvahisseursChamp);
  }


  public static HashMap<String, Integer> trouver_envahisseur(String[] fleurs)
  {
    HashMap<String, Integer> final_envahiseur = trouver_envahisseur(fleurs, 0, fleurs.length);
    
  }

  public static HashMap<String, Integer> trouver_envahisseur(HashMap<String, Integer> fleurs)
  {
    HashMap<String, Integer> fin = new HashMap<>();
    int iMax = 0;
    String sMax = null
    for (String fleur: fleurs.keySet())
    {
      if (iMax < fleurs.get(fleur)) {
        sMax = fleur;
        iMax = fleurs.get(fleur);
        fin.put(sMax, iMax);
      }
    }
    
  }
    

  public static HashMap<String, Integer> trouver_envahisseur(String[] fleurs, int debut, int fin)
  {
 
    HashMap<String, Integer> envahisseur = new HashMap<>();

    // Cas de base : 1 seule fleur dans le tableau  
    if (debut == fin){
      envahisseur.put(fleurs[debut], 1);
      return envahisseur; 
    }


    // Sinon diviser pour régner 
    ArrayList<String> partieGauche = trouver_envahisseur(fleurs, debut, (fin-debut)/ 2);
    ArrayList<String> partieDroite = trouver_envahisseur(fleurs, (fin-debut)/2, fin);
    // Dans le cas d'un tab contenant un nombre impair de fleurs, partieDroite prendra en compte un élément supplémentaire que partieGauche

    // Comparer les résultats pour trouver l'envahisseur final 
    // if (partieGauche == partieDroite) {
    //     return partieGauche;
    // }
    // else{
    //   return partieDroite; // Car partieDroite prend en compte un élement en plus que PartieGauche
    // } 
    HashMap<String, Integer> final_envahiseur = new HashMap<>();
    for (String fleur: partieDroite.keySet()) {
      final_envahiseur.put(fleur, partieDroite.get(fleur))
    }
    Set<String> keyDroite = final_envahiseur.keySet()
    for (String fleur: partieGauche.keySet())
    {
      if (keyDroite.contains(fleur))
      {
        int inGauche = partieGauche.get(fleur);
        int inDroite = partieDroite.get(fleur);

        int total = inDroite + inGauche;
        final_envahiseur.replace(fleur, total);
      }
      else {
        final_envahiseur.put(fleur, partieGauche.get(fleur));
      }
    }
  }

}
