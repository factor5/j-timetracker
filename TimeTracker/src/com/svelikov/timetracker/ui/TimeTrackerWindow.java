package com.svelikov.timetracker.ui;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.svelikov.timetracker.LabelNameConstants;
import com.svelikov.timetracker.action.MainWindowActionListener;
import com.svelikov.timetracker.util.MessageType;
import com.svelikov.timetracker.util.UIUtil;

/**
 * This is the main interface window for this application.
 */
public class TimeTrackerWindow extends JPanel {

	private final ResourceBundle bundle = ResourceBundle.getBundle("labels");

	/**
	 * The action listener for this window.
	 */
	private MainWindowActionListener mainWindowActionListener;

	/**
	 * Constructor to initialize the user interface and to prepare some objects
	 * for use.
	 */
	public TimeTrackerWindow() {

	}

	/**
	 * Creates the GUI.
	 */
	public void makeMainWindow() {
		JFrame frame = new JFrame(bundle
				.getString(LabelNameConstants.MAIN_WINDOW_TITLE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.setLocation(200, 200);
		frame.setMinimumSize(new Dimension(600, 300));

		setLayout(null);
		setOpaque(true);

		try {
			TimeTrackerTableModel tableModel = new TimeTrackerTableModel();
			TimersListPanel timersListPanel = new TimersListPanel(tableModel);
			add(timersListPanel);

			mainWindowActionListener = new MainWindowActionListener(tableModel,
					frame);

			OptionsPanel optionsPanel = new OptionsPanel(bundle);
			optionsPanel.setActionListener(mainWindowActionListener);
			add(optionsPanel);

			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			UIUtil.setWarnings(this,
					"An error occured while starting application!",
					MessageType.WARNING);
		}
	}

}
