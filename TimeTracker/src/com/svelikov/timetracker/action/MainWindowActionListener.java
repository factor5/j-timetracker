package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.ui.NewTimeTrackerInfoWindow;

/**
 * 
 * @author svelikov
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
	public MainWindowActionListener(final JFrame frame) {
		this.mainWindow = frame;
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
		if (ae.getActionCommand().equals(ActionCommandConstants.NEW_TIMER)) {
			final NewTimeTrackerInfoWindow infoWindow = new NewTimeTrackerInfoWindow(
					tableModel, table, mainWindow);
			infoWindow.createAndShowWindow();
		}
	}

}
