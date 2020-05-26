/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class Vinyle extends Document{

	/**
	 * 
	 */
	public Vinyle(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		
	}
	
	@Override
	public String getType(){
		return("Vinyle");
	}

}
