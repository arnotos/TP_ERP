package dao;

import java.util.Calendar;
import java.util.Date;

public class Projet {
	
	private String nom;
	private int dureeDev;
	private int dureeGestionProjet;
	private Calendar dateFinAttendu;
	
	public Projet(String nomP, int dureeDevP, int dureeGestionProjetP, Date dateFin){
		this.dateFinAttendu = Calendar.getInstance();
		this.dateFinAttendu.setTime(dateFin);
		this.nom=nomP;
		this.dureeDev=dureeDevP;
		this.dureeGestionProjet = dureeGestionProjetP;
	}
	
	public Projet(String name){
		this.nom=name;
		this.dureeDev=0;
		this.dureeGestionProjet = 0;
		this.dateFinAttendu = Calendar.getInstance();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getDureeDev() {
		return dureeDev;
	}
	
	public int getDureeDev(double efficience) {
		return calculDureeEfficience(dureeDev, efficience);
	}
	
	//Permet de calculer d'obtenir la durée du projet avec l'efficience
	public int calculDureeEfficience(int duree, double efficience)
	{
		return (int) Math.round(duree/efficience);
	}

	public void setDureeDev(int dureeDev) {
		this.dureeDev = dureeDev;
	}

	public int getDureeGestionProjet() {
		return dureeGestionProjet;
	}
	
	public int getDureeGestionProjet(double efficience) {
		return calculDureeEfficience(dureeGestionProjet, efficience);
	}

	public void setDureeGestionProjet(int dureeGestionProjet) {
		this.dureeGestionProjet = dureeGestionProjet;
	}

	public Calendar getDateFinAttendu() {
		return dateFinAttendu;
	}

	public void setDateFinAttendu(Calendar dateFinAttendu) {
		this.dateFinAttendu = dateFinAttendu;
	}

	

}
