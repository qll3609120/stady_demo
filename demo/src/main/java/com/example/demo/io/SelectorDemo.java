package com.example.demo.io;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * Created by evan.qi on 2017/7/3.
 */
public class SelectorDemo {
	/**
	 * selector
	 * 四种事件：
	 * SelectionKey.OP_CONNECT /OP_READ /OP_ACCESS /OP_WRITE
	 * 多事件：使用位或符|
	 */

	public static void main(String[] args) {
		//创建selector
		try(Selector selector = Selector.open();){

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
