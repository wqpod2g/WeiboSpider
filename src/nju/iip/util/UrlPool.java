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
		WeiboAccountDao wad = new WeiboAccountDao();
		List<WeiboAccount> list = wad.getAccountlist();
		int n = 0;
		for(WeiboAccount wa:list) {
			String accountUrl = wa.getAccountUrl().trim();
			String url = "http://weibo.cn/"+accountUrl;
			int sumPages = HttpUtil.getPageNum(url);
			for(int i=1;i<=sumPages;i++) {
				urlQueue.offer(url+"?page="+i);//插入队列
			}
			logger.info((n++)+"#"+accountUrl+":add "+sumPages+" urls successful!");
		}
	}
	public static void main(String[] args) {
		BlockingQueue<String> urlQueue = new LinkedBlockingQueue<String>();
		Thread pool = new Thread(new UrlPool(urlQueue));
		pool.start();
	}

}
