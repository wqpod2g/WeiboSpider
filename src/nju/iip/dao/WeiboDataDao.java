package nju.iip.dao;

import java.util.List;
import nju.iip.dto.WeiboData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微博数据持久化
 * @author wangqiang
 *
 */
public class WeiboDataDao extends DAO{
	
	private static final Logger logger = LoggerFactory.getLogger(WeiboDataDao.class);
	
	public void saveWeibo(List<WeiboData> weibo_list) {
		try{
			begin();
			for(WeiboData weibo:weibo_list) {
				getSession().save(weibo);
			}
			commit();
			getSession().flush();
			getSession().clear(); 
		}catch(Exception e) {
			rollback();
			logger.error("saveWeibo failed!",e);
		}
	}

}
