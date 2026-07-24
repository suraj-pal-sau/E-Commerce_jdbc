package com.irfan.threads6.itc1;

public class RestaurantProcess
{

	public static void main(String[] args)
	{
		Restaurant restaurant = new Restaurant();

        ChefAsProducer chefThread = new ChefAsProducer(restaurant);
        chefThread.setName("CHEF");
        WaiterAsConsumer waiterThread = new WaiterAsConsumer(restaurant);
        waiterThread.setName("WAITER");

        chefThread.start();
        waiterThread.start();

	}

}
