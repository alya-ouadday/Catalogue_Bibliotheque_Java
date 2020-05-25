/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class BandeDessineeInSerie extends BandeDessinee implements InSerie {

	private Serie serie; 
	private Integer seriesNumber;
	public BandeDessineeInSerie(String ean,String title, String publisher, String date,
			String authorName, String authorSurname, String type, int nbCopies, String isbn, Serie serie, Integer seriesNumber) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
		this.serie = serie; 
		this.seriesNumber = seriesNumber;
		
	}
	@Override
	public Serie getSerie() {
		// TODO Auto-generated method stub
		return serie;
	}
	@Override
	public Integer getSeriesNumber() {
		// TODO Auto-generated method stub
		return seriesNumber;
	}
	@Override
	public String toString() {
		return super.toString() + " Serie: "+serie.getName()+" Numéro dans la serie:"+seriesNumber.toString();
	}

}
