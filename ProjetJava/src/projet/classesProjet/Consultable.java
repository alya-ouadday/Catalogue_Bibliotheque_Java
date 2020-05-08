/**
 * 
 */
package projet.classesProjet;
import java.util.ArrayList;
import java.util.HashMap;
import projet.exceptions.*;

/**
 * @author alyab
 *
 */
public interface Consultable {
	
	public void ShowAllDocuments();
	public HashMap<Integer, Document> searchSerie(String serieName); 
	public Livre searchISBN(String isbn) throws formatISBNException; 
	public Document searchEAN(String ean) throws formatEANException; 
	public ArrayList<Document> searchDocumentsAuthor(String authorName); 
	public ArrayList<Document> searchDocumentsAuthor(String authorName, String authorSurname); 
	public int searchNumberPeriod(String beginDate, String endDate ); 
	

}
