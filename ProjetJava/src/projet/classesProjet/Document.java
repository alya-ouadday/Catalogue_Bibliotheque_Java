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
	private String authorName;
	private String authorSurname;
	private String type;
	private int date;
	private int nbCopies;
	
	public Document(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies) {
		this.ean= ean; 
		this.title= title; 
		this.publisher = publisher; 
		this.date = date;
		this.authorName = authorName;
		this.authorSurname = authorSurname;
		this.type = type;
		this.nbCopies = nbCopies;
	}
	
	public abstract String getType(); 
	
	public String getEAN() {
		return ean; 
	}
	
	public String getAuthor() {
		return authorName + " " + authorSurname; 
	}
	
	public String getTitle() {
		return title; 
	}
	
	public int getDate() {
		return date; 
	}
	@Override
	public String toString() {
		String s = "EAN: " + ean + " titre: "+ title+ " editeur: "+ "date : "+date+ " Auteur: "+ authorName + " "
	+ authorSurname+ " type: "+ type+ " nombre de copies : " + nbCopies; 
		return s; 
	}
	
	public boolean equals(Document document) {
		return false; 
	}
	

}
