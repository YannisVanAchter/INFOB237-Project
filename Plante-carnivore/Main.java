
// Spécification du problème en utilisant JML 


public class Main {

    /*@ public normal_behavior 
    @ requires ;
    @ ensures;

    @ \result                   // VA RENVOYER SOIT LE NOM DE LA FLEUR SOIT NULL 

    @*/ 
    public static void main(String[] args)
    {
    }

    /*@ public normal_behavior                                     // FICHIER BIEN FAIT 
    @ requires 
    @ ensures

    @also 
    @exceptional_behavior                                     // FICHIER PAS BON 
    @requires 
    @ensures 
    @assignable \nothing; 
    @signals (IllegalFileException) true; 
    @*/
    public static void creation_tableau (fichier)
    {
    }


    /*@ public normal_behavior                                     
    @ requires 
    @ ensures
    @*/
    public static void resolution (creation_tableau)
    {
    }
}