/**
 * 
 */
package projet.classesProjet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import projet.exceptions.*;

/**
 * @author fande
 *
 */
public class Reseau implements Consultable {
	private HashMap<String, Bibliotheque> listeBiblio;
	private HashMap<String, Document> listeDocument;
	private HashMap<String, Livre> listeLivre;
	private HashMap<String, ArrayList<Document>> listeAuthor;
	private HashMap<String, Serie> listeSerie;
	
	public Reseau(HashMap<String, Bibliotheque> listeBiblio, HashMap<String, Document> listeDocument, 
				   HashMap<String, Livre> listeLivre, HashMap<String, ArrayList<Document>> listeAuthor, HashMap<String, Serie> listeSerie) {
		this.listeBiblio = listeBiblio;
		this.listeDocument = listeDocument;
		this.listeLivre = listeLivre;
		this.listeAuthor = listeAuthor;
		this.listeSerie = listeSerie;	
	}
	
	public Reseau() {
		
		this(new HashMap<String, Bibliotheque>(), new HashMap<String,Document>(), new HashMap<String, Livre>(),
				new HashMap<String,ArrayList<Document>>(), new HashMap<String, Serie>()) ;
		

	}
	
	
	public  HashMap<String, Serie>  getListeSerie(){
		return listeSerie; 
	}
	
	public  HashMap<String, Bibliotheque> getListeBiblio(){
		return listeBiblio; 
	}
	
	public  HashMap<String, Document> getListeDocument(){
		return listeDocument; 
	}
	public HashMap<String, Livre> getListeLivre(){
		return listeLivre; 
	}
	public HashMap<String, ArrayList<Document>> getListeAuthor(){
		return listeAuthor; 
	}
	
	
	public void addBiblio(Bibliotheque bibliotheque) {
		listeBiblio.put(bibliotheque.getName(), bibliotheque); 
		
	}
	public void addDocument(Document document) {
		if(document != null) {
			if(!document.getEAN().isEmpty()) { //si le document a bien un EAN (ça pourrait être un livre 
				//qui a seulement un ISBN 
				listeDocument.put(document.getEAN(), document); 
			}
			if (listeAuthor.containsKey(document.getAuthor())){ //si l'auteur du doc est deja 
				//dans la liste d'auteur
				listeAuthor.get(document.getAuthor()).add(document); 
			}
			else if (listeAuthor.containsKey(document.getAuthor())!= true) {
				ArrayList<Document> value = new ArrayList<Document>(); 
				value.add(document); 
				listeAuthor.put(document.getAuthor(), value); 
			}
			if(document instanceof Livre) {
				Livre livre = (Livre)document;
				listeLivre.put(livre.getISBN(), livre); 
			}
			
		}
	}
	

	@Override
	public void ShowAllDocuments() {
		for(Document document: listeDocument.values()) {
			System.out.println(document.toString());
		}
		
		for(Livre livre : listeLivre.values()) {
			if(!listeDocument.containsKey(livre.getEAN())) {
				System.out.println(livre.toString()); 
			}
		}
		
	}

	@Override
	public HashMap<Document, Integer> searchSerie(String serieName) {
		HashMap<Document, Integer> serie = listeSerie.get(serieName).getListeDoc();//On retrouve 
		//la série par son nom dans la liste des series, et on parcours sa liste de document pour print
		//tous les docs dedans 
		//il faut encore trier la hashmap avant
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .forEach(entry -> System.out.println(entry.getKey()));
				
		return serie; 
	}

	@Override
	public Livre searchISBN(String isbn) throws formatISBNException{
		isbn = isbn.replace("-", "");
		if ((isbn.matches("\\d{13}") || isbn.matches("\\d{10}") || isbn.matches("\\d{9}"+"X"))!= true){
			throw new formatISBNException(); 
		}
		System.out.println(listeLivre.get(isbn));
		return listeLivre.get(isbn);  
	}

	@Override
	public Document searchEAN(String ean) throws formatEANException{
		if (!ean.matches("\\d{13}")){
			throw new formatEANException(); 
		}
		System.out.println(listeDocument.get(ean));
		return listeDocument.get(ean); 
	}
	
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorName, String authorSurname) {
		if(listeAuthor.get(authorName + " "+ authorSurname) != null) {
		for(Document document :listeAuthor.get(authorName + " "+ authorSurname) ) {
			System.out.println(document.toString()); 
		}
		}
		else {
			System.out.println("Cet auteur n'a rien écrit"); 
		}
		return listeAuthor.get(authorName + " "+ authorSurname); 
	}
	


	
	@Override
	public void searchDocumentsAuthorName(String authorName) {
		/*for(Map.Entry<String, ArrayList<Document>> entry : listeAuthor.entrySet()) {
			if( entry.getKey().matches(authorName +"(.*)")) {
				System.out.println(entry.getKey()+ " : " ); 
				for (Document document: entry.getValue()) {
					System.out.println(document.toString()); 			
				}
			}	
		}*/
		
	
		Stream<String> nomAuteurs = listeAuthor
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches(authorName + "(.*)"))
	      .map(Map.Entry::getKey);;
	      
	      Set<String> names = nomAuteurs.collect(Collectors.toSet());
	      
	      for(String nom:names) {
	    	  
	    	  System.out.println(nom + "\n" + listeAuthor.get(nom));
	      }
	     
	      
	      
	}
	
	@Override
	public void searchDocumentsAuthorSurname(String authorSurname) {
		for(Map.Entry<String, ArrayList<Document>> entry : listeAuthor.entrySet()) {
			if( entry.getKey().matches("(.*)"+authorSurname)) {
				System.out.println(entry.getKey()+ " : ");
				for (Document document: entry.getValue()) {
					System.out.println(document.toString()); 
					
				}
			}
		} 			
	}


	@Override
	public int searchNumberPeriod(int beginDate, int endDate) {
		/*
		int compteur = 0; 
		for(Map.Entry<String, Document> entry : listeDocument.entrySet()) {
			String date = entry.getValue().getDate(); 
			int dateNum = 0; 
			if (date!= "?") {
				try {
					   dateNum = Integer.parseInt(date);
					}
					catch (NumberFormatException e)
					{
					   dateNum = 0;
					}
			}
			
			if(dateNum >= beginDate && dateNum <= endDate) {
				compteur ++; 
			}
			
			
		}
		System.out.println("Nombre de document entre "+beginDate+" et "+endDate+" : "+compteur);
		return compteur; 
		*/
		int compteur = 0; 
		for(Document document: listeDocument.values()) {	
				int date = document.getDate(); 	
				if(date >= beginDate && date <= endDate) {
					compteur ++; 
				}
		}
		for(Livre livre: listeLivre.values()) {	
				if(!(listeDocument.containsKey(livre.getEAN()))) {	
					int date = livre.getDate(); 
					if(date >= beginDate && date <= endDate) {
						compteur ++; 
					}
				}
		}
		System.out.println("Nombre de document entre "+beginDate+" et "+endDate+" : "+compteur);
		return compteur; 
	
	}
	
	public void addSerie(Serie serie) {
		listeSerie.put(serie.getName(), serie); 
	}
	

	
}
