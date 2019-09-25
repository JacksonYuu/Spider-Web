/*
 * Copyright (c) 2017, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.common.cons;

/**
 * 整个应用通用常量类
 * 
 * @author 云计算应用与开发项目组
 * @since V2.0
 * 
 */
public class Constants {
	public static final int PAGE_SIZE_20 = 20;
	// 用户对象放到Session中的键名称
	public static final String USER_CONTEXT = "user";
	// 消息提示
	public static final String SUCCESS_1 = "登录成功";
	public static final String SUCCESS_2 = "注册成功";
	// 错误信息
	public static final String ERROR_1 = "登录用户名不对";
	public static final String ERROR_2 = "登录密码不对";
	public static final String ERROR_3 = "用户名已经注册";
	public static final String ERROR_4 = "没有找到该账号";
	
	// 数据清洗规范化岗位名称的常量
	public static final String JOB_1 = "经理";
	public static final String JOB_2 = "产品";
	public static final String JOB_3 = "云";
	public static final String JOB_4 = "openstack";
	public static final String JOB_5 = "docker";
	public static final String JOB_6 = "云计算";
	public static final String JOB_7 = "大数据";
	public static final String JOB_8 = "hadoop";
	public static final String JOB_9 = "系统";
	public static final String JOB_10 = "java";
	public static final String JOB_11 = "web";
	public static final String JOB_12 = "数据库";
	public static final String JOB_13 = "开发工程师";
	public static final String JOB_14 = "工程师";
	

	// kmeans聚类的输入输出目录以及涉及到的子目录
	public static final String KMEANS_INPUTDIR="/user/txt-seq";
	public static final String KMEANS_OUTPUTDIR="/user/txt-kmeans";
	public static final String TF_VECTORS="tf-vectors";
	public static final String CANOPY_CENTROIDS="canopy-centroids";
	public static final String CLUSTERS="clusters";
	public static final String CLUSTERS_1_FINAL="clusters-1-final";
	public static final String CLUSTERS_0_FINAL="clusters-0-final";
	public static final String CLUSTEREDPOINTS="clusteredPoints";
	public static final String SEQUENCEFILE="sequencefile";
	public static final String DICTIONARY="dictionary.file-0";
	public static final String DUMP_NUM="15";
	
	// 推荐岗位涉及到的输入输出目录
	public static final String RECOMMJOB_INPUT="/user/input/file1";
	public static final String RECOMMJOB_SEEDS="/user/seeds/part-seeds";
	public static final String RECOMMJOB_OUTPUT="/user/vector-dist";
	public static final String RECOMMJOB_DIST_RESULT= "part-m-00000";
	
	
	
	
			
}
