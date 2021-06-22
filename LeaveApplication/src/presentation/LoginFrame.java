package presentation;

//importing necessary packages
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import data.DBAccess;
import javax.swing.*;

public class LoginFrame extends JFrame{

	//Frame has a panel
	//declaring and initializing
	private JPanel mainPanel;
	private DBAccess db=null;

	//constructor
	public LoginFrame() {

		mainPanel=new LoginPanel();

		//location of panel to be added 
		this.add(mainPanel,BorderLayout.CENTER); //this means Frame because we are in frame class
		this.setTitle("LOG IN");

		//size of frame is 400 px wide and 200px height
		this.setSize(400,200);
		this.setResizable(false); //frame cannot be maximised or minimised
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits whole application on closing of one window;;; for eg. if there are 4 windows and user closes only one window, this will close whole application because of this.

		//disconnecting database upon window closure
		addWindowListener(new WindowAdapter() {
			public void windowsClosing(WindowEvent e) {
				try {
					db.disconnect();
					System.exit(0);
				}
				catch(SQLException e1) {
					e1.printStackTrace();
					System.exit(1);
				}
			}
		});

	}


}
