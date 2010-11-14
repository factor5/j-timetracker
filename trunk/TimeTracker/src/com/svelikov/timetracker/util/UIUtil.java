/**
 * 
 */
package com.svelikov.timetracker.util;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.svelikov.timetracker.exception.UIInitializationException;

/**
 * @author Factor5
 * 
 */
public class UIUtil {

	private UIUtil() {
	}

	public static void setColumnWidth(JTable table, final int columnNumber,
			final int width) throws UIInitializationException {
		TableColumnModel columnModel = table.getColumnModel();
		if (columnNumber < 0 || columnNumber > columnModel.getColumnCount()) {
			throw new UIInitializationException(
					"Column number must be > 0 and < "
							+ columnModel.getColumnCount());
		}
		TableColumn column = table.getColumnModel().getColumn(columnNumber);
		column.setPreferredWidth(width);
	}

	public static JPanel getActionButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		JButton stop = new JButton("stop");
		JButton start = new JButton("start");
		JButton remove = new JButton("remove");
		panel.add(stop);
		panel.add(start);
		panel.add(remove);
		return panel;
	}

}
