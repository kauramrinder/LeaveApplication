package presentation;

//importing necessary packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class MLeaveFrame extends JFrame {

	//declaring and initializing 
	private JMenuBar menuBar;
	private JMenu mFile,managersView,mEdit;
	private JMenu mApply,mSavedApplications;
	private JMenuItem mItemQuit,mItemCallout,mItemApplyLeave,mItemManageLeave, mItemManageCallout,mItemViewEmpLeave;
	private JMenuItem mItemViewLeaves,mItemEmployees,mItemNewCallout;
	private JDesktopPane desktop;


	//constructor
	public MLeaveFrame()
	{
		this.initialize();
		this.setTitle("Employee Leave App");
		this.setSize(700,400);

	}

	//design
	private void initialize()
	{
		menuBar = new JMenuBar();


		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);

		mEdit = new JMenu("Edit");
		mEdit.setMnemonic(KeyEvent.VK_D);

		mApply = new JMenu("Apply");
		mApply.setMnemonic(KeyEvent.VK_A);



		mSavedApplications = new JMenu("SavedApplication");
		mSavedApplications.setMnemonic(KeyEvent.VK_S);

		managersView = new JMenu("Manage Applications");
		managersView.setMnemonic(KeyEvent.VK_M);

		desktop = new JDesktopPane();
		this.setContentPane(desktop);

		mItemQuit = new JMenuItem("Quit");
		mItemQuit.setMnemonic(KeyEvent.VK_Q);
		mItemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		mItemQuit.addActionListener(new ExitEventHandler());

		mItemEmployees = new JMenuItem("Employees");
		mItemEmployees.setMnemonic(KeyEvent.VK_P);
		mItemEmployees.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		mItemEmployees.addActionListener(new EmployeeEventHandler());

		mItemNewCallout = new JMenuItem("Callout");
		mItemNewCallout.setMnemonic(KeyEvent.VK_T);
		mItemNewCallout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		mItemNewCallout.addActionListener(new NewCalloutEventHandler());


		mItemViewLeaves = new JMenuItem("View Leave Application");
		mItemViewLeaves.setMnemonic(KeyEvent.VK_V);
		mItemViewLeaves.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		mItemViewLeaves.addActionListener(new ViewEventHandler());

		mItemCallout = new JMenuItem("Callout");
		mItemCallout.setMnemonic(KeyEvent.VK_C);
		mItemCallout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		mItemCallout.addActionListener(new CalloutEventHandler());



		mItemApplyLeave = new JMenuItem("Leave");
		mItemApplyLeave.setMnemonic(KeyEvent.VK_L);
		mItemApplyLeave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		mItemApplyLeave.addActionListener(new LeaveEventHandler());

		mItemManageLeave = new JMenuItem("Manage Leave");
		mItemManageLeave.setMnemonic(KeyEvent.VK_N);
		mItemManageLeave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		mItemManageLeave.addActionListener(new ManageLeaveEventHandler());

		mItemManageCallout = new JMenuItem("View Callouts");
		mItemManageCallout.setMnemonic(KeyEvent.VK_I);
		mItemManageCallout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		mItemManageCallout.addActionListener(new ViewCalloutsEventHandler());

		mItemViewEmpLeave = new JMenuItem("View Employee Leaves");
		mItemViewEmpLeave.setMnemonic(KeyEvent.VK_E);
		mItemViewEmpLeave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		mItemViewEmpLeave.addActionListener(new ViewEmpLeaveEventHandler());


		//linkings to their parent
		mFile.add(mItemQuit);
		mEdit.add(mItemNewCallout);
		mEdit.add(mItemEmployees);
		mApply.add(mItemCallout);
		mApply.add(mItemApplyLeave);
		mSavedApplications.add(mItemViewLeaves);
		managersView.add(mItemManageCallout);
		managersView.add(mItemManageLeave);
		managersView.add(mItemViewEmpLeave);
		menuBar.add(mFile);
		menuBar.add(mEdit);
		menuBar.add(mApply);
		menuBar.add(mSavedApplications);
		menuBar.add(managersView);
		this.setJMenuBar(menuBar);



	}


	private class ExitEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class ViewEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new ViewLeavePanel());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	private class EmployeeEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new EmployeePanel());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	private class NewCalloutEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new NewCalloutPanel());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	private class CalloutEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new CalloutPanel());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	private class LeaveEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new LeavePanel());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	private class ManageLeaveEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new ManageLeave());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	private class ViewCalloutsEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new ViewCalloutsPanel());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	private class ViewEmpLeaveEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new MViewLeavePanel());
			pInternalFrame.setVisible(true);
			MLeaveFrame.this.desktop.add(pInternalFrame);
		}
	}



}
