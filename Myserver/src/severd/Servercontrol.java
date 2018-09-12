package severd;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Servercontrol {
	@FXML
	private Text text;
	public void config() throws Exception {
		Stage stage=new Stage();
		Config con=new Config();
		con.start(stage);
		stage.show();
	}
	public void start() {
		 MYServer server=new MYServer();
         Thread thread=new Thread(server);
         thread.start();
         text.setText("服务器正在运行");
         text.setFill(Color.GREEN);
	}
}
