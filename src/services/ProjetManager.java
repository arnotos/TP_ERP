package services;

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
	
	
	
}
