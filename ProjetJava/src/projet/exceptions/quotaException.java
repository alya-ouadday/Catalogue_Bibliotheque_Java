package projet.exceptions;

/**
 * Exception si le quota d'un utiisateur est dépassé
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class quotaException extends Exception{

	/**
	 * Constructeur
	 */
	public quotaException() {
		super("Votre quota est épuisé, vous ne pouvez plus emprunter"); 
	}

}
