package application;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Messagelistenr implements Runnable{
	private FlowPane pane;
	public void setpane(FlowPane pane) {
		this.pane=pane;
	}
	public void run() {
		// TODO �Զ����ɵķ������
			// TODO �Զ����ɵķ������
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
						Text text1=new Text("\t\t\t\t\t"+dateTimeString+"\n"+message);
						text1.setFont(Font.font ("Verdana", 20));
						text1.setFill(Color.RED);
						Platform.runLater(new Runnable() {
						    @Override
						    public void run() {
						        //����JavaFX�����̵߳Ĵ�����ڴ˴�
						    	pane.getChildren().add(text1);
						    }
						});
						String url = getClass().getResource("/resource/music.aac").toString();
						Media media = new Media(url);
						MediaPlayer player = new MediaPlayer(media);
						player.setAutoPlay(true);         //�����Զ�����
						player.setCycleCount(1);          //����ѭ�����Ŵ���
						//��Ƶ����(ͨ��д�ڿؼ�������)
						player.play(); 
						Save.savechat("\t\t\t\t\t"+dateTimeString+"\r\n"+message+ "\r\n");
						
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
		        //����JavaFX�����̵߳Ĵ�����ڴ˴�
		    	pane.getChildren().add(text1);
		    }
		});
		System.out.println(1212);
	}
}
