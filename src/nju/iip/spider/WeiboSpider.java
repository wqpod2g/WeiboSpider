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

import nju.iip.BloomFilter.BloomFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(WeiboSpider.class);

	private BlockingQueue<String> urlQueue;
	
	private WeiboDataDao wdd = new WeiboDataDao();
	
	private int spiderID;

	public WeiboSpider(BlockingQueue<String> urlQueue,int spiderID) {
		this.urlQueue = urlQueue;
		this.spiderID = spiderID;
	}
	
	private BloomFactory bf = BloomFactory.getInstance();
	
	private static int sum = 0;//所有爬虫已爬取的微博数
	
	private int count = 0;

	@Override
	public void run() {
		logger.info("****************Spider线程start！****************");
		try {
			while (true) {
				if(urlQueue.size()==0) {
					logger.info("urlPool is Empty...");
				}
				String url = urlQueue.take();
				if (url != null) {
					String html = HttpUtil.getHTML(url);
					List<WeiboData> weibo_list = parserWeibo(html,url);
					count+= weibo_list.size();
					if(count>100) {
						synchronized(WeiboSpider.class) {
							sum = sum+count;
						}
						logger.info("already crawler weibo numbers:" + sum);
						count = 0;
						bf.saveBloomFilter();//持久化boolm过滤器
					}
					wdd.saveWeibo(weibo_list);
				}
			}
		} catch (Exception e) {
			logger.error("WeiboSpider error", e);
		}
		logger.info("****************"+spiderID+"号Spider线程stop！****************");
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
				String key = text.substring(0,Math.min(100, text.length()));
				//通过bloom过滤器判断当前微博是否存在
				if(bf.contains(key)) {
					continue;
				}
				else {
					bf.add(key);
				}
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
				weibo_list.add(data);
			}
		}
		return weibo_list;
	}

	public static void main(String[] args) {
		BlockingQueue<String> UrlQueue = new LinkedBlockingQueue<String>();
		CookiePool cookiePool = new CookiePool();//cookie池
		WeiboSpider spider1 = new WeiboSpider(UrlQueue,1);//爬虫任务1
		WeiboSpider spider2 = new WeiboSpider(UrlQueue,2);//爬虫任务2
		WeiboSpider spider3 = new WeiboSpider(UrlQueue,3);//爬虫任务3
		WeiboSpider spider4 = new WeiboSpider(UrlQueue,4);//爬虫任务4
		UrlPool urlPool = new UrlPool(UrlQueue);//url池
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(cookiePool);
		service.execute(urlPool);
		service.execute(spider1);
		service.execute(spider2);
		service.execute(spider3);
		service.execute(spider4);
	}

}
