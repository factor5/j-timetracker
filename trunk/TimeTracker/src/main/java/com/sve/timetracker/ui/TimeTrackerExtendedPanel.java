/**
 * 
 */
package com.sve.timetracker.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import com.sve.timetracker.action.TimeTrackerController;

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

	public static final Color TABLE_BACKGROUND_COLOR = new Color(224, 233, 245);
	public static final Color HEADER_BACKGROUND_COLOR = new Color(66, 130, 211);
	public static final Color CREATE_TIMER_BUTTON_BACKGROUND_COLOR = new Color(
			39, 77, 126);
	public static final Color BASE_OPTIONS_PANEL_BACKGROUND_COLOR = new Color(
			105, 151, 211);
	public static final Color WHITE_COLOR = new Color(255, 255, 255);

	protected TimeTrackerController controller;

	/**
	 * A bundle with labels and messages.
	 */
	protected ResourceBundle bundle;

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

	/**
	 * Getter method for bundle.
	 * 
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
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
