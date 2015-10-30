package nju.iip.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import nju.iip.dto.TargetWeiboAccount;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	/**
	 * 判断当前页是否被反爬
	 * 
	 * @param doc
	 * @return
	 */
	public static boolean JudgeAnti(Document doc) {
		if (doc.title().equals("微博") || getPageNum(doc) == -1
				&& doc.text().contains("微博盗链举报!")
				|| doc.title().trim().contains("帐号异常")) {
			logger.info("AntiCrawler detected.");
			return true;
		}
		return false;
	}
	

	/**
	 * 获取当前微博用户总页数
	 * @param doc
	 * @return
	 */
	public static int getPageNum(Document doc) {
		int sumPages = 0;
		Element e = doc.select("input[name=mp]").first();
		if (e != null) {
			String value = e.attr("value");
			sumPages = Integer.valueOf(value);
		} else {
			sumPages = -1;
		}
		return sumPages;
	}
	
	
	/**
	 * 从excel导入账户信息
	 */
	public static List<TargetWeiboAccount> importFromXls()
	{
		List<TargetWeiboAccount> Accountlist = new ArrayList<TargetWeiboAccount>();
        Workbook workbook = null;
		try {
			workbook=Workbook.getWorkbook(new File("source.xls"));
		} catch (Exception e) {
			logger.error("importFromXls error!",e);
		}
		Sheet sheet=workbook.getSheet(1);
		int rowCount=sheet.getRows();
		for(int i=1;i<rowCount;i++)
		{
			String account = sheet.getCell(1,i).getContents().trim(); // 帐号
			String urlname=sheet.getCell(2, i).getContents().substring(17).replaceAll("u\\/", "");  // 域名:gaoyuanyuan
			TargetWeiboAccount wa = new TargetWeiboAccount(account,urlname);
			Accountlist.add(wa);
		}
		workbook.close();
		return Accountlist;
	}

}
