package application;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Messagelistenr implements Runnable{
	private FlowPane pane;
	public void setpane(FlowPane pane) {
		this.pane=pane;
	}
	public void run() {
		// TODO 自动生成的方法存根
			// TODO 自动生成的方法存根
			DatagramPacket packet=null;
			DatagramSocket mail=null;
			byte data[]=new byte[8192];
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			String dateTimeString = df.format(now);
			try {
				packet=new DatagramPacket(data, data.length);
				mail=new DatagramSocket(656);
			} catch (Exception e) {
				// TODO: handle exception
			}
			while (true) {
				if (mail==null) {
					break;
				}
				else {
					try {
						mail.receive(packet);
						String message=new String(packet.getData(), 0, packet.getLength());
						System.out.println(message);
						settext("\t\t"+dateTimeString+"\n"+message);
						Save.savechat("\t\t"+dateTimeString+"\r\n"+message+ "\r\n");
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e);
					}		
				}				
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
}
