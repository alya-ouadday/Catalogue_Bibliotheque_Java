/**
 * 
 */
package projet.classesProjet;

/**
 * @author fande
 *
 */
public interface Echange {

	public void remettre(Bibliotheque bibliotheque, Document document);
	public void emprunter(Bibliotheque bibliotheque, Document document);
}
