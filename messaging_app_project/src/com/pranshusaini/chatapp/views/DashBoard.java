package com.pranshusaini.chatapp.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class DashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		DashBoard frame = new DashBoard();
//		frame.setVisible(true);
//	}

	/**
	 * Create the frame.
	 */
	public DashBoard(String message) {
		
		// This feature seems useless
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setTitle(message);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DashBoard.class.getResource("/Images/speech-bubble.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 607);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu chatMenu = new JMenu("Chat");
		menuBar.add(chatMenu);
		
		JMenuItem startChat = new JMenuItem("Start Chat");
		startChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ClientChatScreen();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				setVisible(false);
			}
		});
		chatMenu.add(startChat);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel BackgroundImage = new JLabel("");
		BackgroundImage.setHorizontalAlignment(SwingConstants.CENTER);
		BackgroundImage.setIcon(new ImageIcon(DashBoard.class.getResource("/Images/Screenshot 2024-04-19 224741.png")));
		contentPane.add(BackgroundImage, BorderLayout.EAST);
	}

}
