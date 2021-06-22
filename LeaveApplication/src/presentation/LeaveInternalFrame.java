package presentation;

//importing necessary packages 
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;



public class LeaveInternalFrame extends JInternalFrame {


	public LeaveInternalFrame(JPanel panel) {

		//adding panel to frame
		this.add(panel);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("User's GUI");
		this.setIconifiable(true);

		this.setClosable(true);
		this.setSize(550,250);


	}


}
