package com.example.shubham1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class JMSMessageReceiver 
{
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String subject = "test";

	public String recieve() throws JMSException
	{
		//Setting connection.
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		//Setting session.
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//Selecting queue.
		Destination destination = session.createQueue(subject);
		//Setting method to receive message.
		MessageConsumer consumer = session.createConsumer(destination);
		//Received message.
		Message message = consumer.receive();
		TextMessage textMessage = null;
		if (message instanceof TextMessage) 
		{
			//Converting to text message.
			textMessage = (TextMessage) message;
		}
	
		connection.close();
		
		return textMessage.getText();
	}
}
