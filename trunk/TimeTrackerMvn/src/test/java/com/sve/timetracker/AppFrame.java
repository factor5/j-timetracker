package com.sve.timetracker;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AppFrame extends JFrame {

	AppController controller;

	private JTextArea textArea;
	private JButton escapeButton;
	private JButton exitButton;

	public AppFrame() {
		super("XML Escaper Demo");

		controller = AppController.getInstance();

		defineComponents();
		layoutComponents();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				controller.handleExitAction();
			}
		});
	}

	private void defineComponents() {
		textArea = new JTextArea();
		controller.setTextComponent(textArea);

		escapeButton = new JButton("Escape XML");
		escapeButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				controller.handleEscapeAction();
			}
		});

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				controller.handleExitAction();
			}
		});
	}

	private void layoutComponents() {

		/* create a panel to serve as frame's content pane... */
		final JPanel frameContentPanel = new JPanel();
		frameContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
				10));
		final BoxLayout frameLayout = new BoxLayout(frameContentPanel,
				BoxLayout.Y_AXIS);
		frameContentPanel.setLayout(frameLayout);

		/* create scroll pane for text area... */
		final JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400, 150));

		/* create sub-panel for buttons... */
		final JPanel buttonsPanel = new JPanel();
		final BoxLayout buttonsLayout = new BoxLayout(buttonsPanel,
				BoxLayout.X_AXIS);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		buttonsPanel.add(new JLabel("Article Demo / Rob Lybarger"));
		buttonsPanel.add(Box.createHorizontalGlue());
		buttonsPanel.add(escapeButton);
		buttonsPanel.add(exitButton);

		/* now add scroll pane and buttons panel to frame... */
		frameContentPanel.add(scrollPane);
		frameContentPanel.add(buttonsPanel);

		/* finishing details */
		setContentPane(frameContentPanel);
		pack();
	}
}
