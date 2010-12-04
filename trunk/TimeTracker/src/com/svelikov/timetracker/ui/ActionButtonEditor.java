package com.svelikov.timetracker.ui;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

/**
 * Custom JButton editor.
 * 
 * @author Svilen Velikov
 */
public class ActionButtonEditor extends JButton implements TableCellEditor {

	@Override
	public Component getTableCellEditorComponent(final JTable table,
			final Object value, final boolean isSelected, final int row,
			final int column) {
		buttonPressed(table, row, column);
		return (Component) value;
	}

	public void cancelCellEditing() {

	}

	public boolean stopCellEditing() {
		return true;
	}

	public Object getCellEditorValue() {
		return null;
	}

	public boolean isCellEditable(final EventObject anEvent) {
		return true;
	}

	public boolean shouldSelectCell(final EventObject anEvent) {
		return true;
	}

	public void addCellEditorListener(
			final CellEditorListener cellEditorListener) {

	}

	public void removeCellEditorListener(
			final CellEditorListener cellEditorListener) {

	}

	protected void fireCellEditing(final ChangeEvent changeEvent) {

	}

	private void buttonPressed(final JTable table, final int row,
			final int column) {
	}

}
