package application;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Filesend implements Runnable{
	public static int flag;
	File file=null;
	String url;
	Socket socket= null;
	public void setting(File file,Socket socket) {
		// TODO �Զ����ɵķ������
		this.file=file;
		this.socket=socket;
	}
	public void run() {
		// TODO �Զ����ɵķ������
		DataOutputStream dout= null;
		FileInputStream fin = null;
		byte[] sendByte= null;
		int length = 0;
		try {			
			try {
	            Message.sendmessage("127.0.0.1",858,"/t/t/t�Է������㷢�����ļ� ���ڱ���D:\\test2����");
	            
	    		socket=new Socket();
	            try {
	    			socket.connect(new InetSocketAddress("", 33456),10 * 1000);
	    		} catch (IOException e) {
	    			// TODO �Զ����ɵ� catch ��
	    			e.printStackTrace();
	    		}
	            dout = new DataOutputStream(socket.getOutputStream());
	            fin = new FileInputStream(file);
	            sendByte = new byte[1024];
	            dout.writeUTF(file.getName());
	            while((length = fin.read(sendByte, 0, sendByte.length))>0){
	                dout.write(sendByte,0,length);
	                dout.flush();
	            }
	            } catch (Exception e) {

	            } 	
	             if (dout != null)
	                   dout.close();
	             if (fin != null)
	                   fin.close();
	             if (socket != null)
	                   socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
