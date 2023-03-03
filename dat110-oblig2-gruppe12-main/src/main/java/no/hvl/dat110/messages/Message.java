package no.hvl.dat110.messages;

// This is the base class for messages exchanged between the broker and clients.
public abstract class Message {

	// The type of the message is stored in this variable.
	private MessageType type;
	// The username of the client who sent the message is stored in this variable.
	private String user;
	
	public Message() {
		
	}
	
	// Constructor that takes the message type and the username of the sender.
	public Message(MessageType type, String user) {
		this.type = type;
		this.user = user;
	}

	// Getter method for the message type.
	public MessageType getType() {
		return this.type;
	}

	// Getter method for the username of the sender.
	public String getUser() {
		return user;
	}

	// Returns a string representation of the Message object.
	@Override
	public String toString() {
		return "Message [type=" + type + ", user=" + user + "]";
	};
	
}
