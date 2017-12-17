package application;

import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Scope;

public class MyControllog implements Initializable{
	private Socket socket=null;
	static  Stage stage;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Button login;
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
			System.out.println("wrong");
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO �Զ����ɵķ������
		socket=new Socket();
		if(!socket.isConnected()) {
			InetAddress address;
			try {
				address = InetAddress.getByName("127.0.0.1");
				InetSocketAddress socketAddress=new InetSocketAddress(address, 5555);
				socket.connect(socketAddress);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
