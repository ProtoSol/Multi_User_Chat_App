package com.pranshusaini.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pranshusaini.chatapp.dao.UserDAO;
import com.pranshusaini.chatapp.dto.UserDTO;
import com.pranshusaini.chatapp.utils.UserInfo;

// To Run :- 		cd C:\Users\PS144\eclipse-workspace\messaging_app_project\bin
//					java -cp "C:\Users\PS144\eclipse-workspace\messaging_app_project\mysql-connector-j-8.3.0.jar;." com.pranshusaini.chatapp.views.UserScreen

public class UserScreen extends JFrame{
	private JTextField UserIDField;
	private JPasswordField PasswordField;

	public static void main(String[] args) {
		UserScreen window = new UserScreen();
	}
	
	UserDAO userDAO = new UserDAO();
	
//	Login Logic Function
	private void doLogin() {
		String userid = UserIDField.getText();
		char[] password = PasswordField.getPassword();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			if(userDAO.isLogin(userDTO)) {
				JOptionPane.showMessageDialog(this, "Welcome " + userid);
				UserInfo.USER_NAME = userid;
//				Closing the previous window and Erasing from the memory.
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard("Welcome " + userid);
				dashBoard.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid User ID or Passsword, Try Again!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	Registration logic function
	private void register(){
		String userid = UserIDField.getText();
		char[] password = PasswordField.getPassword();
//		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			int result = userDAO.add(userDTO);
			if (result > 0) {
//				We do want to show this in the panel itself
//				System.out.println("Record Added ...");
				
				JOptionPane.showMessageDialog(this, "Registered Sucessfully");
			} else {
//				Same with this 
//				System.out.println("Record Not Added ...");
				JOptionPane.showMessageDialog(this, "Not Registered");
			}
		} catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DataBase Issue ...");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Some generic exception raised.");
			ex.printStackTrace();
		}
//		System.out.println("User: " + userid + " Password: " + password); // will print ClassName@HashCode(Hexa) More Secure.
	}

	/**
	 * We will be using type 4 Pure Java Native drivers to Connect with DB server.
	 * JDBC API plays an important role.
	 */
	public UserScreen() {
		
		getContentPane().setBackground(new Color(128, 128, 128));
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Retro Msg Login");
		setBounds(100, 100, 610, 434);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel LoginLabel = new JLabel("LOGIN");
		LoginLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		LoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LoginLabel.setBounds(213, 41, 148, 58);
		getContentPane().add(LoginLabel);
		
		UserIDField = new JTextField();
		UserIDField.setBounds(199, 168, 344, 34);
		getContentPane().add(UserIDField);
		UserIDField.setColumns(10);
		
		JLabel UserIDLabel = new JLabel("UserID :");
		UserIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		UserIDLabel.setBounds(31, 164, 148, 34);
		getContentPane().add(UserIDLabel);
		
		JLabel PasswordLabel = new JLabel("Password :");
		PasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		PasswordLabel.setBounds(41, 209, 138, 45);
		getContentPane().add(PasswordLabel);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		LoginButton.setBounds(199, 284, 89, 23);
		getContentPane().add(LoginButton);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		RegisterButton.setBounds(298, 284, 89, 23);
		getContentPane().add(RegisterButton);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(199, 213, 344, 36);
		getContentPane().add(PasswordField);
		setVisible(true);

	}
}
