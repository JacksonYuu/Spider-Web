/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.common.bean;

import com.xiandian.cloud.common.util.StoreTools;



/**
 * 与前台交互数据的bean
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class FileBean {

	//是否是目录
	private boolean isdirectory;
	//是否有子文件夹
	private boolean haschild;
	//文件名称
	private String name;
	//文件路径
	private String path;
	//文件大小
	private String length;
	//最后修改时间
	private String lastmodified;
	//如果是文件，存放文件的临时路径
	private String filepath;
	
	public boolean isHaschild() {
		return haschild;
	}

	public void setHaschild(boolean haschild) {
		this.haschild = haschild;
	}
	public boolean isIsdirectory() {
		return isdirectory;
	}
	public void setIsdirectory(boolean isdirectory) {
		this.isdirectory = isdirectory;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = StoreTools.convertFileSize(length);
	}

	public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
//		Date ds = new Date(lastmodified);
		this.lastmodified = lastmodified;//UtilTools.timeTostrHMS(ds);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
