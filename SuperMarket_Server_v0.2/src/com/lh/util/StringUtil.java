package com.lh.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	public static String getStringByLen(String str, int len){
		int countOfChLetter = 0;
		int countOfEnLetter = 0;
		try {
			for (int i = 0; i < str.length(); i++) {
				if(str.substring(i, i+1).getBytes("gbk").length == 2){
					countOfChLetter++;//统计字符串中有多少中文
				}else{
					countOfEnLetter++;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int countOfSpace = len - countOfChLetter * 2 - countOfEnLetter / 2;
		if(countOfEnLetter % 2 != 0)
			countOfSpace++;
		for (int i = 0; i < countOfSpace; i++) {
			str += "?";
		}
		return str;
	}
}
