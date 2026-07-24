package com.irfan.threads9;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture4
{

	public static void main(String[] args)
	{
		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 40);
		CompletableFuture<Integer> finalFuture = future1.thenCombineAsync(future2, (n1, n2) -> n1 + n2);
		
		CompletableFuture<Object> cf0 = CompletableFuture.anyOf(future1, future2);
		CompletableFuture cf= CompletableFuture.allOf(future1, future2);
		
		System.out.println(cf0);
		System.out.println(cf);

		
		System.out.println(finalFuture.join());
		
	}

}
