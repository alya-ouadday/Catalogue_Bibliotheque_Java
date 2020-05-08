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
	private String nom;
	private Reseau reseau;
	private HashMap<String,Integer> listeCopie;
	
	public Bibliotheque(String nom,Reseau reseau, HashMap<String,Integer> listeCopie) {
		this.nom = nom;
		this.reseau = reseau;
		this.listeCopie = listeCopie;
	}
	public Bibliotheque(String nom,Reseau reseau) {
		this(nom, reseau, new HashMap<String,Integer>());
	}
	
	
	
	public void addUtilisateur(Utilisateur utilisateur) {
		
	}
	
	
	
	public void addDocument(Document document) {
		
	}
	@Override
	public void ShowAllDocuments() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HashMap<Integer, Document> searchSerie(String serieName) {
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
	public ArrayList<Document> searchDocumentsAuthor(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorName, String authorSurname) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int searchNumberPeriod(String beginDate, String endDate) {
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
