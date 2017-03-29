package Main;

import java.awt.EventQueue;
import com.carRental.view.*;

public class MainOrigin {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {				
				try {
					CarRentalFrame window = new CarRentalFrame();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}
}
