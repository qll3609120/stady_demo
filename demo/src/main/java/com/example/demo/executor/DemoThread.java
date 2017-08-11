package com.example.demo.executor;

/**
 * Created by evan.qi on 2017/6/19.
 */
public class DemoThread implements Runnable {
	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName()+"start");
	}
}
