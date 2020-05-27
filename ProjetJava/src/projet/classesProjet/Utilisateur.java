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
	
	public void sInscrire(Bibliotheque bibli) throws inscriptionException {
		if(bibli.getListeUtilisateur().containsKey(id)) {
			throw new inscriptionException("deja");
		}
		else {
		listeBibliotheque.add(bibli);
		bibli.addUtilisateur(this);
		}
	}
	

	public void remettre(Bibliotheque bibliotheque, Document document) throws inscriptionException {
		Integer nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN());
		Integer nbCopieLivre = null;
		if(document instanceof Livre) {
			Livre livre = (Livre)document;
			nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
		}
		if(!bibliotheque.getListeUtilisateur().containsKey(id)) {
			throw new inscriptionException("non");
		}
		else {
			for(Document doc : listeDocument) {
				if(doc.equals(document))
					listeDocument.remove(document);
			}
			if(nbCopieDoc != null) nbCopieDoc++;
			if(document instanceof Livre) {
				nbCopieLivre++;
			}
		}
			
	}

	public void emprunter(Bibliotheque bibliotheque, Document document) throws quotaException, nonDispoException, inscriptionException {
		Integer nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN());
		Integer nbCopieLivre = null;
		if(document instanceof Livre) {
			Livre livre = (Livre)document;
			nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
		}
		if(this.listeDocument.size() >= quota) {
			throw new quotaException();
		}
		else if(nbCopieDoc<=0 || (nbCopieLivre<=0 && nbCopieLivre!=null)) {
			throw new nonDispoException();
		}
		else if(!bibliotheque.getListeUtilisateur().containsKey(id)) {
			throw new inscriptionException("non");
		}
		else {
			listeDocument.add(document);
			if(nbCopieDoc != null) nbCopieDoc++;
			if(document instanceof Livre) {
				nbCopieLivre--;
			}
		}
		
	}
	
}
