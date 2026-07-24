package com.irfan.maven.project_unit_test;

public class EvenOdd
{
	public boolean isEvenOdd(int n)
	{
		if(n < 0) return false;
		return (n & 1) == 0 ;
	}
}
