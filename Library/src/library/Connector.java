/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class Connector {
    private String DBurl = "jdbc:mysql://localhost/perpustakaan";
    private String DBUsername = "root";
    private String DBPassword = "";
    Connection koneksi;
    Statement statement;
   
  
    public Connector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl, DBUsername, DBPassword);
            System.out.println("Koneksi Berhasil");

        }catch(Exception e){
            System.out.println("Koneksi Gagal");
        }
    }
}
