package projet.exceptions;

/**
 * Exception si l'utilisateur est d�ja inscrit ou pas encore inscrit dans une bibliotheque
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class inscriptionException extends Exception{

	/**
	 * Constructeur
	 * @param string qui specifie si l'utilisateur est d�j� ou pas inscrit
	 */
	public inscriptionException(String s) {
			super("Erreur. Vous �tes " + s + " inscrit"); 
	}

}
