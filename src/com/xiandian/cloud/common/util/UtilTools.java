/*
 * Copyright (c) 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 工具类，提供公共方法
 * 
 * @author XuanHuiDong
 * @since V2.0
 * 
 */
public class UtilTools {

	/**
	 * 配置缓存文件。
	 */
	private static HashMap<String, Properties> propertiesMap= new HashMap<String, Properties>();
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceStr(String str) {
		str = str.replaceAll("%2F", "/");
		return str;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String converStr(String str) {
		return converStr(str, "UTF-8");
	}

	/**
	 * 
	 * @param str
	 * @param encode
	 * @return
	 */
	public static String converStr(String str, String encode) {
		if (str == null || str.equals("null")) {
			return "";
		}
		try {
			byte[] tmpbyte = str.getBytes("ISO8859_1");
			if (encode != null) {
				// 如果指定编码方式
				str = new String(tmpbyte, encode);
			} else {
				// 用系统默认的编码
				str = new String(tmpbyte);
			}
			return str;
		} catch (Exception e) {
		}
		return str;
	}

	// 去html标签
	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; 
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; 
		String regEx_html = "<[^>]+>"; 

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); 

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); 

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); 

		return htmlStr.trim(); 
	}

	// 去除字符串中的空格、回车、换行符、制表符
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String timeTostrHMS(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String timeTostrYMD(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			strDate = format.format(date);
		}
		return strDate;
	}
	/**
	 * 解析51job的岗位信息。
	 * 
	 * @param tag
	 * @return
	 */
	public static String[]  parseCompony(String tag)
	{
		String separatorChar = "|";
		//Split into 3 parts: Type|Size|industry
		String[] tags = StringUtils.split(tag, separatorChar);
		for (int i = 0; i < tags.length; i++)
		{		
			String content = tags[i].trim();
			//space character (Maybe others
            content=content.replace("&nbsp;","");
            //去除字符串中的空格,回车,换行符,制表符by regex
            content=content.replaceAll("//s*|/t|/r|/n","");
            tags[i]= content.trim();
		}
		return tags;		
	}
	
	
	/**
	 * 获取配置文件。
	 * @param filepath
	 * @return
	 */
	public static Properties getConfig(String filepath) {
		if (propertiesMap.containsKey(filepath))
		{
			return propertiesMap.get(filepath);
		}		
		InputStream inStream = null;
		Properties prop = new Properties();
		try {
			 inStream = new FileInputStream(new File(filepath));
			 prop.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		propertiesMap.put(filepath, prop);
		return prop;  
	}
}
