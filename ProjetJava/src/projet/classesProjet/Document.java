/**
 * 
 */
package projet.classesProjet;

/**
 * @author alyab
 *
 */
public abstract class Document {
	private String ean; 
	private String title; 
	private String publisher; 
	private String date;
	private boolean inSerie;
	private String authorName;
	private String authorSurname;
	private String type;
	private int nbCopies;
	
	public Document(String ean,String title, String publisher, String date, boolean inSerie, String authorName, String authorSurname, String type, int nbCopies) {
		this.ean= ean; 
		this.title= title; 
		this.publisher = publisher; 
		this.date = date;
		this.inSerie = inSerie;
		this.authorName = authorName;
		this.authorSurname = authorSurname;
		this.type = type;
		this.nbCopies = nbCopies;
	}
	
	public boolean equals(Document document) {
		return false; 
	}

}
