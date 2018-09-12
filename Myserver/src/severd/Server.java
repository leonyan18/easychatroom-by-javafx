package severd;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Server extends Application {
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/severd/server.fxml"));
            primaryStage.setTitle("online");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    System.out.print("¼àÌýµ½´°¿Ú¹Ø±Õ");
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