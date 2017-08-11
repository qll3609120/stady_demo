package com.example.demo.io;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * Created by evan.qi on 2017/7/3.
 */
public class ChannelDemo {

	//channel:既可以读数据到buffer ，也可以从buffer中写数据到通道
	/**
	 * 实现：FileChannel =>文件
	 * DatagramChannel=>UDP
	 * SocketChannel=>TCP
	 * ServerSocketChannel =>监听tcp连接
	 */
	public static void main(String[] args) {
		try {
			fileChannel();
			transform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void fileChannel() throws Exception{
		try(RandomAccessFile randomAccessFile =
					new RandomAccessFile(new File("C:\\Users\\evan.qi\\Desktop","demo.txt"),"rw");){
			FileChannel fileChannel = randomAccessFile.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(20);
			//读取的字节数
			int bytesRead = fileChannel.read(byteBuffer);
			while(bytesRead!=-1){
				System.out.println("read:"+bytesRead);
				//转换读写模式
				byteBuffer.flip();
				if(bytesRead==20){
					byte[] bytes = byteBuffer.array();
					System.out.println(bytes.length+" "+ new String(bytes));
				}else{
					while(byteBuffer.hasRemaining()){
						byte bytes = byteBuffer.get();
						System.out.println(bytes+" "+ (char)bytes);
					}
				}
				byteBuffer.clear();
				bytesRead = fileChannel.read(byteBuffer);
			}
		}

	}


	public static void transform() throws Exception{
		try(RandomAccessFile file = new RandomAccessFile(new File("C:\\Users\\evan.qi\\Desktop","demo.txt"),"rw");
			RandomAccessFile copyFile = new RandomAccessFile("data.txt","rw");){
			FileChannel fileChannel = file.getChannel();
			FileChannel copyChannel = copyFile.getChannel();
			Long position = 0l;
			Long count = fileChannel.size();
			copyChannel.transferFrom(fileChannel,position,count);
		}
	}
}
