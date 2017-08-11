package com.example.demo.io;

import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by evan.qi on 2017/7/3.
 */
public class PipeRThread implements Runnable {
	private PipedInputStream in ;

	public PipedInputStream getIn() {
		return in;
	}

	public void setIn(PipedInputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			byte[] bytes = new byte[100];
			in.read(bytes);
			System.out.println(new String(bytes));
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
