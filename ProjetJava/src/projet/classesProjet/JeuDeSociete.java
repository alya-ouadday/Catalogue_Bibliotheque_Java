package projet.classesProjet;

public class JeuDeSociete extends Document{

	public JeuDeSociete(String ean,String title, String publisher, int date, String authorName, String authorSurname, String type, int nbCopies) {
		super(ean,title,publisher,date,authorName, authorSurname, type,nbCopies); 
		
	}
	
	@Override
	public String getType(){
		return("Jeu de societe: ");
	}
		
	

}
