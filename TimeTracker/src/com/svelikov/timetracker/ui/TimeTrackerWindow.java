package com.svelikov.timetracker.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.svelikov.timetracker.LabelNameConstants;
import com.svelikov.timetracker.action.MainWindowActionListener;
import com.svelikov.timetracker.util.MessageType;
import com.svelikov.timetracker.util.UIUtil;

/**
 * This is the main interface window for this application.
 * 
 * @author svelikov
 */
public class TimeTrackerWindow extends TimeTrackerExtendedPanel {

	/**
	 * Constructor.
	 */
	public TimeTrackerWindow() {

	}

	/**
	 * Creates the GUI.
	 */
	public void makeMainWindow() {
		final JFrame frame = new JFrame(bundle
				.getString(LabelNameConstants.MAIN_WINDOW_TITLE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.setLocation(200, 200);
		frame.setMinimumSize(new Dimension(600, 300));
		setLayout(null);
		setOpaque(true);

		log.info("Created main window.");

		try {
			// create custom table model
			final TimeTrackerTableModel tableModel = new TimeTrackerTableModel();
			UIUtil.setTableModel(tableModel);

			// create the table
			final TimersListPanel timersListPanel = new TimersListPanel(
					tableModel);
			table = timersListPanel.getTable();
			UIUtil.setTable(table);
			add(timersListPanel);

			final MainWindowActionListener mainWindowActionListener = new MainWindowActionListener(
					frame);

			// create the options interface panel
			final OptionsPanel optionsPanel = new OptionsPanel();
			optionsPanel.setActionListener(mainWindowActionListener);
			add(optionsPanel);

			frame.pack();
			frame.setVisible(true);

		} catch (final Exception e) {
			log
					.debug(
							getLabel(LabelNameConstants.ERROR_APPLICATION_INITIALIZATION),
							e);
			UIUtil
					.setWarnings(
							this,
							getLabel(LabelNameConstants.ERROR_APPLICATION_INITIALIZATION),
							MessageType.WARNING);
		}
	}

}
