/*
 * Copyright (c) 2014, 2015, dhl and/or its affiliates. All rights reserved.
 * dhl PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.log.Log;

/**
 * 工具类，提供公共方法
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
public class StoreTools {

	public static String COFING_FILE = "/xd.properties";
	public static Properties p;

	public static Properties getConfig() {
		if (p == null) {
			p = new Properties();
			try {
				p.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(COFING_FILE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	private static String imgeArray[] = { "bmp", "dib", "gif", "jfif", "jpe",
			"jpeg", "jpg", "png", "tif", "tiff", "ico" };

	private static String docArray[] = { "doc", "docx", "ppt", "pptx", "xls",
			"xlsx", "txt", "pdf", "csv", "xml", "sql" };

	private static String aviArray[] = { "avi", "wmv", "rmvb", "rm", "mp4",
			"mpg", "mkv", "swf", "flv", "asf", "mov" };

	private static String mp3Array[] = { "mp3", "wav", "mod", "mid", "cda",
			"ogg", "mod", "ra" };

	public static boolean isImage(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = imgeArray.length;
		for (int i = 0; i < len; i++) {
			if (imgeArray[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDoc(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = docArray.length;
		for (int i = 0; i < len; i++) {
			if (docArray[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isMediea(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = aviArray.length;
		for (int i = 0; i < len; i++) {
			if (aviArray[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isMp3(String name) {
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		}
		String tmpName = name.substring(index + 1, name.length());
		int len = mp3Array.length;
		for (int i = 0; i < len; i++) {
			if (mp3Array[i].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 默认是utf-8编码
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

	public static long getTime(String endtime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date end = df.parse(endtime);
			Date start = new Date();
			long l = end.getTime() - start.getTime();
			return l / 1000;
		} catch (Exception e) {
			return -1;
		}
	}

	public static String replaceBackett(String str) {
		str = str.replaceAll("\\\\", "\\\\\\\\");
		str = str.replaceAll("\'", "\\\\\'");
		str = str.replaceAll("\"", "\\\\\"");
		return str;
	}

	// 选择题替换图片
	public static String parserep(String str) {
		int index = str.indexOf("[image]");
		int index2 = str.indexOf("[/image]");
		if (index != -1 && index2 != -1) {
			str = str.substring(0, index) + "<img src='"
					+ str.substring(index + 7, index2) + "'/>"
					+ str.substring(index2 + 8);
			return parserep(str);
		}
		return str;
	}

	// 填空去掉答案，用下划线+数字代替
	public static String parserep(String str, int i) {
		int index = str.indexOf("[[");
		int index2 = str.indexOf("]]");
		if (index != -1 && index2 != -1) {
			str = str.substring(0, index) + "<u>&nbsp;&nbsp;&nbsp;&nbsp;" + i
					+ "&nbsp;&nbsp;&nbsp;&nbsp;</u>"
					+ str.substring(index2 + 2);
			// <span class="question-stem-fill-blank">
			// str =
			// str.substring(0,index)+"<span class='question-stem-fill-blank'>("+i+")</span>"+str.substring(index2+2);
			return parserep(str, i + 1);
		}
		return str;
	}

	public static String replaceStr(String str) {
		str = str.replaceAll("%2F", "/");
		return str;
	}

	// public static String md5Digest(String src){
	// // 定义数字签名方法, 可用：MD5, SHA-1
	// try
	// {
	// MessageDigest md = MessageDigest.getInstance("MD5");
	// byte[] b = md.digest(src.getBytes("utf-8"));
	//
	// return byte2HexStr(b);
	// }catch(Exception e)
	// {
	// return "";
	// }
	// }
	//
	//
	// private static String byte2HexStr(byte[] b) {
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < b.length; i++) {
	// String s = Integer.toHexString(b[i] & 0xFF);
	// if (s.length() == 1) {
	// sb.append("0");
	// }
	//
	// sb.append(s.toUpperCase());
	// }
	//
	// return sb.toString();
	// }

	// 增加填空解析答案,
	public static void parse(List list, String str) {
		int index = str.indexOf("[[");
		int index2 = str.indexOf("]]");
		if (index != -1 && index2 != -1) {
			String start = str.substring(index + 2, index2);
			list.add(start);
			str = str.substring(index2 + 2);
			parse(list, str);
		}
	}

	private static boolean strscontain(String[] u1s, String a1) {
		for (String str : u1s) {
			if (str.equals(a1)) {
				return true;
			}
		}
		return false;
	}


	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_o = "<!--.*?-->";// "<\\!--.*-->";//html注释
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_htmlIMG = "<(?!img)[^>]*>"; // 定义HTML标签的正则表达式
																	// (不包含img)
	// private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	private static final String regEx_br = "<\\/br>";

	public static String getTextFromHtml(String htmlStr) {
		htmlStr = delHTMLTag(htmlStr);
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		// htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
		return htmlStr;
	}

	private static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_o = Pattern.compile(regEx_o, Pattern.CASE_INSENSITIVE);
		Matcher m_o = p_o.matcher(htmlStr);
		htmlStr = m_o.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		// Pattern p_space = Pattern.compile(regEx_space,
		// Pattern.CASE_INSENSITIVE);
		// Matcher m_space = p_space.matcher(htmlStr);
		// htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
		return htmlStr.trim(); // 返回文本字符串
	}

	public static boolean matches(String answer, String regex) {
		if (regex == null || "".equals(regex)) {
			return false;
		}
		String copyanswer = answer;
		String[] regexs = regex.split("\\|\\|");
		for (String str : regexs) {
			String[] strs = str.split(",");
			int lens = strs.length;
			if (lens > 1) {
				boolean flag = false;
				for (String stres : strs) {
					int index = copyanswer.indexOf(stres);
					if (index != -1) {
						copyanswer = copyanswer.substring(index
								+ stres.length());
						flag = true;
						break;
					}
				}
				if (!flag) {
					return false;
				}
			} else {
				int index = copyanswer.indexOf(str);
				if (index != -1) {
					copyanswer = copyanswer.substring(index + str.length());
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static long fromDateStringToLong(String inVal) { // 此方法计算时间毫秒
		Date date = null; // 定义时间类型
		SimpleDateFormat inputFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			date = inputFormat.parse(inVal); // 将字符型转换成日期型
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime(); // 返回毫秒数
	}

	public static void Copy(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("error  ");
			e.printStackTrace();
		}
	}

	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				copyFile2(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	// 复制文件
	public static void copyFile2(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return boolean
	 */
	public static boolean deletefiles(String delpath) throws Exception {
		try {

			File file = new File(delpath);
			// 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
					} else if (delfile.isDirectory()) {
						deletefiles(delpath + "\\" + filelist[i]);
					}
				}
				file.delete();
			}

		} catch (FileNotFoundException e) {
		}
		return true;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param delpath
	 *            String
	 * @return boolean
	 */
	public static boolean deletefile(String filename) {
		boolean flag = false;
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	// 生成tar并压缩成tar.gz
	public static void WriteToTarGzip(String rootPath, String folderPath,
			String targzipFilePath) {
		/*
		byte[] buf = new byte[1024]; // 设定读入缓冲区尺寸
		try {
			targzipFilePath = rootPath + targzipFilePath;
			// 建立压缩文件输出流
			FileOutputStream fout = new FileOutputStream(targzipFilePath);
			// 建立tar压缩输出流
			TarOutputStream tout = new TarOutputStream(fout);
			tout.setLongFileMode(TarOutputStream.LONGFILE_GNU);
			addFiles(rootPath, tout, folderPath);
			tout.close();
			fout.close();

			// 建立压缩文件输出流
			FileOutputStream gzFile = new FileOutputStream(targzipFilePath
					+ ".gz");
			// 建立gzip压缩输出流
			GZIPOutputStream gzout = new GZIPOutputStream(gzFile);
			// 打开需压缩文件作为文件输入流
			FileInputStream tarin = new FileInputStream(targzipFilePath); // targzipFilePath是文件全路径
			int len;
			while ((len = tarin.read(buf)) != -1) {
				gzout.write(buf, 0, len);
			}
			gzout.close();
			gzFile.close();
			tarin.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		File tarfile = new File(targzipFilePath);
		tarfile.delete();
	*/
		
		byte[] buf = new byte[1024]; // 设定读入缓冲区尺寸
		try {
			targzipFilePath = rootPath + targzipFilePath;
			// 建立压缩文件输出流
			FileOutputStream fout = new FileOutputStream(targzipFilePath);
			// 建立tar压缩输出流
			ZipOutputStream tout = new ZipOutputStream(fout);
			addFiles(rootPath, tout, folderPath);
			tout.close();
			fout.close();

			// 建立压缩文件输出流
//			FileOutputStream gzFile = new FileOutputStream(targzipFilePath);
//			// 建立gzip压缩输出流
//			ZipOutputStream gzout = new ZipOutputStream(gzFile);
//			// 打开需压缩文件作为文件输入流
//			FileInputStream tarin = new FileInputStream(targzipFilePath); // targzipFilePath是文件全路径
//			int len;
//			while ((len = tarin.read(buf)) != -1) {
//				gzout.write(buf, 0, len);
//			}
//			gzout.close();
//			gzFile.close();
//			tarin.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

//		File tarfile = new File(targzipFilePath);
//		tarfile.delete();
	
	
	
	
	
	
	
	
	}

	private static void addFiles(String rootPath, ZipOutputStream tout,
			String folderPath) {File srcPath = new File(rootPath + folderPath);
			int length = srcPath.listFiles().length;
			byte[] buf = new byte[1024]; // 设定读入缓冲区尺寸
			File[] files = srcPath.listFiles();
			try {
				for (int i = 0; i < length; i++) {
					if (files[i].isFile()) {
						System.out.println("file:" + files[i].getName());
						String filename = srcPath.getPath() + File.separator
								+ files[i].getName();
						// 打开需压缩文件作为文件输入流
						FileInputStream fin = new FileInputStream(filename); // filename是文件全路径
						ZipEntry tarEn = new ZipEntry(files[i].getName()); // 此处必须使用new
																	// TarEntry(File
																	// file);
//						tarEn.setName(folderPath + File.separator
//								+ files[i].getName()); // 此处需重置名称，默认是带全路径的，否则打包后会带全路径
						tout.putNextEntry(tarEn);
						int num;
						while ((num = fin.read(buf)) != -1) {
							tout.write(buf, 0, num);
						}
						tout.closeEntry();
						fin.close();
					} else {
						System.out.println(files[i].getPath());
						addFiles(rootPath, tout, folderPath + File.separator
								+ files[i].getName());
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}}


	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void unTarGz(File file, String outputDir) throws IOException {}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	public static void createDirectory(String outputDir, String subDir) {

		File file = new File(outputDir);
		if (!(subDir == null || subDir.trim().equals(""))) {// 子目录不为空
			file = new File(outputDir + "/" + subDir);
		}
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String convertFileSize(long filesize) {
		String strUnit = "Bytes";
		String strAfterComma = "";
		int intDivisor = 1;
		if (filesize >= 1024 * 1024)

		{
			strUnit = "MB";
			intDivisor = 1024 * 1024;
		} else if (filesize >= 1024) {
			strUnit = "KB";
			intDivisor = 1024;
		}
		if (intDivisor == 1)
			return filesize + " " + strUnit;
		strAfterComma = "" + 100 * (filesize % intDivisor) / intDivisor;
		if (strAfterComma == "")
			strAfterComma = ".0";
		return filesize / intDivisor + "." + strAfterComma + " " + strUnit;
	}
	
	public static boolean changeEncode(String path ,String encode) {
		
		 try {  
			 	 
	            File file = new File(path);  
	            if (file.isFile() && file.exists())   
	            {   
	            	File f=new File(path.substring(0, path.length()-5));
	            	FileInputStream fis=new FileInputStream (path); 
	            	InputStreamReader insReader = new InputStreamReader(fis, encode);  
	                FileOutputStream fos=new FileOutputStream(f);
	                OutputStreamWriter osw=new OutputStreamWriter(fos);
	                int len = 0;  
	                char [] cs=new char[1024];
	                while ((len = insReader.read(cs)) != -1) {  
	                    osw.write(cs, 0, len);
	                }
	                osw.close();
	                insReader.close();  
	                if(file.delete()) {
	                	Log.info("文件删除成功："+path);
	                }
	            }  
	  
	        } catch (Exception e) {  
	            System.out.println("读取文件内容操作出错");  
	            e.printStackTrace();  
	        }  
		return false;
	}
}
