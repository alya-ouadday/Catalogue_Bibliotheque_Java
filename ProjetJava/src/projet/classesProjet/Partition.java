/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * represente les livres qui sont des Partitions
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Partition extends Livre{

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
	public Partition(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
	}
	
	@Override
	/**
	 * renvoie le type
	 */
	public String getType(){
		return("Partition");
	}

}
