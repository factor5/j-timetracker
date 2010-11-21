package test;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class EditorTest {
	private JScrollPane getTableComponent() {
		final String[] colNames = { "Stock", "Price", "Shares", "Quantity",
				"Action", "Action", "Holder" };
		final Object[][] data = {
				{ "MSFT", Double.valueOf(12.21), Integer.valueOf(10),
						Integer.valueOf(0), "Buy", "Sell", "Bill" },
				{ "IBM", Double.valueOf(13.21), Integer.valueOf(12),
						Integer.valueOf(0), "Buy", "Sell", "Tim" },
				{ "ORACLE", Double.valueOf(21.22), Integer.valueOf(11),
						Integer.valueOf(0), "Buy", "Sell", "Tom" } };
		final DefaultTableModel model = new DefaultTableModel(data, colNames) {
			@Override
			public Class getColumnClass(final int col) {
				return data[0][col].getClass();
			}
		};
		final JTable table = new JTable(model);
		final TableColumnModel colModel = table.getColumnModel();
		colModel.getColumn(1).setCellRenderer(new DoubleRenderer());
		colModel.getColumn(3).setCellRenderer(new SpinnerRenderer());
		colModel.getColumn(4).setCellRenderer(new ButtonRenderer());
		colModel.getColumn(5).setCellRenderer(new ButtonRenderer());
		colModel.getColumn(3).setCellEditor(new SpinnerEditor());
		colModel.getColumn(4).setCellEditor(new ButtonEditor(table));
		colModel.getColumn(5).setCellEditor(new ButtonEditor(table));
		table.setCellSelectionEnabled(true);
		final Dimension d = table.getPreferredSize();
		table.setPreferredScrollableViewportSize(d);
		return new JScrollPane(table);
	}

	public static void main(final String[] args) {
		final JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new EditorTest().getTableComponent());
		f.pack();
		f.setLocation(100, 100);
		f.setVisible(true);
	}
}

class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
	JTable table;
	SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
	JSpinner spinner = new JSpinner(model);
	int clickCountToStart = 1;

	public Component getTableCellEditorComponent(final JTable table,
			final Object value, final boolean isSelected, final int row,
			final int column) {
		spinner.setValue(((Integer) value).intValue());

		return spinner;
	}

	public Object getCellEditorValue() {
		return spinner.getValue();
	}

	@Override
	public boolean isCellEditable(final EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
		}
		return true;
	}

	@Override
	public boolean shouldSelectCell(final EventObject anEvent) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		return super.stopCellEditing();
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
	}
}

class ButtonEditor extends AbstractCellEditor implements TableCellEditor,
		ActionListener {
	JTable table;
	JButton button = new JButton();
	NumberFormat nf = NumberFormat.getCurrencyInstance();
	int clickCountToStart = 1;

	public ButtonEditor(final JTable table) {
		this.table = table;
		button.addActionListener(this);
	}

	public void actionPerformed(final ActionEvent e) {
		final StringBuilder sb = new StringBuilder();
		final int row = table.getEditingRow();
		final int col = table.getEditingColumn();
		// System.out.printf("row = %d  col = %d%n", row, col);
		sb.append((String) table.getValueAt(row, 6));
		sb.append(" has ");
		sb.append(((col == 4) ? "bought " : "sold "));
		sb.append(((Integer) table.getValueAt(row, 3)).toString());
		sb.append(" shares of " + (String) table.getValueAt(row, 0));
		sb.append(" at "
				+ nf.format(((Double) table.getValueAt(row, 1)).doubleValue()));
		stopCellEditing();
		System.out.println(sb.toString());
	}

	public Component getTableCellEditorComponent(final JTable table,
			final Object value, final boolean isSelected, final int row,
			final int column) {
		button.setText(value.toString());
		return button;
	}

	public Object getCellEditorValue() {
		return button.getText();
	}

	@Override
	public boolean isCellEditable(final EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
		}
		return true;
	}

	@Override
	public boolean shouldSelectCell(final EventObject anEvent) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		return super.stopCellEditing();
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
	}
}

class SpinnerRenderer implements TableCellRenderer {
	SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
	JSpinner spinner = new JSpinner(model);

	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		spinner.setValue(((Integer) value).intValue());
		return spinner;
	}
}

class ButtonRenderer implements TableCellRenderer {
	JButton button = new JButton();

	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		button.setText(value.toString());
		return button;
	}
}

class DoubleRenderer extends DefaultTableCellRenderer {
	NumberFormat nf = NumberFormat.getCurrencyInstance();

	public DoubleRenderer() {
		setHorizontalAlignment(RIGHT);
	}

	@Override
	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		setText(nf.format(((Double) value).doubleValue()));
		return this;
	}
}
