/*
 * Copyright (c) 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.common.jobcollect;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

/**
 * Pipeline51Job action
 * 
 * @author 云计算应用与开发项目组
 * @since V2.0
 * 
 */
public class PipelineJob extends FilePersistentBase implements Pipeline {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 默认数据的存储路径 "/data/webmagic/"
	 */
	public PipelineJob() {
		setPath("/data/webmagic");
	}

	public PipelineJob(String path) {
		setPath(path);
	}

	@Override
	public void process(ResultItems resultItems, Task task) {

		try {
			// 定义存储路径，以每天为一个文件存储
			String path = this.path + PATH_SEPERATOR + timeTostrYMD(new Date()) + ".txt";
			File file = getFile(path);
			// Map<String,Object> map=new HashMap<>();
			// String id=resultItems.get("id");
			// map.put("id", id);
			// String date=resultItems.get("date");
			// change(date);
			// map.put("date", change(date));
			// String company=resultItems.get("company");
			// change(company);
			// map.put("company", change(company));
			// String nature=resultItems.get("nature");
			// change(nature);
			// map.put("nature", change(nature));
			// String scale=resultItems.get("scale");
			// change(scale);
			// map.put("scale", change(scale));
			// String industry=resultItems.get("industry");
			// change(industry);
			// map.put("industry", change(industry));
			// String jobname=resultItems.get("jobname");
			// change(jobname);
			// map.put("jobname", change(jobname));
			// String location=resultItems.get("location");
			// change(location);
			// map.put("location", change(location));
			// String experience=resultItems.get("experience");
			// change(experience);
			// map.put("experience", change(experience));
			// String salary=resultItems.get("salary");
			// change(salary);
			// map.put("salary", change(salary));
			// String amount=resultItems.get("amount");
			// change(amount);
			// map.put("amount", change(amount));
			// String education=resultItems.get("education");
			// change(education);
			// map.put("education", change(education));
			// String description=resultItems.get("description");
			// change(description);
			// map.put("description", change(description));
			// String category=resultItems.get("category");
			// change(category);
			// map.put("category", change(category));
			// String jobtag=resultItems.get("jobtag");
			// change(jobtag);
			// map.put("jobtag", change(jobtag));
			// String url=resultItems.get("url");
			// change(url);
			// map.put("url", change(url));
			// String resource=resultItems.get("resource");
			// change(resource);
			// map.put("resource", change(resource));
			// String hdfs="/data/douxue/insight/job/crawer/"+id+".html";
			// change(hdfs);
			// map.put("hdfs", change(hdfs));
			String str = resultItems.get("id") + "\t" + resultItems.get("date") + "\t" + resultItems.get("company")
					+ "\t" + resultItems.get("nature") + "\t" + resultItems.get("scale") + "\t"
					+ resultItems.get("industry") + "\t" + resultItems.get("jobname") + "\t"
					+ resultItems.get("location") + "\t" + resultItems.get("experience") + "\t"
					+ resultItems.get("salary") + "\t" + resultItems.get("amount") + "\t" + resultItems.get("education")
					+ "\t" + resultItems.get("description") + "\t" + resultItems.get("category") + "\t"
					+ resultItems.get("jobtag") + "\t" + resultItems.get("url") + "\t" + resultItems.get("resource")
					// + "\t"
					// + replaceBlank(delHTMLTag(resultItems.get("content")
					// .toString()))
					// +resultItems.get("nexturl")
					+ "\r\n";
			System.out.println(str);
			if (resultItems.get("id") != null) {
				FileUtils.writeStringToFile(file, str, "GBK", true);
			}
		} catch (IOException e) {
			logger.warn("write file error", e);
		}
	}

	public static String change(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		String regEx_html = "<[^>]+>";

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		return htmlStr.trim();
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String timeTostrYMD(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
			strDate = format.format(date);
		}
		return strDate;
	}
}