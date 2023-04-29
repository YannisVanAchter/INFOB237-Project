public class gpt{
    
    public static void main(String args[]){
        String[] plantes = {"Rose", "Tulipe", "Marguerite", "Rose", "Tulipe", "Tulipe", "Tulipe", "Tulipe"};
        String res = trouverMajoritaire(plantes, 0, plantes.length-1);
        System.out.println(res);

    }

    public static String trouverMajoritaire(String[] tableau, int debut, int fin) {
        // Cas de base : tableau d'une seule valeur
        if (debut == fin) {
            return tableau[debut];
        }

        // Diviser le tableau en deux parties
        int milieu = (debut + fin) / 2;
        String gauche = trouverMajoritaire(tableau, debut, milieu);
        String droite = trouverMajoritaire(tableau, milieu + 1, fin);

        // Combiner les résultats
        if (gauche.equals(droite)) {
            // Le même élément est majoritaire dans les deux parties
            return gauche;
        } else {
            // Compter le nombre d'occurrences de chaque élément dans les deux parties
            int compteurGauche = 0;
            int compteurDroite = 0;
            for (int i = debut; i <= fin; i++) {
                if (tableau[i].equals(gauche)) {
                    compteurGauche++;
                } else if (tableau[i].equals(droite)) {
                    compteurDroite++;
                }
            }

            // Retourner l'élément majoritaire (s'il existe)
            if (compteurGauche > (fin - debut + 1) / 2) {
                return gauche;
            } else if (compteurDroite > (fin - debut + 1) / 2) {
                return droite;
            } else {
                return("null");
            }
        }
    }
} 
