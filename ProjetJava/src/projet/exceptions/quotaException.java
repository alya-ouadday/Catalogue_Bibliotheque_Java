package projet.exceptions;

public class quotaException extends Exception{

	public quotaException() {
		super("Votre quota est �puis�, vous ne pouvez plus emprunter"); 
	}

}
