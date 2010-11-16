package com.svelikov.timetracker.ui;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.svelikov.timetracker.LabelNameConstants;

/**
 * Custom table model.
 * 
 * @author Factor5
 * 
 */
public class TimeTrackerTableModel extends AbstractTableModel {

	private final ResourceBundle bundle = ResourceBundle.getBundle("labels");

	private final Vector<String> columnNames;

	private final Vector<Object> data;

	/**
	 * Constructor that creates this table model.
	 */
	public TimeTrackerTableModel() {
		// create the column names list
		columnNames = new Vector<String>(3);
		columnNames.add(bundle.getString(LabelNameConstants.BUTTON_NEW_TIMER));
		columnNames.add(bundle
				.getString(LabelNameConstants.COLUMN_ELLAPSED_TIME));
		columnNames.add(bundle.getString(LabelNameConstants.COLUMN_ACTIONS));

		// create some data
		data = new Vector<Object>();
		// Vector<Object> row = new Vector<Object>();
		// row.add("test timer");
		// row.add(new Date());
		// row.add(UIUtil.getActionButtons());
		//
		// data.add(row);
	}

	public int getColumnCount() {
		return columnNames.size();
	}

	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(final int col) {
		return columnNames.get(col);
	}

	public Object getValueAt(final int row, final int col) {
		return ((Vector<?>) data.get(row)).get(col);
	}

	@Override
	public Class getColumnClass(final int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public boolean isCellEditable(final int row, final int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		return false;
	}

	@Override
	public void setValueAt(final Object value, final int rowIndex,
			final int colIndex) {
		final Vector<Object> row = (Vector<Object>) data.get(rowIndex);
		row.set(colIndex, value);
		fireTableCellUpdated(rowIndex, colIndex);
	}

	/**
	 * @return the data
	 */
	public Vector<Object> getData() {
		return data;
	}

}
