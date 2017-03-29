package com.carRental.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class RentalInfoPanel extends JPanel {
	public JScrollPane scrollPane = null;
	private JTable table = null;
	/**
	 * Create the panel.
	 */
	public RentalInfoPanel() {
		setLayout(null);		
	}
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
}
