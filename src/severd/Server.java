package severd;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(5555);
		Socket you=null;
		while(true) {
			you=server.accept();
			System.out.println("yes");
			new Serverthread(you).start();
		}
	}
}
