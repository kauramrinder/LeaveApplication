package presentation;

//importing necessary packages
import javax.swing.*;
import business.TestLeave;
import data.DBAccess;
import java.awt.*;
import java.awt.event.*;


//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.Calendar;
//import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
//import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
//import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class LeavePanel extends JPanel{

	//declaration
	private JLabel lblGuiName, lblReason, lblFrom , lblTo, blank;
	private JTextField txtReason, txtFrom, txtTo;
	private JButton butApply;

	private DBAccess db1;
	//calendar
	//private UtilDateModel model=new UtilDateModel();
	//private UtilDateModel model1=new UtilDateModel();

	//designing panel
	private void initialize() {

		blank=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText("Leave Application"); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		lblGuiName.setForeground(Color.DARK_GRAY);

		lblReason=new JLabel("Reason: "); //another way to create object using constructors
		lblReason.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblFrom = new JLabel("From: ");
		lblFrom.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblTo = new JLabel("To: ");
		lblTo.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtReason = new JTextField();
		txtReason.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtFrom = new JTextField();//creating empty text fields
		txtFrom.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtTo = new JTextField();
		txtTo.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		butApply=new JButton("APPLY");
		butApply.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butApply.setBackground(Color.GREEN);
		butApply.setForeground(Color.WHITE);

		//calender initialisation
		//JDatePanelImpl datePanel=new JDatePanelImpl(model);
		//JDatePanelImpl datePanel1=new JDatePanelImpl(model1);
		//  JDatePickerImpl datePicker=new JDatePickerImpl(datePanel);
		//JDatePickerImpl datePicker1=new JDatePickerImpl(datePanel1);

		this.setLayout(new GridLayout(5,2));  //5 rows 2 columns
		this.setBackground(Color.PINK);

		this.add(blank);
		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(lblReason);
		this.add(txtReason);
		this.add(lblFrom);
		this.add(txtFrom);
		//this.add(datePicker);//adding date to panel
		this.add(lblTo);
		this.add(txtTo);
		//this.add(datePicker1);//adding date to panel
		this.add(butApply);

		//dealing with date models
		//Calendar cal=(Calendar) datePicker.getModel().getValue();
		//Date selectedDate=(Date) datePicker.getModel().getValue();

		//	Calendar cal1=(Calendar) datePicker1.getModel().getValue();
		// Date selectedDate1=(Date) datePicker1.getModel().getValue();
		//setting initial date
		//model.setDate(1990, 00, 01);
		//model.setSelected(true);
		//	model1.setDate(1990, 00, 01);
		//model1.setSelected(true);

		//String a=selectedDate.toString();
	}

	//constructor
	public LeavePanel() {
		this.initialize();
		butApply.addActionListener(new ApplyButtonHandler());//calling inner class onclick of apply button
	}


	//for event handling we create inner class
	private class ApplyButtonHandler implements ActionListener{

		//this class is going to handle apply button
		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {

			//extract whatever the text input is
			String reason=txtReason.getText();
			String from=txtFrom.getText(); 
			String to=txtTo.getText(); 

			//validating text fields
			if(!isValidData()) {
				return;
			}

			//creating a TestLeave object
			TestLeave tt = new TestLeave(reason,from,to);

			//insertion of data into database
			try{
				db1=new DBAccess();
				db1.addLeave(tt);
				String result="Leave Application \nFrom: " + from + "\nTo: " + to + "\n" ;//built a string  to produce output
				JOptionPane.showMessageDialog(null, result+ "Applied Successfully","Leave Application",JOptionPane.INFORMATION_MESSAGE);  
			}
			catch( Exception e1) {
				//Warning message upon failure of insertion
				JOptionPane.showMessageDialog(null, "Leave Application failed!", "Leave Application", JOptionPane.WARNING_MESSAGE);
			}


		}

	}



	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtReason,"Reason ")) return false;
		if(!Validate.isPresent(txtFrom,"From: ")) return false;
		if(!Validate.isPresent(txtTo,"To: ")) return false;
		return true;
	}

}
