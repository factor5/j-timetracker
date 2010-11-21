/**
 * 
 */
package com.svelikov.timetracker.util;

import java.awt.Container;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.svelikov.timetracker.exception.UIInitializationException;
import com.svelikov.timetracker.ui.TimeTrackerTableModel;

/**
 * UI utility provider class.
 * 
 * @author svelikov
 */
public class UIUtil {

	private static ResourceBundle bundle = null;
	public static JTable table;
	public static TimeTrackerTableModel tableModel;

	/**
	 * Private constructor.
	 */
	private UIUtil() {
	}

	/**
	 * Creates and returns the resource bundle.
	 * 
	 * @return The resource bundle with labels and messages.
	 */
	public static ResourceBundle getBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("labels");
		}
		return bundle;
	}

	/**
	 * 
	 * @param table
	 * @param columnNumber
	 * @param width
	 * @throws UIInitializationException
	 */
	public static void setColumnWidth(final JTable table,
			final int columnNumber, final int width)
			throws UIInitializationException {
		final TableColumnModel columnModel = table.getColumnModel();
		if (columnNumber < 0 || columnNumber > columnModel.getColumnCount()) {
			throw new UIInitializationException(
					"Column number must be > 0 and < "
							+ columnModel.getColumnCount());
		}
		final TableColumn column = table.getColumnModel().getColumn(
				columnNumber);
		column.setPreferredWidth(width);
	}

	/**
	 * Shows windows with messages according the parameters.
	 * 
	 * @param warningMessage
	 *            The message that should be displayed.
	 * @param msgType
	 *            The type of the window to be shown.
	 */
	public static void setWarnings(final Container parent,
			final String warningMessage, final MessageType msgType) {
		switch (msgType) {
		case WARNING:
			JOptionPane.showMessageDialog(parent, warningMessage, "Warning",
					JOptionPane.WARNING_MESSAGE);
			break;
		case INFORMATION:
			JOptionPane.showMessageDialog(parent, warningMessage,
					"Information", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}

	/**
	 * @return the table
	 */
	public static JTable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public static void setTable(final JTable table) {
		UIUtil.table = table;
	}

	/**
	 * @return the tableModel
	 */
	public static TimeTrackerTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @param tableModel
	 *            the tableModel to set
	 */
	public static void setTableModel(final TimeTrackerTableModel tableModel) {
		UIUtil.tableModel = tableModel;
	}

}
