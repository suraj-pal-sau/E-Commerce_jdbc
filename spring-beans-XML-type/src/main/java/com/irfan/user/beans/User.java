package com.irfan.user.beans;

public class User
{
	private String username;
	private String location;
	
	public void displayUser() {
		System.out.println("User [username=" + username + ", location=" + location + "]");
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getLocation()
	{
		return location;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	
	
}
