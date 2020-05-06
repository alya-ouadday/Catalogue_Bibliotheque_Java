/**
 * 
 */
package projet.classesProjet;
import java.util.HashMap;


/**
 * @author alyab
 *
 */
public class Serie {
	private String name; 
	private HashMap<Integer, Document> listDoc;  
	
	
	public Serie(String name) {
		this.name = name; 
		listDoc = new HashMap<Integer, Document>(); 
	}
	
	

}
