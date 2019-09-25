/*
 * Copyright (c) 2017, 1DAOYUN and/or its affiliates. All rights reserved.
 * 1DAOYUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.xiandian.cloud.common.jobcollect;

import us.codecraft.webmagic.Page;

/**
 * 智联网络爬虫。
 * 
 * @since v1.0
 * @date 20170816
 * @author WangHuanhuan
 */
public class ZLJobPageCrawler  extends JobPageCrawler{

	public ZLJobPageCrawler() {
	}

	@Override
	public void process(Page arg0) {
		// TODO 获取智联的tag
		super.process(arg0);
	}

}
