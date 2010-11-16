/**
 * 
 */
package com.svelikov.timetracker.ui;

import java.util.ResourceBundle;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Base component used from all containers in this application. Adds some common
 * logic.
 * 
 * @author Factor5
 */
public class TimeTrackerExtendedPanel extends JPanel {

	protected static final ResourceBundle bundle = ResourceBundle
			.getBundle("labels");

	protected static final Logger log = Logger
			.getLogger(TimeTrackerExtendedPanel.class);

	public TimeTrackerExtendedPanel() {
		// TODO Auto-generated constructor stub
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
