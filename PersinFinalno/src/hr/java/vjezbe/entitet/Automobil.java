package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Predstavlja entitet vozila.
 * 
 * @author Antonio Persin
 *
 */
public class Automobil extends Artikl implements Vozilo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7226663032984101724L;
	
	private BigDecimal snagaKs;
	private static final Logger log = LoggerFactory.getLogger(Automobil.class);

	/**
	 * Inicijalizira podatke o id-u, naslovu, opisu, cijeni i snazi (u konjskim snagama) vozila.
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param naslov podatak o naslovu
	 * @param opis podatak o opisu
	 * @param cijena podatak o cijeni
	 * @param snagaKs podatak o snazi u konjskim snagama
	 * @param stanje podatak o stanju automobila
	 */
	public Automobil(Long id, String naslov, String opis, BigDecimal cijena, BigDecimal snagaKs, Stanje stanje) {
		super(id, naslov, opis, cijena, stanje);
		this.snagaKs = snagaKs;
	}

	public BigDecimal getSnagaKs() {
		return snagaKs;
	}

	public void setSnagaKs(BigDecimal snagaKs) {
		this.snagaKs = snagaKs;
	}

	/**
	 *nadjacavanje metode iz sucelja Vozilo.java
	 */
	@Override
	public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
		if (snagaKs.intValue() <= 100) {
			return new BigDecimal(0);
		} else if (snagaKs.intValue() <= 200) {
			return new BigDecimal(1);
		} else if (snagaKs.intValue() <= 300) {
			return new BigDecimal(2);
		} else if (snagaKs.intValue() <= 400) {
			return new BigDecimal(3);
		} else if (snagaKs.intValue() <= 500) {
			return new BigDecimal(4);
		} else {
			throw new NemoguceOdreditiGrupuOsiguranjaException();
		}
	}

	/**
	 *nadjacavanje metode iz nadklase Artikl.java
	 */
	@Override
	public String tekstOglasa() {
		String tekst = "Naslov automobila: " + super.getNaslov() + "\n" + "Opis automobila: " + super.getOpis() + "\n"
				+ "Snaga automobila: " + this.snagaKs + "\n" + "Stanje automobila: " + super.getStanje() + "\n" + "Izracun osiguranja automobila: ";
				try{
					tekst+=this.izracunajCijenuOsiguranja();
				}catch(NemoguceOdreditiGrupuOsiguranjaException izn) {
					log.info("Nije bilo moguce odrediti grupu osiguranja.", izn);
					tekst+="Previse kw, ne mogu odrediti grupu osiguranja.";
				}
				tekst+="\n" + "Cijena automobila: " + super.getCijena();
		return tekst;
	}

	/**
	 *Nadjacavanje hashCode metode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((snagaKs == null) ? 0 : snagaKs.hashCode());
		return result;
	}

	/**
	 *Nadjacavanje equals metode.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Automobil other = (Automobil) obj;
		if (snagaKs == null) {
			if (other.snagaKs != null)
				return false;
		} else if (!snagaKs.equals(other.snagaKs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return naslov + "," + opis + ",snaga:"+ snagaKs + ",cijena:" + cijena + "kn,stanje:" + stanje;
	}
	
}
