package presentation;

//importing necessary packages
import javax.swing.*;
import business.Test;
import java.awt.*;
import java.awt.event.*;
import data.DAOFactory;
import data.Global;
import data.TestDAO;


public class LoginPanel extends JPanel{

	//declaration and initialization
	private JLabel lblGuiName, lblUsername, lblPassword , blank;
	private JTextField txtUserName;
	private JButton butLogin, butRegister;
	private JCheckBox chkManager;
	JPasswordField passwordField = new JPasswordField(20);

	private TestDAO tDao = DAOFactory.getTestDAO();

	//designing panel
	private void initialize() {
		blank=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText("Login Page"); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.DARK_GRAY);

		lblUsername=new JLabel("Username: "); //another way to create object using constructors
		lblUsername.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtUserName = new JTextField();
		txtUserName.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		passwordField.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		passwordField.setEchoChar('*');

		butLogin=new JButton("LOG IN");
		butLogin.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butLogin.setBackground(Color.GREEN);
		butLogin.setForeground(Color.WHITE);

		butRegister=new JButton("REGISTER");
		butRegister.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butRegister.setBackground(Color.BLUE);
		butRegister.setForeground(Color.WHITE);

		chkManager=new JCheckBox("Are you manager?");
		chkManager.setBackground(Color.PINK);

		this.setLayout(new GridLayout(6,2));  //6 rows 2 columns
		this.setBackground(Color.PINK);


		this.add(blank);
		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(lblUsername);
		this.add(txtUserName);
		this.add(lblPassword);
		this.add(passwordField);
		this.add(chkManager);
		this.add(butLogin);
		this.add(butRegister);
	}

	//constructor
	public LoginPanel() {
		this.initialize();
		butLogin.addActionListener(new LogInButtonHandler());//calling inner class onclick of login button
		butRegister.addActionListener(new RegisterButtonHandler());//calling inner class onclick of Register button
	}


	//for event handling we create inner class
	private class LogInButtonHandler implements ActionListener{


		//this class is going to handle login button
		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {
			//declaring
			String password;

			String userName=txtUserName.getText();//extract whatever the text input is
			char[] pass=passwordField.getPassword();//extract whatever the password input is

			//validating textFields
			if(!isValidData()) {
				return;
			}

			//checking entry in password field
			if(pass == null || pass.length == 0) {

				//setting tooltipText
				passwordField.setToolTipText("Please Enter Password");

				//error message upon no entry
				JOptionPane.showMessageDialog(null,  "Please Enter Password","No Password",JOptionPane.ERROR_MESSAGE);
			}else if( pass.length < 9) {
				//error message upon password length less than 9 characters
				JOptionPane.showMessageDialog(null,  "Password must contain at least 9 characters","Password Length",JOptionPane.ERROR_MESSAGE);
				passwordField.setToolTipText("Password must contain at least 9 characters");
			}
			else {
				//upon correct password entry
				password=new String(pass);

				//if checkbox is selected
				if(chkManager.isSelected()) {

					//creating a Test object
					Test test = tDao.getTest(userName,password,"manager");
					if (test == null) {
						//if employee is not manager,shows error message
						JOptionPane.showMessageDialog(null, "Invalid details", "can not login", JOptionPane.ERROR_MESSAGE);
					}
					else{
						//if employee is manager,user is logged in
						String result="Username: " + userName + "\n";//built a string  to produce output
						Global.employeeID=userName;
						JOptionPane.showMessageDialog(null, result+ "User Logged in...","Logged in",JOptionPane.INFORMATION_MESSAGE);  

						new MLeaveFrame().setVisible(true);
					}

				}else {
					//creating a Test object
					Test test = tDao.getTest(userName,password);
					if (test == null) {
						//if invalid data,shows error message
						JOptionPane.showMessageDialog(null, "Invalid details", "can not login", JOptionPane.ERROR_MESSAGE);
					}
					else{
						//if valid data,user is logged in
						String result="Username: " + userName + "\n";//built a string  to produce output
						Global.employeeID=userName;
						JOptionPane.showMessageDialog(null, result+ "User Logged in...","Logged in",JOptionPane.INFORMATION_MESSAGE);  

						new LeaveFrame().setVisible(true);
					}
				}
			}
		}

	}

	//this class is going to handle Register button
	private class RegisterButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			//linking to register page
			JOptionPane.showMessageDialog(null, "Going to Register Page","Register Yourself",JOptionPane.INFORMATION_MESSAGE);
			new SigninFrame().setVisible(true);
		}

	}


	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtUserName,"User Name")) return false;
		return true;
	}







}
