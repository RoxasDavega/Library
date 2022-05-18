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
/**
 *
 * @author user
 */
public class StudentMenu{
    public static void MenuStudent(String UID){
    JFrame f=new JFrame("Menu Student"); //Beri nama dialog box sesuai role User
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    JButton view_but=new JButton("View Books");
    view_but.setBounds(20,20,120,25);
    view_but.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){
             
            JFrame f = new JFrame("List Books"); //Melihat buku yang ada di database
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
             
            Connector connection = new Connector();
            String sql="SELECT * FROM buku"; //Ambil data dari database
            try {
                Statement stmt = connection.koneksi.createStatement(); //connect database
                
                stmt=connection.koneksi.createStatement();
                ResultSet rs=stmt.executeQuery(sql);
                JTable book_list= new JTable(){
                    @Override
                    public boolean isCellEditable( int row, int column )
                    {
                      return false;
                    }
                  }; 
                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                  
                JScrollPane scrollPane = new JScrollPane(book_list); //enable scroll bar
 
                f.add(scrollPane); //Menambah scroll bar
                f.setSize(800, 400); 
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            } catch (SQLException e1) {
                
                JOptionPane.showMessageDialog(null, e1);
            }               
             
    }
    }
    );
     
    JButton my_book=new JButton("My Books");
    my_book.setBounds(150,20,120,25);
    my_book.addActionListener(new ActionListener() { //Perform action
        public void actionPerformed(ActionEvent e){
             
               
            JFrame f = new JFrame("My Books"); 
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            int UID_int = Integer.parseInt(UID); //Pass user ID
 
           
            Connector connection = new Connector(); //connect database
            
            String sql="SELECT DISTINCT pinjam.*, buku.judul,buku.penerbit FROM pinjam,buku " + "WHERE ((pinjam.uid=" + UID_int + ") AND (buku.bid IN (SELECT bid FROM pinjam WHERE pinjam.uid="+UID_int+"))) GROUP BY iid";
            
            try {
                Statement stmt = connection.koneksi.createStatement();
               
                stmt=connection.koneksi.createStatement();
                ResultSet rs=stmt.executeQuery(sql);
                JTable book_list= new JTable(){
                    @Override
                    public boolean isCellEditable( int row, int column )
                    {
                      return false;
                    }
                  }; 
                book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                //enable scroll bar
                JScrollPane scrollPane = new JScrollPane(book_list);
 
                f.add(scrollPane); //menambah scroll bar
                f.setSize(800, 400); 
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            } catch (SQLException e1) {
                
                 JOptionPane.showMessageDialog(null, e1);
            }               
                 
    }
    }
    );

    f.add(my_book); //menambah my book
    f.add(view_but); // menambah view books
    f.setSize(300,100);
    f.setLayout(null);
    f.setVisible(true);
    f.setLocationRelativeTo(null);
    }
}
