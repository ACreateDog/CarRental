package com.carRental.view;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.carRental.controller.CarManageController;
import com.carRental.observer.Observer;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarManagePanel extends JPanel{
	public Observer observer = null;
	public CarManageController controller = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2064929636199422954L;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public CarManagePanel(){
		setLayout(null);
		
		
		
		JButton delete = new JButton("删除");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (observer != null) {					
					observer.delete(null);
				}
			}
		});
		delete.setBounds(511, 22, 67, 29);
		add(delete);
		
		JButton add = new JButton("添加");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (observer != null) {
					observer.addCallBack();
				}
			}
		});
		add.setBounds(440, 22, 67, 29);
		add(add);	
	}
	
//	public void fillData(CarModel[] carModels) {
//		DefaultTableModel model =  (DefaultTableModel)table.getModel();	
//		if (carModels != null && table != null) {
//			String[] columnNames = {"","","",""};
//			String[][] rowData = new String[carModels.length][columnNames.length];
//			
//			for (int i = 0; i < carModels.length; i++) {
//				rowData[i][0] = carModels[i].getCarID();
//				rowData[i][1] = carModels[i].getShapeSize();
//				rowData[i][2] = String.valueOf(carModels[i].getSinglePrice());
//				
//			}
		//	table = new JTable(rowData, columnNames);
			//model.addRow();		
//		}
//	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	
}
