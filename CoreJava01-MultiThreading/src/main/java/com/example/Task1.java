package com.example;

public class Task1 implements Runnable {

	@Override
	public void run() {
		System.out.println("Task1.run()---->hello");
	}
	
	public static void main(String[] args) {
		Task1 task1=new Task1();
		Thread t1=new Thread(task1);
		t1.start();
		System.out.println("---------------");
		
		Task1 task2=new Task1();
		Thread t2=new Thread(task2);
		t2.start();
		
		// anonymous inner class implementing Runnable(I)
		Runnable run=new Runnable() {
			@Override
			public void run() {
				System.out.println("run() method ---> hi");
			}
		};
		
		Thread t3=new Thread(run);
		t3.start();
		
		// Lamda expression based anonymous inner class implementing Runnable(I)
		Runnable run1=()->System.out.println("lambda run() method ---> hi");
		
		Thread t4=new Thread(run1);
		t4.start();
	}

}
