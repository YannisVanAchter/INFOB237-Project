import java.util.*;
import java.io.*; 

// Sol récursive 
// force brute: l'idée est de trouver une fleur envahissante dans le tableau grâce à la méthode diviser pour régner

public class test {
    
  public static void main(String args[])
  {
    String[] plantes = {"Rose", "Tulipe", "Marguerite", "Rose", "Tulipe", "Tulipe", "Tulipe", "Tulipe"};
    HashMap <String, Integer> EnvahisseursChamp = trouver_envahisseur(plantes);
    for (String fleur: EnvahisseursChamp.keySet())
      System.out.println(fleur);
  }


  public static HashMap<String, Integer> trouver_envahisseur(String[] fleurs)
  {
    HashMap<String, Integer> final_envahiseur = compteTotalParFleur(fleurs, 0, fleurs.length - 1);

    final_envahiseur = trouver_envahisseur(final_envahiseur);
    return final_envahiseur;
  }

  public static HashMap<String, Integer> trouver_envahisseur(HashMap<String, Integer> fleurs)
  {
    HashMap<String, Integer> fin = new HashMap<>();
    int iMax = 0;
    String sMax = null;
    boolean hasOneChange = true;
    Set<String> keyFleur = fleurs.keySet();
    while(hasOneChange) {
      hasOneChange = false;
      for (String fleur: keyFleur)
      {
        if (iMax < fleurs.get(fleur)) {
          sMax = fleur;
          iMax = fleurs.get(fleur);
          hasOneChange = true;
        }
      }
    }
    fin.put(sMax, iMax);
    return fin;
  }
    

  public static HashMap<String, Integer> compteTotalParFleur(String[] fleurs, int debut, int fin)
  {
 
    HashMap<String, Integer> envahisseur = new HashMap<>();

    // Cas de base : 1 seule fleur dans le tableau  
    if (debut >= fin){
      envahisseur.put(fleurs[debut], 1);
      return envahisseur; 
    }


    // Sinon diviser pour régner 
    HashMap<String, Integer> partieGauche = compteTotalParFleur(fleurs, debut, (int) (fin-debut)/ 2);
    HashMap<String, Integer> partieDroite = compteTotalParFleur(fleurs, (int) (fin-debut)/2, fin);
    // Dans le cas d'un tab contenant un nombre impair de fleurs, partieDroite prendra en compte un élément supplémentaire que partieGauche

    // Comparer les résultats pour trouver l'envahisseur final 
    // if (partieGauche == partieDroite) {
    //     return partieGauche;
    // }
    // else{
    //   return partieDroite; // Car partieDroite prend en compte un élement en plus que PartieGauche
    // } 

    // merge (partieDroite + partieGauche -> final_envahiseur)
    HashMap<String, Integer> final_envahiseur = new HashMap<>();
    
    // prend les clé de la partie droite et les ajoute au résultats final
    Set<String> keyDroite = partieDroite.keySet();
    for (String fleur: keyDroite) {
      final_envahiseur.put(fleur, partieDroite.get(fleur));
    }

    // prend les clé à gauche, test si elle sont dans le résultat final
    for (String fleur: partieGauche.keySet())
    {
      if (keyDroite.contains(fleur))
      { // si dans final prend la somme de courant (droite) + courant (gauche) = final_envahiseur[fleur]
        int inGauche = partieGauche.get(fleur);
        int inDroite = partieDroite.get(fleur);

        int total = inDroite + inGauche;
        final_envahiseur.replace(fleur, total);
      }
      else {
        // ajoute au dictionary les clé à gauche qui ne sont pas à droite
        final_envahiseur.put(fleur, partieGauche.get(fleur));
      }
    }
    return final_envahiseur;
  }

}
