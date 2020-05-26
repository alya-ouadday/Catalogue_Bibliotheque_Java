/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class Autre extends Document{

	/**
	 * 
	 */
	public Autre(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		
	}
	
	public String getType(){
		return("Autre");
	}

}
