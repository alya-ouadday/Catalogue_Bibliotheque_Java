package projet.application;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import projet.data.FileReader;
import projet.exceptions.formatEANException;
import projet.exceptions.formatISBNException;
import projet.exceptions.inscriptionException;
import projet.exceptions.nonDispoException;
import projet.exceptions.quotaException;
import projet.classesProjet.*;

public class Main 
{
	public static void afficheMenu() {
		System.out.println("Choisissez le numéro de votre commande : ");
		System.out.println("1 - ajouter une nouvelle bibliothèque au reseau ");
		System.out.println("2 - ajouter document");
		System.out.println("3 - ajouter nouvel utilisateur");
		System.out.println("4 - afficher l'ensemble des document ");
		System.out.println("5 - afficher les document d'une série");
		System.out.println("6 - afficher les documents d'un auteur");
		System.out.println("7 - afficher un livre par son ISBN");
		System.out.println("8 - afficher un document par son EAN");
		System.out.println("9 - afficher le nombre de doc entre deux date");
		System.out.println("10 - emprunter un document");
		System.out.println("11 - rendre un document");
		System.out.println("12 - Quitter");

	}
	public static void menu(Reseau reseau) {
		Scanner sc = new Scanner(System.in);
		
		boolean marche = true;
		int commande = 0;
		int typeAction = 0; //si egal à 0 alors on fait les action sur le réseau sinon on les fait sur une bibliotheque.
		while(marche) {
			//si on est dans le menu
			if(commande ==0) {
				afficheMenu();
				System.out.println("Veuillez choisir une commande :");
				String str = sc.nextLine();
				System.out.println("Vous avez choisis : " + str);
				try {
					commande = Integer.parseInt(str);
					if(commande < 0 || commande > 10) {
						System.out.println("Veuillez entrer un numéro de commande entre 1 et 10 \n "
								+ " Appuyer sur entrer pour revenir au menu");
						str = sc.nextLine();
						commande = 0; 
					}
				}catch(NumberFormatException e) {
					System.out.println("Veuillez entrer un numéro de commande entre 1 et 10 \n "
							+ " Appuyer sur entrer pour revenir au menu");
					str = sc.nextLine();
					commande = 0; 
				}
			}
			//si commande 1
			else if(commande ==1) {
				
				System.out.println("Veuillez saisir le nom de la nouvelle bibliotheque :");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
				reseau.addBiblio(new Bibliotheque(str,reseau));
				System.out.println("La nouvelle bibliothèque " + str + " a ete ajoutee au reseau.");
				commande=0;
				}	
			}
			//si commande 2
			else if(commande ==2) {
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					
				commande=0;
				}	
			}
			//si commande 3
			else if(commande ==3) {
				System.out.println("Taper sur entreée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					
				commande=0;
				}	
			}
			//si commande 4
			else if(commande ==4) {
				System.out.println("Saisir 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
					typeAction = Integer.parseInt(str);
					if(typeAction != 1 && typeAction != 0) {
						System.out.println("Erreur. Il faut saisir 0 ou 1");
						System.out.println("Taper sur entrée pour revenir au menu");
						str = sc.nextLine();
						commande = 0; 
					}
					}catch(NumberFormatException e) {
						System.out.println("Erreur. Il faut saisir 0 ou 1");
						System.out.println("Taper sur entrée pour revenir au menu");
						str = sc.nextLine();
						commande = 0; 
					}
					if(typeAction == 0) {
						reseau.ShowAllDocuments();
					}else if(typeAction == 1) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							reseau.getListeBiblio().get(str).ShowAllDocuments();
						}
					}
					System.out.println("Taper sur entrer pour revenir au menu");
					str = sc.nextLine();
					commande=0;
				}	
			}
			//si commande 5
			else if(commande ==5) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0) {
						System.out.println("Entrer le nom d'une serie");
						str = sc.nextLine();
						reseau.searchSerie(str);
					}else if(typeAction == 1) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
			
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else {
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Entrer le nom d'une serie");
							str = sc.nextLine();
							bibli.searchSerie(str);
						}
						System.out.println("Taper entrée pour retourner au menu "); 
						str = sc.nextLine(); 
						commande = 0;
						
						
					}
					 
				}	
			}
			//si commande 6
			else if(commande ==6) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0) {
						System.out.println("Nous allons vous demander le nom et le prenom de l'auteur");
						System.out.println("d'abord entrer le nom de l'auteur :");
						System.out.println("Taper sur entrée si vous ne connaissez pas le nom");
						String nom = sc.nextLine();
						System.out.println("maintenant entrer le prenom de l'auteur :");
						System.out.println("Taper sur entrée si vous ne connaissez pas le prenom");
						String prenom = sc.nextLine();
						if(!nom.isEmpty() && !prenom.isEmpty()) {
							reseau.searchDocumentsAuthor(nom+ " "+ prenom);
						}else if(!nom.isEmpty() && prenom.isEmpty()) {
							reseau.searchDocumentsAuthorName(nom);
						}else if (nom.isEmpty() && !prenom.isEmpty()) {
								reseau.searchDocumentsAuthorSurname(prenom);}
					}else if(typeAction == 1) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Nous allons vous demander le nom et le prenom de l'auteur");
							System.out.println("d'abord saisir le nom de l'auteur :");
							System.out.println("Taper sur entrée si vous ne connaissez pas le nom");
							String nom = sc.nextLine();
							System.out.println("maintenant saisir le prenom de l'auteur :");
							System.out.println("Taper sur entrée si vous ne connaissez pas le prenom");
							String prenom = sc.nextLine();
							if(!nom.isEmpty() && !prenom.isEmpty()) {
								bibli.searchDocumentsAuthor(nom + " " + prenom);
							}else if(!nom.isEmpty() && prenom.isEmpty()) {
								bibli.searchDocumentsAuthorName(nom);
							}else bibli.searchDocumentsAuthorSurname(prenom);
						}
						System.out.println("Taper sur entrée pour revenir au menu");
						str = sc.nextLine();
						commande=0;
					}	
				}
			}
			//si commande 7
			else if(commande ==7) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0) {
						System.out.println("Entrer l'ISBN du livre que vous cherchez :");
						str = sc.nextLine();
						try {
							reseau.searchISBN(str);
						} catch (formatISBNException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(typeAction == 1) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Entrer l'ISBN du livre que vous cherchez :");
							str = sc.nextLine();
							try {
								bibli.searchISBN(str);
							} catch (formatISBNException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					System.out.println("Taper sur entrée pour retourner au menu");
					str = sc.nextLine();
					commande=0;
				}	
			}
			//si commande 8
			else if(commande ==8) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0) {
						System.out.println("Entrer l'EAN du document que vous cherchez :");
						str = sc.nextLine();
						try {
							reseau.searchEAN(str);
						} catch (formatEANException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(typeAction == 1) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Entrer l'EAN du document que vous cherchez :");
							str = sc.nextLine();
							try {
								bibli.searchEAN(str);
							} catch (formatEANException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					System.out.println("Entrer une chaine quelconque pour revenir au menu");
					str = sc.nextLine();
					commande=0;
				}	
			}
			//si commande 9
			else if(commande ==9) {
				System.out.println("Entrer 0 pour afficher sur le reseau ou 1 pour afficher pour une bibliotheque");
				System.out.println("Taper sur entrée pour revenir au menu");
				String str = sc.nextLine();
				if(str.isEmpty()) {
					commande = 0;
				}else {
					try {
						typeAction = Integer.parseInt(str);
						if(typeAction != 1 && typeAction != 0) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
						}catch(NumberFormatException e) {
							System.out.println("Erreur. Il faut saisir 0 ou 1");
							System.out.println("Taper sur entrée pour revenir au menu");
							str = sc.nextLine();
							commande = 0; 
						}
					if(typeAction == 0) {
						System.out.println("Nous allons vous demander la période sur laquelle vous souhaiter connaitre le nombre de document");
						System.out.println("d'abord l'annee à partir de laquelle vous voulez compter :");
						String debut = sc.nextLine();
						System.out.println("maintenant l'annee jusqu'à laquelle vous voulez compter :");
						String end = sc.nextLine();
						int debutNumber = 0;
						int endNumber = 0;
						try {
							debutNumber = Integer.parseInt(debut);
							endNumber = Integer.parseInt(end);
							if(debutNumber < 0 || endNumber > 2020 || debutNumber > 2020 || endNumber < 0) {
								debutNumber = 0; 
								endNumber = 0; 
								System.out.println("Vos dates sont erronees");
							}
							else {
								reseau.searchNumberPeriod(debutNumber, endNumber);
							}
						}catch(NumberFormatException e ){
							System.out.println("Vos dates sont erronees");
							debutNumber = 0; 
							endNumber = 0; 
						}
						
						
					}else if(typeAction == 1) {
						System.out.println("Entrer le nom d'une bibliotheque");
						str = sc.nextLine();
						while((!reseau.getListeBiblio().containsKey(str)) && !str.isEmpty()) {
							System.out.println("Cette bibliothèque n'existe pas dans le reseau, réessayer ou taper entrer pour quitter : "); 
							str = sc.nextLine();
						}
						if (str.isEmpty()) {
							commande = 0;
						}
						else{
							Bibliotheque bibli = reseau.getListeBiblio().get(str);
							System.out.println("Nous allons vous demander la période sur laquelle vous souhaiter connaitre le nombre de document");
							System.out.println("d'abord l'annee à partir de laquelle vous voulez compter :");
							String debut = sc.nextLine();
							System.out.println("maintenant l'annee jusqu'à laquelle vous voulez compter :");
							String end = sc.nextLine();
							int debutNumber = 0;
							int endNumber = 0;
							try {
								debutNumber = Integer.parseInt(debut);
								endNumber = Integer.parseInt(end);
								if(debutNumber < 0 || endNumber > 2020 || debutNumber > 2020 || endNumber < 0) {
									debutNumber = 0; 
									endNumber = 0; 
									System.out.println("vos dates sont erronees");
								}
								else {
									reseau.searchNumberPeriod(debutNumber, endNumber);
								}
							}catch(NumberFormatException e ){
								System.out.println("vos dates sont erronees");
								debutNumber = 0; 
								endNumber = 0; 
							}
						}
					}
						System.out.println("Taper sur entrer pour revenir au menu");
						str = sc.nextLine();
						commande=0;
						
				}
			}
				
		}
	}
	
	
	
	public static void main(String[] args) 
	{
		
		Reseau parisBiblios = new Reseau(); 
		Bibliotheque aimeCesaire = new Bibliotheque("aime cesaire",parisBiblios ); 
		Bibliotheque edmondRostand = new Bibliotheque("edmond rostand",parisBiblios ); 
		Bibliotheque jeanPierreMelville = new Bibliotheque("jean pierre melville",parisBiblios ); 
		Bibliotheque oscardWilde = new Bibliotheque("oscar wilde",parisBiblios ); 
		Bibliotheque saintSimon = new Bibliotheque("saint simon",parisBiblios ); 
		parisBiblios.addBiblio(aimeCesaire);
		parisBiblios.addBiblio(edmondRostand);
		parisBiblios.addBiblio(jeanPierreMelville);
		parisBiblios.addBiblio(oscardWilde);
		parisBiblios.addBiblio(saintSimon);
		
		//On recupere les donnees  
		if(args.length > 0)
		{
			File tempFile = new File(args[0]);
			
			if(tempFile.exists())
			{
				System.out.println("[Main] Reading the file " + args[0] + " ...");
						
				//We start by reading the CSV file
				FileReader.getDataFromCSVFile(args[0], parisBiblios);
				
				System.out.println("[Main] End of the fi le " + args[0] + ".");
			}
			else
			{
				System.out.println("[Main] No file " + args[0]);
			}
		}
		else
		{
			System.out.println("[Main] You should enter the CSV file path as a parameter.");
		}
		
		/*
		//parisBiblios.searchDocumentsAuthorSurname("Boris"); 
		parisBiblios.searchNumberPeriod(2008, 2010);
		
		//aimeCesaire.searchSerie("Calamity Mamie");
		//edmondRostand.searchSerie("Calamity Mamie");
		aimeCesaire.searchNumberPeriod(2008, 2010);
		edmondRostand.searchNumberPeriod(2008, 2010);
		jeanPierreMelville.searchNumberPeriod(2008, 2010);
		oscardWilde.searchNumberPeriod(2008, 2010);
		saintSimon.searchNumberPeriod(2008, 2010);
		*/
		//parisBiblios.searchSerie("Calamity Mamie");
		//aimeCesaire.searchSerie("Calamity Mamie");
		//edmondRostand.searchSerie("Calamity Mamie");
		//menu(parisBiblios);
		
	
		Utilisateur jean = new Utilisateur("jean", 3); 
		try {
		Document docu1 = parisBiblios.searchEAN("9782070638444");
		Document docu2 = parisBiblios.searchEAN("9782070637119");
		Document docu3 = parisBiblios.searchEAN("9782747035200");
		Document docu4 = parisBiblios.searchEAN("9782070638291");
		try {
			jean.sInscrire(edmondRostand);
			System.out.println(jean.getListeBibliothèque());
		}
		
		catch(inscriptionException e){
			System.out.println("inscrption");
		}
		try{
			jean.emprunter(edmondRostand, docu1);
			jean.emprunter(edmondRostand, docu2);
			System.out.println(jean.getListeDocument());
			
		}catch(quotaException e){
			e.printStackTrace();
		} catch (nonDispoException e) {
			
			e.printStackTrace();
		} catch (inscriptionException e) {
			
			e.printStackTrace();
		}
		}
		catch(formatEANException e) {
			e.printStackTrace();
		}
		
		
		
		
		//TODO Project :)
	}
}
