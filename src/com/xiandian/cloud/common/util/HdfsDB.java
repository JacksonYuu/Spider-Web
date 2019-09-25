package com.xiandian.cloud.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import com.xiandian.cloud.common.bean.FileBean;
import com.xiandian.cloud.common.cons.StoreConstants;


public class HdfsDB {

	static FileSystem fs;
	static Configuration conf;

	private static class HdfsDBInstance {
		private static final HdfsDB instance = new HdfsDB();
	}

	public static HdfsDB getInstance() {
		return HdfsDBInstance.instance;
	}

	private HdfsDB() {
		conf = new Configuration();
		//hdfs://10.10.4.36:8020
//		conf.set("fs.defaultFS", StoreTools.getConfig().getProperty("hdfs"));
		conf.set("fs.defaultFS", "hdfs://10.0.0.46:8020");
//		System.out.println("hdfs地址:" +StoreTools.getConfig().getProperty("hdfs"));
		
		try {
			fs = FileSystem.get(conf);
			System.out.println("22222222");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 * @param filePath
	 * @param dir
	 * @throws Exception
	 */
	public void upload(String filePath, String dir) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		OutputStream out = fs.create(new Path(dir), new Progressable() {

			@Override
			public void progress() {
				//System.out.println("ok");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
	}
	/**
	 * 已流形式上传
	 * @param in
	 * @param dir
	 * @throws Exception
	 */
	public void upload(InputStream in, String dir) throws Exception {
		OutputStream out = fs.create(new Path(dir), new Progressable() {
			@Override
			public void progress() {
				//System.out.println("ok");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
	}
	/**
	 * 下载文件
	 * @param path
	 * @param local
	 * @throws Exception
	 */
	public void downLoad(String path,String local) throws Exception {
		FSDataInputStream in = fs.open(new Path(path));
		OutputStream out = new FileOutputStream(local);
		IOUtils.copyBytes(in, out, 4096, true);
	}
	
	/**
	 * 下载文件
	 * @param path
	 * @throws Exception
	 */
	public void downLoad(String path,OutputStream out) throws Exception {
		FSDataInputStream in = fs.open(new Path(path));
//		OutputStream out = new FileOutputStream(local);
		IOUtils.copyBytes(in, out, 4096, true);
	}
	
	/**
	 * 重命名文件
	 * @param src
	 * @param dst
	 * @throws Exception
	 */
	public void rename(String src,String dst) throws Exception {
		fs.rename(new Path(src), new Path(dst));
	}

	public boolean isexist(String dir) throws Exception {
		return fs.exists(new Path(dir));
	}
	
	/**
	 * 创建文件夹
	 * @param dir
	 * @throws Exception
	 */
	public void mkdir(String dir) throws Exception {
		if (!fs.exists(new Path(dir))) {
			fs.mkdirs(new Path(dir));
		}
	}
	/**
	 * 删除文件及文件夹
	 * @param name
	 * @throws Exception
	 */
	public void delete(String name) throws Exception {
		fs.delete(new Path(name), true);
	}
	
	
	/**
	 * 查新文件夹
	 * @param dir
	 * @param pattern
	 * @param list
	 * @throws Exception
	 */
	public void queryByKey(String dir,Pattern pattern, List list) throws Exception{
		

		FileStatus[] files = fs.listStatus(new Path(dir));
		FileBean f = null;
		for (int i = 0; i < files.length; i++) {
			f = new FileBean();
			if (files[i].isDirectory()) {
				String path =files[i].getPath().toUri().getPath();
				queryByKey(path,pattern,list);
			} else if (files[i].isFile()) {
				String filename =files[i].getPath().getName();
				Matcher matcher = pattern.matcher(filename);
				if(matcher.matches()){
					f.setName(files[i].getPath().getName());
					f.setIsdirectory(false);
					f.setLastmodified(DateUtil.longToString("yyyy-MM-dd HH:mm", files[i].getModificationTime()));
					f.setLength(files[i].getLen());
					f.setPath(files[i].getPath().toUri().getPath());
					list.add(f);
				}
			}
		}
	}
	
	
	
	/**
	 * 分拣分类
	 * @param dir
	 * @param type
	 * @param list
	 * @throws Exception
	 */
	public void queryByCatetory(String dir,int type,List list) throws Exception{
		

		FileStatus[] files = fs.listStatus(new Path(dir));
		FileBean f = null;
		for (int i = 0; i < files.length; i++) {
			f = new FileBean();
			if (files[i].isDirectory()) {
				String path =files[i].getPath().toUri().getPath();
				queryByCatetory(path,type,list);
			} else if (files[i].isFile()) {
				f.setName(files[i].getPath().getName());
				f.setIsdirectory(false);
				f.setLastmodified(DateUtil.longToString("yyyy-MM-dd HH:mm", files[i].getModificationTime()));
				f.setLength(files[i].getLen());
				f.setPath(files[i].getPath().toUri().getPath());
				String filename = files[i].getPath().getName();
				 if (type == StoreConstants.FILE_TYPE1) {
				if (StoreTools.isImage(filename)) {
					list.add(f);
				}
				} else if (type == StoreConstants.FILE_TYPE2) {
					if (StoreTools.isDoc(filename)) {
						list.add(f);
					}
				} else if (type == StoreConstants.FILE_TYPE3) {
					if (StoreTools.isMediea(filename)) {
						list.add(f);
					}
				} else if (type == StoreConstants.FILE_TYPE4) {
					if (StoreTools.isMp3(filename)) {
						list.add(f);
					}
				} else if (type == StoreConstants.FILE_TYPE5) {
					if (!StoreTools.isImage(filename) && !StoreTools.isDoc(filename)
							&& !StoreTools.isMediea(filename) && !StoreTools.isMp3(filename)) {
						list.add(f);
					}
				}
			}
		}
	}

	/**
	 * 查询文件夹
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public List<FileBean> queryAll(String dir) throws Exception{
		FileStatus[] files = fs.listStatus(new Path(dir));
		List<FileBean> fileVos = new ArrayList<FileBean>();
		FileBean f = null;
		for (int i = 0; i < files.length; i++) {
			f = new FileBean();
			if (files[i].isDirectory()) {
				f.setName(files[i].getPath().getName());
				f.setIsdirectory(true);
				f.setLastmodified(DateUtil.longToString("yyyy-MM-dd HH:mm", files[i].getModificationTime()));
				f.setPath(files[i].getPath().toUri().getPath());
			} else if (files[i].isFile()) {
				f.setName(files[i].getPath().getName());
				f.setIsdirectory(false);
				f.setLastmodified(DateUtil.longToString("yyyy-MM-dd HH:mm", files[i].getModificationTime()));
				f.setLength(files[i].getLen());
				f.setPath(files[i].getPath().toUri().getPath());
//				String s=FileUtils.getFileSufix(f.getName());
//				for (int j = 0; j < suf.length; j++) {
//					if (s.equals(suf[j])) {
//						f.setViewflag("Y");
//						break;
//					}
//				}
			}
			fileVos.add(f);
		}
		return fileVos;
	}
	/**
	 * 移动或复制文件
	 * @param path
	 * @param dst
	 * @param src true 移动文件;false 复制文件
	 * @throws Exception
	 */
	public void copy(String[] path, String dst,boolean src) throws Exception {
		Path[] paths = new Path[path.length];
		for (int i = 0; i < path.length; i++) {
			paths[i]=new Path(path[i]);
		}
		FileUtil.copy(fs, paths, fs, new Path(dst), src, true, conf);
	}
	
//	public List<Menu> tree(String dir) throws Exception {
//		FileStatus[] files = fs.listStatus(new Path(dir));
//		List<Menu> menus = new ArrayList<Menu>();
//		for (int i = 0; i < files.length; i++) {
//			if (files[i].isDirectory()) {
//				menus.add(new Menu(files[i].getPath().toString(), files[i].getPath().getName()));
//			}
//		}
//		return menus;
//	}

	public static void main(String[] args) throws Exception {
		HdfsDB hdfsDB = new HdfsDB();
//		hdfsDB.mkdir(ROOT+"weir/qq");98309017.html

		// String path = "C://Users//Administrator//Desktop//jeeshop-jeeshop-master.zip";
		// hdfsDB.upload(path, "weir/"+"jeeshop.zip");
//		List<FileBean> f= hdfsDB.queryAll("/user/douxue/douxue/insight/job/crawler");
		hdfsDB.downLoad("/user/png/5.png", "D://5.png");
		System.out.println("down");
		 String path = "D://1.png";
		 hdfsDB.upload(path, "/user/png/"+"5.png");
		 System.out.println("upup");
		 List<FileBean> f= hdfsDB.queryAll("/user/douxue/douxue/insight/job/crawler");
		System.out.println(f.get(1).getPath());
		//http://192.168.0.99:50075/webhdfs/v1/exam/257/8e32010f-4f4c-4529-a472-c743f90771540.jpg?op=OPEN&namenoderpcaddress=192.168.0.99:8020
		
//		for(FileBean fb: f) {
//			System.out.println(fb.getPath());
//		}
//		hdfsDB.visitPath("hdfs://h1:9000/weir");
//		for (Menu menu : menus) {
//			System.out.println(menu.getName());
//			System.out.println(menu.getPname());
//		}
//		hdfsDB.delete("weirqq");
//		hdfsDB.mkdir("/weirqq");
//		hdfsDB.tree("/admin");
		System.out.println("ok");
	}

	public Long getStorageSize(String str) throws Exception {
		Path path = new Path(str);
		return fs.getContentSummary(path).getLength();
	}
}
