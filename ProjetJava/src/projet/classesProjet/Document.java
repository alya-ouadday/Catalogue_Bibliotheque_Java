/**
 * 
 */
package projet.classesProjet;

/**
 * 
 * Classe abstraite qui représente les documents
 * @author BEN OUADDAY et LEJEUNE
 * 
 */
public abstract class Document {
	/**
	 * l'EAN du document (format doit être de 13 chiffres)
	 */
	private String ean; 
	/**
	 * Titre du document
	 */
	private String title;
	/**
	 * Editeur du document
	 */
	private String publisher;
	/**
	 * Nom de l'auteur du document
	 */
	private String authorName;
	/**
	 * Prénom de l'auteur du document
	 */
	private String authorSurname;
	/**
	 * Type du document
	 */
	private String type;
	/**
	 * date de publication du document
	 */
	private int date;
	/**
	 * nombre de copies total du document dans le réseau
	 */
	private int nbCopies;
	
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
	 */
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
	/**
	 * retourne le type du document
	 * @return le type
	 */
	public abstract String getType(); 
	
	/**
	 * retourne l'EAN du document
	 * @return l'EAN
	 */
	public String getEAN() {
		return ean; 
	}
	
	/**
	 * retourne le nom et prénom de l'auteur
	 * @return le nom et prénom de l'auteur
	 */
	public String getAuthor() {
		return authorName + " " + authorSurname; 
	}
	/**
	 * retourne le titre du document
	 * @return le titre
	 */
	public String getTitle() {
		return title; 
	}
	/**
	 * retourne la date de publication du document
	 * @return la date
	 */
	public int getDate() {
		return date; 
	}
	
	@Override
	/**
	 * renvoie le document sous forme de string
	 */
	public String toString() {
		String s = "EAN: " + ean + " titre: "+ title+ " editeur: "+ "date : "+date+ " Auteur: "+ authorName + " "
	+ authorSurname+ " type: "+ type+ " nombre de copies : " + nbCopies; 
		return s; 
	}

}
