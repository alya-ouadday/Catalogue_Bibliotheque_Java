/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class Revue extends Livre {

	/**
	 * 
	 */
	public Revue(String ean,String title, String publisher, String date, boolean inSerie, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,inSerie,authorName, authorSurname, type,nbCopies,isbn); 
		
	}

}
