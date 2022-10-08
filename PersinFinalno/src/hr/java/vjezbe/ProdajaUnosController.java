package hr.java.vjezbe;

import java.io.IOException;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;

public class ProdajaUnosController {

	@FXML
	private ComboBox<Artikl> artiklComboBox;
	@FXML
	private ComboBox<Korisnik> korisnikComboBox;
	@FXML
	private DatePicker datumObjaveDatePicker;
	@FXML
	private Button unesiButton;

	public void initialize() {
		List<Korisnik>dohvaceniKor;
		List<Artikl>dohvaceniArt;

		try {
			dohvaceniKor = BazaPodataka.dohvatSvihKorisnika();
			dohvaceniArt = BazaPodataka.dohvatSvihArtikala();
		} catch (BazaPodatakaException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return;
		}
		
		artiklComboBox.getItems().addAll(dohvaceniArt);
		korisnikComboBox.getItems().addAll(dohvaceniKor);
	}
	public void unesi() {
		
		String stofali="";
		if (artiklComboBox.getValue()==null) {
			stofali+="Morate odabrati artikl!\n";
		}
		if (korisnikComboBox.getValue()==null) {
			stofali+="Morate odabrati korisnika!\n";
		}
		if(datumObjaveDatePicker.getValue()==null) {
			stofali+="Morate odabrati datum!\n";
		}
		if(!stofali.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Neispravan unos");
			alert.setHeaderText("");
			alert.setContentText(stofali);
			alert.showAndWait();
			return;
		}
		Prodaja novaProd = new Prodaja(artiklComboBox.getValue(),korisnikComboBox.getValue(), datumObjaveDatePicker.getValue());
		
		try {
			BazaPodataka.pohraniNovuProdaju(novaProd);
		} catch (BazaPodatakaException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return;
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Uspjesan unos");
		alert.setHeaderText(null);
		alert.setContentText("Uspijesno ste unijeli prodaju.");
		alert.showAndWait();
		
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
