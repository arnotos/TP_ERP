package dao;

import java.util.Calendar;
import java.util.Date;

public abstract class Personne {
	
	protected String nom;
	//protected boolean op;
	protected Calendar dateOp;
	protected Calendar dateEmbauche;
	//protected Integer coutAuMois;
	//protected double efficience;
	
	public static final Integer NB_MOIS_EMBAUCHE=3;
	public static final Integer NB_MOIS_FORMATION=1;
	
	public Personne(String nomP)
	{
		nom=nomP;
		dateOp = Calendar.getInstance();
		//op = true;
		//coutAuMois=0;
		//efficience=1;
	}
	public Personne(){
		nom="sample personne";
		dateOp = Calendar.getInstance();
		//op = true;
		//coutAuMois=0;
		//efficience=1;
	}
	public Personne(String nomP, Date dateEmbauche)
	{
		nom=nomP;
		this.dateEmbauche = Calendar.getInstance();
		this.dateOp = Calendar.getInstance();
		this.dateEmbauche.setTime(dateEmbauche);
		this.dateOp.setTime(dateEmbauche);
		this.dateOp.add(Calendar.MONTH, NB_MOIS_FORMATION);
		//op = true;
		//coutAuMois=0;
		//efficience=1;		
	}
	
	/*public boolean calcFlagOp(){
		if(dateOp.after(Calendar.getInstance())){ //FIXME il faudra a terme utiliser une date de simulation (simuler une date actuelle)
			op=false;
		}else{
			op=true;
		}
		return op;
	}*/

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/*public boolean isOp() {
		return op;
	}

	public void setOp(boolean op) {
		this.op = op;
	}*/


	public Calendar getDateOp() {
		return dateOp;
	}

	public void setDateOp(Calendar dateOp) {
		this.dateOp = dateOp;
	}

	/*public Integer getCoutAuMois() {
		return coutAuMois;
	}

	public void setCoutAuMois(Integer coutAuMois) {
		this.coutAuMois = coutAuMois;
	}

	public double getEfficience() {
		return efficience;
	}

	public void setEfficience(double efficience) {
		this.efficience = efficience;
	}*/
}
