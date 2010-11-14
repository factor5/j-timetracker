package com.svelikov.timetracker.ui;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

class JTableButtonModel extends AbstractTableModel {

	private final Object[][] __rows = { { "One", new JButton("Button One") },
			{ "Two", new JButton("Button Two") },
			{ "Three", new JButton("Button Three") },
			{ "Four", new JButton("Button Four") } };

	private final String[] __columns = { "Numbers", "Buttons" };

	@Override
	public String getColumnName(int column) {
		return __columns[column];
	}

	public int getRowCount() {
		return __rows.length;
	}

	public int getColumnCount() {
		return __columns.length;
	}

	public Object getValueAt(int row, int column) {
		return __rows[row][column];
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}
}
