/**
 * 
 */
package projet.classesProjet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author alyab
 *
 */
public interface Consultable {
	
	public void ShowAllDocuments();
	public HashMap<Integer, Document> searchSerie(String serieName); 
	public Livre searchISBN(String isbn); 
	public Document searchEAN(String ean); 
	public ArrayList<Document> searchDocumentsAuthor(String authorName); 
	public ArrayList<Document> searchDocumentsAuthor(String authorName, String authorSurname); 
	public int searchNumberPeriod(String beginDate, String endDate ); 
	

}
