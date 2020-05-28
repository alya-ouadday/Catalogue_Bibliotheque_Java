/**
 * 
 */
package projet.exceptions;

/**
 * Exception si le format d'un ISBN n'est as respecté
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class formatISBNException extends Exception{

	/**
	 * Constructeur
	 */
	public formatISBNException() {
		super("L'ISBN donné n'a pas le format d'un ISBN: XXX-X-XXXXX-XXX-X"); 
	}

}
