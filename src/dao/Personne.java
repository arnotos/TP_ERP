package dao;

import java.util.Calendar;
import java.util.Date;

public abstract class Personne {
	
	protected String nom;
	protected Calendar dateOp;
	protected Calendar dateEmbauche;
	
	public static final Integer NB_MOIS_EMBAUCHE=3;
	public static final Integer NB_MOIS_FORMATION=1;
	
	public Personne(String nomP)
	{
		nom=nomP;
		dateOp = Calendar.getInstance();
	}
	public Personne(){
		nom="sample personne";
		dateOp = Calendar.getInstance();
	}
	public Personne(String nomP, Date dateEmbauche)
	{
		nom=nomP;
		this.dateEmbauche = Calendar.getInstance();
		this.dateOp = Calendar.getInstance();
		this.dateEmbauche.setTime(dateEmbauche);
		this.dateOp.setTime(dateEmbauche);
		this.dateOp.add(Calendar.MONTH, NB_MOIS_FORMATION);	
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Calendar getDateOp() {
		return dateOp;
	}

	public void setDateOp(Calendar dateOp) {
		this.dateOp = dateOp;
	}
}
