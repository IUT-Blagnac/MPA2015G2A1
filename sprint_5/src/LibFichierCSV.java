import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe LibFichierCSV permettant de modifier des fichiers CSV.
 * 
 * @author groupe2A1
 * @version 1.0
 */
public class LibFichierCSV {
	public static String ficVoeux = "./data/voeux2014_2015";
	
	/**
	 * Permet de récupérer le fichier sujets dans le répertoire sujet.
	 * 
	 * @return le nom du fichier Sujet.
	 **/
	public static String ficSuj() {
		String path = new File("./data/sujet").listFiles()[0].getPath();
		path = path.replace("\\", "/");
		return path.substring(0, path.length()-4);
	}
	
	/**
	 * Permet de récupérer le fichier etudiant dans le répertoire Etudiant.
	 * 
	 * @return le nom du fichier Etudiant.
	 **/
	public static String ficEtu() {
		String path = new File("./data/etudiant").listFiles()[0].getPath();
		path = path.replace("\\", "/");
		return path.substring(0, path.length()-4);
	}
	
	/**
	 * Permet de récupérer le fichier intervenant dans le répertoire Intervenant.
	 * 
	 * @return le nom du fichier Intervenant.
	 **/
	public static String ficInter() {
		String path = new File("./data/intervenant").listFiles()[0].getPath();
		path = path.replace("\\", "/");
		return path.substring(0, path.length()-4);
	}
	
	/**
	 * Permet de récupérer le fichier projets dans le répertoire Projet
	 * 
	 * @return le nom du fichier Projet.
	 **/
	public static String ficProj() {
		String path = new File("./data/projet").listFiles()[0].getPath();
		path = path.replace("\\", "/");
		return path.substring(0, path.length()-4);
	}

	/**
	 * Permet de lire un fichier et retourner les lignes d'un fichier.
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 * @return Un tableau contenant toutes les lignes du fichier.
	 **/
	public static ArrayList<String[]> lireFichier(String fichier) {
		/* Déclaration des variables */
		ArrayList<String[]> tab = new ArrayList<String[]>();
		Scanner scanner = null;
		String[] parts = null;
		String[] partsVide = null;
		String line;
		File file = new File(fichier + ".csv");

		/* Traitements */
		try {
			scanner = new Scanner(file, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// récupère la première ligne et l'ajoute
		if (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.contains(";")) {
				parts = line.split(";");
			}
			tab.add(parts);
		}

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			parts = line.split(";");
			if (line.endsWith(";")) {
				partsVide = new String[parts.length + 1];
				for (int i = 0; i < parts.length; i++) {
					partsVide[i] = parts[i];
				}
				partsVide[parts.length] = "";
			} else {
				partsVide = parts;
			}
			tab.add(partsVide);
		}

		scanner.close();
		return tab;
	}

