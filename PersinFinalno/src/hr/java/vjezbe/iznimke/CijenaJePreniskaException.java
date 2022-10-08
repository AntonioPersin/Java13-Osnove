package hr.java.vjezbe.iznimke;

/**
 * Baca se u slucaju preniske cijene stana
 * 
 * @author Antonio Persin
 *
 */
public class CijenaJePreniskaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7966744782829041743L;

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 */
	public CijenaJePreniskaException() {
		super("Cijena je preniska.");
	}

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 * 
	 * @param message podatak o poruci
	 */
	public CijenaJePreniskaException(String message) {
		super(message);
	}

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 * 
	 * @param cause podatak o uzroku
	 */
	public CijenaJePreniskaException(Throwable cause) {
		super(cause);
	}

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 * 
	 * @param message podatak o poruci
	 * @param cause podatak o uzroku
	 */
	public CijenaJePreniskaException(String message, Throwable cause) {
		super(message, cause);
	}

}
