/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunisation;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author yifpei
 */
public class personreg extends JFrame {
    
     // Variables declaration - do not modify                     
    private JButton save;
    private JButton reset;
    private JCheckBox isagency;
    private JCheckBox isranger;
    private JComboBox category;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JTextField name;
    private JTextField email;
    // End of variables declaration
    
    String Name = new String ();
    String Category = new String ();
    String isAgency = new String ();
    String isRanger = new String ();
    String Email = new String ();
    
    public personreg (){
    initComponents ();
}
    private void initComponents () {
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        nameLabel = new JLabel ();
        nameLabel.setText ("Name");
        name = new JTextField ();
        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        
        category = new JComboBox ();
        category.setModel(new DefaultComboBoxModel(new String[] { "", "Nurse", "Admin" }));
        category.setToolTipText("");
        category.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });
        
        isagency = new JCheckBox ();
        isagency.setText("Agency");
        isagency.setToolTipText("");
        isagency.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                agencyItemStateChanged(evt);
            }
        });
        isagency.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                agencyMouseClicked(evt);
            }
        });
        
        isranger = new JCheckBox ();
        isranger.setText("Ranger");
        isranger.setToolTipText("");
        isranger.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                rangerItemStateChanged(evt);
            }
        });
        isranger.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                rangerMouseClicked(evt);
            }
        });
        
        emailLabel = new JLabel ();
        emailLabel.setText ("Email");
        email = new JTextField ();
        email.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        
        save = new JButton ();
        save.setText ("Save");
        save.addMouseListener (new MouseAdapter () {
            public void mouseClicked(MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        
        reset = new JButton ();
        reset.setText ("Reset");
        reset.addMouseListener(new MouseAdapter () {
            public void mouseClicked (MouseEvent evt) {
                resetMouseClicked (evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(emailLabel))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(name, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(isagency))
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(isranger)
                                                .addComponent(category, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                                .addComponent(email, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(save)
                                        .addGap(95, 95, 95)
                                        .addComponent(reset)))
                        .addContainerGap(40, Short.MAX_VALUE))
        );
               
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(name, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(category, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameLabel))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(isagency)
                                .addComponent(isranger))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(email, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(emailLabel))
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(save)
                                .addComponent(reset))
                        .addContainerGap(219, Short.MAX_VALUE))
        );
        
        pack();
        
    }
    
    private void nameActionPerformed (ActionEvent evt){
        
    }
    
    private void categoryActionPerformed (ActionEvent evt){
        
    }
    
    private void agencyItemStateChanged (ItemEvent evt) {
        
    }
    
    private void agencyMouseClicked (MouseEvent evt){
        
    }
    
    private void rangerItemStateChanged (ItemEvent evt){
        
    }
    
    private void rangerMouseClicked (MouseEvent evt){
        
    }
    
    private void emailActionPerformed (ActionEvent evt){
        
    }
    
    private void saveMouseClicked(MouseEvent evt) { 
        
        if ((!(name.getText().isEmpty())) && (!(category.getSelectedItem().equals("")))){
            Name = name.getText();
            Category = (String) category.getSelectedItem();
            if (isagency.isSelected ())
                isAgency = "true";
            else
                isAgency = "false";
            if (isranger.isSelected())
                isRanger = "true";
            else
                isRanger = "false";
            Email = email.getText();
            
            System.out.println(Name);
            System.out.println(Category);
            System.out.println(isAgency);
            System.out.println(isRanger);
            System.out.println(Email);
            
            save.setEnabled(false);
            
        } else {
            if (name.getText().isEmpty()) {
                System.out.println("Name empty");
            }
            if (category.getSelectedItem().equals("")) {
                System.out.println("Category is null");
            }
        }
        
        
    }
    
    private void resetMouseClicked (MouseEvent evt) {
        name.setText("");
        category.setSelectedIndex(0);
        isagency.setSelected(false);
        isagency.setEnabled(true);
        isranger.setSelected(false);
        isranger.setEnabled(true);
        email.setText ("");
        save.setEnabled(true);
    }
    
     public static void main(String args[]) {
         EventQueue.invokeLater(new Runnable() {
            public void run() {
                new personreg().setVisible(true);
            }
        });
     }
    
    public void updateDatabase (String q) {

        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://testsql:1433;DatabaseName5=roster_im", "yifpei", "1234");
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareCall(q);
            pstmt.executeUpdate();

            stmt.close();
            conn.close();
            System.out.println("updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     
     
}
