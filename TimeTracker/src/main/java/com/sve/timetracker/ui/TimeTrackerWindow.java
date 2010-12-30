package com.sve.timetracker.ui;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.sve.timetracker.LabelNameConstants;
import com.sve.timetracker.action.TimeTrackerController;
import com.sve.timetracker.model.TimeTrackerMap;
import com.sve.timetracker.util.MessageType;
import com.sve.timetracker.util.UIUtil;

/**
 * This is the main interface window for this application.
 * 
 * @author Svilen Velikov
 */
public class TimeTrackerWindow extends TimeTrackerExtendedPanel {

	/**
	 * Constructor.
	 */
	public TimeTrackerWindow() {
		controller = TimeTrackerController.getInstance();
		final ResourceBundle bundle = ResourceBundle.getBundle("labels");
		controller.setBundle(bundle);
		setBundle(bundle);
		setLayout(null);
		setOpaque(true);
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
		frame.setPreferredSize(new Dimension(MAIN_WINDOW_WIDTH,
				MAIN_WINDOW_HEIGHT));
		frame.setResizable(false);

		LOG.info("Created main window.");

		try {
			// create custom table model
			final TimeTrackerTableModel tableModel = new TimeTrackerTableModel(
					bundle);
			controller.setTableModel(tableModel);

			// create the table
			final TimersListPanel timersListPanel = new TimersListPanel(
					tableModel);
			table = timersListPanel.getTable();
			controller.setTable(table);
			add(timersListPanel);

			final TimeTrackerMap timersMap = new TimeTrackerMap();

			// create the options interface panel
			final OptionsPanel optionsPanel = new OptionsPanel(getBundle(),
					controller);
			add(optionsPanel);

			controller.setTimeTrackerWindow(this);
			controller.setTimers(timersMap);

			frame.pack();
			frame.setVisible(true);

		} catch (final Exception e) {
			LOG
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
