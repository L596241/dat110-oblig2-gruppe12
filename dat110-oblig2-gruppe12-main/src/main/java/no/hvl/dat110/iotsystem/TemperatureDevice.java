package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// Create a simulated temperature sensor object
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		
		// Create a client object and establish a connection with the broker using the username "sensor".
		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		
		// Publish temperature data to the "temperature" topic.
		for (int i = 1; i <= COUNT; i++) {
			// Get temperature data from the simulated temperature sensor and publish it to the "temperature" topic.
			client.publish("temperature", temperatureSensor.read() + "");
			try {
				Thread.sleep(1000); // Wait for 1 second before publishing the next temperature value.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Disconnect from the broker to stop publishing temperature data.
		client.disconnect();

		System.out.println("TemperatureDevice stopping...");
	}
}
