package com.irfan.polymorphism;

/// 22.04

/// Upcasting(widening the type) : assigning subclass object to a superclass reference variable
///								: done by compiler automatically, smaller to larger type, no special syntax required 
/// 							:safe(no data loss) like int ->> double (no data loss)
/// Downcasting(narrowing/reducing the type) : assigning superclass object to a subclass reference variable
/// 							: done by programmer manually, larger to small type, requires (target_type) operator
/// 							: risky(possible data loss)
/// if it is done manually, it gives runtime error of ClassCastException, it can only be done by using the "instanceof" operator
class InvoiceMgmt
{
	int x = 10;
	String name = "Irfan";
}

class GSTInvoice extends InvoiceMgmt
{
	int x = 20;
	String name = "Muhammad Irfan";
}

public class Upcasting {
	public static void main(String[] args) {
		GSTInvoice gstInvoice = new GSTInvoice();
		///type 	 ref name = actual obj
		// int 	     amount   = 100;
		
		//parent class can refer child class object
		InvoiceMgmt invoiceMgmt = new GSTInvoice();	//auto upcasting
		//above line is like the below line
		/// InvoiceMgmt invoiceMgmt = (InvoiceMgmt) new GSTInvoice(); //compiler automatic type ->>(IvoiceMgmt)
		
		System.out.println(invoiceMgmt.name);	///we can override methods only, not the variables(data members)
		System.out.println(invoiceMgmt.x);		///so runtime polymorphism cannot be achieved by data members
		
		System.out.println(gstInvoice.name);	//for accessing data of 'GSTinvoice' we have to give reference of that class.
		
		//GSTInvoice gstInvoice = new InvoiceMgmt();	//downcasting: it showing error, we have to do manually
		GSTInvoice gstInvoice1 = (GSTInvoice) new InvoiceMgmt();	
		//giving runtime error : Exception in thread "main" java.lang.ClassCastException: com.irfan.polymorphism.InvoiceMgmt cannot be cast to
		
	
	}
}
