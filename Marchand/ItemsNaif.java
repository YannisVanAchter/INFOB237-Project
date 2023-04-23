//class à utiliser pour l'algorithme du marchand afin d'avoir la valeur et le poids de chaque produit disponible dans les cas tests
class Items{ 
    private int valeur;
    private int poids;

    public Items(int val, int pds) {
        
        this.valeur = val;
        this.poids = pds;

    }

    public int getValeur(){ 

        return this.valeur;
    
    }

    public int getPoids(){
        
        return this.poids;
    
    }

    public float getRapportValeurPoids(){

        return this.valeur/this.poids;

    }

}
