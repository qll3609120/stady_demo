package com.example.demo.executor;

import java.util.concurrent.*;

/**
 * Created by evan.qi on 2017/6/19.
 */
public class Executor  {

	public static void main(String[] args){
		//创建单线程
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new DemoThread());

		//固定线程池大小
		executorService = Executors.newFixedThreadPool(2);
		for(int i=0;i<3;i++){
			executorService.execute(new DemoThread());
		}

		executorService = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			executorService.execute(new DemoThread());
		}

		Future future = executorService.submit(new Callable() {
			@Override
			public Object call() throws Exception {
				Thread.sleep(10000);
				return "11";
			}
		});

		try {
			try {
				System.out.println(future.get(2,TimeUnit.SECONDS));
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("schedule thread");
			}
		},1,3,TimeUnit.SECONDS);
	}
}
