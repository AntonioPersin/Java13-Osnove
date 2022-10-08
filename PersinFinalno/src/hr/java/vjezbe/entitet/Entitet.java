package hr.java.vjezbe.entitet;

/**
 * @author Antonio Persin
 * 
 * Pretstavlja entitet koji se moze prodavati
 *
 */
public class Entitet {

	private Long id;
	
	/**
	 * Inicijalizira ID koji ce se pridruziti artiklu.
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 */
	public Entitet(Long id) {
		this.id=id;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
