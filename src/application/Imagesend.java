package application;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Imagesend implements Runnable{
	public static int flag;
	private File file=null;
	private Socket socket= null;
	public void setting(File file) {
		// TODO �Զ����ɵķ������
		this.file=file;
	}
	public void run() {
		// TODO �Զ����ɵķ������
		DataOutputStream dout= null;
		FileInputStream fin = null;
		byte[] sendByte= null;
		int length = 0;
		try {			
			try {
	         
	    		socket=new Socket();
	            try {
	            	InetAddress address=InetAddress.getByName(Data.getYou());
	    			socket.connect(new InetSocketAddress(address, 33456));
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