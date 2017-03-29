package com.carRental.model;

import java.io.IOException;
import java.sql.Connection;

import com.carRental.database.DatabaseOP;

public class BaseModel{
	private Connection connection = null;
	
	public BaseModel() {
		 try {
			this.setConnection(DatabaseOP.connection());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
