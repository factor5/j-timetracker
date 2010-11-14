package com.svelikov.timetracker;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.svelikov.timetracker.ui.TimeTrackerWindow;

public class TimeTrackerRunner {

	public static void main(String[] args) {
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TimeTrackerWindow main = new TimeTrackerWindow();
				main.makeMainWindow();
			}
		});
	}
}
