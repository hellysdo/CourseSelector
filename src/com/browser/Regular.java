package com.browser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular {
	
	public static void main(String[] args){
	}
	
	public static String getFormID(String line){
		String result = "";
		String pattern = "(<input type=\"hidden\" name=\'formunqid\'				value=\")(\\S+)(\" />)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find( )) {
	         result = m.group(2);
	      } else {
	         System.out.println("FormID NO MATCH");
	      }		
		return result;
		
	}
	
	public static String getFallToken(String line){
		String result = "";
		String pattern = "(<meta name=\"synchronizerToken\" content=\")(\\S+)(\"/>)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find( )) {
	         result = m.group(2);
	      } else {
	         System.out.println("FallToken NO MATCH");
	      }		
		return result;
		
	}
	
	public static String getAction(String line){
		String result = "error";
		String pattern = "(<form action=\")(\\S+)(\" method=\"post\"/>)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find( )) {
	         result = m.group(2);
	      } else {
	         System.out.println("Action NO MATCH");
	      }		
		return result;
		
	}
	
	public static String getRelayState(String line){
		String result = "error";
		String pattern = "(<input type=\"hidden\" name=\"RelayState\" value=\")(\\S+)(\"/>)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find( )) {
	         result = m.group(2);
	      } else {
	         System.out.println("RelayState NO MATCH");
	      }		
		return result;
		
	}
	
	public static String getSAMLResponse(String line){
		String result = "error";
		String pattern = "(<input type=\"hidden\" name=\"SAMLResponse\" value=\")(\\S+)(\"/>)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find( )) {
	         result = m.group(2);
	      } else {
	         System.out.println("SAMLResponse NO MATCH");
	      }		
		return result;
		
	}

}
