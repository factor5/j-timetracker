package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FuMain {
   private static void createAndShowUI() {

      JFrame frame = new JFrame("Customer Main");
      frame.getContentPane().add(new FuGui(), BorderLayout.CENTER);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            createAndShowUI();
         }
      });
   }
}

class FuGui extends JPanel {
   FuDisplayPanel displayPanel = new FuDisplayPanel();
   FuButtonPanel buttonPanel = new FuButtonPanel(); 
   FuInformationPanel informationPanel = new FuInformationPanel();

   public FuGui() {
      JPanel bottomPanel = new JPanel();
      bottomPanel.add(buttonPanel);
      bottomPanel.add(Box.createHorizontalStrut(10));
      bottomPanel.add(informationPanel);
      
      setLayout(new BorderLayout());
      add(displayPanel, BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
      
      buttonPanel.addInfoBtnAddActionListener(new ActionListener() {
         
         public void actionPerformed(ActionEvent e) {
            String lastName = informationPanel.getLastName();
            String firstName = informationPanel.getFirstName();
            
            displayPanel.addRow(lastName, firstName);
         }
      });
   }
}

class FuDisplayPanel extends JPanel {
   private String[] COLUMNS = {"Last Name", "First Name"};
   private DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
   private JTable table = new JTable(model);
   
   public FuDisplayPanel() {
      setLayout(new BorderLayout());
      add(new JScrollPane(table));
   }
   
   public void addRow(String lastName, String firstName) {
      Object[] row = new Object[2];
      row[0] = lastName;
      row[1] = firstName;
      model.addRow(row);
   }
}

class FuButtonPanel extends JPanel {
   private JButton addInfoButton = new JButton("Add Information");
   
   public FuButtonPanel() {
      add(addInfoButton);
   }
   
   public void addInfoBtnAddActionListener(ActionListener listener) {
      addInfoButton.addActionListener(listener);
   }
}

class FuInformationPanel extends JPanel {
   private JTextField lastNameField = new JTextField(10);
   private JTextField firstNameField = new JTextField(10);
   
   public FuInformationPanel() {
      add(new JLabel("Last Name:"));
      add(lastNameField);
      add(Box.createHorizontalStrut(10));
      add(new JLabel("First Name:"));
      add(firstNameField);
   }
   
   public String getLastName() {
      return lastNameField.getText();
   }
   
   public String getFirstName() {
      return firstNameField.getText();
   }
}
