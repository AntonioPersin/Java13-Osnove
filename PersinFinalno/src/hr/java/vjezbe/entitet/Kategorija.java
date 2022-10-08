package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Pretstavlja entitet kategorije oglasa
 * 
 * @author Antonio Persin
 *
 */
public class Kategorija<T extends Artikl> extends Entitet {
	private String naziv;
	private List<T> artikli=new ArrayList<>();

	/**
	 * Inicijalizira podatke o nazivu, id-u kategorije i polju artikala iste
	 * 
	 * @param id podatak o identifikacijskom broju artikla
	 * @param naziv   podatak o nazivu
	 * @param artikli podatci o svim objektima klase Artikl iz te kategorije
	 */
	public Kategorija(long id, String naziv, List<T> artikli) {
		super(id);
		this.naziv = naziv;
		this.artikli = artikli;
	}

	
	
	/**
	 *Nadjacava hashCode motodu.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		return result;
	}



	/**
	 *Nadjacava equals motodu.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kategorija<?> other = (Kategorija<?>) obj;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		return true;
	}



	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<T> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<T> artikli) {
		this.artikli = artikli;
	}

	/**
	 * Dodaje artikl u vec postojecu listu u objektu
	 * 
	 * @param banana podatak o artiklu koji dodajemo
	 */
	public void dodajArtikl(T banana) {
		artikli.add(banana);
	}
	
	/**
	 * Vraca artikl pod indeksom i iz liste koja se nalazi u objektu.
	 * 
	 * @param i podatak o ondeksu zeljenog artikla
	 * @return artikl zadanog indeksa
	 */
	public T dohvatiArtikl(int i) {
		return artikli.get(i);
	}
	
}
