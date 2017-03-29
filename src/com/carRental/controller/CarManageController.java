package com.carRental.controller;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.carRental.model.CarModel;
import com.carRental.observer.Observer;
import com.carRental.view.AddCarView;
import com.carRental.view.CarManagePanel;


public class CarManageController implements Observer{
	static Object[][] objects = null;
	
	CarManagePanel carManagePanel = null;
	
	public CarManageController(CarManagePanel carManagePanel) {
		carManagePanel.observer = this;
		this.carManagePanel = carManagePanel;	
	}
	
	public void addData() {
		String[] arr = {"aa","dd","777"};
		this.getDefaultTableModel().addRow(arr);
		reload();
	}
	private DefaultTableModel getDefaultTableModel(){
		JTable table = carManagePanel.getTable();
		
		
		DefaultTableModel model = null;

		TableModel tm = table.getModel();

		if(tm  instanceof DefaultTableModel)
		    model = (DefaultTableModel)tm;
		System.out.println("model:"+model);
		
		return model;
	}
	public void reload(){
		JTable table = carManagePanel.getTable();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.fireTableDataChanged();
	}
	public void show(Object[][] data,Object[] columnNames) {
		objects = data;
		
		
		JTable table = new JTable(objects, columnNames);
		table.setBackground(Color.CYAN);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(120, 63, 458, 301);
		this.carManagePanel.add(table);
		this.carManagePanel.setTable(table);
		this.carManagePanel.getTable().repaint();
		this.carManagePanel.repaint();
		table.setVisible(true);
	}
	/**
	 * edit 编辑
	 */
	public void edit() {
		
	}
	/**
	 * delete 删除
	 */
	public void delete(String[] arr){
		JTable table = this.carManagePanel.getTable();
		String carId = (String)objects[table.getSelectedRow()][0];
		
		for (int i = 0; i < objects.length-1; i++) {
			if (i >= table.getSelectedRow()) {
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
				carModel.deleteCarById(carId);

			}
		});
		thread.start();
	}
	
	/**
	 * add 添加
	 */
	public void reloadData(){
		CarManagePanel carManagePanel = this.carManagePanel;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				CarModel carModel = new CarModel();
				objects =  carModel.getCarModels();
				JTable table = carManagePanel.getTable();
				Object[] columnNames = { "车牌号", "车型", "单价（元/日）" };
				DefaultTableModel model = new DefaultTableModel(objects,columnNames);
				table.setModel(model);
			}
		});
		thread.start();
		return;
	}
	public void reloadWithoutFlush() {
		JTable table = this.carManagePanel.getTable();
		Object[] columnNames = { "车牌号", "车型", "单价（元/日）" };
		DefaultTableModel model = new DefaultTableModel(objects,columnNames);
		table.setModel(model);
		
		return;
	}
	public void addCallBack(String carID, String shapesize, float singlePrice) {
		
		CarModel carModel = new CarModel();
		carModel.addCar(carID, shapesize, singlePrice);
		reloadData();
	
		return;
	}

	public void addCallBack(){
		
		AddCarView dialog = new AddCarView();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.observer = this;
		dialog.setVisible(true);
	}
}
