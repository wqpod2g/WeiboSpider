package nju.iip.spider;

import java.util.ArrayList;
import java.util.List;

import nju.iip.dto.WeiboData;
import nju.iip.util.CookiePool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微博爬虫
 * @author wangqiang
 *
 */
public class WeiboSpider {
	
	private static final Logger logger = LoggerFactory.getLogger(WeiboSpider.class);
	
	private static String cookie = CookiePool.getCookie();
	
	/**
	 * 根据url获取网页html
	 * @param url
	 * @return
	 */
	public String getHTML(String url) {
		String html = "";
		try {
			Document doc = Jsoup.connect(url)
					  .userAgent("Mozilla")
					  .cookie("Cookie",cookie)
					  .timeout(5000)
					  .get();
			html = doc.html();
		}catch(Exception e) {
			logger.error("getHTML error",e);
		}
		return html;
	}
	
	
	
	
	
	
	/**
	 * 解析当前页面所有微博
	 * @param html
	 * @return
	 */
	public List<WeiboData> parserWeibo(String html) {
		List<WeiboData> weibo_list = new ArrayList<WeiboData>();
		return weibo_list;
	}
	
	
	public static void main(String[] args) {
		WeiboSpider spider = new WeiboSpider();
		WeiboLogin wb = new WeiboLogin("wqchina007@126.com", "wqchina090513");
		cookie = wb.getCookies();
		System.out.println(cookie);
		System.out.println("html="+spider.getHTML("http://weibo.cn/gaoyuanyuan"));
	}

}
