package com.irfan.threads6;

public class Producer extends Thread
{

	Task task;

	public Producer(Task task)
	{
		super();
		this.task = task;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName());

		for(int i =0; i< 10; i++) {
			
			try
			{
				task.produce(i);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
