package application;

import java.awt.TextArea;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MyControllog {
	static  Stage stage;
	@FXML
	private TextField user;
	@FXML
	private Button login;
	public void tomain() throws IOException {
		if(user.getText().equals("121")) {
			System.out.println("1213");
			stage.close();
			stage=new Stage();
			Main main=new Main();
			main.start(stage);
		}
	}
}
