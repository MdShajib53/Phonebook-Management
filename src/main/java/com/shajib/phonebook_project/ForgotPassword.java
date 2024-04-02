package com.shajib.phonebook_project;

//Email & OTP

import static com.shajib.phonebook_project.LoginUI.e;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

//For Email Validation
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ForgotPassword extends javax.swing.JFrame {
    public static String e;
    
    private String message = "Your Phonebook password reset OTP: ";
    private String subject = "OTP for Password Reset";
    private String to = "";
    private String from = "shajib.cse.53@gmail.com";
    
    String generated_otp;
    
    public ForgotPassword() {
        initComponents();
    }
    
    /* ================== Clear Login Form ================== */
    
    private void clearForgotFrom() { 
        email.setText(""); 
    } 
    
    /* ================== Email Validation ================== */
    
    private boolean isValidEmail(String email) {
    // Regular expression for validating email addresses
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /* ================== Send Email  ================== */
    
    private static void sendEmail(String message, String subject, String to, String from) {
        // Variable for Gmail host
        String host = "smtp.gmail.com";
        
        // Get the system Properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);
        
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
        jLabel2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        sendCode = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("E-mail :");

        varify.setBackground(new java.awt.Color(0, 255, 102));
        varify.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        varify.setForeground(new java.awt.Color(51, 0, 102));
        varify.setText("Varify");
        varify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varifyActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("OTP     :");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        sendCode.setBackground(new java.awt.Color(153, 255, 204));
        sendCode.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sendCode.setForeground(new java.awt.Color(51, 0, 102));
        sendCode.setText("Send Code");
        sendCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendCodeActionPerformed(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sendCode)
                            .addComponent(varify, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendCode))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varify))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void varifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varifyActionPerformed
        String o = otp.getText();
        if(o.equals(generated_otp)){
            dispose();
            
            java.awt.EventQueue.invokeLater(() -> {
                ResetUI rUI = new ResetUI();
                rUI.setVisible(true);
            });
        }else{
            JOptionPane.showMessageDialog(this, "Enter Valid OTP", "Error", JOptionPane.ERROR_MESSAGE);
            otp.setText("");
            return;
        }
    }//GEN-LAST:event_varifyActionPerformed

    private void sendCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendCodeActionPerformed
       
        e = email.getText();
        to = e;
        if(e.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please Enter Email", "Error", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        if(!isValidEmail(e)) {
            JOptionPane.showMessageDialog(this, "Enter Valid Email", "Error", JOptionPane.ERROR_MESSAGE); 
            clearForgotFrom();
            return;
        }
        
        try {
            Connection connection = ConnectDB.getConnection();
            String sql = "SELECT email,password FROM login WHERE email = ?"; 
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, e);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                // Generate a random OTP
                generated_otp = generateOTP(6); // 6 is the length of the OTP
                message = message + generated_otp;

                sendEmail(message, subject, to, from);
            }else{
                JOptionPane.showMessageDialog(this, "Email Doesn't Exist!!", "Error", JOptionPane.ERROR_MESSAGE); 
                return;
            }   
            
        }catch(SQLException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
        
    }//GEN-LAST:event_sendCodeActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        dispose();
        
        //Call the login UI
        java.awt.EventQueue.invokeLater(() -> {
           LoginUI pUI = new LoginUI();
             pUI.setVisible(true);
        });
    }//GEN-LAST:event_backActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField otp;
    private javax.swing.JButton sendCode;
    private javax.swing.JButton varify;
    // End of variables declaration//GEN-END:variables
}
