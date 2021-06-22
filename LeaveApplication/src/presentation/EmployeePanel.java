package presentation;

//importing necessary packages
import javax.swing.*;
import business.ViewLeave;
import data.DBAccess;
import java.awt.*;
import java.awt.event.*;



public class EmployeePanel extends JPanel{

	////declaration and initialization
	private JLabel lblGuiName, lblEmpId, blank;
	private JTextField txtEmpId;
	private JButton butRemove;

	private DBAccess db1;

	//designing panel
	private void initialize() {

		blank=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText("REMOVE EMPLOYEE"); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.DARK_GRAY);

		lblEmpId=new JLabel("Enter Employee ID:  "); //another way to create object using constructors
		lblEmpId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtEmpId = new JTextField();
		txtEmpId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		butRemove=new JButton("DELETE");
		butRemove.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butRemove.setBackground(Color.RED);
		butRemove.setForeground(Color.WHITE);


		//deciding layout
		this.setLayout(new GridLayout(4,2));  //4 rows 2 columns
		this.setBackground(Color.PINK);

		this.add(blank);
		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(lblEmpId);
		this.add(txtEmpId);
		this.add(butRemove);

	}

	//constructor
	public EmployeePanel() {
		this.initialize();
		butRemove.addActionListener(new RemoveButtonHandler());//calling inner class onclick of Remove button
	}


	//for event handling we create inner class
	private class RemoveButtonHandler implements ActionListener{

		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {

			String empId=txtEmpId.getText();//extract whatever the text input is

			//validating textfields
			if(!isValidData()) {
				return;
			}

			//creating a ViewLeave object
			ViewLeave tt = new ViewLeave(empId);

			//deletion of data from database
			try{
				db1=new DBAccess();
				db1.delEmployee(tt);
				JOptionPane.showMessageDialog(null,  "All the employee records are removed successfully.","Remove Employee",JOptionPane.INFORMATION_MESSAGE);  
			}
			catch( Exception e1) {
				//Warning message upon deletion failure
				JOptionPane.showMessageDialog(null, "Deletion failed!", "Remove Employee", JOptionPane.WARNING_MESSAGE);
			}


		}

	}



	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtEmpId,"Employee ID ")) return false;

		return true;
	}

}
