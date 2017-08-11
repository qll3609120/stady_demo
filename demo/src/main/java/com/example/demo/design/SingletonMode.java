package com.example.demo.design;

/**
 * Created by evan.qi on 6/9/2017.
 *
 * 单例模式
 */
public class SingletonMode {

	private static SingletonMode instance = null;

	/**
	 * 私有化构造方法
	 */
	private SingletonMode(){

	}

	//1、懒加载模式

	public static SingletonMode getInstance(){
		if(instance==null){
			instance = new SingletonMode();
		}
		return instance;
	}
	//2、synchronized 获取对象
	public static SingletonMode getInstanceSafe(){
		if(instance==null){
			synchInstance();
		}
		return instance;
	}

	private  static synchronized void synchInstance(){
		instance = new SingletonMode();
	}

	static class SingletonFactory{
		public static SingletonMode instance = new SingletonMode();
	}

	//3、工厂方法获取
	public SingletonMode getInstanceByFactory(){
		if(instance==null){
			instance = SingletonFactory.instance;
		}
		return instance;
	}

}
