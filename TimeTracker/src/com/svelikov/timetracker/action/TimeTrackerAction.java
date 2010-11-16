package com.svelikov.timetracker.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import com.svelikov.timetracker.ActionCommandConstants;

/**
 * @author svelikov
 */
public class TimeTrackerAction extends WindowAdapter implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent ae) {
		if (ae.getActionCommand().equals(ActionCommandConstants.START_TIMER)) {
			System.out.println("START");
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.STOP_TIMER)) {
			System.out.println("STOP");
		} else if (ae.getActionCommand().equals(
				ActionCommandConstants.REMOVE_TIMER)) {
			System.out.println("REMOVE");
		}
	}

}
