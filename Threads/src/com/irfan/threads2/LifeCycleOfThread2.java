package com.irfan.threads2;

class PrintNumbers extends Thread {

	public void run()
	{	//RUNNING
		System.out.println("PrintNumbers.run()....running");
		for (int i = 0; i < 10; i++) {
			System.out.println("Number is " + i);
			
			if(i == 5) {	
				System.out.println("sending " + Thread.currentThread().getName() + " to sleeping/waiting state");
				try {
					Thread.currentThread().sleep(3000);	//sleep for 3 sec --> WAITING
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
				System.out.println(" sleep time is over.. starting executing again..");
			}
		}
	}	//TERMINATED
}

public class LifeCycleOfThread2 {

	public static void main(String[] args) {
		PrintNumbers print = new PrintNumbers();	//NEW
		print.start();	//RUNNABLE
	}

}
