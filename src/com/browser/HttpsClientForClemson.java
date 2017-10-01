package com.browser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
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
import com.sun.mail.util.CRLFOutputStream;
import com.util.JsonUtil;



public class HttpsClientForClemson{

	private static HttpContext localContext = null;
	static CookieStore cookie_use = DataCenter.cookieStore;
	static String COOKIE = "";
	List<Cookie> cookList = new ArrayList<Cookie>();

	DefaultHttpClient client = new DefaultHttpClient(new PoolingClientConnectionManager());
	HttpResponse response = null;
	HttpEntity he = null;
	RootFirst root = null;
	Root_Get_Course get_course = null;
	Root_Post_Course post_course = null;
	
	String token = null;
	
	private static HttpsClientForClemson instance = null;
	
	public HttpsClientForClemson(){
		
	}
	
	public static HttpsClientForClemson getInstance(){
		if(instance == null){
			instance = new HttpsClientForClemson();
			return instance;
		}else{
			return instance;
		}
	}
	
	public static void main(String args[]) throws Exception {

		HttpsClientForClemson hcc = HttpsClientForClemson.getInstance();
		hcc.loginClemson();
		hcc.getResource();
		hcc.runThread();

	}

	public void loginClemson() throws ClientProtocolException, IOException {

		localContext = new BasicHttpContext();
		cookie_use = new BasicCookieStore();
		client.setRedirectStrategy(new LaxRedirectStrategy());
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY) ;

		//client.setCookieStore(cookie_use);
		enableSSL(client);

		System.out.println("======================GET | init cookie - login !=========================");
		HttpGet get = new HttpGet(URLSet.urlLogin);
		HttpResponse responseget = client.execute(get);
		String login_forumid = Regular.getFormID(getEntityString(responseget));
		// print(responseget);

		// cookie_use.addCookie(responseget.get);

		
		System.out.println("======================POST | register cookie - login !=========================");

		HttpPost post = new HttpPost(URLSet.urlLogin);
		post.setEntity(new UrlEncodedFormEntity(PostParams.getParas_login(login_forumid)));
		//post.setHeader("Cookie", cookies);
		HttpResponse responsepost = client.execute(post);
		
		// print(responsepost);

		System.out.println("======================POST | redirct - login !=========================");
		HttpGet getregister = new HttpGet(URLSet.url_Register_Class);
		//getcookie.setHeader("Cookie", cookies);
		HttpResponse responsecookie = client.execute(getregister);

		int status = responsecookie.getStatusLine().getStatusCode();

