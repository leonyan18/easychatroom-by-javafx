package application;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MyControllog implements Initializable{
	private Socket socket=null;
	static  Stage stage;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Button login;
	@FXML
	private Label wropa,reacc,yes;
	public void tomain() throws IOException {
		DataInputStream in=new DataInputStream(socket.getInputStream());
		DataOutputStream out=new DataOutputStream(socket.getOutputStream());
		out.writeInt(1);
		out.writeUTF(user.getText());
		System.out.println(pass.getText());
		out.writeUTF(pass.getText());
		System.out.println(pass.getText());
		if(in.readInt()==1) {
			stage.close();
			stage=new Stage();
			Main main=new Main();
			main.start(stage);
		}
		else {
			double x=pass.getLayoutX();
			double y=pass.getLayoutY();
			pass.getStyleClass().add("wrong");
			user.getStyleClass().add("normal");
			Timeline timeline=new Timeline();
			timeline.getKeyFrames().addAll(  
	                new KeyFrame(Duration.ZERO, // set start position at 0  
	                    new KeyValue(pass.layoutXProperty(), x),  
	                    new KeyValue(pass.layoutYProperty(), y)  
	                ),  
	                new KeyFrame(new Duration(40), // set end position at 40s  
	                    new KeyValue(pass.layoutXProperty(), x+5),  
	                    new KeyValue(pass.layoutYProperty(), y+5) 
	                ) , 
	                new KeyFrame(new Duration(80), // set end position at 40s  
		                    new KeyValue(pass.layoutXProperty(), x-5),  
		                    new KeyValue(pass.layoutYProperty(), y-5) 
		            )  ,
	                new KeyFrame(new Duration(120), // set end position at 40s  
		                    new KeyValue(pass.layoutXProperty(), x),  
		                    new KeyValue(pass.layoutYProperty(), y) 
		            )  
	            );  
			timeline.play();
			yes.setOpacity(0);
			wropa.setOpacity(1.0);
			reacc.setOpacity(0);
		}
	}
	public void regist() throws IOException {
		DataInputStream in=new DataInputStream(socket.getInputStream());
		DataOutputStream out=new DataOutputStream(socket.getOutputStream());
		out.writeInt(0);
		out.writeUTF(user.getText());
		System.out.println(pass.getText());
		out.writeUTF(pass.getText());
		System.out.println(pass.getText());
		if(in.readInt()==1) {
			pass.getStyleClass().add("normal");
			user.getStyleClass().add("normal");
			yes.setOpacity(1.0);
			wropa.setOpacity(0);
			reacc.setOpacity(0);
		}
		else {
			double x=user.getLayoutX();
			double y=user.getLayoutY();
			pass.getStyleClass().add("normal");
			user.getStyleClass().add("wrong");
			Timeline timeline=new Timeline();
			timeline.getKeyFrames().addAll(  
	                new KeyFrame(Duration.ZERO, // set start position at 0  
	                    new KeyValue(user.layoutXProperty(), x),  
	                    new KeyValue(user.layoutYProperty(), y)  
	                ),  
	                new KeyFrame(new Duration(40), // set end position at 40s  
	                    new KeyValue(user.layoutXProperty(), x+5),  
	                    new KeyValue(user.layoutYProperty(), y+5) 
	                ) , 
	                new KeyFrame(new Duration(80), // set end position at 40s  
		                    new KeyValue(user.layoutXProperty(), x-5),  
		                    new KeyValue(user.layoutYProperty(), y-5) 
		            )  ,
	                new KeyFrame(new Duration(120), // set end position at 40s  
		                    new KeyValue(user.layoutXProperty(), x),  
		                    new KeyValue(user.layoutYProperty(), y) 
		            )  
	            );  
			timeline.play();
			yes.setOpacity(0);
			wropa.setOpacity(0);
			reacc.setOpacity(1.0);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 自动生成的方法存根
		socket=new Socket();
		if(!socket.isConnected()) {
			InetAddress address;
			try {
				address = InetAddress.getByName("192.168.0.140");
				InetSocketAddress socketAddress=new InetSocketAddress(address, 5555);
				socket.connect(socketAddress);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		pass.getStyleClass().add("normal");
		user.getStyleClass().add("normal");
	}
}
