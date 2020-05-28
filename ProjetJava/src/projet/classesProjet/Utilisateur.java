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
	 * @param listeBibliotheque
	 * @param listeDocument
	 */
	public Utilisateur(String name, int quota, ArrayList<Bibliotheque> listeBibliotheque, ArrayList<Document> listeDocument) {
		this.id = cpt;
		cpt++;
		this.name = name;
		this.quota = quota;
		this.listeBibliotheque =listeBibliotheque;
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
	public ArrayList<Bibliotheque> getListeBibliotheque() {
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
		if(bibli.getListeUtilisateur().containsKey(id)) { // s'il est deja inscrit 
			throw new inscriptionException("deja");
		}
		else {// sinon on l'ajoute à la liste de la biblio et on ajoute la biblio à sa liste 
		listeBibliotheque.add(bibli);
		bibli.addUtilisateur(this);
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
	
		Integer nbCopieDoc = 0;
		Integer nbCopieLivre = 0; 
		if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())){ // si la biblio a bien le document souhaité 
			 nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN());	// on recupere son nb de copie  
		}
		if(document instanceof Livre) { // pareil si c'est un livre 
			Livre livre = (Livre)document;
			if(bibliotheque.getListeCopieLivre().containsKey(livre.getISBN())){
				 nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
			}
		}
		if((nbCopieDoc==0 && nbCopieLivre==0)   ) { // s'il n'est pas dispo on leve un exception 
			throw new nonDispoException();
		}
		else if(this.listeDocument.size() >= quota) { // si on depasse notre quota on leve une excpetion 
			throw new quotaException();
		}
		else if(!bibliotheque.getListeUtilisateur().containsKey(id)) { // si l'utilisateur n'est pas inscrit dans la bibliotheque 
			throw new inscriptionException("non");
		}
		else { // si tout est ok
			listeDocument.add(document); // on ajoute le document à la liste d'emprunt de l'utilisateur 
			if(nbCopieDoc>0) { // si le document etait bien dispo 
				nbCopieDoc--; // on decremente son nb de copie et on l'actualise dans la liste de documents de la bibliotheque
				bibliotheque.getListeCopieDoc().replace(document.getEAN(), nbCopieDoc); 
			}
			if(nbCopieLivre > 0) { // pareil si c'est un livre 
				Livre livre = (Livre)document; 
				nbCopieLivre--;
				bibliotheque.getListeCopieLivre().replace(livre.getISBN(), nbCopieLivre); 
			}
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
		if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())){// si la bibliotheque a qui on remet le livre l'a deja dans sa liste	 
			
			 nbCopieDoc = bibliotheque.getListeCopieDoc().get(document.getEAN()); // on recupere son nombre de copie 
		}else {// si elle ne l'a jamais eu 
			bibliotheque.getListeCopieDoc().put(document.getEAN(), 0); // on l'ajoute à sa liste 
		
		}
		if(document instanceof Livre) {// pareil si c'est un livre 
			Livre livre = (Livre)document;
			if(bibliotheque.getListeCopieLivre().containsKey(livre.getISBN())){
				 nbCopieLivre = bibliotheque.getListeCopieLivre().get(livre.getISBN());
			}else {
				bibliotheque.getListeCopieLivre().put(livre.getISBN(), 0);
	
			}
		}
		if(!bibliotheque.getListeUtilisateur().containsKey(id)) { // si l'utilisateur n'est pas inscrit dans la bibliotheque 
			//a qui il souhaite rendre
			throw new inscriptionException("non");
		}
		else {		// si tout est bon 
					listeDocument.remove(document);// on enleve le document de la liste d'emprunt de l'utilisateur 
		
			if(bibliotheque.getListeCopieDoc().containsKey(document.getEAN())) { // si c'est un document contenu dans la biblio
				nbCopieDoc++; // on incremente son nb de copie et on met a jour sa liste de document 
				bibliotheque.getListeCopieDoc().replace(document.getEAN(), nbCopieDoc); 
			}
			if(document instanceof Livre) {// pareil si c'est un livre 
				Livre livre = (Livre)document; 
				nbCopieLivre++;
				bibliotheque.getListeCopieLivre().replace(livre.getISBN(), nbCopieLivre); 
			}
		}
			
	}
	
}
