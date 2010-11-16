/**
 * 
 */
package com.svelikov.timetracker.util;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.action.TimeTrackerAction;
import com.svelikov.timetracker.exception.UIInitializationException;

/**
 * UI utility provider class.
 * 
 * @author svelikov
 */
public class UIUtil {

	/**
	 * Private constructor.
	 */
	private UIUtil() {
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
	 * Creates a panel with action buttons to be placed inside every timer.
	 * 
	 * @return A JPanel with action buttons.
	 */
	public static JPanel getActionButtons() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));

		final TimeTrackerAction actionListener = new TimeTrackerAction();

		final ImageIcon stopIcon = new ImageIcon("img/Aqua-Stop-icon.png");
		final JButton stopTimerActionButton = new JButton(stopIcon);
		stopTimerActionButton
				.setActionCommand(ActionCommandConstants.STOP_TIMER);
		panel.add(stopTimerActionButton);
		stopTimerActionButton.addActionListener(actionListener);

		final ImageIcon startIcon = new ImageIcon("img/Aqua-Play-icon.png");
		final JButton startTimerActionButton = new JButton(startIcon);
		startTimerActionButton
				.setActionCommand(ActionCommandConstants.START_TIMER);
		panel.add(startTimerActionButton);
		startTimerActionButton.addActionListener(actionListener);

		final ImageIcon deleteIcon = new ImageIcon("img/delete-icon.png");
		final JButton removeTimerActionButton = new JButton(deleteIcon);
		removeTimerActionButton
				.setActionCommand(ActionCommandConstants.REMOVE_TIMER);
		panel.add(removeTimerActionButton);
		removeTimerActionButton.addActionListener(actionListener);

		return panel;
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

}
