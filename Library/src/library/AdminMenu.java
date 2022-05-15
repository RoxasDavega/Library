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
import java.util.concurrent.TimeUnit;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import library.Library.ex;
/**
 *
 * @author user
 */
public class AdminMenu {
    public static void MenuAdmin(){
        JFrame f=new JFrame("Menu Admin");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton view_but=new JButton("View Books");//creating instance of JButton to view books
        view_but.setBounds(20,20,120,25);//x axis, y axis, width, height 
        view_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                JFrame f = new JFrame("Books Available"); 
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                Connector connection = new Connector(); //connect to database
                String sql="SELECT * FROM buku"; //select all books 
                try {
                    Statement stmt = connection.koneksi.createStatement();
                    //stmt.executeUpdate("USE LIBRARY"); //use database
                    stmt=connection.koneksi.createStatement();
                    ResultSet rs=stmt.executeQuery(sql);
                    JTable book_list= new JTable(); //view data in table format
                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                    //mention scroll bar
                    JScrollPane scrollPane = new JScrollPane(book_list); 

                    f.add(scrollPane); //add scrollpane
                    f.setSize(1200, 400); //set size for frame
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                     JOptionPane.showMessageDialog(null, e1);
                }               

            }
        }
        );
        
        JButton users_but=new JButton("View Users");//creating instance of JButton to view users
        users_but.setBounds(150,20,120,25);//x axis, y axis, width, height 
        users_but.addActionListener(new ActionListener() { //Perform action on click button
            public void actionPerformed(ActionEvent e){

                JFrame f = new JFrame("Users List");
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                Connector connection = new Connector();
                String sql="SELECT * FROM user"; //retrieve all users
                try {
                    Statement stmt = connection.koneksi.createStatement();
                    //stmt.executeUpdate("USE LIBRARY"); //use database
                    stmt=connection.koneksi.createStatement();
                    ResultSet rs=stmt.executeQuery(sql);
                    JTable book_list= new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
                    //mention scroll bar
                    JScrollPane scrollPane = new JScrollPane(book_list);

                    f.add(scrollPane); //add scrollpane
                    f.setSize(800, 400); //set size for frame
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, e1);
                }       
            }
        }
        );
        
        JButton issued_but=new JButton("View Issued Books");//creating instance of JButton to view the issued books
        issued_but.setBounds(280,20,160,25);//x axis, y axis, width, height 
        issued_but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                 
                JFrame f = new JFrame("Users List");
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
                 
                Connector connection = new Connector();
                String sql="SELECT * FROM pinjam";
                try {
                    Statement stmt = connection.koneksi.createStatement();
                    //stmt.executeUpdate("USE LIBRARY");
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
                    // TODO Auto-generated catch block
                     JOptionPane.showMessageDialog(null, e1);
                }       
                             
            }
        }
        );

        JButton add_user=new JButton("Add User"); //creating instance of JButton to add users
        add_user.setBounds(20,60,120,25); //set dimensions for button
        add_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                 
                JFrame g = new JFrame("Enter User Details"); //Frame to enter user details
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Create label 
                JLabel l1,l2,l3;  
                l1=new JLabel("Username");  //label 1 for username
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("Password");  //label 2 for password
                l2.setBounds(30,85, 100,30);
                 
                l3=new JLabel("Name");
                l3.setBounds(30,50, 100,30); 
                
                //set text field for username 
                JTextField F_user = new JTextField();
                F_user.setBounds(110, 15, 200, 30);
                
                JTextField F_name = new JTextField();
                F_name.setBounds(110, 50, 200, 30);
                
                //set text field for password
                JPasswordField F_pass=new JPasswordField();
                F_pass.setBounds(110, 85, 200, 30);
                
                //set radio button for admin
                JRadioButton a1 = new JRadioButton("Admin");
                a1.setBounds(55, 130, 200,30);
                //set radio button for user
                JRadioButton a2 = new JRadioButton("User");
                a2.setBounds(130, 130, 200,30);
                //add radio buttons
                ButtonGroup bg=new ButtonGroup();    
                bg.add(a1);bg.add(a2);  
                 
                                 
                JButton create_but=new JButton("Create");//creating instance of JButton for Create 
                create_but.setBounds(130,200,80,25);//x axis, y axis, width, height 
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
                        //stmt.executeUpdate("USE LIBRARY");
                        stmt.executeUpdate("INSERT INTO user(username,password, nama, role) VALUES ('"+username+"','"+password+"','"+name+"','"+admin+"')");
                        JOptionPane.showMessageDialog(null,"User added!");
                        g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                        // TODO Auto-generated catch block
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
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
                    g.setLocationRelativeTo(null);
                 
                 
            }
        }
        );
        
        JButton add_book=new JButton("Add Book"); //creating instance of JButton for adding books
        add_book.setBounds(150,60,120,25); 
        add_book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //set frame wot enter book details
                JFrame g = new JFrame("Enter Book Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // set labels
                JLabel l1,l2,l3;  
                l1=new JLabel("Book Name");  //lebel 1 for book name
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("Publisher");  //label 2 for genre
                l2.setBounds(30,53, 100,30); 
                 
                l3=new JLabel("Page");  //label 2 for price
                l3.setBounds(30,90, 100,30); 
                 
                //set text field for book name
                JTextField F_bname = new JTextField();
                F_bname.setBounds(110, 15, 200, 30);
                 
                //set text field for genre 
                JTextField F_publisher=new JTextField();
                F_publisher.setBounds(110, 53, 200, 30);
                //set text field for price
                JTextField F_page=new JTextField();
                F_page.setBounds(110, 90, 200, 30);
                         
                 
                JButton create_but=new JButton("Submit");//creating instance of JButton to submit details  
                create_but.setBounds(130,130,80,25);//x axis, y axis, width, height 
                create_but.addActionListener(new ActionListener() {
                     
                    public void actionPerformed(ActionEvent e){
                    // assign the book name, genre, price
                    String judul = F_bname.getText();
                    String penerbit = F_publisher.getText();
                    String tebal = F_page.getText();
                    //convert price of integer to int
                    int tebal_int = Integer.parseInt(tebal);
                     
                    Connector connection = new Connector();
                     
                    try {
                    Statement stmt = connection.koneksi.createStatement();
                     //stmt.executeUpdate("USE LIBRARY");
                    stmt.executeUpdate("INSERT INTO buku(judul,penerbit,tebal) VALUES ('"+judul+"','"+penerbit+"',"+tebal_int+")");
                    JOptionPane.showMessageDialog(null,"Book added!");
                    g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                        // TODO Auto-generated catch block
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
                    g.setSize(350,200);//400 width and 500 height  
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
                    g.setLocationRelativeTo(null);
                             
            }
        }
        );
        JButton issue_book=new JButton("Issue Book"); //creating instance of JButton to issue books
        issue_book.setBounds(450,20,120,25); 
        issue_book.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
                //enter details
                JFrame g = new JFrame("Enter Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //create labels
                JLabel l1,l2,l3,l4;  
                l1=new JLabel("Book ID(BID)");  // Label 1 for Book ID
                l1.setBounds(30,15, 100,30); 
                 
                 
                l2=new JLabel("User ID(UID)");  //Label 2 for user ID
                l2.setBounds(30,53, 100,30); 
                 
                l3=new JLabel("Period(days)");  //Label 3 for period
                l3.setBounds(30,90, 100,30); 
                 
                l4=new JLabel("Issued Date(YYYY-MM-DD)");  //Label 4 for issue date
                l4.setBounds(30,127, 150,30); 
                 
                JTextField F_bid = new JTextField();
                F_bid.setBounds(110, 15, 200, 30);
                 
                 
                JTextField F_uid=new JTextField();
                F_uid.setBounds(110, 53, 200, 30);
                 
                JTextField F_period=new JTextField();
                F_period.setBounds(110, 90, 200, 30);
                 
                JTextField F_issue=new JTextField();
                F_issue.setBounds(180, 130, 130, 30);   
 
                 
                JButton create_but=new JButton("Submit");//creating instance of JButton  
                create_but.setBounds(130,170,80,25);//x axis, y axis, width, height 
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
//                     stmt.executeUpdate("USE LIBRARY");
                    stmt.executeUpdate("INSERT INTO pinjam(uid,bid,tanggal_pinjam,jangka_waktu) VALUES ('"+uid+"','"+bid+"','"+issued_date+"',"+period_int+")");
                    JOptionPane.showMessageDialog(null,"Book Issued!");
                    g.dispose();
                      
                    }
                     
                    catch (SQLException e1) {
                        // TODO Auto-generated catch block
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
                    g.setSize(350,250);//400 width and 500 height  
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
                    g.setLocationRelativeTo(null);
                 
                 
            }
        }
        );
        JButton return_book=new JButton("Return Book"); //creating instance of JButton to return books
        return_book.setBounds(280,60,160,25); 
        return_book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                 
                JFrame g = new JFrame("Enter Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //set labels 
                JLabel l1,l2,l3,l4;  
                l1=new JLabel("Issue ID(IID)");  //Label 1 for Issue ID
                l1.setBounds(30,15, 100,30); 
                
                 
                l4=new JLabel("Return Date(YYYY-MM-DD)");  
                l4.setBounds(30,50, 150,30); 
                 
                JTextField F_iid = new JTextField();
                F_iid.setBounds(110, 15, 200, 30);
                 
                 
                JTextField F_return=new JTextField();
                F_return.setBounds(180, 50, 130, 30);
             
 
                JButton create_but=new JButton("Return");//creating instance of JButton to mention return date and calculcate fine
                create_but.setBounds(130,170,80,25);//x axis, y axis, width, height 
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
                            Date date_1=new SimpleDateFormat("yyyy-MM-DD").parse(date1);
                            Date date_2=new SimpleDateFormat("yyyy-MM-DD").parse(date2);
                            //subtract the dates and store in diff
                            //long diff = date_2.getTime() - date_1.getTime();
                            //Convert diff from milliseconds to days
                            //ex.days=(int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                             
                             
                        } catch (ParseException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                      
                     
                     //update return date
                     stmt.executeUpdate("UPDATE pinjam SET tanggal_kembali='"+return_date+"' WHERE iid="+iid);
                     g.dispose();

                    JOptionPane.showMessageDialog(null,"Book Returned!");
                      
                    }

                    catch (SQLException e1) {
                        // TODO Auto-generated catch block
                         JOptionPane.showMessageDialog(null, e1);
                    }   
                     
                    }
                     
                }); 
                    g.add(l4);
                    g.add(create_but);
                    g.add(l1);
                    g.add(F_iid);
                    g.add(F_return);
                    g.setSize(350,250);//400 width and 500 height  
                    g.setLayout(null);//using no layout managers  
                    g.setVisible(true);//making the frame visible 
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
    f.setSize(600,200);//400 width and 500 height  
    f.setLayout(null);//using no layout managers  
    f.setVisible(true);//making the frame visible 
    f.setLocationRelativeTo(null);
     
    
    }
}
