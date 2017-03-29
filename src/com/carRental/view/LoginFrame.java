package com.carRental.view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carRental.controller.UserActionController;
import com.carRental.observer.LoginObserver;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LoginFrame extends JFrame {
	public LoginObserver observer = null;
	private JPanel contentPane;
	private JTextField account;
	private JPasswordField passwordfld;
	private JLabel title;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel acount = new JLabel("账号");
		acount.setBounds(168, 95, 61, 16);
		contentPane.add(acount);
		
		JLabel password = new JLabel("密码");
		password.setBounds(169, 143, 61, 16);
		contentPane.add(password);
		
		account = new JTextField();
		account.setBounds(241, 89, 134, 28);
		contentPane.add(account);
		account.setColumns(10);
		
		passwordfld = new JPasswordField();
		passwordfld.setBounds(242, 137, 134, 28);
		contentPane.add(passwordfld);
		
		JButton btnNewButton = new JButton("登陆");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (observer != null) {
					String name = account.getText();
					String password = passwordfld.getText();
					
					observer.loginCallback(name,password);
				}
				
			}
		});
		
		btnNewButton.setBounds(215, 212, 117, 29);
		contentPane.add(btnNewButton);
		title = new JLabel("租赁后台管理员登陆");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(131, 23, 285, 34);
		contentPane.add(title);
	}
}
