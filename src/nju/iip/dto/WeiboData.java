package nju.iip.dto;

/**
 * 微博内容实体类
 * @author wangqiang
 *
 */
public class WeiboData {
	
	private int id;
	private String weiboUrl;
	private String author;//作者
	private String text;//正文
	private int loves;//赞 数量
	private int repost;//转发 数量
	private int comment;//评论
	private String postTime;//发布时间
	private String device;//发布设备
	private String crawlTime; //爬取时间
	
	
	public String getCrawlTime() {
		return crawlTime;
	}
	public void setCrawlTime(String crawlTime) {
		this.crawlTime = crawlTime;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getWeiboUrl() {
		return weiboUrl;
	}
	public void setWeiboUrl(String weiboUrl) {
		this.weiboUrl = weiboUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getLoves() {
		return loves;
	}
	public void setLoves(int loves) {
		this.loves = loves;
	}
	public int getRepost() {
		return repost;
	}
	public void setRepost(int repost) {
		this.repost = repost;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}

}
