package hr.java.vjezbe.entitet;

/**
 * Sadrzi konstante koje oznacavaju stanje artikala na prodaju.
 * 
 * @author Antonio Persin
 *
 */
public enum Stanje {

	novo(0,"Novi proizvod"),
	izvrsno(1,"Proizvod nije nov, ali je u izvrsnom stanju"),
	rabljeno(2,"Proizvod je rabljen."),
	neispravno(3,"Proizvod je neispravan.");
	
	private int kod;
	private String opis;
	
	/**
	 * Inicijalizira podatke o konstantama stanja.
	 * 
	 * @param kod podatak o ID-ju stanja artikla
	 * @param opis podatak o opisu stanja artikla
	 */
	private Stanje(int kod, String opis) {
		this.kod=kod;
		this.opis=opis;
	}

	public int getKod() {
		return kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}
