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
	public BandeDessineeInSerie(String ean,String title, String publisher, String date,
			String authorName, String authorSurname, String type, int nbCopies, String isbn, Serie serie) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
		this.serie = serie; 
		
	}
	@Override
	public Serie getSerie() {
		// TODO Auto-generated method stub
		return null;
	}

}
