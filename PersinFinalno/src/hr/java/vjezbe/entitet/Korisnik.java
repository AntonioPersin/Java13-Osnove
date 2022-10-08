package hr.java.vjezbe.entitet;

/**
 * pretstavlja entitet korisnika
 * 
 * @author Antonio Persin
 *
 */
public abstract class Korisnik extends Entitet {

	protected String email;
	protected String telefon;
	
	/**
	 * Inicijalizira osnovne kontakt podatke o korisniku i njegov ID
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param email podatak o mailu korisnika
	 * @param telefon podatak o broju telefona korisnika
	 */
	public Korisnik(Long id, String email, String telefon) {
		super(id);
		this.email=email;
		this.telefon=telefon;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	/**
	 * Sastavlja tekst sa svim podatcima korisnika
	 * 
	 * @return kontakt, svi osobni podatci korisnika i njegov kontakt
	 */
	public abstract String dohvatiKontakt();
	
}
