package com.example.demo.io;

import java.io.PipedOutputStream;
import java.io.PipedWriter;

/**
 * Created by evan.qi on 2017/7/3.
 */
public class PipeWThread implements Runnable{

	private PipedOutputStream out = new PipedOutputStream();

	public PipedOutputStream getOut() {
		return out;
	}

	public void setOut(PipedOutputStream out) {
		this.out = out;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			out.write("ssssssss".getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
