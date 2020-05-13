package projet.application;

import java.io.File;
import java.util.HashMap;

import projet.data.FileReader;
import projet.exceptions.formatEANException;
import projet.exceptions.formatISBNException;
import projet.classesProjet.*;

public class Main 
{
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
	
		//parisBiblios.ShowAllDocuments();
		//parisBiblios.searchSerie("Dragonball");
		String isbn = "978-2-03-585916-7";
		isbn = isbn.replace("-", ""); 
		try {
			
			System.out.println(parisBiblios.searchISBN(isbn));
		}catch(formatISBNException e) {
			System.out.println("erreur ouille");
		}
		
		try {
			
			System.out.println(parisBiblios.searchEAN("9782758510598"));
		}catch(formatEANException e) {
			System.out.println("erreur ouille");
		}
		
		
		parisBiblios.searchDocumentsAuthor("Vian", "Boris"); 
		
		parisBiblios.searchSerie("Angel voice");
		parisBiblios.searchDocumentsAuthorName("Walter");
		
		
		//TODO Project :)
	}
}
