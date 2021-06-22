package presentation;

//importing necessary packages
import javax.swing.*;
import business.Leave;
import data.DBAccess;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;




public class ManageLeave extends JPanel{

	//declaration and initialization
	private JLabel lblGuiName, lblEmpID, lblAppId ,lblComment, blank;
	private JTextField txtEmpID,txtAppId,txtComment ;
	private JButton butAccept,butDecline;
	private DBAccess db=null;


	//designing panel
	private void initialize() {

		blank=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText("Manage Leaves"); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.DARK_GRAY);

		lblEmpID = new JLabel("Employee ID: ");
		lblEmpID.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblAppId = new JLabel("Application Id: ");
		lblAppId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblComment = new JLabel("Comment: ");
		lblComment.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));


		txtEmpID = new JTextField();//creating empty text fields
		txtEmpID.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtAppId = new JTextField();//creating empty text fields
		txtAppId.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtComment = new JTextField();
		txtComment.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		butAccept=new JButton("ACCEPT");
		butAccept.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butAccept.setBackground(Color.GREEN);
		butAccept.setForeground(Color.WHITE);

		butDecline=new JButton("DECLINE");
		butDecline.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butDecline.setBackground(Color.RED);
		butDecline.setForeground(Color.WHITE);

		//deciding layout
		this.setLayout(new GridLayout(5,2));  //5 rows 2 columns
		this.setBackground(Color.PINK);

		this.add(blank);
		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(lblEmpID);
		this.add(txtEmpID);
		this.add(lblAppId);
		this.add(txtAppId);
		this.add(lblComment);
		this.add(txtComment);
		this.add(butAccept);
		this.add(butDecline);

	}

	//constructor
	public ManageLeave() {
		this.initialize();
		butAccept.addActionListener(new AcceptButtonHandler());//calling inner class onclick of accept button
		butDecline.addActionListener(new DeclineButtonHandler());//calling inner class onclick of decline button

	}


	//for event handling we create inner class
	private class AcceptButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			//extracting text 
			String empID=txtEmpID.getText();
			String appID=txtAppId.getText();
			String Cause=txtComment.getText();

			//validating textfields
			if(!isValidData()) {
				return;
			}

			//creating a Leave object
			Leave tt = new Leave(empID,appID,Cause);

			//modification of data in database
			try{
				db=new DBAccess();
				int count=db.acceptLeave(tt);

				String result="Leave Accepted for \n Employee: " + empID + "\n";//built a string  to produce output
				JOptionPane.showMessageDialog(null, result+count+" Leave Accepted","Successful",JOptionPane.INFORMATION_MESSAGE);


			}
			catch(SQLException e1) {
				//Warning message upon failure of updation
				JOptionPane.showMessageDialog(null, "Could not Accept!", "Failed!!!", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
	}

	//for event handling we create inner class
	private class DeclineButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			//extracting data from textfield
			String empID=txtEmpID.getText();
			String appID=txtAppId.getText();
			String Cause=txtComment.getText();

			//validating textfield
			if(!isValidData()) {
				return;
			}

			//creating a Leave object
			Leave tt = new Leave(empID,appID,Cause);

			//modification of data in database
			try{
				db=new DBAccess();
				int count=db.declineLeave(tt);

				String result="Leave Decline for \n Employee: " + empID + "\n";//built a string  to produce output
				JOptionPane.showMessageDialog(null, result+" Leave Declined","Successful",JOptionPane.INFORMATION_MESSAGE);


			}
			catch(SQLException e1) {
				//Warning message upon failure of updation
				JOptionPane.showMessageDialog(null, "Could not Decline!", "Failed!!!", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}
	}



	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtEmpID,"Employee ID")) return false;
		if(!Validate.isPresent(txtAppId,"Application ID")) return false;
		return true;
	}





}

