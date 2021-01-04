package com.base;


import com.base.util.PropertySupport;
import org.springframework.context.ConfigurableApplicationContext;

public class ConstantsCode {

	public static  String WELCOME_IMG = "welcome@sys";//欢迎页
	public static  String OAUTH_LOGIN = "oauthLogin@sys";//第三方登录开关
	public static  String SERVER_URL = PropertySupport.getProperty("server.url");
	public static  String SERVER_PUSH_URL = SERVER_URL+"ws/send";
	public static  String API_URL = PropertySupport.getProperty("api.url");
	public static  String ICON_URL = SERVER_URL + "download?id=";
	public static  String SERVICE_CACHE = "serviceCache";
	public static  String PROJECT_NAME = PropertySupport.getProperty("project.name");
	public static  boolean MULTI_LOGIN = "true".equals(PropertySupport.getProperty("api.login.multi"))?true:false;

	public static  String FFMPEG_PATH = PropertySupport.getProperty("ffmpeg.path","");

	public static  boolean DEBUG = "true".equals(PropertySupport.getProperty("api.debug"))?true:false;
	public static  boolean AJAX_DEBUG = "true".equals(PropertySupport.getProperty("api.ajax.debug"))?true:false;
	public static  String ENCRYPT = PropertySupport.getProperty("api.encrypt","DES");

	public static  String JKS_PATH = PropertySupport.getProperty("jks.path");
}
