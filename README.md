***********************************README*****************************************************

Projet ERP MULLER LAMBS HORVAT LAPENNA 


Au lancement, la console s'ouvre en affichant un choix permettant de choisir la situation shouhaitée.
Une fois la situation sélectionée, la console affiche dans l'ordre :  


-Affichage des dates et durées des projets : 
	Un affichage sommaire des projets de la situation en cours.

-Présentation de l'équipe : 
	Les membres de l'équipe et leurs rôles pour la situation en cours.

-Liste des projets :
	Liste tous les projets chargés dans la configuration actuelle.
	Leurs durées de développement, leurs durées de gestion de projet et leurs dates de fin estimée.

-Liste des projets triés :
	La liste des projets, mais triés par date de fin.
	Si deux projets ont la même date de fin, on prendra le plus cours en premier.

	
Ensuite, la console affiche la complétion des projets les uns après les autres dans le format qui suis : 
	Nom du projet
	Nombre de jours de développement en prenant en compte l'efficience
	Nombre de jours de gestion de projet en prenant en compte l'efficience
	Jour de fin du développement
	Jour de fin de la gestion de projet
	L'avance ou le retard de fin du projet. (Un nombre positif indique une avance, un nombre négatif indique un retard)
	


Nous importons les situations depuis un fichier input.json. Les fichiers de 1 à 4 permettent de répondre aux questions :
Situation 1 : Question A et B
Situation 2 : Question C
Situation 3 : Question D
Situation 4 : Question E
Situation Libre : à modifier à loisir pour tests.

Pour gérer les Json nous utilisons la librairie de Google Gson.


Réponses aux questions : 

Questions A et B : 
	Oui, voir le programme pour les détails.
	
Questions C :
	Non, nous avons du retard sur le dernier projet, voir le programme pour les détails.
	
Questions D : 
	Nous n'aurons pas le temps de terminer le dernier projet(Sony) et ne pouvons donc pas valider la date du 01/01.
	Il faut embaucher du personnel en plus, ou repousser la date butoire. De plus, une efficience de 120% pour un humain est peu crédible.
	
Question E : 
	Pour cette question, nous sommes retournés à une efficience de 100%.
	Nous avons embauché 4 développeurs et un chef de projet.
	Avec ces embauches, nous bouclons les projets avec une avance de 10 jours.
	
	
	