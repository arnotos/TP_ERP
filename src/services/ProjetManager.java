package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dao.Projet;

/**
 * Manager concernant le traitement des projets
 * 
 * @author arnot
 *
 */
public class ProjetManager {
	
	
	/**
	 * Tri des projets par ordre croissant sur les dates de fin attendues
	 * 
	 * @param lesProjets
	 */
	public void triProjetAuPlusTot(List<Projet> lesProjets){
		//le tri des projets par date au plus tôt
		Collections.sort(lesProjets,new Comparator<Projet>() {
			@Override
			public int compare(final Projet o1, final Projet o2) {
				return o1.getDateFinAttendu().compareTo(o2.getDateFinAttendu());
			}
		});
	}
	
	/**
	 * Tri des projets par 
	 */
	
	
	/**
	 * Affichage des projets sur la console
	 * @param projets
	 */
	public void afficherProjets(ArrayList<Projet> projets)
	{
		for(Projet p : projets)
			System.out.println(p.getNom() + " -> JoursDev: " + p.getDureeDev() + " JoursGest: "+ p.getDureeGestionProjet() + " DateFin: " + p.getDateFinAttendu().getTime());
		System.out.println("\n");
	}
	
	/**
	 * Affiche sur la console pour chaque projet de la liste son non et sa date 
	 * @param lesProjets
	 */
	public void afficherDatesProjets(List<Projet> lesProjets){
		System.out.println("# # # Affichage des dates et durées des projets # # #");
		for(Projet p : lesProjets){
			System.out.println("Nom :'"+p.getNom()+"' Durée dev :"+p.getDureeDev()+" Durée de gest-pro :"+p.getDureeGestionProjet()+" Date fin attentdu :"+p.getDateFinAttendu().getTime());
		}
		System.out.println("# # #___________________________________________# # #");
	}
	
}
