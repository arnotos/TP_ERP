package dao;

import java.util.Calendar;
import java.util.Date;

public class Developper extends Personne {

	/**
	 * Instancie un Dev qu'on a d�ja chez nous et op�rationnel
	 * avec 100% d'efficience
	 */
	public Developper() {
		super();
		nom="Dev Sample";
	}
	
	/**
	 * Instancie un Dev qu'on a d�ja chez nous et op�rationnel
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
	 * Instancie un nouveau Dev op�rationnel ou non suivantl le param�tre donn�.
	 * avec 100% d'efficience
	 * @param name
	 * @param nouvelEmbauche boolean qui d�finit si on instancie un developper qui vient d'etre embauch� (passe par le processus d'embauche)
	 */
	public Developper(String name,boolean nouvelEmbauche) {
		super();
		nom=name;
		
		if(nouvelEmbauche==true){
			//mettre la date OP � + 4mois ( voir les constantes de la superclasse)
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, NB_MOIS_EMBAUCHE+NB_MOIS_FORMATION);
			
			//set de la futur date op�rationnelle
			dateOp=c;
			
			//process qui met en place via m�thode le bool op�rationnel
			calcFlagOp();
		}
	}
	
	

}
