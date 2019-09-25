/*
 * Copyright (c) 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiandian.cloud.common.bean.JobBean;
import com.xiandian.cloud.common.util.HdfsDB;
import com.xiandian.cloud.common.util.StoreTools;

/**
 * 系统不需要登陆的action
 * 
 * @author 云计算应用与开发项目组
 * @since V2.0
 * 
 */
@Controller
@RequestMapping("/course")
public class IndexController {


	//hdfs存储服务
	private HdfsDB hdfsDB=HdfsDB.getInstance();
	/**
	 * 跳转到登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("/index/{pages}")
	public ModelAndView tologin(HttpServletRequest request, @PathVariable int pages) {
		String url = "/crawl/list";
		ModelAndView view = new ModelAndView(url);
		view.addObject("pages", pages);
		String contextpaht = request.getSession().getServletContext().getRealPath("/");
		String path = contextpaht + "/json/1.json";
		String jsonstr="";
		 ArrayList<JobBean> list = new ArrayList<JobBean>();  
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String s = null; 
			while ((s = br.readLine()) != null) {  
				jsonstr +=s;  
            }
			br.close();
			try {  
                JSONObject dataJson = new JSONObject(jsonstr); 
                JSONArray json = dataJson.getJSONArray("job"); 
                int jsonarrysize = json.length();
                int pagecount=jsonarrysize/50;
                view.addObject("pagecount", pagecount);
                view.addObject("pages", pages);
                for (int i = 0 + (pages-1)*50; i<(pages-1)*50+49 && i < json.length(); i++) {  
                    JSONObject jsonobj = json.getJSONObject(i);
                    String jobname=jsonobj.getString("jobname");
                    String name=jsonobj.getString("name");
                    String city=jsonobj.getString("city");
                    String wages=jsonobj.getString("wages");
                    String createtime=jsonobj.getString("createtime");
                    String html=jsonobj.getString("html");
                    JobBean jobBean=new JobBean(jobname, name, city, wages, createtime, html);
                    list.add(jobBean);
                    String hdfspath="/user/douxue/douxue/insight/job/crawler/"+html;
                    String loaclpath=contextpaht+"/WEB-INF/view/51job/"+html+"_copy";
                    File file =new File(contextpaht+"/WEB-INF/view/51job/"+html);
                    if(file.exists()) {
                    }else {
                    	hdfsDB.downLoad(hdfspath,loaclpath);
                    	StoreTools.changeEncode(loaclpath, "GBK");
                    }
                }  
            } catch (JSONException e) {  
                e.printStackTrace();  
            } catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		view.addObject("jobBean", list);
		return view;
	}

}
