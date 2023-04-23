import java.util.Comparator;

public class Items implements Comparable<Items>{
    private int valeur;
    private int poids;

    private float VperW;

    public Items(int val, int pds, float vperw) {
        this.valeur = val;
        this.poids = pds;
        this.VperW = vperw;
    }

    public static Comparator<Items> VperWcomparator = new Comparator<Items>() {
        @Override
        public int compare(Items s1, Items s2) {
            float cpr1 = s1.getVperW();
            float cpr2 = s2.getVperW();

            if (cpr1 < cpr2)
                return 1;
            else
                return -1;
        }};

    public int getValeur(){ 
        return this.valeur;
    }

    public int getPoids(){
        return this.poids;
    }

    public float getVperW(){
        return this.VperW;
    }

    @Override
    public String toString() {
        return "[ poids=" + poids + ", valeur =" + valeur + ", valeursurpoids " + String.format("%.2f",VperW)+ "]";
    }

    @Override
    public int compareTo(Items other) {
        return Float.compare(getValeur(), other.getVperW());
    }


}