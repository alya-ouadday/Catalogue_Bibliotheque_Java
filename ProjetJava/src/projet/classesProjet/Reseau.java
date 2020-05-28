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
 * 
 * represente le reseau
 * @author BEN OUADDAY et LEJEUNE
 * 
 */
public class Reseau implements Consultable {
	/**
	 * represente les bibliotheque du reseau
	 * Hashmap qui associe le nom des bibliotheque du réseau aux instances de ces bibliotheque
	 */
	private HashMap<String, Bibliotheque> listeBiblio;
	/**
	 * représente les document du reseau
	 * Hashmap qui associe les EAN à leur document
	 */
	private HashMap<String, Document> listeDocument;
	/**
	 * représente les livres du reseau
	 * Hashmap qui associe les ISBN à leur livre
	 */
	private HashMap<String, Livre> listeLivre;
	/**
	 * représente les document ecrit par un auteur
	 * Hashmap qui associe le nom d'un auteur à une liste des documents qu'il a realise
	 */
	private HashMap<String, ArrayList<Document>> listeAuthor;
	/**
	 * représente les serie du reseau
	 * Hashmap qui associe les noms des serie à leur instance de serie
	 */
	private HashMap<String, Serie> listeSerie;
	/**
	 * représente la liste des utilisateur du reseau
	 * Hashmap qui associe les id des utilisateur à leur instance d'utilisateur
	 */
	private HashMap<Integer,Utilisateur> listeUtilisateur;
	
	/**
	 * Constructeur
	 * @param listeBiblio
	 * @param listeDocument
	 * @param listeLivre
	 * @param listeAuthor
	 * @param listeSerie
	 * @param listeUtilisateur
	 */
	public Reseau(HashMap<String, Bibliotheque> listeBiblio, HashMap<String, Document> listeDocument, 
				   HashMap<String, Livre> listeLivre, HashMap<String, ArrayList<Document>> listeAuthor, HashMap<String, Serie> listeSerie,HashMap<Integer,Utilisateur> listeUtilisateur) {
		this.listeBiblio = listeBiblio;
		this.listeDocument = listeDocument;
		this.listeLivre = listeLivre;
		this.listeAuthor = listeAuthor;
		this.listeSerie = listeSerie;	
		this.listeUtilisateur = listeUtilisateur;
	}
	/**
	 * Constructeur simplifie
	 */
	public Reseau() {
		
		this(new HashMap<String, Bibliotheque>(), new HashMap<String,Document>(), new HashMap<String, Livre>(),
				new HashMap<String,ArrayList<Document>>(), new HashMap<String, Serie>(),new HashMap<Integer,Utilisateur>()) ;
	}
	
	/**
	 * retourne la hashmap des serie du reseau
	 * @return listeSerie
	 */
	public  HashMap<String, Serie>  getListeSerie(){
		return listeSerie; 
	}
	/**
	 * retourne la hashmap de la liste des bibliotheque du reseau
	 * @return listeBiblio
	 */
	public  HashMap<String, Bibliotheque> getListeBiblio(){
		return listeBiblio; 
	}
	/**
	 * retourne la Hashmap qui associe les EAN à leur document
	 * @return listeDocument
	 */
	public  HashMap<String, Document> getListeDocument(){
		return listeDocument; 
	}
	/**
	 * retourne la Hashmap qui associe les ISBN à leur livre
	 * @return listeLivre
	 */
	public HashMap<String, Livre> getListeLivre(){
		return listeLivre; 
	}
	/**
	 * retourne la Hashmap qui associe le nom d'un auteur à une liste des documents qu'il a realise
	 * @return listeAuthor
	 */
	public HashMap<String, ArrayList<Document>> getListeAuthor(){
		return listeAuthor; 
	}
	/**
	 * retourne la Hashmap qui associe les id des utilisateur à leur instance d'utilisateur
	 * @return listeUtilisateur
	 */
	public HashMap<Integer,Utilisateur> getListeUtilisateur() {
		return listeUtilisateur; 
	}
	/**
	 * affiche tous les utilisateur du reseau
	 */
	public void afficherUtilisateur() {
		System.out.println(listeUtilisateur);
	}
	
