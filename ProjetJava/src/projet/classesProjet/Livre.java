/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class Livre extends Document {

	/**
	 * 
	 */
	
	String isbn; 
	
	public Livre(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies, String isbn) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		this.isbn = isbn; 
		
	}
	
	public String getISBN() {
		return isbn;
	}
	
	@Override
	public String getType(){
		return("Livre");
	}
	
	@Override
	public String toString() {
		return super.toString() + " ISBN: " + this.isbn;  
	}

}
