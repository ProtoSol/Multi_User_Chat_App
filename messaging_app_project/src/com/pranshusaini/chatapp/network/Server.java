package com.pranshusaini.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.pranshusaini.chatapp.utils.ConfigReader;

// // To Run :- 		cd C:\Users\PS144\eclipse-workspace\messaging_app_project\bin
//						java com.pranshusaini.chatapp.network.Server

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>(); // Contains all the ClientSockets.
	
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and Waiting for Clients to Join");
		handleClientRequest();
	}
	
	// Multiple Client Handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept(); // Handshaking
			// Per Client Per Thread.
			ServerWorker serverWorker = new ServerWorker(clientSocket, this); // Creating a new Worker / thread
			workers.add(serverWorker);
			serverWorker.start();
		}
	}
	
/* For Single Client.
*	public Server() throws IOException {
*		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
*		serverSocket = new ServerSocket(PORT);
*		System.out.println("Server Started and Waiting for Client Connection ...");
*		Socket socket = serverSocket.accept();	// Handshaking Process
*		System.out.println("Client has Connected the Server ...");
*		InputStream inp = socket.getInputStream(); // Read bytes from the Network.
*		byte arr[] = inp.readAllBytes();
*		String str = new String(arr);
*		System.out.println("Message Recieved from the Client: " + str);
*		inp.close();
*		socket.close();
*	}
*/	
	
	public static void main(String[] args) throws IOException {
		//This will give the port number that will be used as a connection point by clients.
		Server server = new Server();
	}
}
