import java.util.ArrayList;
import java.io.*; 

// Sol récursive 
// force brute: l'idée est de trouver une fleur envahissante dans le tableau grâce à la méthode diviser pour régner

public class test {
    
  public static void main(String args[])
  {
    String[] plantes = {"Rose", "Tulipe", "Marguerite", "Rose", "Tulipe", "Tulipe", "Tulipe", "Tulipe"};
    ArrayList <String> EnvahisseursChamp = new ArrayList <>();
    EnvahisseursChamp.add(trouver_envahisseur(plantes, 0, plantes.length)); 
    System.out.println(EnvahisseursChamp);
  }
    

  public static String trouver_envahisseur(String[] fleurs, int debut, int fin)
  { 
    // Cas de base : 1 seule fleur dans le tableau  
    if (debut == fin){
      return fleurs[debut]; 
    }

    // Sinon diviser pour régner 
    String partieGauche = trouver_envahisseur(fleurs, debut, (fin-debut)/ 2);
    String partieDroite = trouver_envahisseur(fleurs, (fin-debut)/2, fin);
    // Dans le cas d'un tab contenant un nombre impair de fleurs, partieDroite prendra en compte un élément supplémentaire que partieGauche

    // Comparer les résultats pour trouver l'envahisseur final 
    if (partieGauche == partieDroite) {
        return partieGauche;
    }
    else{
      return partieDroite; // Car partieDroite prend en compte un élement en plus que PartieGauche
    } 
  }

}
