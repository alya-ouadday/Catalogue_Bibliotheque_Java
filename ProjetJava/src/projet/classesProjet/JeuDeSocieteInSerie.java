/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * represente les documents qui sont des jeux de société qui appartiennent à une serie
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class JeuDeSocieteInSerie extends JeuDeSociete implements InSerie{

	/**
	 * Serie à laquelle appartient ce document
	 */
	private Serie serie; 
	/**
	 * Numero dans la série à laquelle appartient ce document
	 */
	private Integer seriesNumber;
	/**
	 * Conctructeur
	 * @param ean
	 * @param title
	 * @param publisher
	 * @param date
	 * @param authorName
	 * @param authorSurname
	 * @param type
	 * @param nbCopies
	 * @param serie
	 * @param seriesNumber
	 */
	public JeuDeSocieteInSerie(String ean,String title, String publisher, int date,
			String authorName, String authorSurname, String type, int nbCopies, Serie serie, Integer seriesNumber) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		this.serie = serie; 
		this.seriesNumber = seriesNumber;
	}
	@Override
	/**
	 * retourne la serie
	 */
	public Serie getSerie() {
		return serie;
	}
	@Override
	/**
	 * retourne le numero dans la serie
	 */
	public Integer getSeriesNumber() {
		return seriesNumber;
	}
	@Override
	/**
	 * renvoie le document sous forme de String
	 */
	public String toString() {
		return super.toString() + " Serie: "+serie.getName()+" Numéro dans la serie:"+seriesNumber.toString();
	}

}
