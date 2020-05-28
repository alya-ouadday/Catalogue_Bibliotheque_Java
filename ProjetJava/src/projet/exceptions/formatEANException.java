package projet.exceptions;

/**
 * Exception si le format d'un EAN n'est as respecté
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class formatEANException extends Exception {

	/**
	 * Constructeur
	 */
	public formatEANException() {
		super("L'EAN donné n'a pas le bon format de 13 chiffres"); 
	}

}
