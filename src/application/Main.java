 package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private double xOffset = 0;
	private double yOffset = 0;
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/MyScene.fxml"));
            primaryStage.setTitle("My Application1");
            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            MyControl.stage=primaryStage;
            root.setOnMousePressed((MouseEvent event) -> {
			    event.consume();
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			});		
			root.setOnMouseDragged((MouseEvent event)->{
				event.consume();
				primaryStage.setX(event.getScreenX() - xOffset);
			
					//根据自己的需求，做不同的判断
				if (event.getScreenY() - yOffset < 0) {
				primaryStage.setY(0);
				} else {
				primaryStage.setY(event.getScreenY() - yOffset);
		        }
			}); 
			FileListener listener=new FileListener();
			Thread thread2=new Thread(listener);
			thread2.start();
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    System.out.print("监听到窗口关闭");
                    System.exit(0);
                }
            });

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {   	
        launch(args);
    }
}