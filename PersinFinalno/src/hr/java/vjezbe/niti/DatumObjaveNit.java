package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumObjaveNit implements Runnable {
	@Override
	public void run() {
		Alert mADrevenue = new Alert(AlertType.INFORMATION);
		mADrevenue.setHeaderText("");
		try {
			mADrevenue.setContentText(BazaPodataka.zadnjaProdaja().toString());
		} catch (BazaPodatakaException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return;
		}
		mADrevenue.showAndWait();
	}

	
}
