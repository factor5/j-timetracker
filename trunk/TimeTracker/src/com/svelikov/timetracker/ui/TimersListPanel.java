package com.svelikov.timetracker.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.svelikov.timetracker.exception.UIInitializationException;
import com.svelikov.timetracker.util.UIUtil;

/**
 * A JTable wrapped in scroller pane.
 * 
 * @author svelikov
 */
public class TimersListPanel extends TimeTrackerExtendedPanel {

	/**
	 * This table.
	 */
	private JTable table;

	/**
	 * Constructor that creates this component.
	 * 
	 * @param tableModel
	 *            The table model to use for this table.
	 */
	public TimersListPanel(final TimeTrackerTableModel tableModel) {
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(575, 260));
		table.setFillsViewportHeight(true);
		table.setDefaultRenderer(JPanel.class, new ActionColumnRenderer());

		try {
			UIUtil.setColumnWidth(table, 0, 350);
			UIUtil.setColumnWidth(table, 1, 100);
			UIUtil.setColumnWidth(table, 2, 50);
		} catch (final UIInitializationException e) {
			log.debug(e);
		}

		setLayout(new BorderLayout());
		add(new JScrollPane(table));
		final Insets insets = getInsets();
		final Dimension size = getPreferredSize();
		setBounds(2 + insets.left, 35 + insets.top, size.width, size.height);

		log.info("Created timers list table.");
	}

}
