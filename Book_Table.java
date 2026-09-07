package amal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;


public class Book_Table extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField bid;
	private JTextField title;
	private JTextField author;
	private JTextField quantity;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_Table frame = new Book_Table();
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
	public Book_Table() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bid = new JTextField();
		bid.setBounds(242, 49, 64, 18);
		contentPane.add(bid);
		bid.setColumns(10);
		
		title = new JTextField();
		title.setBounds(242, 87, 131, 18);
		contentPane.add(title);
		title.setColumns(10);
		
		author = new JTextField();
		author.setBounds(242, 125, 131, 18);
		contentPane.add(author);
		author.setColumns(10);
		
		quantity = new JTextField();
		quantity.setBounds(242, 198, 131, 18);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		JComboBox<String> category = new JComboBox<String>();
		category.setModel(new DefaultComboBoxModel<String>(new String[] {"Fantasy", "Crime", "Horror", "Romance", "Mystery", "Thriller", "History", "Fiction"}));
		category.setBounds(242, 159, 131, 20);
		contentPane.add(category);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = bid.getText();
				String btitle = title.getText();
				String bauthor = author.getText();
				String bcategory = (String) category.getItemAt(category.getSelectedIndex());
				String bquantity = quantity.getText();
				if(id.isEmpty() || btitle.isEmpty() || bauthor.isEmpty() || bcategory.isEmpty() || bquantity.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All Field Must Be Filled!");
					return;
				}
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    PreparedStatement pstmt = con.prepareStatement("insert into books values(?,?,?,?,?)");
                    pstmt.setString(1, id);
                    pstmt.setString(2, btitle);
                    pstmt.setString(3, bauthor);
                    pstmt.setString(4, bcategory);
                    pstmt.setString(5, bquantity);

                    int rowadd = pstmt.executeUpdate();

                    if (rowadd>0) {
                    	JOptionPane.showMessageDialog(null,"Book Added"); 
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");   
                    }
                    con.close();
                }
                catch (Exception ex) {
                	if(ex.getMessage().contains("ORA-00001")) {
                        JOptionPane.showMessageDialog(null,"Book ID already exists");
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
				String id = bid.getText();
				String btitle = title.getText();
				String bauthor = author.getText();
				String bcategory = (String) category.getItemAt(category.getSelectedIndex());
				String bquantity = quantity.getText();
				if(id.isEmpty() || btitle.isEmpty() || bauthor.isEmpty() || bcategory.isEmpty() || bquantity.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All Field Must Be Filled!");
					return;
				}
				try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    PreparedStatement pstmt = con.prepareStatement("update books set title=?, author=?, category=?, quantity=? where book_id=?");
                    pstmt.setString(5, id);
                    pstmt.setString(1, btitle);
                    pstmt.setString(2, bauthor);
                    pstmt.setString(3, bcategory);
                    pstmt.setString(4, bquantity);

                    int rowupdate = pstmt.executeUpdate();

                    if (rowupdate>0) {
                    	JOptionPane.showMessageDialog(null,"Book Updated");  
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");  
                    }
                    con.close();
                }
                catch (Exception ex) {
                	if(ex.getMessage().contains("ORA-01722")) {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
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
				String id = bid.getText();

		        if(id.isEmpty()) {
		            JOptionPane.showMessageDialog(null,"Book_ID Must Be Filled!");
		            return;
		        }

		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");

		            PreparedStatement check = con.prepareStatement("select book_id from books where book_id=?");
		            check.setString(1,id);

		            ResultSet rs = check.executeQuery();

		            if(!rs.next()) {
		                JOptionPane.showMessageDialog(null,"Book_ID " + id + " Does Not Exist!");
		                con.close();
		                return;
		            }

		            int choice = JOptionPane.showConfirmDialog(null,"Do you want to delete Book_ID = " + id + "?","Confirm Delete",JOptionPane.YES_NO_OPTION);
		            
		            if(choice == JOptionPane.YES_OPTION) {
		                PreparedStatement pstmt = con.prepareStatement("delete from books where book_id=?");
		                pstmt.setString(1,id);

		                int rowdelete = pstmt.executeUpdate();

		                if(rowdelete > 0) {
		                    JOptionPane.showMessageDialog( null, "Book Deleted");}
		                else {
		                    JOptionPane.showMessageDialog( null, "Book Deletion Failed");
		                }
		            }
		            con.close();
		        }
		        catch(Exception ex) {
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
		scrollPane.setBounds(450, 49, 356, 167);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book_ID", "Title", "Author", "Category", "Quantity"
			}
		));
		
		JButton btnNewButton_4 = new JButton("VIEW");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from books order by book_id");
                    
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    con.close();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
			}
		});
		btnNewButton_4.setBounds(586, 234, 84, 20);
		contentPane.add(btnNewButton_4);
		
		JLabel lblBookid = new JLabel("Book_ID");
		lblBookid.setBounds(125, 52, 64, 12);
		contentPane.add(lblBookid);
		
		JLabel lblNewLabel_1_1 = new JLabel("Title");
		lblNewLabel_1_1.setBounds(125, 90, 64, 12);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Author");
		lblNewLabel_2_1.setBounds(125, 128, 78, 12);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Category");
		lblNewLabel_3_1.setBounds(125, 159, 64, 20);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Quantity");
		lblNewLabel_4_1.setBounds(125, 198, 78, 18);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel = new JLabel("BOOK RECORDS");
		lblNewLabel.setForeground(new Color(206, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(351, 10, 153, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_5 = new JButton("FETCH");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = bid.getText();

		        if(id.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Enter Book ID");
		            return;
		        }

		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","USERNAME","PASSWORD");

		            PreparedStatement pstmt = con.prepareStatement("select * from books where book_id=?");
		            pstmt.setString(1, id);

		            ResultSet rs = pstmt.executeQuery();

		            if(rs.next()) {
		                title.setText(rs.getString("title"));
		                author.setText(rs.getString("author"));
		                category.setSelectedItem(rs.getString("category"));
		                quantity.setText(rs.getString("quantity"));

		            }
		            else {
		                JOptionPane.showMessageDialog(null,"Book Not Found");
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
		btnNewButton_5.setBounds(311, 49, 64, 18);
		contentPane.add(btnNewButton_5);

	}
}
