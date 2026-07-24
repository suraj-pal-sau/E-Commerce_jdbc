package com.irfan.threads2;

class MyThread1 implements Runnable {

	@Override
	public void run() {	//RUNNING --> executing the task
		System.out.println("thread is in  'RUNNING state' --> executing the task");
		System.out.println();
		
		System.out.println("MyThread1.run()......Started");
		System.out.println("MyThread1.run()");
		System.out.println("MyThread1.run()");
		System.out.println("MyThread1.run()");
		
		//t1 can go to sleep/pause/wait state.  --> WAITING STATE
		//once waiting is over, t1 will move to RUNNABLE STATE --> CPU gives time to t1 --> RUNNING STATE(will execute the below task)
		
		System.out.println("MyThread1.run()");
		System.out.println("MyThread1.run()");
		System.out.println("MyThread1.run()......Ended");
		System.out.println();
		
		System.out.println("thread is 'Terminated' --> completed the task");
	}	// once run method is completed, thread t1 is TERMINATED / DEAD

}

public class LifeCycleOfThread {

	public static void main(String[] args) {
		MyThread1 myThread = new MyThread1();	//

		Thread t1 = new Thread(myThread); // new born (thread object created)
		t1.start(); // thread moved from new born to RUNNABLE
		
		//t1.start();	//once thread is terminated, you can't restart the thread --> IllegalThreadStateException
	}

}