	/**
	 * Permet d'écire dans un fichier, le crée s'il n'existe pas déjà.
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 * @param tab
	 *            Contient ce qui doit etre ecrit dans le fichier.
	 **/
	public static void ajouterLignes(String fichier, ArrayList<String[]> tab) {
		/* Déclaration des variables */
		FileWriter writer = null;

		/* Traitements */
		try {
			writer = new FileWriter(fichier + ".csv", true);
			for (int i = 0; i < tab.size(); i++) {
				for (int j = 0; j < tab.get(i).length; j++) {
					writer.write(tab.get(i)[j], 0, tab.get(i)[j].length());
					if (j < tab.get(i).length - 1) {
						writer.write(";", 0, 1);
					} else if (j == tab.get(i).length - 1) {
						writer.write("\n", 0, 1);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Ajoute une ligne dans un fichier (le crée s'il n'existe pas déjà).
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 * @param tab
	 *            Ligne à ajouter.
	 **/
	public static void ajouterLigne(String fichier, String[] tab) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fichier + ".csv", true);
			for (int j = 0; j < tab.length; j++) {
				writer.write(tab[j], 0, tab[j].length());
				if (j < tab.length - 1) {
					writer.write(";", 0, 1);
				} else if (j == tab.length - 1) {
					writer.write("\n", 0, 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Modifie une ligne dans un fichier.
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 * @param tab
	 *            Ligne à ajouter à la place de l'ancienne.
	 **/
	public static void modifierLigneByID(String fichier, String[] tab) {
		/* Déclaration des variables */
		ArrayList<String[]> ALfichier = lireFichier(fichier);
		int posID = -1;

		/* Traitements */
		for (int i = 0; i < ALfichier.get(0).length; i++) {
			if (ALfichier.get(0)[i].equals("id")) {
				posID = i;
				i = ALfichier.get(0).length;
			}
		}

		for (int j = 0; j < ALfichier.size(); j++) {
			if (ALfichier.get(j)[posID].equals(String.valueOf(tab[0]))) {
				ALfichier.get(j)[1] = tab[1];
				ALfichier.get(j)[2] = tab[2];
				j = ALfichier.size();
			}
		}

		effacerFichier(fichier);
		ajouterLignes(fichier, ALfichier);
	}

	/**
	 * Efface le contenu d'un fichier.
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 **/
	public static void effacerFichier(String fichier) {
		try {
			new FileWriter(new File(fichier + ".csv")).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime une ligne dans un fichier.
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 * @param idLigne
	 *            Ligne à supprimer.
	 **/
	public static void supprimerLigneByID(String fichier, int idLigne) {
		/* Déclaration des variables */
		ArrayList<String[]> ALfichier = lireFichier(fichier);
		int posID = -1;

		/* Traitements */
		for (int i = 0; i < ALfichier.get(0).length; i++) {
			if (ALfichier.get(0)[i].equals("id")) {
				posID = i;
				i = ALfichier.get(0).length;
			}
		}

		for (int j = 0; j < ALfichier.size(); j++) {
			if (ALfichier.get(j)[posID].equals(String.valueOf(idLigne))) {
				ALfichier.remove(j);
				j = ALfichier.size();
			}
		}

		effacerFichier(fichier);
		ajouterLignes(fichier, ALfichier);
	}

	/**
	 * Récupère une ligne avec son identifiant.
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 * @param idLigne
	 *            Ligne à retourner.
	 * @return Une ligne du fichier.
	 **/
	public static String[] getLigneByID(String fichier, int idLigne) {
		/* Déclaration des variables */
		ArrayList<String[]> ALfichier = lireFichier(fichier);
		int posID = -1;

		/* Traitements */
		for (int i = 0; i < ALfichier.get(0).length; i++) {
			if (ALfichier.get(0)[i].equals("id")) {
				posID = i;
				i = ALfichier.get(0).length;
			}
		}

		for (int j = 0; j < ALfichier.size(); j++) {
			if (ALfichier.get(j)[posID].equals(String.valueOf(idLigne))) {
				return ALfichier.get(j);
			}
		}
		return null;
	}

	/**
	 * Retourne le plus grand identifiant utilisé dans le fichier.
	 * 
	 * @param fichier
	 *            Contient le chemin et le nom du fichier sans extention. Ex : "F:/home/monFichier".
	 * @return Dernier identifiant utilisé dans le fichier.
	 **/
	public static int lastID(String fichier) {
		ArrayList<String[]> ALfichier = lireFichier(fichier);
		int higherID = -1;
		int posID = -1;
		for (int i = 0; i < ALfichier.get(0).length; i++) {
			if (ALfichier.get(0)[i].equals("id")) {
				posID = i;
				i = ALfichier.get(0).length;
			}
		}
		if (ALfichier.get(ALfichier.size() - 1)[posID].equals("id")) {
			return 0;
		} else {
			for (int i = 1; i < ALfichier.size(); i++) {
				if (higherID < Integer.valueOf(ALfichier.get(i)[posID]))
					higherID = Integer.valueOf(ALfichier.get(i)[posID]);
			}
			return higherID;
		}
	}

	/**
	 * Permet de modifier le fichier contenant la liste des voeux par groupe.
	 * 
	 * @param id
	 *            Le numéro du groupe.
	 * @param voeux
	 *            La liste des voeux.
	 **/
	public static void modifVoeux(String id, String voeux) {
		ArrayList<String[]> ALfichier = lireFichier(ficVoeux);
		for (int i = 1; i < ALfichier.size(); i++) {
			if (ALfichier.get(i)[0].equals(id)) {
				ALfichier.get(i)[1] = voeux;
				i = ALfichier.size();
			}
		}
		effacerFichier(ficVoeux);
		ajouterLignes(ficVoeux, ALfichier);
	}

	/**
	 * Permet de récuperer la liste des voeux pour un groupe donné.
	 * 
	 * @param id
	 *            Le numéro du groupe.
	 * @return La liste des voeux.
	 **/
	public static String getVoeux(String id) {
		ArrayList<String[]> ALfichier = lireFichier(ficVoeux);
		for (int i = 1; i < ALfichier.size(); i++) {
			if (ALfichier.get(i)[0].equals(id)) {
				return ALfichier.get(i)[1];
			}
		}
		return null;
	}

	public static String getVoeuxRang(String id, int rang) {
		ArrayList<String[]> ALfichier = lireFichier(ficVoeux);
		String[] Voeux;
		for (int i = 1; i < ALfichier.size(); i++) {
			if (ALfichier.get(i)[0].equals(id)) {
				Voeux = ALfichier.get(i)[1].split("-");
				if (Voeux.length >= rang) {
					return Voeux[rang - 1];
				}
			}
		}
		return null;
	}
}