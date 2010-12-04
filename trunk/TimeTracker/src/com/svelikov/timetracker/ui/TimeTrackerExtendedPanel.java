/**
 * 
 */
package com.svelikov.timetracker.ui;

import java.awt.Font;
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

	public static final int MAIN_WINDOW_WIDTH = 627;
	public static final int MAIN_WINDOW_HEIGHT = 332;
	public static final String TABLE_FONT_FAMILY = "Agency FB";
	public static final int TABLE_FONT_STYLE = Font.PLAIN;
	public static final int TABLE_FONT_SIZE = 15;
	public static final String TABLE_HEADER_FONT_FAMILY = "Arial";
	public static final int TABLE_HEADER_FONT_STYLE = Font.BOLD;
	public static final int TABLE_HEADER_FONT_SIZE = 12;
	public static final int TABLE_ROW_HEIGHT = 30;

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
