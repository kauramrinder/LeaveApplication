package presentation;

//importing necessary packages
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Validate  {

	//validation for text fields
	public static boolean isPresent(JTextComponent c,String title) {
		if(c.getText().length()==0)
		{
			showMessage(c,title + " is a required field.\n Please re-enter.");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}

	//validation of text fields containing Integers
	public static boolean isInteger(JTextComponent c, String title) {
		try {
			int i=Integer.parseInt(c.getText());
			return true;
		}
		catch(NumberFormatException e) {
			showMessage(c,title+" must be integer.\nPlease re-enter.");
			c.requestFocusInWindow();
			return false;
		}
	}

	//Message of invalidation
	public static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c,message,"Invalid Entry",JOptionPane.ERROR_MESSAGE);
	}


}
