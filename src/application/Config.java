package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Config extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自动生成的方法存根
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/config.fxml"));
			Scene scene = new Scene(root);
			ConfigControl.sta=primaryStage;
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("resource/gray1.jpg"));  
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
