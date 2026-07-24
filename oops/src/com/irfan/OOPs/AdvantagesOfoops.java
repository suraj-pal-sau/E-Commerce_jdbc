package com.irfan.OOPs;

//14/4

public class AdvantagesOfoops {
	// if you follow oops concept you will achieve these following features:
	
	/*
	 	1)reusable : re-use of codes(Invoice ->generated invoice pdf -> reseller) 
	 		> e.g. fill water in water bottle(not creating new bottles)
	 		> time -> money/high cost
	 		> trust -> suppose above "invoice class" is already tested in production, 
	 				   then this same code is more trustable than somebody writing new codes.
	 				   something already working in production is more trustable,
	 				   so ultimately you got trust,you save money and you get the confidence. 
	 		
	 	2)maintainable:
	 		> easy to maintain(development->productions->maintenance)
	 		> maintain post-production issues
	 		> bug fixing
	 		> enhancement(improving existing features, adding extra features)
	 	3)secure:
	 		> no body can modify your attributes or values.
	 		  In ATM without entering pin you can't withdraw amount.
	 		  and if you have 10k money, can you withdraw 30k ? >> NO 
	 	4)scalable:
	 		> 10k users -> 100k users (not writing new codes) 
	 		> increasing no. of cpu  or computers, storages(consider e-commerce on festival /big billion sale)
	 	5)readable:
	 		> easy to read (does not mean can read or not)
	 		> during maintenance, other developers need to see the existing code and now code is not readable properly,
	 		> then the developer will have to read all codes and see the logics
	 		> for example: class X {
	 		
	 			public void gen(){
	 			
	 			}
	 		} 
	 		for the above codes, developer will have to read/see all logics what this class/method is doing 
	 		------------------------------------------
	 		the below code is readable
	 		class Invoice {
	 		
	 			public void generateInvoice(){
	 			
	 			}
	 		} 
	 		**people come & go and join company but code will be there for years, so write meaningful codes 
	 		--------------------------------------------
	 	6)modular:(loose coupling)
	 		> dividing entire application into different modules/parts
	 		
	 		> e.g. modular kitchen -> everything is in its place -> for masala, separate space made(self compartment), for cylinder, for utensils
	 		> for everything there is a particular/dedicated area. so now if problem is with cylinder then there is a dedicated area for it, you can fix it
	 		> if you have some works in utensil then you have dedicated area for it, if you want to check masala then go its space
	 		> if space is not separated for every then if you tell anyone to bring any masala, 
	 		> then finding will become difficult(he will search entire kitchen)    
	 		
	 		> time consuming ---> time save
	 		> one problem can impact entire application/system(if not modular)
	 		> application should be modular.
	 		> Catalog, Payment, Invoice module, search module...
	 	
	 	
	*/
	
}
