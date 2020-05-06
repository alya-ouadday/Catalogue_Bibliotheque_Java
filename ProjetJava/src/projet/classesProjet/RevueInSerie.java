/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class RevueInSerie extends Revue implements InSerie{

	private Serie serie; 
		public RevueInSerie(String ean,String title, String publisher, String date,
				String authorName, String authorSurname, String type, int nbCopies, String isbn, Serie serie) {
			super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies,isbn); 
			this.serie = serie; 
			
		}
		@Override
		public Serie getSerie() {
			// TODO Auto-generated method stub
			return serie;
		}
	

}
