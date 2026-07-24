package com.irfan.overload;

class EmployeeSalary
{
	
	public int calculateSalary(int baseSal)
	{
		return  baseSal;
	}
	
	///access modifier different
	private void calculateSalary(double baseSal, double bonus)
	{
		double salary = baseSal + bonus;
		System.out.println("Salary of Employee2 :" + salary);
	}
	
	//return type different
	public double calculateSalary(double baseSal, double bonus, double incentives)
	{
		return baseSal + bonus + incentives;
	}
	
	//I had to create default constructor and constructor with argument since I made a method private 
	public EmployeeSalary() {
		//this is for all object creation
	}
	
	///this is for mainly private method
	public EmployeeSalary(double baseSal, double bonus) {
		this.calculateSalary(baseSal, bonus);
	}
}
public class Driver2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeSalary emp1 = new EmployeeSalary();
		int emp1Salary = emp1.calculateSalary(233124);
		System.out.println("Salary of Employee1 :" + emp1Salary);
		
		EmployeeSalary emp2 = new EmployeeSalary(225353.45, 3423);
		
		
		EmployeeSalary emp3 = new EmployeeSalary();
		double emp3Salary = emp3.calculateSalary(29383500, 5000, 3244.55);
		System.out.println("Salary of Employee3 :" + emp3Salary);
	}

}
