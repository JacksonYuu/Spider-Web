/*
 * Copyright (c) 2017, 1DAOYUN and/or its affiliates. All rights reserved.
 * 1DAOYUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.xiandian.cloud.common.jobcollect;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadHtml {

	public static void SaveTo51Job_Relation(String num, String jobname) throws IOException {
		String file = System.getProperty("user.dir") + "/webmagic/51job_relation.txt";
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
		out.write(num + "\t" + jobname + "\r\n");
		out.close();
	}

	public static void SaveToHTML(URL url, String num) throws IOException {
		// String fileName = System.getProperty("user.dir")+"/webmagic/pages/" + num+
		// ".html";
		FileOutputStream fos = null;
		InputStream is;
		// byte bytes[] = new byte[1024 * 1000];
		// int index = 0;
		is = url.openStream();
//		HdfsClient hdfsClient = HdfsClient.getInstance();
//		HdfsClient.uploadByIo(is, "/user/PC/dict_in/" + num + ".html");
		// int count = is.read(bytes, index, 1024 * 100);
		// while (count != -1) {
		// index += count;
		// count = is.read(bytes, index, 1);
		// }
		//
		// fos = new FileOutputStream(fileName);
		// fos.write(bytes, 0, index);
		// is.close();
		// fos.close();
	}
	/**
	 * 保存数据到hdfs
	 * @param urlname
	 * @throws IOException
	 */
	public static void Save(String urlname,String savepath) throws IOException {
		URL url = new URL(urlname);
		Pattern pattern1 = Pattern.compile("/([0-9]+)\\.html");
		Matcher matcher1 = pattern1.matcher(urlname);
		String num = null;
		while (matcher1.find()) {
			num = matcher1.group(1);
		}
		FileOutputStream fos = null;
		InputStream is;
		is = url.openStream();
//		HdfsClient hdfsClient = HdfsClient.getInstance();
//		HdfsClient.uploadByIo(is, savepath + num + ".html");
	}
}
