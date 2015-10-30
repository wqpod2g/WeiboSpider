package nju.iip.dto;

/**
 * 待爬取微博用户信息实体类
 * @author wangqiang
 *
 */
public class WeiboAccount {
	
	private int id;
	private String accountName;//微博名
	private String accountUrl;//微博url
	private int history;//是否被爬过
	
	public WeiboAccount(String accountName,String accountUrl) {
		this.accountName = accountName;
		this.accountUrl = accountUrl;
	}
	
	public WeiboAccount() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountUrl() {
		return accountUrl;
	}
	public void setAccountUrl(String accountUrl) {
		this.accountUrl = accountUrl;
	}
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	

}
