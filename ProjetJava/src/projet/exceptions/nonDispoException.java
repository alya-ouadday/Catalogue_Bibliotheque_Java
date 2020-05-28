package projet.exceptions;

/**
 * Exception si un document n'est actuellement pas disponible dans un bibliotheque
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class nonDispoException extends Exception {

	/**
	 * Constructeur
	 */
	public nonDispoException() {
		super("Le livre est indisponible, vous ne pouvez pas l'emprunter"); 
	}

}