/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class BandeDessinee extends Livre{

	/**
	 * 
	 */
	public BandeDessinee(String ean,String title, String publisher, String date, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
		
	}
	
	@Override
	public String getType(){
		return("Bande Dessinee");
	}

}
