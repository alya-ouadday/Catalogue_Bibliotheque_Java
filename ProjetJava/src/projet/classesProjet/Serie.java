package projet.classesProjet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
	
	public void addDocument(Document document, Integer numero) {
		if(document instanceof InSerie) {
			listeDoc.put(numero, document); 
			Map<Integer, Document> treeMap = new TreeMap<Integer, Document>(listeDoc);
		}
	}
	
	public String getName() {
		return name; 
	}
}
