package fvarrui.ftoi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private MainController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new MainController();
		
		Scene escena = new Scene(controller.getView());
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("Formula To Image");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
