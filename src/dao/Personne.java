package dao;

import java.util.Calendar;
import java.util.Date;

public abstract class Personne {
	
	protected String nom;
	protected Calendar dateOp;
	protected Calendar dateEmbauche;
	protected Calendar dateDebutRecherche;
	
	public static final int NB_MOIS_EMBAUCHE=3;
	public static final int NB_MOIS_FORMATION=1;
	
	public Personne(String nomP)
	{
		nom=nomP;
		dateOp = Calendar.getInstance();
	}
	public Personne(){
		nom="sample personne";
		dateOp = Calendar.getInstance();
	}
	public Personne(String nomP, Date dateDebutRecherche)
	{
		nom=nomP;
		this.dateDebutRecherche = Calendar.getInstance();
		this.dateEmbauche = Calendar.getInstance();
		this.dateOp = Calendar.getInstance();
		
		this.dateEmbauche.setTime(dateDebutRecherche);
		this.dateEmbauche.setTime(dateDebutRecherche);
		this.dateOp.setTime(dateDebutRecherche);
		
		this.dateEmbauche.add(Calendar.MONTH, NB_MOIS_EMBAUCHE);
		this.dateOp.add(Calendar.MONTH, NB_MOIS_EMBAUCHE + NB_MOIS_FORMATION);	
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
