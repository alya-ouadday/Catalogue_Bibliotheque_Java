package projet.exceptions;

public class nonDispoException extends Exception {

	public nonDispoException() {
		super("Le livre est indisponible, vous ne pouvez pas l'emprunter"); 
	}

}