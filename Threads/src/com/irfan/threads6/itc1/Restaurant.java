package com.irfan.threads6.itc1;

//task
import java.util.*;

public class Restaurant
{
	private Queue<String> orders = new LinkedList<>();

	// Chef prepares food as a Producer
	public void prepareFood(String order) throws InterruptedException
	{

		synchronized (orders)
		{
			while (!orders.isEmpty())
			{
				System.out.println(Thread.currentThread().getName() + " --> waiting... Kitchen full!");
				orders.wait(); // wait until waiter serves
			}
			orders.add(order);
			System.out.println(Thread.currentThread().getName() + " -->  prepared food: " + order);
			orders.notify(); // notify waiter
		}
	}

	// Waiter serves food
	public void serve() throws InterruptedException
	{

		synchronized (orders)
		{
			while (orders.isEmpty())
			{
				System.out.println(Thread.currentThread().getName() + " --> waiting... No food!");
				orders.wait(); // wait until chef prepares
			}
			String order = orders.remove();
			System.out.println(Thread.currentThread().getName() + " -->  served: " + order);
			orders.notify(); // notify chef
		}
	}
}
