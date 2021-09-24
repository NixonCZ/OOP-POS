/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicho
 */
public class POS extends javax.swing.JFrame {

    /**
     * Creates new form POSapp
     */
    Connection con = null;
    Statement st=null;
    PreparedStatement add=null;
    ResultSet rs = null;
    List<Integer> quantity1=new ArrayList<>();
    int i=0, total=0, billtotal=0;
    
    public POS() {
        initComponents();
        Displaybooks();
        bname.setEditable(false);
        price.setEditable(false);
        billtxt.setEditable(false);
        billno.setEditable(false);
        quantity.setEditable(false);
        countrow();
        backup();
        Billchk();
    }
    
    public POS(String Un){
        initComponents();
        userlabel.setText(Un);
        Displaybooks();
        bname.setEditable(false);
        price.setEditable(false);
        billtxt.setEditable(false);
        billno.setEditable(false);
        quantity.setEditable(false);
        countrow();
        backup();
        Billchk();
    }
    private void Billchk(){
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                String data=billtxt.getText().trim();
                if(!data.equals("")){
                    getToolkit().beep();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/User/icon/icons8_stop_sign_50px.png"));
                    JOptionPane.showMessageDialog(null,"Bill Payment Pending!","Error",JOptionPane.WARNING_MESSAGE, icon);
                }else{
                    System.exit(0);
                }
                    }
        });
    }
    
    private void Displaybooks(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/multiuserlogin", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `bookstock`");
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void backup(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/multiuserlogin", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT `Quantity` FROM `bookstock`");
            while (rs.next()) {      
                quantity1.add(rs.getInt("Quantity"));                                 
            }
        }catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   private void restore(){
            int size = quantity1.size();
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/multiuserlogin", "root", "");
                for (int num=0;num<size;num++) {
                    String Query = "UPDATE `bookstock` SET Quantity='"+quantity1.get(num)+"' WHERE BookId='"+(num+1)+"'"; 
                    Statement rest = con.createStatement();
                    rest.executeUpdate(Query);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void countrow(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/multiuserlogin", "root", "");
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT COUNT(*) FROM `billreports`");
            rs1.next();
            int Id = rs1.getInt(1)+1;
            billno.setText(Id+"");
        }catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void savebill(){
        if(userlabel.getText().isEmpty()||billno.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(null,"Give a Bill Number.");
        }else{
            try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost/multiuserlogin", "root", "");
                    PreparedStatement add=con.prepareStatement("INSERT INTO `billreports` VALUES(?,?,?,?)");
                    add.setString(1, userlabel.getText());
                    add.setInt(2, Integer.valueOf(billno.getText()));
                    add.setInt(3, billtotal);
                    
                    java.util.Date utilDate = new java.util.Date();
                    add.setObject(4, utilDate);
                    
                    int row = add.executeUpdate();
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Bill Saved.");
            } catch (SQLException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void reset(){
        bid.setText("");
        bname.setText("");
        quantity.setText("");
        price.setText("");
        billtxt.setText("");
        i=0;
        jLabel12.setText("");
        countrow();
        Displaybooks();
    }
    
    int bookid=0;
    
    private void stockupdate(){
        int newQty = stock - Integer.valueOf(quantity.getText());
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/multiuserlogin", "root", "");
            String Query = "UPDATE `bookstock` SET Quantity="+newQty+" WHERE BookId="+bookid+""; 
            Statement Delete = con.createStatement();
            Delete.executeUpdate(Query);
            getToolkit().beep();
            Displaybooks();
        }catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        userlabel = new javax.swing.JLabel();
        logoutbtn = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bname = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        billtxt = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        billno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(23, 31, 65));
        jPanel1.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(41, 57, 80));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_books_25px.png"))); // NOI18N
        jLabel1.setText("Stock");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8);
        jPanel8.setBounds(0, 244, 150, 47);

        jPanel18.setBackground(new java.awt.Color(71, 120, 197));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_cash_register_25px.png"))); // NOI18N
        jLabel5.setText("POS");

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel18);
        jPanel18.setBounds(0, 309, 150, 47);

        jPanel19.setBackground(new java.awt.Color(120, 168, 252));

        userlabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        userlabel.setForeground(new java.awt.Color(255, 255, 255));
        userlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_user_25px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(userlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel19);
        jPanel19.setBounds(0, 490, 150, 47);

        logoutbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_exit_50px.png"))); // NOI18N
        logoutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtnActionPerformed(evt);
            }
        });
        jPanel1.add(logoutbtn);
        logoutbtn.setBounds(50, 560, 50, 50);

        jPanel16.setBackground(new java.awt.Color(41, 57, 80));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_home_25px.png"))); // NOI18N
        jLabel4.setText("Home");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel16);
        jPanel16.setBounds(0, 179, 150, 47);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 640));

        jPanel2.setBackground(new java.awt.Color(71, 120, 197));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_books_75px.png"))); // NOI18N
        jLabel7.setText("Book Store Management System");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_users_75px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 860, -1));

        jPanel3.setBackground(new java.awt.Color(120, 168, 252));

        bname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        quantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        quantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        price.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 31, 65));
        jLabel2.setText("Book Name");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(23, 31, 65));
        jLabel8.setText("Quantity");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(23, 31, 65));
        jLabel9.setText("BID");

        billtxt.setColumns(20);
        billtxt.setRows(5);
        jScrollPane1.setViewportView(billtxt);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(23, 31, 65));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_bill_50px.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(23, 31, 65));
        jLabel10.setText("Bill");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(23, 31, 65));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setSelectionBackground(new java.awt.Color(41, 57, 80));
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setForeground(new java.awt.Color(23,31,65));
        jTable1.getTableHeader().setBackground(new java.awt.Color(168, 194, 251));
        jTable1.getTableHeader().setFont(new java.awt.Font("Times New Roman", 1, 14));

        bid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bid.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(23, 31, 65));
        jLabel13.setText("Price");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(23, 31, 65));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_bill_22px.png"))); // NOI18N
        jButton2.setText("Add To Bill");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_search_22px.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 110, 209));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/icon/icons8_reset_22px.png"))); // NOI18N
        jButton4.setText("Reset");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        billno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        billno.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(23, 31, 65));
        jLabel11.setText("Bill No:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel9)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel13)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bid, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(bname, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel11)
                        .addGap(9, 9, 9)
                        .addComponent(billno, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jButton1))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel11))
                            .addComponent(billno, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 860, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        String data=billtxt.getText().trim();
        if(!data.equals("")){
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Settle the Bill Payment!");
        }else{
            String username =userlabel.getText();
            Stockuser su=new Stockuser(username);
            su.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void logoutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtnActionPerformed
        // TODO add your handling code here:
        String data=billtxt.getText().trim();
        if(!data.equals("")){
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Settle Bill Payment!");
        }else{
            getToolkit().beep();
            int logoutResult = JOptionPane.showConfirmDialog (this, "Do you want to logout?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(logoutResult == JOptionPane.YES_OPTION){
                LoginForm l = new LoginForm();
                l.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_logoutbtnActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        String data=billtxt.getText().trim();
        if(!data.equals("")){
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Settle the Bill Payment!");
        }else{
            String username =userlabel.getText();
            Userapp u = new Userapp(username);
            u.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        if(bid.getText().isEmpty()){
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Empty filed Detected!");
        }else{
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/multiuserlogin", "root", "");
                st = con.createStatement();
                rs = st.executeQuery("SELECT * FROM `bookstock` WHERE `BookId`='"+bid.getText().toString()+"'");
                if(rs.next()){
                    ResultSet rs1 = st.executeQuery("SELECT * FROM `bookstock` WHERE `BookId`='"+bid.getText().toString()+"'");
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs1));
                    getToolkit().beep();
                }else{
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(this, "No Record Found!","Error",JOptionPane.YES_OPTION);
                    Displaybooks();
                    bid.setText("");
                }
            }catch (SQLException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3MouseClicked
    int stock=0;
    int price1=0;
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int MyIndex = jTable1.getSelectedRow();
        bookid = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
        bid.setText(model.getValueAt(MyIndex, 0).toString());
        bname.setText(model.getValueAt(MyIndex, 1).toString());
        price.setText("Rs."+model.getValueAt(MyIndex, 6).toString()+"/-");
        price1 = Integer.valueOf(model.getValueAt(MyIndex, 6).toString());
        stock = Integer.valueOf(model.getValueAt(MyIndex, 5).toString());
        quantity.setEditable(true);
        quantity.setText("");
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if(bname.getText().isEmpty()||quantity.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Missing Filed!");
        }else if(Integer.valueOf(quantity.getText())>stock){
            JOptionPane.showMessageDialog(this, "Stock Amount Unavaliable!","Error",JOptionPane.YES_OPTION);
        }else{
            i++;
            total=price1*Integer.valueOf(quantity.getText());
            if(i==1){
                billtxt.setText(billtxt.getText()+"==================BOOK STORE==================\n"+"NUM PRODUCT     PRICE      QUANTITY      TOTAL\n"+i+"   "+bname.getText().substring(0, 8)+"    "+price.getText()+"      "+quantity.getText()+"      Rs."+total+"/-\n");
            }else{
                billtxt.setText(billtxt.getText()+i+"   "+bname.getText().substring(0, 8)+"    "+price.getText()+"      "+quantity.getText()+"      Rs."+total+"/-\n");
            }
            billtotal = billtotal + total;
            jLabel12.setText("Total Bill  Rs."+billtotal+"/-");
            stockupdate();
            bid.setText("");
            bname.setText("");
            quantity.setText("");
            price.setText("");
            quantity.setText("");
            quantity.setEditable(false);
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String data=billtxt.getText().trim();
        if(data.equals("")){
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Bill Is Empty!");
        }else{
            ImageIcon icon = new ImageIcon(getClass().getResource("/User/icon/icons8_money_bill_wave_50px.png"));
            int billpay = JOptionPane.showConfirmDialog (this, "Is customer paid?","Confirm",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE, icon);
            if(billpay == JOptionPane.YES_OPTION){
                getToolkit().beep();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                billtxt.setText(billtxt.getText()+"\n             "+jLabel12.getText()+"\n\n             "+dtf.format(now)+"\n===========Thank You For Purchasing.===========\n"+"==================Come Again!==================\n");
                try{
                    billtxt.print();
                }catch(Exception e){
                }
                savebill();
                reset();
            }else if(billpay == JOptionPane.CANCEL_OPTION){
                reset();
                restore();
            }
        }
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        reset();
        restore();
    }//GEN-LAST:event_jButton4MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS().setVisible(true);
            }
        });
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bid;
    private javax.swing.JTextField billno;
    private javax.swing.JTextArea billtxt;
    private javax.swing.JTextField bname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JTextField price;
    private javax.swing.JTextField quantity;
    private javax.swing.JLabel userlabel;
    // End of variables declaration//GEN-END:variables
}
