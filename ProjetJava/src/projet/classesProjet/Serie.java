package projet.classesProjet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Serie {
	private String name;
	private HashMap<Document, Integer> listeDoc;
	private HashMap<Document, Integer> listeDocDate;
	
	public Serie(String name, HashMap<Document, Integer> listeDoc, HashMap<Document, Integer> listeDocDate) {
		this.name = name;
		this.listeDoc = listeDoc;
		this.listeDocDate = listeDocDate;
	}
	public Serie(String name) {
		this(name, new HashMap<Document, Integer>(),new HashMap<Document, Integer>());
	}
	public String getName() {
		return name; 
	}
	public HashMap<Document, Integer> getListeDoc(){
		return listeDoc; 
	}
	public HashMap<Document, Integer> getListeDocDate(){
		return listeDocDate; 
	}
	
	public void addDocument(Document document, Integer numero,Integer date) {
		if(document instanceof InSerie) {
			listeDoc.put(document, numero); 
			listeDocDate.put(document, date);
		}
	}
	
	
}
