/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * represente les documents qui sont des CD qui appartiennent � une serie
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class CDInSerie extends CD implements InSerie{
	/**
	 * Serie � laquelle appartient ce document
	 */
	private Serie serie; 
	/**
	 * Numero dans la s�rie � laquelle appartient ce document
	 */
	private Integer seriesNumber;
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
	 * @param serie
	 * @param seriesNumber
	 */
	public CDInSerie(String ean, String title, String publisher, int date, String authorName,
														String authorSurname, String type, int nbCopies, Serie serie, Integer seriesNumber) {
		
		super(ean, title, publisher, date, authorName, authorSurname, type, nbCopies);
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
		return super.toString() + " Serie: "+serie.getName()+" Num�ro dans la serie:"+seriesNumber.toString();
	}

}

