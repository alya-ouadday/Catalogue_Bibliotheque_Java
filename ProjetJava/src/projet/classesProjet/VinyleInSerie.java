/**
 * 
 */
package projet.classesProjet;

/**
 * @author fande
 *
 */
public class VinyleInSerie extends Vinyle implements InSerie{

	private Serie serie; 
	
	public VinyleInSerie(String ean, String title, String publisher, String date, String authorName,
														String authorSurname, String type, int nbCopies, Serie serie) {
		
		super(ean, title, publisher, date, authorName, authorSurname, type, nbCopies);
		this.serie = serie;
	}

	@Override
	public Serie getSerie() {
		// TODO Auto-generated method stub
		return serie;
	}

}
