package presentation;

//importing necessary packages
import java.awt.BorderLayout;
import javax.swing.*;

public class SigninFrame extends JFrame{

	//Frame has a panel

	private JPanel mainPanel;

	public SigninFrame() {

		mainPanel=new SigninPanel();

		//location of panel to be added 
		this.add(mainPanel,BorderLayout.CENTER); //this means Frame because we are in frame class
		this.setTitle("LOG IN");

		//size of frame is 400 px wide and 200px height
		this.setSize(400,200);
		this.setResizable(false); //frame cannot be maximised or minimised
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits whole application on closing of one window;;; for eg. if there are 4 windows and user closes only one window, this will close whole application because of this.
	}


}

