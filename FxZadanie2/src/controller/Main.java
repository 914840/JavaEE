package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.PaintSquare;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	public void mainWindow() {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindowView.fxml"));
		try {
			AnchorPane pane = loader.load();
			Pane paneCube = new Pane();

			primaryStage.setMinHeight(500.0); // wysokoœæ
			primaryStage.setMinWidth(650.0); // szerokoœæ
			Scene scene = new Scene(pane);
			MainWindowController mainWindowController = loader.getController();

			// metoda w MainWindowController
			mainWindowController.setMain(this);
			mainWindowController.setPane(paneCube);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO obs³uga wyj¹tku
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
