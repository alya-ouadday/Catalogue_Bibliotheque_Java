/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * represente les documents qui sont des revues
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Revue extends Livre {

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
	public Revue(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
		
	}
	
	@Override
	/**
	 * renvoie le type
	 */
	public String getType(){
		return("Revue");
	}

}
