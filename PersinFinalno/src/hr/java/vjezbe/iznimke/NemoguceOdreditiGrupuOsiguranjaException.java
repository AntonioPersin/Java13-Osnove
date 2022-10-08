package hr.java.vjezbe.iznimke;

/**
 * Baca se u slucaju ne mogucnosti izracunavanja grupe osiguranja.
 * 
 * @author Antonio Persin
 *
 */
public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -212880976145839358L;

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 */
	public NemoguceOdreditiGrupuOsiguranjaException() {
		super("Nije bilo moguce odrediti grupu osiguranja.");
	}

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 * 
	 * @param message podatak o poruci
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(String message) {
		super(message);
	}

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 * 
	 * @param cause podatak o uzroku
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(Throwable cause) {
		super(cause);
	}

	/**
	 * Prenosi podatke konstruktoru nadklase.
	 * 
	 * @param message podatak o poruci
	 * @param cause podatak o uzroku
	 */
	public NemoguceOdreditiGrupuOsiguranjaException(String message, Throwable cause) {
		super(message, cause);
	}

}
