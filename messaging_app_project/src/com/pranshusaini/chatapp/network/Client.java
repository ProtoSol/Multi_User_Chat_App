package com.pranshusaini.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.pranshusaini.chatapp.utils.ConfigReader;

public class Client {
	
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		readMessage();
//		System.out.println("Client Connected ...");
//		System.out.println("Enter the Message to be sent to the Server ...");
//		Scanner sc = new Scanner(System.in);
//		String message = sc.nextLine();
//		OutputStream out = socket.getOutputStream(); // Write Bytes on Network.
//		out.write(message.getBytes());
//		System.out.println("Message sent to the Server");
//		sc.close();
//		out.close();
//		socket.close();
	}
	
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}
	
	public void readMessage() {
		worker = new ClientWorker(in, textArea); // Calling a Read Thread
		worker.start();
	}
	
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		Client client = new Client();
//	}
}
