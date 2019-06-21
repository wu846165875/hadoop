package com.lww.hadoop.hadoop2;

import org.junit.Test;

public class TestThread {
	@Test
	public void test() {
		MyThread1 thread1 = new MyThread1("1号窗口");
		MyThread1 thread2 = new MyThread1("2号窗口");
		MyThread1 thread3 = new MyThread1("3号窗口");
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	@Test
	public void test2() {
		MyThread2 t = new MyThread2();
		Thread t1 = new Thread(t, "1号窗口");
		Thread t2 = new Thread(t, "2号窗口");
		Thread t3 = new Thread(t, "3号窗口");
		t1.start();
		t2.start();
		t3.start();
		
	}
	
}



class MyThread1 extends Thread{
	private int ticket = 10;
	private String name;
	@Override
	public void run() {
		while(ticket>0) {
			System.out.println(this.name +":"+ (ticket--));
		}
	}
	public MyThread1(String name) {
		super();
		this.name = name;
	}
	
}



class MyThread2 implements Runnable {
	private int ticket = 10;
	private String name;
	public void run() {
		while(ticket>0) {
			System.out.println(Thread.currentThread().getName() +":"+ (ticket--));
		}
	}
	
}
	
