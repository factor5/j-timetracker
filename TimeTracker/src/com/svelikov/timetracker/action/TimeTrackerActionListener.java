package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.ui.NewTimeTrackerInfoWindow;
import com.svelikov.timetracker.ui.TimeTrackerTableModel;

public class TimeTrackerActionListener extends WindowAdapter implements
		ActionListener {

	private final TimeTrackerTableModel tableModel;
	private final JFrame mainWindow;

	public TimeTrackerActionListener(TimeTrackerTableModel tableModel,
			JFrame frame) {
		this.tableModel = tableModel;
		this.mainWindow = frame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(ActionCommandConstants.NEW_TIMER)) {
			NewTimeTrackerInfoWindow infoWindow = new NewTimeTrackerInfoWindow(
					tableModel, mainWindow);
			infoWindow.createAndShowWindow();
		}
	}

	// public abstract void createTimeTracker();
}
