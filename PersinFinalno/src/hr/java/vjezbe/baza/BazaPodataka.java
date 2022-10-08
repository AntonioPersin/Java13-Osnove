package hr.java.vjezbe.baza;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {
	private static final String DATABASE_FILE = "database.properties";
	private static final Logger logger = LoggerFactory.getLogger(BazaPodataka.class);

	private static Connection spajanjeNaBazu() throws IOException, SQLException {
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		return veza;
	}

	public static List<Stan> dohvatiStanovePremaKriterijima(Stan stan) throws BazaPodatakaException {
		List<Stan> listaStanova = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");
			if (Optional.ofNullable(stan).isEmpty() == false) {
				if (Optional.ofNullable(stan).map(Stan::getId).isPresent())
					sqlUpit.append(" AND artikl.id = " + stan.getId());
				if (Optional.ofNullable(stan.getNaslov()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND artikl.naslov LIKE '%" + stan.getNaslov() + "%'");
				if (Optional.ofNullable(stan.getOpis()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND artikl.opis LIKE '%" + stan.getOpis() + "%'");
				if (Optional.ofNullable(stan).map(Stan::getCijena).isPresent())
					sqlUpit.append(" AND artikl.cijena = " + stan.getCijena());
				if (Optional.ofNullable(stan).map(Stan::getKvadratura).isPresent())
					sqlUpit.append(" AND artikl.kvadratura = " + stan.getKvadratura());
			}
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				BigDecimal kvadratura = resultSet.getBigDecimal("kvadratura");
				String stanje = resultSet.getString("naziv");
				Stan newStan = new Stan(id, naslov, opis, cijena, kvadratura, Stanje.valueOf(stanje));
				listaStanova.add(newStan);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaStanova;
	}

	public static void pohraniNoviStan(Stan stan) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 3);");
			preparedStatement.setString(1, stan.getNaslov());
			preparedStatement.setString(2, stan.getOpis());
			preparedStatement.setBigDecimal(3, stan.getCijena());
			preparedStatement.setBigDecimal(4, stan.getKvadratura());
			preparedStatement.setLong(5, (stan.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static List<Automobil> dohvatiAutomobilePremaKriterijima(Automobil auto) throws BazaPodatakaException {
		List<Automobil> listaAutomobila = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil'");
			if (Optional.ofNullable(auto).isEmpty() == false) {
				if (Optional.ofNullable(auto).map(Automobil::getId).isPresent())
					sqlUpit.append(" AND artikl.id = " + auto.getId());
				if (Optional.ofNullable(auto.getNaslov()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND artikl.naslov LIKE '%" + auto.getNaslov() + "%'");
				if (Optional.ofNullable(auto.getOpis()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND artikl.opis LIKE '%" + auto.getOpis() + "%'");
				if (Optional.ofNullable(auto).map(Automobil::getCijena).isPresent())
					sqlUpit.append(" AND artikl.cijena = " + auto.getCijena());
				if (Optional.ofNullable(auto).map(Automobil::getSnagaKs).isPresent())
					sqlUpit.append(" AND artikl.snaga = " + auto.getSnagaKs());
			}
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				BigDecimal snaga = resultSet.getBigDecimal("snaga");
				String stanje = resultSet.getString("naziv");
				Automobil newAuto = new Automobil(id, naslov, opis, cijena, snaga, Stanje.valueOf(stanje));
				listaAutomobila.add(newAuto);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaAutomobila;
	}

	public static void pohraniNoviAutomobil(Automobil auto) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 1);");
			preparedStatement.setString(1, auto.getNaslov());
			preparedStatement.setString(2, auto.getOpis());
			preparedStatement.setBigDecimal(3, auto.getCijena());
			preparedStatement.setBigDecimal(4, auto.getSnagaKs());
			preparedStatement.setLong(5, (auto.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static List<Usluga> dohvatiUslugePremaKriterijima(Usluga usluga) throws BazaPodatakaException {
		List<Usluga> listaUsluga = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv "
					+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");
			if (Optional.ofNullable(usluga).isEmpty() == false) {
				if (Optional.ofNullable(usluga).map(Usluga::getId).isPresent())
					sqlUpit.append(" AND artikl.id = " + usluga.getId());
				if (Optional.ofNullable(usluga.getNaslov()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND artikl.naslov LIKE '%" + usluga.getNaslov() + "%'");
				if (Optional.ofNullable(usluga.getOpis()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND artikl.opis LIKE '%" + usluga.getOpis() + "%'");
				if (Optional.ofNullable(usluga).map(Usluga::getCijena).isPresent())
					sqlUpit.append(" AND artikl.cijena = " + usluga.getCijena());
			}
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				String stanje = resultSet.getString("naziv");
				Usluga newUsluga = new Usluga(id, naslov, opis, cijena, Stanje.valueOf(stanje));
				listaUsluga.add(newUsluga);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaUsluga;
	}

	public static void pohraniNovuUslugu(Usluga usluga) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(1, usluga.getNaslov());
			preparedStatement.setString(2, usluga.getOpis());
			preparedStatement.setBigDecimal(3, usluga.getCijena());
			preparedStatement.setLong(4, (usluga.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static List<PrivatniKorisnik> dohvatiPrivatneKorisnikePremaKriterijima(PrivatniKorisnik priv)
			throws BazaPodatakaException {
		List<PrivatniKorisnik> listaPriv = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("select distinct korisnik.id, ime, prezime, email, telefon "
					+ "from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika "
					+ "where tipKorisnika.naziv = 'PrivatniKorisnik'");
			if (Optional.ofNullable(priv).isEmpty() == false) {
				if (Optional.ofNullable(priv).map(PrivatniKorisnik::getId).isPresent())
					sqlUpit.append(" AND korisnik.id = " + priv.getId());
				if (Optional.ofNullable(priv.getIme()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND korisnik.ime LIKE '%" + priv.getIme() + "%'");
				if (Optional.ofNullable(priv.getPrezime()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND korisnik.prezime LIKE '%" + priv.getPrezime() + "%'");
				if (Optional.ofNullable(priv).map(PrivatniKorisnik::getEmail).isPresent())
					sqlUpit.append(" AND korisnik.email = " + priv.getEmail());
				if (Optional.ofNullable(priv).map(PrivatniKorisnik::getTelefon).isPresent())
					sqlUpit.append(" AND korisnik.telefon = " + priv.getTelefon());
			}
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String email = resultSet.getString("email");
				String telefon = resultSet.getString("telefon");
				PrivatniKorisnik newPriv = new PrivatniKorisnik(id, ime, prezime, email, telefon);
				listaPriv.add(newPriv);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaPriv;
	}

	public static void pohraniNovogPrivatnogKorisnika(PrivatniKorisnik priv) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Ime, Prezime, Email, Telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 1);");
			preparedStatement.setString(1, priv.getIme());
			preparedStatement.setString(2, priv.getPrezime());
			preparedStatement.setString(3, priv.getEmail());
			preparedStatement.setString(4, priv.getTelefon());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static List<PoslovniKorisnik> dohvatiPoslovneKorisnikePremaKriterijima(PoslovniKorisnik posl)
			throws BazaPodatakaException {
		List<PoslovniKorisnik> listaPosl = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"select distinct korisnik.id, korisnik.naziv, web, email, telefon "
							+ "from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika "
							+ "where tipKorisnika.naziv = 'PoslovniKorisnik'");
			if (Optional.ofNullable(posl).isEmpty() == false) {
				if (Optional.ofNullable(posl).map(PoslovniKorisnik::getId).isPresent())
					sqlUpit.append(" AND korisnik.id = " + posl.getId());
				if (Optional.ofNullable(posl.getNaziv()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND korisnik.ime LIKE '%" + posl.getNaziv() + "%'");
				if (Optional.ofNullable(posl.getWeb()).map(String::isBlank).orElse(true) == false)
					sqlUpit.append(" AND korisnik.prezime LIKE '%" + posl.getWeb() + "%'");
				if (Optional.ofNullable(posl).map(PoslovniKorisnik::getEmail).isPresent())
					sqlUpit.append(" AND korisnik.email = " + posl.getEmail());
				if (Optional.ofNullable(posl).map(PoslovniKorisnik::getTelefon).isPresent())
					sqlUpit.append(" AND korisnik.telefon = " + posl.getTelefon());
			}
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naziv = resultSet.getString("naziv");
				String web = resultSet.getString("web");
				String email = resultSet.getString("email");
				String telefon = resultSet.getString("telefon");
				PoslovniKorisnik newPosl = new PoslovniKorisnik(id, naziv, web, email, telefon);
				listaPosl.add(newPosl);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaPosl;
	}

	public static void pohraniNovogPoslovnogKorisnika(PoslovniKorisnik posl) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Naziv, Web, Email, Telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(1, posl.getNaziv());
			preparedStatement.setString(2, posl.getWeb());
			preparedStatement.setString(3, posl.getEmail());
			preparedStatement.setString(4, posl.getTelefon());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static List<Prodaja> dohvatiProdajuPremaKriterijima(Prodaja prodaja) throws BazaPodatakaException {
		List<Prodaja> listaProdaje = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika, \r\n"
							+ "korisnik.naziv as nazivKorisnika, web, email, telefon, \r\n"
							+ "korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla,\r\n"
							+ "artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura,\r\n"
							+ "artikl.snaga, stanje.naziv as stanje, prodaja.datumObjave, artikl.id as idArtikla\r\n"
							+ "from korisnik inner join \r\n"
							+ "tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join\r\n"
							+ "prodaja on prodaja.idKorisnik = korisnik.id inner join\r\n"
							+ "artikl on artikl.id = prodaja.idArtikl inner join\r\n"
							+ "tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join\r\n"
							+ "stanje on stanje.id = artikl.idStanje where 1=1");
			if (Optional.ofNullable(prodaja).isEmpty() == false) {
				if (Optional.ofNullable(prodaja.getArtikl()).isPresent())
					sqlUpit.append(" AND prodaja.idArtikl = " + prodaja.getArtikl().getId());
				if (Optional.ofNullable(prodaja.getKorisnik()).isPresent())
					sqlUpit.append(" AND prodaja.idArtikl = " + prodaja.getKorisnik().getId());
				if (Optional.ofNullable(prodaja.getDatumObjave()).isPresent()) {
					sqlUpit.append(" AND prodaja.datumObjave = '"
							+ prodaja.getDatumObjave().format(DateTimeFormatter.ISO_DATE) + "'");
				}
			}
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Korisnik korisnik = null;
				if (resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
					korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("ime"),
							resultSet.getString("prezime"), resultSet.getString("email"),
							resultSet.getString("telefon"));
				} else if (resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
					korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"),
							resultSet.getString("nazivKorisnika"), resultSet.getString("web"),
							resultSet.getString("telefon"), resultSet.getString("email"));
				}
				Artikl artikl = null;
				if (resultSet.getString("tipArtikla").equals("Automobil")) {
					artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							resultSet.getBigDecimal("snaga"), Stanje.valueOf(resultSet.getString("stanje")));
				} else if (resultSet.getString("tipArtikla").equals("Usluga")) {
					artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							Stanje.valueOf(resultSet.getString("stanje")));
				} else if (resultSet.getString("tipArtikla").equals("Stan")) {
					artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							resultSet.getBigDecimal("kvadratura"), Stanje.valueOf(resultSet.getString("stanje")));
				}
				Prodaja novaProdaja = new Prodaja(artikl, korisnik,
						resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				listaProdaje.add(novaProdaja);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaProdaje;
	}

	public static void pohraniNovuProdaju(Prodaja prod) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into prodaja(IDARTIKL, IDKORISNIK, DATUMOBJAVE) values (?, ?, ?);");
			preparedStatement.setLong(1, prod.getArtikl().getId());
			preparedStatement.setLong(2, prod.getKorisnik().getId());
			preparedStatement.setDate(3, Date.valueOf(prod.getDatumObjave()));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static List<Artikl> dohvatSvihArtikala() throws BazaPodatakaException {
		List<Artikl> dohvaceni = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			String upit = "SELECT distinct artikl.id as idArtikla, naslov, opis, cijena, snaga, "
					+ "kvadratura, stanje.naziv as stanje, tipArtikla.naziv as tipArtikla " + "FROM artikl inner join "
					+ "stanje on stanje.id = artikl.idStanje inner join "
					+ "tipArtikla on tipArtikla.id = artikl.idTipArtikla";
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(upit);
			while (resultSet.next()) {
				Artikl artikl = null;
				if (resultSet.getString("tipArtikla").equals("Automobil")) {
					artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							resultSet.getBigDecimal("snaga"), Stanje.valueOf(resultSet.getString("stanje")));
				} else if (resultSet.getString("tipArtikla").equals("Usluga")) {
					artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							Stanje.valueOf(resultSet.getString("stanje")));
				} else if (resultSet.getString("tipArtikla").equals("Stan")) {
					artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
							resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
							resultSet.getBigDecimal("kvadratura"), Stanje.valueOf(resultSet.getString("stanje")));
				}
				dohvaceni.add(artikl);
			}

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return dohvaceni;
	}

	public static List<Korisnik> dohvatSvihKorisnika() throws BazaPodatakaException {
		List<Korisnik> dohvaceni = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			String upit = "SELECT distinct korisnik.id as idKorisnika, korisnik.naziv, web, email, "
					+ "telefon, ime, prezime, tipKorisnika.naziv as tipKorisnika " + "from korisnik inner join "
					+ "tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika";
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(upit);
			while (resultSet.next()) {
				Korisnik korisnik = null;
				if (resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
					korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("ime"),
							resultSet.getString("prezime"), resultSet.getString("email"),
							resultSet.getString("telefon"));
				} else if (resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
					korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("naziv"),
							resultSet.getString("web"), resultSet.getString("telefon"), resultSet.getString("email"));
				}
				dohvaceni.add(korisnik);
			}

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return dohvaceni;
	}
	public static Prodaja zadnjaProdaja() throws BazaPodatakaException {
		Prodaja novaProdaja=null;
		try (Connection connection = spajanjeNaBazu()) {
			String upit = "select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika,\r\n" + 
					"\r\n" + 
					"       korisnik.naziv as nazivKorisnika, web, email, telefon,\r\n" + 
					"\r\n" + 
					"       korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla,\r\n" + 
					"\r\n" + 
					"       artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura,\r\n" + 
					"\r\n" + 
					"       artikl.snaga, stanje.naziv as stanje, prodaja.datumObjave,\r\n" + 
					"\r\n" + 
					"       artikl.id as idArtikla\r\n" + 
					"\r\n" + 
					"from korisnik inner join\r\n" + 
					"\r\n" + 
					"     tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join\r\n" + 
					"\r\n" + 
					"     prodaja on prodaja.idKorisnik = korisnik.id inner join\r\n" + 
					"\r\n" + 
					"     artikl on artikl.id = prodaja.idArtikl inner join\r\n" + 
					"\r\n" + 
					"     tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join\r\n" + 
					"\r\n" + 
					"     stanje on stanje.id = artikl.idStanje\r\n" + 
					"\r\n" + 
					"order by datumObjave desc\r\n" + 
					"\r\n" + 
					"limit 1";
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(upit);
			resultSet.next();
			Korisnik korisnik = null;
			if (resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
				korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("ime"),
						resultSet.getString("prezime"), resultSet.getString("email"),
						resultSet.getString("telefon"));
			} else if (resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
				korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"),
						resultSet.getString("nazivKorisnika"), resultSet.getString("web"),
						resultSet.getString("telefon"), resultSet.getString("email"));
			}
			Artikl artikl = null;
			if (resultSet.getString("tipArtikla").equals("Automobil")) {
				artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
						resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
						resultSet.getBigDecimal("snaga"), Stanje.valueOf(resultSet.getString("stanje")));
			} else if (resultSet.getString("tipArtikla").equals("Usluga")) {
				artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
						resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
						Stanje.valueOf(resultSet.getString("stanje")));
			} else if (resultSet.getString("tipArtikla").equals("Stan")) {
				artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("naslov"),
						resultSet.getString("opis"), resultSet.getBigDecimal("cijena"),
						resultSet.getBigDecimal("kvadratura"), Stanje.valueOf(resultSet.getString("stanje")));
			}
			novaProdaja = new Prodaja(artikl, korisnik,
					resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return novaProdaja;
		
	}

}
