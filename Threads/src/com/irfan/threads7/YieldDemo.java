package com.irfan.threads7;

// limitation of run() --> returns void, cannot throw an exception  
/// how would 'main thread' / YieldDemo class know that t1 or t2 executed successfully or failed ??? how, 
/// since run() returns void, thats why that class would not able to get the status of threads

class FirstThread extends Thread
{

	@Override
	public void run()
	{
		System.out.println("FirstThread.run().... START " + Thread.currentThread().getName());
		Thread.yield(); // pause the current thread and give chance to other thread 
		System.out.println("FirstThread.run().... END " + Thread.currentThread().getName());

	}
	
	public boolean someMethod() {
		//account creation logics
		return true;
	}
}

public class YieldDemo
{

	public static void main(String[] args)
	{
		FirstThread t1 = new FirstThread();
		
		///When we started, thread. What is the outcome of that thread? How do we know?? we can know from console but main method/class how would it get informed
		/// How this main method or class knows that Whatever code you return, It's successful or failed. --> run() limitation
		t1.start();

		boolean status = t1.someMethod();	//since method has been returning something main method or class can know its status but run() returns void
											//now class/this main method can take action according to status
		if(status) {
			System.out.println("Account has been created successfully....");
		}
		
		FirstThread t2 = new FirstThread();
		t2.start();

	}

}
