package application;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Imagelistenr implements Runnable{
	private ServerSocket server =null;
	private Pane pane;
	public void setpane(FlowPane pane) {
		this.pane=pane;
	}
    public  void receiveFile(Socket socket) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream din = null;
        FileOutputStream fout = null;
        try {
            din = new DataInputStream(socket.getInputStream());
            File file1=new File("D:\\test2");
            if(!file1.exists()) {
            	file1.mkdirs();
            }
            File file2=new File("D:\\test2\\"+din.readUTF());
            fout = new FileOutputStream(file2);
            inputByte = new byte[1024];
            System.out.println("��ʼ��������...");
            while (true) {
                if (din != null) {
                    length = din.read(inputByte, 0, inputByte.length);
                }
                if (length == -1) {
                    break;
                }
                System.out.println(length);
                fout.write(inputByte, 0, length);
                fout.flush();
            }
            System.out.println("��ɽ���");
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			String dateTimeString = df.format(now);
			settext("\t\t\t\t\t"+dateTimeString+"\t\t\t\t\t");
			Save.savechat("\t\t\t\t\t"+dateTimeString+"\r\n"+file2.getName()+ "\r\n");
			setimg(file2);
			String url = getClass().getResource("/resource/music.aac").toString();
			Media media = new Media(url);
			MediaPlayer player = new MediaPlayer(media);
			player.setAutoPlay(true);         //�����Զ�����
			player.setCycleCount(1);          //����ѭ�����Ŵ���
			//��Ƶ����(ͨ��д�ڿؼ�������)
			player.play();
			settext("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null)
                fout.close();
            if (din != null)
                din.close();
            if (socket != null)
                socket.close();
        }
    }
    private void setimg(File file) throws FileNotFoundException {
    	FileInputStream fis = new FileInputStream(file);  
		Image image=new Image(fis);
		ImageView imageView=new ImageView(image);
		imageView.resize(20, 20);
		imageView.setImage(image);
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        //����JavaFX�����̵߳Ĵ�����ڴ˴�
		    	pane.getChildren().add(imageView);
		    }
		});
	}
	private void settext(String string1) {
		Text text1=new Text(string1);
		text1.setFont(Font.font ("Verdana", 20));
		text1.setFill(Color.RED);
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
	}
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			server = new ServerSocket(33456);///
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}

         while (true) {
                try {
                    System.out.println("��ʼ����������");
                    Socket socket = server.accept();
                    System.out.println("������");
                    receiveFile(socket);
                } catch (Exception e) {
                     e.printStackTrace();
                }
         }
	}
}