package passwordcracker;
import java.awt.EventQueue;

import view.MainFrame;

public class Pwdcrckr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller mc = new Controller();
					mc.readFiles();
					MainFrame frame = new MainFrame(mc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
