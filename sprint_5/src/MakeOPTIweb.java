import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe MakeOPTIweb permettant de créer l'application web.
 * 
 * @author groupe2A1
 * @version 1.0
 */
public class MakeOPTIweb {

	public static ArrayList<String[]> intervenants = LibFichierCSV.lireFichier(LibFichierCSV.ficInter());
	public static ArrayList<String[]> projets = LibFichierCSV.lireFichier(LibFichierCSV.ficProj());
	public static ArrayList<String[]> listEtu = LibFichierCSV.lireFichier(LibFichierCSV.ficEtu());
	public static ArrayList<String[]> listesujet = LibFichierCSV.lireFichier(LibFichierCSV.ficSuj());
	
	public static void main(String[] args) throws IOException {
		sauverFichier(genererPage("0.1"));
	}

	/**
	 * Sauvegarde dans un fichier
	 * 
	 * @param site
	 *            site à sauvegarder
	 * @throws IOException lève une exception lorsqu'une erreur d'E/S se produit.
	 */
	public static void sauverFichier(String site) throws IOException {
		Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./OPTIweb/tests/OptiWeb.html"), "UTF-8"));
		w.write(site);
		w.close();
	}

	/**
	 * Permet de générer toutes les pages de l'application web
	 * 
	 * @param version
	 *            version du site
	 * @return site le site complet
	 */
	public static String genererPage(String version) {

		String site = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
				+ "<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />\n" + "<title>" + version + " - V" + version
				+ "</title>\n"
				+ "<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />\n"
				+ "<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />\n"
				+ "<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\n"
				+ "<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>\n"
				+ "<style type='text/css'>  \n"
				+ "@media all and (orientation:portrait) { .landscape {display: none;} }\n"
				+ "@media all and (orientation:landscape) { .landscape {display: inline;} }\n" + "</style>\n"
				+ "</head><body>\n\n";

		site = site + pageAccueil(version);

		site = site + pageCredits(version);

		site = site + genProjet(version,projets, intervenants, listesujet, listEtu);

		site = site + genererSujet(version,listesujet,projets);

		site = site + MakeEtu(version,listEtu);

		site += genIntervenants(version,intervenants,projets);

		site = site + "<script>\n" + "// li click handler which fills the projects search bar \n"
				+ "// with the value of the current data-find attribute\n"
				+ "$( 'li[data-find]' ).on( 'click',function(event){\n"
				+ "$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');\n" + "});\n"
				+ "</script>\n" + "</body>\n" + "</html>\n\n";
		MakeJSON.makeJSON("./OPTIweb/tests/");///
		return site;
	}

	/**
	 * Créé la page Accueil.
	 * 
	 * @param version
	 *            version du site
	 * @return pageAcc partie accueil
	 */
	public static String pageAccueil(String version) {
		String pageAcc = "<!-- DEBUT page accueil -->\n"
				+ "<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V" + version + "\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>\n"
				+ "<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>\n" + "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">\n"
				+ " <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>\n"
				+ "<li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>\n"
				+ "<li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>\n"
				+ "<li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>\n" + "</ul>\n"
				+ "</div>\n" + "<div data-role=\"footer\"> \n"
				+ "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>\n"
				+ "</div>\n" + "</div>\n" + "<!-- FIN page accueil -->\n\n";

		return pageAcc;
	}

	/**
	 * Créé la page Crédit.
	 * 
	 * @param version
	 *            version du site
	 * 
	 * @return pageCredit partie crédits
	 */
	public static String pageCredits(String version) {
		String pageCredit = "<!-- DEBUT page credits -->\n"
				+ "<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V" + version + " - Crédits\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n" + "<h1>Crédits</h1>\n" + "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "<p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p>\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">\n"
				+ "<li data-role=\"list-divider\">Product Owner</li>\n" + "<li>André PÉNINOU</li>\n"
				+ "<li>Université Toulouse 2 - IUT de Blagnac\n" + "<br/>Département INFORMATIQUE</li>\n" + "</ul>\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">\n"
				+ "<li data-role=\"list-divider\">Membres de l'équipe enseignante</li>\n"
				+ "<li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>André PÉNINOU</li><li>Olivier ROQUES</li>\n"
				+ "</ul>\n"
				+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\">\n"
				+ "<li data-role=\"list-divider\">Propulsé par</li>\n"
				+ "<li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>\n"
				+ "<li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>\n"
				+ "</ul>\n" + "</div>\n" + "<div data-role=\"footer\">\n"
				+ "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>\n"
				+ "</div>\n" + "</div>\n" + "<!-- FIN page credits -->\n";

		return pageCredit;
	}

