package com.svelikov.timetracker.ui;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.svelikov.timetracker.LabelNameConstants;
import com.svelikov.timetracker.util.UIUtil;

/**
 * Custom table model.
 * 
 * @author svelikov
 */
public class TimeTrackerTableModel extends AbstractTableModel {

	/**
	 * Reference to the label's bundle.
	 */
	private final ResourceBundle bundle;

	/**
	 * Column names.
	 */
	private Vector<String> columnNames;

	/**
	 * The model.
	 */
	private Vector<Object> data;

	/**
	 * Constructor that creates this table model.
	 */
	public TimeTrackerTableModel() {
		bundle = UIUtil.getBundle();
		createModel();
	}

	/**
	 * Creates the table model.
	 */
	private void createModel() {
		// create the column names list
		columnNames = new Vector<String>(6);
		columnNames.add(bundle.getString(LabelNameConstants.COLUMN_TIMER_NAME));
		columnNames.add(bundle
				.getString(LabelNameConstants.COLUMN_ELLAPSED_TIME));
		columnNames.add(bundle.getString(LabelNameConstants.COLUMN_NOTES));
		columnNames
				.add(bundle.getString(LabelNameConstants.COLUMN_ACTIONS_RUN));
		columnNames.add(bundle
				.getString(LabelNameConstants.COLUMN_ACTIONS_STOP));
		columnNames.add(bundle
				.getString(LabelNameConstants.COLUMN_ACTIONS_DELETE));

		data = new Vector<Object>();
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
		if (col == 0 || col == 1) {
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(final Object value, final int rowIndex,
			final int colIndex) {
		final Vector<Object> row = (Vector<Object>) data.get(rowIndex);
		row.set(colIndex, value);
		fireTableCellUpdated(rowIndex, colIndex);
	}

	/**
	 * Getter for the underlying model.
	 * 
	 * @return the data
	 */
	public Vector<Object> getData() {
		return data;
	}

}
