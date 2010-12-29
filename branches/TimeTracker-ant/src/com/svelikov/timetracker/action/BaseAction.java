package com.svelikov.timetracker.action;

import java.util.ResourceBundle;

import javax.swing.JTable;

import org.apache.log4j.Logger;

import com.svelikov.timetracker.TimeTrackerMap;
import com.svelikov.timetracker.ui.TimeTrackerTableModel;
import com.svelikov.timetracker.util.UIUtil;

/**
 * Base action class providing access to shared resources for all actions and
 * and some common logic.
 * 
 * @author Svilen Velikov
 */
public class BaseAction {

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
	protected final ResourceBundle bundle;

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
		this.table = UIUtil.getTable();
		this.tableModel = UIUtil.getTableModel();
		this.bundle = UIUtil.getBundle();
	}

}
