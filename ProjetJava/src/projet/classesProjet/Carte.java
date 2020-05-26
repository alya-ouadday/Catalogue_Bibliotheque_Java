/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class Carte extends Livre{

	/**
	 * 
	 */
	public Carte(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
		
	}
	
	@Override
	public String getType(){
		return("Carte");
	}


}
