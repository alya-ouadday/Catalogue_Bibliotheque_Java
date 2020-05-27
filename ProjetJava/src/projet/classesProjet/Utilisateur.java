/**
 * 
 */
package projet.classesProjet;
import projet.exceptions.*;
import java.util.ArrayList;

/**
 * @author fande
 *
 */
public class Utilisateur{
	private Integer id;
	private String name;
	private Integer quota;
	private ArrayList<Bibliotheque> listeBibliotheque;
	private ArrayList<Document> listeDocument;
	private static int cpt=1;
	
	public Utilisateur(String name, int quota, ArrayList<Bibliotheque> listeBibliothèque, ArrayList<Document> listeDocument) {
		this.id = cpt;
		cpt++;
		this.name = name;
		this.quota = quota;
		this.listeBibliotheque =listeBibliothèque;
		this.listeDocument = listeDocument;
	}
	public Utilisateur(String name, int quota) {
		this(name, quota, new ArrayList<Bibliotheque>(), new ArrayList<Document>());
		
	}
	
	public int getId() {
		return id;
	}
	public int getQuota() {
		return quota;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Bibliotheque> getListeBibliothèque() {
		return listeBibliotheque;
	}
	public ArrayList<Document> getListeDocument() {
		return listeDocument;
	}
	
	public void sInscrire(Bibliotheque bibli) {
		listeBibliotheque.add(bibli);
		bibli.addUtilisateur(this);
	}
	

	public void remettre(Bibliotheque bibliotheque, Document document) {
		// TODO Auto-generated method stub
		
	}

	public void emprunter(Bibliotheque bibliotheque, Document document) throws quotaException {
		
		listeDocument.add(document);
		Integer nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN());
		nbCopieDoc--;
		if(document instanceof Livre) {
			Livre livre = (Livre)document;
			Integer nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
			nbCopieLivre--;
		}
		
	}
	
}
