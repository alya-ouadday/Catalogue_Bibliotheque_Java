/**
 * 
 */
package projet.classesProjet;

import java.util.HashMap;
/**
 * @author fande
 *
 */
public class Bibliotheque {
	private String nom;
	private Reseau reseau;
	private HashMap<String,Integer> listeCopie;
	
	public Bibliotheque(String nom,Reseau reseau, HashMap<String,Integer> listeCopie) {
		this.nom = nom;
		this.reseau = reseau;
		this.listeCopie = listeCopie;
	}
	public Bibliotheque(String nom,Reseau reseau) {
		this(nom, reseau, new HashMap<String,Integer>());
	}
	
	
	
	public void addUtilisateur(Utilisateur utilisateur) {
		
	}
	
	
	
	public void addDocument(Document document) {
		
	}
	
}
