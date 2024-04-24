package com.pranshusaini.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	int counter;
	public UserView(){
		counter = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setResizable(false);
		setTitle("Login");
		
		setLocationRelativeTo(null);
		
//		This is the point location specification for the Screen
//		setLocation(500, 150);
		
//		Creating Components
		
//		LOGIN TEXT
		
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial", Font.BOLD, 40)); // Font
		Container container = this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(100, 70, 200, 60);
		container.add(welcome);
		
//		 COUNT BUTTON TO LEARN EVENTS DELEGATION MODEL
		
		JButton button = new JButton("Count"); // source of event
		button.addActionListener(new ActionListener() {
//			Basic Action result
			@Override
			public void actionPerformed(ActionEvent event) {
				counter++;
				welcome.setText("Count " + counter);
			}
		});
		button.setBounds(100, 300, 200, 50);
		container.add(button);
		
//		We are required to do the layout for the text
		
		
		
		setVisible(true);
	}
	
//	 Main class of the class
	
	public static void main(String[] args) {
		UserView userView = new UserView();
		
	}
}
