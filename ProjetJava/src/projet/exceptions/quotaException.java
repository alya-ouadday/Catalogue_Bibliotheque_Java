package projet.exceptions;

/**
 * Exception si le quota d'un utiisateur est d�pass�
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class quotaException extends Exception{

	/**
	 * Constructeur
	 */
	public quotaException() {
		super("Votre quota est �puis�, vous ne pouvez plus emprunter"); 
	}

}
