/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * represente les documents qui sont des Livres
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Livre extends Document {

	/**
	 * ISBN du document (format à 10 ou 13 chiffre ou avec 9chiffre et un X avec des tirets)
	 */
	String isbn; 
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
	public Livre(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		this.isbn = isbn; 
		
	}
	/**
	 * retourne l'ISBN du livre
	 * @return l'isbn
	 */
	public String getISBN() {
		return isbn;
	}
	
	@Override
	/**
	 * renvoie le type
	 */
	public String getType(){
		return("Livre");
	}
	
	@Override
	/**
	 * renvoie le document sous forme de String
	 */
	public String toString() {
		return super.toString() + " ISBN: " + this.isbn;  
	}

}
