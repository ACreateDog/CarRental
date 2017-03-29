package com.carRental.controller;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.carRental.model.CarModel;
import com.carRental.model.RentalInfoModel;
import com.carRental.observer.RentalCallback;
import com.carRental.view.CarManagePanel;
import com.carRental.view.RentalFrame;

public class UserActionController implements RentalCallback{
	private Object[][] objects = null;
	RentalFrame rentalFrame = null;
	
	public void returnCallBack(String carID,String phone){
		int selected = Integer.parseInt(carID);
		
		carID = (String)objects[selected][0];
		long start = System.currentTimeMillis();
		CarModel carModel = new CarModel();
		RentalInfoModel infoModel = new RentalInfoModel();
		
		
		carModel.rentalCar(carID);
		infoModel.returnCarWithRentalModel(carID);
		
		reload(selected);
	}
	public void rentalCallBack(String carID,String phone) {
		int selected = Integer.parseInt(carID);
		
		carID = (String)objects[selected][0];
		long start = System.currentTimeMillis();
		RentalInfoModel model = new RentalInfoModel();
		
		boolean success = model.rentalCarBy(phone, carID, start);
		if (success) {
			System.out.println(success);
			reload(selected);
		}
	}
	/**
	 * 
	 * @param phone 租车人的手机号
	 */
	public void rentalCar(String phone) {
		System.out.println();
		RentalFrame frame = new RentalFrame("租车");
		frame.observer = this;
		frame.phone = phone;
		frame.setVisible(true);
		
		CarModel carModel = new CarModel();
		objects = carModel.getCarModelWithNurental();
		
		this.rentalFrame = frame;
		Object[] columnNames = { "车牌号", "车型", "单价（元/日）" };
		this.rentalFrame.show(objects, columnNames);
	}
	
	/**
	 * 
	 * @param phone 还车人手机号
	 */
	
	public  void ruturnCar(String phone) {
		
		RentalFrame frame = new RentalFrame("还车");
		
		
		frame.observer = this;
		frame.phone = phone;
		frame.setVisible(true);
		
		RentalInfoModel reInfoModel = new RentalInfoModel();
		objects = reInfoModel.getRentalInfoByPhone(phone);
		
		this.rentalFrame = frame;
		Object[]  colum = {"车牌号","租赁日期","型号","单价(元/天)"};

		this.rentalFrame.show(objects, colum);
	}
	public void reload(int selected){
		JTable table = this.rentalFrame.getTable();
		String carId = (String)objects[table.getSelectedRow()][0];
		
		for (int i = 0; i < objects.length-1; i++) {
			if (i >= selected) {
				objects[i] = objects[i+1];
			}
		}
		Object[][] obs = new Object[objects.length-1][3];
		
		for (int i = 0; i < obs.length; i++) {
			obs[i] = objects[i];
		}
		objects = obs;
		reloadWithoutFlush();
		Thread thread = new Thread(new Runnable() {
			public void run() {
				CarModel carModel = new CarModel();
				carModel.rentalCar(carId);
			}
		});
		thread.start();
	}
	
	/**
	 * 刷新
	 */
	public void reloadData(){

		RentalFrame rentalFrame = this.rentalFrame;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				CarModel carModel = new CarModel();
				objects =  carModel.getCarModels();
				JTable table = rentalFrame.getTable();
				Object[] columnNames = { "车牌号", "车型", "单价（元/日）" };
				DefaultTableModel model = new DefaultTableModel(objects,columnNames);
				table.setModel(model);
			}
		});
		thread.start();
		return;
	}
	private void reloadWithoutFlush() {
		JTable table = this.rentalFrame.getTable();
//		if () {
//			
//		}
		Object[] columnNames = { "车牌号", "车型", "单价（元/日）" };
		
		DefaultTableModel model = new DefaultTableModel(objects,columnNames);
		table.setModel(model);
		
		return;
	}
	
}
