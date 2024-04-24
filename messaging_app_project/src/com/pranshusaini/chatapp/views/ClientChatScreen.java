package com.pranshusaini.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pranshusaini.chatapp.network.Client;
import com.pranshusaini.chatapp.utils.UserInfo;

public class ClientChatScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientChatScreen frame = new ClientChatScreen();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendIt() {
		String message = textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME + " - " + message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea = new JTextArea();
		client = new Client(textArea);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClientChatScreen.class.getResource("/Images/speech-bubble.png")));
		setTitle("Rustic Msg App");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 582);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 972, 438);
		contentPane.add(scrollPane);
		setVisible(true);
		
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(10, 11, 972, 438);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 460, 824, 72);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendit = new JButton("Send Message");
		sendit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendit.setBounds(844, 485, 138, 23);
		contentPane.add(sendit);
	}
}
