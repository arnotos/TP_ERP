package dao;

import java.util.Calendar;
import java.util.Date;

public class Projet {
	
	private String nom;
	private int dureeDev;
	private int dureeGestionProjet;
	private Calendar dateFinAttendu;
	
	public Projet(){
		nom="sample constructor";
		dureeDev=0;
		dureeGestionProjet = 0;
		dateFinAttendu = Calendar.getInstance();
	}
	
	public Projet(String name){
		nom=name;
		dureeDev=0;
		dureeGestionProjet = 0;
		dateFinAttendu = Calendar.getInstance();
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

	public void setDureeDev(int dureeDev) {
		this.dureeDev = dureeDev;
	}

	public int getDureeGestionProjet() {
		return dureeGestionProjet;
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
