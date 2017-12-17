package severd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	String driver="com.mysql.jdbc.Driver";  
    String url="jdbc:mysql://localhost:3306/yan";  
    String user="root";  
    String password="1183503933yan";
    Connection conn=null;
    public Dao() {
		// TODO 自动生成的构造函数存根
    	 try {  
             Class.forName(driver);  
             //System.out.println("驱动程序加载成功");  
         } catch (ClassNotFoundException e) {  
             e.printStackTrace();  
         }  
           
         //链接数据库  
         try {  
             conn=DriverManager.getConnection(url, user, password);  
             if (!conn.isClosed()) {  
                 System.out.println("连接数据库成功");  
             }  
         } catch (SQLException e) {  
             System.out.println("链接数据库失败: "+e.getMessage());  
         }  
	}
    public  boolean search(String str1,String str2) throws SQLException {
    	 PreparedStatement sql;
    	 ResultSet rs;
    	 sql=conn.prepareStatement("SELECT * FROM user");
    	 rs=sql.executeQuery();
    	 while(rs.next()) {
    		 if(rs.getString(1).equals(str1)) {
    			 if(rs.getString(2).equals(str2))
    				 return true;
    		 }
    	 }
    	 return false;
	} 
    public  boolean insert(String str1,String str2) throws SQLException {
	   	 PreparedStatement sql;
	   	 ResultSet rs;
	   	 sql=conn.prepareStatement("SELECT * FROM user");
	   	 rs=sql.executeQuery();
	   	 while(rs.next()) {
	   		 if(rs.getString(1).equals(str1)) {
	   				 return false;
	   		 }
	   	 }
	   	 sql=conn.prepareStatement("INSERT INTO user VALUES("+str1+","+str2+")");
	   	 sql.execute();
	   	 return true;
    }
    public static void main(String[] args) throws SQLException {
		Dao op1=new Dao();
		if(op1.insert("1212", "123")) {
			System.out.println("success");
		}
		else {
				System.out.println(0);
		}
		if (op1.search("121", "123")) {
			System.out.println("success");
		}
		else {
			System.out.println(0);
		}
	}
}
