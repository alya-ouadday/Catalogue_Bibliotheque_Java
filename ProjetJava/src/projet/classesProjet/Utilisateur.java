/**
 * 
 */
package projet.classesProjet;
import java.util.ArrayList;

/**
 * @author fande
 *
 */
public class Utilisateur {
	private int id;
	private String name;
	private int quota;
	private ArrayList<Bibliotheque> listeBiblioth�que;
	private ArrayList<Document> listeDocument;
	private static int cpt=1;
	
	public Utilisateur(String name, int quota, ArrayList<Bibliotheque> listeBiblioth�que, ArrayList<Document> listeDocument) {
		this.id = cpt;
		cpt++;
		this.name = name;
		this.quota = quota;
		this.listeBiblioth�que =listeBiblioth�que;
		this.listeDocument = listeDocument;
	}
	public Utilisateur(String name, int quota) {
		this(name, quota, new ArrayList<Bibliotheque>(), new ArrayList<Document>());
		
	}
	
}
