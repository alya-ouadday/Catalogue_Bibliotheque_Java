/**
 * 
 */
package projet.exceptions;

/**
 * @author alyab
 *
 */
public class formatISBNException extends Exception{

	/**
	 * 
	 */
	public formatISBNException() {
		super("L'ISBN donné n'a pas le format d'un ISBN: XXX-X-XXXXX-XXX-X"); 
	}

}
