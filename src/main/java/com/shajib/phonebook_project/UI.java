package com.shajib.phonebook_project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class UI extends javax.swing.JFrame {

    private int selectedContactId; 
    private int selectedRow = -1; 
    public UI() {
        
        initComponents();
        fetchContacts("");

        table.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String fN = (String) table.getValueAt(selectedRow, 0);
                        String lN = (String) table.getValueAt(selectedRow, 1);
                        String l = (String) table.getValueAt(selectedRow, 2);
                        String p = (String) table.getValueAt(selectedRow, 3);

                        firstName.setText(fN);
                        lastName.setText(lN);
                        location.setText(l);
                        phone.setText(p);

                        Add.setEnabled(false);

                        try {
                            Connection connection = ConnectDB.getConnection();
                            String idSql = "SELECT id FROM phn WHERE phone=?";
                            PreparedStatement idStatement = connection.prepareStatement(idSql);
                            idStatement.setString(1, p);
                            ResultSet resultSet = idStatement.executeQuery();

                            if (resultSet.next()) {
                                selectedContactId = resultSet.getInt("id");
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        
        // Make the search text field get focus when the UI is shown
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                search.requestFocusInWindow();
            }
        });
        
    }
    
    
    /* ================== Fetch Table Content ================== */
    
    private void fetchContacts(String query) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            Connection connection = ConnectDB.getConnection();
            
            String sql = "SELECT firstName,lastName,location,phone FROM phn "; 
            if (!query.isEmpty()) {
                sql += " WHERE firstName LIKE ? OR lastName LIKE ? OR location LIKE ? OR phone LIKE ?"; //When fetchContacts() function has parameter
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            if (!query.isEmpty()) {
                for (int i = 1; i <= 4; i++) {
                    statement.setString(i, "%" + query + "%");
                }
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String fN = resultSet.getString("firstName");
                String lN = resultSet.getString("lastName");
                String l = resultSet.getString("location");
                String p = resultSet.getString("phone");
                model.addRow(new Object[]{fN, lN, l, p});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching contacts: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    /* ================== Clear Form ================== */
    
    private void clearForm() { 
        firstName.setText(""); 
        lastName.setText(""); 
        location.setText(""); 
        phone.setText(""); 
 
        table.clearSelection(); 
        selectedRow = -1; 
 
        Add.setEnabled(true); 
        Edit.setEnabled(true); 
        Delete.setEnabled(true); 
    } 
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        firstName = new javax.swing.JTextField();
        lastName = new javax.swing.JTextField();
        location = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        cross = new javax.swing.JButton();
        back = new javax.swing.JButton();
        search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Phone Book");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Search :");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 204));

        table.setBackground(new java.awt.Color(255, 204, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Location", "Phone"
            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter Contact Details"));

        jLabel3.setText("First Name :");

        jLabel4.setText("Last Name :");

        jLabel5.setText("Location    :");

        jLabel6.setText("Phone        :");

        Add.setBackground(new java.awt.Color(51, 255, 51));
        Add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Edit.setBackground(new java.awt.Color(255, 255, 51));
        Edit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(255, 51, 51));
        Delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Clear.setBackground(new java.awt.Color(204, 255, 255));
        Clear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameActionPerformed(evt);
            }
        });

        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstName))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phone))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(location))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastName))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Add)
                        .addGap(18, 18, 18)
                        .addComponent(Edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete))
                    .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add)
                    .addComponent(Edit)
                    .addComponent(Delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Clear)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        cross.setBackground(new java.awt.Color(255, 255, 204));
        cross.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cross.setForeground(new java.awt.Color(255, 0, 0));
        cross.setText("X");
        cross.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crossActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(255, 255, 204));
        back.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        back.setForeground(new java.awt.Color(51, 51, 255));
        back.setText("←");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(207, 207, 207)
                .addComponent(cross)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cross))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    
    
    
    /* ================== Add ================== */
    
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        try {
            String fN = firstName.getText();
            String lN = lastName.getText();
            String l = location.getText();
            String p = phone.getText();
            
            
            if(p.isEmpty() || p.length()>11){
                JOptionPane.showMessageDialog(this, "Phone number is mandatory( less than 12 digit)", "Error", JOptionPane.ERROR_MESSAGE); 
                return;
            }
            String sql = "CALL phn_insert(?, ?, ?, ?)";
            Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, fN);
            statement.setString(2, lN);
            statement.setString(3, l);
            statement.setString(4, p);

            int rowsInserted = statement.executeUpdate();
            
            fetchContacts(""); 
            clearForm(); 

            if (rowsInserted > 0) { 
                JOptionPane.showMessageDialog(this, "Contact added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_AddActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        clearForm();
    }//GEN-LAST:event_ClearActionPerformed

    
    
    /* ================== Edit ================== */
    
    
    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        if (selectedRow == -1) { 
            JOptionPane.showMessageDialog(this, "Please select a contact to edit.", "Error", JOptionPane.ERROR_MESSAGE); 
            return; 
        } 

        // Retrieve data from the form fields 
        String fN = firstName.getText(); 
        String lN = lastName.getText(); 
        String l = location.getText(); 
        String p = phone.getText();

        try { 
            Connection connection = ConnectDB.getConnection(); 
            String updateSql = "UPDATE phn SET firstName=?, lastName=?, location=?, phone=? WHERE id=?"; 
            PreparedStatement updateStatement = connection.prepareStatement(updateSql); 
            updateStatement.setString(1, fN); 
            updateStatement.setString(2, lN); 
            updateStatement.setString(3, l); 
            updateStatement.setString(4, p); 
            updateStatement.setInt(5, selectedContactId); 

            // Execute the update statement 
            int rowsUpdated = updateStatement.executeUpdate(); 
            
            if (rowsUpdated > 0) { 
                fetchContacts(""); 
                clearForm(); 
                JOptionPane.showMessageDialog(this, "Contact updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
            } else { 
                JOptionPane.showMessageDialog(this, "Failed to update contact.", "Error", JOptionPane.ERROR_MESSAGE); 
            } 

        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(this, "Error updating contact: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE); 
        } 
    }//GEN-LAST:event_EditActionPerformed

    
    
    /* ================== Delete ================== */
    
    
    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        if (selectedRow == -1) { 
            JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE); 
            return; 
        }

        // Confirm deletion 
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contact?", "Confirm Deletion", JOptionPane.YES_NO_OPTION); 
        if (confirm != JOptionPane.YES_OPTION) { 
            return; 
        } 

        String deleteSql = "DELETE FROM phn WHERE id=?"; 

        try { 
            Connection connection = ConnectDB.getConnection(); 
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql); 
            deleteStatement.setInt(1, selectedContactId); 

            // Execute the delete statement 
            int rowsDeleted = deleteStatement.executeUpdate(); 
            if (rowsDeleted > 0) { 
                fetchContacts(""); 
                clearForm(); 
                JOptionPane.showMessageDialog(this, "Contact deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE); 
            }else{ 
                JOptionPane.showMessageDialog(this, "Failed to delete contact.", "Error", JOptionPane.ERROR_MESSAGE); 
            } 

        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(this, "Error deleting contact: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE); 
        } 

    }//GEN-LAST:event_DeleteActionPerformed

    
    /* ================== Cross/Close ================== */
    
    private void crossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crossActionPerformed
        dispose();
    }//GEN-LAST:event_crossActionPerformed
    
    /* ================== Back ================== */
    
    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        dispose();
        
        java.awt.EventQueue.invokeLater(() -> {
           LoginUI pUI = new LoginUI();
             pUI.setVisible(true);
        });
    }//GEN-LAST:event_backActionPerformed

    /* ================== Search ================== */
    
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
       String query = search.getText().trim(); 
       fetchContacts(query); 
    }//GEN-LAST:event_searchActionPerformed

    
    
    
    
    /* ================== Main ================== */
    
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Clear;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Edit;
    private javax.swing.JButton back;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cross;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lastName;
    private javax.swing.JTextField location;
    private javax.swing.JTextField phone;
    private javax.swing.JTextField search;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
