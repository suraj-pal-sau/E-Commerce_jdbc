package com.irfan;

//problem with jar files ---> adding new features or any modification in the actual class will not reflect upon its jar file 
//				1) so you have create jar file again due to new update
//				2) and other project which is using that jar will have to add new updated jar files
//				3) in real life there are thousands of classes and they get modified rapidly --> think about doing manually
///Solution --> maven 

public class UserType
{
	public static String RETAIL = "retail";
	public static String RESELLER = "reseller";
	
	//adding new type
	public static String MARKETPLACE = "marketplace";
}
