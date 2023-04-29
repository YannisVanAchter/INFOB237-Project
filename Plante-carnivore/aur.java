import java.util.ArrayList;
import java.io.*; 

public class aur {

    public static void main(String args[]) {
        String[] plantes = {"Rose", "Tulipe", "Marguerite", "Rose", "Tulipe"};
        ArrayList<String> EnvahisseursChamp = new ArrayList<>();
        EnvahisseursChamp.add(trouver_envahisseur(plantes, 0, plantes.length-1));
        System.out.println("FINITO");
        System.out.println(EnvahisseursChamp);
    }

    public static String trouver_envahisseur(String[] fleurs, int debut, int fin) {
        // Cas de base : 1 seule fleur dans le tableau
        if (debut == fin) { 
            return fleurs[debut];
        }
       
        // Sinon diviser pour régner
        int milieu = (debut + fin) / 2;

        System.out.println(fin);

        String partieGauche = trouver_envahisseur(fleurs, debut, milieu);
        System.out.println(partieGauche);
        String partieDroite = trouver_envahisseur(fleurs, milieu + 1, fin);


        // Comparer les résultats pour trouver l'envahisseur final
        if (partieGauche.equals(partieDroite)) {
            return partieDroite;
        } else {
                int compteurGauche = 0;
                int compteurDroite = 0;
                for ( int i = debut; i <= fin; i++) {
                    if (fleurs[i].equals(partieGauche)) {
                        compteurGauche++;
                    } else if (fleurs[i].equals(partieDroite)) {
                        compteurDroite++;
                    }
                }

                if (compteurGauche > (fin - debut + 1) / 2) {
                    return partieGauche;
                } else if (compteurDroite > (fin - debut + 1) / 2) {
                    return partieDroite;
                } else {
                    return ("null");
                }      
            }
    }
}