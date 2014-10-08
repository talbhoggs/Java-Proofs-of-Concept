package com.daniel;

import javax.ejb.*;
import javax.annotation.*;
import javax.jms.*;

@Stateless(name = "sendTwitterMessage")
public class TwitterSendMessage {

	public TwitterSendMessage() {

	}

	@Resource(mappedName = "TwitterMessageTopic")
	private Topic twitterMsgTopicQueue; // javax.jms.Topic

	@Resource(mappedName = "TwitterMessageTopicConnectionFactory")
	private TopicConnectionFactory twitterTCF;

	public void sendTwitterMessageToJMSListener(String by, String content) {

		try {
			Connection connection = twitterTCF.createConnection();
			System.out.println("CREATION OF CONNECTION SEEMS TO HAVE WORKED!");
			connection.start();
			System.out.println("CONNECTION STARTED.");
			Session sessionWithTwitterTopic = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			System.out.println("CONNECTION SESSION CREATED.");
			MessageProducer messageDelivery = sessionWithTwitterTopic
					.createProducer(twitterMsgTopicQueue);
			MapMessage theTwit = sessionWithTwitterTopic.createMapMessage();

			theTwit.setStringProperty("by", by);
			theTwit.setStringProperty("content", content);

			messageDelivery.send(theTwit);

		} catch (JMSException e) {
			System.out.println(e);
			System.out.println("CREATION OF CONNECTION SADLY FAILED... :(");
		}

	}

}
