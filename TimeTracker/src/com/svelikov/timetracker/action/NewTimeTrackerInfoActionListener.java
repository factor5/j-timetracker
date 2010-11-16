package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.ui.NewTimeTrackerInfoWindow;
import com.svelikov.timetracker.ui.TimeTrackerTableModel;
import com.svelikov.timetracker.util.MessageType;
import com.svelikov.timetracker.util.UIUtil;

public class NewTimeTrackerInfoActionListener extends WindowAdapter implements
		ActionListener {

	private final NewTimeTrackerInfoWindow newTimeTrackerInfoWindow;
	private String trackerName;
	private final TimeTrackerTableModel tableModel;
	private final JFrame mainWindow;

	public NewTimeTrackerInfoActionListener(
			NewTimeTrackerInfoWindow newTimeTrackerInfoWindow,
			TimeTrackerTableModel tableModel, JFrame mainWindow) {
		this.newTimeTrackerInfoWindow = newTimeTrackerInfoWindow;
		this.tableModel = tableModel;
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(ActionCommandConstants.CREATE_TIMER)) {
			trackerName = newTimeTrackerInfoWindow.getTimeTrackerName()
					.getText();
			if (!nameAlreadyExists(tableModel, trackerName)) {
				createTimeTracker();
			} else {
				UIUtil.setWarnings(mainWindow,
						"The chosen name already exists!",
						MessageType.INFORMATION);
			}
			newTimeTrackerInfoWindow.closeInfoWindow();
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.CANCEL_TIMER)) {
			newTimeTrackerInfoWindow.closeInfoWindow();
		}
	}

	protected void createTimeTracker() {
		Vector<Object> newRow = new Vector<Object>(3);
		newRow.add(trackerName);
		newRow.add(new Date());
		newRow.add(UIUtil.getActionButtons());

		Vector<Object> data = tableModel.getData();
		data.add(newRow);
		tableModel.fireTableDataChanged();
	}

	/**
	 * Searches the table for specified name.
	 * 
	 * @param tableModel
	 *            The table model.
	 * @param timerName
	 *            The name to search for.
	 * @return true if the specified name already exists in the table and false
	 *         otherwise.
	 */
	private boolean nameAlreadyExists(TimeTrackerTableModel tableModel,
			String timerName) {
		int rowsCount = tableModel.getRowCount();
		for (int i = 0; i < rowsCount; i++) {
			String n = (String) tableModel.getValueAt(i, 0);
			if (n.equals(timerName)) {
				return true;
			}
		}
		return false;
	}

}
