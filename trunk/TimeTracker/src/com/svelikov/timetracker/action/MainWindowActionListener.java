package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.TimeTrackerMap;
import com.svelikov.timetracker.ui.NewTimeTrackerInfoWindow;

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
	private final JFrame mainWindow;

	/**
	 * Constructor.
	 * 
	 * @param frame
	 */
	public MainWindowActionListener(final JFrame frame,
			final TimeTrackerMap timersMap) {
		timers = timersMap;
		this.mainWindow = frame;
	}

	/**
	 * Catches the events fired by the main window controls.
	 */
	@Override
	public void actionPerformed(final ActionEvent ae) {
		// a button for creating of new timer is pressed
		if (ae.getActionCommand().equals(ActionCommandConstants.NEW_TIMER)) {
			final NewTimeTrackerInfoWindow infoWindow = new NewTimeTrackerInfoWindow(
					tableModel, table, mainWindow);
			infoWindow.createAndShowWindow();
		}
	}

}
