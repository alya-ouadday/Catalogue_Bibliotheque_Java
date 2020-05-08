package projet.classesProjet;
import java.util.HashMap;

public class Serie {
	private String name;
	private HashMap<Integer, Document> listeDoc;
	
	public Serie(String name, HashMap<Integer, Document> listeDoc) {
		this.name = name;
		this.listeDoc = listeDoc;
	}
	public Serie(String name) {
		this(name, new HashMap<Integer, Document>());
	}
	
	public HashMap<Integer, Document> getListeDoc(){
		return listeDoc; 
	}
	
	public void addDocument(Document document) {
		
	}
}
