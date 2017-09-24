package com.check.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;




public class FileUtil {
	

	
	public  static void Createir(String dir){
		File file = new File("D:/data/"+dir);
		if(!file.exists()){
			file.mkdir();
		}
	}


	
	public static File CreateTxt(int cno,int times) throws IOException, ParseException{
		File file = new File("D:/data/"+cno+"/"+times+".txt");
		if(!file.exists()){
			file.createNewFile();
		}
		return file;
	}
}
