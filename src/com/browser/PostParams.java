package com.browser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class PostParams {
    public static List<NameValuePair> getParas_login(String login_formid){
    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("userid", "hail"));  
        nvps.add(new BasicNameValuePair("password", "hai901009!")); 
        nvps.add(new BasicNameValuePair("submit", "Login")); 
        nvps.add(new BasicNameValuePair("formunqid", login_formid)); 
        
        return nvps;
    }
    
    public static List<NameValuePair> getParas_fall(){
    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("altPin", ""));  
        nvps.add(new BasicNameValuePair("endDatepicker", "")); 
        nvps.add(new BasicNameValuePair("startDatepicker", ""));
        nvps.add(new BasicNameValuePair("studyPath", ""));
        nvps.add(new BasicNameValuePair("studyPathText", ""));
        nvps.add(new BasicNameValuePair("term", "201608")); 
        
        return nvps;
    }
	
    public static List<NameValuePair> getParas_action(String RelayState,String SAMLResponse){
    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    	 nvps.add(new BasicNameValuePair("RelayState", RelayState));  
    	 nvps.add(new BasicNameValuePair("SAMLResponse", SAMLResponse));
    	return nvps;
    }
    
    public static List<NameValuePair> getParas_search(){
    	//term=201608&studyPath=&studyPathText=&altPin=&startDatepicker=&endDatepicker=
    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    	 nvps.add(new BasicNameValuePair("term", "201608"));  
    	 nvps.add(new BasicNameValuePair("studyPath", ""));
    	 nvps.add(new BasicNameValuePair("studyPathText", ""));
    	 nvps.add(new BasicNameValuePair("altPin", ""));
    	 nvps.add(new BasicNameValuePair("startDatepicker", ""));
    	 nvps.add(new BasicNameValuePair("endDatepicker", ""));
    	return nvps;
    }
	
}
