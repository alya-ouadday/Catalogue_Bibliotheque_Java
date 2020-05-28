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
 * @author fande
 *
 */
public class Bibliotheque implements Consultable{
	private String name;
	private Reseau reseau;
	private HashMap<String,Integer> listeCopieDoc;
	private HashMap<String,Integer> listeCopieLivre;
	private HashMap<Integer,Utilisateur> listeUtilisateur;
	
	public Bibliotheque(String name,Reseau reseau, HashMap<String,Integer> listeCopieDoc,HashMap<String,Integer> listeCopieLivre,HashMap<Integer,Utilisateur> listeUtilisateur) {
		this.name = name.toLowerCase();
		this.reseau = reseau;
		this.listeCopieDoc = listeCopieDoc;
		this.listeCopieLivre = listeCopieLivre; 
		this.listeUtilisateur = listeUtilisateur;
	}
	public Bibliotheque(String nom,Reseau reseau) {
		this(nom.toLowerCase(), reseau, new HashMap<String,Integer>(), new HashMap<String,Integer>(),new HashMap<Integer,Utilisateur>());
	}
	
	public String getName() {
		return name; 
	}
	public Reseau getReseau() {
		return reseau; 
	}
	public HashMap<String,Integer> getListeCopieDoc() {
		return listeCopieDoc; 
	}
	public HashMap<String,Integer> getListeCopieLivre() {
		return listeCopieLivre; 
	}
	public HashMap<Integer,Utilisateur> getListeUtilisateur() {
		return listeUtilisateur; 
	}
	

	
	public void addDocument(Document document, Integer nBCopie) {
		if(document != null) {
			if(!document.getEAN().isEmpty()) { //si le document a bien un EAN (ça pourrait être un livre 
				//qui a seulement un ISBN 
				listeCopieDoc.put(document.getEAN(), nBCopie); 
			}
			if(document instanceof Livre) {
				Livre livre = (Livre)document;
				listeCopieLivre.put(livre.getISBN(), nBCopie); 
			}
		}
	}
	
	
	@Override
	public void ShowAllDocuments() {
		for(Document document: reseau.getListeDocument().values()) {
			if(listeCopieDoc.containsKey(document.getEAN())) {
				// test si value != 0 
				System.out.println(document.toString() + "  Nombre de copie disponible dans cette bibliothèque :" + listeCopieDoc.get(document.getEAN()));
				if(listeCopieDoc.get(document.getEAN()).equals(0)) {
					System.out.println("Attention, ce document n'est plus disponible dans cette bibliothèque !");
				} 
			}
		}
		for(Livre livre: reseau.getListeLivre().values()) {	
				if(listeCopieLivre.containsKey(livre.getISBN()) && !(listeCopieDoc.containsKey(livre.getEAN()))) {	
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
		if(reseau.getListeSerie().containsKey(serieName)) {
			serie = reseau.getListeSerie().get(serieName).getListeDoc();
			if(!serie.containsValue(0)) {//si les documents ont tous un numero ont les affiche par numero
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .filter(entry -> {
									  if(entry.getKey() instanceof Livre) {
										  Livre livre = (Livre)entry.getKey();
										  if(!listeCopieLivre.containsKey(livre.getISBN())) {//si les tomes ne sont pas dan sla bu 
											  System.out.println("Le tome " + entry.getValue() + "de la serie n'est pas dans cette bibliothèque");
											  return false; 
										  }
										  else {
											  if(listeCopieLivre.get(livre.getISBN()).equals(0)) {
												  System.out.println("Atention le tome " + entry.getValue() + "de la serie n'est plus dans cette bibliothèque");
											  return false; }
											  return true;
										  }
										  
									  }else {
										  if(!listeCopieDoc.containsKey(entry.getKey().getEAN())) { 
											  System.out.println("Le numéro " + entry.getValue() + " n'est pas dans cette bibliothèque");
											  return false; 
										  }
										  else {
											  if(listeCopieDoc.get(entry.getKey().getEAN()).equals(0)) {
												  System.out.println("Atention le numéro " + entry.getValue() + "de la serie n'est plus dans cette bibliothèque");
												  return false; }
											  return true;
										  }
									  }
								  	} )
				  .forEach(entry -> System.out.println(entry.getKey()));
			}else {
				serie = reseau.getListeSerie().get(serieName).getListeDocDate();
				serie.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .filter(entry -> {
									  if(entry.getKey() instanceof Livre) {
										  Livre livre = (Livre)entry.getKey();
										  if(!listeCopieLivre.containsKey(livre.getISBN())) {//si les tomes ne sont pas dan sla bu 
											  System.out.println("Le livre : " + livre.getTitle()+"  de la serie n'est pas dans cette bibliothèque");
											  return false; 
										  }
										  else {
											  if(listeCopieLivre.get(livre.getISBN()).equals(0)) {
												  System.out.println("Atention le tome " + livre.getTitle() + "de la serie n'est plus dans cette bibliothèque");
											  return false; }
											  return true;
										  }
									  }else
										  if(!listeCopieDoc.containsKey(entry.getKey().getEAN())) { 
											  System.out.println("Le document "+ entry.getKey().getTitle() +"n'est pas dans cette bibliothèque");
											  return false; 
										  }
										  else {
											  if(listeCopieDoc.get(entry.getKey().getEAN()).equals(0)) {
												  System.out.println("Atention le document " + entry.getKey().getTitle() + "de la serie n'est plus dans cette bibliothèque");
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
		String isbnVerif = isbn.replace("-", "");
		if ((isbnVerif.matches("\\d{13}") || isbnVerif.matches("\\d{10}") || isbnVerif.matches("\\d{9}"+"X"))!= true){
			throw new formatISBNException(); 
		}
		Livre livre = null; 
		if(listeCopieLivre.containsKey(isbn)) {
			livre = reseau.getListeLivre().get(isbn); 
			System.out.println(livre+ " nombre de copie dans la bilbiothèque: " + listeCopieLivre.get(isbn)); 
			if(listeCopieLivre.get(isbn).equals(0)) {
				System.out.println("Ce livre n'est plus disponible dans cette bibliothèque !");
			}
		}
		else {
			if(reseau.getListeLivre().containsKey(isbn)) {
				System.out.println("Ce livre n'est pas dans cette bibliothèque. Consultez le réseau");
			}
			else {
				System.out.println("Ce livre n'est pas dans le réseau");
			}
		}
		return livre;
	}
	@Override
	public Document searchEAN(String ean) throws formatEANException{
		if (!ean.matches("\\d{13}")){
			throw new formatEANException(); 
		}
		Document document = null; 
		if(listeCopieDoc.containsKey(ean)) {
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
		
		authorNameSurname = authorNameSurname.toLowerCase(); 
		ArrayList<Document> listeDoc = new ArrayList<Document>(); 
		if(reseau.getListeAuthor().containsKey(authorNameSurname)){
			System.out.println(authorNameSurname + " : "); 
			for(Document document :reseau.getListeAuthor().get(authorNameSurname) ) {
				if(listeCopieDoc.containsKey(document.getEAN())) {
					listeDoc.add(document);
					System.out.println(document.toString()+ " nombre de copies dans la bilbiothèque:  " + listeCopieDoc.get(document.getEAN())); 
					if(listeCopieDoc.get(document.getEAN()).equals(0)) {
						System.out.println("Ce document n'est plus disponible dans cette bibliothèque !");
					} 
				}
				else if(document instanceof Livre) {
					Livre livre = (Livre)document; 
					if(listeCopieLivre.containsKey(livre.getISBN()) && !listeCopieDoc.containsKey(livre.getEAN())) {
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
	      .filter(entry -> entry.getKey().matches(authorName.toLowerCase() + "(.*)"))
	      .forEach(entry -> {
	    	  				searchDocumentsAuthor(entry.getKey()); 
	      					});      
	}
	
	@Override
	public void searchDocumentsAuthorSurname(String authorSurname) {
		  reseau.getListeAuthor()
	      .entrySet()
	      .stream()
	      .filter(entry -> entry.getKey().matches("(.*)"+authorSurname.toLowerCase()))
	      .forEach(entry -> {
	    	  	searchDocumentsAuthor(entry.getKey()); 
	      					});      
	}
	
	
	

	@Override
	public int searchNumberPeriod(int beginDate, int endDate) {
		int compteur = 0; 
		for(Document document: reseau.getListeDocument().values()) {
			if(listeCopieDoc.containsKey(document.getEAN())) {
				int date = document.getDate(); 
				if(!listeCopieDoc.get(document.getEAN()).equals(0)) {
					if(date >= beginDate && date <= endDate) {
						compteur ++; 
					}
				}
			
			}
		}
		for(Livre livre: reseau.getListeLivre().values()) {	
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
	
	
	public void addUtilisateur(Utilisateur utilisateur) {
		listeUtilisateur.put(utilisateur.getId(), utilisateur);
	}
	

	public void remettre(Bibliotheque bibliotheque, Document document){
		try {
			bibliotheque.emprunter(this, document);
		} catch (nonDispoException e) {
			e.printStackTrace();
		}
	}

	public void emprunter(Bibliotheque bibliotheque, Document document) throws nonDispoException {
		
		Integer nbCopieDoc = 0;
		Integer nbCopieLivre = 0;
		Integer nbCopieDocHome = 0;
		Integer nbCopieLivreHome = 0;
		if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())){ // si notre biblio a ce doc 
			 nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN()); // on prends le nb de copie du doc 
			 if(!listeCopieDoc.containsKey(document.getEAN())) { // si nous on l'a pas
				 listeCopieDoc.put(document.getEAN(), 0); //on ajoute le titre à notre liste 
			 }
			 else {
				 nbCopieDocHome = listeCopieDoc.get(document.getEAN());// sinon on recupere le nb dans notre liste 
			 }
			 
		}
		if(document instanceof Livre) {
			Livre livre = (Livre)document;
			if(bibliotheque.getListeCopieLivre().containsKey(livre.getISBN())){
				 nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
			
				 if(!listeCopieLivre.containsKey(livre.getISBN())) {
					 listeCopieLivre.put(livre.getISBN(), 0);
				 }
				 else {
					 nbCopieLivreHome = listeCopieLivre.get(livre.getISBN());
				 }
			}
		}
		if((nbCopieDoc==0 && nbCopieLivre==0)   ) {
			throw new nonDispoException();
		}
		else {
			if(nbCopieDoc!=0 ) {
				nbCopieDoc--;	
				nbCopieDocHome++;
				
				bibliotheque.getListeCopieDoc().replace(document.getEAN(), nbCopieDoc); 
				listeCopieDoc.replace(document.getEAN(), nbCopieDocHome);
			}
			if(nbCopieLivre != 0) {
				Livre livre = (Livre)document; 
				 
				nbCopieLivre--;
				nbCopieLivreHome++;
				bibliotheque.getListeCopieLivre().replace(livre.getISBN(), nbCopieLivre); 
				listeCopieLivre.replace(livre.getISBN(), nbCopieLivreHome);
			}	
		}

	}
	
}
