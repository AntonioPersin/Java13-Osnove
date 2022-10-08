package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Omogucava izracunavanje grupe i cijene osiguranja na temelju snage vozila.
 * 
 * @author Antonio Persin
 *
 */
public interface Vozilo {

	static BigDecimal koeficijent=new BigDecimal(1.34);
	
	/**
	 * Izracunava snagu u kilovatima na temelju konjskih snaga.
	 * 
	 * @param ks podatak o konjskim snagama
	 * @return kw podatak o izracunatim kilovatima
	 */
	default public BigDecimal izracunajKw(BigDecimal ks) {
		BigDecimal kw=ks.divide(koeficijent);
		return kw;
	};	
	
	/**
	 * Vreca ID grupe osiguranja na temelju snage. 
	 * 
	 * @return grupa podatak o ID-ju grupe osiguranja
	 * @throws NemoguceOdreditiGrupuOsiguranjaException baca iznimku u slucaju da ID grupe ne postoji
	 */
	public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;
	
	/**
	 * izracunava cijenu osiguranja u kunama ovisno o ID-ju grupe osiguranja
	 * 
	 * @return cijena podatak o cijeni osiguranja u kunama
	 * @throws NemoguceOdreditiGrupuOsiguranjaException prosljeduje iznimku
	 */
	default public BigDecimal izracunajCijenuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
		switch(this.izracunajGrupuOsiguranja().intValue()) {
		case 1 :
			return new BigDecimal(1000); 
		case 2 :
			return new BigDecimal(2000); 
		case 3 :
			return new BigDecimal(3000); 
		case 4 :
			return new BigDecimal(4000); 
		case 5 :
			return new BigDecimal(5000); 
		default : 
			return new BigDecimal(0);
		}
	};

}