	/**
	 * Créé la page Intervenant.
	 * 
	 * @param version
	 *            version du site
	 * @param intervenants
	 *            tableau de string contenant tout les intervenants
	 * @param projets
	 *             tableau de string contenant tout les projets
	 * @return pageInt html de la partie Intervenants de l'application
	 */
	public static String genIntervenants(String version,ArrayList<String[]> intervenants,ArrayList<String[]> projets) {
		ArrayList<String[]> pintervenants = intervenants;
		int nbCli;
		int nbSup;
		String pageInt = "<!-- DEBUT page intervenants -->\n<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V"
				+ version
				+ "\">\n<div data-role=\"header\" data-add-back-btn=\"true\">\n<h1>Intervenants 2014-2015</h1>\n\n</div>\n<div data-role=\"content\">\n\n";
		pageInt += "<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\">\n</form>\n\n";
		pageInt += "<ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\">\n<li data-role=\"list-divider\">\nIntervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span>\n</li>\n\n";

		for (String[] intervenant : pintervenants) {
			if (!intervenant[0].equals("id")) {
				nbCli = 0;
				nbSup = 0;
				for (String[] projet : projets) {
					if (projet[3].equals(intervenant[0]))
						nbCli++;
					if (projet[4].equals(intervenant[0]))
						nbSup++;
				}
				pageInt += "<li data-find=\"" + intervenant[2] + " " + intervenant[1] + "\">\n<a href=\"#projets\">"
						+ intervenant[2] + " " + intervenant[1]
						+ "<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">";
				pageInt += nbCli;
				pageInt += "</span><span class=\"ui-li-count\" title=\"Superviseur\">";
				pageInt += nbSup;
				pageInt += "</span></a>\n</li>\n";
			}
		}
		pageInt += "\n";
		pageInt += "</ul>\n</div>\n<div data-role=\"footer\">\n<h4>OPTIweb V<span class=\"landscape\">ersion </span>"
				+ version
				+ " <i class=\"fa fa-group fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page intervenants -->\n\n";
		return pageInt;
	}

	/**
	 * Créé la page étudiant.
	 * 
	 * @param version
	 *            La version de l'application
	 * @param listEtu
	 *            tableau de string contenant tout les étudiants
	 * @return pageEtu La chaîne contenant la page Étudiant
	 */
	public static String MakeEtu(String version,ArrayList<String[]> listEtu) {
		ArrayList<String[]> pliste = listEtu;
		// Déclaration des variables
		String pageEtu;

		// Initialisation des variables
		pageEtu = "";

		// Traitements
		pliste.remove(0); // Suppression de la ligne d'entêtes

		// Tri de la liste par Nom
		Collections.sort(pliste, new Comparator<String[]>() {
			@Override
			public int compare(String[] etu1, String[] etu2) {
				return etu1[2].compareTo(etu2[2]);
			}
		});

		pageEtu += "<!-- DEBUT page etudiants -->\n<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V";
		pageEtu += version;
		pageEtu += "\">\n<div data-role=\"header\" data-add-back-btn=\"true\">\n<h1>Etudiants 2014-2015</h1>\n\n</div>\n<div data-role=\"content\">\n\n<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\">\n</form>\n<ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\">\n<li data-role=\"list-divider\">\nEtudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>\n</li>\n";

		for (String[] s : pliste) { // Pour chaque étudiant du fichier
			pageEtu += "<li data-find=\"";
			pageEtu += s[3] + " " + s[2]; // Prénom Nom
			pageEtu += "\">\n";
			pageEtu += "<a href=\"#projets\">";
			pageEtu += s[2] + " " + s[3]; // Nom Prénom
			pageEtu += "<span class=\"ui-li-count\" title=\"Groupe\">Groupe ";
			pageEtu += s[0]; // Groupe
			pageEtu += "</span>\n";
			pageEtu += "</a>\n";
			pageEtu += "</li>\n";
		}

		pageEtu += "</ol>\n</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>";
		pageEtu += version;
		pageEtu += " <i class=\"fa fa-group fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page etudiants -->\n";
		return pageEtu;
	}

