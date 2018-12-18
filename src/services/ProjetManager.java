package services;

import java.lang.reflect.Array;
import java.rmi.dgc.Lease;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import org.w3c.dom.ls.LSInput;

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
	public void triProjetAuPlusTot(List<Projet> lesProjets)
	{
		//le tri des projets par date au plus tôt
		Collections.sort(lesProjets,new Comparator<Projet>() {
			@Override
			public int compare(final Projet o1, final Projet o2) {
				return o1.getDateFinAttendu().compareTo(o2.getDateFinAttendu());
			}
		});
	}
	
	public ArrayList<Projet> sortProjectWithSameEndDate(ArrayList<Projet> lesProjets)
	{
		int size = lesProjets.size();
		Projet p1 = null;
		Projet p2 = null;
		
		for(int i = 0 ; i < size ; i++)
		{
			for(int j = i + 1 ; j < size; j++)
			{
				p1 = lesProjets.get(i);
				p2 = lesProjets.get(j);
				if(p1.getDateFinAttendu().equals(p2.getDateFinAttendu()))
				{
					//Ils ont la même date de fin
					if((p2.getDureeDev() <= p1.getDureeDev() && p2.getDureeDev() <= p1.getDureeGestionProjet()) ||
					   (p2.getDureeGestionProjet() <= p1.getDureeDev() && p2.getDureeGestionProjet() <= p1.getDureeGestionProjet()))
					{
						lesProjets.set(i, p2);
						lesProjets.set(j, p1);
					}
				}
			}
		}
		
		return lesProjets;
	}
	
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
