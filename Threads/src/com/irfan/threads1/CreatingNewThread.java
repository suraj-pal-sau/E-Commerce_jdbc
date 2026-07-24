package com.irfan.threads1;

/// in multithreading, execution order is not guaranteed
/// start() method --> register the new thread with thread scheduler 
/// and thread scheduler will talk to CPU that i have one thread, can you execute it? when you get time, please execute it 
/// that's why start() method is called  and if you call run(), your program will run normal but by single threaded 'main' because new thread did not get registered to the CPU
///  Once CPU allows to execute that thread, then run method will get executed.

class MyThread extends Thread {
	public void run() {	// it will do the task
		//both the threads Th1 and Th2 will execute this same task at the same time (parallel)
		System.out.println("MyThread.run()...executing task...." + Thread.currentThread().getName());
		Payment p1 = new Payment();
		p1.doPayment();
	}
}

class Payment{
	public void doPayment(){
		
		System.out.println("Payment.doPayment()....executing task..." + Thread.currentThread().getName());
	}
}

public class CreatingNewThread {

	public static void main(String[] args) {
		System.out.println("HelloWorld.main()...started....." + Thread.currentThread().getName() );
		
		//Thread-0,1,2....    --> by default thread name
		MyThread t1 = new MyThread();	//new thread created
		t1.setName("Th1");
		t1.start(); //new thread started --> main + Th1
		
		MyThread t2 = new MyThread();	//new thread created
		t2.setName("Th2");
		t2.start(); //new thread started  --> main + Th2
		
		
		///again starting the thread
		// t1.start();	//IllegalThreadStateException -->  
		/** It is never legal to start a thread more than once.
	     * In particular, a thread may not be restarted once it has completed
	     * execution.
	     *
	     * @exception  IllegalThreadStateException  if the thread was already
	     *               started.
		*/
		 System.out.println("HelloWorld.main()...Ended....." + Thread.currentThread().getName() );
		 
	}

}
