/**
 * 
 */
package ihm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import dao.ChefDeProjet;
import dao.Developper;
import dao.Personne;
import dao.Projet;
import services.PersonneManager;
import services.PersonneManager.Poste;
import services.ProjetManager;
import services.ServicesManager;

/**
 * @author Arnaud
 *
 */
public class EntryPoint {
	
	public static Calendar dateDebutSimulation;
	public static Calendar dateFinDev;
	public static Calendar dateFinGestionProjet;
	
	//var globale d'efficience globale à la simulation (1 = 100%)
	public static double efficienceGlobale = 1;
	
	
	/* METHODES INIT utilisée dans le main juste en dessous */
	//TODO a terme déplacer les inits dans le ServiceManager
	public static void initDate()
	{
		Calendar c = new GregorianCalendar(2018, Calendar.JUNE, 1);
		//Imposible de faire dateDebut = c.
		//On doit utiliser .clone() pour c/c des dates.
		dateDebutSimulation= (Calendar) c.clone();
		dateFinDev= (Calendar) c.clone();
		dateFinGestionProjet= (Calendar) c.clone();
	}
	
	public static void initProjets(ArrayList<Projet> projets)
	{ //(String nomP, int dureeDevP, int dureeGestionProjetP, Date dateFin)
		Calendar c = GregorianCalendar.getInstance();
		//Projet 1
		c.set(2018,  Calendar.DECEMBER,  1);
		Projet pro1 = new Projet("AIRBUS   ", 150, 40, c.getTime());
		projets.add(pro1);
		//Projet 2
		c.clear();
		c.set(2018,  Calendar.SEPTEMBER,  1);
		Projet pro2 = new Projet("NINETENDO", 75, 25, c.getTime());
		projets.add(pro2);
		//Projet 3
		c.clear();
		c.set(2019,  Calendar.JANUARY,  1);
		Projet pro3 = new Projet("HTC VR   ", 150, 45, c.getTime());
		projets.add(pro3);
	}
	
	public static void initEquipe(ArrayList<Personne> equipe)
	{
		Developper dev1 = new Developper("William M.", dateDebutSimulation.getTime()); //dev
		Developper dev2 = new Developper("Antoine H.", dateDebutSimulation.getTime()); //dev
		Developper dev3 = new Developper("Arnaud L. ", dateDebutSimulation.getTime()); //responsable technique
		
		//init les chef de projet 
		ChefDeProjet chefProjet1 = new ChefDeProjet("Victor L. ", dateDebutSimulation.getTime());
		
		//ajout des employés à la liste d'employés
		equipe.add(dev1);
		equipe.add(dev2);
		equipe.add(dev3);
		equipe.add(chefProjet1);
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
		ArrayList<ArrayList<Projet>> lesCombinaisonsProjets = new ArrayList<>();
		
		//init des managers
		ProjetManager projetManager = new ProjetManager();
		PersonneManager personneManager = new PersonneManager();
		ServicesManager servicesManager = new ServicesManager();
		
		//init des dates
		initDate();
		//init de l'équipe 
		initEquipe(lesPersonnes);
		//init les projets
		initProjets(lesProjets);
		
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
		//si 2 livrables pour la meme dates ( on test tt les combinaisons différentes)
		//Après résultat
		projetManager.triProjetAuPlusTot(lesProjets);
		System.out.println("Liste des projets triés : ____________________________________________________");
		projetManager.afficherProjets(lesProjets);
		
		
		//TODO lister toutes les combinaisons
		lesCombinaisonsProjets.add(lesProjets);
		
		for (ArrayList<Projet> combinaisons : lesCombinaisonsProjets)
		{
			//Si plusieurs combinaison initialise
			for(Projet projet : combinaisons)
			{
				int jourDevRestant = projet.getDureeDev();
				int jourGestionRestant = projet.getDureeGestionProjet();
				Calendar dateTmp = (Calendar) dateFinDev.clone();
				
				while(jourDevRestant > 0)
				{					
					if(dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
					{
						//Rien on travail pas.
					}
					else
					{
						jourDevRestant -= personneManager.getForceDeTravail(lesPersonnes, dateTmp, Poste.DEVELOPPER) * efficienceGlobale;
						System.out.println(dateTmp.get(Calendar.DAY_OF_WEEK) + " : " + jourDevRestant);
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
						jourGestionRestant -= personneManager.getForceDeTravail(lesPersonnes, dateTmp, Poste.CHEF_DE_PROJET) * efficienceGlobale;
					}
					//Vérifier si ok
					dateTmp.add(Calendar.DATE, 1);
				}
				
				dateFinGestionProjet = (Calendar) dateTmp.clone();
				
				System.out.println("Projet : " + projet.getNom());
				System.out.println("Fin DEV : " + dateFinDev.getTime());
				System.out.println("Fin GESTION : " + dateFinGestionProjet.getTime() + "\n\r");
			}
		}
		
		System.out.println("slt");
		System.out.println(dateDebutSimulation.getTime());
		System.out.println(dateDebutSimulation.get(Calendar.YEAR));
	}
	

}
