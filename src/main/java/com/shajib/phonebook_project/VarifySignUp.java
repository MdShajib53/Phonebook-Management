package com.shajib.phonebook_project;

import static com.shajib.phonebook_project.ForgotPassword.e;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class VarifySignUp extends javax.swing.JFrame {
    private String message = "Your Phonebook SignUp OTP : ";
    private String subject = "OTP for SignUp";
    private String to = "";
    private String from = "md.shajib.ce21053@gmail.com";
    
    //Email and password from LoginUI.java
    LoginUI obj = new LoginUI();
    String email = obj.e;
    String pass = obj.p;
        
    String generated_otp;
    
    public VarifySignUp() {
        initComponents();
        
        // Make the otp text field get focus when the Varify UI is shown
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                otp.requestFocusInWindow();
            }
        });
        
        to = email;
        
        // Generate a random OTP
        generated_otp = generateOTP(6); // 6 is the length of the OTP
        message = message + generated_otp;

        sendEmail(message, subject, to, from);    
    }
    
    private static void sendEmail(String message, String subject, String to, String from) {
        // Variable for Gmail host
        String host = "smtp.gmail.com";
        
        // Get the system Properties
        Properties properties = System.getProperties();
        //System.out.println("PROPERTIES " + properties);
        
        // Setting important information to properties object
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        // Step 1: Get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("md.shajib.ce21053@gmail.com", "unzbnatdryqtdrqq");
            }
        });
        
        session.setDebug(true);
        
        // Step 2: Compose the message
        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(from);
            // Adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                
            // Adding subject to message
            m.setSubject(subject);
            
            // Adding text to message
            m.setText(message);
            
            // Step 3: Send the message using Transport class
            Transport.send(m);
            
            System.out.println("Sent Successfully!");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    /* ================== OTP Generate ================== */
    
    private static String generateOTP(int length) {
        // Characters used for generating OTP
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }

    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        otp = new javax.swing.JTextField();
        varify = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Enter OTP : ");

        otp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        varify.setBackground(new java.awt.Color(0, 255, 51));
        varify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        varify.setText("Varify");
        varify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varifyActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(153, 255, 153));
        back.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        back.setForeground(new java.awt.Color(51, 51, 255));
        back.setText("←");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(varify)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(varify)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void varifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varifyActionPerformed
        String o = otp.getText();
        if(o.equals(generated_otp)){
                dispose();
            try {
                String sql = "CALL login_insert(?, ?)";
                Connection connection = ConnectDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                
                statement.setString(1,email);
                statement.setString(2,pass);
                
                statement.executeUpdate();
                
                
                JOptionPane.showMessageDialog(this, "Sign UP Successfull. " + email + " " + pass , "Success", JOptionPane.INFORMATION_MESSAGE); 
                
                //Call the login UI
                java.awt.EventQueue.invokeLater(() -> {
                   LoginUI pUI = new LoginUI();
                     pUI.setVisible(true);
                });
               
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error fetching contacts: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }       
        }else{
            JOptionPane.showMessageDialog(this, "Enter Valid OTP", "Error", JOptionPane.ERROR_MESSAGE);
            otp.setText("");
            return;
        }
    }//GEN-LAST:event_varifyActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        dispose();

        //Call the login UI
        java.awt.EventQueue.invokeLater(() -> {
            LoginUI pUI = new LoginUI();
            pUI.setVisible(true);
        });
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VarifySignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VarifySignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VarifySignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VarifySignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VarifySignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField otp;
    private javax.swing.JButton varify;
    // End of variables declaration//GEN-END:variables
}
