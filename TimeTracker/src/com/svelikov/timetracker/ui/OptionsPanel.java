package com.svelikov.timetracker.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.LabelNameConstants;

/**
 * @author svelikov
 */
public class OptionsPanel extends JPanel {

	/**
	 * Button that is used to add/create a new TimeTracker.
	 */
	private JButton createTimeTracker;

	public OptionsPanel(ResourceBundle bundle) {
		setLayout(null);
		setSize(new Dimension(600, 34));

		createTimeTracker = new JButton(bundle
				.getString(LabelNameConstants.BUTTON_NEW_TIMER));
		createTimeTracker.setActionCommand(ActionCommandConstants.NEW_TIMER);
		createTimeTracker.setToolTipText("Create a new time tracker");
		add(createTimeTracker);

		Insets insets = getInsets();
		Dimension size = createTimeTracker.getPreferredSize();
		createTimeTracker.setBounds(480 + insets.left, 4 + insets.top,
				size.width + 10, size.height);
	}

	public void setActionListener(ActionListener listener) {
		createTimeTracker.addActionListener(listener);
	}
}
