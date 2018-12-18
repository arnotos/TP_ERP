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
	
	public ArrayList<ArrayList<Projet>> getAllPossibilities(List<Projet> lesProjets)
	{
		/*ArrayList<ArrayList<Projet>> allPossibilities = new ArrayList<>();
		ArrayList<ArrayList<Projet>> allEqualsCombinaisons = new ArrayList<>();
		ArrayList<Projet> equalProject = new ArrayList<>();
		int startingEqual = 0;
		Boolean isEqual = false;
		
		
		for(int i = 0 ; i <= lesProjets.size() ; i++)
		{
			
			if(!lesProjets.get(i).getDateFinAttendu().equals(lesProjets.get(i + 1)))
			{
				
				if(isEqual = true)
				{
					equalProject.clear();
					for(int j = startingEqual ; j <= i ; j++)
					{
						equalProject.add(lesProjets.get(j));
					}
					
					allEqualsCombinaisons = get
				}
				
				for (ArrayList<Projet> possibility : allPossibilities)
				{
					possibility.add(lesProjets.get(i));
				}
				isEqual = false;
			}
			else if(lesProjets.get(i).getDateFinAttendu().equals(lesProjets.get(i + 1)))
			{
				isEqual = true;
				startingEqual = i;
			}
			
		}
		
		return allPossibilities;*/

		ArrayList<Integer> listzz = new ArrayList<>();
		listzz.add(1);
		listzz.add(2);
		listzz.add(3);
		//listzz.add(4);
		recursif(listzz, "");
		return null;
	}
	
	private void recursif(ArrayList<Integer> listProject, String chaine)
	{
		ArrayList<Integer> listRemoved = new ArrayList<>();
		for(int i = 0 ; i <= listProject.size() - 1; i++)
		{
			listRemoved.clear();
			for (Integer integer : listProject)
			{
				listRemoved.add(integer);
			}
			listRemoved.remove(i);
			
			recursif(listRemoved, chaine + listProject.get(i));
		}
		
		if(listRemoved.isEmpty() && listProject.size() == 0)
		{
			System.out.println(chaine);
		}
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