		if (status == HttpStatus.SC_OK) {

			System.out.println("Deal With SSO Data !");
			String html = ResponseHandle(responsecookie);
			String action = URLSet.url_commonauth;
			String RelayState = Regular.getRelayState(html);
			String SAMLResponse = Regular.getSAMLResponse(html);
			System.out.println("action:" + action + " RelayState:" + RelayState + " SAMLResponse:" + SAMLResponse);

			HttpPost actionpost = new HttpPost(action);
			actionpost.setEntity(new UrlEncodedFormEntity(PostParams.getParas_action(RelayState, SAMLResponse)));

			HttpResponse redirectresp = client.execute(actionpost);

			html = ResponseHandle(redirectresp);
			
			//page of termSelection
			HttpGet gettoken = new HttpGet(URLSet.url_selection);
			HttpResponse tokenresp = client.execute(gettoken);
			html = ResponseHandle(tokenresp);
			token = Regular.getFallToken(html);
			System.out.println("token:"+token);
			
			int classRegistration =redirectresp.getStatusLine().getStatusCode();
			
			if(classRegistration==HttpStatus.SC_OK){
				
				
				
				HttpPost postsearch = new HttpPost(URLSet.urlRegister_fall);
				postsearch.setHeader("X-Synchronizer-Token", token);
				postsearch.setHeader("X-Requested-With", "XMLHttpRequest");
				
				postsearch.setEntity(new UrlEncodedFormEntity(PostParams.getParas_search()));
				HttpResponse responsefinal = client.execute(postsearch);
				html = ResponseHandle(responsefinal);
				
				System.out.println("====================================get the class page and store token===============================");
				HttpGet getClassPage = new HttpGet(URLSet.url_Register_Class);
				HttpResponse classResponse = client.execute(getClassPage);
				html = ResponseHandle(classResponse);
				token = Regular.getFallToken(html);
				System.out.println("token:"+token);
				
				//System.out.println(html);
				
					
			}
			
			/*Header[] h = redirectresp.getAllHeaders();
			for (int i = 0; i < h.length; i++) {

				System.out.println(h[i].getName() + ":" + h[i].getValue());
				if ("Set-Cookie".equals(h[i].getName()))
					cookies = cookies + ";" + h[i].getValue();
			}*/

		}

		
		System.out.println("======================end - login !=========================");
		//
	}

	public void handleRedirect() {

	}

	public void getResource() throws Exception {
		//
		getAllCourse(client);
		root = DataCenter.getRoot();
		if (root.getSuccess()) {
			//System.out.println("[class:getResource]" + root.getSuccess());
			List<Data> datas = root.getData();
			for (int i = 0; i < datas.size(); i++) {
				
				//===============================================Software Design=================================================
				if (76598 == datas.get(i).getId()) {
					if (!(0 == datas.get(i).getWaitAvailable())) {
						if(!CourseStatus.select_softwareDesign){
							
							//tap add button
							this.getCourseData(client, URLSet.url_get_softDesign);
							// submit
							if(get_course.getSuccess()){
								this.transferGetToPost(get_course);
								post_course.getUpdate().get(0).setSelectedAction("WL");
								this.postCourseData(client, post_course);
								CourseStatus.select_softwareDesign = true;
							}else{
								this.loginClemson();
							}
							
							System.out.println("software design :" + datas.get(i).getWaitAvailable());
						}
					}else{
						if(!CourseStatus.select_softwareDesign){
							System.out.println("software design :" + datas.get(i).getWaitAvailable());
						}
						
					}
				}
				
				
				//==========================================2-D Game Engine Contruction=========================================
				if (83273 == datas.get(i).getId()) {
					if (!(0 == datas.get(i).getSeatsAvailable())) {
						if(!CourseStatus.select_2dGameEngine){
							// add button 
							this.getCourseData(client, URLSet.url_get_2DgameEngine);
							// submit
							if(get_course.getSuccess()){
								this.transferGetToPost(get_course);
								this.postCourseData(client, post_course);
								CourseStatus.select_2dGameEngine = true;
							}else{
								this.loginClemson();
							}
							
							System.out.println("2D-game engine :" + datas.get(i).getSeatsAvailable());
						}
					}else{
						if(!CourseStatus.select_2dGameEngine){
							System.out.println("2D-game engine :" + datas.get(i).getSeatsAvailable());
						}
						
					}
				}
				
				/*
				//=========================================== Computer Security Principles=======================================
				if (76755 == datas.get(i).getId()) {
					if (!(0 == datas.get(i).getSeatsAvailable())) {
						if(!CourseStatus.select_SecurityPrinciple){
							// add button 
							this.getCourseData(client, URLSet.url_get_securityPrinciple);
							// submit
							if(get_course.getSuccess()){
								this.transferGetToPost(get_course);
								this.postCourseData(client, post_course);
								CourseStatus.select_SecurityPrinciple = true;
							}else{
								this.loginClemson();
							}
							
							System.out.println("Computer Security Principles :" + datas.get(i).getSeatsAvailable());
						}
					}else{
						System.out.println("Computer Security Principles :" + datas.get(i).getSeatsAvailable());
					}
				}
				
				
				//=============================================Database Management System==========================================
				if (73833 == datas.get(i).getId()) {
					if (!(0 == datas.get(i).getSeatsAvailable())) {
						if(!CourseStatus.select_databaseMange){
							// add button 
							this.getCourseData(client, URLSet.url_get_database);
							// submit
							if(get_course.getSuccess()){
								this.transferGetToPost(get_course);
								this.postCourseData(client, post_course);
								CourseStatus.select_databaseMange = true;
							}else{
								this.loginClemson();
							}
							
							System.out.println("Database Management Systems :" + datas.get(i).getSeatsAvailable());
						}
					}else{
						System.out.println("Database Management Systems :" + datas.get(i).getSeatsAvailable());
					}
				}
				
				//========================================================= Register Test ===========================================
				if (83266 == datas.get(i).getId()) {
					if (!(0 == datas.get(i).getSeatsAvailable())) {
						if(!CourseStatus.select_register){
							// add button 
							this.getCourseData(client, URLSet.url_get_generalPurpose);
							// submit
							this.transferGetToPost(get_course);
							this.postCourseData(client, post_course);
							CourseStatus.select_register = true;
							System.out.println("General purpose Computation on .. :" + datas.get(i).getSeatsAvailable());
						}
					}
				}
				
				//==================================================== Waitlist Test =================================================
				if (80579 == datas.get(i).getId()) {
					if (!(0 == datas.get(i).getWaitAvailable())) {
						if(!CourseStatus.select_waitlist){
							// add button 
							this.getCourseData(client, URLSet.url_get_waitlist);
							// submit
							this.transferGetToPost(get_course);
							post_course.getUpdate().get(0).setSelectedAction("WL");
							this.postCourseData(client, post_course);
							CourseStatus.select_waitlist = true;
							System.out.println("General purpose Computation on .. :" + datas.get(i).getSeatsAvailable());
						}
					}
				}*/
				
				
			}
		} else {
			this.loginClemson();
			System.out.println("[class:getResource] root fail");

		}
		System.out.println("======================================================================================================");
	}

	public void getAllCourse(DefaultHttpClient client) throws Exception {
		// client.setCookieStore(cookie_use);
		//System.out.println("[class:getAllCourse] come  into ");
		HttpGet get = new HttpGet(URLSet.url_GetAll_class);
		get.setHeader("X-Synchronizer-Token", token);
		get.setHeader("X-Requested-With", "XMLHttpRequest");
		/*CookieStore cs = client.getCookieStore();
		List<Cookie> cookies = cs.getCookies();
		cookList = switchCookies(cookies);
		
		cs.clear();

		for (Cookie c : cookList) {
			System.out.println(c.getName()+":"+c.getValue());
			cs.addCookie(c);
			//get.setHeader(c.getName(), c.getValue());
		}*/
		
		//client.setCookieStore(cs);
		response = client.execute(get);
		//print(response);

		String json_data = getEntityString(response);
		//System.out.println("[class:getAllCourse]" + json_data);
	
		JsonUtil json_to_java = new JsonUtil();
		RootFirst root = json_to_java.format(json_data, RootFirst.class);

		DataCenter.setRoot(root);
	}

	public void getCourseData(DefaultHttpClient client, String URLSet_course) throws Exception {
		String oldReplace = "\"class\"";
		String newReplace = "\"myclass\"";

		// this.postFall(client);

		HttpGet get_Courese_Data = new HttpGet(URLSet_course);
		get_Courese_Data.setHeader("X-Synchronizer-Token", token);
		get_Courese_Data.setHeader("X-Requested-With", "XMLHttpRequest");
		// get_Courese_Data.setHeader("X-Synchronizer-Token",
		// "b9d24d6f-242e-4483-b974-ce6a22a8f96a");
		//get_Courese_Data.setHeader("Cookie", COOKIE);
		//System.out.println(URLSet.url_get_test_course);
		response = client.execute(get_Courese_Data);

		// get the coure string and transfer the "class" to "myclass"

		String course_string_old = getEntityString(response);
		String course_string_new = course_string_old.replace(oldReplace, newReplace);
		JsonUtil json_to_java = new JsonUtil();
		get_course = json_to_java.format(course_string_new, Root_Get_Course.class);
	}
	

	public void transferGetToPost(Root_Get_Course course_get)
			throws IllegalAccessException, InvocationTargetException {
		post_course = new Root_Post_Course();
		List<Update> list_update = new ArrayList<Update>();
		Update update = new Update();
		post_course.setCreate(new ArrayList<Create>());
		post_course.setDestroy(new ArrayList<Destroy>());
		// get the data in Model
		BeanUtils.copyProperties(update, course_get.getModel());
		list_update.add(update);
		post_course.setUpdate(list_update);

	}
	
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


	public void postCourseData(DefaultHttpClient client, Root_Post_Course course_post)
			throws JsonGenerationException, JsonMappingException, IOException {
		String oldReplace = "\"myclass\"";
		String newReplace = "\"class\"";

		HttpPost post_course_data = new HttpPost(URLSet.url_post_submit_course);
		post_course_data.setHeader("X-Synchronizer-Token", token);
		post_course_data.setHeader("X-Requested-With", "XMLHttpRequest");
		JsonUtil json_to_java = new JsonUtil();
		String json_old = json_to_java.format(course_post);
		String json_new = json_old.replace(oldReplace, newReplace);
		he = new StringEntity(json_new);
		post_course_data.setEntity(he);
		client.execute(post_course_data);
	}

	private static void print(HttpResponse response) {

		Header[] h = response.getAllHeaders();

		for (int i = 0; i < h.length; i++) {

			System.out.println(h[i].getName() + ":" + h[i].getValue());
		}

		//System.out.println(ResponseHandle(response));
	}

	// transfer the entity to json data
	public static String getEntityString(HttpResponse httpResponse) {
		StringBuilder entityStringBuilder = new StringBuilder();
		String resultJsonString = "";
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			// 得到httpResponse的实体数据
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				try {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						entityStringBuilder.append(line);
					}
					// 利用从HttpEntity中得到的String生成JsonObject
					//System.out.print(entityStringBuilder.toString());
					resultJsonString = entityStringBuilder.toString();
					// resultJsonObject=new JSONObject();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultJsonString;
	}

	private static String ResponseHandle(HttpResponse response) {
		String result = null;
		try {

			InputStream inputstream = response.getEntity().getContent();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = inputstream.read()) != -1) {
				baos.write(i);
			}
			result = baos.toString();
			System.out.println(baos.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Cookie> switchCookies(List<Cookie> cookies) {

		Cookie jsessionid = null;

		for (Cookie c : cookies) {
			System.out.println(c.getName()+":"+c.getValue());
			
			switch (c.getName()) {
			case "BIGipServerregssb.bannerxe.clemson.edu_pool":
				cookList.add(c);
				break;
			case "CUTOKEN2":
				cookList.add(c);
				break;
			case "CUTOKENTNG":
				cookList.add(c);
				break;
			case "CUTOKEN_IDP":
				cookList.add(c);
				break;
			case "JSESSIONID": {
				if (jsessionid == null)
					jsessionid = c;
				else {
					if (c.getValue().length() > jsessionid.getValue().length())
						jsessionid = c;
				}
			}
				;
				break;
			default:
				break;
			}
		}
		cookList.add(jsessionid);
		return cookList;
	}

	private static void enableSSL(DefaultHttpClient httpclient) {
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

	private static TrustManager truseAllManager = new X509TrustManager() {
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public void runThread() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(3*1000);
				this.getResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
