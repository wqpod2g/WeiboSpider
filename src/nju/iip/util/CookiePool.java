package nju.iip.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nju.iip.spider.WeiboLogin;

/**
 * 产生cookie池
 * @author wangqiang
 * 
 */
public class CookiePool implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(CookiePool.class);

	private static BlockingQueue<String> cookieQueue = new LinkedBlockingQueue<String>();// 用于存放cookie的队列
	
	public static List<String>AccountList = new ArrayList<String>();//存放账户信息
	
	@Override
	public void run() {
		logger.info("****************cookiePool线程start！****************");
		while(true) {
			initializeCookiePool();
			try{
				Thread.sleep(10*60*1000);
			}catch(Exception e) {
				logger.error("cookiePool sleep error",e);
			}
		}
	}

	/**
	 * 获取一个cookie
	 * 
	 * @return
	 */
	public static String getCookie() {
		return cookieQueue.peek();
	}
	
	/**
	 * 将无效cookie插至队尾
	 */
	public static String changeCookie() {
		try{
			String cookie = cookieQueue.take();
			cookieQueue.offer(cookie);// 将该cookie插至队尾
		}catch(Exception e) {
			logger.error("changeCookie error",e);
		}
		return cookieQueue.peek();
	}

	/**
	 * 初始化cookie池
	 */
	public void initializeCookiePool() {
		try{
			int count = 0;
			BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/resources/WeiboAccount.data"));
			String s = "";
			while ((s = in.readLine()) != null) {
				if(!AccountList.contains(s)) {
					AccountList.add(s);
					count++;
					String account = s.split("#")[0];
					String password = s.split("#")[1];
					WeiboLogin wb = new WeiboLogin(account,password);
					String cookie = null;
					while(cookie==null) {
						cookie = wb.getCookies();
					}
					cookieQueue.offer(cookie);
				}
			}
			logger.info("add "+count+" cookies successful!");
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
