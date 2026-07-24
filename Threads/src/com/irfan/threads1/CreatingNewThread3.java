package com.irfan.threads1;

//you can run() directly but you will not achieve multithreading --> act as normal method calling
//run your program and see only 'main' thread is executing 

class TheadDemo extends Thread{
	@Override
	public void run() {
		System.out.println("TheadDemo.run()......" + Thread.currentThread().getName());
	}
}

public class CreatingNewThread3 {

	public static void main(String[] args) {
System.out.println("HelloWorld.main()...started....." + Thread.currentThread().getName() );
		
		
		MyThread t1 = new MyThread();	//new thread created
		
		t1.run(); //calling run() directly
	}

}
