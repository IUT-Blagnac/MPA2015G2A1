import java.io.*;
import java.util.ArrayList;
import junit.textui.TestRunner;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe MakeOPTIwebTest permettant de réaliser les tests de la classe MakeOPTIweb.
 * 
 * @author groupe2A1
 * @version 1.0
 */
public class MakeOPTIwebTest extends TestCase {

	static String programmeATester = "MakeOPTIweb.java";
	Process executionProgrammeATester;
	BufferedReader ecranProgrammeATester;
	BufferedWriter clavierProgrammeATester;

	String finDeLigne = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		if (args.length > 0) {
			programmeATester = args[0];
		}
		System.out.println("Tests du programme : " + programmeATester);
		TestRunner.run(new TestSuite(MakeOPTIwebTest.class));
	}

	protected void setUp() throws IOException {
		executionProgrammeATester = Runtime.getRuntime().exec("java -cp .;bin " + programmeATester);
		ecranProgrammeATester = new BufferedReader(new InputStreamReader(executionProgrammeATester.getInputStream()));
		clavierProgrammeATester = new BufferedWriter(new OutputStreamWriter(executionProgrammeATester.getOutputStream()));
	}
	
	
	/**
	 * Permet de vérifier le contenue de la page Etudiant.
	 **/
	public void test_page_etudiants() {
		String expected = "<!-- DEBUT page etudiants -->\n"
				+ "<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>Etudiants 2014-2015</h1>\n"
				+ "\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "\n"
				+ "<form class=\"ui-filterable\">\n"
				+ "<input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\">\n"
				+ "</form>\n"
				+ "<ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\">\n"
				+ "<li data-role=\"list-divider\">\n"
				+ "Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>\n"
				+ "</li>\n"
				+ "<li data-find=\"Arnauld ALEX\">\n"
				+ "<a href=\"#projets\">ALEX Arnauld<span class=\"ui-li-count\" title=\"Groupe\">Groupe F</span>\n"
				+ "</a>\n"
				+ "</li>\n"
				+ "<li data-find=\"Camille ALRAM\">\n"
				+ "<a href=\"#projets\">ALRAM Camille<span class=\"ui-li-count\" title=\"Groupe\">Groupe I</span>\n"
				+ "</a>\n"
				+ "</li>\n"
				+ "<li data-find=\"Jeremy ALVES NETO\">\n"
				+ "<a href=\"#projets\">ALVES NETO Jeremy<span class=\"ui-li-count\" title=\"Groupe\">Groupe P</span>\n"
				+ "</a>\n"
				+ "</li>\n"
				+ "</ol>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page etudiants -->\n";

		ArrayList<String[]> etudiants = LibFichierCSV.lireFichier("./data/fichierTest/etudiant/etudiants2014_2015");	
		String chaine = MakeOPTIweb.MakeEtu("0.1",etudiants);
		assertEquals("Affiche : ", expected, chaine);
	}

	/**
	 * Permet de vérifier le contenue de la page Intervenants.
	 **/
	public void test_page_intervenants() {
		String expected = "<!-- DEBUT page intervenants -->\n<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1"
				+ "\">\n<div data-role=\"header\" data-add-back-btn=\"true\">\n<h1>Intervenants 2014-2015</h1>\n\n</div>\n<div data-role=\"content\">\n\n"
				+ "<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\">\n</form>\n\n"
				+ "<ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\">\n<li data-role=\"list-divider\">\nIntervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span>\n</li>\n\n"
				+ "<li data-find=\"BOULLE Rémi\">\n"
				+ "<a href=\"#projets\">BOULLE Rémi<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">1</span><span class=\"ui-li-count\" title=\"Superviseur\">1</span></a>\n"
				+ "</li>\n"
				+ "<li data-find=\"BRUEL Jean-Michel\">\n"
				+ "<a href=\"#projets\">BRUEL Jean-Michel<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">1</span><span class=\"ui-li-count\" title=\"Superviseur\">1</span></a>\n"
				+ "</li>\n"
				+ "<li data-find=\"CANUT Marie-Françoise\">\n"
				+ "<a href=\"#projets\">CANUT Marie-Françoise<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">1</span><span class=\"ui-li-count\" title=\"Superviseur\">1</span></a>\n"
				+ "</li>\n\n"
				+ "</ul>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div>\n"
				+ "<!-- FIN page intervenants -->\n\n";
		
		ArrayList<String[]> intervenants = LibFichierCSV.lireFichier("./data/fichierTest/intervenant/intervenants2014_2015");
		ArrayList<String[]> projets = LibFichierCSV.lireFichier("./data/fichierTest/projet/projets2014_2015");
		String chaine = MakeOPTIweb.genIntervenants("0.1",intervenants,projets);
		assertEquals("Affiche : ", expected, chaine);
	}

	/**
	 * Permet de vérifier le contenue de la page Sujets.
	 **/
	public void test_page_sujets() {
		String expected;

		expected = "<!-- DEBUT page sujets -->"+"\n"
				+ "<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">"+"\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">" + "\n"

				+ "<h1>Sujets 2014-2015</h1>\n\n</div>\n<div data-role=\"content\">\n\n<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\">"+"\n"
				+ "</form>\n<ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\">\n<li data-role=\"list-divider\">    "+"\n"
				+ "Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li>"+"\n"
				
				+ "<li data-find=\"[ApexEComm]\">"+"\n"
				+ "<a href=\"#projets\">[ApexEComm] <br/>"+"\n"
				+ "<div style=\"white-space:normal;\">"+"\n"
				+ "<span><b>Application et tutoriel Oracle Apex pour un site d'e-commerce</b>"+"\n"
				+ "</span><span class=\"ui-li-count\">F</span>"+"\n"//???
				+ "</div>\n</a>\n</li>\n"
				
				+ "<li data-find=\"[Archeologie]\">"+"\n"
				+ "<a href=\"#projets\">[Archeologie] <br/>"+"\n"
				+ "<div style=\"white-space:normal;\">"+"\n"
				+ "<span><b>Groupe de recherche Chasséen Méridional</b>"+"\n"
				+ "</span><span class=\"ui-li-count\">P</span>"+"\n" //???
				+ "</div>\n</a>\n</li>\n"
				
				+ "<li data-find=\"[Architekt]\">"+"\n"
				+ "<a href=\"#projets\">[Architekt] <br/>"+"\n"
				+ "<div style=\"white-space:normal;\">"+"\n"
				+ "<span><b>Architekt</b>"+"\n"
				+ "</span><span class=\"ui-li-count\">I</span>"+"\n" //???
				+ "</div>\n</a>\n</li>\n"
				
				+"</ol>"+"\n"+"</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page sujets -->\n";
				

		ArrayList<String[]> sujets = LibFichierCSV.lireFichier("./data/fichierTest/sujet/sujets2014_2015");
		ArrayList<String[]> projets = LibFichierCSV.lireFichier("./data/fichierTest/projet/projets2014_2015");		
		String chaine = MakeOPTIweb.genererSujet("0.1",sujets,projets);
		assertEquals("Affichage : ", expected , chaine );
	}

	/**
	 * Permet de vérifier le contenue de la page Projets.
	 **/
	public void test_page_projets() {
		String expected = "<!-- DEBUT page projets -->\n"
				+ "<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">\n"
				+ "<div data-role=\"header\" data-add-back-btn=\"true\">\n"
				+ "<h1>Projets 2014-2015</h1>\n"
				+ "</div>\n"
				+ "<div data-role=\"content\">\n"
				+ "<form class=\"ui-filterable\">\n"
				+ "<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\" >\n"
				+ "</form>\n"
				+ "<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">\n"
				+ "<li>\n"
				+ "<p>\n"
				+ "<b>[ApexEComm]</b> Application et tutoriel Oracle Apex pour un site d'e-commerce</p>\n"
				+ "<p>\n"
				+ "<b>Client :</b> BRUEL Jean-Michel\n"
				+ "</p>\n"
				+ "<p>\n"
				+ "<b>Superviseur :</b> CANUT Marie-Françoise\n"
				+ "</p>\n"
				+ "<p>\n"
				+ "<b>Groupe F :</b> Arnauld ALEX - \n"
				+ "</p>\n"
				+ "</li>\n"
				+ "<li>\n"
				+ "<p>\n"
				+ "<b>[Architekt]</b> Architekt</p>\n"
				+ "<p>\n"
				+ "<b>Client :</b> BOULLE Rémi\n"
				+ "</p>\n"
				+ "<p>\n"
				+ "<b>Superviseur :</b> BRUEL Jean-Michel\n"
				+ "</p>\n"
				+ "<p>\n"
				+ "<b>Groupe I :</b> Camille ALRAM - \n"
				+ "</p>\n"
				+ "</li>\n"
				+ "<li>\n"
				+ "<p>\n"
				+ "<b>[Archeologie]</b> Groupe de recherche Chasséen Méridional</p>\n"
				+ "<p>\n"
				+ "<b>Client :</b> CANUT Marie-Françoise\n"
				+ "</p>\n"
				+ "<p>\n"
				+ "<b>Superviseur :</b> BOULLE Rémi\n"
				+ "</p>\n"
				+ "<p>\n"
				+ "<b>Groupe P :</b> Jeremy ALVES NETO - \n"
				+ "</p>\n"
				+ "</li>\n"
				+ "</ol>\n"
				+ "</div>\n"
				+ "<div data-role=\"footer\">\n"
				+ "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>\n"
				+ "</div>\n"
				+ "</div> \n"
				+ "<!-- FIN page projets -->\n";
		
		ArrayList<String[]> projets = LibFichierCSV.lireFichier("./data/fichierTest/projet/projets2014_2015");	
		ArrayList<String[]> intervenants = LibFichierCSV.lireFichier("./data/fichierTest/intervenant/intervenants2014_2015");		
		ArrayList<String[]> sujets = LibFichierCSV.lireFichier("./data/fichierTest/sujet/sujets2014_2015");		
		ArrayList<String[]> etudiants = LibFichierCSV.lireFichier("./data/fichierTest/etudiant/etudiants2014_2015");		
		String chaine = MakeOPTIweb.genProjet("0.1",projets,intervenants,sujets,etudiants);
		assertEquals("Affiche : ", expected, chaine);
	}
	


	public static String listerRepertoire(File repertoire) { // ajouter
		String[] listefichiers;
		listefichiers = repertoire.list();
		for (int i = 0; i < listefichiers.length; i++) {
			System.out.println(listefichiers[i]);
			listefichiers[i] = listefichiers[i].substring(0, listefichiers[i].length() - 4);
			System.out.println(listefichiers[i]);
		}
		return listefichiers[0];
	}
}
