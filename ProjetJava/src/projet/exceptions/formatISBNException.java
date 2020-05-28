/**
 * 
 */
package projet.exceptions;

/**
 * Exception si le format d'un ISBN n'est as respect�
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public class formatISBNException extends Exception{

	/**
	 * Constructeur
	 */
	public formatISBNException() {
		super("L'ISBN donn� n'a pas le format d'un ISBN: XXX-X-XXXXX-XXX-X"); 
	}

}
