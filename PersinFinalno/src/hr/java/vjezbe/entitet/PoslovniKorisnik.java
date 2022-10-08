package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Pretstavlja entitet poslovnog korisnika
 * 
 * @author Antonio Persin
 *
 */
public class PoslovniKorisnik extends Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4049249845934863530L;
	
	private String naziv;
	private String web;
	
	/**
	 * Inicijalizira osobne i kontakt podatke poslovnog korisnika
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param naziv podatak o nazivu
	 * @param web podatak o web stranici
	 * @param email podatak o mail adresi
	 * @param telefon podatak o broju telefona
	 */
	public PoslovniKorisnik(Long id, String naziv, String web, String email, String telefon) {
		super(id, email, telefon);
		this.naziv=naziv;
		this.web=web;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 *nadjacava metodu nadklase Korisnik.java
	 */
	public String dohvatiKontakt() {
		String kontakt="Naziv tvrtke: " + this.naziv + ", eMail: " + super.getEmail() + ", Web: " + this.web + ", Telefon: " + super.getTelefon();
		return kontakt;
	}

	@Override
	public String toString() {
		return naziv +",email:" + email + ",web:" + web +  ",tel:" + telefon;
	}
	
	
}
