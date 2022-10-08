package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet artikla na prodaju, klasu nasljeduju druge klase koje poblize opisuju artikle
 * 
 * @author Antonio Persin
 *
 */
public abstract class Artikl extends Entitet {
	
	protected String naslov;
	protected String opis;
	protected BigDecimal cijena;
	protected Stanje stanje;
	
	/**
	 * Inicijalizira podatke o artiklu, id, naslov, opis i cijenu
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param naslov podatak o naslovu
	 * @param opis podatak o opisu
	 * @param cijena podatak o cijeni
	 * @param stanje podatak o stanju artikla
	 */
	public Artikl(Long id, String naslov, String opis, BigDecimal cijena, Stanje stanje){
		super(id);
		this.naslov=naslov;
		this.opis=opis;
		this.cijena=cijena;
		this.stanje=stanje;
	}
	
	/**
	 *Nadjacavanje hashCode metode.
	 *
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cijena == null) ? 0 : cijena.hashCode());
		result = prime * result + ((naslov == null) ? 0 : naslov.hashCode());
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((stanje == null) ? 0 : stanje.hashCode());
		return result;
	}

	/**
	 *N+Nadjacavanje equals metode.
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikl other = (Artikl) obj;
		if (cijena == null) {
			if (other.cijena != null)
				return false;
		} else if (!cijena.equals(other.cijena))
			return false;
		if (naslov == null) {
			if (other.naslov != null)
				return false;
		} else if (!naslov.equals(other.naslov))
			return false;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (stanje != other.stanje)
			return false;
		return true;
	}

	public void setNaslov(String naslov) {
		this.naslov=naslov;
	}
	
	public void setOpis(String opis) {
		this.opis=opis;
	}
	
	public void setCijena(BigDecimal cijena) {
		this.cijena=cijena;
	}
	
	public String getNaslov() {
		return this.naslov;
	}
	
	public String getOpis() {
		return this.opis;
	}
	
	public BigDecimal getCijena() {
		return this.cijena;
	}
	
	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	
	/**
	 * Sastavlja tekst koji ce se prikazivati na oglasu.
	 * 
	 * @return tekst, tekst oglasa za taj artikl
	 */
	public abstract String tekstOglasa();

}
