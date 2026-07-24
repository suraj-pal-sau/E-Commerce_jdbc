package com.irfan.inheritance.override;

//;; will give no error
public class Driver3 {

	public static void main(String[] args) {
		Vehicle veh = new Vehicle();
		veh.engine();
		
		Car car = new Car();
		car.engine();;
		
		ElectricCar ec = new ElectricCar();
		ec.engine();
	}

}
