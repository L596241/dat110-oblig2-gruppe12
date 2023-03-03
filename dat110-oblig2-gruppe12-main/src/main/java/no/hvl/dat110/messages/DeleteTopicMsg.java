package no.hvl.dat110.messages;

// This class implements a message that is sent from a client to delete a topic on the broker.
public class DeleteTopicMsg extends Message {
	
	// The name of the topic to be deleted is stored in this variable.
	private String topic;

	// Constructor that takes the username of the sender and the name of the topic to be deleted.
    public DeleteTopicMsg(String user, String topic) {
    	super(MessageType.DELETETOPIC, user);
    	this.topic = topic;
    }
    
    // Setter method for the topic name.
    public void setTopic(String topic) {
    	this.topic = topic;
    }
    
    // Getter method for the topic name.
    public String getTopic() {
    	return this.topic;
    }
    
    // Returns a string representation of the DeleteTopicMsg object.
    @Override
    public String toString() {
    	return "DeleteTopicMsg [topic=" + topic + "]";
    }
    
}
