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
	final double x1=156.0;
	final double y1=172.0;
	final double x2=156.0;
	final double y2=238.0;
	public void tomain() throws IOException {
		DataInputStream in=new DataInputStream(socket.getInputStream());
		DataOutputStream out=new DataOutputStream(socket.getOutputStream());
		out.writeInt(1);
		out.writeUTF(user.getText());
		out.writeUTF(pass.getText());
		if(in.readInt()==1) {
			MyControl.name=in.readUTF();
			MyControl.account=user.getText();
			stage.close();
			stage=new Stage();
			Main main=new Main();
			main.start(stage);
		}
		else {
			pass.getStyleClass().clear();
			user.getStyleClass().clear();
			pass.getStyleClass().add("wrong");
			user.getStyleClass().add("normal");
			Timeline timeline=new Timeline();
			timeline.getKeyFrames().addAll(  
	                new KeyFrame(Duration.ZERO,   
	                    new KeyValue(pass.layoutXProperty(), x2),  
	                    new KeyValue(pass.layoutYProperty(), y2)  
	                ),  
	                new KeyFrame(new Duration(40), 
	                    new KeyValue(pass.layoutXProperty(), x2+5),  
	                    new KeyValue(pass.layoutYProperty(), y2+5) 
	                ) , 
	                new KeyFrame(new Duration(80),  
		                    new KeyValue(pass.layoutXProperty(), x2-5),  
		                    new KeyValue(pass.layoutYProperty(), y2-5) 
		            )  ,
	                new KeyFrame(new Duration(120), 
		                    new KeyValue(pass.layoutXProperty(), x2),  
		                    new KeyValue(pass.layoutYProperty(), y2) 
		            )  
	            );  
			timeline.play();
			yes.setOpacity(0);
			wropa.setOpacity(1.0);
			reacc.setOpacity(0);
		}
	}
	public void config() throws Exception {
		stage=new Stage();
		Config config1=new Config();
		config1.start(stage);
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
			pass.getStyleClass().clear();
			user.getStyleClass().clear();
			pass.getStyleClass().add("normal");
			user.getStyleClass().add("normal");
			yes.setOpacity(1.0);
			wropa.setOpacity(0);
			reacc.setOpacity(0);
		}
		else {
			pass.getStyleClass().clear();
			user.getStyleClass().clear();
			pass.getStyleClass().add("normal");
			user.getStyleClass().add("wrong");
			Timeline timeline=new Timeline();
			timeline.getKeyFrames().addAll(  
	                new KeyFrame(Duration.ZERO,
	                    new KeyValue(user.layoutXProperty(), x1),  
	                    new KeyValue(user.layoutYProperty(), y1)  
	                ),  
	                new KeyFrame(new Duration(40), 
	                    new KeyValue(user.layoutXProperty(), x1+5),  
	                    new KeyValue(user.layoutYProperty(), y1+5) 
	                ) , 
	                new KeyFrame(new Duration(80), 
		                    new KeyValue(user.layoutXProperty(), x1-5),  
		                    new KeyValue(user.layoutYProperty(), y1-5) 
		            )  ,
	                new KeyFrame(new Duration(120),
		                    new KeyValue(user.layoutXProperty(), x1),  
		                    new KeyValue(user.layoutYProperty(), y1) 
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
