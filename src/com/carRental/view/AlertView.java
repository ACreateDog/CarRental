package com.carRental.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class AlertView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel alertText = null;
	

	/**
	 * Create the dialog.
	 */
	public AlertView(String alertString) {
		
		setBounds(250, 200, 214, 177);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			alertText = new JLabel(alertString);
			alertText.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			
			alertText.setBounds(19, 17, 189, 93);
			contentPanel.add(alertText);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setVisible(true);
		
	}
	public void dispose() {
		this.setVisible(false);
	}
	public void setAlertText(String alertText) {
		this.alertText.setText(alertText);
	}
}
