package amal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1_3 = new JButton("BOOK");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book_Table frame = new Book_Table();
				frame.setVisible(true);
				
				dispose();
			}
		});
		btnNewButton_1_3.setBounds(167, 57, 97, 37);
		contentPane.add(btnNewButton_1_3);
		
		JButton btnNewButton_1 = new JButton("MEMBER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member_Table frame = new Member_Table();
                frame.setVisible(true);

                dispose();
			}
		});
		btnNewButton_1.setBounds(167, 119, 97, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("ISSUE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Issue_Table frame = new Issue_Table();
				frame.setVisible(true);
				
				dispose();
				
			}
		});
		btnNewButton_1_1.setBounds(167, 179, 97, 37);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("EXIT");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login frame = new login();
		        frame.setVisible(true);

		        dispose();
			}
		});
		btnNewButton_1_2.setBounds(10, 232, 69, 21);
		contentPane.add(btnNewButton_1_2);
		
		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setForeground(new Color(132, 251, 244));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(108, 10, 239, 37);
		contentPane.add(lblNewLabel);
		
		

	}
}
