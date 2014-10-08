package com.daniel;

import javax.jms.*;
import javax.ejb.*;

//This bean class listens to the twitter message topic
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationName", propertyValue = "TwitterMessageTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") }, mappedName = "TwitterMessageTopic")
public class TwitterQueue implements javax.jms.MessageListener {

	public void onMessage(Message m) {

		try {
			if (m instanceof MapMessage) {

				m = (MapMessage) m;

				// The names of the properties are arbitrary as I set them my
				// self when I send the message
				String by = m.getStringProperty("by");
				String content = m.getStringProperty("content");
				System.out.println("I GOT A TWIT!!! HERE IT IS: by - " + by + " content - " + content);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
