package amal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;

public class Issue_Table extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField retdate;
	private JTextField issdate;
	private JTextField Issid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Issue_Table frame = new Issue_Table();
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
	public Issue_Table() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIssueid = new JLabel("Issue_ID");
		lblIssueid.setBounds(101, 52, 64, 12);
		contentPane.add(lblIssueid);
		
		JLabel lblNewLabel_1 = new JLabel("Book_ID");
		lblNewLabel_1.setBounds(101, 90, 64, 12);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Member_ID");
		lblNewLabel_2.setBounds(101, 128, 78, 12);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Issue Date");
		lblNewLabel_3.setBounds(101, 160, 67, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Return Date");
		lblNewLabel_4.setBounds(101, 198, 73, 18);
		contentPane.add(lblNewLabel_4);
		
		retdate = new JTextField();
		retdate.setColumns(10);
		retdate.setBounds(242, 198, 131, 18);
		contentPane.add(retdate);
		
		issdate = new JTextField();
		issdate.setColumns(10);
		issdate.setBounds(242, 160, 131, 18);
		contentPane.add(issdate);
		
		Issid = new JTextField();
		Issid.setColumns(10);
		Issid.setBounds(242, 49, 64, 18);
		contentPane.add(Issid);
		
		JComboBox<String> bookid = new JComboBox<String>();
		bookid.setBounds(242, 86, 131, 20);
		contentPane.add(bookid);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
            PreparedStatement pstmt = con.prepareStatement("select book_id from books");

                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    bookid.addItem(rs.getString("book_id"));
                }

                con.close();
            
		}
		catch(Exception ex) {
			System.out.println(ex);		
		}
		
		JComboBox<String> memid = new JComboBox<String>();
		memid.setBounds(242, 124, 131, 20);
		contentPane.add(memid);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
            PreparedStatement pstmt = con.prepareStatement("select member_id from members");

                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                	memid.addItem(rs.getString("member_id"));
                }

                con.close();
            
		}
		catch(Exception ex) {
			System.out.println(ex);		
		}
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iid = Issid.getText();
				String bid = (String) bookid.getSelectedItem();
				String mid = (String) memid.getSelectedItem();
				String idt = issdate.getText();
				String rdt = retdate.getText();
				if(iid.isEmpty() || bid.isEmpty() || mid.isEmpty() || idt.isEmpty() || rdt.isEmpty()){
					JOptionPane.showMessageDialog(null,"All Field Must Be Filled!");
					return;
				}
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
					PreparedStatement pstmt = con.prepareStatement("insert into issue values(?,?,?,?,?)");
					pstmt.setString(1,iid);
					pstmt.setString(2,bid);
					pstmt.setString(3,mid);
					pstmt.setDate(4, java.sql.Date.valueOf(idt));
					pstmt.setDate(5, java.sql.Date.valueOf(rdt));
			
					int rowadd = pstmt.executeUpdate();
					
					if (rowadd>0) {
                    	JOptionPane.showMessageDialog(null,"Issue Added Succesfully"); 
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");   
                    }
                    con.close();
                }
                catch (Exception ex) {
                	if(ex.getMessage().contains("ORA-00001")) {
                        JOptionPane.showMessageDialog(null,"Issue ID already exists");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
                }
			}
			
		});
		btnNewButton.setBounds(101, 234, 84, 20);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iid = Issid.getText();
				String bid = (String) bookid.getSelectedItem();
				String mid = (String) memid.getSelectedItem();
				String idt = issdate.getText();
				String rdt = retdate.getText();
				if(iid.isEmpty() || bid.isEmpty() || mid.isEmpty() || idt.isEmpty() || rdt.isEmpty()){
					JOptionPane.showMessageDialog(null,"All Field Must Be Filled!");
					return;
				}
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
					PreparedStatement pstmt = con.prepareStatement("update issue set book_id=?, member_id=?, issue_date=?,return_date=? where issue_id=?");
					pstmt.setString(5,iid);
					pstmt.setString(1,bid);
					pstmt.setString(2,mid);
					pstmt.setDate(3, java.sql.Date.valueOf(idt));
					pstmt.setDate(4, java.sql.Date.valueOf(rdt));
					
					int rowupdate = pstmt.executeUpdate();
					
					if (rowupdate>0) {
                    	JOptionPane.showMessageDialog(null,"Issue Updated Succesfully"); 
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");   
                    }
                    con.close();
                }
                catch (Exception ex) {
                	if(ex.getMessage().contains("ORA-00001")) {
                        JOptionPane.showMessageDialog(null,"Issue ID already exists");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                    }
                }
			}
		});
		btnNewButton_1.setBounds(195, 234, 84, 20);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iid = Issid.getText();

		        if(iid.isEmpty()) {
		            JOptionPane.showMessageDialog(null,"Issue_ID Must Be Filled!");
		            return;
		        }

		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");

		            PreparedStatement check = con.prepareStatement("select Issue_id from Issue where Issue_id=?");
		            check.setString(1,iid);

		            ResultSet rs = check.executeQuery();

		            if(!rs.next()) {
		                JOptionPane.showMessageDialog(null,"Issue_ID " + iid + " Does Not Exist!");
		                con.close();
		                return;
		            }

		            int choice = JOptionPane.showConfirmDialog(null,"Do you want to delete Issue_ID = " + iid + "?","Confirm Delete",JOptionPane.YES_NO_OPTION);
		            
		            if(choice == JOptionPane.YES_OPTION) {
		                PreparedStatement pstmt = con.prepareStatement("delete from Issue where Issue_ID=?");
		                pstmt.setString(1,iid);

		                int rowdelete = pstmt.executeUpdate();

		                if(rowdelete > 0) {
		                    JOptionPane.showMessageDialog( null, "Issue Deleted Successfully");}
		                else {
		                    JOptionPane.showMessageDialog( null, "Issue Deletion Failed");
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
		btnNewButton_2.setBounds(289, 234, 84, 20);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("VIEW");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery("select * from issue order by issue_id");
                    
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    con.close();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
			}
		});
		btnNewButton_4.setBounds(580, 234, 84, 20);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("BACK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home frame = new home();
		        frame.setVisible(true);

		        dispose();
			}
		});
		btnNewButton_3.setBounds(10, 263, 69, 20);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(439, 51, 367, 165);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Issue_ID", "Book_ID", "Member_ID", "Issue_Date", "Return_Date"
			}
		));
		
		JLabel lblIssueRecords = new JLabel("ISSUE RECORDS");
		lblIssueRecords.setForeground(new Color(206, 255, 255));
		lblIssueRecords.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIssueRecords.setBounds(344, 10, 153, 31);
		contentPane.add(lblIssueRecords);
		
		JButton btnNewButton_5_1 = new JButton("FETCH");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = Issid.getText();

		        if(id.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Enter Issue ID");
		            return;
		        }

		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
		            
		            PreparedStatement pstmt = con.prepareStatement("select * from issue where issue_id=?");
		            pstmt.setString(1, id);

		            ResultSet rs = pstmt.executeQuery();
		            
		            if(rs.next()) {  
		                bookid.setSelectedItem(rs.getString("book_id"));
		                memid.setSelectedItem(rs.getString("member_id"));
		                issdate.setText(rs.getDate("issue_date").toString());
		                retdate.setText(rs.getDate("return_date").toString());

		            }
		            else {
		                JOptionPane.showMessageDialog(null,"Issue Not Found");
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
		btnNewButton_5_1.setBounds(309, 49, 64, 18);
		contentPane.add(btnNewButton_5_1);
		
		JLabel lblNewLabel = new JLabel("(yyyy-mm-dd)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel.setBounds(173, 164, 81, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("(yyyy-mm-dd)");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_5.setBounds(173, 201, 81, 12);
		contentPane.add(lblNewLabel_5);
		
		
		
		

	}
}
