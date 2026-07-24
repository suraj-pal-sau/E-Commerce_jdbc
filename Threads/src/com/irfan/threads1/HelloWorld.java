package com.irfan.threads1;

/// now program is single threaded --> 'main' thread is doing all the task

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("who is executing this code? :..........  " + Thread.currentThread().getName() );
		
		System.out.println("HelloWorld.main()");
		
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.someMethod();
		

	}

	public void someMethod() {
		System.out.println("who is executing this code? :..........  ");
		System.out.println("HelloWorld.someMethod()......" + Thread.currentThread().getName() );
		
	}
}
