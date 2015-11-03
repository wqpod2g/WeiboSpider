package nju.iip.util;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nju.iip.dao.WeiboAccountDao;
import nju.iip.dto.WeiboAccount;

/**
 * 管理待爬取url池
 * @author wangqiang
 *
 */
public class UrlPool implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(UrlPool.class);
	
	private  BlockingQueue<String> urlQueue;
	
	public UrlPool(BlockingQueue<String> urlQueue) {
		this.urlQueue = urlQueue;
	}

	@Override
	public void run() {
		while(true) {
			logger.info("****************urlPool线程start！****************");
			getSpiderURL();
			logger.info("****************urlPool线程finish！****************");
			try{
				Thread.sleep(60*60*1000);
			}catch(Exception e) {
				logger.info("UrlPool run error!",e);
			}
		}
		
	}
	
	/**
	 * 获取待爬取的URL
	 */
	public void getSpiderURL() {
		WeiboAccountDao wad = new WeiboAccountDao();
		List<WeiboAccount> list = wad.getAccountlist();
		int n = 0;
		int url_sum = 0;//待爬取url计数器
		for(WeiboAccount wa:list) {
			String accountUrl = wa.getAccountUrl().trim();
			String url = "http://weibo.cn/"+accountUrl;
			int history = wa.getHistory();//全部爬取 or 增量爬取
			int sumPages = 0;
			//全部爬取
			if(history==0) {
				sumPages = HttpUtil.getPageNum(url);
				wad.updateHistory(accountUrl);//标记为已全部爬取
			}
			//增量爬取
			else {
				sumPages = Integer.valueOf(Config.getValue("addSpiderPages"));//增量爬取前5页
			}
			url_sum += sumPages;
			for(int i=1;i<=sumPages;i++) {
				urlQueue.offer(url+"?page="+i);//插入队列
			}
			logger.info((n++)+"#"+accountUrl+":add "+sumPages+" urls successful!");
		}
		logger.info("本次待爬取URL总数为："+url_sum);
	}
	
	
	public static void main(String[] args) {
		BlockingQueue<String> urlQueue = new LinkedBlockingQueue<String>();
		Thread pool = new Thread(new UrlPool(urlQueue));
		pool.start();
	}

}
