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
	public HashMap<Document, Integer> searchSerie(String serieName); 
	public Livre searchISBN(String isbn) throws formatISBNException; 
	public Document searchEAN(String ean) throws formatEANException; 
	public void searchDocumentsAuthorName(String authorName);
	public void searchDocumentsAuthorSurname(String authorSurname);
	public ArrayList<Document> searchDocumentsAuthor(String authorNameSurname); 
	public int searchNumberPeriod(int beginDate, int endDate ); 
	

}
