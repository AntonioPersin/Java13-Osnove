package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Pretstavlja entitet usluge, nematerijalnog artikla
 * 
 * @author Antonio Persin
 *
 */
public class Usluga extends Artikl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3803384571916291472L;

	/**
	 * Inicijalizira tip artikla usluga
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param naslov podatak o naslovu usluge
	 * @param opis podatak o opisu usluge
	 * @param cijena podatak o cijeni usluge
	 * @param stanje podatak o stanju usluge
	 */
	public Usluga(Long id, String naslov, String opis, BigDecimal cijena, Stanje stanje) {
		super(id, naslov, opis, cijena, stanje);
	}

	/**
	 *nadjacava metodu nadklase Artikl.java
	 */
	@Override
	public String tekstOglasa() {
		String tekst="Naslov usluge: " + super.getNaslov() + "\n" + "Opis usluge: " + super.getOpis() + "\n" + "Cijena: " + super.getCijena() + "\n" + "Stanje usluge: " + super.getStanje();
		return tekst;
	}

	/**
	 *Nadjacavanje hashCode metode.
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
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
		return true;
	}

	@Override
	public String toString() {
		return naslov + "," + opis + "," + cijena;
	}
	

}
