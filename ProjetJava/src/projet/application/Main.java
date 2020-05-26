package projet.application;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import projet.data.FileReader;
import projet.exceptions.formatEANException;
import projet.exceptions.formatISBNException;
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
		
	}
	public static void menu(Reseau reseau) {
		Scanner sc = new Scanner(System.in);
		
		boolean marche = true;
		int commande = 0;
		while(marche) {
			//si on est dans le menu
			if(commande ==0) {
				afficheMenu();
				System.out.println("Veuillez choisir une commande :");
				String str = sc.nextLine();
				System.out.println("Vous avez choisis, : " + str);
				commande = Integer.parseInt(str);
			}
			//si commande 1
			if(commande ==1) {
				System.out.println("Veuillez choisir une commande :");
				String str = sc.nextLine();
				System.out.println("Vous avez choisis, : " + str);
				commande = Integer.parseInt(str);
			}
			
		}
	}
	
	
	
	public static void main(String[] args) 
	{
		
		Reseau parisBiblios = new Reseau(); 
		Bibliotheque aimeCesaire = new Bibliotheque("AimeCesaire",parisBiblios ); 
		Bibliotheque edmondRostand = new Bibliotheque("EdmondRostand",parisBiblios ); 
		Bibliotheque jeanPierreMelville = new Bibliotheque("JeanPierreMelville",parisBiblios ); 
		Bibliotheque oscardWilde = new Bibliotheque("OscarWilde",parisBiblios ); 
		Bibliotheque saintSimon = new Bibliotheque("SaintSimon",parisBiblios ); 
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
		//menu(parisBiblios);
		
		aimeCesaire.searchDocumentsAuthorSurname("Jean");
		
		
		
		//TODO Project :)
	}
}
