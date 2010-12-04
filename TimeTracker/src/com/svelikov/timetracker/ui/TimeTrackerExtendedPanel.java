/**
 * 
 */
package com.svelikov.timetracker.ui;

import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import com.svelikov.timetracker.util.UIUtil;

/**
 * Base component used from all containers in this application. Adds some common
 * logic.
 * 
 * @author Svilen Velikov
 */
public class TimeTrackerExtendedPanel extends JPanel {

	/**
	 * A bundle with labels and messages.
	 */
	protected final ResourceBundle bundle;

	/**
	 * Logger.
	 */
	protected final static Logger LOG = Logger
			.getLogger(TimeTrackerExtendedPanel.class);

	/**
	 * Reference to the table component.
	 */
	protected JTable table;

	/**
	 * Constructor.
	 */
	public TimeTrackerExtendedPanel() {
		bundle = UIUtil.getBundle();
	}

	/**
	 * Search for a key in the resource bundle.
	 * 
	 * @param key
	 *            The key to search for.
	 * @return A string matched by this key.
	 */
	protected String getLabel(final String key) {
		return bundle.getString(key);
	}

}
