package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task3 {

	public static void main(String[] args) throws Exception {
		// get threads count
		// lambda based anonymous inner class for implementing Callable(I)
		Callable<Object> callable = ()->{
				System.out.println("Thread execution"+ Thread.currentThread());
				return "Hello";
			
		};
		ExecutorService service=Executors.newFixedThreadPool(3);// thread pool
		for (int i = 0; i < 3; i++) {
			service.submit(callable);
		}
		//shutdown the thread
		service.awaitTermination(20, TimeUnit.SECONDS);
	}
}
