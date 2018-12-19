package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dao.ChefDeProjet;
import dao.Developper;
import dao.Personne;
import dao.Projet;
import services.PersonneManager.Poste;

public class JSonManager {
	
	//No need constructor
	public static String jsonSituationFromChoice(int choice)
	{
		if(choice >= 1 && choice <= 3)
			return "situation" + choice;
		else
			return "situationLibre";
	}
	
	public static Map<String, Object> jsonReader(int choice) throws FileNotFoundException
	{	
		String situation = jsonSituationFromChoice(choice);
		Gson gson = new Gson();
		//En dure pour le moment à changer plus tard
		File jsonFile = Paths.get("C:\\Users\\wmuller\\Documents\\Cours\\ERP\\TPE3\\TP-ERP-JAVA\\TP_ERP\\src\\json\\input.json").toFile();
		JsonObject jsonObject = gson.fromJson(new FileReader(jsonFile), JsonObject.class);
		JsonObject jsonSituation = jsonObject.getAsJsonObject(situation);
		JsonArray jsonProject = jsonSituation.getAsJsonArray("project");
		JsonArray jsonDev = jsonSituation.getAsJsonArray("developper");
		JsonArray jsonManager = jsonSituation.getAsJsonArray("projectManager");
		JsonObject jsonDateStartSimulation = jsonSituation.get("dateStartSimulation").getAsJsonObject();
		double efficience = jsonSituation.getAsJsonPrimitive("efficiency").getAsDouble();
		
		ArrayList<Projet> lesProjets = new ArrayList<>();
		ArrayList<Personne> lesPersonnes = new ArrayList<>();
		Calendar calStartSimulation = null;
		JsonObject jsonObjectTmp = null;
		
		for(int i = 0 ; i < jsonProject.size() ; i++)
		{
			jsonObjectTmp = jsonProject.get(i).getAsJsonObject();
			lesProjets.add(jsonObjectToProject(jsonObjectTmp));
		}
		
		for(int i = 0 ; i < jsonDev.size() ; i++)
		{
			jsonObjectTmp = jsonDev.get(i).getAsJsonObject();
			lesPersonnes.add(jsonObjectToPersonnes(jsonObjectTmp, Poste.DEVELOPPER));
		}
		
		for(int i = 0 ; i < jsonManager.size() ; i++)
		{
			jsonObjectTmp = jsonManager.get(i).getAsJsonObject();
			lesPersonnes.add(jsonObjectToPersonnes(jsonObjectTmp, Poste.CHEF_DE_PROJET));
		}
		
		calStartSimulation = jsonObjectToCalendar(jsonDateStartSimulation);
		
		Map<String, Object> res = new HashMap<>();
		res.put("Projects", lesProjets);
		res.put("Personnes", lesPersonnes);
		res.put("Efficience", efficience);
		res.put("DateStartSimulation", calStartSimulation);
		return res;
	}
	
	public static Calendar jsonObjectToCalendar(JsonObject jsonDate)
	{
		int year = jsonDate.get("year").getAsInt();
		int month = jsonDate.get("month").getAsInt();
		int dayOfMonth = jsonDate.get("dayOfMonth").getAsInt();		
		Calendar c = GregorianCalendar.getInstance();
		c.clear();
		c.set(year, month, dayOfMonth);
		return c;
	}
	
	public static Projet jsonObjectToProject(JsonObject jsonProject)
	{
		JsonObject dateFin = null;
		Calendar cal = null;
		String nom = jsonProject.get("nom").getAsString();
		int dureeDev = jsonProject.get("dureeDev").getAsInt();
		int dureeGestionProjet = jsonProject.get("dureeGestionProjet").getAsInt();
		dateFin = jsonProject.get("dateFinAttendu").getAsJsonObject();
		cal = jsonObjectToCalendar(dateFin);
		Projet projet = new Projet(nom, dureeDev, dureeGestionProjet, cal.getTime());
		return projet;
	}
	
	public static Personne jsonObjectToPersonnes(JsonObject jsonPersonne, Poste poste)
	{
		JsonObject dateEmbauche = null;
		Calendar cal = null;
		String nom = jsonPersonne.get("nom").getAsString();
		dateEmbauche = jsonPersonne.get("dateEmbauche").getAsJsonObject();
		cal = jsonObjectToCalendar(dateEmbauche);
		Personne personne = null;
		
		if(poste.equals(Poste.CHEF_DE_PROJET))
		{
			personne = new ChefDeProjet(nom, cal.getTime());
		}
		else if(poste.equals(Poste.DEVELOPPER))
		{
			personne = new Developper(nom, cal.getTime());
		}
		
		return personne;
	}

}
