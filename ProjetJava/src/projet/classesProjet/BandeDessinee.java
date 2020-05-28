/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * represente les documents qui sont des bandes-dessinees
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class BandeDessinee extends Livre{

	/**
	 * Constructeur
	 * @param ean
	 * @param title
	 * @param publisher
	 * @param date
	 * @param authorName
	 * @param authorSurname
	 * @param type
	 * @param nbCopies
	 * @param isbn
	 */
	public BandeDessinee(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
		
	}
	
	@Override
	/**
	 * renvoie le type
	 */
	public String getType(){
		return("Bande Dessinee");
	}

}
