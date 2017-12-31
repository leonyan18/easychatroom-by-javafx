package application;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//656
public class MyControl  implements	 Initializable {
	private static Stage stage;
	@FXML
	private Button myButton ,button1,min,drag,test;
	@FXML
	private TextArea text1, text2;
	@FXML
	private FlowPane pane;
	@FXML
	private AnchorPane backpane;
	@FXML
	private TextField textname;
	@FXML
	private ImageView img;
	private double xOffset = 0;
    private double yOffset = 0;
    private Socket socket1;
    private static String name="";
    private static String account="";
	private Socket socket;
	private static String headimg;
	public static void setStage(Stage stage) {
		MyControl.stage = stage;
	}
	public static void setName(String name) {
		MyControl.name = name;
	}
	public static void setAccount(String account) {
		MyControl.account = account;
	}
	public static String getaccount() {
		return account;
	}
	public static void setHeadimg(String headimg) {
		MyControl.headimg = headimg;
	}
	public void initialize(URL location, ResourceBundle resources) {
		
	       // TODO (don't really need to do anything here).
		System.out.println(121);
		Headcontrol.setHead(img);
		Emojicontrol.setPane(pane);
		Messagelistenr mesl=new Messagelistenr();
		mesl.setpane(pane);
		Thread thread1=new Thread(mesl);	
		thread1.start();
		Emojilistener emojilistener=new Emojilistener();
		emojilistener.setpane(pane);
		Thread thread3=new Thread(emojilistener);
		thread3.start();
		Imagelistenr imagelistenr=new Imagelistenr();
		imagelistenr.setpane(pane);
		Thread thread4=new Thread(imagelistenr);
		thread4.start();
		textname.setText(name);
		socket=new Socket();
		img.setImage(new Image("resource/"+headimg+".jpg"));
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
	   }
	public void showDateTime(ActionEvent event) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dateTimeString = df.format(now);	
		Message.sendmessage(Data.getYou(), 656, "from "+name+":"+text2.getText().trim()+"\n");
		System.out.println(name);
		Save.savechat("\t\t\t\t\t"+ dateTimeString + "\r\n" + text2.getText()  + "\r\n"); 
		// Show in VIEW
		if (!text2.getText().equals("")) {
			settext("\t\t\t\t\t"+ dateTimeString + "\n" + text2.getText()  + "\n");
//			text1.appendText("\t\t\t\t\t "+ dateTimeString + "\n" + text2.getText() + "\n"+"\n");
			text2.setText(null);
		}
	}
	public void changename() throws IOException {
		name=textname.getText();
		DataOutputStream out=new DataOutputStream(socket.getOutputStream());
		out.writeInt(2);
		out.writeUTF(account);
		out.writeUTF(name);
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
	public void close() throws InterruptedException {
		Timeline timeline=new Timeline();
		EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
        		Event.fireEvent(stage, new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST ));
            }
        };
		timeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO,
	                    new KeyValue(stage.opacityProperty(), 1.0)   
	                ),
				new KeyFrame(new Duration(500),onFinished,
	                    new KeyValue(stage.opacityProperty(), 0.0)   
	                )
				);
		timeline.play();
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
		send.setting(file);
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dateTimeString = df.format(now);
		settext("\t\t\t\t\t"+dateTimeString+"\t\t\t\t\t\n");
		settext("发送成功\n\t\t\t\t\t\t\t\t");
		Thread thread3=new Thread(send);
		thread3.start();
	}
	public void Record() {
		Recordmain recordmain=new Recordmain();
		Stage estage=new Stage();
		recordmain.start(estage);
	}
	public void sendemo() throws Exception {
		Emoji mEmoji=new Emoji();
		Stage estage=new Stage();
		mEmoji.start(estage);
	}
	public void picsend() throws FileNotFoundException, InterruptedException {
		FileChooser fileChooser = new FileChooser();
		 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File file=fileChooser.showOpenDialog(stage);
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dateTimeString = df.format(now);
		Text text1=new Text("\t\t\t\t\t"+dateTimeString+"\t\t\t\t\t");
		text1.setFont(Font.font ("Verdana", 20));
		pane.getChildren().add(text1);
		FileInputStream fis = new FileInputStream(file);  
		Image image=new Image(fis);
		ImageView imageView=new ImageView(image);
		imageView.resize(20, 20);
		imageView.setImage(image);
		pane.getChildren().add(imageView);		
		Imagesend imagesend=new Imagesend();
		imagesend.setting(file);
		Save.savechat("\t\t\t\t\t"+dateTimeString+"\n me :"+file.getName()+"\r\n");
		Thread thread3=new Thread(imagesend);
		thread3.start();
	}
	public void imgreplace() throws Exception  {
		Headimg headimg=new Headimg();
		Stage stage=new Stage();
		headimg.start(stage);
	}
	public static String getName() {
		// TODO 自动生成的方法存根
		return name;
	}
}
