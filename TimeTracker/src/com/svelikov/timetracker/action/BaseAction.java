package com.svelikov.timetracker.action;

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

	/**
	 * Logger.
	 */
	protected static final Logger LOG = Logger.getLogger(BaseAction.class);

	/**
	 * A map that stores the timers.
	 */
	public static TimeTrackerMap timers;

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
	}

}
