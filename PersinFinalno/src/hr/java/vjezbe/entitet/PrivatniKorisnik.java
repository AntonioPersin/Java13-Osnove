package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * pretstavlja entitet privatnog korisnika
 * 
 * @author Antonio Persin
 *
 */
public class PrivatniKorisnik extends Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7487476001851985168L;
	
	private String ime;
	private String prezime;
	
	/**
	 * Inicijalizira osobne i kontakt podatke o privatnom korisniku
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param ime podatak o imenu korisnika
	 * @param prezime podatak o prezimenu korisnika
	 * @param email podatak o mail adresi korisnika
	 * @param telefon podatak o broju telefona korisnika
	 */
	public PrivatniKorisnik(Long id, String ime, String prezime, String email, String telefon) {
		super(id, email, telefon);
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	/**
	 *nadjacava metodu iz nadklase Korisnik.java
	 */
	public String dohvatiKontakt() {
		String kontakt="Osobni podatci prodavatelja: " + this.ime + " " + this.prezime + ", eMail: " + super.getEmail() + ", Telefon: " + super.getTelefon();
		return kontakt;
	}

	@Override
	public String toString() {
		return ime+"," + prezime + ",email:" + email + ",tel:" + telefon;
	}
	
}
