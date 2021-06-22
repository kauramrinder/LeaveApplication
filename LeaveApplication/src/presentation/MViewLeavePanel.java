package presentation;

//importing necessary packages
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import com.sun.jdi.connect.spi.Connection;
import data.DBAccess;

public class MViewLeavePanel extends JPanel {

	//declaring and initializing
	private JTable table;
	private Connection c;
	private ResultSet rs;
	private JLabel lblName;
	byte[] imgData = null;
	private String[] columnNames = {"AppId", "Reason", "FromDate", "ToDate","Status","Cause", "Applied Date", "empID"};


	/**
	 * Create the panel.
	 */
	public MViewLeavePanel() {

		setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.BLUE));
		table.setForeground(new Color(25, 25, 112));

		//setting size of table
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{" ", " ", " ", " ", " ", "", ""},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, " ", null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
				},
				columnNames
				));

		//desgning table
		table.setBounds(20, 77, 495, 116);
		JScrollPane scrollPane = new JScrollPane( table );
		this.add( scrollPane );
		add(table);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.setOpaque(true);
		table.setFillsViewportHeight(true);


		lblName= new JLabel("..Leaves Applied In This Week..");
		lblName.setBounds(120, 10, 500, 20);
		add(lblName);
		lblName.setFont(new java.awt.Font("Arial", Font.BOLD, 20));

		//connection and retrieving records from database
		try {
			DBAccess db = new DBAccess();
			db.connect();
			rs = db.viewEmpLeaves();
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}


		//inserting records in table
		DefaultTableModel tm = (DefaultTableModel)table.getModel();
		tm.setRowCount(1);
		tm.addRow(columnNames);
		int i=0;

		try {
			while(rs.next()) {

				Object o[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};

				tm.addRow(o);
				i++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		//required messages based on number of records retrieved
		if(i <1)
		{
			JOptionPane.showMessageDialog(null, "No Record Found","Error",
					JOptionPane.ERROR_MESSAGE);
		}
		if(i ==1)
		{
			JOptionPane.showMessageDialog(null,i + " Records Found"," Records Found",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, " Records Found",i +" Records Found",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}


}