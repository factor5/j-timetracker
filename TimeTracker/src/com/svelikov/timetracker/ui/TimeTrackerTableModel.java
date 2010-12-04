package com.svelikov.timetracker.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import com.svelikov.timetracker.LabelNameConstants;
import com.svelikov.timetracker.util.UIUtil;

/**
 * Custom table model.
 * 
 * @author Svilen Velikov
 */
public class TimeTrackerTableModel extends AbstractTableModel {

	/**
	 * Reference to the label's bundle.
	 */
	private final ResourceBundle bundle;

	/**
	 * Column names.
	 */
	private List<String> columnNames;

	/**
	 * The model.
	 */
	private List<Object> data;

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
		columnNames = new ArrayList<String>(6);
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

		data = new ArrayList<Object>();
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
		return ((List<?>) data.get(row)).get(col);
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
		final List<Object> row = (List<Object>) data.get(rowIndex);
		row.set(colIndex, value);
		fireTableCellUpdated(rowIndex, colIndex);
	}

	/**
	 * Getter for the underlying model.
	 * 
	 * @return the data
	 */
	public List<Object> getData() {
		return data;
	}

}
