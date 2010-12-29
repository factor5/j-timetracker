package com.svelikov.timetracker.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.LabelNameConstants;
import com.svelikov.timetracker.action.NewTimerActionListener;
import com.svelikov.timetracker.util.UIUtil;

/**
 * New time tracker info window is where the user can enter the name for the new
 * timer.
 * 
 * @author Svilen Velikov
 */
public class NewTimeTrackerInfoWindow extends JFrame {

	private final ResourceBundle bundle;

	private JLabel timeTrackerNameLabel;
	private JTextField timeTrackerName;
	private JButton createTimerButton;
	private JButton cancelButton;
	private final TimeTrackerWindow mainWindow;

	/**
	 * Constructor.
	 * 
	 * @param tableModel
	 * @param mainWindow
	 */
	public NewTimeTrackerInfoWindow(final TimeTrackerWindow mainWindow) {
		this.bundle = UIUtil.getBundle();
		this.mainWindow = mainWindow;
	}

	/**
	 * Creates and shows a window where the user can enter the name for the new
	 * timer.
	 */
	public void createAndShowWindow() {
		setTitle(bundle.getString(LabelNameConstants.NEWTIMER_WINDOW_TITLE));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		final NewTimerActionListener actionListener = new NewTimerActionListener(
				this, mainWindow);
		timeTrackerNameLabel = new JLabel(bundle
				.getString(LabelNameConstants.LABEL_TIMER_NAME));
		add(timeTrackerNameLabel);

		timeTrackerName = new JTextField();
		add(timeTrackerName);
		timeTrackerName.addKeyListener(actionListener);

		createTimerButton = new JButton(bundle
				.getString(LabelNameConstants.BUTTON_CREATE));
		createTimerButton.setActionCommand(ActionCommandConstants.CREATE_TIMER);
		add(createTimerButton);
		createTimerButton.addActionListener(actionListener);

		cancelButton = new JButton(bundle
				.getString(LabelNameConstants.BUTTON_CANCEL));
		cancelButton.setActionCommand(ActionCommandConstants.CANCEL_TIMER);
		add(cancelButton);
		cancelButton.addActionListener(actionListener);

		// apply sizes
		final Insets insets = getInsets();
		Dimension size = timeTrackerNameLabel.getPreferredSize();
		timeTrackerNameLabel.setBounds(5 + insets.left, 5 + insets.top,
				size.width + 70, size.height + 15);

		timeTrackerName.setBounds(80 + insets.left, 5 + insets.top,
				size.width + 155, size.height + 15);

		size = createTimerButton.getPreferredSize();
		createTimerButton.setBounds(5 + insets.left, 40 + insets.top,
				size.width + 75, size.height);

		size = cancelButton.getPreferredSize();
		cancelButton.setBounds(159 + insets.left, 40 + insets.top,
				size.width + 75, size.height);

		setResizable(true);
		setSize(330 + insets.left + insets.right, 117 + insets.top
				+ insets.bottom);
		setLocationRelativeTo(mainWindow);
		setVisible(true);
	}

	/**
	 * Getter for the text from the timeTrackerName field.
	 * 
	 * @return the timeTrackerName
	 */
	public JTextField getTimeTrackerName() {
		return timeTrackerName;
	}

	/**
	 * Disposes this window.
	 */
	public void closeInfoWindow() {
		setVisible(false);
		dispose();
	}
}
