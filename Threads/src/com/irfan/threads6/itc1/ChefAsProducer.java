package com.irfan.threads6.itc1;

public class ChefAsProducer extends Thread
{

	private final Restaurant restaurant;

	ChefAsProducer(Restaurant restaurant)
	{
		this.restaurant = restaurant;
	}

	@Override
	public void run()
	{
		try
		{
			for (int i = 1; i <= 5; i++)
			{
				//System.out.println("Taking time for cooking.......");
				restaurant.prepareFood("Dish " + i);
				Thread.sleep(500); // simulate cooking time
			}
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
