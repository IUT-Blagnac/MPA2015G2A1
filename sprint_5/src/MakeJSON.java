import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Classe MakeJSON permettant de modifier des fichiers json.
 * 
 * @author groupe2A1
 * @version 1.0
 */
public class MakeJSON {

	public static void main(String[] args) {
		makeJSON("./OPTIweb/tests/");// Appel de la fonction avec le chemin ou nous voulons les fichiers JSON
	}
	
	/**
	 * Remplace (ou créé) les fichiers JSON pour que les tests soient à jour.
	 * 
	 * @param chemin  Le chemin vers les fichiers à créer / remplacer
	 */
	static void makeJSON(String chemin){
		Writer out = null; //Permet d'ecrire dans un fichier
		ArrayList<String[]> intervenants = LibFichierCSV.lireFichier(LibFichierCSV.ficInter());
		ArrayList<String[]> etudiants = LibFichierCSV.lireFichier(LibFichierCSV.ficEtu());
		String chaineInterventants = "[\n"; // Le contenu du fichier d'intervenants
		String chaineEtudiants = "[\n"; // Le contenu du fichier d'etudiants
		int compteur = 0; // sert a savoir ou on en est de la liste
		int tailleEtu = etudiants.size(); 
		
		for(String[] tab : intervenants) { // parcours des intervenants
			if(compteur == 0) {
				compteur++;
				continue; // On ne traite pas la ligne d'entetes
			}
			if(compteur == 1) { // Le premier parcour on ne met rien
				compteur++;
			} else { // Les autres ont met une vigule
				chaineInterventants += ",";
			}
			chaineInterventants += "{\"prenom\": \"" + tab[1] + "\",\n";
			chaineInterventants += "\"nom\": \"" + tab[2] + "\"}\n";
		}
		chaineInterventants += "]\n";
		
		compteur = 0; // réinitialisation du compteur
		for(String[] tab : etudiants) { // parcours des étudiants
			if(compteur == 0) {
				compteur++;
				continue; // On ne traite pas la ligne d'entetes
			}
			chaineEtudiants += "{\"group\": \"" + tab[0] + "\",\n";
			chaineEtudiants += "\"id\": \"" + tab[1] + "\",\n";
			chaineEtudiants += "\"nom\": \"" + tab[2] + "\",\n";
			chaineEtudiants += "\"prenom\": \"" + tab[3] + "\"}";
			if(compteur != tailleEtu-1) {// à toutes les lignes on met une vifgule
				chaineEtudiants += ",\n";
				compteur++;
			} else { // Sauf a la derniere
				chaineEtudiants += "\n";
			}
		}
		chaineEtudiants += "]\n";
		
		
		// On ecrit dans les deux fichiers
		try{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(chemin + "intervenants2014_2015.json"),"UTF-8"));
		     out.write(chaineInterventants,0,chaineInterventants.length());
		}catch(IOException ex){
		    ex.printStackTrace();
		}finally{
		  if(out != null){
		     try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		}
		try{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(chemin + "etudiants2014_2015.json"),"UTF-8"));
		     out.write(chaineEtudiants,0,chaineEtudiants.length());
		}catch(IOException ex){
		    ex.printStackTrace();
		}finally{
		  if(out != null){
		     try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		}
		
		
	}

}
