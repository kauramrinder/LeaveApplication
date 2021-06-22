package presentation;

//importing necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SigninPanel extends JPanel{

	//declaration
	private JLabel lblGuiName, lblFirstname, lblLastname, lblDOB , lblEmpId, lblCompName, blank;
	private JTextField txtFirstname, txtLastname, txtDOB, txtEmpId, txtCompName;
	private JButton butNext;

	//designing of panel
	private void initialize() {
		blank=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText(" Register Yourself Up... "); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.MAGENTA);

		lblFirstname=new JLabel("Firstname: "); //another way to create object using constructors
		lblFirstname.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblLastname = new JLabel("Lastname: ");
		lblLastname.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblDOB = new JLabel("D.O.B: ");
		lblDOB.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblEmpId = new JLabel("Employee ID: ");
		lblEmpId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblCompName = new JLabel("Company Name: ");
		lblCompName.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtFirstname = new JTextField();
		txtFirstname.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtLastname = new JTextField();//creating empty text fields
		txtLastname.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtDOB = new JTextField();
		txtDOB.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtEmpId = new JTextField();
		txtEmpId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtCompName = new JTextField();
		txtCompName.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		butNext=new JButton("NEXT>>> ");
		butNext.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butNext.setBackground(Color.BLUE);
		butNext.setForeground(Color.WHITE);

		//deciding layout
		this.setLayout(new GridLayout(7,2));  //7 rows 2 columns
		this.setBackground(Color.YELLOW);

		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(blank);
		this.add(lblFirstname);
		this.add(txtFirstname);
		this.add(lblLastname);
		this.add(txtLastname);
		this.add(lblDOB);
		this.add(txtDOB);
		this.add(lblEmpId);
		this.add(txtEmpId);
		this.add(lblCompName);
		this.add(txtCompName);
		this.add(butNext);

	}

	//constructor
	public SigninPanel() {
		this.initialize();
		butNext.addActionListener(new NextButtonHandler());//calling inner class onclick of next button

	}


	//for event handling we create inner class
	private class NextButtonHandler implements ActionListener{

		//this class is going to handle next button
		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {

			String fName=txtFirstname.getText();//extract whatever the text input is
			String lName=txtLastname.getText();
			String dob=txtDOB.getText();
			String empID=txtEmpId.getText();
			String compName=txtCompName.getText();

			//validating textfields
			if(!isValidData()) {
				return;
			}

			String result="Firstname: " + fName + "\n";//built a string  to produce output
			JOptionPane.showMessageDialog(null, result+ "Goint to next page...","Next...",JOptionPane.INFORMATION_MESSAGE);  
			new SignUpFrame(fName, lName, dob,empID,compName).setVisible(true);//making next frame visible
		}





	}




	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtFirstname,"First Name")) return false;
		if(!Validate.isPresent(txtLastname,"Last Name")) return false;
		if(!Validate.isPresent(txtDOB,"D.O.B")) return false;
		if(!Validate.isPresent(txtEmpId,"Employee ID")) return false;
		if(!Validate.isPresent(txtCompName,"Company Name")) return false;
		return true;
	}





}
