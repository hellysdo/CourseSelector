package com.browser;

import org.json.simple.JSONObject;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "\"class\":\"123\",\"567\":\"891\",\"class\":\"999999\",\"class999\":\"999999\"";
		String oldReplace = "\"class\"";
		String newReplace = "\"myclass\"";
		String m = s.replace(oldReplace, newReplace);
		
		System.out.println(m);
		
		
		JSONObject postData = new JSONObject();
		
		postData.put("supervisor", "123");
		postData.put("content", "123");
		postData.put("userId", "5555");
		System.out.println(postData.toString());
	}

}
