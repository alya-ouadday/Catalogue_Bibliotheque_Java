/**
 * 
 */
package projet.classesProjet;
import java.util.ArrayList;
import java.util.HashMap;
import projet.exceptions.*;

/**
 * 
 * Interface qui specifie les m�thodes necessaire pour afficher et rechercher des documents
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public interface Consultable {
	
	/**
	 * affiche tous les documents
	 */
	public void ShowAllDocuments();
	/**
	 * affiche et retourne une s�rie recherch�e avec son nom
	 * @param serieName
	 * @return une hashmap liant les document de la s�rie � leur num�ro dans la s�rie si la s�rie est trouv�, renvoie un null sinon
	 */
	public HashMap<Document, Integer> searchSerie(String serieName); 
	/**
	 * affiche et retourne un livre recherch� avec son ISBN
	 * @param isbn
	 * @return le livre recherch� s'il est trouv� un livre null sinon
	 * @throws formatISBNException
	 */
	public Livre searchISBN(String isbn) throws formatISBNException; 
	/**
	 * affiche et retourne un document recherch� avec son EAN
	 * @param ean
	 * @return le document recherch� s'il est trouv�, renvoi un null sinon
	 * @throws formatEANException
	 */
	public Document searchEAN(String ean) throws formatEANException; 
	/**
	 * affiche les document des auteurs ayant le nom recherch�
	 * @param authorName
	 */
	public void searchDocumentsAuthorName(String authorName);
	/**
	 * affiche les document des auteurs ayant le prenom recherch�
	 * @param authorSurname
	 */
	public void searchDocumentsAuthorSurname(String authorSurname);
	/**
	 * affiche et retourne la liste des documents de l'auteur ayant le nom et le pr�nom recherch�s
	 * @param authorNameSurname
	 * @return la liste des documents de l'auteur
	 */
	public ArrayList<Document> searchDocumentsAuthor(String authorNameSurname); 
	/**
	 * retourne le nombre de document paru entre deux date demand�e
	 * @param beginDate
	 * @param endDate
	 * @return le nombre de document entre les deux dates
	 */
	public int searchNumberPeriod(int beginDate, int endDate ); 
	

}
