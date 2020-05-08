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
public class Reseau implements Consultable {
	private ArrayList<Bibliotheque> listeBiblio;
	private HashMap<String, Document> listeDocument;
	private HashMap<String, Livre> listeLivre;
	private HashMap<String, ArrayList<Document>> listeAuthor;
	private HashMap<String, Serie> listeSerie;
	
	public Reseau(ArrayList<Bibliotheque> listeBiblio, HashMap<String, Document> listeDocument, 
				   HashMap<String, Livre> listeLivre, HashMap<String, ArrayList<Document>> listeAuthor, HashMap<String, Serie> listeSerie) {
		this.listeBiblio = listeBiblio;
		this.listeDocument = listeDocument;
		this.listeLivre = listeLivre;
		this.listeAuthor = listeAuthor;
		this.listeSerie = listeSerie;	
	}
	
	public Reseau() {
		
		this(new ArrayList<Bibliotheque>(), new HashMap<String,Document>(), new HashMap<String, Livre>(),
				new HashMap<String,ArrayList<Document>>(), new HashMap<String, Serie>()) ;
		

	}
				
	
	public  HashMap<String, Serie>  getListeSerie(){
		return listeSerie; 
	}
	
	public void addBiblio(Bibliotheque bibliotheque) {
		listeBiblio.add(bibliotheque); 
		
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
	public Livre searchISBN(String isbn) {
		return null; 
	}

	@Override
	public Document searchEAN(String ean) {
		return listeDocument.get(ean); 
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
	
}
