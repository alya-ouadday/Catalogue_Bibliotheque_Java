/**
 * 
 */
package projet.classesProjet;

/**
 * @author fande
 *
 */
public class CDInSerie extends CD{

	private Serie serie; 
	
	public CDInSerie(String ean, String title, String publisher, String date, boolean inSerie, String authorName,
														String authorSurname, String type, int nbCopies, Serie serie) {
		
		super(ean, title, publisher, date, inSerie, authorName, authorSurname, type, nbCopies);
		this.serie = serie;
	}

}

