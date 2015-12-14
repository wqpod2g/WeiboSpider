package nju.iip.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private static String cookie;
	
	private static int times = 0;

	/**
	 * 判断当前页是否被反爬
	 * 
	 * @param doc
	 * @return
	 */
	public static boolean JudgeAnti(Document doc) {
		if (doc.text().contains("微博盗链举报")|| doc.title().trim().contains("帐号异常")) {
			logger.info("***************AntiCrawler detected***************");
			return true;
		}
		return false;
	}
	
	/**
	 * 根据url获取网页html
	 * @param url
	 * @return
	 */
	public static String getHTML(String url) {
		String html = "";
		while(cookie==null) {
			cookie = CookiePool.getCookie();
		}
		try {
			Document doc = Jsoup.connect(url)
					  .userAgent("Mozilla")
					  .cookie("Cookie",cookie)
					  .timeout(5000)
					  .get();
			//检测到被反爬
			if(JudgeAnti(doc)) {
				times++;
				if(times>CookiePool.getCookiesNum()) {
					logger.info("the sum of cookies are:"+CookiePool.getCookiesNum());
					logger.info("***************begin sleep!!!!***************");
					Thread.sleep(20*60*1000);
					times = 0;
				}
				cookie = CookiePool.changeCookie();//更换cookie
				logger.info("***************new cookie is:"+cookie);
				html = getHTML(url);
			}
			else {
				html = doc.html();
				times = 0;
			}
		}catch(Exception e) {
			logger.error("getHTML error",e);
		}
		return html;
	}

	/**
	 * 获取当前微博用户总页数
	 * @param doc
	 * @return
	 */
	public static int getPageNum(String url) {
		int sumPages = 0;
		Document doc = Jsoup.parse(getHTML(url));
		Element e = doc.select("input[name=mp]").first();
		if (e != null) {
			String value = e.attr("value");
			sumPages = Integer.valueOf(value);
		}
		else{
			sumPages = -1;
			logger.error("getPageNum error",e);
		}
		return sumPages;
	}
	
	public static void main(String[] args) {
		CookiePool pool = new CookiePool();
		new Thread(pool).start();
		cookie = CookiePool.getCookie();
		System.out.println(cookie);
		System.out.println("html="+getPageNum("http://weibo.cn/gaoyuanyuan"));
	}

}
