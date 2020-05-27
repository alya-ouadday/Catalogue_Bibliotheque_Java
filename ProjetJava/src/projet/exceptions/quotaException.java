package projet.exceptions;

public class quotaException extends Exception{

	public quotaException() {
		super("Votre quota est épuisé, vous ne pouvez plus emprunter"); 
	}

}
