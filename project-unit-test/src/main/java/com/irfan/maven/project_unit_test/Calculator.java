package com.irfan.maven.project_unit_test;

public class Calculator
{
	// if you make change in below --> adding any new features unit test should be working fine --> rerunning test after changing (regression test )
	//don't break existing functionality
	public int addTwoNumber(int a, int b) {
		
		return a + b;
		
		///suppose new requirements come that only +ve no be added
		/// then unit test will fail --> it is not acceptable
		///***  make sure after modification, all unit test cases must be passed 
//		int sum=0;
//		if(a > 0 && b >0) {
//			sum = a + b;
//		}
//		return sum;
	}
}
