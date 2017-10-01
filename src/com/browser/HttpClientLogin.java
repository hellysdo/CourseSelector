package com.browser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.pojo.Create;
import com.pojo.Data;
import com.pojo.Destroy;
import com.pojo.RootFirst;
import com.pojo.Root_Get_Course;
import com.pojo.Root_Post_Course;
import com.pojo.Update;
import com.util.JsonUtil;

public class HttpClientLogin implements Runnable{
	

	private static HttpContext localContext = null;
	
	
	static CookieStore cookie_use = DataCenter.cookieStore;
	static String COOKIE="";
	DefaultHttpClient client = null;
	HttpResponse response = null;
	HttpEntity he = null;
	RootFirst root = null;
	Root_Get_Course get_course = null;
	Root_Post_Course post_course = null;
	
	
	
	public static void main(String args[]) throws Exception{
		HttpClientLogin ht = new HttpClientLogin();
		ht.getResoucesByLoginCookies();
	}
	
	//start to select course
    public void getResoucesByLoginCookies() throws Exception
    {
        client = new DefaultHttpClient(new PoolingClientConnectionManager());
        localContext = new BasicHttpContext();
        cookie_use = new BasicCookieStore();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookie_use);
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY,CookiePolicy.IGNORE_COOKIES);
        //client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        enableSSL(client);
        this.firstStep(client);
        
        //1、get the data
        if(cookie_use == null){
        	//login and connect
        	this.firstStep(client);
        	this.getAllCourse(client);
        }else{
        	this.getAllCourse(client);
        	root = DataCenter.getRoot();
            if(root.getSuccess()){
            	System.out.println(root.getSuccess());
            	List<Data> datas = root.getData();
            	for(int i =0; i< datas.size(); i++){
            		if(76598 == datas.get(i).getId()){
            			if(!("0".equals(datas.get(i).getWaitAvailable()))){
            				// register for the course
            				//1、add button， get the data from remote center
            				
            				//2、submit the data to the remote center
            				
            				
            			}
            			System.out.println("software design :"+ datas.get(i).getWaitAvailable());
            		}
            		if(83273 == datas.get(i).getId()){
            			if(!("0".equals(datas.get(i).getSeatsAvailable()))){
            				// register for the course
            				
            			}
            			System.out.println("2D-game engine :"+ datas.get(i).getSeatsAvailable());
            		}
            		if(76755 == datas.get(i).getId()){
            			if(!("0".equals(datas.get(i).getSeatsAvailable()))){
            				// register for the course
            				
            			}
            			System.out.println("Computer Security Principles :"+ datas.get(i).getSeatsAvailable());
            		}
            		if(73833 == datas.get(i).getId()){
            			if(!("0".equals(datas.get(i).getSeatsAvailable()))){
            				// register for the course
            				
            			}
            			System.out.println("Database Management Systems :"+ datas.get(i).getSeatsAvailable());
            			
            		}
            		if(80569 == datas.get(i).getId()){
            			if(!("0".equals(datas.get(i).getSeatsAvailable()))){
            				// register for the course
            				
            				//get the course data first
            				get_course = this.getCourseData(client);
            				post_course = this.transferGetToPost(get_course);
            				this.postCourseData(client, post_course);
            			}
            			System.out.println("General purpose Computation on .. :"+ datas.get(i).getSeatsAvailable());
            		}
            	}
            }else{
            	this.firstStep(client);
            }
        }
    }
    	
    //call ssl
    private static void enableSSL(DefaultHttpClient httpclient){   
         try {  
                SSLContext sslcontext = SSLContext.getInstance("TLS");  
                sslcontext.init(null, new TrustManager[] { truseAllManager }, null);  
                SSLSocketFactory sf = new SSLSocketFactory(sslcontext);  
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
                Scheme https = new Scheme("https", sf, 443);  
                httpclient.getConnectionManager().getSchemeRegistry().register(https);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
    }  
    
    
    // call ssl manager
    private static TrustManager truseAllManager = new X509TrustManager(){  
        public void checkClientTrusted(  
                java.security.cert.X509Certificate[] arg0, String arg1)  
                throws CertificateException {  
        }  
        public void checkServerTrusted(  
                java.security.cert.X509Certificate[] arg0, String arg1)  
                throws CertificateException {  
        }  
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
    }; 
    
    
    
    // transfer the entity to json data 
    public static String getEntityString (HttpResponse httpResponse){
    	StringBuilder entityStringBuilder=new StringBuilder();
    	String resultJsonString="";
    	int statusCode=httpResponse.getStatusLine().getStatusCode();
		if (statusCode==HttpStatus.SC_OK) {
			//得到httpResponse的实体数据
			HttpEntity httpEntity=httpResponse.getEntity();
			if (httpEntity!=null) {
				try {
					BufferedReader bufferedReader=new BufferedReader
					(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8*1024);
				    String line=null;
					while ((line=bufferedReader.readLine())!=null) {
						entityStringBuilder.append(line);
					}
					//利用从HttpEntity中得到的String生成JsonObject
					System.out.print(entityStringBuilder.toString());
					resultJsonString = entityStringBuilder.toString();
					//resultJsonObject=new JSONObject();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultJsonString;
    }
    
    
    // this part can be removed 
    
    public void setCookieStore(HttpResponse httpResponse) {
    	BasicClientCookie cookie = null;
    	HeaderElementIterator it = new BasicHeaderElementIterator(  
    			httpResponse.headerIterator("Set-Cookie")); 
    	
    	while (it.hasNext()) {  
    	    HeaderElement elem = it.nextElement(); 
    	    if(cookie == null){
        		cookie = new BasicClientCookie(elem.getName(),elem.getValue());
        		cookie.setAttribute("AUTHURL", "https://idp.clemson.edu/idp/Authn/RemoteUser");
        		cookie.setAttribute("AUTHREASON", "Please login...");
        	}else{
        		cookie.setAttribute(elem.getName(), elem.getValue());
        	}
    	    NameValuePair[] params = elem.getParameters();  
    	    for (int i = 0; i < params.length; i++) {
    	    	if(cookie != null&&(params[i].getValue() != null)){
    	    		cookie.setAttribute(params[i].getName(), params[i].getValue());
    	    	}
    	    }  
    	}
        // JSESSIONID
        //Header[] head = httpResponse.getAllHeaders();
        
        /*String setCookie = httpResponse.getFirstHeader("Set-Cookie")
            .getValue();*/
       /* String XSynchronizerToken = httpResponse.getFirstHeader("X-Synchronizer-Token")
                .getValue();
        System.out.println("XSynchronizerToken:" + XSynchronizerToken);*/

        /*String CUTOKENTNG = (head[6].getValue()).substring("CUTOKENTNG=".length(),
        		(head[6].getValue()).indexOf(";"));
        System.out.println("CUTOKENTNG:" + CUTOKENTNG);
        
        String CUTOKEN_IDP = (head[7].getValue()).substring("CUTOKEN_IDP=".length(),
        		(head[7].getValue()).indexOf(";"));
        String CUTOKEN2 = (head[8].getValue()).substring("CUTOKEN2=".length(),
        		(head[8].getValue()).indexOf(";"));*/
        // 新建一个Cookie
        
        //cookie.setAttribute("CUTOKENTNG", CUTOKENTNG);
        
        //generate cookie header string
        //generateCookieHeaderString(CUTOKENTNG, CUTOKEN_IDP, CUTOKEN2);
        //cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
        //cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
        //cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
        // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
        cookie_use.addCookie(cookie);
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookie_use);
        DataCenter.setCookieStore(cookie_use);
      }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(5*1000);
				this.getResoucesByLoginCookies();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//try to login
	
	public void firstStep(DefaultHttpClient client) throws ClientProtocolException, IOException{
		//request to the class page and get the location
		//get the formuniqid  ——get method
   	 	HttpGet get_login = new HttpGet(URLSet.url_Register_Class);
   	 	response = client.execute(get_login);
   	 	
   	 	CookieStore cookieStore1 = (CookieStore)localContext.getAttribute(ClientContext.COOKIE_STORE);
   	 	//if 302, we should redirect
   	 	//this.handleRedirect(response, client);
        String login_forumid =Regular.getFormID(getEntityString(response));
        
        //login to clemson  ——post method
        HttpPost post = new HttpPost(URLSet.urlLogin);
        post.setEntity(new UrlEncodedFormEntity(PostParams.getParas_login(login_forumid)));
        response = client.execute(post);
        
        Header[] hs =response.getAllHeaders();
        //
		System.out.println("======================final login !=========================");
		for(int i = 0;i<hs.length;i++){
			
		System.out.println(hs[i].getName()+":"+hs[i].getValue());
			
		}
		System.out.print(getEntityString(response));
        
		System.out.print(getEntityString(response));
        //
        
        COOKIE = response.getFirstHeader("Set-cookie").getValue();
        //setCookieStore(response);
        
        //post fall
        this.postFall(client);
	}
	
	//get the fall token
	
	public String getFallToken(DefaultHttpClient client) throws ClientProtocolException, IOException{
		client.setCookieStore(cookie_use);
		HttpGet get = new HttpGet(URLSet.url_get_fall_token);
		response = client.execute(get);
		String ssss = cookie_use.getCookies().get(2).getValue();
		System.out.println(response);
		String fall_token = Regular.getFallToken(getEntityString(response));
		return fall_token;
	}
	
	// register the fall term 
	
	public void postFall(DefaultHttpClient client) throws ClientProtocolException, IOException{
		//get falltoken
		//String fall_token = this.getFallToken(client);
		
		//select the 2016 fall semester ——post method
        //client.setCookieStore(cookie_use);
        HttpPost postAgain = new HttpPost(URLSet.urlRegister_fall);
        //postAgain.setHeader("X-Synchronizer-Token", fall_token);
        postAgain.setEntity(new UrlEncodedFormEntity(PostParams.getParas_fall()));
        response = client.execute(postAgain);
        //setCookieStore(response);
        System.out.println(getEntityString(response));
	}
	
	//get the html page --------------token
	public void getTokenHtmlPage(DefaultHttpClient client) throws ClientProtocolException, IOException{
		client.setCookieStore(cookie_use);
		HttpGet get = new HttpGet(URLSet.url_get__course);
		response = client.execute(get);
		String json_data = getEntityString(response);
		System.out.println(json_data);
	}
	
	
	//get all the course data 
	
	public void getAllCourse(DefaultHttpClient client) throws Exception{
		//client.setCookieStore(cookie_use);
        HttpGet get = new HttpGet(URLSet.url_GetAll_class);
        response = client.execute(get);
        String json_data = getEntityString(response);
        System.out.println(json_data);
        JsonUtil json_to_java = new JsonUtil();
        RootFirst root =  json_to_java.format(json_data, RootFirst.class);
        
        DataCenter.setRoot(root);
	}
	
	//get the course data you want to register 
	
	public Root_Get_Course getCourseData (DefaultHttpClient client) throws Exception{
		String oldReplace = "\"class\"";
		String newReplace = "\"myclass\"";
		
		//this.postFall(client);
		client.setCookieStore(cookie_use);
		HttpGet get_Courese_Data = new HttpGet(URLSet.url_get_softDesign);
		//get_Courese_Data.setHeader("X-Synchronizer-Token", "b9d24d6f-242e-4483-b974-ce6a22a8f96a");
		response = client.execute(get_Courese_Data);
		
		//get the coure string and transfer the "class" to "myclass"
		
		String course_string_old = getEntityString(response);
		String course_string_new = course_string_old.replace(oldReplace, newReplace);
		JsonUtil json_to_java = new JsonUtil();
        Root_Get_Course root_get_course =  json_to_java.format(course_string_new, Root_Get_Course.class);
		return root_get_course;
	}
	
	//register the course you want to submit
	
	public void postCourseData(DefaultHttpClient client, Root_Post_Course course_post) throws JsonGenerationException, JsonMappingException, IOException{
		String oldReplace = "\"myclass\"";
		String newReplace = "\"class\"";
		
		client.setCookieStore(cookie_use);
		HttpPost post_course_data = new HttpPost(URLSet.url_post_submit_course);
		JsonUtil json_to_java = new JsonUtil();
		String json_old = json_to_java.format(course_post);
		String json_new = json_old.replace(oldReplace, newReplace);
		he = new StringEntity(json_new);
		post_course_data.setEntity(he);
		client.execute(post_course_data);
	}
	
	public Root_Post_Course transferGetToPost(Root_Get_Course course_get) throws IllegalAccessException, InvocationTargetException{
		Root_Post_Course course_post = new Root_Post_Course();
		List<Update> list_update = new ArrayList<Update>();
		Update update = new Update();
		course_post.setCreate(new ArrayList<Create>());
		course_post.setDestroy(new ArrayList<Destroy>());
		//get the data in Model
		BeanUtils.copyProperties(update, course_get.getModel());
		list_update.add(update);
		course_post.setUpdate(list_update);
		return course_post;
		
	}
	
	public static void generateCookieHeaderString(String CUTOKENTNG, String CUTOKEN_IDP, String CUTOKEN2){
		String jsessionID = "";
		String BIGipSer = "BIGipServerregssb.bannerxe.clemson.edu_pool=1025643530.43039.0000;";
		String authURL = "AUTHURL=https://idp.clemson.edu/idp/Authn/RemoteUser;";
		String authreason = "AUTHREASON=Please login...;";
		
		DataCenter.COOKIE = "JSESSIONID="+jsessionID+";"+BIGipSer+authURL+authreason+"CUTOKENTNG="+CUTOKENTNG+";"+"CUTOKEN_IDP="+CUTOKEN_IDP+";"+"CUTOKEN2="+CUTOKEN2+";";
		
	}
	
	public void handleRedirect(HttpResponse response_redir, DefaultHttpClient client) throws ClientProtocolException, IOException{
		//if 302, execute get method 
		if(response_redir.getStatusLine().getStatusCode()==302){
			String redirectURL = response_redir.getFirstHeader("Location").getValue();
			//store cookie and execute get
			this.setCookieStore(response_redir);
			HttpGet get_location = new HttpGet(redirectURL);
			HttpResponse redir_responsere = client.execute(get_location, localContext);
			
			//
			Header[] hs =redir_responsere.getAllHeaders();
			System.out.println("======================redirect start !=========================");
			for(int i = 0;i<hs.length;i++){
				
			System.out.println(hs[i].getName()+":"+hs[i].getValue());
				
			}
			System.out.print(getEntityString(response_redir));
			//
			
			this.handleRedirect(redir_responsere, client);
			
		}else if(response_redir.getStatusLine().getStatusCode()==200){
			this.response = response_redir;
			
		     System.out.print(getEntityString(response_redir));
		}
	}
	

}