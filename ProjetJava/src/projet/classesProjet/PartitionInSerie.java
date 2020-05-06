/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class PartitionInSerie extends Partition implements InSerie{

	/**
	 * 
	 */
	private Serie serie; 
	public PartitionInSerie(String ean,String title, String publisher, String date,
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
