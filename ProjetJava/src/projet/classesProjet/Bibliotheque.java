/**
 * 
 */
package projet.classesProjet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * @author fande
 *
 */
public class Bibliotheque implements Consultable, Echange {
	private String name;
	private Reseau reseau;
	private HashMap<String,Integer> listeCopieDoc;
	private HashMap<String,Integer> listeCopieLivre;
	
	public Bibliotheque(String name,Reseau reseau, HashMap<String,Integer> listeCopieDoc,HashMap<String,Integer> listeCopieLivre) {
		this.name = name;
		this.reseau = reseau;
		this.listeCopieDoc = listeCopieDoc;
		this.listeCopieLivre = listeCopieLivre; 
		
	}
	public Bibliotheque(String nom,Reseau reseau) {
		this(nom, reseau, new HashMap<String,Integer>(), new HashMap<String,Integer>());
	}
	
	public String getName() {
		return name; 
	}
	
	
	
	public void addUtilisateur(Utilisateur utilisateur) {
		
		
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
				System.out.println(document.toString() + "  Nombre de copie disponible dans cette bibliothèque :" + listeCopieDoc.get(document.getEAN()));
			}
		}
		for(Livre livre: reseau.getListeLivre().values()) {	
				if(listeCopieLivre.containsKey(livre.getISBN()) && !(listeCopieDoc.containsKey(livre.getEAN()))) {	
					System.out.println(livre.toString() + "  Nombre de copie disponible dans cette bibliothèque :" + listeCopieLivre.get(livre.getISBN()));
				}
		}
	}
	
	@Override
	public HashMap<Document, Integer> searchSerie(String serieName) {
		HashMap<Document, Integer> serie = reseau.getListeSerie().get(serieName).getListeDoc();
		
		serie.entrySet()
		  .stream()
		  .sorted(Map.Entry.comparingByValue())
		  .filter(entry -> {
					  if(entry.getKey() instanceof Livre) {
						  Livre livre = (Livre)entry.getKey();
						  return listeCopieLivre.containsKey(livre.getISBN());
					  }else
					  return listeCopieDoc.containsKey(entry.getKey().getEAN());
		  } )
		  .forEach(System.out::println);
		
		return serie; 
	}
	@Override
	public Livre searchISBN(String isbn) {
		Livre livre = null; 
		if(listeCopieLivre.containsKey(isbn)) {
			livre = reseau.getListeLivre().get(isbn); 
			System.out.println(livre); 
		}
		return livre;
	}
	@Override
	public Document searchEAN(String ean) {
		Document document = null; 
		if(listeCopieDoc.containsKey(ean)) {
			document = reseau.getListeDocument().get(ean); 
			System.out.println(document); 
		}
		return document;
	}
	@Override
	public void searchDocumentsAuthorName(String authorName) {
		
	}
	
	@Override
	public void searchDocumentsAuthorSurname(String authorName) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorName, String authorSurname) {
		
		ArrayList<Document> listeDoc = new ArrayList<Document>(); 
		if(reseau.getListeAuthor().containsKey(authorName + " "+ authorSurname)){
		for(Document document :reseau.getListeAuthor().get(authorName + " "+ authorSurname) ) {
			if(listeCopieDoc.containsKey(document.getEAN())) {
				listeDoc.add(document);
				System.out.println(document.toString()); 
			}
			else if(document instanceof Livre) {
				Livre livre = (Livre)document; 
				if(listeCopieLivre.containsKey(livre.getISBN())) {
					listeDoc.add(livre); 
					System.out.println(livre.toString());
				}
			}
		}
		}
		else {
			System.out.println("Cet auteur n'a rien écrit"); 
		}
		return listeDoc; 
	}
	@Override
	public int searchNumberPeriod(int beginDate, int endDate) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public void remettre(Bibliotheque bibliotheque, Document document) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void emprunter(Bibliotheque bibliotheque, Document document) {
		// TODO Auto-generated method stub
		
	}
	
}
