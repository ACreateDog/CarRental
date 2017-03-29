package com.carRental.controller;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.carRental.model.CarModel;
import com.carRental.model.RentalInfoModel;
import com.carRental.view.AdminFrame;

public class AdminController  implements ChangeListener{
	CarManageController carManageController = null;
	MoneyController moneyController = null;
	RentalInfoController rentalInfoController = null;

	AdminFrame adminFrame = null;
	public AdminController(AdminFrame adminFrame){
		this.adminFrame = adminFrame;
		this.adminFrame.navTabPanel.addChangeListener(this);
		carManageController = new CarManageController(adminFrame.getCarManagePanel());
		rentalInfoController = new RentalInfoController(adminFrame.getRentalInfoPanel());
		moneyController = new MoneyController(adminFrame.getMoneyPanel());
		RentalInfoModel rentalInfoModel = new RentalInfoModel();
		Object[]  colum = {"车牌号","租赁日期","型号","单价(元/天)"};

		Object[][]  data = rentalInfoModel.getRentalInfoByPhone(""); 
		RentalInfoController.objects = data;
		rentalInfoController.show(data,colum);
		
		this.adminFrame.setVisible(true);
		
	}

	public void stateChanged(ChangeEvent e) {
		int index = this.adminFrame.navTabPanel.getSelectedIndex();
		DefaultTableModel model = null;
		if (index == 0) {
			RentalInfoModel rentalInfoModel = new RentalInfoModel();
			Object[]  colum = {"车牌号","租赁日期","型号","单价(元/天)"};

			Object[][]  data = rentalInfoModel.getRentalInfoByPhone(""); 
			RentalInfoController.objects = data;
			rentalInfoController.show(data,colum);


		}else if (index == 1) {
			CarModel carModel = new CarModel();
			Object[] columnNames = { "车牌号", "车型", "单价（元/日）" };

			Object[][]  data = carModel.getCarModels(); 
			CarManageController.objects = data;
			carManageController.show(data, columnNames);
		}else if (index == 2) {
		}


	}


}
