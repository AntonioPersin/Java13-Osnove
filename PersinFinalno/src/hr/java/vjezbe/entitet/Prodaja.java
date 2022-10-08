package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Pretstavlja entitet prodaje, potpunom oglasu s podatcima oglasivaca i artikla
 * kojeg oglasivac prodaje
 * 
 * @author Antonio Persin
 *
 */
public class Prodaja extends Entitet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3905072273621107425L;
	
	private Artikl artikl;
	private Korisnik korisnik;
	private LocalDate datumObjave;

	/**
	 * inicijalizira podatke o oglasivacu, artiklu kojeg prodaje oglasivac i datumu objave oglasa
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param artikl podatak o artiklu
	 * @param korisnik podatak o oglasivacu
	 * @param datumObjave podatak o datumu objave oglasa
	 */
	public Prodaja(Artikl artikl, Korisnik korisnik, LocalDate datumObjave) {
		super(null);
		this.artikl = artikl;
		this.korisnik = korisnik;
		this.datumObjave = datumObjave;
	}

	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public LocalDate getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(LocalDate datumObjave) {
		this.datumObjave = datumObjave;
	}

	@Override
	public String toString() {
		return "Oglas: "+artikl+"\nProdavatelj: "+ korisnik + "\nDatumObjave:" + datumObjave;
	}
	

}
