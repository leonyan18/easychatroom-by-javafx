package severd;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MYServer implements Runnable{
	public void run()  {
		ServerSocket server;
		try {
			System.out.println(13123);
			server = new ServerSocket(5555);
			Socket you=null;
			while(true) {
				you=server.accept();
				new Serverthread(you).start();
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
