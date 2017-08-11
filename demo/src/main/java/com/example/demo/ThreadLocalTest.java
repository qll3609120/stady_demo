package com.example.demo;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by evan.qi on 2017/8/2.
 */
@SpringBootTest(classes = {DemoApplication.class})
@RunWith(SpringRunner.class)
public class ThreadLocalTest {

	private static final ThreadLocal<String> name = new ThreadLocal<String>();

	private static final ThreadLocal<String> value = new ThreadLocal<String>();

	@org.junit.Test
	public void test(){
		int i =0;
		while(i<20){
			i++;
			final String tValue = i+"";
			new Thread(new Runnable() {
				@Override
				public void run() {
					String tName = Thread.currentThread().getName();
					name.set(tName);
					value.set(tValue);
					call();

				}
			}).start();
		}

	}

	public void call(){
		new ThreadLocalTest().callB();
	}

	public void callB(){
		System.out.println(name.get());
		System.out.println(value.get());
	}
}
