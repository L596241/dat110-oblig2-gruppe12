package no.hvl.dat110.messages;

// This class implements a message that is sent from a client to publish a message on a topic.
public class PublishMsg extends Message {
	
	// The name of the topic on which the message is published is stored in this variable.
	private String topic;
	// The message to be published is stored in this variable.
	private String message;
	
	// Constructor that takes the username of the sender, the name of the topic and the message to be published.
	public PublishMsg(String user, String topic, String message) {
		super(MessageType.PUBLISH, user);
		this.topic = topic;
		this.message = message;
	}

	// Getter method for the topic name.
	public String getTopic() {
		return topic;
	}

	// Setter method for the topic name.
	public void setTopic(String topic) {
		this.topic = topic;
	}

	// Getter method for the message.
	public String getMessage() {
		return message;
	}

	// Setter method for the message.
	public void setMessage(String message) {
		this.message = message;
	}

	// Returns a string representation of the PublishMsg object.
	@Override
	public String toString() {
		return "PublishMsg [topic=" + topic + ", message=" + message + "]";
	}

}
