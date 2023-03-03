package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("DisplayDevice starting...");
		
		// Create a client object and establish a connection with the broker by using the username "display".
		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();

		// Create a new topic on the broker named "temperature".
		client.createTopic("temperature");
		
		// Subscribe to the "temperature" topic to receive temperature data from the broker.
		client.subscribe("temperature");
		
		// Receive temperature data on the subscribed topic.
		for (int i = 1; i <= COUNT; i++) {
			client.receive();
		}
		
		// Unsubscribe from the "temperature" topic.
		client.unsubscribe("temperature");
		
		// Disconnect from the broker to stop receiving temperature data.
		client.disconnect();

		System.out.println("DisplayDevice stopping...");
	}
}
