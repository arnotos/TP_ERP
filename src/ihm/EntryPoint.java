/**
 * 
 */
package ihm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dao.ChefDeProjet;
import dao.Developper;
import dao.Personne;
import dao.Projet;
import services.ProjetManager;

/**
 * @author Arnaud
 *
 */
public class EntryPoint {
	
	public static Calendar dateDebutSimulation =null;
	public static Calendar dateFinDev =null;
	public static Calendar dateFinGestionProjet =null;
	
	//var globale d'efficience globale � la simulation (1 = 100%)
	public static double efficienceGlobale = 1;
	
	public static void initProjets(ArrayList<Projet> projets) { //(String nomP, int dureeDevP, int dureeGestionProjetP, Date dateFin)
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
		
		//ajout des employ�s � la liste d'employ�s
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
	 * @param args
	 */
	public static void main(String[] args) {
		//Listes des projets et des personnes de l'�quipe
		ArrayList<Projet> lesProjets = new ArrayList<Projet>();
		ArrayList<Personne> lesPersonnes = new ArrayList<Personne>();
		
		//init des managers
		ProjetManager projetManager = new ProjetManager();
		//init de l'�quipe 
		initEquipe(lesPersonnes);
		//init les projets
		initProjets(lesProjets);
		
		//Affichage titre
		afficherTitre();
		//Pr�sentation de l'�quipe
		System.out.println("Pr�sentation de l'�quipe : ___________________________________________________");
		afficherEquipe(lesPersonnes);
		
		//Etats de projets avant
		System.out.println("Liste des projets : __________________________________________________________");
		afficherProjets(lesProjets);
		
		//trie des projets par date de livrable au plus tot
		//si 2 livrables pour la meme dates ( on test tt les combinaisons diff�rentes)
		//Apr�s r�sultat
		projetManager.triProjetAuPlusTot(lesProjets);
		System.out.println("Liste des projets tri�s : ____________________________________________________");
		afficherProjets(lesProjets);
	}
	

}
