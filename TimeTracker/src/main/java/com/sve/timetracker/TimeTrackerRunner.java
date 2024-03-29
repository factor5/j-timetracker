package com.sve.timetracker;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.sve.timetracker.ui.TimeTrackerWindow;

/**
 * Runner class for this application.
 * 
 * @author Svilen Velikov
 */
public class TimeTrackerRunner {

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(TimeTrackerRunner.class);

	/**
	 * Starter method.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (final UnsupportedLookAndFeelException ex) {
			LOG.debug(ex);
		} catch (final IllegalAccessException ex) {
			LOG.debug(ex);
		} catch (final InstantiationException ex) {
			LOG.debug(ex);
		} catch (final ClassNotFoundException ex) {
			LOG.debug(ex);
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		// do other UI configurations
		UIManager.put("ToolTip.background", new Color(255, 234, 115));
		UIManager.put("ToolTip.foreground", Color.BLACK);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final TimeTrackerWindow main = new TimeTrackerWindow();
				main.makeMainWindow();
			}
		});
	}
}
