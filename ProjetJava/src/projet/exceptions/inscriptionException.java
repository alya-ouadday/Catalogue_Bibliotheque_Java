package projet.exceptions;

public class inscriptionException extends Exception{

	public inscriptionException(String s) {
			super("Erreur. Vous �tes " + s + " inscrit"); 
	}

}
