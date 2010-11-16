package com.svelikov.timetracker.ui;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Custom column renderer. Provides possibility for the JTable to render JPanel
 * or JButon in its cells.
 * 
 * @author svelikov
 * 
 */
public class ActionColumnRenderer extends JPanel implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		return (Component) value;
	}

}
