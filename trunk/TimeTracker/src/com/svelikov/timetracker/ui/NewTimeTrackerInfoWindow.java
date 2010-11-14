package com.svelikov.timetracker.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.action.NewTimeTrackerInfoActionListener;

public class NewTimeTrackerInfoWindow extends JFrame {

	private JTextField timeTrackerName;
	private JButton createTimerButton;
	private JButton cancelButton;
	private final TimeTrackerTableModel tableModel;
	private final JFrame mainWindow;

	public NewTimeTrackerInfoWindow(TimeTrackerTableModel tableModel,
			JFrame mainWindow) {
		this.tableModel = tableModel;
		this.mainWindow = mainWindow;
	}

	public void createAndShowWindow() {
		setTitle("New time tracker info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(2, 2, 2, 2));
		setSize(200, 100);

		NewTimeTrackerInfoActionListener actionListener = new NewTimeTrackerInfoActionListener(
				this, tableModel, mainWindow);
		JLabel timeTrackerNameLabel = new JLabel("Time tracker name: ");
		add(timeTrackerNameLabel);

		timeTrackerName = new JTextField();
		add(timeTrackerName);

		createTimerButton = new JButton("Create");
		createTimerButton.setActionCommand(ActionCommandConstants.CREATE_TIMER);
		add(createTimerButton);
		createTimerButton.addActionListener(actionListener);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand(ActionCommandConstants.CANCEL_TIMER);
		add(cancelButton);
		cancelButton.addActionListener(actionListener);

		setLocationRelativeTo(mainWindow);
		setResizable(false);
		pack();
		setVisible(true);
	}

	/**
	 * @return the timeTrackerName
	 */
	public JTextField getTimeTrackerName() {
		return timeTrackerName;
	}

	public void closeInfoWindow() {
		setVisible(false);
		dispose();
	}
}
