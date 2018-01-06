package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Message {
	static void sendmessage(String url,int port,String string) {
		byte buffer[]=(string).getBytes();
		try {
				InetAddress address = InetAddress.getByName(url);
				DatagramPacket data_pack=new DatagramPacket(buffer, buffer.length,address,port);
				DatagramSocket mail_data=new DatagramSocket();
				mail_data.send(data_pack);			
				mail_data.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e);
		} 
	}
	static String recmessage(int port) {
		DatagramPacket packet=null;
		DatagramSocket mail=null;
		byte data[]=new byte[8192];
		try {
			packet=new DatagramPacket(data, data.length);
			mail=new DatagramSocket(port);
		} catch (Exception e) {
			// TODO: handle exception
		}
		while (true) {
				try {
					mail.receive(packet);
					String message=null;
					message=new String(packet.getData(), 0, packet.getLength());
					System.out.println(message);
				} catch (Exception e) {
					// TODO: handle exception
				}					
		}
	}
}
