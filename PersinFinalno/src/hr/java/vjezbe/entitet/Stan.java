package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * Pretstavlja entitet stana 
 * 
 * @author Antonio Persin
 *
 */
public class Stan extends Artikl implements Nekretnina, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8075714138022180068L;
	
	private BigDecimal kvadratura;
	private static final Logger log = LoggerFactory.getLogger(Automobil.class);
	
	/**
	 * Inicijalizira podatke o stanu, naslov, opis, cijenu i kvadraturu
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param naslov podatak o naslovu stana
	 * @param opis podatak o opisu stana
	 * @param cijena podatak o cijeni stana
	 * @param kvadratura podatak o kvadraturi stana
	 * @param stanje podatak o stanju stana
	 */
	public Stan(Long id, String naslov, String opis, BigDecimal cijena, BigDecimal kvadratura, Stanje stanje) {
		super(id, naslov, opis, cijena, stanje);
		this.kvadratura=kvadratura;
	}

	/**
	 *nadjacava metodu nadklase Artikl.java
	 */
	@Override
	public String tekstOglasa() {
		String tekst = "Naslov nekretnine: " + super.getNaslov() + "\n" + "Opis nekretnine: " + super.getOpis() + "\n"
				+ "Kvadratura nekretnine: "+ this.kvadratura + "\n" + "Stanje nekretnine: " + super.getStanje() + "\n" + "Porez na nekretnine: ";
		try {
			tekst+=this.izracunajPorez(super.getCijena()).toString();
		}catch(CijenaJePreniskaException izn){
			log.info("Cijena je manja od 10 000kn.", izn);
			tekst+="Cijena ne smije biti manja od 10000kn.";
		}
		tekst+="\n" + "Cijena nekretnine: " + super.getCijena();
		return tekst;
	}


	/**
	 *Nadjacavanje hashCode metode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((kvadratura == null) ? 0 : kvadratura.hashCode());
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
		Stan other = (Stan) obj;
		if (kvadratura == null) {
			if (other.kvadratura != null)
				return false;
		} else if (!kvadratura.equals(other.kvadratura))
			return false;
		return true;
	}

	public BigDecimal getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(BigDecimal kvadratura) {
		this.kvadratura = kvadratura;
	}

	@Override
	public String toString() {
		return naslov + "," + opis + ","+ kvadratura +","+ cijena + ",stanje:" + stanje;
	}
	

}
