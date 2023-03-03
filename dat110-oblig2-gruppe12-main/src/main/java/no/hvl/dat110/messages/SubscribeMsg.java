package no.hvl.dat110.messages;

// This class implements a message that is sent from a client to subscribe to a topic.
public class SubscribeMsg extends Message {

	// The name of the topic to which the client subscribes is stored in this variable.
	private String topic;

	// Constructor that takes the username of the sender and the name of the topic to which the client subscribes.
    public SubscribeMsg(String user, String topic) {
    	super(MessageType.SUBSCRIBE, user);
    	this.topic = topic;
    }

	// Getter method for the topic name.
	public String getTopic() {
		return topic;
	}

	// Setter method for the topic name.
	public void setTopic(String topic) {
		this.topic = topic;
	}

	// Returns a string representation of the SubscribeMsg object.
	@Override
	public String toString() {
		return "SubscribeMsg [topic=" + topic + "]";
	}
		
}
