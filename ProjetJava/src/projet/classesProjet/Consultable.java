/**
 * 
 */
package projet.classesProjet;
import java.util.ArrayList;
import java.util.HashMap;
import projet.exceptions.*;

/**
 * 
 * Interface qui specifie les méthodes necessaire pour afficher et rechercher des documents
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public interface Consultable {
	
	/**
	 * affiche tous les documents
	 */
	public void ShowAllDocuments();
	/**
	 * affiche et retourne une série recherchée avec son nom
	 * @param serieName
	 * @return une hashmap liant les document de la série à leur numéro dans la série si la série est trouvé, renvoie un null sinon
	 */
	public HashMap<Document, Integer> searchSerie(String serieName); 
	/**
	 * affiche et retourne un livre recherché avec son ISBN
	 * @param isbn
	 * @return le livre recherché s'il est trouvé un livre null sinon
	 * @throws formatISBNException
	 */
	public Livre searchISBN(String isbn) throws formatISBNException; 
	/**
	 * affiche et retourne un document recherché avec son EAN
	 * @param ean
	 * @return le document recherché s'il est trouvé, renvoi un null sinon
	 * @throws formatEANException
	 */
	public Document searchEAN(String ean) throws formatEANException; 
	/**
	 * affiche les document des auteurs ayant le nom recherché
	 * @param authorName
	 */
	public void searchDocumentsAuthorName(String authorName);
	/**
	 * affiche les document des auteurs ayant le prenom recherché
	 * @param authorSurname
	 */
	public void searchDocumentsAuthorSurname(String authorSurname);
	/**
	 * affiche et retourne la liste des documents de l'auteur ayant le nom et le prénom recherchés
	 * @param authorNameSurname
	 * @return la liste des documents de l'auteur
	 */
	public ArrayList<Document> searchDocumentsAuthor(String authorNameSurname); 
	/**
	 * retourne le nombre de document paru entre deux date demandée
	 * @param beginDate
	 * @param endDate
	 * @return le nombre de document entre les deux dates
	 */
	public int searchNumberPeriod(int beginDate, int endDate ); 
	

}
