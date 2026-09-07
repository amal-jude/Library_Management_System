package amal;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField uname;
    private JTextField pass;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 453, 317);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 102, 102));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        uname = new JTextField();
        uname.setFont(new Font("Tahoma", Font.PLAIN, 12));
        uname.setBounds(203, 101, 96, 26);
        contentPane.add(uname);
        uname.setColumns(10);

        pass = new JPasswordField();
        pass.setBounds(203, 153, 96, 26);
        contentPane.add(pass);
        
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setForeground(new Color(174, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(127, 107, 96, 12);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password:");
        lblNewLabel_1.setForeground(new Color(174, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(127, 158, 115, 12);
        contentPane.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("LOGIN");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String u = uname.getText();
                String p = new String(pass.getPassword());
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    PreparedStatement pstmt = con.prepareStatement("select * from login where username=? and password=?");
                    pstmt.setString(1, u);
                    pstmt.setString(2, p);

                    ResultSet rs = pstmt.executeQuery();

                    if(rs.next()) {
                        JOptionPane.showMessageDialog(null,"        Login Successful");
                        home frame = new home();
        		        frame.setVisible(true);
        		        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"            Login Failed");
                    }
                    con.close();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        btnNewButton.setBounds(171, 203, 84, 20);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("Library Database");
        lblNewLabel_2.setForeground(new Color(174, 255, 255));
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_2.setBounds(150, 48, 168, 28);
        contentPane.add(lblNewLabel_2);
    }
}
