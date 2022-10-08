package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * Omogucava izracunavanje cijene poreza na nekretnine, ovisno o cijeni same nekretnine
 * 
 * @author Antonio Persin
 *
 */
public interface Nekretnina {

	/**
	 * Izracunava cijenu poreza na nekretnine ovisno o cijeni same nekretnine
	 * 
	 * @param cijena podatak o cijeni nekretnine
	 * @return cijena poreza na nekretnine
	 */
	default public BigDecimal izracunajPorez (BigDecimal cijena) {
		BigDecimal uvijet =new BigDecimal(10000);
		if(cijena.compareTo(uvijet)<0) {
			throw new CijenaJePreniskaException();
		}
		BigDecimal postotak =new BigDecimal(0.03);
		return cijena.multiply(postotak);
	}

}
