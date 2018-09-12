package severd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;


public class Serverthread extends Thread{
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	private Dao dao;
	public Serverthread(Socket t) throws IOException {
		// TODO 自动生成的构造函数存根
		socket=t;
		in=new DataInputStream(socket.getInputStream());
		out=new DataOutputStream(socket.getOutputStream());
		dao=new Dao();
	}
	public void run() {
		while(true) {
			if(socket.isClosed()) {
				break;
			}
			try {
				int i=in.readInt();
				String str1=in.readUTF();
				String str2=in.readUTF();
				System.out.println(str1+"\n"+str2);
				if(i==1) {
					if(dao.search3(str1, str2)!=null) {
						out.writeInt(1);
						out.writeUTF(dao.search3(str1, str2));
						out.writeUTF(dao.search4(str1, str2));
					}
					else {
						out.writeInt(0);
					}
				}
				else if (i==0){
					if(dao.insert(str1, str2)) {
						out.writeInt(1);
					}
					else {
						out.writeInt(0);
					}
				}
				else if(i==2){
					dao.update1(str1, str2);				
				}
				else {
					dao.update2(str1, str2);
				}
			} catch (IOException | SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				break;
			}
		}
	}
}
