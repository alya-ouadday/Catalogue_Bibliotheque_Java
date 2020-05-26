package projet.data;

import projet.classesProjet.*;
import projet.application.*;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader 
{
	public static void getDataFromCSVFile(String csvFilePath, Reseau reseau)
	{
        String line = "";
        String[] data = null;
        String separator = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        
        //Document data
        String isbn;
        String ean;
        String title;
        String publisher;
        String seriesTitle;
        Integer seriesNumber;
        String authorName;
        String authorSurname;
        String type;
        int date;
        int totalCopies;
        int numberCopyAimeCesaire;
        int numberCopyEdmondRostand;
        int numberCopyJeanPierreMelville;
        int numberCopyOscarWilde;
        int numberCopySaintSimon;
        
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(csvFilePath), StandardCharsets.ISO_8859_1)) 
        {
        	//Read the first line
        	line = bufferedReader.readLine();
        	
        	//Get data from the line
        	data = line.split(separator, -1);
        	
        	if(data.length != 16)
        	{
        		System.out.println("[FileReader] The file at " + csvFilePath + " does not contain the right number of columns.");
        		return;
        	}
        	
        	int i = 1;
        	
        	//Read the file line by line
            while ((line = bufferedReader.readLine()) != null)
            {
            	//Get data from the line
            	data = line.split(separator, -1);
            	
            	//Sort data
            		
            		//Get the ISBN number
            		isbn = data[0].replace("-", "");//On remplace les tirets par rien 
            		
            		//Get the EAN number
            		ean = data[1];
            		
            		//Get the title of the document
            		title = data[2];

            		//Get the name of the publisher
            		publisher = data[3];
            		
            		//Get the publication date
            		try
            		{
            			date = Integer.parseInt(data[4].replaceAll("[^0-9]", ""));
            			
            			if(date%10000 >= 2021 || date%10000 < 0)
            			{
            				date = 0;
            			}
            			
            		}
            		catch (Exception exception)
            		{
            			date = 0;
            		}
            		
            		//Get the title of the series
            		seriesTitle = data[5];
            		
            		//Get the number of this document in the series
            		try
            		{
            			seriesNumber = Integer.parseInt(data[6]);
            		}
            		catch (Exception exception)
            		{
            			seriesNumber = 0;
            		}
            		
            		//Get the name of the author
            		authorName = data[7];
            		
            		//Get the surname of the author
            		authorSurname = data[8];
            		
            		//Get the type of the document
            		type = data[9];
            		
            		//Get the total number of copy in Paris for this document 
            		try
            		{
            			totalCopies = Integer.parseInt(data[10]);
            		}
            		catch (Exception exception)
            		{
            			totalCopies = 0;
            		}
            		
            		//Get the number of copy in the library "Aime Cesaire"
            		try
            		{
            			numberCopyAimeCesaire = Integer.parseInt(data[11]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyAimeCesaire = 0;
            		}
            		
            		//Get the number of copy in the library "Edmond Rostand"
            		try
            		{
            			numberCopyEdmondRostand = Integer.parseInt(data[12]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyEdmondRostand = 0;
            		}
            		
            		//Get the number of copy in the library "Jean-Pierre Melville"
            		try
            		{
            			numberCopyJeanPierreMelville = Integer.parseInt(data[13]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyJeanPierreMelville = 0;
            		}
            		
            		//Get the number of copy in the library "Oscar Wilde"
            		try
            		{
            			numberCopyOscarWilde = Integer.parseInt(data[14]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyOscarWilde = 0;
            		}
            		
            		//Get the number of copy in the library "Saint-Simon"
            		try
            		{
            			numberCopySaintSimon = Integer.parseInt(data[15]);
            		}
            		catch (Exception exception)
            		{
            			numberCopySaintSimon = 0;
            		}
                
                
            	/*
                System.out.println(
                		isbn + ";" +
                		ean + ";" +
                		title + ";" +
                		publisher + ";" +
                		date + ";" +
                		seriesTitle + ";" +
                		seriesNumber + ";" +
                		authorName + ";" +
                		authorSurname + ";" +
                		type + ";" +
                		totalCopies + ";" +
                		numberCopyAimeCesaire + ";" +
                		numberCopyEdmondRostand + ";" +
                		numberCopyJeanPierreMelville + ";" +
                		numberCopyOscarWilde + ";" +
                		numberCopySaintSimon);
                */
                /*
                 * methode à rajouter 
                 * addDocument dans Serie
                 * addDocument dans bibliotheque
                 * 
                 */
               
                
                
                
                Document document = null;
                
                //si le document n'appartient pas à une série
                if(seriesTitle.isEmpty()) {
                	//si le document est identifiable
                	if((!ean.isEmpty()) || (!isbn.isEmpty())) {
	                	//si le doc a un ean et pas d'isbn
		                if( (!ean.isEmpty()) && (isbn.isEmpty())) {
		                		//si le doc est un vinyle
		                		if(type.matches("Vinyle(.*)")) {
		                			document = new Vinyle(ean,title,publisher,date,authorName,authorSurname,type, totalCopies);	
		                		}
		                		//si le doc est un CD
		                		else if(type.matches("Disque compact(.*)")) {
		                			document = new CD(ean,title,publisher,date,authorName,authorSurname,type, totalCopies);	
		                		}
		                		//si le doc est un Jeu de societe
		                		else if(type.matches("Jeux de(.*)")) {
		                			document = new JeuDeSociete(ean,title,publisher,date,authorName,authorSurname,type, totalCopies);	
		                		}
		                		//si le doc est un Jeu video
		                		else if(type.matches("Jeux Videos(.*)")) {
		                			document = new JeuVideo(ean,title,publisher,date,authorName,authorSurname,type, totalCopies);	
		                		}
		                		//sinon on le met dans autre
		                		else {
		                			document = new Autre(ean,title,publisher,date,authorName,authorSurname,type, totalCopies);	
		                		}
		                		
		                }
		                //si le doc a un ISBN
		                else{
		                	//si le doc est une partition
	                		if(type.matches("Partition(.*)")) {
	                			document = new Partition(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn);	
	                		}
	                		//si le doc est une carte
	                		else if(type.matches("Carte(.*)")) {
	                			document = new Carte(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn);	
	                		}
	                		//si le doc est une revue
	                		else if(type.matches("Revue(.*)")) {
	                			document = new Revue(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn);	
	                		}
	                		//si le doc est une Bande Dessinee
	                		else if(type.matches("Bande dessinee(.*)")) {
	                			document = new BandeDessinee(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn);	
	                		}
	                		//sinon c'est juste un livre
	                		else {
	                			document = new Livre(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn);	
	                		}
		                }	
                	}
	            }
                //si le document appartient à une série
                else {
                	//si le document est identifiable
                	if((!ean.isEmpty()) || (!isbn.isEmpty())) {
                		//on commence par gerer sa serie
                		Serie serie = new Serie(seriesTitle);
                		
                		if((reseau.getListeSerie()).containsKey(seriesTitle)) {
                			serie = (reseau.getListeSerie()).get(seriesTitle);//on pointe sur la série déja dans le réseau si elle existe
                		}
                		else {
                			reseau.addSerie(serie);
                		}
	                	//si le doc a un ean et pas d'isbn
		                if( (!ean.isEmpty()) && (isbn.isEmpty())) {
		                	//si le doc est un vinyle
	                		if(type.matches("Vinyle(.*)")) {
	                			document = new VinyleInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,serie,seriesNumber);	
	                		}
	                		//si le doc est un CD
	                		else if(type.matches("Disque compact(.*)")) {
	                			document = new CDInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,serie,seriesNumber);	
	                		}
	                		//si le doc est un Jeu de societe
	                		else if(type.matches("Jeux de societe(.*)")) {
	                			document = new JeuDeSocieteInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,serie,seriesNumber);	
	                		}
	                		//si le doc est un Jeu video
	                		else if(type.matches("Jeux Videos(.*)")) {
	                			document = new JeuVideoInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,serie,seriesNumber);	
	                		}
	                		//sinon on le met dans autre
	                		else {
	                			document = new AutreInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,serie,seriesNumber);	
	                		}
		                }
		                //si le doc a un ISBN
		                else{
		                	//si le doc est une partition
	                		if(type.matches("Partition(.*)")) {
	                			document = new PartitionInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn,serie,seriesNumber);	
	                		}
	                		//si le doc est une carte
	                		else if(type.matches("Carte(.*)")) {
	                			document = new CarteInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn,serie,seriesNumber);	
	                		}
	                		//si le doc est une revue
	                		else if(type.matches("Revue(.*)")) {
	                			document = new RevueInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn,serie,seriesNumber);	
	                		}
	                		//si le doc est une Bande Dessinee
	                		else if(type.matches("Bande dessinee(.*)")) {
	                			document = new BandeDessineeInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn,serie,seriesNumber);	
	                		}
	                		//sinon c'est juste un livre
	                		else {
	                			document = new LivreInSerie(ean,title,publisher,date,authorName,authorSurname,type, totalCopies,isbn,serie,seriesNumber);	
	                		}
		                }
		                //Integer numeroSerie = new Integer(seriesNumber);
		                serie.addDocument(document,seriesNumber,date);
                	}
                }
                //on ajoute le document au reseau
                reseau.addDocument(document);
                //on ajoute le document au bibliotheque
                if(numberCopyAimeCesaire > 0) {
                	(reseau.getListeBiblio()).get("AimeCesaire").addDocument(document, numberCopyAimeCesaire);
                }
                if(numberCopyEdmondRostand > 0) {
                	(reseau.getListeBiblio()).get("EdmondRostand").addDocument(document, numberCopyEdmondRostand);
                }
                if(numberCopyJeanPierreMelville > 0) {
                	(reseau.getListeBiblio()).get("JeanPierreMelville").addDocument(document, numberCopyJeanPierreMelville);
                }
                if(numberCopyOscarWilde > 0) {
                	(reseau.getListeBiblio()).get("OscarWilde").addDocument(document, numberCopyOscarWilde);
                }
                if(numberCopySaintSimon > 0) {
                	(reseau.getListeBiblio()).get("SaintSimon").addDocument(document, numberCopySaintSimon);
                }
                 
            }
            
        } 
        catch (IOException exception) 
        {
            System.err.println(exception);
        }
	}
}
