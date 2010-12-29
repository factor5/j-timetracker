/**
 * 
 */
package com.sve.timetracker.util;

import java.awt.Container;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.sve.timetracker.exception.UIInitializationException;

/**
 * UI utility provider class.
 * 
 * @author Svilen Velikov
 */
public final class UIUtil {

	/**
	 * Private constructor.
	 */
	private UIUtil() {

	}

	/**
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
				JOptionPane.showMessageDialog(parent, warningMessage,
						"Warning", JOptionPane.WARNING_MESSAGE);
				break;
			case INFORMATION:
				JOptionPane.showMessageDialog(parent, warningMessage,
						"Information", JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				break;
		}
	}

}
