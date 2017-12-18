package severd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;


public class Serverthread extends Thread{
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	Dao dao;
	public Serverthread(Socket t) throws IOException {
		// TODO �Զ����ɵĹ��캯�����
		socket=t;
		in=new DataInputStream(socket.getInputStream());
		out=new DataOutputStream(socket.getOutputStream());
		dao=new Dao();
	}
	public void run() {
		while(true) {
			try {
				int i=in.readInt();
				String str1=in.readUTF();
				String str2=in.readUTF();
				System.out.println(str1+"\n"+str2);
				if(i==1) {
					if(dao.search(str1, str2)) {
						out.writeInt(1);
						System.out.println("yes");
					}
					else {
						out.writeInt(0);
						System.out.println("yes");
					}
				}
				else {
					if(dao.insert(str1, str2)) {
						out.writeInt(1);
						System.out.println("yes zhuce");
					}
					else {
						out.writeInt(0);
						System.out.println("yes");
					}
				}
			} catch (IOException | SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
