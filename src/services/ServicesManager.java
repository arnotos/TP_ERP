package services;

import java.util.Calendar;

/**
 * Manager concernant les traitements divers
 * 
 *
 */
public class ServicesManager {
	
	
	/* ICI dans ce manager mettre les méthodes méthiers concernant les demandes diverses */


	public void afficherTitre()
	{
		System.out.println("_________________________________            _________________________________");
		System.out.println("_________________________________ PROJET ERP _________________________________");
		System.out.println("\n");
	}
	
	//TODO à mettre dans une classe
	public long differenceBetweenToCalendar(Calendar calExpected, Calendar calEnd)
	{
		long millisExpected = calExpected.getTimeInMillis();
		long millisEnd = calEnd.getTimeInMillis();
		long diff = millisExpected - millisEnd;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}
	
}
