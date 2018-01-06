package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	static public void savechat(String str) {
		File file = new File("D:/temp", "chatrecord.txt");  
        try {  
        	 File file1=new File("D:\\temp");
             if(!file1.exists()) {
             	file1.mkdirs();
             }
             if (!file.exists()){
            	    file.createNewFile();
            }
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        // 向文件写入内容(输出流)   
        try {  
        	 FileWriter writer = new FileWriter("D:/temp/chatrecord.txt", true); 
            try {  
                writer.write(str); 
                writer.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}
}
