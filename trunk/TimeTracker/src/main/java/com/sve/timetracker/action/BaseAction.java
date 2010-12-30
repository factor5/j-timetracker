package com.sve.timetracker.action;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JTable;

import org.apache.log4j.Logger;

import com.sve.timetracker.model.TimeTrackerMap;
import com.sve.timetracker.ui.TimeTrackerTableModel;

/**
 * Base action class providing access to shared resources for all actions and
 * and some common logic.
 * 
 * @author Svilen Velikov
 */
public abstract class BaseAction implements ActionListener {

	public static final int DELAY = 200;
	public static final int INITIAL_DELAY = 0;
	public static final int TIMER_NAME_LENGTH = 30;

	/**
	 * Logger.
	 */
	protected static final Logger LOG = Logger.getLogger(BaseAction.class);

	/**
	 * Resource bundle.
	 */
	protected ResourceBundle bundle;

	/**
	 * A map that stores the timers.
	 */
	protected static TimeTrackerMap timers;

	/**
	 * The tableModel.
	 */
	protected TimeTrackerTableModel tableModel;

	/**
	 * The table component.
	 */
	protected JTable table;

	/**
	 * Constructor that initializes fields.
	 */
	public BaseAction() {

	}

	/**
	 * Setter method for tableModel.
	 * 
	 * @param tableModel
	 *            the tableModel to set
	 */
	public void setTableModel(final TimeTrackerTableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * Setter method for table.
	 * 
	 * @param table
	 *            the table to set
	 */
	public void setTable(final JTable table) {
		this.table = table;
	}

	/**
	 * Setter method for bundle.
	 * 
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(final ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
