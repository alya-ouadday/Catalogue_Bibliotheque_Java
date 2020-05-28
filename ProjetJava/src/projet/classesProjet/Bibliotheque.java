/**
 * 
 */
package projet.classesProjet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import projet.exceptions.formatEANException;
import projet.exceptions.formatISBNException;
import projet.exceptions.nonDispoException;

/**
 * 
 * represente une bibliotheque
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Bibliotheque implements Consultable{
	/**
	 * nom de la bibliotheque
	 */
	private String name;
	/**
	 * reseau auquel la bibliothque appartient
	 */
	private Reseau reseau;
	/**
	 * represente les document disponibles dans la bibliothèque
	 * HashMap qui associe l'EAN d'un document au nombre de copies restantes de ce document dans la bibliotheque
	 */
	private HashMap<String,Integer> listeCopieDoc;
	/**
	 * represente les livres disponibles dans la bibliothèque
	 * HashMap qui associe l'ISBN d'un livre au nombre de copies restantes de ce livre dans la bibliotheque
	 */
	private HashMap<String,Integer> listeCopieLivre;
	/**
	 * represente la liste des utilisateurs inscrits dans cette biblliotheque
	 * HashMap qui associe l'id d'un utilisateur à l'instance de cet utilisateur
	 */
	private HashMap<Integer,Utilisateur> listeUtilisateur;
	
	/**
	 * Constructeur
	 * @param name
	 * @param reseau
	 * @param listeCopieDoc
	 * @param listeCopieLivre
	 * @param listeUtilisateur
	 */
	public Bibliotheque(String name,Reseau reseau, HashMap<String,Integer> listeCopieDoc,HashMap<String,Integer> listeCopieLivre,HashMap<Integer,Utilisateur> listeUtilisateur) {
		this.name = name.toLowerCase();
		this.reseau = reseau;
		this.listeCopieDoc = listeCopieDoc;
		this.listeCopieLivre = listeCopieLivre; 
		this.listeUtilisateur = listeUtilisateur;
	}
	/**
	 * Constructeur simplifie
	 * @param nom
	 * @param reseau
	 */
	public Bibliotheque(String nom,Reseau reseau) {
		this(nom.toLowerCase(), reseau, new HashMap<String,Integer>(), new HashMap<String,Integer>(),new HashMap<Integer,Utilisateur>());
	}
	/**
	 * retourne le nom de la bibliotheque
	 * @return name
	 */
	public String getName() {
		return name; 
	}
	/**
	 * retourne le reseau auquel la bibliothque appartient
	 * @return reseau
	 */
	public Reseau getReseau() {
		return reseau; 
	}
	/**
	 * retourne les documents disponibles dans la bibliothèque
	 * c'est a dire la HashMap qui associe l'EAN d'un document au nombre de copies restantes de ce document dans la bibliotheque
	 * @return listeCopieDoc
	 */
	public HashMap<String,Integer> getListeCopieDoc() {
		return listeCopieDoc; 
	}
	/**
	 * retourne les livres disponibles dans la bibliothèque
	 * c'est a dire la HashMap qui associe l'ISBN d'un livre au nombre de copies restantes de ce livre dans la bibliotheque
	 * @return listeCopieLivre
	 */
	public HashMap<String,Integer> getListeCopieLivre() {
		return listeCopieLivre; 
	}
	/**
	 * la liste des utilisateurs inscrits dans cette biblliotheque
	 * c'est à dire la HashMap qui associe l'id d'un utilisateur à l'instance de cette utilisateur
	 * @return listeUtilisateur
	 */
	public HashMap<Integer,Utilisateur> getListeUtilisateur() {
		return listeUtilisateur; 
	}
	

	/**
	 * ajoute un document à une bibliotheque
	 * en precisant le nombre de copies qu'on ajoute a cette bibliotheque
	 * @param document
	 * @param nBCopie
	 */
	public void addDocument(Document document, Integer nBCopie) {
		if(document != null) {
			if(!document.getEAN().isEmpty()) { //si le document a bien un EAN 
				listeCopieDoc.put(document.getEAN(), nBCopie); // on l'ajoute a la liste de documents du reseau
			}
			if(document instanceof Livre) {
				Livre livre = (Livre)document;
				listeCopieLivre.put(livre.getISBN(), nBCopie); // pareil si c'est un livre 
			}
		}
	}
	
	
	@Override
	public void ShowAllDocuments() {
		for(Document document: reseau.getListeDocument().values()) {// on itere sur tous les documents du reseau 
			if(listeCopieDoc.containsKey(document.getEAN())) {
				
				System.out.println(document.toString() + "  Nombre de copie disponible dans cette bibliothèque :" + listeCopieDoc.get(document.getEAN()));
				if(listeCopieDoc.get(document.getEAN()).equals(0)) {// si le nombre de copie est nul, on notifie l'utilisateur 
					System.out.println("Attention, ce document n'est plus disponible dans cette bibliothèque !");
				} 
			}
		}
		for(Livre livre: reseau.getListeLivre().values()) {	
				if(listeCopieLivre.containsKey(livre.getISBN()) && !(listeCopieDoc.containsKey(livre.getEAN()))) {	// si c'est un livre sans ean qui n'a pas ete deja affiche au dessus
					System.out.println(livre.toString() + "  Nombre de copie disponible dans cette bibliothèque :" + listeCopieLivre.get(livre.getISBN()));
					if(listeCopieLivre.get(livre.getISBN()).equals(0)) {
						System.out.println("Attention, ce livre n'est plus disponible dans cette bibliothèque !");
					}
				}
		}
	}
	
	@Override
	public HashMap<Document, Integer> searchSerie(String serieName) {
		serieName = serieName.toLowerCase(); 
		HashMap<Document, Integer> serie = null;
		if(reseau.getListeSerie().containsKey(serieName)) {// si cette serie existe dans le reseau 
			serie = reseau.getListeSerie().get(serieName).getListeDoc();//on recupere la liste des documents de cette serie 
			if(!serie.containsValue(0)) {//si les documents ont tous un numero dans la serie  
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())// on les trie par leur numero dans la serie 
				  .filter(entry -> { // on filtre les documents disponibles dans la bibliotheque 
									  if(entry.getKey() instanceof Livre) {// si le document est un livre 
										  Livre livre = (Livre)entry.getKey();
										  if(!listeCopieLivre.containsKey(livre.getISBN())) {//si les tomes ne sont pas dans cette bibliotheque 
											  System.out.println("Le tome " + entry.getValue() + " de la serie n'est pas dans cette bibliothèque");
											  return false; 
										  }
										  else {
											  if(listeCopieLivre.get(livre.getISBN()).equals(0)) {// s'il n'est plus disponible (car emprunté)
												  System.out.println("Atention le tome " + entry.getValue() + " de la serie n'est plus dans cette bibliothèque");
											  return false; }
										  return true;// il passe le filtre 
										  }
										  
									  }else { // si c'est un document sans ISBN on fait pareil 
										  if(!listeCopieDoc.containsKey(entry.getKey().getEAN())) { 
											  System.out.println("Le numéro " + entry.getValue() + " n'est pas dans cette bibliothèque");
											  return false; 
										  }
										  else {
											  if(listeCopieDoc.get(entry.getKey().getEAN()).equals(0)) {
												  System.out.println("Atention le numéro " + entry.getValue() + " de la serie n'est plus dans cette bibliothèque");
												  return false; }
											  return true;
										  }
									  }
								  	} )
				  .forEach(entry -> System.out.println(entry.getKey()));// on affiche chacun des documents qui passent le filtre 
			}else { // si les doucments n'ont pas de numero de serie 
				serie = reseau.getListeSerie().get(serieName).getListeDocDate();
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue()) // on les compare par date de publication 
				  .filter(entry -> { //meme logique de filtrage qu'au dessus 
									  if(entry.getKey() instanceof Livre) {
										  Livre livre = (Livre)entry.getKey();
										  if(!listeCopieLivre.containsKey(livre.getISBN())) {
											  System.out.println("Le livre : " + livre.getTitle()+"  de la serie n'est pas dans cette bibliothèque");
											  return false; 
										  }
										  else {
											  if(listeCopieLivre.get(livre.getISBN()).equals(0)) {
												  System.out.println("Atention le tome " + livre.getTitle() + " de la serie n'est plus dans cette bibliothèque");
											  return false; }
											  return true;
										  }
									  }else
										  if(!listeCopieDoc.containsKey(entry.getKey().getEAN())) { 
											  System.out.println("Le document "+ entry.getKey().getTitle() +" n'est pas dans cette bibliothèque");
											  return false;  
										  }
										  else {
											  if(listeCopieDoc.get(entry.getKey().getEAN()).equals(0)) {
												  System.out.println("Atention le document " + entry.getKey().getTitle() + " de la serie n'est plus dans cette bibliothèque");
											  return false; }
											  return true;
										  }
								  	} )
				  .forEach(entry -> System.out.println(entry.getKey()));
			}		
		}
		else {
			System.out.println("Cette série n'est pas dans le réseau"); 
		}
		return serie; 
	}
	@Override
	public Livre searchISBN(String isbn) throws formatISBNException{
		String isbnVerif = isbn.replace("-", "");// on recupere les chiffres sans tirets 
		if ((isbnVerif.matches("\\d{13}") || isbnVerif.matches("\\d{10}") || isbnVerif.matches("\\d{9}"+"X"))!= true){
			throw new formatISBNException(); // si l'isbn ne passe pas les tests de formats (13 chiffres, 10 chiffres ou 9 chiffres et X)
		}
		Livre livre = null; 
		if(listeCopieLivre.containsKey(isbn)) {// le document est dans la liste de la bibliotheque 
			livre = reseau.getListeLivre().get(isbn); 
			System.out.println(livre+ " nombre de copie dans la bilbiothèque: " + listeCopieLivre.get(isbn)); 
			if(listeCopieLivre.get(isbn).equals(0)) { // s'il n'est plus disponible, on notifie l'utilisateur 
				System.out.println("Ce livre n'est plus disponible dans cette bibliothèque !");
			}
		}
		else {// il n'est pas dans la bibliotheque 
			if(reseau.getListeLivre().containsKey(isbn)) {//mais il est dans le reseau 
				System.out.println("Ce livre n'est pas dans cette bibliothèque. Consultez le réseau");
			}
			else {// il n'est nul part 
				System.out.println("Ce livre n'est pas dans le réseau");
			}
		}
		return livre;
	}
	@Override
	public Document searchEAN(String ean) throws formatEANException{
		if (!ean.matches("\\d{13}")){ // si ce n'est pas 13 chiffres entres en parametres 
			throw new formatEANException(); 
		}
		Document document = null; 
		if(listeCopieDoc.containsKey(ean)) { //meme logique que pour l'isbn
			document = reseau.getListeDocument().get(ean); 
			System.out.println(document+ " nombre de copies dans la bilbiothèque:  " + listeCopieDoc.get(ean)); 
			if(listeCopieDoc.get(ean).equals(0)) {
				System.out.println("Ce document n'est plus disponible dans cette bibliothèque !");
			}
		}
		else {
			if(reseau.getListeDocument().containsKey(ean)) {
				System.out.println("Ce document n'est pas dans cette bibliothèque. Consultez le réseau");
			}
			else {
				System.out.println("Ce document n'est pas dans le réseau");
			}
		}
		return document;
	}
	
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorNameSurname) {
		
		authorNameSurname = authorNameSurname.toLowerCase(); //on recupere le nom et prenom de l'auteur en minuscule
		ArrayList<Document> listeDoc = new ArrayList<Document>(); 
		if(reseau.getListeAuthor().containsKey(authorNameSurname)){// si l'auteur est bien dans le reseau 
			System.out.println(authorNameSurname + " : "); 
			for(Document document :reseau.getListeAuthor().get(authorNameSurname) ) {// pour tous les documents ecrits pas l'auteur dans le reseau 
				if(listeCopieDoc.containsKey(document.getEAN())) { // si le document est bien dans la bibliotheque 
					listeDoc.add(document); // on l'ajoute à sa liste de document et on l'affiche 
					System.out.println(document.toString()+ " nombre de copies dans la bilbiothèque:  " + listeCopieDoc.get(document.getEAN())); 
					if(listeCopieDoc.get(document.getEAN()).equals(0)) {// s'il n'est plus dispo , on notifie 
						System.out.println("Ce document n'est plus disponible dans cette bibliothèque !");
					} 
				}
				else if(document instanceof Livre) { // pareil si c'est un livre 
					Livre livre = (Livre)document; 
					if(listeCopieLivre.containsKey(livre.getISBN()) && !listeCopieDoc.containsKey(livre.getEAN())) {
						// on verifie qu'il n'a pas deja ete traite au dessus 
						listeDoc.add(livre); 
						System.out.println(livre.toString()+ " nombre de copies dans la bilbiothèque:  " + listeCopieDoc.get(livre.getISBN())); 
						if(listeCopieLivre.get(livre.getISBN()).equals(0)) {
							System.out.println("Ce livre n'est plus disponible dans cette bibliothèque !");
						} 
					}
					else {
						System.out.println("Son livre à l'ISBN " + livre.getISBN() + " n'est pas dans la bibliothèque. Consulter le réseau");
					}
				}
				else {
					System.out.println("Son document à l'EAN "+ document.getEAN()+ " n'est pas dans la bibliothèque. Consulter le réseau");
				}
			}
		}
		else {
			System.out.println("Cet auteur n'a écrit aucun document du réseau "); 
		}
		return listeDoc; 
	}
	@Override
	public void searchDocumentsAuthorName(String authorName) {
		 
		  reseau.getListeAuthor()
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches(authorName.toLowerCase() + "(.*)")) // si la cle nom prenom correspond au nom entre en param (avec n'importe quelle suite et prenom)
	      .forEach(entry -> {
	    	  				searchDocumentsAuthor(entry.getKey()); // on fait appel a la fonction searchDocumentAuthor avec la cle qui a passe le filtre 
	      					});      
	}
	
	@Override
	public void searchDocumentsAuthorSurname(String authorSurname) {
		  reseau.getListeAuthor()
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches("(.*)"+authorSurname.toLowerCase())) // si le prenom de la cle correspond au prenom entre en param (avec n'importe quel nom)
	      .forEach(entry -> {
	    	  	searchDocumentsAuthor(entry.getKey()); 
	      					});      
	}
	
	
	

	@Override
	public int searchNumberPeriod(int beginDate, int endDate) {
		int compteur = 0; 
		for(Document document: reseau.getListeDocument().values()) { // on itere sur tous les documents du reseau 
			if(listeCopieDoc.containsKey(document.getEAN())) {// on verifie pour chacun qu'il est dans la bibliotheque 
				int date = document.getDate(); 
				if(!listeCopieDoc.get(document.getEAN()).equals(0)) { // s'il n'est pas dispo on ne le compte pas 
					if(date >= beginDate && date <= endDate) {
						compteur ++; // sinon c'est un doc de plus publie entre les bonnes dates 
					}
				}
			
			}
		}
		for(Livre livre: reseau.getListeLivre().values()) {	// si c'est un livre qui n'a pas ete traite au dessus (pas d'EAN)
				if(listeCopieLivre.containsKey(livre.getISBN()) && !(listeCopieDoc.containsKey(livre.getEAN()))) {	
					
					int date = livre.getDate(); 
					if(!listeCopieLivre.get(livre.getISBN()).equals(0)) {
						if(date >= beginDate && date <= endDate) {
							compteur ++; 
						}
					}
				}
		}
		System.out.println("Nombre de document entre "+beginDate+" et "+endDate+" : "+compteur);
		return compteur; 
	
	}
	
	/**
	 * ajoute un nouvel utilisateur à la liste des utilisateur inscrit dans la bibliotheque
	 * @param utilisateur
	 */
	public void addUtilisateur(Utilisateur utilisateur) {
		listeUtilisateur.put(utilisateur.getId(), utilisateur);
	}
	

	/**
	 * Emprunte un document dans une bibliotheque 
	 * et incremente le nombre de copie du document dans cette bibliotheque
	 * et l'ajoute s'il n'y etait pas
	 * @param bibliotheque
	 * @param document
	 * @throws nonDispoException
	 */
	public void emprunter(Bibliotheque bibliotheque, Document document) throws nonDispoException {
		
		Integer nbCopieDoc = 0;
		Integer nbCopieLivre = 0;
		Integer nbCopieDocHome = 0;
		Integer nbCopieLivreHome = 0;
		if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())){ // si la biblio de qui on emprunte a bien ce doc
			 nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN()); // on récupere son nombre de copie 
			 if(!listeCopieDoc.containsKey(document.getEAN())) { // si le document n'est pas dans notre biblio
				 listeCopieDoc.put(document.getEAN(), 0); //on l'ajoute à notre liste de documents (qui associe ean au nb de copie)
			 }
			 else {
				 nbCopieDocHome = listeCopieDoc.get(document.getEAN());// si on l'a deja, on recupere le nb de ses copies dans notre liste de documents
			 }
			 
		}
		if(document instanceof Livre) {
			Livre livre = (Livre)document;
			if(bibliotheque.getListeCopieLivre().containsKey(livre.getISBN())){// si la bibliotheque a ce livre
				 nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN()); // on recupere son nb de copie 
			
				 if(!listeCopieLivre.containsKey(livre.getISBN())) { // si on ne l'a pas 
					 listeCopieLivre.put(livre.getISBN(), 0);// on l'ajoute 
				 }
				 else {
					 nbCopieLivreHome = listeCopieLivre.get(livre.getISBN()); // sinon on recupere le nombre de copies 
				 }
			}
		}
		if((nbCopieDoc==0 && nbCopieLivre==0)   ) { // si le document n'est pas disponible (nbcopie = 0)
			throw new nonDispoException(); // on leve un excepetion 
		}
		else {
			if(nbCopieDoc!=0 ) { // si le document est disponible 
				nbCopieDoc--;	// on decremente le nb de copie dans la bibliotheque de qui on emprunte 
				nbCopieDocHome++; // on incremente le nb de copie dans notre bibliotheque 
				
				bibliotheque.getListeCopieDoc().replace(document.getEAN(), nbCopieDoc); // on remplace le bon nombre de copie dans 
				// les listes respectives des bibliotheques 
				listeCopieDoc.replace(document.getEAN(), nbCopieDocHome);
			}
			if(nbCopieLivre != 0) {
				Livre livre = (Livre)document; // pareil si c'est un livre 
				 
				nbCopieLivre--;
				nbCopieLivreHome++;
				bibliotheque.getListeCopieLivre().replace(livre.getISBN(), nbCopieLivre); 
				listeCopieLivre.replace(livre.getISBN(), nbCopieLivreHome);
			}	
		}

	}
	
	/**
	 * remet un document emprunté dans une bibliotheque 
	 * et décremente le nombre de copie du document dans cette bibliotheque
	 * @param bibliotheque ou l'on remet
	 * @param document
	 */
	public void remettre(Bibliotheque bibliotheque, Document document){
		try {
			bibliotheque.emprunter(this, document); // emprunt inverse entre les deux bibliotheques 
		} catch (nonDispoException e) {
			e.printStackTrace();
		}
	}
	
}
