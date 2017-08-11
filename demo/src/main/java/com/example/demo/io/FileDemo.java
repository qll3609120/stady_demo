package com.example.demo.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Created by evan.qi on 2017/6/29.
 */
public class FileDemo {
		public static void main(String[] args) throws Exception {
			/*File file = new File("C:\\Users\\evan.qi\\Desktop\\demo.txt");
			RandomAccessFile inFile = new RandomAccessFile(file,"r");
			byte[] bytes = new byte[1024];
			RandomAccessFile randomAccessFile = new RandomAccessFile("demo","rw");
			String data = "";


			data = inFile.readLine();
			while((data)!=null){

				System.out.println(data);
				randomAccessFile.writeBytes(data);

				if((data=inFile.readLine())!=null){

				}
				randomAccessFile.writeBytes("\n");
			}*/

			RandomAccessFile file = new RandomAccessFile("demo","rw");

			// 占4个字节

			file.writeInt(20);
			file.writeDouble(8.236598);// 占8个字节
			file.writeUTF("这是一个UTF字符串");// 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
			file.writeBoolean(true);// 占1个字节
			file.writeShort(395);// 占2个字节
			file.writeLong(2325451l);// 占8个字节
			file.writeUTF("又是一个UTF字符串");
			file.writeFloat(35.5f);// 占4个字节
			file.writeChar('a');// 占2个字节

			file.seek(0);// 把文件指针位置设置到文件起始处

			// 以下从file文件中读数据，要注意文件指针的位置
			System.out.println("——————从file文件指定位置读数据——————");
			System.out.println(file.readInt());
			System.out.println(file.readDouble());
			System.out.println(file.readUTF());

			file.skipBytes(3);// 将文件指针跳过3个字节，本例中即跳过了一个boolean值和short值。
			System.out.println(file.readLong());

			file.skipBytes(file.readShort()); // 跳过文件中“又是一个UTF字符串”所占字节，注意readShort()方法会移动文件指针，所以不用加2。
			System.out.println(file.readFloat());

			//以下演示文件复制操作
			System.out.println("——————文件复制（从file到fileCopy）——————");
			file.seek(0);
			RandomAccessFile fileCopy=new RandomAccessFile("fileCopy","rw");
			int len=(int)file.length();//取得文件长度（字节数）
			byte[] b=new byte[len];
			Long pointer = file.getFilePointer();
			while(file.read(b,pointer.intValue(),1024)!=-1){
				fileCopy.write(b);
				pointer = file.getFilePointer();
			}
			/*file.readFully(b);
			fileCopy.write(b);*/
			System.out.println("复制完成！");
		}

}
