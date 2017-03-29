package com.carRental.view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carRental.controller.AdminController;

import javax.swing.JTabbedPane;
import net.sf.json.*;

public class AdminFrame extends JFrame {
	public AdminController aController = null; 
	public JTabbedPane navTabPanel = null ;
	private JPanel contentPane;
	private RentalInfoPanel rentalInfoPanel = null;
	private CarManagePanel carManagePanel  = null;
	private MoneyPanel moneyPanel = null;

	public AdminFrame() {
		
		setTitle("管理员");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		navTabPanel = new JTabbedPane(JTabbedPane.TOP);
		navTabPanel.setToolTipText("");
		navTabPanel.setBounds(6, 6, 719, 424);
		contentPane.add(navTabPanel);
		
		rentalInfoPanel = new RentalInfoPanel();
		navTabPanel.addTab("租赁信息", null, rentalInfoPanel, null);
		carManagePanel  = new CarManagePanel();
		navTabPanel.addTab("车辆管理", null, carManagePanel, null);
//		moneyPanel = new MoneyPanel();
//		navTabPanel.addTab("盈利查看", null, moneyPanel, null);
//		
		
		navTabPanel.addChangeListener(aController);
	}

	public MoneyPanel getMoneyPanel() {
		return moneyPanel;
	}


	public void setMoneyPanel(MoneyPanel moneyPanel) {
		this.moneyPanel = moneyPanel;
	}


	public RentalInfoPanel getRentalInfoPanel() {
		return rentalInfoPanel;
	}

	public void setRentalInfoPanel(RentalInfoPanel rentalInfoPanel) {
		this.rentalInfoPanel = rentalInfoPanel;
	}

	public CarManagePanel getCarManagePanel() {
		return carManagePanel;
	}

	public void setCarManagePanel(CarManagePanel carManagePanel) {
		this.carManagePanel = carManagePanel;
	}
	
}
