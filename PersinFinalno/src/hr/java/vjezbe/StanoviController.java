package hr.java.vjezbe;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class StanoviController {

	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField kvadraturaTextField;
	@FXML
	private TextField cijenaTextField;
	@FXML
	private Button pretraziButton;
	@FXML
	private TableView<Stan> table;
	@FXML
	private TableColumn<Stan, String> naslovTableColumn;
	@FXML
	private TableColumn<Stan, String> opisTableColumn;
	@FXML
	private TableColumn<Stan, BigDecimal> kvadraturaTableColumn;
	@FXML
	private TableColumn<Stan, BigDecimal> cijenaTableColumn;
	@FXML
	private TableColumn<Stan, String> stanjeTableColumn;

	public void initialize() {
		naslovTableColumn.setCellValueFactory(new PropertyValueFactory<>("naslov"));
		opisTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
		kvadraturaTableColumn.setCellValueFactory(new PropertyValueFactory<>("kvadratura"));
		cijenaTableColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
		stanjeTableColumn.setCellValueFactory(new PropertyValueFactory<>("stanje"));

		List<Stan> listItems;
		try {
			listItems = BazaPodataka.dohvatiStanovePremaKriterijima(null);
		} catch (BazaPodatakaException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return;
		}
		List<Stan>filtriraniStanovi=new ArrayList<>();
		try {
			filtriraniStanovi=filterStan(listItems);
		}catch(NumberFormatException ex) {
			
		}
		ObservableList<Stan> listaStanova = FXCollections.observableArrayList(filtriraniStanovi);

		table.setItems(listaStanova);
	}

	public List<Stan> filterStan(List<Stan> neFiltrirani) {
		List<Stan> filtrirani1;
		List<Stan> filtrirani2;
		List<Stan> filtrirani3;
		List<Stan> filtrirani4;
		
		filtrirani1 = neFiltrirani.stream()
				.filter(a -> a.getNaslov().contains(naslovTextField.getText()))
				.collect(Collectors.toList());
		filtrirani2 = filtrirani1.stream()
				.filter(a -> a.getOpis().contains(opisTextField.getText()))
				.collect(Collectors.toList());
		try {
			filtrirani3 = filtrirani2.stream()
					.filter(a -> a.getKvadratura().equals(new BigDecimal(kvadraturaTextField.getText())))
					.collect(Collectors.toList());
		}catch(NumberFormatException ex) {
			filtrirani3=filtrirani2;
		}
		try {
			filtrirani4 = filtrirani3.stream()
					.filter(a -> a.getCijena().equals(new BigDecimal(cijenaTextField.getText())))
					.collect(Collectors.toList());
		}catch(NumberFormatException ex) {
			filtrirani4=filtrirani3;
		}
		return filtrirani4;
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
	public void prikaziPretraguAutomobila() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Automobili.fxml"));
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
