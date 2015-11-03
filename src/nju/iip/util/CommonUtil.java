package nju.iip.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jxl.Sheet;
import jxl.Workbook;
import nju.iip.dto.WeiboAccount;

public class CommonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	/**
	 * 从excel导入账户信息
	 */
	public static List<WeiboAccount> importFromXls()
	{
		List<WeiboAccount> Accountlist = new ArrayList<WeiboAccount>();
        Workbook workbook = null;
		try {
			workbook=Workbook.getWorkbook(new File(System.getProperty("user.dir")+"/resources/source.xls"));
		} catch (Exception e) {
			logger.error("importFromXls error!",e);
		}
		Sheet sheet=workbook.getSheet(1);
		int rowCount=sheet.getRows();
		for(int i=1;i<rowCount;i++)
		{
			String account = sheet.getCell(1,i).getContents().trim(); // 帐号
			String urlname=sheet.getCell(2, i).getContents().substring(17).replaceAll("u\\/", "");  // 域名:gaoyuanyuan
			WeiboAccount wa = new WeiboAccount(account,urlname);
			Accountlist.add(wa);
		}
		workbook.close();
		return Accountlist;
	}

}
