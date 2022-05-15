/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;
import javax.swing.JFrame;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import library.Library.ex;
/**
 *
 * @author user
 */
public class StudentMenu{
    public static void MenuStudent(String UID){
    JFrame f=new JFrame("Menu Student"); //Give dialog box name as User functions
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit user menu on closing the dialog box
    JButton view_but=new JButton("View Books");//creating instance of JButton  
    view_but.setBounds(20,20,120,25);//x axis, y axis, width, height 
    view_but.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){
             
            JFrame f = new JFrame("Books Available"); //View books stored in database
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
             
            Connector connection = new Connector();
            String sql="SELECT * FROM buku"; //Retreive data from database
            try {
                Statement stmt = connection.koneksi.createStatement(); //connect to database
                //stmt.executeUpdate("USE LIBRARY"); // use librabry
                stmt=connection.koneksi.createStatement();
                ResultSet rs=stmt.executeQuery(sql);
                JTable book_list= new JTable(); //show data in table format
                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                  
                JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
 
                f.add(scrollPane); //add scroll bar
                f.setSize(800, 400); //set dimensions of view books frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                 JOptionPane.showMessageDialog(null, e1);
            }               
             
    }
    }
    );
     
    JButton my_book=new JButton("My Books");//creating instance of JButton  
    my_book.setBounds(150,20,120,25);//x axis, y axis, width, height 
    my_book.addActionListener(new ActionListener() { //Perform action
        public void actionPerformed(ActionEvent e){
             
               
            JFrame f = new JFrame("My Books"); //View books issued by user
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            int UID_int = Integer.parseInt(UID); //Pass user ID
 
            //.iid,issued.uid,issued.bid,issued.issued_date,issued.return_date,issued,
            Connector connection = new Connector(); //connect to database
            //retrieve data
            String sql="SELECT DISTINCT pinjam.*, buku.judul,buku.penerbit FROM pinjam,buku " + "WHERE ((pinjam.uid=" + UID_int + ") AND (buku.bid IN (SELECT bid FROM pinjam WHERE pinjam.uid="+UID_int+"))) GROUP BY iid";
            String sql1 = "select bid from issued where uid="+UID_int;
            try {
                Statement stmt = connection.koneksi.createStatement();
                //use database
                //stmt.executeUpdate("USE LIBRARY");
                stmt=connection.koneksi.createStatement();
                //store in array
                ArrayList books_list = new ArrayList();
  
                
                 
                ResultSet rs=stmt.executeQuery(sql);
                JTable book_list= new JTable(); //store data in table format
                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                //enable scroll bar
                JScrollPane scrollPane = new JScrollPane(book_list);
 
                f.add(scrollPane); //add scroll bar
                f.setSize(800, 400); //set dimensions of my books frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                 JOptionPane.showMessageDialog(null, e1);
            }               
                 
    }
    }
    );
     
     
     
    f.add(my_book); //add my books
    f.add(view_but); // add view books
    f.setSize(300,100);//400 width and 500 height  
    f.setLayout(null);//using no layout managers  
    f.setVisible(true);//making the frame visible 
    f.setLocationRelativeTo(null);
    }
}
