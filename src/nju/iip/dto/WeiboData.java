package nju.iip.dto;

/**
 * 微博内容实体类
 * @author wangqiang
 *
 */
public class WeiboData {
	
	private int id;
	private String baseUrl;//微博所在页面链接
	private String author;//作者
	private String text;//正文
	private int loves;//赞 数量
	private int repost;//转发 数量
	private int comment;//评论
	private String time;//发布时间
	private String device;//发布设备
	private String inputTime; //爬取时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

}
