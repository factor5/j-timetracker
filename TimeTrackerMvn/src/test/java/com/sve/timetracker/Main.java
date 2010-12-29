package com.sve.timetracker;

public class Main {

	public static void main(final String[] args) {
		final AppFrame frame = new AppFrame();
		frame.setLocationRelativeTo(null); /* centers on screen */
		frame.setVisible(true);

		// show as a followup part...
		AppController.getInstance().displayText("<This is & a test./>");
	}

}
