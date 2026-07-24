package com.irfan.threads6.itc1;

public class WaiterAsConsumer extends Thread
{
	private final Restaurant restaurant;

    WaiterAsConsumer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                restaurant.serve();
                Thread.sleep(1000); // simulate serving time
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
