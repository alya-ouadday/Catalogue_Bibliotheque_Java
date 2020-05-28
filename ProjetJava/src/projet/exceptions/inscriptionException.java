package projet.exceptions;

/**
 * Exception si l'utilisateur est déja inscrit ou pas encore inscrit dans une bibliotheque
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class inscriptionException extends Exception{

	/**
	 * Constructeur
	 * @param string qui specifie si l'utilisateur est déjà ou pas inscrit
	 */
	public inscriptionException(String s) {
			super("Erreur. Vous êtes " + s + " inscrit"); 
	}

}
