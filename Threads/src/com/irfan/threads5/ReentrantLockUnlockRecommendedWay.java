package com.irfan.threads5;

// you need to understand when to lock and when to unlock

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUnlockRecommendedWay
{

	private ReentrantLock lock = new ReentrantLock();

	public void example() {
	    // lock() - blocks until acquired
	    lock.lock();
	    try {
	        // critical section
	    } finally {
	        lock.unlock();
	    }

	    // tryLock() - immediate attempt
	    if (lock.tryLock()) {
	        try {
	            // critical section
	        } finally {
	            lock.unlock();
	        }
	    } else {
	        System.out.println("Lock not available");
	    }

	    // tryLock(timeout) - waits up to 2 seconds
	    try {
	        if (lock.tryLock(2, TimeUnit.SECONDS)) {
	            try {
	                // critical section
	            } finally {
	                lock.unlock();
	            }
	        } else {
	            System.out.println("Could not acquire lock in time");
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	
	public static void main(String[] args)
	{
		

	}

}
