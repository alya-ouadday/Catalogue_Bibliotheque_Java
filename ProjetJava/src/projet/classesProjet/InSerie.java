package projet.classesProjet;
/**
 * 
 * Interface qui specifie les m�thodes necessaire pour appartenir � une s�rie
 * @author BEN OUADDAY et LEJEUNE
 *
 */
public interface InSerie {
	/**
	 * retourne la s�rie � laquelle appartient le document
	 * @return la s�rie
	 */
	public Serie getSerie();
	/**
	 * retourne le numero dans la serie � laquelle appartien le document
	 * @return le num�ro
	 */
	public Integer getSeriesNumber();
	/**
	 * renvoie le document appartenant � une s�rie sous forme de string
	 * @return la string
	 */
	public String toString();
}
