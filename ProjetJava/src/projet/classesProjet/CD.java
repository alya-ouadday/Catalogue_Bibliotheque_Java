/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class CD extends Document {

	/**
	 * 
	 */
	public CD(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		
	}
	
	public String getType(){
		return("CD");
	}

}
