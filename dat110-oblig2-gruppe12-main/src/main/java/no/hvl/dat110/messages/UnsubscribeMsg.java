package no.hvl.dat110.messages;

// This class implements a message that is sent from a client to unsubscribe from a topic.
public class UnsubscribeMsg extends Message {

	// The name of the topic from which the client unsubscribes is stored in this variable.
	private String topic;

	// Constructor that takes the username of the sender and the name of the topic from which the client unsubscribes.
    public UnsubscribeMsg(String user, String topic) {
    	super(MessageType.UNSUBSCRIBE, user);
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

	// Returns a string representation of the UnsubscribeMsg object.
	@Override
	public String toString() {
		return "UnsubscribeMsg [topic=" + topic + "]";
	}

}
