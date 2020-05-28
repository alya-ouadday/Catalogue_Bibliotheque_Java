/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * Autre représente les autres types de document
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Autre extends Document{

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
	public Autre(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		
	}
	
	/**
	 * renvoie le type
	 */
	public String getType(){
		return("Autre");
	}

}
