/**
 * 
 */
package projet.classesProjet;
import projet.exceptions.*;
import java.util.ArrayList;

/**
 * 
 * represente un utilisateur du reseau
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class Utilisateur{
	/**
	 * identifiant de l'utilisateur
	 */
	private Integer id;
	/**
	 * nom de l'utilisateur
	 */
	private String name;
	/**
	 * quota de l'utilisateur
	 */
	private Integer quota;
	/**
	 * liste des bibliotheques dans lesquelles l'utilisateur est inscrit
	 */
	private ArrayList<Bibliotheque> listeBibliotheque;
	/**
	 * liste des documents que l'utilisateur a emprunter
	 */
	private ArrayList<Document> listeDocument;
	/**
	 * compteur de la classe utilisateur
	 */
	private static int cpt=1;
	
	/**
	 * Constructeur
	 * @param name
	 * @param quota
	 * @param listeBibliothèque
	 * @param listeDocument
	 */
	public Utilisateur(String name, int quota, ArrayList<Bibliotheque> listeBibliothèque, ArrayList<Document> listeDocument) {
		this.id = cpt;
		cpt++;
		this.name = name;
		this.quota = quota;
		this.listeBibliotheque =listeBibliothèque;
		this.listeDocument = listeDocument;
	}
	/**
	 * Constructeur simplifié
	 * @param name
	 * @param quota
	 */
	public Utilisateur(String name, int quota) {
		this(name, quota, new ArrayList<Bibliotheque>(), new ArrayList<Document>());
		
	}
	/**
	 * retourne l'id de l'utilisateur
	 * @return l'id
	 */
	public int getId() {
		return id;
	}
	/**
	 * retourne le quota de l'utilisateur
	 * @return quota
	 */
	public int getQuota() {
		return quota;
	}
	/**
	 * retourne le nom de l'utilisateur
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * retourne la liste des bibliotheques dans lesquelles l'utilisateur est inscrit
	 * @return liste de bibliotheque
	 */
	public ArrayList<Bibliotheque> getListeBibliothèque() {
		return listeBibliotheque;
	}
	/**
	 * retourne la liste des documents que l'utilisateur a emprunter
	 * @return liste de documents
	 */
	public ArrayList<Document> getListeDocument() {
		return listeDocument;
	}
	
	@Override
	/**
	 * renvoie le document sous forme de String
	 */
	public String toString(){
		String s = "utilisateur: id = "+id+" , nom = "+name+" , quota = "+quota+"\n";
		s+="inscrit dans les bibliotheque : "+listeBibliotheque+"\n";
		s+="possède les documents suivants : "+listeDocument+"\n";
		return s;
	}
	
	
	
	
	/**
	 * inscrit l'utilisateur dans une bibliotheque
	 * @param bibliotheque dans laquelle on inscrit l'utilisateur
	 * @throws inscriptionException
	 */
	public void sInscrire(Bibliotheque bibli) throws inscriptionException {
		if(bibli.getListeUtilisateur().containsKey(id)) {
			throw new inscriptionException("deja");
		}
		else {
		listeBibliotheque.add(bibli);
		bibli.addUtilisateur(this);
		}
	}
	

	/**
	 * remet un document emprunté dans une bibliotheque et le retire de la liste des document de l'utilisateur
	 * @param bibliotheque dans laquelle on rend
	 * @param document que l'on rend
	 * @throws inscriptionException
	 */
	public void remettre(Bibliotheque bibliotheque, Document document) throws inscriptionException {
		Integer nbCopieDoc = 0;
		Integer nbCopieLivre = 0;
		if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())){
			 nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN());		 
		}else {
			bibliotheque.getListeCopieDoc().put(document.getEAN(), 0);
			//nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN());
		}
		if(document instanceof Livre) {
			Livre livre = (Livre)document;
			if(bibliotheque.getListeCopieLivre().containsKey(livre.getISBN())){
				 nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
			}else {
				bibliotheque.getListeCopieLivre().put(livre.getISBN(), 0);
				//nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
			}
		}
		if(!bibliotheque.getListeUtilisateur().containsKey(id)) {
			throw new inscriptionException("non");
		}
		else {
			//for(Document doc : listeDocument) {
				//if(doc.equals(document))
					listeDocument.remove(document);
			//}
			if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())) {
				nbCopieDoc++;
				bibliotheque.getListeCopieDoc().replace(document.getEAN(), nbCopieDoc); 
			}
			if(document instanceof Livre) {
				Livre livre = (Livre)document; 
				nbCopieLivre++;
				bibliotheque.getListeCopieLivre().replace(livre.getISBN(), nbCopieLivre); 
			}
		}
			
	}

	/**
	 * Emprunte un document dans une bibliotheque et l'ajoute à la liste des document de l'utilisateur
	 * @param bibliotheque dans laquelle on emprunte
	 * @param document qu'on emprunte
	 * @throws quotaException
	 * @throws nonDispoException
	 * @throws inscriptionException
	 */
	public void emprunter(Bibliotheque bibliotheque, Document document) throws quotaException, nonDispoException, inscriptionException {
		// dans le main: vérifier que le document est au moins dans le réseau et pas null 
		Integer nbCopieDoc = 0;//null;
		Integer nbCopieLivre = 0; // null;
		if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())){
			 nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN());		 
		}
		if(document instanceof Livre) {
			Livre livre = (Livre)document;
			if(bibliotheque.getListeCopieLivre().containsKey(livre.getISBN())){
				 nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
			}
		}
		if((nbCopieDoc==0 && nbCopieLivre==0)   ) { // || (nbCopieDoc==null && nbCopieLivre==null) 
			//|| (nbCopieDoc<=0 && nbCopieLivre==null) || (nbCopieDoc==null && nbCopieLivre<=0)
			throw new nonDispoException();
		}
		else if(this.listeDocument.size() >= quota) {
			throw new quotaException();
		}
		else if(!bibliotheque.getListeUtilisateur().containsKey(id)) {
			throw new inscriptionException("non");
		}
		else {
			listeDocument.add(document);
			if(nbCopieDoc>0) {
				nbCopieDoc--;// nbCopieDoc != null && 
				bibliotheque.getListeCopieDoc().replace(document.getEAN(), nbCopieDoc); 
			}
			if(nbCopieLivre > 0) {
				Livre livre = (Livre)document; 
				nbCopieLivre--;
				bibliotheque.getListeCopieLivre().replace(livre.getISBN(), nbCopieLivre); 
			}
		}
		
	}
	
}
