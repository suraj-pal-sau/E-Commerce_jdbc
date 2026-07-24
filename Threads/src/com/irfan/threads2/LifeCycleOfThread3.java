package com.irfan.threads2;

// if you call run() method directly --> multithreading not achieved --> thread created but not started
// only 'main' thread is executing the task --> single threaded

class Printing extends Thread
{
	public void run()
	{	
		System.out.println("PrintNumbers.run()....running");
		for (int i = 0; i < 10; i++) {
			System.out.println("Number is " + i + " printed by " + Thread.currentThread().getName());
			
			if(i == 5) {	
				System.out.println("sending " + Thread.currentThread().getName() + " to sleeping/waiting state");
				try {
					Thread.currentThread().sleep(3000);	//sleep for 3 sec --> WAITING
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
				System.out.println(" sleep time is over.. starting executing again.." + Thread.currentThread().getName());
			}
		}
	}	
}

public class LifeCycleOfThread3
{

	public static void main(String[] args)
	{
		Printing print = new Printing();	//NEW	
		print.run();		//thread not started
	}

}
