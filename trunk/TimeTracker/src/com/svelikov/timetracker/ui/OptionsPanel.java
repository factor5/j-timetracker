package com.svelikov.timetracker.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.LabelNameConstants;

/**
 * Options panel.
 * 
 * @author Svilen Velikov
 */
public class OptionsPanel extends TimeTrackerExtendedPanel {

	/**
	 * Button that is used to add/create a new TimeTracker.
	 */
	private JButton createTimeTracker;

	/**
	 * Constructor that creates this component.
	 */
	public OptionsPanel() {
		setLayout(null);
		setSize(new Dimension(600, 34));

		createTimeTracker = new JButton(bundle
				.getString(LabelNameConstants.BUTTON_NEW_TIMER));
		createTimeTracker.setActionCommand(ActionCommandConstants.NEW_TIMER);
		createTimeTracker.setToolTipText("Create a new time tracker");
		add(createTimeTracker);

		final Insets insets = getInsets();
		final Dimension size = createTimeTracker.getPreferredSize();
		createTimeTracker.setBounds(480 + insets.left, 4 + insets.top,
				size.width + 10, size.height);

		LOG.info("Create options panel.");
	}

	/**
	 * Sets a action listener to createTimeTracker button.
	 * 
	 * @param listener
	 *            The listener to be set for the button.
	 */
	public void setActionListener(final ActionListener listener) {
		createTimeTracker.addActionListener(listener);
	}
}
