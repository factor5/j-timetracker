package com.sve.timetracker.action;

import java.awt.event.ActionEvent;

import javax.swing.JTable;

import com.sve.timetracker.ActionCommandConstants;
import com.sve.timetracker.TimeTrackerMap;
import com.sve.timetracker.ui.TimeTrackerTableModel;
import com.sve.timetracker.ui.TimeTrackerWindow;

/**
 * @author Svilen Velikov
 */
public class TimeTrackerController extends BaseAction {

	private static TimeTrackerController CONTROLLER_INSTANCE = null;
	private TimeTrackerWindow timeTrackerWindow;

	private TimeTrackerController() {

	}

	public static TimeTrackerController getInstance() {
		if (CONTROLLER_INSTANCE == null) {
			CONTROLLER_INSTANCE = new TimeTrackerController();
		}
		return CONTROLLER_INSTANCE;
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
		if (ae.getActionCommand().equals(ActionCommandConstants.CREATE_TIMER)) {
			LOG.debug("create timer");
			// createTimerActionDispatcher();
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.CANCEL_TIMER)) {
			LOG.debug("cancel timer");
			// newTimeTrackerInfoWindow.closeInfoWindow();
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.NEW_TIMER)) {
			LOG.debug("new timer");
			// final NewTimeTrackerInfoWindow infoWindow = new
			// NewTimeTrackerInfoWindow(
			// mainWindow);
			// infoWindow.createAndShowWindow();
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.CONFIGURATION)) {
			LOG.debug("Configuration button");
		} else if (ae.getActionCommand().equals(ActionCommandConstants.ABOUT)) {
			LOG.debug("about");
			// showAbout();
		}
	}

	/**
	 * Getter method for timeTrackerWindow.
	 * 
	 * @return the timeTrackerWindow
	 */
	public TimeTrackerWindow getTimeTrackerWindow() {
		return timeTrackerWindow;
	}

	/**
	 * Setter method for timeTrackerWindow.
	 * 
	 * @param timeTrackerWindow
	 *            the timeTrackerWindow to set
	 */
	public void setTimeTrackerWindow(final TimeTrackerWindow timeTrackerWindow) {
		this.timeTrackerWindow = timeTrackerWindow;
	}

	/**
	 * Getter method for tableModel.
	 * 
	 * @return the tableModel
	 */
	public TimeTrackerTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Setter method for tableModel.
	 * 
	 * @param tableModel
	 *            the tableModel to set
	 */
	@Override
	public void setTableModel(final TimeTrackerTableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * Getter method for table.
	 * 
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Setter method for table.
	 * 
	 * @param table
	 *            the table to set
	 */
	@Override
	public void setTable(final JTable table) {
		this.table = table;
	}

	/**
	 * Getter method for timers.
	 * 
	 * @return the timers
	 */
	public TimeTrackerMap getTimers() {
		return timers;
	}

	/**
	 * Setter method for timers.
	 * 
	 * @param timers
	 *            the timers to set
	 */
	public void setTimers(final TimeTrackerMap timers) {
		this.timers = timers;
	}
}
