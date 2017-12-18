package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import junit.framework.Test;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class login extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/main.fxml"));
			Scene scene = new Scene(root);
			MyControllog.stage=primaryStage;
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("resource/batman.jpg"));  
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
