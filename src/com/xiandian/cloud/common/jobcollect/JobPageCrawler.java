/*
 * Copyright (c) 2017, 1DAOYUN and/or its affiliates. All rights reserved.
 * 1DAOYUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.xiandian.cloud.common.jobcollect;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬虫父类。
 * 
 * @since v1.0
 * @date 20170815
 * @author Soongxueyong
 */
public class JobPageCrawler implements PageProcessor{
	// Init site info
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setCharset("gb2312");

	public JobPageCrawler() {
	}
	
   	public Site getSite() {
   		site.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
   		return site;
   	}

	
	public void process(Page arg0) {
		

	}

}
