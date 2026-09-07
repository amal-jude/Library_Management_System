package amal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Member_Table extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mphone;
	private JTextField memail;
	private JTextField mname;
	private JTextField mid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member_Table frame = new Member_Table();
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
	public Member_Table() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberid = new JLabel("Member_ID");
		lblMemberid.setBounds(126, 59, 69, 12);
		contentPane.add(lblMemberid);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(126, 97, 44, 12);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(126, 135, 44, 12);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone number");
		lblNewLabel_3.setBounds(126, 166, 96, 20);
		contentPane.add(lblNewLabel_3);
		
		mphone = new JTextField();
		mphone.setColumns(10);
		mphone.setBounds(239, 167, 134, 18);
		contentPane.add(mphone);
		
		memail = new JTextField();
		memail.setColumns(10);
		memail.setBounds(239, 132, 134, 18);
		contentPane.add(memail);
		
		mname = new JTextField();
		mname.setColumns(10);
		mname.setBounds(239, 94, 134, 18);
		contentPane.add(mname);
		
		mid = new JTextField();
		mid.setColumns(10);
		mid.setBounds(239, 56, 64, 18);
		contentPane.add(mid);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = mid.getText();
				String nm = mname.getText();
				String ph = mphone.getText();
				String ml = memail.getText();
				if(id.isEmpty() || nm.isEmpty() || ml.isEmpty() || ph.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All Field Must Be Filled!");
					return;
				}
				try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    PreparedStatement pstmt = con.prepareStatement("insert into members values(?,?,?,?)");
                    pstmt.setString(1, id);
                    pstmt.setString(2, nm);
                    pstmt.setString(3, ml);
                    pstmt.setString(4, ph);

                    int rowadd = pstmt.executeUpdate();

                    if (rowadd>0) {
                    	JOptionPane.showMessageDialog(null,"Member Added"); 
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");   
                    }
                    con.close();
                }
                catch (Exception ex) {
                	if(ex.getMessage().contains("ORA-00001")) {
                        JOptionPane.showMessageDialog(null,"Member ID already exists");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
                }
				
			}
		});
		btnNewButton.setBounds(101, 204, 84, 20);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = mid.getText();
				String nm = mname.getText();
				String ph = mphone.getText();
				String ml = memail.getText();
				if(id.isEmpty() || nm.isEmpty() || ml.isEmpty() || ph.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All Field Must Be Filled!");
					return;
				}
				try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    PreparedStatement pstmt = con.prepareStatement("update members set name =?, email=?, phone=? where member_id=?");
                    pstmt.setString(4, id);
                    pstmt.setString(1, nm);
                    pstmt.setString(2, ml);
                    pstmt.setString(3, ph);

                    int rowupdate = pstmt.executeUpdate();

                    if (rowupdate>0) {
                    	JOptionPane.showMessageDialog(null,"Member Updated"); 
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");   
                    }
                    con.close();
                }
                catch (Exception ex) {
                	if(ex.getMessage().contains("ORA-00001")) {
                        JOptionPane.showMessageDialog(null,"Member ID already exists");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
                }
			}
		});
		btnNewButton_1.setBounds(195, 204, 84, 20);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = mid.getText();

		        if(id.isEmpty()) {
		            JOptionPane.showMessageDialog(null,"Member_ID Must Be Filled!");
		            return;
		        }

		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");

		            PreparedStatement check = con.prepareStatement("select Member_ID from members where Member_ID=?");
		            check.setString(1,id);

		            ResultSet rs = check.executeQuery();

		            if(!rs.next()) {
		                JOptionPane.showMessageDialog(null,"Member_ID " + id + " Does Not Exist!");
		                con.close();
		                return;
		            }

		            int choice = JOptionPane.showConfirmDialog(null,"Do you want to delete Member_ID = " + id + "?","Confirm Delete",JOptionPane.YES_NO_OPTION);
		            
		            if(choice == JOptionPane.YES_OPTION) {
		                PreparedStatement pstmt = con.prepareStatement("delete from members where Member_ID=?");
		                pstmt.setString(1,id);

		                int rowdelete = pstmt.executeUpdate();

		                if(rowdelete > 0) {
		                    JOptionPane.showMessageDialog( null, "Member Deleted Successfully");}
		                else {
		                    JOptionPane.showMessageDialog( null, "Member Deletion Failed");
		                }
		            }
		            con.close();
		        }
		        catch(Exception ex) {
		        	System.out.println(ex);
		        	if(ex.getMessage().contains("ORA-01722")) {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
		        }
			}
		});
		btnNewButton_2.setBounds(289, 204, 84, 20);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("BACK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home frame = new home();
		        frame.setVisible(true);

		        dispose();
			}
		});
		btnNewButton_3.setBounds(10, 233, 69, 20);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("VIEW");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    Statement stmt = con.createStatement();
                    
                    ResultSet rs = stmt.executeQuery("select * from members order by member_id");
                    
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    con.close();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
			}
		});
		btnNewButton_4.setBounds(585, 204, 84, 20);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 56, 358, 130);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Member_ID", "Name", "Email", "Phone"
			}
		));
		
		JLabel lblMemberRecords = new JLabel("MEMBER RECORDS");
		lblMemberRecords.setForeground(new Color(206, 255, 255));
		lblMemberRecords.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblMemberRecords.setBounds(334, 10, 153, 36);
		contentPane.add(lblMemberRecords);
		
		JButton btnNewButton_5_1 = new JButton("FETCH");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = mid.getText();

		        if(id.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Enter Member ID");
		            return;
		        }

		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");

		            PreparedStatement pstmt = con.prepareStatement("select * from members where member_id=?");
		            pstmt.setString(1, id);

		            ResultSet rs = pstmt.executeQuery();

		            if(rs.next()) {
		                mname.setText(rs.getString("name"));
		                memail.setText(rs.getString("email"));
		                mphone.setText(rs.getString("phone"));

		            }
		            else {
		                JOptionPane.showMessageDialog(null,"Member Not Found");
		            }

		            con.close();
		        }
		        catch(Exception ex) {
		        	System.out.println(ex);
		        	if(ex.getMessage().contains("ORA-01722")) {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
		        }
			}
		});
		btnNewButton_5_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_5_1.setBounds(309, 56, 64, 18);
		contentPane.add(btnNewButton_5_1);

	}
}
