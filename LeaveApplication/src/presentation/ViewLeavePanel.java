package presentation;

//importing necessary packages
import javax.swing.*;
import business.ViewLeave;
import data.DBAccess;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


public class ViewLeavePanel extends JPanel{

	//declaration and initialization
	private JLabel lblGuiName, lblApplicant, lblApplicationId ,lblStatus, lblCause, blank, lblFromDate, lblToDate;
	private JTextField txtApplicant, txtApplicationId, txtStatus, txtCause, txtFrom, txtTo;
	private JButton butExit,butCheck;
	DBAccess db = null;
	ViewLeave leave = null;

	//design panel
	private void initialize() {

		blank=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText("View My Leave Application"); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.DARK_GRAY);

		lblFromDate = new JLabel("From: ");
		lblFromDate.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblToDate = new JLabel("To: ");
		lblToDate.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblApplicant=new JLabel("Applicant: "); //another way to create object using constructors
		lblApplicant.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblApplicationId = new JLabel("Application ID: ");
		lblApplicationId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblStatus = new JLabel("Status: ");
		lblStatus.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblCause = new JLabel("Cause: ");
		lblCause.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtApplicant = new JTextField();
		txtApplicant.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtApplicationId = new JTextField();//creating empty text fields
		txtApplicationId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtStatus = new JTextField();
		txtStatus.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtCause = new JTextField();
		txtCause.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtFrom = new JTextField();
		txtFrom.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtTo = new JTextField();
		txtTo.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		butExit=new JButton("EXIT");
		butExit.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butExit.setBackground(Color.BLUE);
		butExit.setForeground(Color.WHITE);

		butCheck=new JButton("CHECK");
		butCheck.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butCheck.setBackground(Color.ORANGE);
		butCheck.setForeground(Color.WHITE);



		//deciding layout
		this.setLayout(new GridLayout(9,2));  //9 rows 2 columns
		this.setBackground(Color.PINK);

		this.add(blank);
		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(lblFromDate);
		this.add(txtFrom);
		this.add(lblToDate);
		this.add(txtTo);
		this.add(lblApplicant);
		this.add(txtApplicant);
		this.add(lblApplicationId);
		this.add(txtApplicationId);
		this.add(lblStatus);
		this.add(txtStatus);
		this.add(lblCause);
		this.add(txtCause);
		this.add(butExit);
		this.add(butCheck);


	}

	//constructor
	public ViewLeavePanel() {
		this.initialize();
		butExit.addActionListener(new ExitButtonHandler());//calling inner class onclick of exit button
		butCheck.addActionListener(new CheckButtonHandler());//calling inner class onclick of check button

	}

	//for event handling we create inner class
	private class CheckButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			//extracting data from text fields
			String from=txtFrom.getText();
			String to=txtTo.getText();

			//validating text fields
			if(!isValidData()) {
				return;
			}

			//initializing a DBAccess object and retrieving the leave details of employee
			try {
				db = new DBAccess();
				leave =db.leaveAccess(from,to);
			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			} catch (SQLException e1 ) {

				e1.printStackTrace();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

			//setting data to text fields
			txtApplicant.setText(leave.getApplicant());
			txtApplicationId.setText(leave.getAppId());
			txtStatus.setText(leave.getStatus());
			txtCause.setText(leave.getCause());
		}
	}


	//for event handling we create inner class
	private class ExitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtFrom,"From: ")) return false;
		if(!Validate.isPresent(txtTo,"TO: ")) return false;
		return true;
	}





}
