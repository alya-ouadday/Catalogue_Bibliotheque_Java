/**
 * 
 */
package projet.classesProjet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public void addBiblio(Bibliotheque bibliotheque) {
		listeBiblio.put(bibliotheque.getName(), bibliotheque); 
		
	}
	public void addDocument(Document document) {
		if(document != null) {
			listeDocument.put(document.getEAN(), document); 
			if (listeAuthor.containsKey(document.getAuthor())){
				listeAuthor.get(document.getAuthor()).add(document); 
			}
			else {
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
			System.out.print(document.toString());
		}
		
	}

	@Override
	public HashMap<Integer, Document> searchSerie(String serieName) {
		HashMap<Integer, Document> serie = listeSerie.get(serieName).getListeDoc(); 
		for(Document document : serie.values()) {
			System.out.println(document.getTitle()); 
		}
		
		return serie; 
	}

	@Override
	public Livre searchISBN(String isbn) throws formatISBNException{
		if (!isbn.matches("\\d{3}-\\d{1}-\\d{5}-\\d{3}-\\d{1}")){
			throw new formatISBNException(); 
		}
		return listeLivre.get(isbn);  
	}

	@Override
	public Document searchEAN(String ean) throws formatEANException{
		if (!ean.matches("\\d{13}")){
			throw new formatEANException(); 
		}
		return listeDocument.get(ean); 
	}
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorName, String authorSurname) {
			
		return listeAuthor.get(authorName + " "+ authorSurname); 
	}
	@Override
	public ArrayList<Document> searchDocumentsAuthor(String authorName) {
		for(Map.Entry<String, ArrayList<Document>> entry : listeAuthor.entrySet()) {
			if( entry.getKey().matches(authorName +"(.*)")) {
				System.out.println(entry.getKey()+ " : \n" + entry.getValue().toString()); 
			}
			
		}
		return null; 
		
		
		
	}

	

	@Override
	public int searchNumberPeriod(String beginDate, String endDate) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addSerie(Serie serie) {
		listeSerie.put(serie.getName(), serie); 
	}
	
	public HashMap<String, Bibliotheque> getListeBiblio(){
		return listeBiblio; 
	}
	
}
