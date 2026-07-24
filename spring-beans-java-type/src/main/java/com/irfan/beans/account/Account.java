package com.irfan.beans.account;


public class Account
{
	private String accountHolder;
	private String accountNumber;
	private String ifscCode;
	
	
	public String getAccountHolder()
	{
		return accountHolder;
	}
	public String getAccountNumber()
	{
		return accountNumber;
	}
	public String getIfscCode()
	{
		return ifscCode;
	}
	public void setAccountHolder(String accountHolder)
	{
		this.accountHolder = accountHolder;
	}
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	public void setIfscCode(String ifscCode)
	{
		this.ifscCode = ifscCode;
	}
	
	
	public void showAccountHolder() {
		System.out.println("Account [accountHolder=" + accountHolder + ", accountNumber=" + accountNumber + ", ifscCode=" + ifscCode
				+ "]");
	}
	
}
