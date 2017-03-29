package com.carRental.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class CarModel extends BaseModel {
	
	private String shapeSize;
	private String carID;
	private float singlePrice;
	private String SELECT_CAR = "select * from car";
	private String SELECT_CAR_WITH_UNRENTAL = "select * from car where state=0";
	private String INSERT = "insert into car(carID,shapesize,singleprice)  values(?,?,?)";
	private String DELETE = "delete from car where carID = ?";
	private String UPDATE = "update car set state = 1 where carID = ?";
	
	public CarModel() {
		super();
	}
	public void rentalCar(String carID){
		try {
			PreparedStatement statement =(PreparedStatement) (this.getConnection().prepareStatement(UPDATE));
			statement.setString(1, carID);
			
			int i = statement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}
	public Object[][] getCarModelWithNurental(){
			Vector<CarModel> vector = selectCarWithUnrental();
			Object[][] objects = new Object[vector.size()][3];
			
			for (int i = 0; i < objects.length; i++) {
				objects[i][0] = vector.get(i).getCarID();
				objects[i][1] = vector.get(i).getShapeSize();
				objects[i][2] = vector.get(i).getSinglePrice();
			}
			return objects;
	}
	private Vector<CarModel> selectCarWithUnrental() {
		Vector<CarModel> vector = new Vector<CarModel>();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) (this.getConnection().prepareStatement(SELECT_CAR_WITH_UNRENTAL));
			ResultSet set =  statement.executeQuery();
			while (set.next()) {
				CarModel carModel = new CarModel();
				
				carModel.setCarID(set.getString(2));
				carModel.setShapeSize(set.getString(3));
				carModel.setSinglePrice(set.getFloat(4));
				
				vector.add(carModel);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return vector;
	}
	private Vector<CarModel> selectCar(){
		
		Vector< CarModel> vector = new Vector<CarModel>();
		try {
			PreparedStatement statement =(PreparedStatement) (this.getConnection().prepareStatement(SELECT_CAR));
			ResultSet set =  statement.executeQuery();
			while (set.next()) {
				CarModel carModel = new CarModel();
				
				carModel.setCarID(set.getString(2));
				carModel.setShapeSize(set.getString(3));
				carModel.setSinglePrice(set.getFloat(4));
				
				vector.add(carModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}
	public Object[][] getCarModels() {
		Vector<CarModel> vector = selectCar();
		Object[][] objects = new Object[vector.size()][3];
		for (int i = 0; i < objects.length; i++) {
			objects[i][0] = vector.get(i).getCarID();
			objects[i][1] = vector.get(i).getShapeSize();
			objects[i][2] = vector.get(i).getSinglePrice();
		}

		return objects;
	}
	public  void addCar(String carID,String shapesize,float singleprice) {
		try {
			PreparedStatement statement =(PreparedStatement) (this.getConnection().prepareStatement(INSERT));
			System.out.println(singleprice);
			statement.setString(1, carID);
			statement.setString(2, shapesize);
			statement.setFloat(3, singleprice);
			System.out.println(statement.toString());
			
			int i = statement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}
	
	public void deleteCarById(String carID) {
		try {
			PreparedStatement statement =(PreparedStatement) (this.getConnection().prepareStatement(DELETE));
			statement.setString(1, carID);
			
			int i = statement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}
	
	public String getShapeSize() {
		return shapeSize;
	}
	
	public void setShapeSize(String shapeSize) {
		this.shapeSize = shapeSize;
	}

	public String getCarID() {
		return carID;
	}
	public void setCarID(String carID) {
		this.carID = carID;
	}

	public float getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(float singlePrice) {
		this.singlePrice = singlePrice;
	}
}
