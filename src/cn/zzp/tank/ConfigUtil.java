/**
 * 
 */
package cn.zzp.tank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *初始化配置参�?
 */
public class ConfigUtil {
	
	public static void initConfig(){
		//获取配置文件信息
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(System.getProperty("user.dir")+"/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�?��获取配置的初始�?
		Tank.SPEED = Integer.valueOf(properties.getProperty("tankSpeed").toString());
		Bullet.SPEED = Integer.valueOf(properties.getProperty("bulletSpeed").toString());
		
	}

	

}
