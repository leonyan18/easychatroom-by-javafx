package application;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Headcontrol implements Initializable{
	private static ImageView head;
	private static Stage stage;
	public static void setHead(ImageView head) {
		Headcontrol.head = head;
	}
	public static void setStage(Stage stage) {
		Headcontrol.stage = stage;
	}
	private Socket socket;
	public void set(MouseEvent event) throws IOException {
		socket=new Socket();
		if(!socket.isConnected()) {
			InetAddress address;
			try {
				address = InetAddress.getByName(Data.getServer());
				InetSocketAddress socketAddress=new InetSocketAddress(address, 5555);
				socket.connect(socketAddress);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		DataOutputStream out=new DataOutputStream(socket.getOutputStream());
		if (event.getSource().toString().equals("ImageView[id=666, styleClass=image-view]")) {			
			Image image=new Image("resource/666.jpg");
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			        //更新JavaFX的主线程的代码放在此处
			    	head.setImage(image);
			    }
			});
			out.writeInt(3);
			out.writeUTF(MyControl.getaccount());
			out.writeUTF("666");
		}
		if (event.getSource().toString().equals("ImageView[id=iu, styleClass=image-view]")) {
			Image image=new Image("resource/iu.jpg");
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			        //更新JavaFX的主线程的代码放在此处
			    	head.setImage(image);
			    }
			});
			out.writeInt(3);
			out.writeUTF(MyControl.getaccount());
			out.writeUTF("iu");
		}
		if (event.getSource().toString().equals("ImageView[id=6666, styleClass=image-view]")) {
			Image image=new Image("resource/6666.jpg");
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			        //更新JavaFX的主线程的代码放在此处
			    	head.setImage(image);
			    }
			});
			out.writeInt(3);
			out.writeUTF(MyControl.getaccount());
			out.writeUTF("6666");
		}
		stage.close();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根
		System.out.println(1231212);
	}
}
