package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// Data structure for managing subscriptions
	// Maps from a topic to a set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// Data structure for managing currently connected clients
	// Maps from a user to the corresponding client session object
	protected ConcurrentHashMap<String, ClientSession> clients;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

	// Retrieve all client session objects in the storage
	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	// Retrieve all topics in the storage
	public Set<String> getTopics() {
		return subscriptions.keySet();
	}

	// Retrieve the session object for a given user
	// The session object can be used to send a message to the user
	public ClientSession getSession(String user) {
		return clients.get(user);
	}

	// Retrieve all subscribers for a given topic
	public Set<String> getSubscribers(String topic) {
		return subscriptions.get(topic);
	}

	// Add a client session to the storage
	public void addClientSession(String user, Connection connection) {
		ClientSession clientSession = new ClientSession(user, connection);
		clients.put(user, clientSession);
	}

	// Remove a client session from the storage
	public void removeClientSession(String user) {
		ClientSession clientSession = getSession(user);
		clientSession.disconnect();
		clients.remove(user);
	}

	// Create a new topic in the storage
	public void createTopic(String topic) {
		subscriptions.put(topic, ConcurrentHashMap.newKeySet());
	}

	// Delete a topic from the storage
	public void deleteTopic(String topic) {
		subscriptions.remove(topic);
	}

	// Add a user as a subscriber to a given topic
	public void addSubscriber(String user, String topic) {
		if (getTopics().contains(topic)) {
			Set<String> subscribers = getSubscribers(topic);
			subscribers.add(user);
			subscriptions.put(topic, subscribers);
		} else {
			System.err.println("Topic does not exist");
		}
	}

	// Remove a user as a subscriber from a given topic
	public void removeSubscriber(String user, String topic) {
		if (getTopics().contains(topic)) {
			Set<String> subscribers = getSubscribers(topic);
			subscribers.remove(user);
		} else {
			System.err.println("Topic does not exist");
		}
	}
}
