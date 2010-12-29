package com.sve.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.sve.timetracker.ActionCommandConstants;
import com.sve.timetracker.TimeTrackerMap;
import com.sve.timetracker.ui.NewTimeTrackerInfoWindow;
import com.sve.timetracker.ui.TimeTrackerWindow;

/**
 * Action listener for the main window.
 * 
 * @author Svilen Velikov
 */
public class MainWindowActionListener extends BaseAction implements
		ActionListener {

	/**
	 * Reference to the main window.
	 */
	private final TimeTrackerWindow mainWindow;

	/**
	 * Constructor.
	 * 
	 * @param timeTrackerWindow
	 */
	public MainWindowActionListener(final TimeTrackerWindow timeTrackerWindow,
			final TimeTrackerMap timersMap) {
		timers = timersMap;
		this.mainWindow = timeTrackerWindow;
	}

	/**
	 * Catches the events fired by the main window controls.
	 */
	@Override
	public void actionPerformed(final ActionEvent ae) {
		// a button for creating of new timer is pressed
		if (ae.getActionCommand().equals(ActionCommandConstants.NEW_TIMER)) {
			final NewTimeTrackerInfoWindow infoWindow = new NewTimeTrackerInfoWindow(
					mainWindow);
			infoWindow.createAndShowWindow();
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.CONFIGURATION)) {
			LOG.debug("Configuration button");
		} else if (ae.getActionCommand().equals(ActionCommandConstants.ABOUT)) {
			showAbout();
		}
	}

	/**
	 * Displays a window about.
	 */
	private void showAbout() {
		final StringBuilder msg = new StringBuilder();
		msg.append("                        TimeTracker application\n");
		msg.append("                                   Version 1.0\n");
		msg.append("                       Created by Svilen Velikov\n");
		msg.append("    Icons are from http://www.freeiconsweb.com");
		JOptionPane.showMessageDialog(mainWindow, msg.toString(),
				"About TimeTracker", JOptionPane.PLAIN_MESSAGE);
	}
}
