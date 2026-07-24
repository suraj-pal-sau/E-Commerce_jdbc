package com.irfan.threads1;

//all threads are executing task independently at the same 
// there is not sequential execution order followed here, one after another --> NO
//whoever will complete task fast, will come first --> see main executing last 

class MyThread2 extends Thread{
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("MyThread2.run()..." + i + "  " + Thread.currentThread().getName());
		}
	}
}

public class CreatingNewThread2 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("CreatingNewThread2.main()....Started ---> " + Thread.currentThread().getName());
		
		
		MyThread2 t0 = new MyThread2();
		t0.start();
		
		MyThread2 t1 = new MyThread2();
		t1.start();

		Thread.sleep(1000);	//suppose --> main method is taking 1 second for below task
		System.out.println("CreatingNewThread2.main()....Ended ---> " + Thread.currentThread().getName());
	}

}
