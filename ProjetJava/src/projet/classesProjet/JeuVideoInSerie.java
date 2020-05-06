/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class JeuVideoInSerie extends JeuVideo {

	/**
	 * 
	 */
	Serie serie; 
	public JeuVideoInSerie(String ean,String title, String publisher, String date, boolean inSerie,
			String authorName, String authorSurname, String type, int nbCopies, Serie serie) {
		super(ean,title,publisher,date,inSerie,authorName, authorSurname, type,nbCopies); 
		this.serie = serie; 
		
	}

}
