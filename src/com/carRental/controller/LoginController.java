package com.carRental.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carRental.database.DatabaseOP;
import com.carRental.observer.LoginObserver;
import com.carRental.view.AdminFrame;
import com.carRental.view.LoginFrame;

public class LoginController implements LoginObserver{
	public LoginFrame loginFrame = null;
	
	public LoginController(LoginFrame loginFrame) {
		
		this.loginFrame = loginFrame;
		this.loginFrame.setVisible(true);
	}

	public void loginCallback(String name,String password) {
		if (login(name, password)) {
			AdminFrame adminFrame = new AdminFrame(); 
			AdminController controller = new AdminController(adminFrame);
		}
	}
	private boolean login(String name,String password){
		if (VerifyController.isInput(name, password)) {
			String login_SQL = "SELECT * from admin where account = ? and password=?";
			DatabaseOP databaseOP = new DatabaseOP();
			Connection connection;
			try {
				connection = databaseOP.connection();
				PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(login_SQL);
				pstmt.setString(1, name);
				pstmt.setString(2, password);

				
				ResultSet rs = (ResultSet) pstmt.executeQuery();
				if (rs.next()){
					return true;
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			
		}
		return true;
	}
	
}
