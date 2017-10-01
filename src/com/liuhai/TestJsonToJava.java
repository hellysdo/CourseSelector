package com.liuhai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.pojo.RootFirst;
import com.util.JsonUtil;

public class TestJsonToJava {
	public static void main(String args[]){
		File file = new File("C:/json.txt");
		String json_data = txt2String(file);
		//System.out.println(json_data);
		JsonUtil json_to_java = new JsonUtil();
		try {
			RootFirst root =  json_to_java.format(json_data, RootFirst.class);
			System.out.println(root.getSuccess());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static String txt2String(File file){
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result = result + "\n" +s;
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
	
}
