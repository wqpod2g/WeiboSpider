package nju.iip.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import nju.iip.spider.WeiboLogin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 产生cookie池
 * @author wangqiang
 * 
 */
public class CookiePool implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(CookiePool.class);

	private static BlockingQueue<String> cookieQueue = new LinkedBlockingQueue<String>();// 用于存放cookie的队列
	
	@Override
	public void run() {
		initializeCookiePool();
	}

	/**
	 * 获取一个cookie
	 * 
	 * @return
	 */
	public static String getCookie() {
		String cookie = null;
		try{
			cookie = cookieQueue.take();
			cookieQueue.offer(cookie);// 将该cookie插至队尾
		}catch(Exception e) {
			logger.error("getCookie error",e);
		}
		return cookie;
	}

	/**
	 * 初始化cookie池
	 */
	public void initializeCookiePool() {
		try{
			BufferedReader in = new BufferedReader(new FileReader("WeiboAccount.data"));
			String s = "";
			while ((s = in.readLine()) != null) {
				String account = s.split("#")[0];
				String password = s.split("#")[1];
				WeiboLogin wb = new WeiboLogin(account,password);
				String cookie = wb.getCookies();
				cookieQueue.offer(cookie);
			}
			in.close();
		}catch(Exception e) {
			logger.error("initializeCookiePool failed",e);
		}
	}
	
	/**
	 * 获取当前cookie总数
	 * @return
	 */
	public static int getCookiesNum() {
		return cookieQueue.size();
	}
	
	public static void main(String[] args) {
	}
}
