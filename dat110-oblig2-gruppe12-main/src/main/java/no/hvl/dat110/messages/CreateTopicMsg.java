package no.hvl.dat110.messages;

// This class implements a message that is sent from a client to create a topic on the broker.
public class CreateTopicMsg extends Message {
	
	// The topic name is stored in this variable.
	private String topic;

	// Constructor that takes the username of the sender and the name of the topic to be created.
	public CreateTopicMsg(String user, String topic) {
    	super(MessageType.CREATETOPIC, user);
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

	// Returns a string representation of the CreateTopicMsg object.
	@Override
	public String toString() {
		return "CreateTopicMsg [topic=" + topic + "]";
	}
	
}
