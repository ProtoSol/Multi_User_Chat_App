package com.pranshusaini.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/*
*	Thread = Worker
*	Worker needs a job to work similarly Thread needs Runnable to Perform
*	We create a Job / Runnable then we write the logic inside the Run function.
*	we Assign the job to a Thread / Worker.
*
*	We use Runnable when the code has extended class Thread and we have to implement it multiple times.
*	The extends Thread method is used when only one time it is required.
*/

// We will create one thread for one client.

public class ServerWorker extends Thread{
	
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	
	public ServerWorker(Socket clientSocket, Server server) throws IOException{
		this.server = server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream(); // Client Data read
		out = clientSocket.getOutputStream(); // Client Data write
		System.out.println("New Client Connected ...");
	}
	
	@Override
	public void run() {
		// Read the Data from the Client and Broadcast the Data to All.
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
			
				line = br.readLine(); // needs \n
//				System.out.println("Server Read Line " + line);
				if(line.equalsIgnoreCase("quit")) {
					break; // when "quit" is entered the client will end the thread and ends the Chat.
				}
//				out.write(line.getBytes()); // Client Send
				// BroadCast to all other Client.
				for(ServerWorker serverWorker : server.workers) {
					line = line + "\n"; // one more \n added
					serverWorker.out.write(line.getBytes());
				}
			} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				if(clientSocket != null) {
					clientSocket.close();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

