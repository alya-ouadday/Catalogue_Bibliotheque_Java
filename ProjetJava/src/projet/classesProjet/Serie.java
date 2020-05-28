package projet.classesProjet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * represente les series
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Serie {
	/**
	 * nom de la série
	 */
	private String name;
	/**
	 * HashMap qui relie les document appartenant à la série à leur numéro dans la série
	 */
	private HashMap<Document, Integer> listeDoc;
	/**
	 * HashMap qui relie les document appartenant à la série à leur date de publication
	 */
	private HashMap<Document, Integer> listeDocDate;
	/**
	 * Constructeur
	 * @param name
	 * @param listeDoc
	 * @param listeDocDate
	 */
	public Serie(String name, HashMap<Document, Integer> listeDoc, HashMap<Document, Integer> listeDocDate) {
		this.name = name;
		this.listeDoc = listeDoc;
		this.listeDocDate = listeDocDate;
	}
	/**
	 * Constructeur simplifie
	 * @param name
	 */
	public Serie(String name) {
		this(name, new HashMap<Document, Integer>(),new HashMap<Document, Integer>());
	}
	/**
	 * retourne le nom de la serie
	 * @return name
	 */
	public String getName() {
		return name; 
	}
	/**
	 * retourne la HashMap qui relie les document appartenant à la série à leur numéro dans la série
	 * @return listeDoc
	 */
	public HashMap<Document, Integer> getListeDoc(){
		return listeDoc; 
	}
	/**
	 * retourne la HashMap qui relie les document appartenant à la série à leur date de publication
	 * @return listeDocDate
	 */
	public HashMap<Document, Integer> getListeDocDate(){
		return listeDocDate; 
	}
	/**
	 * ajoute un document à la série
	 * @param document qu'on veut ajouter
	 * @param numero dans la serie de ce document
	 * @param date de publication du document
	 */
	public void addDocument(Document document, Integer numero,Integer date) {
		if(document instanceof InSerie) {
			listeDoc.put(document, numero); 
			listeDocDate.put(document, date);
		}
	}
	
	
}
