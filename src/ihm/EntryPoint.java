/**
 * 
 */
package ihm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	
	//var globale d'efficience globale à la simulation (1 = 100%)
	public static double efficienceGlobale = 1;
	

	/**
	 * 
	 * MAIN DU PROJET : interface console
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//variables non statique pour le MAIN 
		ArrayList<Projet> lesProjets;
		ArrayList<Personne> lesPersonnes;
		
		
		//init des managers
		ProjetManager projetManager = new ProjetManager();
		
		//On set la date de début simulation à l'instant t actuel.
		Calendar calendar = Calendar.getInstance();
		
		dateDebutSimulation = calendar;
		dateFinDev = calendar;
		dateFinGestionProjet = calendar;
		
		//init les dev 
		Developper dev1 = new Developper("dev1");
		Developper dev2 = new Developper("dev2");
		Developper dev3 = new Developper("dev3");
		
		//init les chef de projet 
		ChefDeProjet chefProjet1 = new ChefDeProjet("Carlos");
		
		//init les dates
		
		//init les projets
		Projet pro1 = new Projet("pro1");
		Projet pro2 = new Projet("pro2");
		Projet pro3 = new Projet("pro3");
		
		//trie des projets par date de livrable au plus tot
		//si 2 livrables pour la meme dates ( on test tt les combinaisons différentes)
		System.out.println("Hello");
		
		
		
		
		
	}

}
