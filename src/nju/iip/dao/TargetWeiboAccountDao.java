package nju.iip.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nju.iip.dto.TargetWeiboAccount;
import nju.iip.util.CommonUtil;

/**
 * 待爬取用户账户信息数据持久化
 * @author wangqiang
 *
 */
public class TargetWeiboAccountDao extends DAO{
	
	private static final Logger logger = LoggerFactory.getLogger(TargetWeiboAccountDao.class);
	
	
	/**
	 * 持久化账户信息
	 * @param Accountlist
	 */
	public void saveAccount(List<TargetWeiboAccount> Accountlist) {
		try{
			begin();
			for(TargetWeiboAccount account:Accountlist) {
				getSession().save(account);
			}
			commit();
		}catch(Exception e) {
			rollback();
			logger.error("saveAccount failed!",e);
		}
	}
	
	public static void main(String[] args) {
		TargetWeiboAccountDao wa = new TargetWeiboAccountDao();
		wa.saveAccount(CommonUtil.importFromXls());
	}

}
