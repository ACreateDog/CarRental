package com.carRental.controller;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import com.carRental.view.RentalInfoPanel;

public class RentalInfoController {
	
	RentalInfoPanel rentalInfoPanel = null;
	public static Object[][] objects = null;
	
	public RentalInfoController(RentalInfoPanel rentalInfoPanel) {
		this.rentalInfoPanel = rentalInfoPanel;
	}
public void show(Object[][] data,Object[] columnNames) {
		
		JTable table = new JTable(data, columnNames);
		
		table.setBackground(Color.CYAN);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(120, 63, 458, 301);
		//scrollPane.add(table);
		if (this.rentalInfoPanel == null) {
			System.out.println(this.rentalInfoPanel);
			return;
		}
		this.rentalInfoPanel.setTable(table);
		this.rentalInfoPanel.getTable().repaint();
		this.rentalInfoPanel.repaint();
		table.setVisible(true);
		this.rentalInfoPanel.scrollPane = new JScrollPane(table);
		this.rentalInfoPanel.scrollPane.setBounds(6, 48, 587, 283);
		this.rentalInfoPanel.add(this.rentalInfoPanel.scrollPane);
	}
	
}
