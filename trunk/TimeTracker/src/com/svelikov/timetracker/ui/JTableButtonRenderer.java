package com.svelikov.timetracker.ui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class JTableButtonRenderer implements TableCellRenderer {

	private final TableCellRenderer defaultCellRenderer;

	public JTableButtonRenderer(TableCellRenderer renderer) {
		defaultCellRenderer = renderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Component) {
			return (Component) value;
		}
		return defaultCellRenderer.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
	}
}
