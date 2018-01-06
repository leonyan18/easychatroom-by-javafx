package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Emoji extends Application{

	@Override
	public void start(Stage emostage) throws Exception {
		// TODO 自动生成的方法存根
		Parent root = FXMLLoader.load(getClass().getResource("/application/emoji.fxml"));
		emostage.setTitle("");
		emostage.setScene(new Scene(root));
		emostage.show();
	}
}
