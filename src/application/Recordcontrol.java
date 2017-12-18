package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class Recordcontrol implements Initializable{
	@FXML
	private TextArea text1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根
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
        try {
			FileReader in1=new FileReader("D:/temp/chatrecord.txt");
			BufferedReader in2=new BufferedReader(in1);
			String str=null;
			str=in2.readLine();
			while(str!=null){
				text1.setText(text1.getText()+str+"\n");
				str=in2.readLine();
			}
			System.out.println(str);
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
