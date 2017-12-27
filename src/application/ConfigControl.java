package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigControl {
	static Stage sta;
	@FXML
	private TextField tex1,tex2;
	public void saveconfig() {
		Data.setYou(tex1.getText());
		Data.setServer(tex2.getText());
		sta.close();
	}
}
