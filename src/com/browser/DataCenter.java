package com.browser;

import org.apache.http.client.CookieStore;

import com.pojo.RootFirst;

public class DataCenter {
	public static CookieStore cookieStore = null;
	
	public static String COOKIE;
	
	public static CookieStore getCookieStore() {
		return cookieStore;
	}

	public static void setCookieStore(CookieStore cookieStore) {
		DataCenter.cookieStore = cookieStore;
	}

	public static RootFirst getRoot() {
		return root;
	}

	public static void setRoot(RootFirst root) {
		DataCenter.root = root;
	}

	public static RootFirst root = null;
	
}
