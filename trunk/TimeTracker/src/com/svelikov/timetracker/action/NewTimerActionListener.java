package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.ui.NewTimeTrackerInfoWindow;
import com.svelikov.timetracker.ui.TimeTrackerTableModel;
import com.svelikov.timetracker.util.MessageType;
import com.svelikov.timetracker.util.UIUtil;

public class NewTimerActionListener extends BaseAction implements
		ActionListener, KeyListener {

	private final NewTimeTrackerInfoWindow newTimeTrackerInfoWindow;
	private final JFrame mainWindow;

	/**
	 * Constructor.
	 * 
	 * @param newTimeTrackerInfoWindow
	 * @param tableModel
	 * @param table
	 * @param mainWindow
	 */
	public NewTimerActionListener(
			final NewTimeTrackerInfoWindow newTimeTrackerInfoWindow,
			final JFrame mainWindow) {
		this.newTimeTrackerInfoWindow = newTimeTrackerInfoWindow;
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
		if (ae.getActionCommand().equals(ActionCommandConstants.CREATE_TIMER)) {
			createTimerActionDispatcher();
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.CANCEL_TIMER)) {
			newTimeTrackerInfoWindow.closeInfoWindow();
		}
	}

	/**
	 * Dispatches the application activity according to what the user was
	 * provided for name for the new timer. Called by the actionPerformed and
	 * keyPressed methods.
	 */
	private void createTimerActionDispatcher() {
		final String timerName = newTimeTrackerInfoWindow.getTimeTrackerName()
				.getText().trim();
		if (timerName.equals("")) {
			UIUtil.setWarnings(mainWindow,
					"You must provide a name for the new timer!",
					MessageType.INFORMATION);
		} else if (isValidName(tableModel, timerName)) {
			createTimer(timerName);
		} else {
			UIUtil.setWarnings(mainWindow, "Chosen name already exists!",
					MessageType.INFORMATION);
		}
		newTimeTrackerInfoWindow.closeInfoWindow();
	}

	/**
	 * Creates a row in the table for the new timer.
	 * 
	 * @param timerName
	 *            The name for the new timer.
	 */
	protected void createTimer(final String timerName) {
		final Vector<Object> newRow = new Vector<Object>(3);
		newRow.add(timerName);
		newRow.add(new Date());

		final TimerActionsListener timerActionsListener = new TimerActionsListener();

		newRow.add(getActionButton("img/notes-24.png", "Notes",
				ActionCommandConstants.VIEW_NOTES,
				new NotesActionButtonListener()));
		newRow.add(getActionButton("img/start-24.png", "Start the timer",
				ActionCommandConstants.START_TIMER, timerActionsListener));
		newRow.add(getActionButton("img/stop-24.png", "Stop the timer",
				ActionCommandConstants.STOP_TIMER, timerActionsListener));
		newRow.add(getActionButton("img/delete-24.png", "Delete the timer",
				ActionCommandConstants.REMOVE_TIMER, timerActionsListener));

		final Vector<Object> data = tableModel.getData();
		data.add(newRow);
		tableModel.fireTableDataChanged();
	}

	/**
	 * Creates and initializes a JButton.
	 * 
	 * @param iconImage
	 *            The image to apply.
	 * @param tooltip
	 *            The tooltip text to apply.
	 * @param actionCommand
	 *            The action command for the button.
	 * @param listener
	 *            Action listener for the button.
	 * @return Created JButton.
	 */
	private JButton getActionButton(final String iconImage,
			final String tooltip, final String actionCommand,
			final ActionListener listener) {
		final ImageIcon icon = new ImageIcon(iconImage);
		final JButton btn = new JButton(icon);
		btn.setToolTipText(tooltip);
		btn.setActionCommand(actionCommand);
		btn.addActionListener(listener);
		return btn;
	}

	/**
	 * Searches the table for specified name.
	 * 
	 * @param tableModel
	 *            The table model.
	 * @param timerName
	 *            The name to search for.
	 * @return true if the specified name already exists in the table and false
	 *         otherwise.
	 */
	private boolean isValidName(final TimeTrackerTableModel tableModel,
			final String timerName) {
		boolean isValid = true;
		final int rowsCount = tableModel.getRowCount();
		for (int i = 0; i < rowsCount; i++) {
			final String n = ((String) tableModel.getValueAt(i, 0)).trim();
			if (n.equals(timerName)) {
				isValid = false;
			}
		}
		return isValid;
	}

	@Override
	public void keyPressed(final KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			createTimerActionDispatcher();
		} else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
			newTimeTrackerInfoWindow.closeInfoWindow();
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {

	}

	@Override
	public void keyTyped(final KeyEvent e) {

	}
}
