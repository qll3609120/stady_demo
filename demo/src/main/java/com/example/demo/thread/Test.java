package com.example.demo.thread;

public class Test {
	public  class myThread implements Runnable{
		private String threadName;
		private Object pre;
		private Object curr;
		public  myThread(String n,Object p,Object c){
			this.threadName=n;
			this.pre=p;
			this.curr=c;
		}
		public void run() {
			// TODO Auto-generated method stub
			int i=10;
			while (i>0){
				synchronized(pre){
					synchronized(curr){
						System.out.println(threadName);
						i--;
						curr.notify();//通知下一个对象获得锁
					}
					try {
						pre.wait();
					}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//释放对pre对象持有的锁
				}
			}
		}
	}
	public static void main(String[] args){
		Object a=new Object();
		Object b=new Object();
		Object c=new Object();
		Test t=new Test();
		myThread pa=t.new myThread("A",c,a);
		new Thread(pa).start();
		myThread pb=t.new myThread("B",a,b);
		new Thread(pb).start();
		myThread pc=t.new myThread("C",b,c);
		new Thread(pc).start();
	}
}