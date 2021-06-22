package presentation;

//importing necessary packages
import javax.swing.*;
import business.TestCall;
import data.DBAccess;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;



public class CalloutPanel extends JPanel{

	//declaration and initialization
	private JLabel lblGuiName, lblReason, lblShift , lblNumOfCallout, blank, blank1, blank2;
	private JTextField txtReason,txtNumOfCallout;
	private JButton butCallout;
	private JRadioButton jMorning, jEvening, jNight;
	private DBAccess db=null;
	private String noofcall = null;





	//designing panel
	private void initialize() {

		blank=new JLabel();
		blank1=new JLabel();
		blank2=new JLabel();
		lblGuiName=new JLabel();
		lblGuiName.setText("Callout Application"); //object creation of label
		lblGuiName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));

		lblReason=new JLabel("Reason: "); //another way to create object using constructors
		lblReason.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		lblShift = new JLabel("Shift: ");
		lblShift.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		jMorning=new JRadioButton("Morning");
		jMorning.setFont(new java.awt.Font("Arial", Font.PLAIN, 14));
		jMorning.setBackground(Color.PINK);

		jEvening=new JRadioButton("Evening");
		jEvening.setFont(new java.awt.Font("Arial", Font.PLAIN, 14));
		jEvening.setBackground(Color.PINK);

		jNight=new JRadioButton("Night");
		jNight.setFont(new java.awt.Font("Arial", Font.PLAIN, 14));
		jNight.setBackground(Color.PINK);

		lblNumOfCallout = new JLabel("Number of Callouts Left: ");
		lblNumOfCallout.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtReason = new JTextField();//creating empty text fields
		txtReason.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		txtNumOfCallout = new JTextField();
		txtNumOfCallout.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));

		butCallout=new JButton("CALLOUT");
		butCallout.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		butCallout.setBackground(Color.GREEN);
		butCallout.setForeground(Color.WHITE);


		//adding radio buttons to group
		ButtonGroup group=new ButtonGroup();
		group.add(jMorning);
		group.add(jEvening);
		group.add(jNight);

		//setting Mnemonics to radio buttons
		jMorning.setMnemonic('M');
		jEvening.setMnemonic('E');
		jNight.setMnemonic('N');

		//selecting radio button by default
		jMorning.setSelected(true);

		//deciding layout
		this.setLayout(new GridLayout(7,2));  //7 rows 2 columns
		this.setBackground(Color.PINK);

		this.add(blank);
		this.add(lblGuiName);//add components in the order you want them to appear
		this.add(lblReason);
		this.add(txtReason);
		this.add(lblShift);
		this.add(jMorning);
		this.add(blank1);
		this.add(jEvening);
		this.add(blank2);
		this.add(jNight);
		this.add(lblNumOfCallout);
		this.add(txtNumOfCallout);
		this.add(butCallout);


		//initializing a DBAccess object and retrieving the number of callouts of an employee
		try {
			db=new DBAccess();
			noofcall =db.findNoOfCallout();
			txtNumOfCallout.setText(noofcall);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}


	}

	//constructor
	public CalloutPanel() {
		this.initialize();
		butCallout.addActionListener(new CalloutButtonHandler());//calling inner class onclick of callout button

	}


	//for event handling we create inner class
	private class CalloutButtonHandler implements ActionListener{

		//this class is going to handle callout button
		//overwriting methods of interface
		public void actionPerformed(ActionEvent e) {

			String reason=txtReason.getText();//extract whatever the text input is
			String shift=""; 

			//analysing radio button data
			if(jMorning.isSelected())
			{
				shift="Morning";
			}
			else if(jEvening.isSelected())
				shift="Evening";
			else if(jNight.isSelected())
				shift="Night";


			//validating textfields 
			if(!isValidData()) {
				return;
			}

			//creating a TestCall object
			TestCall tt = new TestCall(reason,shift);

			//insertion of data into database if callouts are available
			Integer num=Integer.parseInt(noofcall);
			if(num>0) {
				try{
					db.addCallout(tt);

					String result="Callout made for \n Shift: " + shift + "\n";//built a string  to produce output
					JOptionPane.showMessageDialog(null, result+ "Callout Made","Successful",JOptionPane.INFORMATION_MESSAGE);
					txtNumOfCallout.setText(noofcall);//resetting the textfield with new data

				}
				catch(SQLException e1) {
					//Warning message upon failure of insertion
					JOptionPane.showMessageDialog(null, "Callout failed!", "Callout Page", JOptionPane.WARNING_MESSAGE);
				}
			}else {
				//Warning message upon failure of insertion due to lack of callouts
				JOptionPane.showMessageDialog(null, "OOPS!! No callouts left", "Callout Page", JOptionPane.WARNING_MESSAGE);
			}


		}

	}



	//validating inputs
	public boolean isValidData() {
		if(!Validate.isPresent(txtReason,"Reason")) return false;
		return true;
	}


}
