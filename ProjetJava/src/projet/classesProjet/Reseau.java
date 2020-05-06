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
public class Reseau {
	private ArrayList<Bibliotheque> listeBiblio;
	private HashMap<String, Document> listeDocument;
	private HashMap<String, Livre> listeLivre;
	private HashMap<String, Document> listeAuthor;
	private HashMap<String, Serie> listeSerie;
	
	public Reseau(ArrayList<Bibliotheque> listeBiblio, HashMap<String, Document> listeDocument, 
				   HashMap<String, Livre> listeLivre, HashMap<String, Document> listeAuthor, HashMap<String, Serie> listeSerie) {
		this.listeBiblio = listeBiblio;
		this.listeDocument = listeDocument;
		this.listeLivre = listeLivre;
		this.listeAuthor = listeAuthor;
		this.listeSerie = listeSerie;	
	}
	public Reseau() {
		this(new ArrayList<Bibliotheque>(), new HashMap<String, Document>(), new HashMap<String, Livre>(),
				new HashMap<String, Document>(), new HashMap<String, Serie>());
				
	}
	
	
	public void addBiblio(Bibliotheque bibliotheque) {
		
	}
	public void addDocument(Document document) {
		
	}
	public void consulterDocuments() {
		
	}
	
}
