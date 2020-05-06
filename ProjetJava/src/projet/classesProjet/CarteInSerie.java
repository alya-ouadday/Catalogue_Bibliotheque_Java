/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class CarteInSerie extends Carte{

	/**
	 * 
	 */
	Serie serie; 
	public CarteInSerie(String ean,String title, String publisher, String date, boolean inSerie,
			String authorName, String authorSurname, String type, int nbCopies, String isbn, Serie serie) {
		super(ean,title,publisher,date,inSerie,authorName, authorSurname, type,nbCopies,isbn); 
		this.serie = serie; 
		
	}
}
