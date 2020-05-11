/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public class JeuVideo extends Document {

	/**
	 * 
	 */
	public JeuVideo(String ean,String title, String publisher, String date, String authorName, String authorSurname, String type, int nbCopies) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		
	}
	
	@Override
	public String getType(){
		return("Jeu video");
	}

}
