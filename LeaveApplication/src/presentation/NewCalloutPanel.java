package presentation;

//importing necessary packages
import javax.swing.*;
import business.ViewLeave;
import data.DBAccess;
import java.awt.*;
import java.awt.event.*;



public class NewCalloutPanel extends JPanel{

	//declaration
	private JLabel lblGuiName, lblEmpId,blank,blank1,blank2,blank3;
	private JTextField txtEmpId;
	private JButton butAllocate;

	private DBAccess db1;

	//designing panel
	private void initialize() {

		blank=new JLabel();
		blank1=new JLabel();
		blank2=new JLabel();
		blank3=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText("ALLOCATE CALLOUTS "); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.DARK_GRAY);

		lblEmpId=new JLabel("Enter Employee ID:  "); //another way to create object using constructors
		lblEmpId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtEmpId = new JTextField();
		txtEmpId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		butAllocate=new JButton("ALLOCATE");
		butAllocate.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butAllocate.setBackground(Color.GREEN);
		butAllocate.setForeground(Color.WHITE);


		//deciding layout
		this.setLayout(new GridLayout(4,2));  //4 rows and 2 columns
		this.setBackground(Color.PINK);


		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(blank);
		this.add(lblEmpId);
		this.add(blank1);
		this.add(txtEmpId);
		this.add(blank2);
		this.add(blank3);
		this.add(butAllocate);

	}

	//constructor
	public NewCalloutPanel() {
		this.initialize();
		butAllocate.addActionListener(new AllocateButtonHandler());//calling inner class onclick of allocate button
	}


	//for event handling we create inner class
	private class AllocateButtonHandler implements ActionListener{

		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {

			String empId=txtEmpId.getText();//extract whatever the text input is

			//validating text fields
			if(!isValidData()) {
				return;
			}

			//creating a ViewLeave object
			ViewLeave tt = new ViewLeave(empId);

			//insertion of data into database
			try{
				db1=new DBAccess();
				db1.allocateCallout(tt);
				JOptionPane.showMessageDialog(null,  "Allocated Successfully","Allocate Callout",JOptionPane.INFORMATION_MESSAGE);  
			}
			catch( Exception e1) {
				//Warning message upon failure of insertion
				JOptionPane.showMessageDialog(null, "Allocation failed!", "Allocate Callout", JOptionPane.WARNING_MESSAGE);
			}


		}

	}



	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtEmpId,"Employee ID ")) return false;

		return true;
	}

}
