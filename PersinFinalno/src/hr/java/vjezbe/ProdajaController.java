package hr.java.vjezbe;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ProdajaController {

	@FXML
	private ComboBox<Artikl> artiklComboBox;
	@FXML
	private ComboBox<Korisnik> korisnikComboBox;
	@FXML
	private DatePicker datumObjaveDatePicker;
	@FXML
	private Button pretraziButton;
	@FXML
	private TableView<Prodaja> table;
	@FXML
	private TableColumn<Artikl, String> artiklTableColumn;
	@FXML
	private TableColumn<Korisnik, String> korisnikTableColumn;
	@FXML
	private TableColumn<LocalDate, String> datumTableColumn;

	public void initialize() {
		artiklTableColumn.setCellValueFactory(new PropertyValueFactory<>("artikl"));
		korisnikTableColumn.setCellValueFactory(new PropertyValueFactory<>("korisnik"));
		datumTableColumn.setCellValueFactory(new PropertyValueFactory<>("datumObjave"));

		List<Korisnik> dohvaceniKor;
		List<Artikl> dohvaceniArt;
		List<Prodaja> dohvacenaProd;

		try {
			dohvaceniKor = BazaPodataka.dohvatSvihKorisnika();
			dohvaceniArt = BazaPodataka.dohvatSvihArtikala();
			dohvacenaProd = BazaPodataka.dohvatiProdajuPremaKriterijima(null);
		} catch (BazaPodatakaException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return;
		}

		artiklComboBox.getItems().addAll(dohvaceniArt);
		korisnikComboBox.getItems().addAll(dohvaceniKor);

		ObservableList<Prodaja> listaProdaje = FXCollections.observableArrayList(dohvacenaProd);
		table.setItems(listaProdaje);
	}
	
	public void naPritisak() {
		List<Prodaja> dohvacenaProd;

		try {
			dohvacenaProd = BazaPodataka.dohvatiProdajuPremaKriterijima(null);
		} catch (BazaPodatakaException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return;
		}

		List<Prodaja> filtriranaProdaja = filterProdaja(dohvacenaProd);
		ObservableList<Prodaja> listaProdaje = FXCollections.observableArrayList(filtriranaProdaja);
		table.setItems(listaProdaje);
	}

	public List<Prodaja> filterProdaja(List<Prodaja> neFiltrirani) {
		List<Prodaja> filtrirani1;
		List<Prodaja> filtrirani2;
		List<Prodaja> filtrirani3;

		filtrirani1 = neFiltrirani.stream()
				.filter(a -> artiklComboBox.getValue() == null || a.getArtikl().equals(artiklComboBox.getValue()))
				.collect(Collectors.toList());
		filtrirani2 = filtrirani1.stream()
				.filter(a -> korisnikComboBox.getValue() == null || a.getKorisnik().getId()==korisnikComboBox.getValue().getId())
				.collect(Collectors.toList());
		filtrirani3 = filtrirani2.stream().filter(a -> datumObjaveDatePicker.getValue() == null
				|| a.getDatumObjave().equals(datumObjaveDatePicker.getValue())).collect(Collectors.toList());

		return filtrirani3;
	}

	public void prikaziPretraguAutomobila() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Automobili.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguProdaje() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Prodaja.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziUnosProdaje() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("ProdajaUnos.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguStanova() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Stanovi.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguUsluga() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Usluge.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguPrivatnihKorisnika() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("PrivatniKorisnici.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziPretraguPoslovnihKorisnika() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("PoslovniKorisnici.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziUnosAutomobila() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("AutomobiliUnos.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziUnosStanova() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("StanoviUnos.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziUnosUsluga() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("UslugeUnos.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziUnosPrivatnihKorisnika() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("PrivatniKorisniciUnos.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziUnosPoslovnih() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("PoslovniKorisniciUnos.fxml"));
			Main.setMainPage(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
