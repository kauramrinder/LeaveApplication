package presentation;

//importing necessary packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class LeaveFrame extends JFrame {

	//declaring and initializing 
	private JMenuBar menuBar;
	private JMenu mFile;
	private JMenu mApply,mSavedApplications;
	private JMenuItem mItemQuit,mItemCallout,mItemApplyLeave;
	private JMenuItem mItemViewLeaves;
	private JDesktopPane desktop;

	//constructor
	public LeaveFrame()
	{
		this.initialize();
		this.setTitle("Employee Leave App");
		this.setSize(700,400);

	}

	//designing frame
	private void initialize()
	{
		menuBar = new JMenuBar();

		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);

		mApply = new JMenu("Apply");
		mApply.setMnemonic(KeyEvent.VK_A);



		mSavedApplications = new JMenu("SavedApplication");
		mSavedApplications.setMnemonic(KeyEvent.VK_S);

		desktop = new JDesktopPane();
		this.setContentPane(desktop);

		mItemQuit = new JMenuItem("Quit");
		mItemQuit.setMnemonic(KeyEvent.VK_Q);
		mItemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		mItemQuit.addActionListener(new ExitEventHandler());


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


		//linkings to their parent
		mFile.add(mItemQuit);
		mApply.add(mItemCallout);
		mApply.add(mItemApplyLeave);
		mSavedApplications.add(mItemViewLeaves);
		menuBar.add(mFile);
		menuBar.add(mApply);
		menuBar.add(mSavedApplications);
		this.setJMenuBar(menuBar);



	}

	//upon quit
	private class ExitEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	//upon selecting View Leave
	private class ViewEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new ViewLeavePanel());
			pInternalFrame.setVisible(true);
			LeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	//upon selecting Callout
	private class CalloutEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new CalloutPanel());
			pInternalFrame.setVisible(true);
			LeaveFrame.this.desktop.add(pInternalFrame);
		}
	}

	//upon selecting  Leave
	private class LeaveEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new LeaveInternalFrame(new LeavePanel());
			pInternalFrame.setVisible(true);
			LeaveFrame.this.desktop.add(pInternalFrame);
		}
	}



}