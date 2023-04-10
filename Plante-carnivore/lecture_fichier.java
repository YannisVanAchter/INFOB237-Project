import java.io.*;

public class lecture_fichier
{
  public static void main(String args[])
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
    
        // Lit les nx2 lignes suivantes du fichier
      while (i < n){
        br.readLine(); 
        String m_plantes = br.readLine(); 
        // Crée le tableau contenant les plantes
        String[]plantes = m_plantes.split(", "); 
        
        System.out.println(m_plantes); 
        i+=1; 
      }

      fr.close();
      System.out.println("Tt ok");

    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}