package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.ElapsedTime;
import com.svelikov.timetracker.LabelNameConstants;
import com.svelikov.timetracker.TimeTrackerDTO;
import com.svelikov.timetracker.ui.NewTimeTrackerInfoWindow;
import com.svelikov.timetracker.ui.TimeTrackerTableModel;
import com.svelikov.timetracker.util.MessageType;
import com.svelikov.timetracker.util.UIUtil;

/**
 * Action listener for the window where the user enters the name of the new time
 * tracker.
 * 
 * @author Svilen Velikov
 */
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

	/**
	 * Catches the events fired by the {@link NewTimeTrackerInfoWindow}
	 * controls.
	 */
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
		if ("".equals(timerName)) {
			UIUtil.setWarnings(mainWindow, bundle
					.getString(LabelNameConstants.MSG_MUST_PROVIDE_NAME),
					MessageType.INFORMATION);
		} else if (timerName.length() > TIMER_NAME_LENGTH) {
			UIUtil.setWarnings(mainWindow, bundle
					.getString(LabelNameConstants.MSG_NAME_TOO_LONG),
					MessageType.INFORMATION);
		} else if (isValidName(tableModel, timerName)) {
			final int timerRowId = createTimerView(timerName);
			storeTimer(timerName, timerRowId);
		} else {
			UIUtil.setWarnings(mainWindow, bundle
					.getString(LabelNameConstants.MSG_NAME_ALREADY_EXISTS),
					MessageType.INFORMATION);
		}
		newTimeTrackerInfoWindow.closeInfoWindow();
	}

	/**
	 * Creates a timer dto object and puts it in the timers map.
	 * 
	 * @param timerName
	 *            The name to use for this new timer.
	 * @param timerRowId
	 *            The row index of the new timer. This is the position in the
	 *            table model where this timer will be set.
	 */
	private void storeTimer(final String timerName, final int timerRowId) {
		final TimeTrackerDTO timer = new TimeTrackerDTO();
		timer.setTimerName(timerName);
		timer.setDateCreated(new Date());
		timer.setElapsedTime(new ElapsedTime());
		timer.setNotes("");
		timer.setId(timerRowId);

		final Timer timerTask = new Timer(DELAY, new TimeCounterListener(
				timerRowId, timer));
		timerTask.setInitialDelay(INITIAL_DELAY);
		timerTask.setDelay(DELAY);

		timer.setTimer(timerTask);

		timers.put(timerRowId, timer);
	}

	/**
	 * Creates a row in the table for the new timer.
	 * 
	 * @param timerName
	 *            The name for the new timer.
	 */
	protected int createTimerView(final String timerName) {
		final List<Object> newRow = new ArrayList<Object>(3);
		newRow.add(timerName);
		newRow.add("00:00:00");

		final TimerActionsListener timerActionsListener = new TimerActionsListener();

		newRow.add(getActionButton("img/notes-24.png", bundle
				.getString(LabelNameConstants.TOOLTIP_NOTES),
				ActionCommandConstants.VIEW_NOTES,
				new NotesActionButtonListener()));
		newRow.add(getActionButton("img/start-24.png", bundle
				.getString(LabelNameConstants.TOOLTIP_START_TIMER),
				ActionCommandConstants.START_TIMER, timerActionsListener));
		newRow.add(getActionButton("img/stop-24.png", bundle
				.getString(LabelNameConstants.TOOLTIP_STOP_TIMER),
				ActionCommandConstants.STOP_TIMER, timerActionsListener));
		newRow.add(getActionButton("img/delete-24.png", bundle
				.getString(LabelNameConstants.TOOLTIP_DELETE_TIMER),
				ActionCommandConstants.REMOVE_TIMER, timerActionsListener));

		final List<Object> data = tableModel.getData();
		data.add(newRow);
		tableModel.fireTableDataChanged();
		return data.indexOf(newRow);
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