	/**
	 * ajoute une bibliotheque au reseau
	 * @param bibliotheque
	 */
	public void addBiblio(Bibliotheque bibliotheque) {
		listeBiblio.put(bibliotheque.getName(), bibliotheque); 
	}
	/**
	 * ajoute un document au reseau 
	 * @param document
	 */
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
			else if (listeAuthor.containsKey(document.getAuthor())!= true) { //
				ArrayList<Document> value = new ArrayList<Document>(); 
				value.add(document); 
				listeAuthor.put(document.getAuthor(), value); 
			}
			if(document instanceof Livre) {//si c'est un livre alors il a un ISBN et on l'ajoute à la liste des livre
				Livre livre = (Livre)document;
				listeLivre.put(livre.getISBN(), livre); 
			}
			
		}
	}
	/**
	 * test pour savoir si on peut ajouter un nouveau document au reseau
	 * retourne faux si le document est deja dans le reseau ou si il ne respecte pas ù
	 * certaine conditions et retourne vrai si on peut l'ajouter
	 * @param document
	 * @return boolean
	 * @throws formatISBNException
	 * @throws formatEANException
	 */
	public boolean addNewDocument(Document document) throws formatISBNException, formatEANException{
		if(listeDocument.containsKey(document.getEAN())) {
			System.out.println("EAN déjà attribué");
			return false; 
		}else if(!(document.getEAN().isEmpty()) && !(document.getEAN().matches("\\d{13}"))) {
			throw(new formatEANException ());
		}
		else if(document instanceof Livre) {
			Livre livre = (Livre)document; 
			String isbnVerif = livre.getISBN().replaceAll("-", ""); 
			if(listeLivre.containsKey(livre.getISBN())) {
				System.out.println("ISBN déjà attribué");
				return false; 
			}
			else if((isbnVerif.matches("\\d{13}") || isbnVerif.matches("\\d{10}") || isbnVerif.matches("\\d{9}"+"X"))!= true) {
				throw(new formatISBNException()); 
			}
			
		}
		return true; 
		
	}
	

	@Override
	public void ShowAllDocuments() {
		for(Document document: listeDocument.values()) {//on affiche tous les documents
			System.out.println(document.toString());
		}
		
		for(Livre livre : listeLivre.values()) {//on affiche tout les livres
			if(!listeDocument.containsKey(livre.getEAN())) {
				System.out.println(livre.toString()); 
			}
		}
		
	}

	@Override
	public HashMap<Document, Integer> searchSerie(String serieName) {
		serieName = serieName.toLowerCase(); 
		HashMap<Document, Integer> serie = null;
		if(listeSerie.containsKey(serieName)) {//si la serie est bien dans notre reseau
			serie = listeSerie.get(serieName).getListeDoc();
			if(!serie.containsValue(0)) {//si les documents ont tous un numero ont les affiche par numero
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())//on trie la hashmap par sa valeur donc par les numéro des documents
				  .forEach(entry -> System.out.println(entry.getKey()));//on affiche les document de la serie dans l'ordre de leur numero
			}else {//sinon on les trie par date
				serie = listeSerie.get(serieName).getListeDocDate();
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())//on trie la hashmap par sa valeur donc par les dates des documents
				  .forEach(entry -> System.out.println(entry.getKey()));//on affiche les document de la serie dans l'ordre de publication
			}		
		}
		else {
			System.out.println("Cette série n'est pas dans le réseau !");
		}
		return serie; 
	}

	@Override
	public Livre searchISBN(String isbn) throws formatISBNException{
		String isbnVerif = isbn.replace("-", "");//on enleve les tiret et espace pour verifier ensuite que le ISBN a le bon format
		if ((isbnVerif.matches("\\d{13}") || isbnVerif.matches("\\d{10}") || isbnVerif.matches("\\d{9}"+"X"))!= true){
			throw new formatISBNException(); 
		}
	
		if (!listeLivre.containsKey(isbn)) {//si l'ISBN ne correspond a aucun livre du reseau
			System.out.println("Cet ISBN ne correspond à aucun livre du réseau");
		}
		else {//sinon on affiche le bon livre
			System.out.println(listeLivre.get(isbn));
		}
		return listeLivre.get(isbn);  
	}

	@Override
	public Document searchEAN(String ean) throws formatEANException{
		if (!ean.matches("\\d{13}")){//on verifie que l'EAN a le bon format
			throw new formatEANException(); 
		}
		
		if (!listeDocument.containsKey(ean)) {//si l'EAN ne correspond a aucun document du reseau
			System.out.println("Cet EAN ne correspond à aucun document du réseau");
		}
		else {//sinon on affiche le bon document
			System.out.println(listeDocument.get(ean));
		}

		return listeDocument.get(ean); 
	}
	
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorNameSurname) {
		authorNameSurname = authorNameSurname.toLowerCase(); 
		if(listeAuthor.containsKey(authorNameSurname)) {//si le nom et prenom correspond bien a un auteur du reseau 
			System.out.println(authorNameSurname + " : ");
			for(Document document :listeAuthor.get(authorNameSurname) ) {//alors on affiche sa liste de livre
				System.out.println(document.toString()); 
			}
		}
		else {
			System.out.println("Cet auteur n'a écrit aucun document du réseau"); 
		}
		return listeAuthor.get(authorNameSurname); 
	}
	
	
	@Override
	public void searchDocumentsAuthorName(String authorName) {
		
		  listeAuthor
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches(authorName.toLowerCase() + "(.*)")) //on filtre la hashmap pour ne garder que 
	      																				//les auteurs avec un nom qui correspond
	      .forEach(entry -> {
	      					searchDocumentsAuthor(entry.getKey());//on affiche les liste de documents pour chacun des auteur ayant le nom rechercher
	      					
	    		  
	      					});      
	}
	
	@Override
	public void searchDocumentsAuthorSurname(String authorSurname) {
		  listeAuthor
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches("(.*)"+authorSurname.toLowerCase()))//on filtre la hashmap pour ne garder que 
																						//les auteurs avec un prenom qui correspond
	      .forEach(entry -> {
	    	  				searchDocumentsAuthor(entry.getKey());//on affiche les liste de documents pour chacun des auteur ayant le prenom rechercher
	    		  
	      					});      
	}
	
	

	@Override
	public int searchNumberPeriod(int beginDate, int endDate) {

		int compteur = 0; //compte le nombre de doc
		for(Document document: listeDocument.values()) {	//pour chaque document du reseau
				int date = document.getDate(); 	
				if(date >= beginDate && date <= endDate) {	//si la date est comprise dans l'intervalle
					compteur ++; //on incremente le compteur
				}
		}
		for(Livre livre: listeLivre.values()) {	//pour tout les livre
				if(!(listeDocument.containsKey(livre.getEAN()))) {	//si on a pas deja compte ce livre dans les document
					int date = livre.getDate(); 
					if(date >= beginDate && date <= endDate) {//si la date est comprise dans l'intervalle
						compteur ++; //on incremente le compteur
					}
				}
		}
		System.out.println("Nombre de document entre "+beginDate+" et "+endDate+" : "+compteur);
		return compteur; 
	
	}
	
	/**
	 * ajoute une série au reseau
	 * @param serie
	 */
	public void addSerie(Serie serie) {
		listeSerie.put(serie.getName(), serie); 
	}
	

	
}
