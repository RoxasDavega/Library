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
import java.util.Date;
/**
 *
 * @author user
 */
public class AdminMenu {
    public static void MenuAdmin(){
        JFrame f=new JFrame("Menu Admin");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton view_but=new JButton("View Books");
        view_but.setBounds(20,20,120,25);
        view_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                JFrame f = new JFrame("Books Available"); 
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                Connector connection = new Connector(); //connect to database
                String sql="SELECT * FROM buku"; //select all books 
                try {
                    Statement stmt = connection.koneksi.createStatement();
                    
                    stmt=connection.koneksi.createStatement();
                    ResultSet rs=stmt.executeQuery(sql);
                    JTable book_list= new JTable(); //melihat data dengan format tabel
                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                    //mention scroll bar
                    JScrollPane scrollPane = new JScrollPane(book_list); 

                    f.add(scrollPane); //menambah scrollpane
                    f.setSize(1200, 400); //set size frame
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    
                    JOptionPane.showMessageDialog(null, e1);
                }               

            }
        }
        );
        
        JButton users_but=new JButton("View Users");
        users_but.setBounds(150,20,120,25);
        users_but.addActionListener(new ActionListener() { //Perform action on click button
            public void actionPerformed(ActionEvent e){

                JFrame f = new JFrame("Users List");
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                Connector connection = new Connector();
                String sql="SELECT * FROM user"; //mengambil semua user
                try {
                    Statement stmt = connection.koneksi.createStatement();
                    
                    stmt=connection.koneksi.createStatement();
                    ResultSet rs=stmt.executeQuery(sql);
                    JTable book_list= new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                    //mention scroll bar
                    JScrollPane scrollPane = new JScrollPane(book_list);

                    f.add(scrollPane); //menambah scrollpane
                    f.setSize(800, 400); //set size for frame
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }       
            }
        }
        );
        
        JButton issued_but=new JButton("View Issued Books");
        issued_but.setBounds(280,20,160,25);
        issued_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                 
                JFrame f = new JFrame("Users List");
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
                 
                Connector connection = new Connector();
                String sql="SELECT * FROM pinjam";
                try {
                    Statement stmt = connection.koneksi.createStatement();
                    
                    stmt=connection.koneksi.createStatement();
                    ResultSet rs=stmt.executeQuery(sql);
                    JTable book_list= new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                     
                    JScrollPane scrollPane = new JScrollPane(book_list);
 
                    f.add(scrollPane);
                    f.setSize(800, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    
                    JOptionPane.showMessageDialog(null, e1);
                }       
                             
            }
        }
        );

        JButton add_user=new JButton("Add User"); 
        add_user.setBounds(20,60,120,25); //set dimensions for button
        add_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                 
                JFrame g = new JFrame("Enter User Details"); //Frame to enter user details
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Membuat label 
                JLabel l1,l2,l3;  
                l1=new JLabel("Username");  
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("Password");  
                l2.setBounds(30,85, 100,30);
                 
                l3=new JLabel("Name");
                l3.setBounds(30,50, 100,30); 
                
                //set text field untuk username 
                JTextField F_user = new JTextField();
                F_user.setBounds(110, 15, 200, 30);
                
                //set text field untuk name
                JTextField F_name = new JTextField();
                F_name.setBounds(110, 50, 200, 30);
                
                //set text field untuk password
                JPasswordField F_pass=new JPasswordField();
                F_pass.setBounds(110, 85, 200, 30);
                
                //set radio button untuk admin
                JRadioButton a1 = new JRadioButton("Admin");
                a1.setBounds(55, 130, 200,30);
                //set radio button untuk user
                JRadioButton a2 = new JRadioButton("User");
                a2.setBounds(130, 130, 200,30);
                //add radio buttons
                ButtonGroup bg=new ButtonGroup();    
                bg.add(a1);bg.add(a2);  
                 
                                 
                JButton create_but=new JButton("Create");
                create_but.setBounds(130,200,80,25);
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){
                     
                    String username = F_user.getText();
                    String password = String.valueOf(F_pass.getPassword());
                    String name = F_name.getText();
                    int admin = 2;
                     
                    if(a1.isSelected()) {
                        admin=1;
                    }
                     
                    Connector connection = new Connector();
                     
                    try {
                        Statement stmt = connection.koneksi.createStatement();
                       
                        stmt.executeUpdate("INSERT INTO user(username,password, nama, role) VALUES ('"+username+"','"+password+"','"+name+"','"+admin+"')");
                        JOptionPane.showMessageDialog(null,"User added!");
                        g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                        
                        JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                });
                     
                 
                    g.add(create_but);
                    g.add(a2);
                    g.add(a1);
                    g.add(l1);
                    g.add(l2);
                    g.add(l3);
                    g.add(F_user);
                    g.add(F_pass);
                    g.add(F_name);
                    g.setSize(450,300); 
                    g.setLayout(null);
                    g.setVisible(true);
                    g.setLocationRelativeTo(null);
                 
                 
            }
        }
        );
        
        JButton add_book=new JButton("Add Book"); 
        add_book.setBounds(150,60,120,25); 
        add_book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                JFrame g = new JFrame("Enter Book Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // set labels
                JLabel l1,l2,l3;  
                l1=new JLabel("Book Name");  //lebel 1 untuk nama buku
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("Publisher");  //label 2 untuk penerbit
                l2.setBounds(30,53, 100,30); 
                 
                l3=new JLabel("Page");  //label 3 untuk tebal halaman
                l3.setBounds(30,90, 100,30); 
                 
                //set text field untuk nama buku
                JTextField F_bname = new JTextField();
                F_bname.setBounds(110, 15, 200, 30);
                 
                //set text field untuk penerbit
                JTextField F_publisher=new JTextField();
                F_publisher.setBounds(110, 53, 200, 30);
                
                //set text field untuk tebal halaman
                JTextField F_page=new JTextField();
                F_page.setBounds(110, 90, 200, 30);
                         
                 
                JButton create_but=new JButton("Submit");
                create_but.setBounds(130,130,80,25);
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){
                    // mengisi nama buku, penerbit, tebal
                    String judul = F_bname.getText();
                    String penerbit = F_publisher.getText();
                    String tebal = F_page.getText();
                    //convert tebal menjadi int
                    int tebal_int = Integer.parseInt(tebal);
                     
                    Connector connection = new Connector();
                     
                    try {
                    Statement stmt = connection.koneksi.createStatement();
                    
                    stmt.executeUpdate("INSERT INTO buku(judul,penerbit,tebal) VALUES ('"+judul+"','"+penerbit+"',"+tebal_int+")");
                    JOptionPane.showMessageDialog(null,"Book added!");
                    g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                        
                        JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                });
                                 
                    g.add(l3);
                    g.add(create_but);
                    g.add(l1);
                    g.add(l2);
                    g.add(F_bname);
                    g.add(F_publisher);
                    g.add(F_page);
                    g.setSize(350,200);
                    g.setLayout(null);
                    g.setVisible(true);
                    g.setLocationRelativeTo(null);
                             
            }
        }
        );
        JButton issue_book=new JButton("Issue Book"); 
        issue_book.setBounds(450,20,120,25); 
        issue_book.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
               
                JFrame g = new JFrame("Enter Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //create labels
                JLabel l1,l2,l3,l4;  
                l1=new JLabel("Book ID(BID)");  // Label 1 untuk buku ID
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("User ID(UID)");  //Label 2 untuk user ID
                l2.setBounds(30,53, 100,30); 
                 
                l3=new JLabel("Period(days)");  //Label 3 untuk jangkat waktu
                l3.setBounds(30,90, 100,30); 
                 
                l4=new JLabel("Issued Date(YYYY-MM-DD)");  //Label 4 untuk tanggal pinjam
                l4.setBounds(30,127, 150,30); 
                 
                JTextField F_bid = new JTextField();
                F_bid.setBounds(110, 15, 200, 30);
                 
                 
                JTextField F_uid=new JTextField();
                F_uid.setBounds(110, 53, 200, 30);
                 
                JTextField F_period=new JTextField();
                F_period.setBounds(110, 90, 200, 30);
                 
                JTextField F_issue=new JTextField();
                F_issue.setBounds(180, 130, 130, 30);   
 
                 
                JButton create_but=new JButton("Submit");
                create_but.setBounds(130,170,80,25);
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){
                     
                    String uid = F_uid.getText();
                    String bid = F_bid.getText();
                    String period = F_period.getText();
                    String issued_date = F_issue.getText();
 
                    int period_int = Integer.parseInt(period);
                     
                    Connector connection = new Connector();
                     
                    try {
                    Statement stmt = connection.koneksi.createStatement();
                    stmt.executeUpdate("INSERT INTO pinjam(uid,bid,tanggal_pinjam,jangka_waktu) VALUES ('"+uid+"','"+bid+"','"+issued_date+"',"+period_int+")");
                    JOptionPane.showMessageDialog(null,"Book Issued!");
                    g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                       
                        JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                });
                     
                 
                    g.add(l3);
                    g.add(l4);
                    g.add(create_but);
                    g.add(l1);
                    g.add(l2);
                    g.add(F_uid);
                    g.add(F_bid);
                    g.add(F_period);
                    g.add(F_issue);
                    g.setSize(350,250);
                    g.setLayout(null);
                    g.setVisible(true);
                    g.setLocationRelativeTo(null);
                 
                 
            }
        }
        );
        JButton return_book=new JButton("Return Book"); 
        return_book.setBounds(280,60,160,25); 
        return_book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                 
                JFrame g = new JFrame("Enter Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //set labels 
                JLabel l1,l2,l3,l4;  
                l1=new JLabel("Issue ID(IID)");  //Label 1 untuk id pinjam
                l1.setBounds(30,15, 100,30); 
                
                 
                l4=new JLabel("Return Date(YYYY-MM-DD)");  
                l4.setBounds(30,50, 150,30); 
                 
                JTextField F_iid = new JTextField();
                F_iid.setBounds(110, 15, 200, 30);
                 
                 
                JTextField F_return=new JTextField();
                F_return.setBounds(180, 50, 130, 30);
             
 
                JButton create_but=new JButton("Return");
                create_but.setBounds(130,170,80,25);
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){                 
                     
                    String iid = F_iid.getText();
                    String return_date = F_return.getText();
                     
                    Connector connection = new Connector();
                     
                    try {
                    Statement stmt = connection.koneksi.createStatement();
                     //stmt.executeUpdate("USE LIBRARY");
                    //Intialize date1 with NULL value
                    String date1=null;
                    String date2=return_date; //Intialize date2 with return date

                    //select issue date
                    ResultSet rs = stmt.executeQuery("SELECT tanggal_pinjam FROM pinjam WHERE iid="+iid);
                    while (rs.next()) {
                        date1 = rs.getString(1);

                      }
                      
                     try {
                            Date date_1=new SimpleDateFormat("YYYY-MM-DD").parse(date1);
                            Date date_2=new SimpleDateFormat("YYYY-MM-DD").parse(date2);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                      
                     
                     //update return date
                     stmt.executeUpdate("UPDATE pinjam SET tanggal_kembali='"+return_date+"' WHERE iid="+iid);
                     g.dispose();

                    JOptionPane.showMessageDialog(null,"Book Returned!");
                      
                    }

                    catch (SQLException e1) {
                        
                        JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                }); 
                    g.add(l4);
                    g.add(create_but);
                    g.add(l1);
                    g.add(F_iid);
                    g.add(F_return);
                    g.setSize(350,250);
                    g.setLayout(null);
                    g.setVisible(true);
                    g.setLocationRelativeTo(null);              
    }
    });
     
    f.add(return_book);
    f.add(issue_book);
    f.add(add_book);
    f.add(issued_but);
    f.add(users_but);
    f.add(view_but);
    f.add(add_user);
    f.setSize(600,200);
    f.setLayout(null); 
    f.setVisible(true);
    f.setLocationRelativeTo(null);
     
    }
}
