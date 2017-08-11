package com.example.demo.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by evan.qi on 2017/7/3.
 */
public class PipeDemo {
	public static void main(String[] args) throws Exception{

		PipeWThread pipeWThread = new PipeWThread();
		PipeRThread pipeRThread = new PipeRThread();
		PipedOutputStream out = pipeWThread.getOut();
		PipedInputStream in = pipeRThread.getIn();
		out.connect(in);

		new Thread(pipeWThread).start();
		new Thread(pipeRThread).start();
		 /*PipedOutputStream out =new PipedOutputStream();
		 PipedInputStream in = new PipedInputStream(out);

		 Thread wThread = new Thread(new Runnable() {
			 @Override
			 public void run() {
				 try {
				 	String content = "测试";
					 byte[] bytes = content.getBytes("UTF-8");
					 out.write(bytes);
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 });

		Thread rThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					byte[] bytes = new byte[100];
					in.read(bytes);
					System.out.println(new String(bytes,"UTF-8"));
					//流关闭
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		wThread.start();
		rThread.start();*/
	}








}
