package com.svelikov.timetracker.ui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.svelikov.timetracker.ActionCommandConstants;
import com.svelikov.timetracker.action.NewTimeTrackerInfoActionListener;

/**
 * 
 * @author svelikov
 */
public class NewTimeTrackerInfoWindow extends JFrame {

	private JTextField timeTrackerName;
	private JButton createTimerButton;
	private JButton cancelButton;
	private final TimeTrackerTableModel tableModel;
	private final JFrame mainWindow;

	/**
	 * Constructor.
	 * 
	 * @param tableModel
	 * @param mainWindow
	 */
	public NewTimeTrackerInfoWindow(final TimeTrackerTableModel tableModel,
			final JFrame mainWindow) {
		this.tableModel = tableModel;
		this.mainWindow = mainWindow;
	}

	/**
	 * 
	 */
	public void createAndShowWindow() {
		setTitle("New time tracker info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(2, 2, 2, 2));
		setSize(200, 100);

		final NewTimeTrackerInfoActionListener actionListener = new NewTimeTrackerInfoActionListener(
				this, tableModel, mainWindow);
		final JLabel timeTrackerNameLabel = new JLabel("Time tracker name: ");
		add(timeTrackerNameLabel);

		timeTrackerName = new JTextField();
		add(timeTrackerName);
		timeTrackerName.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(final KeyEvent arg0) {
			}

			@Override
			public void keyReleased(final KeyEvent arg0) {
			}

			@Override
			public void keyPressed(final KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("ENTER");
				} else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					closeInfoWindow();
				}
			}
		});

		createTimerButton = new JButton("Create");
		createTimerButton.setActionCommand(ActionCommandConstants.CREATE_TIMER);
		add(createTimerButton);
		createTimerButton.addActionListener(actionListener);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand(ActionCommandConstants.CANCEL_TIMER);
		add(cancelButton);
		cancelButton.addActionListener(actionListener);

		setLocationRelativeTo(mainWindow);
		setResizable(false);
		pack();
		setVisible(true);
	}

	/**
	 * @return the timeTrackerName
	 */
	public JTextField getTimeTrackerName() {
		return timeTrackerName;
	}

	/**
	 * 
	 */
	public void closeInfoWindow() {
		setVisible(false);
		dispose();
	}
}
