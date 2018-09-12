package severd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	private	static String driver="com.mysql.jdbc.Driver";  
	private static String url="jdbc:mysql://localhost:3306/yan";  
	private static String user="root";  
	private static String password="yanchenxi";
	private static Connection conn=null;
	public static void setUrl(String url) {
		Dao.url = url;
	}
	public static void setUser(String user) {
		Dao.user = user;
	}
	public static void setPassword(String password) {
		Dao.password = password;
	}
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
    public  String search3(String str1,String str2) throws SQLException {
    	 PreparedStatement sql;
    	 ResultSet rs;
    	 sql=conn.prepareStatement("SELECT * FROM user");
    	 rs=sql.executeQuery();
    	 while(rs.next()) {
    		 if(rs.getString(1).equals(str1)) {
    			 if(rs.getString(2).equals(str2))
    				 return rs.getString(3);
    		 }
    	 }
    	 return null;
	} 
    public  String search4(String str1,String str2) throws SQLException {
   	 PreparedStatement sql;
   	 ResultSet rs;
   	 sql=conn.prepareStatement("SELECT * FROM user");
   	 rs=sql.executeQuery();
   	 while(rs.next()) {
   		 if(rs.getString(1).equals(str1)) {
   			 if(rs.getString(2).equals(str2))
   				 return rs.getString(4);
   		 }
   	 }
   	 return null;
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
	   	 String str3="";
	   	 sql=conn.prepareStatement("INSERT INTO user VALUES("+str1+","+str2+","+"1231"+",'iu')");
	   	 sql.execute();
	   	 return true;
    }
    public void update1(String str1,String str2) throws SQLException {
    	 PreparedStatement sql;
	   	 ResultSet rs;
	   	 sql=conn.prepareStatement("UPDATE user SET name = '"+str2+"' WHERE account ='"+str1+"'");
	   	 sql.execute();
	}
    public void update2(String str1,String str2) throws SQLException {
   	 PreparedStatement sql;
	   	 ResultSet rs;
	   	 sql=conn.prepareStatement("UPDATE user SET head = '"+str2+"' WHERE account ='"+str1+"'");
	   	 sql.execute();
	}
    public static void main(String[] args) throws SQLException {
		Dao op1=new Dao();
		if(op1.insert("12132", "123")) {
			System.out.println("success");
		}
		else {
				System.out.println(0);
		}
		if (op1.search3("121", "123")!=null) {
			System.out.println("success");
		}
		else {
			System.out.println(0);
		}
	}
}
