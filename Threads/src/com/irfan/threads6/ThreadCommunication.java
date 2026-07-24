package com.irfan.threads6;

public class ThreadCommunication
{

	public static void main(String[] args)
	{
		Task task = new Task();	//shared object

		Producer p = new Producer(task);
		p.setName("Producer");
		Consumer c = new Consumer(task);
		c.setName("Consumer");
		
		p.start();
		c.start();
	}

}
