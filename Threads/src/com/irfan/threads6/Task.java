package com.irfan.threads6;

public class Task
{
	int number;
	boolean isDataAvailable = false;
	
	public synchronized void produce(int num) throws InterruptedException {
		
		while(isDataAvailable) {
			System.out.println("---> " + Thread.currentThread().getName() + " is waiting");
			wait();
		}
		number = num;
		System.out.println("producing data : " + number);
		isDataAvailable = true;
		notify();
		
	}
	
	public synchronized void consume() throws InterruptedException {
		while(!isDataAvailable) {
			System.out.println("---> " + Thread.currentThread().getName() + " is waiting");
			wait();
		}
		System.out.println("consuming data : " + number);
		isDataAvailable = false;
		notify();
		
	}
}
