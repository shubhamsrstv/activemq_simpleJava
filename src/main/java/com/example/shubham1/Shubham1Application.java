package com.example.shubham1;

import java.util.Scanner;

import javax.jms.JMSException;

public class Shubham1Application 
{	
	public static void main(String[] args) throws JMSException 
	{
		System.out.println("Welcome");
		System.out.println("Enter your message");
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		sc.close();
		
		JMSMessageSender sender = new JMSMessageSender();
		System.out.println("Message send to queue is : "+sender.send(text));
		
		JMSMessageReceiver receiver = new JMSMessageReceiver();
		System.out.println("Message recieved from the queue is : "+receiver.recieve());
	}
}
