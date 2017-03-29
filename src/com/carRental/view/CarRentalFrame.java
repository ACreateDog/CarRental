package com.carRental.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.carRental.controller.AdminController;
import com.carRental.controller.UserActionController;
import com.carRental.controller.VerifyController;
import com.carRental.controller.LoginController;

public class CarRentalFrame {
	
	private JFrame frame;
	private JTextField phone;
	private ImageIcon background1;    //背景
    private JPanel jpanel1;           //面板
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CarRentalFrame window = new CarRentalFrame();
//					window.getFrame().setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	 private void setBackground()
//     {
//             //背景图片
//             background1 = new ImageIcon("images\\bgimg.jpg");
//             System.out.println(background1);
//             JLabel label = new JLabel(background1);
//
//             //获得窗口内容的面板
//             jpanel1 = (JPanel)getFrame().getContentPane();              
//             //使面板透明化
//             jpanel1.setOpaque(true);  
//              
//             //加入背景
//             this.getFrame().add(label,new Integer(Integer.MIN_VALUE) );
//             this.getFrame().setBounds( 0,0,background1.getIconWidth(),background1.getIconHeight() );               
//     }

	/**
	 * Create the application.
	 */
	public CarRentalFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 540, 371);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton rentalBtn = new JButton("我要租车");
		rentalBtn.setBounds(165, 151, 96, 29);
		
		rentalBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (VerifyController.verifyPhoneNumber(phone.getText())) {
					UserActionController userActionController = new UserActionController();
					userActionController.rentalCar(phone.getText());
				}
			}
			
		});
		
		getFrame().getContentPane().setLayout(null);
		getFrame().getContentPane().add(rentalBtn);
		
		JButton returnBtn = new JButton("我要还车");
		returnBtn.setBounds(279, 151, 96, 29);
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VerifyController.verifyPhoneNumber(phone.getText())) {
					UserActionController userActionController = new UserActionController();
					 
					userActionController.ruturnCar(phone.getText());
				}else {
					
				}
			}
		});
		getFrame().getContentPane().add(returnBtn);
		
		phone = new JTextField();
		phone.setBounds(165, 81, 210, 43);
		phone.setBackground(Color.PINK);
		getFrame().getContentPane().add(phone);
		phone.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("租车系统");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Geneva", Font.BOLD, 25));
		lblNewLabel.setBounds(192, 33, 156, 36);
		getFrame().getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("管理员登陆");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame frame = new LoginFrame();
				
				LoginController controller = new LoginController(frame);
				frame.observer = controller;
				
			}
		});
		btnNewButton.setBounds(417, 314, 117, 29);
		frame.getContentPane().add(btnNewButton);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
