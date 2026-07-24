package com.irfan.threads2;
class Odd extends Thread{
	
	public void run() {
		
		for (int i = 1; i < 20; i=i+2)
		{
			System.out.println("odd number is " + i + " printed by --> " + Thread.currentThread().getName());
			
			if(i == 11) {
				try
				{
					System.out.println("sleeping state...." + Thread.currentThread().getName());
					Thread.sleep(5000);
				} catch (InterruptedException e)
				{
					
					e.printStackTrace();
				}
			}
		}
	}
}

class Even extends Thread{
	
	public void run() {
		
		for (int i = 0; i < 20; i=i+2)
		{
			System.out.println("even number is " + i + " printed by --> " + Thread.currentThread().getName());
			
			if(i == 10) {
				try
				{
					System.out.println("sleeping state...." + Thread.currentThread().getName());
					Thread.sleep(5000);
				} catch (InterruptedException e)
				{
					
					e.printStackTrace();
				}
			}
		}
	}
}

public class Driver2
{

	public static void main(String[] args)
	{
		Odd t1 = new Odd();
		t1.setName("OddThread");
		t1.start();
		
		Even t2 = new Even();
		t2.setName("EvenThread");
		t2.start();

	}

}
