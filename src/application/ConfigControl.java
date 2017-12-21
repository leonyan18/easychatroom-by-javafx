package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigControl {
	static Stage sta;
	@FXML
	private TextField tex1;
	public void saveconfig() {
		data.you=tex1.getText();
		sta.close();
		System.out.println(data.you);
	}
}
