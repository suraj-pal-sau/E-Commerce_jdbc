package com.irfan.threads2;

class New1Thread implements Runnable{
	public void run() {
		System.out.print("New1Thread.run()....by Implementing Runnable interface... ");
		System.out.println(Thread.currentThread().getName());
	}
}

public class CreatingNewThread2
{

	public static void main(String[] args)
	{
		New1Thread thread = new New1Thread();
		
		Thread t1 = new Thread(thread);
		t1.start();

	}

}
