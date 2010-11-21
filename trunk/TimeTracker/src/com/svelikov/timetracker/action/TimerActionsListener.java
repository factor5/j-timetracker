package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.svelikov.timetracker.ActionCommandConstants;

/**
 * @author svelikov
 */
public class TimerActionsListener extends BaseAction implements ActionListener {

	private static final int DELAY = 2000;
	private static final int INITIAL_DELAY = 0;

	@Override
	public void actionPerformed(final ActionEvent ae) {
		final int timerIndex = table.getEditingRow();
		if (ae.getActionCommand().equals(ActionCommandConstants.START_TIMER)) {
			startTimer(timerIndex);
			log.debug("Timer started: ID(" + timerIndex + ")");
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.STOP_TIMER)) {
			stopTimer(timerIndex);
			log.debug("Timer stopped: ID(" + timerIndex + ")");
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.REMOVE_TIMER)) {
			removeTimer(timerIndex);
			log.debug("Timer removed: ID(" + timerIndex + ")");
		}
	}

	private void stopTimer(final int timerIndex) {
		final int row = table.getEditingRow();
		timer.stop();
	}

	Timer timer;

	private void removeTimer(final int timerIndex) {
		stopTimer(timerIndex);
		tableModel.getData().remove(timerIndex);
		tableModel.fireTableDataChanged();
	}

	protected void startTimer(final int timerIndex) {
		timer = new Timer(DELAY, new TimeCounterListener(timerIndex));
		timer.setInitialDelay(INITIAL_DELAY);
		timer.setDelay(DELAY);
		timer.start();
	}
}
