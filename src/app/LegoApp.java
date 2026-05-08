package app;

import threads.*;

public class LegoApp {

	// this is the main program that starts all the robot threads, it creates robot systems and run them simultaneously
	public static void main(String[] args) {
		RunLego runLego=new RunLego();
		ReadData readData=new ReadData();
		SendData sendData=new SendData();

		Thread sendDataThread = new Thread(sendData);
		sendDataThread.start(); // Start sending robot data to the server

		System.out.println("Run in Threads");

		Thread runLegoThread=new Thread(runLego); // create thread for robot movement
		Thread readDataThread=new Thread(readData); // create thread for reading data from REST server
		runLegoThread.start(); // start robot movement thread
		readDataThread.start(); // start thread for reading data from REST server
	}
}
