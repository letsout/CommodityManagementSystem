package com.lh.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ��������java�����ļ�
 * @author 
 *
 */
public class Config {
	/** ����������java�����ļ��� */
	private static Properties props = new Properties();
	private static String propsPath = "config/config.properties";	
	/**
	 * ϵͳ����ǰ���ȼ��������ļ����Ա�ϵͳ����ʱ��ȡ�����ļ��е���������
	 */
	static{
		loadConfigFile();
	}
	/**
	 * ���������ļ�
	 * @return
	 */
	private static boolean loadConfigFile(){
		try {
			ClassLoader classLoader = Config.class.getClassLoader();
			props.load(classLoader.getResourceAsStream(propsPath));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * ���ݼ���key���õ������ļ��ж�Ӧ��valueֵ<br/>
	 * Ϊ�˱���������ڻ���д���󣬿��Խ�������ɳ���
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return props.getProperty(key);
	}

}
