package com.carRental.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class DatabaseOP {
	
	private static Connection connection = null;
	
//	public static DatabaseOP getInstance() {
//		synchronized (new Object()) {
//			if (singleInstance == null) {
//				singleInstance = new DatabaseOP();
//			}
//		}
//		return singleInstance;
//	}
	
	public DatabaseOP() {
		
	}
//	public static void main(String[] args) throws IOException {
//		System.out.println(connection());
//	}
	
	/**
	 * 单例模式 获取链接
	 * @return
	 * @throws IOException
	 */
	public static  Connection connection() throws IOException{
		synchronized (new Object()) {
			if (connection == null) {
				
				Properties conf = new Properties();
				FileInputStream in = new FileInputStream("CarRental.conf");
				conf.load(in);
				
				String url = conf.getProperty("dburl");
				String user = conf.getProperty("user");
				String password = conf.getProperty("password");
				String driver = conf.getProperty("driver");
				
				try {
					Class.forName(driver);
					
					connection = DriverManager.getConnection(url, user, password);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}				
		return connection;
	}
}
