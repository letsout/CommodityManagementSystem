package com.lh.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 用来操作java配置文件
 * @author 
 *
 */
public class Config {
	/** 用来操作的java配置文件类 */
	private static Properties props = new Properties();
	private static String propsPath = "config/config.properties";	
	/**
	 * 系统加载前首先加载配置文件，以便系统加载时读取配置文件中的配置内容
	 */
	static{
		loadConfigFile();
	}
	/**
	 * 加载配置文件
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
	 * 根据键（key）得到配置文件中对应的value值<br/>
	 * 为了避免键不存在或书写错误，可以将键定义成常量
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return props.getProperty(key);
	}

}
