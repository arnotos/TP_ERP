/**
 * 
 */
package ihm;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dao.Personne;
import dao.Projet;
import services.JSonManager;
import services.PersonneManager;
import services.PersonneManager.Poste;
import services.ProjetManager;
import services.ServicesManager;


public class EntryPoint {
	
	public static Calendar dateDebutSimulation;
	public static Calendar dateFinDev;
	public static Calendar dateFinGestionProjet;
	
	//var globale d'efficience globale à la simulation (1 = 100%)
	public static double efficienceGlobale = 1;
	public static SimpleDateFormat formatDateDisplay = new SimpleDateFormat("dd MMMM yyyy");
	
	/* METHODES INIT utilisée dans le main juste en dessous */
	public static void initDate(Calendar c)
	{
		//Imposible de faire dateDebut = c.
		//On doit utiliser .clone() pour c/c des dates.
		dateDebutSimulation= (Calendar) c.clone();
		dateFinDev= (Calendar) c.clone();
		dateFinGestionProjet= (Calendar) c.clone();
	}
	
	/**
	 * 
	 * MAIN DU PROJET : interface console
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Listes des projets et des personnes de l'équipe
		ArrayList<Projet> lesProjets = new ArrayList<Projet>();
		ArrayList<Personne> lesPersonnes = new ArrayList<Personne>();
		
		//init des managers
		ProjetManager projetManager = new ProjetManager();
		PersonneManager personneManager = new PersonneManager();
		ServicesManager servicesManager = new ServicesManager();
		JSonManager jsonManager = new JSonManager();
		
		Map<String, Object> jsonInput = new HashMap<String, Object>();
		
		System.out.println("Veuillez choisir le fichier à charger : ");
		System.out.println("	1 - La situation numéro 1");
		System.out.println("	2 - La situation numéro 2");
		System.out.println("	3 - La situation numéro 3");
		System.out.println("	4 - La situation libre\n\r");
		
		Scanner reader = new Scanner(System.in);
		System.out.print("Numéro : ");
		int choice = reader.nextInt();
		reader.close();
		
		if(choice < 1 || choice > 4)
		{
			System.out.println("Mauvais numéro. Fin de la simulation.");
			System.exit(0);
		}
		
		try {
			jsonInput = jsonManager.jsonReader(choice);
			
	        for(String key: jsonInput.keySet())
	        {
	        	if(key == "Projects")
	        		lesProjets = (ArrayList<Projet>) jsonInput.get(key);
	        	else if(key == "Personnes")
	        		lesPersonnes = (ArrayList<Personne>) jsonInput.get(key);
	        	else if(key == "Efficience")
	        		efficienceGlobale = (double) jsonInput.get(key);
	        	else if(key == "DateStartSimulation")
	        		initDate((Calendar) jsonInput.get(key));
	        	
	        }			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		projetManager.afficherDatesProjets(lesProjets);
		
		//Affichage titre
		servicesManager.afficherTitre();
		//Présentation de l'équipe
		System.out.println("Présentation de l'équipe : ___________________________________________________");
		personneManager.afficherEquipe(lesPersonnes);
		
		//Etats de projets avant
		System.out.println("Liste des projets : __________________________________________________________");
		projetManager.afficherProjets(lesProjets);
		
		//trie des projets par date de livrable au plus tot
		projetManager.triProjetAuPlusTot(lesProjets);
		lesProjets = projetManager.sortProjectWithSameEndDate(lesProjets);
		System.out.println("Liste des projets triés : ____________________________________________________");
		projetManager.afficherProjets(lesProjets);
		
		for(Projet projet : lesProjets)
		{
			int jourDevRestant = projet.getDureeDev(efficienceGlobale);
			int jourGestionRestant = projet.getDureeGestionProjet(efficienceGlobale);
			Calendar dateTmp = (Calendar) dateFinDev.clone();
			
			while(jourDevRestant > 0)
			{					
				if(dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				{
					//Rien on travail pas.
				}
				else
				{
					jourDevRestant -= personneManager.getForceDeTravail(lesPersonnes, dateTmp, Poste.DEVELOPPER);
					//System.out.println(jourDevRestant);
				}
				//Vérifier si ok
				dateTmp.add(Calendar.DATE, 1);
			}
			
			dateFinDev = (Calendar) dateTmp.clone();
			dateTmp = (Calendar) dateFinGestionProjet.clone();
			
			while(jourGestionRestant > 0)
			{					
				if(dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				{
					//Rien on travail pas.
				}
				else
				{
					jourGestionRestant -= personneManager.getForceDeTravail(lesPersonnes, dateTmp, Poste.CHEF_DE_PROJET);
				}
				//Vérifier si ok
				dateTmp.add(Calendar.DATE, 1);
			}
			
			dateFinGestionProjet = (Calendar) dateTmp.clone();
			
			System.out.println("Projet : " + projet.getNom());
			System.out.println("Fin DEV : " + formatDateDisplay.format(dateFinDev.getTime()));
			System.out.println("Fin GESTION : " + formatDateDisplay.format(dateFinGestionProjet.getTime()));
			long diff = servicesManager.differenceBetweenToCalendar(projet.getDateFinAttendu(), dateFinGestionProjet);
			System.out.println("Avance/Retard : " + diff + " jours");
			System.out.println();
			
		}
		
		System.out.println(dateDebutSimulation.getTime());
		System.out.println(dateDebutSimulation.get(Calendar.YEAR));
	}
}
