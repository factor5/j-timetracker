package com.sve.timetracker;

import javax.swing.text.JTextComponent;

public class AppController {

	/* ===== singleton details ===== */

	private static AppController instance = null;

	protected AppController() {
		/*
		 * nothing needed, but this prevents a public no-arg constructor from
		 * being created automatically. Call this out in article text. Also add
		 * a note in article text: For maximum paranoia, override clone() to
		 * throw a CloneNotSupportedException.
		 */
	}

	public static AppController getInstance() {
		if (instance == null) {
			instance = new AppController();
		}
		return instance;
	}

	/* ===== controller instance fields, methods ===== */

	/* GUI widgets we want to call methods on ... */
	private JTextComponent textComponent = null;

	public void setTextComponent(final JTextComponent textComponent) {
		this.textComponent = textComponent;
	}

	/* actions we want to provide to GUI event handlers ... */

	public void handleEscapeAction() {
		final String oldText = textComponent.getText();
		final Escaper escaper = new Escaper();
		final String newText = escaper.escapeXML(oldText);
		textComponent.setText(newText);
	}

	public void handleExitAction() {
		System.exit(0);
	}

	/* a convenience action method as a followup part... */

	public void displayText(final String text) {
		textComponent.setText(text);
	}
}
