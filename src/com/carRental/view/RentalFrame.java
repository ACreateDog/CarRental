package com.carRental.view;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.carRental.observer.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class RentalFrame extends JFrame {
	
	private JPanel contentPane;
	private JScrollPane scrollPane = null;
	private JTable table = null;
	public String phone = null;
	public RentalCallback observer = null;
	public JButton button = null;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RentalFrame frame = new RentalFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
		/**
	 * Create the frame.
	 */
	public RentalFrame(String title) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton(title);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (observer != null && button.getText().equals("租车")) {
					String carID = String.valueOf(table.getSelectedRow());
					observer.rentalCallBack(carID,phone);
				}else if(observer != null && button.getText().equals("还车")) {
					String carID = String.valueOf(table.getSelectedRow());
					observer.returnCallBack(carID, phone);
				}
			}
		});
		button.setBounds(434, 19, 117, 29);
		contentPane.add(button);
	}
	public void show(Object[][] data,Object[] columnNames) {
		
		JTable table = new JTable(data, columnNames);
		table.setBackground(Color.CYAN);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(120, 63, 458, 301);
		//scrollPane.add(table);
		this.setTable(table);
		this.getTable().repaint();
		this.repaint();
		table.setVisible(true);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 48, 587, 283);
		contentPane.add(scrollPane);
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
