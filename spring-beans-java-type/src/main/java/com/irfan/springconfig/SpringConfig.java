package com.irfan.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irfan.beans.account.Account;

@Configuration	// this(@Configuration) tells that this class is source of bean definition //making class able to manage beans 
public class SpringConfig
{
	//define the bean
	@Bean	//if no id-name is given then method id-name will be taken as method-name("createAccount")
	public Account createAccount() {
		Account account = new Account();
		account.setAccountHolder("Kodewala");
		account.setAccountNumber("00998776444672");
		account.setIfscCode("CNRB00447");;
		return account;
	}
	
	@Bean("acc2")	
	public Account createAccount2() {
		Account account = new Account();
		account.setAccountHolder("Kodnest");
		account.setAccountNumber("0453776444672");
		account.setIfscCode("CNRB00447");;
		return account;
	}
	
	@Bean("acc2")	
	public Account createAccount3() {
		Account account = new Account();
		account.setAccountHolder("TAP Ac");
		account.setAccountNumber("01112245122");
		account.setIfscCode("SBI00447");;
		return account;
	}
}
