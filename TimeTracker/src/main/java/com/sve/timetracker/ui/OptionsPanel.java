package com.sve.timetracker.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.sve.timetracker.ActionCommandConstants;
import com.sve.timetracker.LabelNameConstants;
import com.sve.timetracker.action.TimeTrackerController;

/**
 * Options panel.
 * 
 * @author Svilen Velikov
 */
public class OptionsPanel extends TimeTrackerExtendedPanel {

	/**
	 * Button that is used to add/create a new TimeTracker.
	 */
	private JButton createTimeTrackerButton;

	/**
	 * Button that opens a configuration panel.
	 */
	private JButton configurationButton;

	/**
	 * Buttons that opens an about info panel.
	 */
	private JButton aboutButton;

	/**
	 * Constructor that creates this component.
	 * 
	 * @param controller
	 * @param mainWindowActionListener
	 *            Action listener used in this panel.
	 */
	public OptionsPanel(final ResourceBundle bundle,
			final TimeTrackerController controller) {
		super.controller = controller;
		super.bundle = bundle;
		setLayout(null);
		setSize(new Dimension(MAIN_WINDOW_WIDTH, 34));
		setBackground(BASE_OPTIONS_PANEL_BACKGROUND_COLOR);

		configurationButton = new JButton();
		createButton(configurationButton, ActionCommandConstants.CONFIGURATION,
				bundle.getString(LabelNameConstants.TOOLTIP_BTN_CONFIGURATION),
				"src/main/resources/img/config2-24.png", controller);
		add(configurationButton);

		aboutButton = new JButton();
		createButton(aboutButton, ActionCommandConstants.ABOUT, bundle
				.getString(LabelNameConstants.TOOLTIP_BTN_ABOUT),
				"src/main/resources/img/about2-24.png", controller);
		add(aboutButton);

		createTimeTrackerButton = new JButton();
		createButton(createTimeTrackerButton, ActionCommandConstants.NEW_TIMER,
				bundle.getString(LabelNameConstants.TOOLTIP_CREATE_TIMER),
				"src/main/resources/img/hourglass2-24.png", controller);
		add(createTimeTrackerButton);

		final Insets insets = getInsets();
		Dimension size = configurationButton.getPreferredSize();
		configurationButton.setBounds(5 + insets.left, 4 + insets.top,
				size.width, size.height);

		size = aboutButton.getPreferredSize();
		aboutButton.setBounds(35 + insets.left, 4 + insets.top, size.width,
				size.height);

		size = createTimeTrackerButton.getPreferredSize();
		createTimeTrackerButton.setBounds(590 + insets.left, 4 + insets.top,
				size.width, size.height);

		LOG.info("Created options panel.");
	}

	/**
	 * Initializes a button.
	 * 
	 * @param btn
	 *            The button's reference.
	 * @param actionCommand
	 *            The action command to apply.
	 * @param tooltipText
	 *            The tooltip text to apply.
	 * @param iconPath
	 *            The path to the icon for the button.
	 * @param actionListener
	 *            The action listener to be set.
	 */
	protected void createButton(final JButton btn, final String actionCommand,
			final String tooltipText, final String iconPath,
			final ActionListener actionListener) {
		final ImageIcon icon = new ImageIcon(iconPath);
		final StringBuilder activeIconPath = new StringBuilder(iconPath);
		activeIconPath.replace(22, 23, "\\active-");
		final ImageIcon rolloverIcon = new ImageIcon(activeIconPath.toString());
		final Cursor pointerCursor = new Cursor(Cursor.HAND_CURSOR);
		final Insets buttonInsets = configurationButton.getInsets();
		buttonInsets.set(-1, -1, -1, -1);
		btn.setMargin(buttonInsets);
		btn.setIcon(icon);
		btn.setRolloverEnabled(true);
		btn.setRolloverIcon(rolloverIcon);
		btn.setBorder(null);
		btn.setContentAreaFilled(false);
		btn.setCursor(pointerCursor);
		btn.setBackground(BASE_OPTIONS_PANEL_BACKGROUND_COLOR);
		btn.setActionCommand(actionCommand);
		btn.setToolTipText(tooltipText);
		btn.addActionListener(actionListener);
	}

}
