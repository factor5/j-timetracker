package com.sve.timetracker;

/*
 * This programs uses the information found in the UIManager to create a table
 * of key/value pairs for each Swing component.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class UIManagerDefaults implements ActionListener, ItemListener {
	private final static String[] COLUMN_NAMES = { "Key", "Value", "Sample" };
	private static String selectedItem;

	private JComponent contentPane;
	private JMenuBar menuBar;
	private JComboBox comboBox;
	private JRadioButton byComponent;
	private JTable table;
	private TreeMap<String, TreeMap<String, Object>> items;
	private HashMap<String, DefaultTableModel> models;

	/*
	 * Constructor
	 */
	public UIManagerDefaults() {
		items = new TreeMap<String, TreeMap<String, Object>>();
		models = new HashMap<String, DefaultTableModel>();

		contentPane = new JPanel(new BorderLayout());
		contentPane.add(buildNorthComponent(), BorderLayout.NORTH);
		contentPane.add(buildCenterComponent(), BorderLayout.CENTER);

		resetComponents();
	}

	/*
	 * The content pane should be added to a high level container
	 */
	public JComponent getContentPane() {
		return contentPane;
	}

	/*
	 * A menu can also be added which provides the ability to switch between
	 * different LAF's.
	 */
	public JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = createMenuBar();
		}

		return menuBar;
	}

	/*
	 * This panel is added to the North of the content pane
	 */
	private JComponent buildNorthComponent() {
		comboBox = new JComboBox();

		final JLabel label = new JLabel("Select Item:");
		label.setDisplayedMnemonic('S');
		label.setLabelFor(comboBox);

		byComponent = new JRadioButton("By Component", true);
		byComponent.setMnemonic('C');
		byComponent.addActionListener(this);

		final JRadioButton byValueType = new JRadioButton("By Value Type");
		byValueType.setMnemonic('V');
		byValueType.addActionListener(this);

		final ButtonGroup group = new ButtonGroup();
		group.add(byComponent);
		group.add(byValueType);

		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(15, 0, 15, 0));
		panel.add(label);
		panel.add(comboBox);
		panel.add(byComponent);
		panel.add(byValueType);
		return panel;
	}

	/*
	 * This panel is added to the Center of the content pane
	 */
	private JComponent buildCenterComponent() {
		final DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0);
		table = new JTable(model);
		table.setAutoCreateColumnsFromModel(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setCellRenderer(
				new SampleRenderer());
		final Dimension d = table.getPreferredSize();
		d.height = 350;
		table.setPreferredScrollableViewportSize(d);

		return new JScrollPane(table);
	}

	/*
	 * When the LAF is changed we need to reset the content pane
	 */
	public void resetComponents() {
		items.clear();
		models.clear();
		((DefaultTableModel) table.getModel()).setRowCount(0);

		buildItemsMap();

		final Vector<String> comboBoxItems = new Vector<String>(50);
		final Iterator keys = items.keySet().iterator();

		while (keys.hasNext()) {
			final Object key = keys.next();
			comboBoxItems.add((String) key);
		}

		comboBox.removeItemListener(this);
		comboBox.setModel(new DefaultComboBoxModel(comboBoxItems));
		comboBox.setSelectedIndex(-1);
		comboBox.addItemListener(this);
		comboBox.requestFocusInWindow();

		if (selectedItem != null) {
			comboBox.setSelectedItem(selectedItem);
		}
	}

	/*
	 * The item map will contain items for each component or items for each
	 * attribute type.
	 */
	private TreeMap buildItemsMap() {
		final UIDefaults defaults = UIManager.getLookAndFeelDefaults();

		// Build of Map of items and a Map of attributes for each item

		for (final Enumeration enumm = defaults.keys(); enumm.hasMoreElements();) {
			final Object key = enumm.nextElement();
			final Object value = defaults.get(key);

			final String itemName = getItemName(key.toString(), value);

			if (itemName == null) {
				continue;
			}

			// Get the attribute map for this componenent, or
			// create a map when one is not found

			TreeMap<String, Object> attributeMap = items.get(itemName);

			if (attributeMap == null) {
				attributeMap = new TreeMap<String, Object>();
				items.put(itemName, attributeMap);
			}

			// Add the attribute to the map for this componenent

			attributeMap.put(key.toString(), value);
		}

		return items;
	}

	/*
	 * Parse the key to determine the item name to use
	 */
	private String getItemName(final String key, final Object value) {
		// Seems like this is an old check required for JDK1.4.2

		if (key.startsWith("class") || key.startsWith("javax")) {
			return null;
		}

		if (byComponent.isSelected()) {
			return getComponentName(key, value);
		} else {
			return getValueName(key, value);
		}
	}

	private String getComponentName(final String key, final Object value) {
		// The key is of the form:
		// "componentName.componentProperty", or
		// "componentNameUI", or
		// "someOtherString"

		String componentName;

		final int pos = key.indexOf(".");

		if (pos != -1) {
			componentName = key.substring(0, pos);
		} else if (key.endsWith("UI")) {
			componentName = key.substring(0, key.length() - 2);
		} else if (value instanceof ColorUIResource) {
			componentName = "System Colors";
		} else {
			componentName = "Miscellaneous";
		}

		// Fix inconsistency

		if (componentName.equals("Checkbox")) {
			componentName = "CheckBox";
		}

		return componentName;
	}

	private String getValueName(final String key, final Object value) {
		if (value instanceof Icon) {
			return "Icon";
		} else if (value instanceof Font) {
			return "Font";
		} else if (value instanceof Border) {
			return "Border";
		} else if (value instanceof Color) {
			return "Color";
		} else if (value instanceof Insets) {
			return "Insets";
		} else if (value instanceof Boolean) {
			return "Boolean";
		} else if (value instanceof Dimension) {
			return "Dimension";
		} else if (value instanceof Number) {
			return "Number";
		} else if (key.endsWith("UI")) {
			return "UI";
		} else if (key.endsWith("InputMap")) {
			return "InputMap";
		} else if (key.endsWith("RightToLeft")) {
			return "InputMap";
		} else if (key.endsWith("radient")) {
			return "Gradient";
		} else {
			return "The Rest";
		}
	}

	/**
	 * Create menu bar
	 */
	private JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();

		menuBar.add(createFileMenu());
		menuBar.add(createLAFMenu());

		return menuBar;
	}

	/**
	 * Create menu items for the Application menu
	 */
	private JMenu createFileMenu() {
		final JMenu menu = new JMenu("Application");
		menu.setMnemonic('A');

		menu.addSeparator();
		menu.add(new ExitAction());

		return menu;
	}

	/**
	 * Create menu items for the Look & Feel menu
	 */
	private JMenu createLAFMenu() {
		final ButtonGroup bg = new ButtonGroup();

		final JMenu menu = new JMenu("Look & Feel");
		menu.setMnemonic('L');

		final String lafId = UIManager.getLookAndFeel().getID();
		final UIManager.LookAndFeelInfo[] lafInfo = UIManager
				.getInstalledLookAndFeels();

		for (int i = 0; i < lafInfo.length; i++) {
			final String laf = lafInfo[i].getClassName();
			final String name = lafInfo[i].getName();

			final Action action = new ChangeLookAndFeelAction(this, laf, name);
			final JRadioButtonMenuItem mi = new JRadioButtonMenuItem(action);
			menu.add(mi);
			bg.add(mi);

			if (name.equals(lafId)) {
				mi.setSelected(true);
			}
		}

		return menu;
	}

	/*
	 * Implement the ActionListener interface
	 */
	public void actionPerformed(final ActionEvent e) {
		selectedItem = null;
		resetComponents();
		comboBox.requestFocusInWindow();
	}

	/*
	 * Implement the ItemListener interface
	 */
	public void itemStateChanged(final ItemEvent e) {
		final String itemName = (String) e.getItem();
		changeTableModel(itemName);
		updateRowHeights();
		selectedItem = itemName;
	}

	/*
	 * Change the TabelModel in the table for the selected item
	 */
	private void changeTableModel(final String itemName) {
		// The model has been created previously so just use it

		DefaultTableModel model = models.get(itemName);

		if (model != null) {
			table.setModel(model);
			return;
		}

		// Create a new model for the requested item
		// and add the attributes of the item to the model

		model = new DefaultTableModel(COLUMN_NAMES, 0);
		final Map attributes = items.get(itemName);

		final Iterator ai = attributes.keySet().iterator();

		while (ai.hasNext()) {
			final String attribute = (String) ai.next();
			Object value = attributes.get(attribute);

			final Vector<Object> row = new Vector<Object>(3);
			row.add(attribute);

			if (value != null) {
				row.add(value.toString());

				if (value instanceof Icon) {
					value = new SafeIcon((Icon) value);
				}

				row.add(value);
			} else {
				row.add("null");
				row.add("");
			}

			model.addRow(row);
		}

		table.setModel(model);
		models.put(itemName, model);
	}

	/*
	 * Some rows containing icons, may need to be sized taller to fully display
	 * the icon.
	 */
	private void updateRowHeights() {
		try {
			for (int row = 0; row < table.getRowCount(); row++) {
				int rowHeight = table.getRowHeight();

				for (int column = 0; column < table.getColumnCount(); column++) {
					final Component comp = table.prepareRenderer(table
							.getCellRenderer(row, column), row, column);
					rowHeight = Math.max(rowHeight,
							comp.getPreferredSize().height);
				}

				table.setRowHeight(row, rowHeight);
			}
		} catch (final ClassCastException e) {
		}
	}

	/**
	 * Thanks to Jeanette for the use of this code found at:
	 * https://jdnc-incubator
	 * .dev.java.net/source/browse/jdnc-incubator/src/kleopatra
	 * /java/org/jdesktop
	 * /swingx/renderer/UIPropertiesViewer.java?rev=1.2&view=markup Some
	 * ui-icons misbehave in that they unconditionally class-cast to the
	 * component type they are mostly painted on. Consequently they blow up if
	 * we are trying to paint them anywhere else (f.i. in a renderer). This Icon
	 * is an adaption of a cool trick by Darryl Burke found at
	 * http://tips4java.wordpress.com/2008/12/18/icon-table-cell-renderer The
	 * base idea is to instantiate a component of the type expected by the icon,
	 * let it paint into the graphics of a bufferedImage and create an ImageIcon
	 * from it. In subsequent calls the ImageIcon is used.
	 */
	public static class SafeIcon implements Icon {
		private Icon wrappee;
		private Icon standIn;

		public SafeIcon(final Icon wrappee) {
			this.wrappee = wrappee;
		}

		@Override
		public int getIconHeight() {
			return wrappee.getIconHeight();
		}

		@Override
		public int getIconWidth() {
			return wrappee.getIconWidth();
		}

		@Override
		public void paintIcon(final Component c, final Graphics g, final int x,
				final int y) {
			if (standIn == this) {
				paintFallback(c, g, x, y);
			} else if (standIn != null) {
				standIn.paintIcon(c, g, x, y);
			} else {
				try {
					wrappee.paintIcon(c, g, x, y);
				} catch (final ClassCastException e) {
					createStandIn(e, x, y);
					standIn.paintIcon(c, g, x, y);
				}
			}
		}

		/**
		 * @param e
		 */
		private void createStandIn(final ClassCastException e, final int x,
				final int y) {
			try {
				final Class<?> clazz = getClass(e);
				final JComponent standInComponent = getSubstitute(clazz);
				standIn = createImageIcon(standInComponent, x, y);
			} catch (final Exception e1) {
				// something went wrong - fallback to this painting
				standIn = this;
			}
		}

		private Icon createImageIcon(final JComponent standInComponent,
				final int x, final int y) {
			final BufferedImage image = new BufferedImage(getIconWidth(),
					getIconHeight(), BufferedImage.TYPE_INT_ARGB);
			final Graphics g = image.createGraphics();
			try {
				wrappee.paintIcon(standInComponent, g, 0, 0);
				return new ImageIcon(image);
			} finally {
				g.dispose();
			}
		}

		/**
		 * @param clazz
		 * @throws IllegalAccessException
		 */
		private JComponent getSubstitute(final Class<?> clazz)
				throws IllegalAccessException {
			JComponent standInComponent;

			try {
				standInComponent = (JComponent) clazz.newInstance();
			} catch (final InstantiationException e) {
				standInComponent = new AbstractButton() {
				};
				((AbstractButton) standInComponent)
						.setModel(new DefaultButtonModel());
			}
			return standInComponent;
		}

		private Class<?> getClass(final ClassCastException e)
				throws ClassNotFoundException {
			String className = e.getMessage();
			className = className.substring(className.lastIndexOf(" ") + 1);
			return Class.forName(className);

		}

		private void paintFallback(final Component c, final Graphics g,
				final int x, final int y) {
			g.drawRect(x, y, getIconWidth(), getIconHeight());
			g.drawLine(x, y, x + getIconWidth(), y + getIconHeight());
			g.drawLine(x + getIconWidth(), y, x, y + getIconHeight());
		}

	}

	/*
	 * Render the value based on its class.
	 */
	class SampleRenderer extends JLabel implements TableCellRenderer {
		public SampleRenderer() {
			super();
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(final JTable table,
				final Object sample, final boolean isSelected,
				final boolean hasFocus, final int row, final int column) {
			setBackground(null);
			setBorder(null);
			setIcon(null);
			setText("");

			if (sample instanceof Color) {
				setBackground((Color) sample);
			} else if (sample instanceof Border) {
				setBorder((Border) sample);
			} else if (sample instanceof Font) {
				setText("Sample");
				setFont((Font) sample);
			} else if (sample instanceof Icon) {
				setIcon((Icon) sample);
			}

			return this;
		}

		/*
		 * Some icons are painted using inner classes and are not meant to be
		 * shared by other items. This code will catch the ClassCastException
		 * that is thrown.
		 */
		@Override
		public void paint(final Graphics g) {
			try {
				super.paint(g);
			} catch (final Exception e) {
				// System.out.println(e);
				// System.out.println(e.getStackTrace()[0]);
			}
		}
	}

	/*
	 * Change the LAF and recreate the UIManagerDefaults so that the properties
	 * of the new LAF are correctly displayed.
	 */
	class ChangeLookAndFeelAction extends AbstractAction {
		private UIManagerDefaults defaults;
		private String laf;

		protected ChangeLookAndFeelAction(final UIManagerDefaults defaults,
				final String laf, final String name) {
			this.defaults = defaults;
			this.laf = laf;
			putValue(Action.NAME, name);
			putValue(Action.SHORT_DESCRIPTION, getValue(Action.NAME));
		}

		public void actionPerformed(final ActionEvent e) {
			try {
				UIManager.setLookAndFeel(laf);
				defaults.resetComponents();

				final JMenuItem mi = (JMenuItem) e.getSource();
				final JPopupMenu popup = (JPopupMenu) mi.getParent();
				final JRootPane rootPane = SwingUtilities.getRootPane(popup
						.getInvoker());
				SwingUtilities.updateComponentTreeUI(rootPane);

				// Use custom decorations when supported by the LAF

				final JFrame frame = (JFrame) SwingUtilities
						.windowForComponent(rootPane);
				frame.dispose();

				if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
					frame.setUndecorated(true);
					frame.getRootPane().setWindowDecorationStyle(
							JRootPane.FRAME);
				} else {
					frame.setUndecorated(false);
				}

				frame.setVisible(true);
			} catch (final Exception ex) {
				System.out.println("Failed loading L&F: " + laf);
				System.out.println(ex);
			}
		}
	}

	/*
	 * Close the frame
	 */
	class ExitAction extends AbstractAction {
		public ExitAction() {
			putValue(Action.NAME, "Exit");
			putValue(Action.SHORT_DESCRIPTION, getValue(Action.NAME));
			putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_X));
		}

		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	/*
	 * Build a GUI using the content pane and menu bar of UIManagerDefaults
	 */
	private static void createAndShowGUI() {
		final UIManagerDefaults application = new UIManagerDefaults();

		JFrame.setDefaultLookAndFeelDecorated(true);
		final JFrame frame = new JFrame("UIManager Defaults");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(application.getMenuBar());
		frame.getContentPane().add(application.getContentPane());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/*
	 * UIManagerDefaults Main. Called only if we're an application.
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}