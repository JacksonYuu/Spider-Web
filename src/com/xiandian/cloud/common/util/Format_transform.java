/*
 * Copyright (c) 2017, 1DAOYUN and/or its affiliates. All rights reserved.
 * 1DAOYUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.xiandian.cloud.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
/**
 * 字符类型转换公共类
 * 
 * @author XuanHuiDong
 * @since V2.0
 * 
 */
public class Format_transform {
	//字符类型转换
	public static String gb2312ToUtf8(String str) {
        String urlEncode = "" ;
    	if(str == null){
    		return urlEncode;
    	}
        try {
            urlEncode = URLEncoder.encode (str, "UTF-8");
            urlEncode = URLDecoder.decode (urlEncode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncode;
    }
	//null转""
	public static String change(String s) {
		if(s==null) {
			return "";
		}
		return s;
	}
}


