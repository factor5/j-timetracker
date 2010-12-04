package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.TimeTrackerDTO;

/**
 * @author Svilen Velikov
 */
public class TimerActionsListener extends BaseAction implements ActionListener {

	/**
	 * 
	 */
	@Override
	public void actionPerformed(final ActionEvent ae) {
		final int timerIndex = table.getEditingRow();
		if (ae.getActionCommand().equals(ActionCommandConstants.START_TIMER)) {
			startTimer(timerIndex);
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.STOP_TIMER)) {
			stopTimer(timerIndex);
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.REMOVE_TIMER)) {
			removeTimer(timerIndex);
		}
	}

	/**
	 * Stops the timer with given index.
	 * 
	 * @param timerIndex
	 *            The index of the timer to be stopped.
	 */
	private void stopTimer(final int timerIndex) {
		final TimeTrackerDTO timerTask = timers.get(timerIndex);
		final Timer timer = timerTask.getTimer();
		if (timer.isRunning()) {
			timer.stop();
			LOG.debug("Stoped timer with id (" + timerIndex
					+ "), elapsed time (" + timerTask.getElapsedTime() + ")");
		}
	}

	/**
	 * Removes the timer with given index.
	 * 
	 * @param timerIndex
	 *            The index of the timer to be removed.
	 */
	private void removeTimer(final int timerIndex) {
		final TimeTrackerDTO timerTask = timers.remove(timerIndex);
		final Timer timer = timerTask.getTimer();
		if (timer.isRunning()) {
			timer.stop();
		}
		LOG.debug("Removing timer with id (" + timerIndex + "), elapsed time ("
				+ timerTask.getElapsedTime() + ")");
		tableModel.getData().remove(timerIndex);
		tableModel.fireTableDataChanged();
	}

	/**
	 * Starts the timer with given index.
	 * 
	 * @param timerIndex
	 *            The index of the timer to be started.
	 */
	protected void startTimer(final int timerIndex) {
		final TimeTrackerDTO timerTask = timers.get(timerIndex);
		final Timer timer = timerTask.getTimer();
		if (!timer.isRunning()) {
			timer.start();
			LOG.debug("Starting timer with id (" + timerIndex
					+ "), current elapsed time (" + timerTask.getElapsedTime()
					+ ")");
		}
	}
}
