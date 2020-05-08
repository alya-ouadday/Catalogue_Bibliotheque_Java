package projet.exceptions;

public class formatEANException extends Exception {

	public formatEANException() {
		super("L'EAN donné n'a pas le bon format de 13 chiffres"); 
	}

}
