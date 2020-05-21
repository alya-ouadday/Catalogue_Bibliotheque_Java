/**
 * 
 */
package projet.classesProjet;

import java.util.ArrayList;
import java.util.HashMap;
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
				System.out.println(document.toString());
			}
			else if(document instanceof Livre) {
				Livre livre = (Livre)document;
				if(listeCopieLivre.containsKey(livre.getISBN())) {	
					System.out.println(livre.toString());
				}
			}
		}
	}
	
	@Override
	public HashMap<Document, Integer> searchSerie(String serieName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Livre searchISBN(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Document searchEAN(String ean) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void searchDocumentsAuthorName(String authorName) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void searchDocumentsAuthorSurname(String authorName) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorName, String authorSurname) {
		// TODO Auto-generated method stub
		return null;
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
