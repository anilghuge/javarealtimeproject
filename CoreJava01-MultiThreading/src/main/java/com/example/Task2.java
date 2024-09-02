package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task2 {

	public static void main(String[] args) throws Exception {
		// get threads count
		// anonymous inner class for implementing Callable(I)
		Callable<Object> callable = new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				System.out.println("Thread execution"+ Thread.currentThread());
				return "Hello";
			}
		};
		ExecutorService service=Executors.newFixedThreadPool(3);// thread pool
		for (int i = 0; i < 3; i++) {
			service.submit(callable);
		}
		//shutdown the thread
		service.awaitTermination(20, TimeUnit.SECONDS);
	}
}
