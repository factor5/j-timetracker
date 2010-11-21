package com.svelikov.timetracker.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;

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

		table.getColumnModel().getColumn(2).setCellRenderer(
				new ActionButtonRenderer());
		table.getColumnModel().getColumn(2).setCellEditor(
				new ActionButtonEditor());
		table.getColumnModel().getColumn(3).setCellRenderer(
				new ActionButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(
				new ActionButtonEditor());
		table.getColumnModel().getColumn(4).setCellRenderer(
				new ActionButtonRenderer());
		table.getColumnModel().getColumn(4).setCellEditor(
				new ActionButtonEditor());
		table.getColumnModel().getColumn(5).setCellRenderer(
				new ActionButtonRenderer());
		table.getColumnModel().getColumn(5).setCellEditor(
				new ActionButtonEditor());

		try {
			UIUtil.setColumnWidth(table, 0, 300);
			UIUtil.setColumnWidth(table, 1, 100);
			UIUtil.setColumnWidth(table, 2, 25);
			UIUtil.setColumnWidth(table, 3, 25);
			UIUtil.setColumnWidth(table, 4, 25);
			UIUtil.setColumnWidth(table, 5, 25);
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

	/**
	 * Getter for the created table.
	 * 
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

}