	/**
	 * Créé la page Projet.
	 * 
	 * @param version
	 *            La version de l'application
	 *
	 * @param projets
	 *            tableau de string contenant tout les projets
	 *
	 * @param intervenants
	 *            tableau de string contenant tout les intervenants
	 *
	 * @param listesujet
	 *            tableau de string contenant tout les sujets
	 * @param listEtu 
	 *            tableau de string contenant tout les étudiants
	 * @return pageProj La chaîne contenant la page Projet
	 */
	public static String genProjet(String version,ArrayList<String[]> projets,ArrayList<String[]> intervenants,ArrayList<String[]> listesujet,ArrayList<String[]> listEtu) {

		String pageProj;

		pageProj = "<!-- DEBUT page projets -->\n" + "<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V"
				+ version + "\">" + "\n";
		pageProj += "<div data-role=\"header\" data-add-back-btn=\"true\">" + "\n";
		pageProj += "<h1>Projets 2014-2015</h1>" + "\n";
		pageProj += "</div>" + "\n";

		pageProj += "<div data-role=\"content\">" + "\n";
		pageProj += "<form class=\"ui-filterable\">" + "\n";
		pageProj += "<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\" >"
				+ "\n";
		pageProj += "</form>" + "\n";
		pageProj += "<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">"
				+ "\n";

		for (String[] pro : projets) {
			if (!pro[0].equals("id")) {
				pageProj += "<li>\n<p>\n";
				for (String[] suj : listesujet) {
					if (pro[2].equals(suj[0])) {
						pageProj += "<b>[" + suj[1] + "]</b>" + " " + suj[2];
					}
				}
				pageProj += "</p>\n<p>\n";
				for (String[] in : intervenants) {
					if (pro[3].equals(in[0])) {
						pageProj += "<b>Client :</b> ";
						pageProj += in[2] + " " + in[1] + "\n";
					}
				}

				pageProj += "</p>\n<p>\n";
				for (String[] in : intervenants) {
					if (pro[4].equals(in[0])) {
						pageProj += "<b>Superviseur :</b> ";
						pageProj += in[2] + " " + in[1] + "\n";
					}
				}
				pageProj += "</p>\n<p>\n";
				pageProj += "<b>Groupe " + pro[1] + " :</b> ";
				for (String[] etu : listEtu) {
					if (pro[1].equals(etu[0])) {
						pageProj += etu[3] + " " + etu[2] + " - ";
					}
				}
				pageProj += "\n</p>\n</li>\n";
			}
		}

		pageProj += "</ol>" + "\n";
		pageProj += "</div>" + "\n";

		pageProj += "<div data-role=\"footer\">" + "\n";
		pageProj += "<h4>OPTIweb V<span class=\"landscape\">ersion </span>" + version
				+ " <i class=\"fa fa-tasks fa-2x\"></i></h4>" + "\n";
		pageProj += "</div>" + "\n";
		pageProj += "</div> \n<!-- FIN page projets -->\n";

		return pageProj;

	}

	/**
	 * Créé la page Sujet.
	 * 
	 * @param version
	 *            La version de l'application
	 * @param listeSujet
	 *            tableau de string contenant tout les sujets
	 * @param projets
	 *            tableau de string contenant tout les projets
	 *
	 * @return pageSujet La chaîne contenant la page Étudiant
	 */
	public static String genererSujet(String version,ArrayList<String[]> listeSujet, ArrayList<String[]> projets) {

		String pageSujet;

		// Debut intacte

		pageSujet = "<!-- DEBUT page sujets -->" + "\n";
		pageSujet += "<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V" + version + "\">" + "\n";
		pageSujet += "<div data-role=\"header\" data-add-back-btn=\"true\">" + "\n";

		pageSujet = pageSujet
				+ "<h1>Sujets 2014-2015</h1>\n\n</div>\n<div data-role=\"content\">\n\n<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\">"
				+ "\n";
		pageSujet = pageSujet
				+ "</form>\n<ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\">\n<li data-role=\"list-divider\">    "
				+ "\n";
		// Partie séparé (modifié)
		pageSujet = pageSujet
				+ "Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li>"
				+ "\n";
		for (int ligne = 1; ligne < listeSujet.size(); ligne++) {
			pageSujet = pageSujet + "<li data-find=\"[" + listeSujet.get(ligne)[1] + "]\">" + "\n";
			pageSujet += "<a href=\"#projets\">[" + listeSujet.get(ligne)[1] + "] <br/>" + "\n";
			pageSujet += "<div style=\"white-space:normal;\">" + "\n";
			pageSujet += "<span><b>" + listeSujet.get(ligne)[2] + "</b>" + "\n";
			pageSujet += "</span><span class=\"ui-li-count\">" + getGroupe(ligne, projets) + "</span>"
					+ "\n";
			pageSujet += "</div>\n</a>\n</li>\n";
		}
		// Fin intacte
		pageSujet = pageSujet + "</ol>" + "\n"
				+ "</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>" + version
				+ " <i class=\"fa fa-copy fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page sujets -->\n";

		return pageSujet;
	}

	/**
	 * Retourne le groupe d'un projet.
	 * 
	 * @param pLigne
	 *            numéro du sujet
	 * @param pProjet
	 *            fichier contenant la liste des projets       
	 * @return groupe Le groupe appartenant à un sujet 
	 */	
	private static String getGroupe(int pLigne, ArrayList<String[]> pProjet) {
		String groupe = "";
		for (int i = 1; i < pProjet.size(); i++) {
			if (Integer.parseInt(pProjet.get(i)[2]) == pLigne) {
				if (!groupe.equals("")) {
					groupe += " ";
				}
				groupe += pProjet.get(i)[1];
			}
		}

		return groupe;

	}

}
