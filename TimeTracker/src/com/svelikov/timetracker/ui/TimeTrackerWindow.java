package com.svelikov.timetracker.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.LabelNameConstants;
import com.svelikov.timetracker.action.TimeTrackerActionListener;
import com.svelikov.timetracker.exception.UIInitializationException;
import com.svelikov.timetracker.util.UIUtil;

/**
 * This is the main interface window for this application.
 */
public class TimeTrackerWindow extends JPanel {

	private final ResourceBundle bundle = ResourceBundle.getBundle("labels");

	/**
	 * The action listener for this window.
	 */
	private TimeTrackerActionListener timeTrackerActionListener;

	private JTable table;

	private TimeTrackerTableModel tableModel;

	/**
	 * Button that is used to add/create a new TimeTracker.
	 */
	private JButton createTimeTracker;

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
			createTable();

			timeTrackerActionListener = new TimeTrackerActionListener(
					tableModel, frame);
			createOptionsPanel();

			frame.pack();
			frame.setVisible(true);
		} catch (UIInitializationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			setWarnings("An error occured whil starting the application!", 1);
		}
	}

	private void createOptionsPanel() {
		final JPanel optionsPanel = new JPanel(null);
		optionsPanel.setSize(new Dimension(600, 34));

		createTimeTracker = new JButton(bundle
				.getString(LabelNameConstants.BUTTON_NEW_TIMER));
		createTimeTracker.setActionCommand(ActionCommandConstants.NEW_TIMER);
		createTimeTracker.setToolTipText("Create a new time tracker");
		optionsPanel.add(createTimeTracker);
		createTimeTracker.addActionListener(timeTrackerActionListener);

		Insets insets = optionsPanel.getInsets();
		Dimension size = createTimeTracker.getPreferredSize();
		createTimeTracker.setBounds(480 + insets.left, 4 + insets.top,
				size.width + 10, size.height);
		add(optionsPanel);
	}

	private void createTable() throws UIInitializationException {
		tableModel = new TimeTrackerTableModel();
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(575, 260));
		table.setFillsViewportHeight(true);
		table.setDefaultRenderer(JPanel.class, new ActionColumnRenderer());
		UIUtil.setColumnWidth(table, 0, 200);
		UIUtil.setColumnWidth(table, 1, 100);
		UIUtil.setColumnWidth(table, 2, 200);

		JScrollPane scrollPane = new JScrollPane(table);
		Insets insets = scrollPane.getInsets();
		Dimension size = scrollPane.getPreferredSize();
		scrollPane.setBounds(2 + insets.left, 35 + insets.top, size.width,
				size.height);
		add(scrollPane);
	}

	/**
	 * Shows windows with messages according the parameters.
	 * 
	 * @param warningMessage
	 *            The message that should be displayed.
	 * @param msgType
	 *            The type of the window to be shown.
	 */
	public void setWarnings(String warningMessage, int msgType) {
		switch (msgType) {
		case 1:
			JOptionPane.showMessageDialog(this, warningMessage, "Warning",
					JOptionPane.WARNING_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(this, warningMessage, "Information",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
