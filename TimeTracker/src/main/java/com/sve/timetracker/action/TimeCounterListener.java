package com.sve.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sve.timetracker.model.ElapsedTime;
import com.sve.timetracker.model.TimeTrackerDTO;

/**
 * @author Svilen Velikov
 */
public class TimeCounterListener extends BaseAction implements ActionListener {

	private int timerIndex;
	private TimeTrackerDTO timerDTO;

	public TimeCounterListener(final int timerIndex, final TimeTrackerDTO timer) {
		this.timerIndex = timerIndex;
		this.timerDTO = timer;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		updateElapsedTime(timerDTO.getElapsedTime());
		tableModel.setValueAt(timerDTO.getElapsedTime(), timerIndex, 1);
	}

	/**
	 * Updates the ellapsedTime.
	 * 
	 * @param elapsedTime
	 *            The elapsed time taken from the underlying timer.
	 */
	private void updateElapsedTime(final ElapsedTime elapsedTime) {
		boolean hasCarry = false;
		final int minutes = elapsedTime.getMinutes();
		if (minutes == 59) {
			elapsedTime.setMinutes(0);
			hasCarry = true;
		} else {
			elapsedTime.setMinutes(minutes + 1);
		}
		if (hasCarry) {
			final int hours = elapsedTime.getHours();
			if (hours == 24) {
				elapsedTime.setHours(0);
				hasCarry = true;
			} else {
				elapsedTime.setHours(hours + 1);
				hasCarry = false;
			}
		}
		if (hasCarry) {
			final int days = elapsedTime.getDays();
			elapsedTime.setDays(days + 1);
			hasCarry = false;
		}
	}

}
