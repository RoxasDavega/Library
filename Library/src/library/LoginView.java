/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import static library.AdminMenu.MenuAdmin;
import static library.StudentMenu.MenuStudent;

/**
 *
 * @author user
 */
public class LoginView extends JFrame implements ActionListener{
        
    JButton tombolLogout,tombolAdd, tombolDelete;
    Connector connect = new Connector();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JLabel lUsername = new JLabel("Username");
    JLabel lPassword = new JLabel("Password");
    
    JButton tombolLogin = new JButton("Login");
    JFrame f=new JFrame("Login");
    public LoginView(){
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(500,400);
        
        

        add(lUsername);
        add(lPassword);
        lUsername.setBounds(120, 100, 60, 40);
        lPassword.setBounds(120, 140, 60, 40);
        
        
        add(tombolLogin);
        tombolLogin.setBounds(180, 200, 80, 40);
        
        add(username);
        add(password);
        username.setBounds(200, 100, 130, 40);
        password.setBounds(200, 140, 130, 40);
        
        
        tombolLogin.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == tombolLogin){  
            try{
            if(getUsername().isBlank() || getUsername().isEmpty()){ //error handling username
                throw new IllegalArgumentException("Username is empty");
            }
            if(getPassword().isBlank() || getPassword().isEmpty()){ //error handling password
                throw new IllegalArgumentException("Password is empty");
            }
            Connector connection = new Connector();
            
            String username = getUsername();
            String password = getPassword();
            try{
                
                Statement stmt = connection.koneksi.createStatement();
                String st = ("SELECT * FROM USER WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'"); //Retrieve username and passwords from users
                ResultSet rs = stmt.executeQuery(st); //Execute query
                if (rs.next()) { //Move pointer below
                    f.dispose();
                    
                    String admin = rs.getString("role"); //mengambil role user
                   
                    String UID = rs.getString("uid"); //Get user ID of the user
                    if (admin.equals("1")) { //If boolean value 1/admin
                        
                        setVisible(false);
                        MenuAdmin();
                    } else {
                        
                        setVisible(false);
                        MenuStudent(UID);
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Username/Password!"); //Display Message
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }  
        }catch(HeadlessException | IllegalArgumentException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        }
        
    }
    public String getUsername(){
        return username.getText();
    }
    
    public String getPassword(){
        return String.valueOf(password.getPassword());
    }
    
    
}
