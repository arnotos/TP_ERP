package dao;

import java.util.Date;

public class ChefDeProjet extends Personne{

	public ChefDeProjet() {
		super();
		
		nom="sample chef de projet";
	}
	
	public ChefDeProjet(String name) {
		super();
		
		nom=name;
	}
	
	public ChefDeProjet(String name, Date dateOp) {
		super(name, dateOp);
	}
	
	

}
