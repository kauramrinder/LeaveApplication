package presentation;

//importing necessary packages
import javax.swing.*;
import business.Test;
import data.DBAccess;
import data.Global;
import data.DAOFactory;
import data.TestDAO;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class SignUpPanel extends JPanel{

	//declaration and initialization
	private JLabel lblGuiName, lblPosition, lblPassword, lblReEnterPass, blank;
	private JTextField txtPosition;
	private JButton butSignIn, butPrevious;
	JPasswordField passwordField = new JPasswordField(20);
	JPasswordField passwordField1 = new JPasswordField(20);

	private String fName, lName,dob, empID, compName;

	private TestDAO tDao = DAOFactory.getTestDAO();

	//designing panel
	private void initialize() {
		blank=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText(" Register Yourself Up... "); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.MAGENTA);

		lblPosition=new JLabel("Job Position: "); //another way to create object using constructors
		lblPosition.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblReEnterPass = new JLabel("Re-Enter Password: ");
		lblReEnterPass.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));


		txtPosition = new JTextField();
		txtPosition.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		passwordField.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		passwordField.setEchoChar('*');
		passwordField1.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		passwordField1.setEchoChar('*');


		butSignIn=new JButton("SIGN IN ");
		butSignIn.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butSignIn.setBackground(Color.GREEN);
		butSignIn.setForeground(Color.WHITE);

		butPrevious=new JButton("<< PREVIOUS ");
		butPrevious.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butPrevious.setBackground(Color.BLUE);
		butPrevious.setForeground(Color.WHITE);

		//deciding layout
		this.setLayout(new GridLayout(5,2));  //5 rows 2 columns
		this.setBackground(Color.YELLOW);

		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(blank);
		this.add(lblPosition);
		this.add(txtPosition);
		this.add(lblPassword);
		this.add(passwordField);
		this.add(lblReEnterPass);
		this.add(passwordField1);
		this.add(butPrevious);
		this.add(butSignIn);

	}

	//constructor
	public SignUpPanel(String fName,String lName,String dob,String empID,String compName) {
		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.empID = empID;
		this.compName = compName;

		this.initialize();

		butPrevious.addActionListener(new PreviousButtonHandler());//calling inner class onclick of Previous button
		butSignIn.addActionListener(new SignUpButtonHandler());//calling inner class onclick of signup button

	}


	//handling previous button
	private class PreviousButtonHandler implements ActionListener{

		//this class is going to handle previous button
		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {

			String result="Going to previous page..." + "\n";//built a string  to produce output
			JOptionPane.showMessageDialog(null, result,"Previous...",JOptionPane.INFORMATION_MESSAGE); 
			new SigninFrame().setVisible(true);

		}

	}


	//for event handling we create inner class
	private class SignUpButtonHandler implements ActionListener{

		//this class is going to handle signup button
		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {

			//declaring string variables
			String password,reEnterPass;

			String result="Signed In Successfully!! ";//message for output
			String position=txtPosition.getText();//extract whatever the text input is

			char[] pass=passwordField.getPassword();//extract whatever the password input is
			char[] pass1=passwordField1.getPassword();

			//validating textfields
			if(!isValidData()) {
				return;
			}

			//checking entry in password field
			if(pass == null || pass.length == 0) {

				JOptionPane.showMessageDialog(null,  "Please Enter Password","No Password",JOptionPane.ERROR_MESSAGE);
				passwordField.setToolTipText("Please Enter Password");

			}else if( pass.length < 9) {
				//error message upon password length less than 9 characters
				JOptionPane.showMessageDialog(null,  "Password must contain at least 9 characters","Password Length",JOptionPane.ERROR_MESSAGE);
				passwordField.setToolTipText("Password must contain at least 9 characters");

			}
			else if(Arrays.equals(pass, pass1))  {
				//when both passwords match
				password=new String(pass);
				reEnterPass=new String(pass1);

				//creating a Test object
				Test tt = new Test(fName, lName, dob,empID,compName,position,password,reEnterPass);

				//insertion of data into database and text file
				try {

					DBAccess db = new DBAccess();
					db.addEmployee(tt);
					tDao.addTest(tt);

					JOptionPane.showMessageDialog(null, result+ "\n\nGoint to next page...","Linking...",JOptionPane.INFORMATION_MESSAGE); 
					Global.employeeID=empID;
					new LeaveFrame().setVisible(true);
				}catch(Exception e1) {
					//Warning message upon failure of insertion
					JOptionPane.showMessageDialog(null, "Not Saved!", "Registration Page", JOptionPane.WARNING_MESSAGE);
				}
			}else
				//when both passwords do not match
				JOptionPane.showMessageDialog(null,"Re-Enter same Password","Your Password does not match",JOptionPane.ERROR_MESSAGE);



		}

	}




	//validating input
	public boolean isValidData() {
		if(!Validate.isPresent(txtPosition,"First Name")) return false;
		return true;
	}





}
