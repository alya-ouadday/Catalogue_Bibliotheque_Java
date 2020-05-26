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
		HashMap<Document, Integer> serie = null;
		if(listeSerie.containsKey(serieName)) {
			serie = listeSerie.get(serieName).getListeDoc();
			if(!serie.containsValue(0)) {//si les documents ont tous un numero ont les affiche par numero
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .forEach(entry -> System.out.println(entry.getKey()));
			}else {//sinon on les trie par date
				serie = listeSerie.get(serieName).getListeDocDate();
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .forEach(entry -> System.out.println(entry.getKey()));
			}		
		}
		else {
			System.out.println("Cette série n'est pas dans le réseau !");
		}
		return serie; 
	}

	@Override
	public Livre searchISBN(String isbn) throws formatISBNException{
		isbn = isbn.replace("-", "");
		if ((isbn.matches("\\d{13}") || isbn.matches("\\d{10}") || isbn.matches("\\d{9}"+"X"))!= true){
			throw new formatISBNException(); 
		}
	
		if (listeLivre.get(isbn) ==null) {
			System.out.println("Cet ISBN ne correspond à aucun livre du réseau");
		}
		else {
			System.out.println(listeLivre.get(isbn));
		}
		return listeLivre.get(isbn);  
	}

	@Override
	public Document searchEAN(String ean) throws formatEANException{
		if (!ean.matches("\\d{13}")){
			throw new formatEANException(); 
		}
		
		if (listeLivre.get(ean) ==null) {
			System.out.println("Cet EAN ne correspond à aucun document du réseau");
		}
		else {
			System.out.println(listeLivre.get(ean));
		}

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
			System.out.println("Cet auteur n'a écrit aucun document du réseau"); 
		}
		return listeAuthor.get(authorName + " "+ authorSurname); 
	}
	


	
	@Override
	public void searchDocumentsAuthorName(String authorName) {
		
		  listeAuthor
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches(authorName + "(.*)"))
	      .forEach(entry -> {
	      					System.out.println("\n"+entry.getKey());
	      					for(Document document: listeAuthor.get(entry.getKey())) {
	      						System.out.println(document); 
	      					}
	    		  
	      					});      
	}
	
	@Override
	public void searchDocumentsAuthorSurname(String authorSurname) {
		  listeAuthor
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches("(.*)"+authorSurname))
	      .forEach(entry -> {
	      					System.out.println("\n"+entry.getKey());
	      					for(Document document: listeAuthor.get(entry.getKey())) {
	      						System.out.println(document); 
	      					}
	    		  
	      					});      
	}
	
	


	@Override
	public int searchNumberPeriod(int beginDate, int endDate) {

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
