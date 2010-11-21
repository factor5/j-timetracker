package com.svelikov.timetracker.action;

import javax.swing.JTable;

import org.apache.log4j.Logger;

import com.svelikov.timetracker.ui.TimeTrackerTableModel;
import com.svelikov.timetracker.util.UIUtil;

/**
 * Base action class providing access to shared resources for all actions and
 * and some common logic.
 * 
 * @author svelikov
 */
public class BaseAction {

	/**
	 * Logger.
	 */
	protected static final Logger log = Logger.getLogger(BaseAction.class);

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
		table = UIUtil.getTable();
		tableModel = UIUtil.getTableModel();
	}

}
