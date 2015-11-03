package nju.iip.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nju.iip.dto.WeiboAccount;
import nju.iip.util.CommonUtil;

/**
 * 待爬取用户账户信息数据持久化
 * @author wangqiang
 *
 */
public class WeiboAccountDao extends DAO{
	
	private static final Logger logger = LoggerFactory.getLogger(WeiboAccountDao.class);
	
	
	/**
	 * 持久化账户信息
	 * @param Accountlist
	 */
	public void saveAccount(List<WeiboAccount> Accountlist) {
		try{
			begin();
			for(WeiboAccount account:Accountlist) {
				getSession().save(account);
			}
			commit();
		}catch(Exception e) {
			rollback();
			logger.error("saveAccount failed!",e);
		}
	}
	
	public void updateHistory(String accountUrl) {
		try{
			begin();
			Query q = getSession().createQuery("update WeiboAccount a set a.history = 1 where accountUrl = :accountUrl");
			q.setString("accountUrl", accountUrl);
			q.executeUpdate();
			commit();
		}catch(HibernateException e) {
			rollback();
    		logger.info("getAccountlist",e);
		}
	}
	
	/**
	 * 取出所有待爬取微博用户的信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WeiboAccount> getAccountlist() {
		List<WeiboAccount> list = null;
		try {
			begin();
			Query q = getSession().createQuery("from WeiboAccount");
			list=q.list();
		}catch (HibernateException e) {
    		rollback();
    		logger.info("getAccountlist",e);
		} 
		return list;
	}
	
	public static void main(String[] args) {
		WeiboAccountDao wa = new WeiboAccountDao();
		//wa.saveAccount(CommonUtil.importFromXls());
		wa.updateHistory("finance");
		System.out.println(wa.getAccountlist().size());
	}

}
