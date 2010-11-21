//package test;
//
//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.EventQueue;
//import java.awt.HeadlessException;
//import java.util.EventObject;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.event.CellEditorListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;
//
//public class ButtonEditorTest extends JFrame {
//
//	/**
//	 * @throws HeadlessException
//	 */
//	public ButtonEditorTest() throws HeadlessException {
//		super();
//
//		final JTable tbl = new JTable(5, 4);
//		final JScrollPane scroll = new JScrollPane(tbl);
//
//		tbl.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
//		tbl.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor());
//
//		getContentPane().add(scroll, BorderLayout.CENTER);
//		pack();
//	}
//
//	public static void main(final String[] args) {
//		final JFrame f = new ButtonEditorTest();
//		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//		EventQueue.invokeLater(new Runnable() {
//
//			public void run() {
//				f.setVisible(true);
//			}
//		});
//	}
//
//}
//
//class ButtonEditor extends JButton implements TableCellEditor {
//	public ButtonEditor() {
//		super("Button");
//	}
//
//	public Component getTableCellEditorComponent(final JTable table,
//			final Object value, final boolean isSelected, final int row,
//			final int column) {
//		buttonPressed(table, row, column);
//		return this;
//	}
//
//	public void cancelCellEditing() {
//		System.out.println("Cancel");
//	}
//
//	public boolean stopCellEditing() {
//		return true;
//	}
//
//	public Object getCellEditorValue() {
//		return null;
//	}
//
//	public boolean isCellEditable(final EventObject anEvent) {
//		return true;
//	}
//
//	public boolean shouldSelectCell(final EventObject anEvent) {
//		return true;
//	}
//
//	public void addCellEditorListener(final CellEditorListener l) {
//	}
//
//	public void removeCellEditorListener(final CellEditorListener l) {
//	}
//
//	protected void fireCellEditing(final ChangeEvent e) {
//
//	}
//
//	private void buttonPressed(final JTable table, final int row,
//			final int column) {
//		JOptionPane
//				.showMessageDialog(table, "Pressed at " + row + "x" + column);
//	}
//
//}
//
//class ButtonRenderer extends JButton implements TableCellRenderer {
//
//	public Component getTableCellRendererComponent(final JTable table,
//			final Object value, final boolean isSelected,
//			final boolean hasFocus, final int row, final int column) {
//		setText("" + (row + column));
//		return this;
//	}
// }
