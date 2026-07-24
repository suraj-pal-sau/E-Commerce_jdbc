package com.irfan.threads2;

class MyThread implements Runnable{

	@Override
	public void run() {
		System.out.println("MyThread.run()");
	}
	
}

public class Driver {

	public static void main(String[] args) {
		MyThread myThread = new MyThread();	//runnable has no start() functionality so we have to create thread object
		
		//creating object of thread to start the created thread(myThread) & passing it 
		Thread t1 = new Thread(myThread);	//new Thread(runnable reference)
		t1.start();

	}

}
