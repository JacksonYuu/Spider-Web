package com.xiandian.cloud.common.jobcollect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiandian.cloud.common.bean.JobBean;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

public class TestPipline extends FilePersistentBase implements Pipeline {

	public TestPipline() {
		setPath("/data/webmagic");
	}

	public TestPipline(String path) {
		setPath(path);
	}
	public TestPipline(String path,Spider spider) {
		setPath(path);
		this.spider=spider;
	}
	
	private static int count=0;
	private JsonObject jsonObject=new JsonObject();
	private JSONArray jsonaray=new JSONArray();
	private Spider spider;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		try {
			String path = this.path + PATH_SEPERATOR + "111" + ".txt";
			File file = getFile(path);
			if (resultItems.get("jobname") != null) {
				JobBean jobBean = new JobBean(resultItems.get("jobname") + "", resultItems.get("name") + "",
						resultItems.get("city") + "", resultItems.get("wages") + "", resultItems.get("createtime") + "",
						resultItems.get("html") + "");
				
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(jobBean)+"\r\n";
				
				JsonObject jsonObject2=new JsonObject();
				String jobname=(String) resultItems.get("jobname");
				String name=(String) resultItems.get("name");
				String city=(String) resultItems.get("city");
				String wages=(String) resultItems.get("wages");
				String createtime=(String) resultItems.get("createtime");
				String html=(String) resultItems.get("html");
				
				if(jobname != null ) {
					jsonObject2.addProperty("jobname", jobname);
				}else {
					jsonObject2.addProperty("jobname", "");
				}
				if(name != null ) {
					jsonObject2.addProperty("name", name);
				}else {
					jsonObject2.addProperty("name", "");
				}
				if(city != null ) {
					jsonObject2.addProperty("city", city);
				}else {
					jsonObject2.addProperty("city", "");
				}
				if(wages != null ) {
					jsonObject2.addProperty("wages", wages);
				}else {
					jsonObject2.addProperty("wages", "");
				}
				if(createtime != null ) {
					jsonObject2.addProperty("createtime", createtime);
				}else {
					jsonObject2.addProperty("createtime", "");
				}
				if(html != null ) {
					jsonObject2.addProperty("html", html);
				}else {
					jsonObject2.addProperty("html", "");
				}
				
				System.out.println(jsonObject2);
				count++;
				System.out.println(count);
				jsonaray.put(jsonObject2);
				if(count==2000) {
					System.out.println(jsonaray.length());
					jsonObject.addProperty("job", jsonaray.toString());
					System.out.println(jsonObject.toString());
					String json1=jsonObject.toString().replace("\\", "");
					json1 = json1.replace("\"[\"", "[");
					json1 = json1.replace("\"]\"", "]");
					json1 = json1.replace("}\",\"{", "},{");
					System.out.println(json1);
					
					FileUtils.writeStringToFile(file,json1, "UTF-8", true);
					spider.stop();
				}
				
				SaveToHTML(new URL(resultItems.get("URL") + ""), resultItems.get("num") + "");
			}
		} catch (Exception e) {

		}

	}

	public static String timeTostrYMD(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static void SaveToHTML(URL url, String num) throws IOException {
		String fileName = System.getProperty("user.dir") + "/webmagic/pages/" + num + ".html";
		FileOutputStream fos = null;
		InputStream is;
		byte bytes[] = new byte[1024 * 1000];
		int index = 0;
		is = url.openStream();
		int count = is.read(bytes, index, 1024 * 100);
		InputStreamReader insReader = new InputStreamReader(is,"GBK");
		fos = new FileOutputStream(fileName);
		OutputStreamWriter osw=new OutputStreamWriter(fos);
		int len = 0;  
        char [] cs=new char[1024];
		 while ((len = insReader.read(cs)) != -1) {  
             osw.write(cs, 0, len);
         }
		 
		 
		insReader.close();
		is.close();
		fos.close();
	}
}
