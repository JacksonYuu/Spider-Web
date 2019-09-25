/*
 * Copyright (c) 2017, 1DAOYUN and/or its affiliates. All rights reserved.
 * 1DAOYUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.xiandian.cloud.common.jobcollect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiandian.cloud.common.util.Format_transform;
import com.xiandian.cloud.common.util.ReadFile;
import com.xiandian.cloud.common.util.UtilTools;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 51job网络爬虫。 1. url configuration 2. Download HTML --> HDFS 3. RowID -->HBbase
 * |rowID|Raw Data (hdfs file path) | Tag Data(( Comp, Major Fields) |Flag )| 4.
 * Clean Data.
 * 
 * @since v1.0
 * @date 20170816
 * @author XuanHuiDong
 */
public class WYJobPageCrawler extends JobPageCrawler {
	private Logger logger = LoggerFactory.getLogger(getClass());
//	private JobDataReposity jobDataReposity = JobDataReposity.getInstance();
	private static final String HDFS = "hdfs";
	private static final String Suffix = ".html";
//	private Properties hadoopProperties = UtilTools.getConfig(System.getProperty("user.dir") + "/configuration/hadoop.properties");	
//	private Properties job51Properties = UtilTools.getConfig(System.getProperty("user.dir") + "/configuration/51job.properties");	
	
	// hdfs存储路径
//	private String job_rawdata_path = hadoopProperties.getProperty("job_rawdata_path");
	private Pattern pattern = Pattern.compile("/([0-9]+)\\.html");
	// 这些写法是否会改变？如果改变如何处理？放在配置表？保证服务不能停止。
	private String urlBreaks = "http://search.51job.com/list/000000,000000,0000,00,9,99,%2520,2,1.html?%22%20+%20%22lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&%22%20+%20%22companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&%22%20+%20%22fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

	// TODO: 如果51和智联的实现类似，不需要写2个爬虫类。
	/**
	 * 51Job的爬虫类。
	 */
	public WYJobPageCrawler() {
		// TODO:在这里读取配置，由于外部是每次New
	}
	/**
	 * 爬虫单个页面的处理方法
	 */
	@Override
	public void process(Page page) {
		// Init select and urls
		Selectable select = null;
		List<String> urls = null;
		// 如果是下一级别的内容
		if (page.getUrl().toString()
				.contains("search.51job.com")) {
			// ?
			select = page.getHtml().xpath("//p[@class='t1']");
			urls = select.links().all();
			page.addTargetRequests(urls);
			// ?
			select = page.getHtml().xpath("//div[@class='dw_page']");
			urls = select.links().all();
			// ?
			Iterator<String> it = urls.iterator();
			// ?
			while (it.hasNext()) {
				String x = it.next();
				if (x.equals(urlBreaks)) {
					it.remove();
				}
			}
			// 收集下一级的url
			page.addTargetRequests(urls);
		}
		// 岗位页面
		else if (page.getUrl().toString()
				.startsWith("http://jobs.51job.com/")) {
			try {
				Map<String, Object> map = new HashMap<>();
				// TODO: 每个页面不能重新读取配置，每次爬虫任务
				String JsonContext = ReadFile
						.ReadFile("D:\\job_config.json");
				JSONObject jsonObject = new JSONObject(JsonContext);
				JSONArray wbsites = jsonObject.getJSONArray("wbsites");
				for (int i = 0; i < wbsites.length(); i++) {
					JSONObject wbsite = wbsites.getJSONObject(i);
					String wbsitename = wbsite.getString("wbsitename");
					if (wbsitename.equals("51Job")) {
						// 设置来源
						map.put("resource", wbsitename);
						JSONArray fields = wbsite.getJSONArray("fields");
						for (int j = 0; j < fields.length(); j++) {
							JSONObject field = fields.getJSONObject(j);
							String chinesename = field.getString("chinesename");
							String name = field.getString("name");
							String path = field.getString("path");
							// ?
							if (path.startsWith("//")) {
								String objectStr = Format_transform.gb2312ToUtf8(page.getHtml().xpath(path).toString());
								map.put(name, Format_transform.change(objectStr));
							} else {
								String objectStr = Format_transform.gb2312ToUtf8(page.getHtml().regex(path).toString());
								logger.info(chinesename + ":" + objectStr);
								map.put(name, Format_transform.change(objectStr));
							}
							// ?
							if (name.equals("companymess")) {
								String companymess = Format_transform
										.gb2312ToUtf8(page.getHtml().xpath(path).toString());
								String[] strs = UtilTools.parseCompony(companymess);
								map.put("nature", Format_transform.change(strs[0]));
								map.put("scale", Format_transform.change(strs[1]));
								map.put("industry", Format_transform.change(strs[2]));
							}
						}
					}
				}
				// 设置url
				map.put("url", page.getUrl().toString());
				// 设置岗位ID
				Matcher matcher = pattern.matcher(page.getUrl().toString());
				String pageID = null;
				while (matcher.find()) {
					pageID = matcher.group(1);
				}
				// 如果没有ID，不处理
				if (pageID == null) {
					return;
				}
				// 网址是否已经爬取
				boolean isExist = false;

//				isExist = jobDataReposity.queryByTwoId("job_internet", "TAG_DATA", "ID", pageID);
				// 保存到HBase中，并设置结束日期为明天
				if (pageID != null && isExist == false) {
					map.put("id", pageID);
					// 需要常量
//					map.put(HDFS, job_rawdata_path + pageID + Suffix);
//					jobDataReposity.insert("job_internet", map);
//					DownloadHtml.Save(page.getUrl().toString(), job_rawdata_path);
				} else {
					// 找到这个岗位，设置它的结束日期为今天+1
//					jobDataReposity.insertEndTime("job_internet", map);
				}
			} catch (Exception exp) {
				logger.error(page.getUrl() + exp.toString());
			}
		}
	}
	public static void main(String[] args) {
		Spider spider;
		spider = Spider.create(new WYJobPageCrawler());
		List<String> wuyiJoburls = new ArrayList<>();
//		for (String str : wuyiJoburls) {
//			 spider.addUrl(str);
//		 }
		spider.addUrl("http://search.51job.com/list/070300,000000,0000,00,9,99,%2520,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=");
		// start 异步执行 (spider.run)
		spider.thread(5).setExitWhenComplete(true);
		spider.start();
	}
}
