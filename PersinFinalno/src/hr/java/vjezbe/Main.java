package hr.java.vjezbe;

import hr.java.vjezbe.niti.DatumObjaveNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Persin-7.fxml"));
			Scene scene = new Scene(root, 600, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			Timeline prikazSlavljenika = new Timeline(new
			KeyFrame(Duration.seconds(10),
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Platform.runLater(new DatumObjaveNit());
						}
					}));
			prikazSlavljenika.setCycleCount(Timeline.INDEFINITE);
			prikazSlavljenika.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setMainPage(BorderPane root) {
		Scene scene = new Scene(root, 600, 500);
		stage.setScene(scene);
		stage.show();
	}
}
