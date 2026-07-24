package com.irfan.inheritance;

public class Order extends Notifications {
	public void placeOrder(String userId, String productId, String someOtherInfo) {
		System.out.println("Order.placeOrder()");
		//once order placed, system/platform need to send an update over email/sms
		
		String text = "Your order has been successfully placed.";
		sendEmail(userId, text, null);
		
	}

}
