package nju.iip.spider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import nju.iip.dao.WeiboDataDao;
import nju.iip.dto.WeiboData;
import nju.iip.util.CookiePool;
import nju.iip.util.HttpUtil;
import nju.iip.util.UrlPool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微博爬虫
 * @author wangqiang
 * 
 */
public class WeiboSpider implements Runnable {

	private static final Logger logger = LoggerFactory
			.getLogger(WeiboSpider.class);

	private BlockingQueue<String> urlQueue;

	public WeiboSpider(BlockingQueue<String> urlQueue) {
		this.urlQueue = urlQueue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String url = urlQueue.take();
				if (url != null) {
					String html = HttpUtil.getHTML(url);
					List<WeiboData> weibo_list = parserWeibo(html,url);
					logger.info("weibo_list_size=" + weibo_list.size());
					WeiboDataDao wdd = new WeiboDataDao();
					wdd.saveWeibo(weibo_list);
				}
			}
		} catch (Exception e) {
			logger.error("WeiboSpider error", e);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 解析当前页面所有微博
	 * 
	 * @param html
	 * @return
	 */
	public List<WeiboData> parserWeibo(String html,String url) {
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
		String currentYear = dateFormatYear.format(new Date());
		List<WeiboData> weibo_list = new ArrayList<WeiboData>();
		Document doc = Jsoup.parse(html);
		String author = "";
		String title = doc.title();
		if (title.length() > 3)
			author = title.substring(0, title.length() - 3);
		else
			author = title;
		Elements weibos = doc.select("div.c");
		for (Element weibo : weibos) {
			WeiboData data = new WeiboData();
			String text = weibo.getElementsByClass("ctt").text();// 微博正文
			if (text != null && text.length() != 0) {
				String reg = "转发理由";
				Elements cmtEle = weibo.select("div:contains(" + reg + ")");
				if (cmtEle.size() != 0) {
					text = cmtEle.text().replaceAll("赞\\[\\d+\\].+", "")
							+ " 原文:" + text;
				}
				String device = weibo.getElementsByClass("ct").text();// 来自某某设备
				String time = "";// 发文时间
				int p = device.indexOf("来");
				if (p > 0) {
					time = device.substring(0, p);
					device = device.substring(p);
				}
				if (time.contains("分钟前")) {
					int n = Integer.parseInt(time.substring(0,
							time.indexOf("分钟前")).trim());
					Date date = new Date(System.currentTimeMillis() - n * 60000);
					time = sdf.format(date).toString();
				} else if (time.contains("今天")) {
					time = time.substring(3, 8);
					DateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
					String currentTime = Format.format(new Date());
					time = currentTime.toString() + " " + time + ":00";
				} else if (time.contains("月")) {
					String month = time.substring(0, 2);
					String day = time.substring(3, 5);
					String hourAndMinute = time.substring(7, 12) + ":00";
					time = currentYear + "-" + month + "-" + day + " "
							+ hourAndMinute;
				}
				int attitude = 0, repost = 0, comment = 0;
				Elements urlEles = weibo.select("a[href]");
				for (Element temp : urlEles) {
					if (temp.text().contains("原文"))
						continue;
					String u = temp.absUrl("href");
					if (u.contains("attitude")) {
						String in = temp.text();
						in = in.substring(2, in.length() - 1);
						attitude = Integer.parseInt(in);
					}
					if (u.contains("repost")) {
						String in = temp.text();
						int pos = in.indexOf("[");
						in = in.substring(pos + 1, in.length() - 1);
						repost = Integer.parseInt(in);
					}
					if (u.contains("comment")) {

						String in = temp.text();
						int pos = in.indexOf("[");
						in = in.substring(pos + 1, in.length() - 1);
						comment = Integer.parseInt(in);
					}
				}
				data.setAuthor(author);
            	data.setBaseUrl(url);
            	data.setComment(comment);
            	data.setDevice(device);
            	data.setLoves(attitude);
            	data.setRepost(repost);
            	data.setText(text);
            	data.setTime(time);
            	data.setInputTime(sdf.format(new Date()));
//            	logger.info("author="+author);
//            	logger.info("BaseUrl="+url);
//            	logger.info("comment="+comment);
//            	logger.info("attitude="+attitude);
//            	logger.info("text="+text);
//            	logger.info("time="+time);
//            	logger.info("device="+device);
				weibo_list.add(data);
			}
		}
		return weibo_list;
	}

	public static void main(String[] args) {
		BlockingQueue<String> UrlQueue = new LinkedBlockingQueue<String>();
		CookiePool cookiePool = new CookiePool();//cookie池
		WeiboSpider spider = new WeiboSpider(UrlQueue);//爬虫任务
		UrlPool urlPool = new UrlPool(UrlQueue);//url池
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(cookiePool);
		service.execute(urlPool);
		service.execute(spider);
	}

}