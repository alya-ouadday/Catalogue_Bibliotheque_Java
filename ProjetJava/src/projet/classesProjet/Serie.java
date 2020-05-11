package projet.classesProjet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Serie {
	private String name;
	private HashMap<Document, Integer> listeDoc;
	
	public Serie(String name, HashMap<Document, Integer> listeDoc) {
		this.name = name;
		this.listeDoc = listeDoc;
	}
	public Serie(String name) {
		this(name, new HashMap<Document, Integer>());
	}
	
	public HashMap<Document, Integer> getListeDoc(){
		return listeDoc; 
	}
	
	public void addDocument(Document document, Integer numero) {
		if(document instanceof InSerie) {
			listeDoc.put(document, numero); 
		}
	}
	
	public String getName() {
		return name; 
	}
}
