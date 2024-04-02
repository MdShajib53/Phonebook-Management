package com.shajib.phonebook_project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhoneBook_Project {

    public static void main(String[] args) {
        try {
            Connection connection = ConnectDB.getConnection();
            System.out.println("DB Connection Successfull");
        } 
        catch(SQLException ex){
            Logger.getLogger(PhoneBook_Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Call the login UI
        java.awt.EventQueue.invokeLater(() -> {
           LoginUI pUI = new LoginUI();
             pUI.setVisible(true);
        });
        
        
    }
}
