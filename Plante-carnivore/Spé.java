
// Spécification du problème en utilisant JML 

public class Spé {

    /*@ public normal_behavior;
    @also 
    @exceptional_behavior                                    
    @signals IOException; 
    @*/
    public static void main(String[] args)
    {
    }

    /*@ public normal_behavior;
    @requires tab[] != null && elem != null;
    @invariant compteur >= 0;
    @assignable compteur; 
    @ensure \result == compteur
    @*/  
    public static int compterOccurence (String[] tab, String elem){
    }


    /*@ public normal_behavior;  
    @requires fleurs != null && fleurs.length > 0; 
    @invariant compteur >= 0;  
    @assignable compteur, fleurEnvahissante; 
    @*/
    public static void trouver_envahisseur(String[] fleurs)
    {
    }
}
