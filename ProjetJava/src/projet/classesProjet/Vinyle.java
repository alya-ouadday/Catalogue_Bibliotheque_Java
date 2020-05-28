/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * represente les documents qui sont des vinyles
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Vinyle extends Document{

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
	 */
	public Vinyle(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		
	}
	
	@Override
	/**
	 * renvoie le type
	 */
	public String getType(){
		return("Vinyle");
	}

}
