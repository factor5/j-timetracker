package com.svelikov.timetracker.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.svelikov.timetracker.exception.UIInitializationException;
import com.svelikov.timetracker.util.UIUtil;

/**
 * A JTable wrapped in scroller pane.
 * 
 * @author Svilen Velikov
 */
public class TimersListPanel extends TimeTrackerExtendedPanel {

	/**
	 * This table.
	 */
	private final JTable table;

	/**
	 * Constructor that creates this component.
	 * 
	 * @param tableModel
	 *            The table model to use for this table.
	 */
	public TimersListPanel(final TimeTrackerTableModel tableModel) {
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(615, 260));
		table.setFillsViewportHeight(true);
		table.setFont(new Font(TABLE_FONT_FAMILY, TABLE_FONT_SIZE,
				TABLE_FONT_SIZE));
		table.setBackground(TABLE_BACKGROUND_COLOR);

		setCellRenderersAndEditors(table.getColumnModel());

		table.setRowHeight(TABLE_ROW_HEIGHT);
		final JTableHeader header = table.getTableHeader();
		header.setFont(new Font(TABLE_HEADER_FONT_FAMILY,
				TABLE_HEADER_FONT_STYLE, TABLE_HEADER_FONT_SIZE));
		header.setBackground(HEADER_BACKGROUND_COLOR);
		header.setForeground(WHITE_COLOR);

		try {
			UIUtil.setColumnWidth(table, 0, 300);
			UIUtil.setColumnWidth(table, 1, 100);
			UIUtil.setColumnWidth(table, 2, 35);
			UIUtil.setColumnWidth(table, 3, 35);
			UIUtil.setColumnWidth(table, 4, 35);
			UIUtil.setColumnWidth(table, 5, 35);
		} catch (final UIInitializationException e) {
			LOG.debug(e);
		}

		setLayout(new BorderLayout());
		add(new JScrollPane(table));
		final Insets insets = getInsets();
		final Dimension size = getPreferredSize();
		setBounds(2 + insets.left, 35 + insets.top, size.width, size.height);

		LOG.info("Created timers list table.");
	}

	protected void setCellRenderersAndEditors(
			final TableColumnModel tableColumnModel) {
		final ActionButtonRenderer renderer = new ActionButtonRenderer();
		final ActionButtonEditor editor = new ActionButtonEditor();
		for (int i = 2; i <= 5; i++) {
			tableColumnModel.getColumn(i).setCellRenderer(renderer);
			tableColumnModel.getColumn(i).setCellEditor(editor);
		}
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
