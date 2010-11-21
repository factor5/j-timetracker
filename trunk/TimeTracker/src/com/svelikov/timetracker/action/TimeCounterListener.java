package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author svelikov
 */
public class TimeCounterListener extends BaseAction implements ActionListener {

	private int timerIndex;
	private static int count;

	public TimeCounterListener(final int timerIndex) {
		this.timerIndex = timerIndex;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		tableModel.setValueAt(count++, timerIndex, 1);
	}

}
