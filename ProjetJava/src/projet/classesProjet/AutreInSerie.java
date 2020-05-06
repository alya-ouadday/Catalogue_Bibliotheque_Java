/**
 * 
 */
package projet.classesProjet;

/**
 * @author fande
 *
 */
public class AutreInSerie extends Autre{

	private Serie serie; 
	
	public AutreInSerie(String ean, String title, String publisher, String date, boolean inSerie, String authorName,
														String authorSurname, String type, int nbCopies, Serie serie) {
		
		super(ean, title, publisher, date, inSerie, authorName, authorSurname, type, nbCopies);
		this.serie = serie;
	}

}