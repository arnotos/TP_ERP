/**
 * 
 */
package ihm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dao.ChefDeProjet;
import dao.Developper;
import dao.Personne;
import dao.Projet;
import services.PersonneManager;
import services.ProjetManager;
import services.ServicesManager;
import services.PersonneManager.Poste;

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
	
	public static void initDate()
	{
		Calendar c = GregorianCalendar.getInstance();
		c.set(2018,  Calendar.JUNE,  1);
		dateDebutSimulation = c;
		dateFinDev = c;
		dateFinGestionProjet = c;
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
		Developper dev1 = new Developper("William M."); //dev
		Developper dev2 = new Developper("Antoine H."); //dev
		Developper dev3 = new Developper("Arnaud L. "); //responsable technique
		
		//init les chef de projet 
		ChefDeProjet chefProjet1 = new ChefDeProjet("Victor L. ");
		
		//ajout des employés à la liste d'employés
		equipe.add(dev1);
		equipe.add(dev2);
		equipe.add(dev3);
		equipe.add(chefProjet1);
	}
	public static void afficherProjets(ArrayList<Projet> projets)
	{
		for(Projet p : projets)
			System.out.println(p.getNom() + " -> JoursDev: " + p.getDureeDev() + " JoursGest: "+ p.getDureeGestionProjet() + " DateFin: " + p.getDateFinAttendu().getTime());
		System.out.println("\n");
	}
	public static void afficherEquipe(ArrayList<Personne> equipe)
	{
		for(Personne p : equipe)
			System.out.println(p.getNom() + " : " + p.getClass().toString().replaceAll("class dao.", ""));
		System.out.println("\n");
	}
	public static void afficherTitre()
	{
		System.out.println("_________________________________            _________________________________");
		System.out.println("_________________________________ PROJET ERP _________________________________");
		System.out.println("\n");
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
		
		//Affichage titre
		afficherTitre();
		//Présentation de l'équipe
		System.out.println("Présentation de l'équipe : ___________________________________________________");
		afficherEquipe(lesPersonnes);
		
		//Etats de projets avant
		System.out.println("Liste des projets : __________________________________________________________");
		afficherProjets(lesProjets);
		
		//trie des projets par date de livrable au plus tot
		//si 2 livrables pour la meme dates ( on test tt les combinaisons différentes)
		//Après résultat
		projetManager.triProjetAuPlusTot(lesProjets);
		System.out.println("Liste des projets triés : ____________________________________________________");
		afficherProjets(lesProjets);
		
		//TODO lister toutes les combinaisons
		lesCombinaisonsProjets.add(lesProjets);
		
		for (ArrayList<Projet> combinaisons : lesCombinaisonsProjets)
		{
			//Si plusieurs combinaison initialise
			for(Projet projet : combinaisons)
			{
				int jourDevRestant = projet.getDureeDev();
				int jourGestionRestant = projet.getDureeGestionProjet();
				Calendar dateTmp = dateFinDev;
				
				while(jourDevRestant > 0)
				{					
					if(dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || dateTmp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
					{
						//Rien on travail pas.
					}
					else
					{
						jourDevRestant -= personneManager.getForceDeTravail(lesPersonnes, dateTmp, Poste.DEVELOPPER) * efficienceGlobale;
					}
					//Vérifier si ok
					dateTmp.add(Calendar.DATE, 1);
				}
				
				dateFinDev = dateTmp;
				dateTmp = dateFinGestionProjet;
				
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
				
				dateFinGestionProjet = dateTmp;
				
				System.out.println("Fin DEV : " + dateFinDev.getTime());
				System.out.println("Fin GESTION : " + dateFinGestionProjet.getTime());
			}
		}
		
		System.out.println("slt");
		System.out.println(dateDebutSimulation.getTime());
	}
	

}
