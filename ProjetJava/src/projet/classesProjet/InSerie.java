package projet.classesProjet;
/**
 * 
 * Interface qui specifie les méthodes necessaire pour appartenir à une série
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public interface InSerie {
	/**
	 * retourne la série à laquelle appartient le document
	 * @return la série
	 */
	public Serie getSerie();
	/**
	 * retourne le numero dans la serie à laquelle appartien le document
	 * @return le numéro
	 */
	public Integer getSeriesNumber();
	/**
	 * renvoie le document appartenant à une série sous forme de string
	 * @return la string
	 */
	public String toString();
}
