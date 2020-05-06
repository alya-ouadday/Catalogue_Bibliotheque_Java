/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class Livre extends Document {

	/**
	 * 
	 */
	
	String isbn; 
	
	public Livre(String ean,String title, String publisher, String date, boolean inSerie, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,inSerie,authorName, authorSurname, type,nbCopies); 
		this.isbn = isbn; 
		
	}

}
