package com.svelikov.timetracker.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.svelikov.timetracker.exception.UIInitializationException;
import com.svelikov.timetracker.util.UIUtil;

/**
 * @author svelikov
 */
public class TimersListPanel extends JPanel {

	private JTable table;

	public TimersListPanel(TimeTrackerTableModel tableModel) {
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(575, 260));
		table.setFillsViewportHeight(true);
		table.setDefaultRenderer(JPanel.class, new ActionColumnRenderer());

		try {
			UIUtil.setColumnWidth(table, 0, 200);
			UIUtil.setColumnWidth(table, 1, 100);
			UIUtil.setColumnWidth(table, 2, 200);
		} catch (UIInitializationException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());
		add(new JScrollPane(table));
		Insets insets = getInsets();
		Dimension size = getPreferredSize();
		setBounds(2 + insets.left, 35 + insets.top, size.width, size.height);
	}

}
