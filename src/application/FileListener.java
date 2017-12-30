package application;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FileListener implements Runnable {
	private ServerSocket server =null;
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
            fout = new FileOutputStream(new File("D:\\test2\\"+din.readUTF()));
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
            String url = getClass().getResource("/resource/music.aac").toString();
			Media media = new Media(url);
			MediaPlayer player = new MediaPlayer(media);
			player.setAutoPlay(true);         //�����Զ�����
			player.setCycleCount(1);          //����ѭ�����Ŵ���
			//��Ƶ����(ͨ��д�ڿؼ�������)
			player.play();
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
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		try {
			server = new ServerSocket(33455);///
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
