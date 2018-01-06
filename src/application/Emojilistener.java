package application;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Emojilistener implements Runnable{
	private FlowPane pane;
	public void setpane(FlowPane pane) {
		this.pane=pane;
	}
	@Override
	public void run() {
		String message=null;
		int i=0;
		while(true) {
			System.out.println("第"+i+"ci");
			DatagramPacket packet=null;
			DatagramSocket mail=null;
			byte data[]=new byte[8192];
			try {
				packet=new DatagramPacket(data, data.length);
				mail=new DatagramSocket(33454);
			} catch (Exception e) {
				// TODO: handle exception
			}
			while (true) {
					try {
						mail.receive(packet);
						message=null;
						message=new String(packet.getData(), 0, packet.getLength());
						System.out.println(message);
						String imgloc=new String("application/"+message+".jpg");
						i++;
						Date now = new Date();
						DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
						String dateTimeString = df.format(now);
						settext("\t\t\t\t\t"+dateTimeString+"\t\t\t\t\t");
						setimg(imgloc);
						System.out.println(i);
						settext("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
					} catch (Exception e) {
						// TODO: handle exception
					}					
			}
		}
	}	
	private void setimg(String imgloc) {
		Image image =new Image(imgloc);
		ImageView imageView=new ImageView(image);
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        //更新JavaFX的主线程的代码放在此处
		    	pane.getChildren().add(imageView);
		    }
		});
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
		System.out.println("文本");
	}
}
