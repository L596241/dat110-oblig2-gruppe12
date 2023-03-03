package no.hvl.dat110.broker;

import java.util.Set;
import java.util.Collection;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;

public class Dispatcher extends Stopable {

	private Storage storage;

	public Dispatcher(Storage storage) {
		super("Dispatcher");
		this.storage = storage;

	}

	@Override
	public void doProcess() {

		// get all client sessions from storage
		Collection<ClientSession> clients = storage.getSessions();

		// iterate through all client sessions
		Logger.lg(".");
		for (ClientSession client : clients) {

			Message msg = null;

			// check if the client session has data to receive
			if (client.hasData()) {
				msg = client.receive();
			}

			// if a message was received
			if (msg != null) {
				dispatch(client, msg);
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void dispatch(ClientSession client, Message msg) {

		MessageType type = msg.getType();

		// invoke the appropriate handler method based on message type
		switch (type) {

		case DISCONNECT:
			onDisconnect((DisconnectMsg) msg);
			break;

		case CREATETOPIC:
			onCreateTopic((CreateTopicMsg) msg);
			break;

		case DELETETOPIC:
			onDeleteTopic((DeleteTopicMsg) msg);
			break;

		case SUBSCRIBE:
			onSubscribe((SubscribeMsg) msg);
			break;

		case UNSUBSCRIBE:
			onUnsubscribe((UnsubscribeMsg) msg);
			break;

		case PUBLISH:
			onPublish((PublishMsg) msg);
			break;

		default:
			// log a message indicating that an unhandled message type was encountered
			Logger.log("broker dispatch - unhandled message type");
			break;

		}
	}

	// called from Broker after having established the underlying connection
	public void onConnect(ConnectMsg msg, Connection connection) {

		String user = msg.getUser();

		// log message indicating that a client has connected
		Logger.log("onConnect:" + msg.toString());

		// add client session to the storage
		storage.addClientSession(user, connection);

	}

	// called by dispatch upon receiving a disconnect message
	public void onDisconnect(DisconnectMsg msg) {

		String user = msg.getUser();

		// log message indicating that a client has disconnected
		Logger.log("onDisconnect:" + msg.toString());

		// remove client session from the storage
		storage.removeClientSession(user);

	}

	// create the topic in the broker storage
	public void onCreateTopic(CreateTopicMsg msg) {

		// log message indicating that a topic has been created
		Logger.log("onCreateTopic:" + msg.toString());

		// create the topic in the storage
		storage.createTopic(msg.getTopic());
	}

	// delete the topic from the broker storage
	public void onDeleteTopic(DeleteTopicMsg msg) {

		// log message indicating that a topic has been deleted
		Logger.log("onDeleteTopic:" + msg.toString());

		// delete the topic from the storage
		storage.deleteTopic(msg.getTopic());
	}
	// This method adds a subscriber to a specific topic by storing the user id in
	// the subscribers list of the topic.
	// It is called when a SUBSCRIBE message is received by the broker.

	public void onSubscribe(SubscribeMsg msg) {
		// Log the received message
		Logger.log("onSubscribe:" + msg.toString());

		// Add the subscriber to the topic
		storage.addSubscriber(msg.getUser(), msg.getTopic());
		// This method removes a subscriber from a specific topic by removing the user
		// id from the subscribers list of the topic.
		// It is called when an UNSUBSCRIBE message is received by the broker.

	}

	public void onUnsubscribe(UnsubscribeMsg msg) {
		// Log the received message
		Logger.log("onUnsubscribe:" + msg.toString());

		// Remove the subscriber from the topic
		storage.removeSubscriber(msg.getUser(), msg.getTopic());
	}

	// This method publishes a message to all the clients that are subscribed to a
	// specific topic.
	// It is called when a PUBLISH message is received by the broker.
	// The method uses the session object of each subscriber to send the message.

	public void onPublish(PublishMsg msg) {
		// Log the received message
		Logger.log("onPublish:" + msg.toString());

		// Get the subscribers for the topic and send the message to them
		storage.getSubscribers(msg.getTopic()).forEach(x -> storage.getSession(x).send(msg));

	}
}
