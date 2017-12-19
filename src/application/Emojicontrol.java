package application;


import java.awt.Desktop.Action;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Emojicontrol {
	static FlowPane pane;
	@FXML
	private ImageView im1,im2,im3;
	public void send(MouseEvent event) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dateTimeString = df.format(now);
		if (event.getSource().toString().equals("ImageView[id=666, styleClass=image-view]")) {			
			settext("\t\t\t\t\t"+dateTimeString+"\t\t\t\t\t");
			sendimg(pane, "666");
			Message.sendmessage(data.you, 33454, "666");
			settext("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
			System.out.println(666);
		}
		if (event.getSource().toString().equals("ImageView[id=iu, styleClass=image-view]")) {
			settext("\t\t\t\t\t"+dateTimeString+"\t\t\t\t\t");
			sendimg(pane, "iu");
			Message.sendmessage(data.you, 33454, "iu");
			settext("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
			System.out.println("iu");
		}
		if (event.getSource().toString().equals("ImageView[id=6666, styleClass=image-view]")) {
			settext("\t\t\t\t\t"+dateTimeString+"\t\t\t\t\t");
			sendimg(pane, "6666");
			Message.sendmessage(data.you, 33454, "6666");
			settext("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
			System.out.println(6666);
		}
	}
	private void sendimg(Pane pane,String pic) {
		Image image =new Image("resource/"+pic+".jpg");
		ImageView imageView=new ImageView(image);
		imageView.resize(20, 20);
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
		System.out.println(1212);
	}
}
