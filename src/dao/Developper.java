package dao;

import java.util.Calendar;
import java.util.Date;

public class Developper extends Personne {

	/**
	 * Instancie un Dev qu'on a déja chez nous et opérationnel
	 * avec 100% d'efficience
	 */
	public Developper() {
		super();
		nom="Dev Sample";
	}
	
	/**
	 * Instancie un Dev qu'on a déja chez nous et opérationnel
	 * avec 100% d'efficience
	 * @param name nom du dev
	 */
	public Developper(String name) {
		super();
		nom=name;
	}
	
	public Developper(String name, Date dateOp) {
		super(name, dateOp);
	}
	
	/**
	 * Instancie un nouveau Dev opérationnel ou non suivantl le paramètre donné.
	 * avec 100% d'efficience
	 * @param name
	 * @param nouvelEmbauche boolean qui définit si on instancie un developper qui vient d'etre embauché (passe par le processus d'embauche)
	 */
	public Developper(String name,boolean nouvelEmbauche) {
		super();
		nom=name;
		
		if(nouvelEmbauche==true){
			//mettre la date OP à + 4mois ( voir les constantes de la superclasse)
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, NB_MOIS_EMBAUCHE+NB_MOIS_FORMATION);
			
			//set de la futur date opérationnelle
			dateOp=c;
			
			//process qui met en place via méthode le bool opérationnel
			calcFlagOp();
		}
	}
	
	

}
