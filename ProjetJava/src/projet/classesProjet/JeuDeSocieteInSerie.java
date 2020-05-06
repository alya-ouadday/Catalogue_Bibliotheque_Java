/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class JeuDeSocieteInSerie extends JeuDeSociete{

	/**
	 * 
	 */
	Serie serie; 
	public JeuDeSocieteInSerie(String ean,String title, String publisher, String date, boolean inSerie,
			String authorName, String authorSurname, String type, int nbCopies, Serie serie) {
		super(ean,title,publisher,date,inSerie,authorName, authorSurname, type,nbCopies); 
		this.serie = serie; 
		
	}

}
