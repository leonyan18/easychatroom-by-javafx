package application;

import java.io.File;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//656
public class MyControl  implements	 Initializable {
	static Stage stage;
	@FXML
	private Button myButton ,button1,min,drag,test;
	@FXML
	private TextArea text1, text2;
	@FXML
	private FlowPane pane;
	private double xOffset = 0;
    private double yOffset = 0;
    private Socket socket1;
	String name;
	int location1;
	public void initialize(URL location, ResourceBundle resources) {
		
	       // TODO (don't really need to do anything here).
		System.out.println(121);
		Emojicontrol.pane=pane;
		Messagelistenr mesl=new Messagelistenr();
		mesl.setpane(pane);
		Thread thread1=new Thread(mesl);	
		thread1.start();
		Emojilistener emojilistener=new Emojilistener();
		emojilistener.setpane(pane);
		Thread thread3=new Thread(emojilistener);
		thread3.start();
	   }
	public void showDateTime(ActionEvent event) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dateTimeString = df.format(now);	
		Message.sendmessage("127.0.0.1", 858, name+" "+text2.getText().trim()+"\n");
		Save.savechat("\t\t\t\t\t"+ dateTimeString + "\r\n" + text2.getText()  + "\r\n");
		// Show in VIEW
		if (!text2.getText().equals("")) {
			settext("\t\t\t\t\t"+ dateTimeString + "\n" + text2.getText()  + "\n");
//			text1.appendText("\t\t\t\t\t "+ dateTimeString + "\n" + text2.getText() + "\n"+"\n");
			text2.setText(null);
		}
	}
	private void settext(String string1) {
		Text text1=new Text(string1);
		text1.setFont(Font.font ("Verdana", 20));
		System.out.println(2121);
		if(pane==null) {
			System.out.println("No");
		}
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        //更新JavaFX的主线程的代码放在此处
		    	pane.getChildren().add(text1);
		    }
		});
		System.out.println(1212);
	}
	public void close() {
		Event.fireEvent(stage, new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST ));
	}
	public void minwind() {
		stage.setIconified(true);
	}
	public void drag(MouseEvent event) {
		event.consume();
		stage.setX(event.getScreenX() - xOffset);
		if (event.getScreenY() - yOffset < 0) {
				stage.setY(0);
		} else {
				stage.setY(event.getScreenY() - yOffset);
		}
	}
	public void press(MouseEvent event) {
		event.consume();
		xOffset = event.getSceneX();
		yOffset = event.getSceneY();
	}
	public void filesend() {
		FileChooser fileChooser = new FileChooser();
		File file=fileChooser.showOpenDialog(stage);
		Filesend send=new Filesend();
		send.setting( file,socket1);
		Thread thread3=new Thread(send);
		thread3.start();
	}
	public void listening() {	
		Message.sendmessage("127.0.0.1",959,"121");
	}
	public void sendemo() throws Exception {
		Emoji mEmoji=new Emoji();
		Stage estage=new Stage();
		mEmoji.start(estage);
	}
	
}
