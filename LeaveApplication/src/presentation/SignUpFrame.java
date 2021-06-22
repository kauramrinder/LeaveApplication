package presentation;

//importing necessary packages
import java.awt.BorderLayout;
import javax.swing.*;

public class SignUpFrame extends JFrame{

	//declaration
	private JPanel mainPanel;
	private String fName, lName,dob, empID, compName;

	//constructor
	public SignUpFrame(String fName,String lName,String dob,String empID,String compName) {

		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.empID = empID;
		this.compName = compName;

		mainPanel=new SignUpPanel( fName, lName, dob,empID, compName);

		//location of panel to be added 
		this.add(mainPanel,BorderLayout.CENTER); //this means Frame because we are in frame class
		this.setTitle("LOG IN");

		//size of frame is 400 px wide and 200px height
		this.setSize(400,200);
		this.setResizable(false); //frame cannot be maximised or minimised
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}

