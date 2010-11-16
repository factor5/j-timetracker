package com.svelikov.timetracker;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.svelikov.timetracker.ui.TimeTrackerWindow;

/**
 * Runner class for this application.
 * 
 * @author Factor5
 */
public class TimeTrackerRunner {

	public static void main(final String[] args) {
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (final UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (final IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (final InstantiationException ex) {
			ex.printStackTrace();
		} catch (final ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final TimeTrackerWindow main = new TimeTrackerWindow();
				main.makeMainWindow();
			}
		});
	}
}
