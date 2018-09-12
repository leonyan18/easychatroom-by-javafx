package severd;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigControl {
	private static Stage sta;
	@FXML
	private TextField text1,text2,text3;
	public void saveconfig() {
		Dao.setUrl("jdbc:mysql://localhost:3306/"+text1.getText());
		Dao.setUser(text2.getText());
		Dao.setPassword(text3.getText());
		sta.close();
	}
	public static void setSta(Stage sta) {
		ConfigControl.sta = sta;
	}
}
