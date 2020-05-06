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
				
	
	
	
	public void addBiblio(Bibliotheque bibliotheque) {
		listeBiblio.add(bibliotheque); 
		
	}
	public void addDocument(Document document) {
		
		
		
	}
	public void consulterDocuments() {
		
	}

	@Override
	public void ShowAllDocuments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Document> searchSerie() {
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
	
}
