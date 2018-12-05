package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ChefDeProjet;
import dao.Developper;
import dao.Personne;
import ihm.EntryPoint;

/**
 * Manager concernant le traitement des personnes
 * 
 * incluants les traitements des 
 * - DEV
 * - Chef de projet
 * - ...
 * 
 *
 */
public class PersonneManager {
	
	
	/* ICI dans ce manager mettre les méthodes méthiers concernant les personnes */
	
	/**
	 * Enum servant au choix dans les params de fonctions 
	 * ex: dans l'appel de la fonc force de travail
	 * @author arnot
	 *
	 */
	public enum Poste{DEVELOPPER,CHEF_DE_PROJET;}
	
	
	/**
	 * Calcule et renvoie la force de travail disponible de l'équipe au jours j
	 * 
	 * @param lesPersonnes liste des personnes
	 * @param calendar : date souhaité pour ce calcul
	 * @param poste : enum Poste (définit dans PersonneManager)
	 * @return
	 */
	public int getForceDeTravail(List<Personne> lesPersonnes,Calendar calendar,Poste poste){
		int uniteForceTravail = 0;
		
		
		for(Personne p: lesPersonnes){ // on traite toutes les personnes
			if(poste.equals(Poste.DEVELOPPER)){ // on check quel type on veut traiter avant de poursuivre
				if(!(calendar.before(p.getDateOp()))){  // évite de faire after true && isToday ==> l'inverse est si !=before
					if(p instanceof Developper){
						uniteForceTravail = uniteForceTravail + 1;
					}
				}
			}else{
				if(poste.equals(Poste.CHEF_DE_PROJET)){
					if(!(calendar.before(p.getDateOp()))){  // évite de faire after true && isToday ==> l'inverse est si !=before
						if(p instanceof ChefDeProjet){
							uniteForceTravail = uniteForceTravail + 1;
						}
					}
				}
			}
		}
		
		return uniteForceTravail;
	}
	
	/**
	 * Utilise la methode getForce de travail en associant le résultat au coefficient global
	 * @param lesPersonnes
	 * @param calendar
	 * @param poste
	 * @return
	 */
	public double getForceDeTravailCoeficient(List<Personne> lesPersonnes,Calendar calendar,Poste poste) {
		return getForceDeTravail(lesPersonnes, calendar, poste)*EntryPoint.efficienceGlobale;
	}
	
	/**
	 * Affichage console de l'équipes
	 * @param equipe
	 */
	public void afficherEquipe(ArrayList<Personne> equipe)
	{
		for(Personne p : equipe)
			System.out.println(p.getNom() + " : " + p.getClass().toString().replaceAll("class dao.", ""));
		System.out.println("\n");
	}
	

}
