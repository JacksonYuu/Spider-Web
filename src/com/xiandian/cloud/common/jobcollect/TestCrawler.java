package com.xiandian.cloud.common.jobcollect;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class TestCrawler implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setCharset("gb2312");
	static String urlBreaks = "http://search.51job.com/list/000000,000000,0000,00,9,99,%25E4%25BA%2591%25E8%25AE%25A1%25E7%25AE%2597,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
	private Pattern pattern = Pattern.compile("/([0-9]+)\\.html");

	@Override
	public Site getSite() {
		site.setUserAgent(
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		return site;
	}

	@Override
	public void process(Page page) {
		// Init select and urls
		Selectable select = null;
		List<String> urls = null;
		if (page.getUrl().toString().contains("search.51job.com")) {
			select = page.getHtml().xpath("//p[@class='t1']");
			urls = select.links().all();
			page.addTargetRequests(urls);

			select = page.getHtml().xpath("//div[@class='dw_page']");
			urls = select.links().all();
			Iterator<String> it = urls.iterator();
			while (it.hasNext()) {
				String x = it.next();
				if (x.equals(urlBreaks)) {
					it.remove();
				}
			}
			page.addTargetRequests(urls);
		} else if (page.getUrl().toString().startsWith("http://jobs.51job.com/")) {

			// 岗位名称
			page.putField("jobname", page.getHtml().xpath("//div[@class='cn']/h1/text()").toString());
			// 公司名称
			page.putField("name", page.getHtml().xpath("//p[@class='cname']/a/text()").toString());
			// 工作地点
			page.putField("city", page.getHtml().xpath("//span[@class='lname']/text()").toString());
			// 工作薪资
			page.putField("wages", page.getHtml().xpath("//div[@class='cn']/strong/text()").toString());
			// 发布时间
			page.putField("createtime", page.getHtml().regex("<em class=\\\"i4\\\"></em>([^<]+)</span>").toString());
			// 存储地点
			Matcher matcher = pattern.matcher(page.getUrl().toString());
			String pageID = null;
			while (matcher.find()) {
				pageID = matcher.group(1);
			}

			page.putField("html", pageID + ".html");
			page.putField("num", pageID);
			page.putField("URL", page.getUrl().toString());
		}

	}

	public static void main(String[] args) {
		Spider spider= Spider.create(new TestCrawler());
		spider.addUrl(urlBreaks);
		spider.addPipeline(new TestPipline(System.getProperty("user.dir") + "/webmagic/",spider));
		spider.thread(10);
		spider.setExitWhenComplete(true);
		spider.run();
	}

}
