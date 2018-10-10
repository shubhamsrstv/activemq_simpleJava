package com.example.shubham1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class JMSMessageSender 
{
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "test";

	public String send(String text) throws JMSException
	{
		//Here setting connection to the localhost of Activemq.
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		//Setting-up a session.
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//Selecting the queue.
		Destination destination = session.createQueue(subject);
		//Creating method to send or receive message.
		MessageProducer producer = session.createProducer(destination);
		//Setting-up the message.
		TextMessage message = session.createTextMessage(text);
		//Sending the message to queue.
		producer.send(message);
		connection.close();
		
		return message.getText();
    }
}