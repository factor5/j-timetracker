package com.svelikov.timetracker.ui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Custom JButton renderer. Provides possibility for the JTable to render
 * JButton in its cells.
 * 
 * @author Svilen Velikov
 */
public class ActionButtonRenderer extends JButton implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		return (Component) value;
	}

}
