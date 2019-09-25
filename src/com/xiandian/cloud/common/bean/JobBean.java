package com.xiandian.cloud.common.bean;

public class JobBean {

	// 岗位名称
	private String jobname;
	// 公司名称
	private String name;
	// 工作地点
	private String city;
	// 工作薪资
	private String wages;
	//发布时间
	private String createtime;
	//存储地点
	private String html;
	
	public JobBean() {
		
	}
	public JobBean(String jobname, String name, String city, String wages, String createtime, String html) {
		super();
		this.jobname = jobname;
		this.name = name;
		this.city = city;
		this.wages = wages;
		this.createtime = createtime;
		this.html = html;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWages() {
		return wages;
	}
	public void setWages(String wages) {
		this.wages = wages;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}

}
