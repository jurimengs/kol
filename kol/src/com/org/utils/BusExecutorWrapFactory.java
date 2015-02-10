package com.org.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 *
 * 线程池包装器
 */
public class BusExecutorWrapFactory implements ExecutorService {
	
    //默认初始化的线程数
    public static int DEFAULT_THREAD_SIZE = 50;
    
    public static String CACHE_POOL = "cached";
    public static String FIXED_POOL = "fixed";
    public static String SINGLE_POOL = "single";
    public static String SCHEDULED_POOL = "scheduled";
    public static String THREADPOOL_POOL = "threadpool";
    
    private ExecutorService executor;
    
    public BusExecutorWrapFactory(String type,int threads){
	     if(CACHE_POOL.equalsIgnoreCase(type)){
	    	 executor = Executors.newCachedThreadPool(new MaxPriorityThreadFactory());
	     }else if(FIXED_POOL.equalsIgnoreCase(type)){
	    	 executor = Executors.newFixedThreadPool(threads, new MaxPriorityThreadFactory());
	     }else if(SINGLE_POOL.equalsIgnoreCase(type)){
	    	 executor = Executors.newSingleThreadExecutor(new MaxPriorityThreadFactory());
	     }else if(SCHEDULED_POOL.equalsIgnoreCase(type)){
	    	 executor = Executors.newScheduledThreadPool(threads, new MaxPriorityThreadFactory());
	     }else if(THREADPOOL_POOL.equalsIgnoreCase(type)){
	    	 /**
	    	  * arg0:the number of threads to keep in the pool, even if they are idle.
	    	  * arg1:the maximum number of threads to allow in the pool.
	    	  * arg2:when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
	    	  * arg3:the time unit for the keepAliveTime
	    	  * arg4:the queue to use for holding tasks before they are executed. This queue will hold only the <tt>Runnable</tt>tasks submitted by the <tt>execute</tt> method.
	    	  * arg5:the factory to use when the executor creates a new thread.
	    	  * arg6:the handler to use when execution is blocked because the thread bounds and queue capacities are reached.
	    	  */
	    	 int arg0 = threads;
	    	 int arg1 = threads+50;
	    	 long arg2 = 5;
	    	 TimeUnit arg3 = TimeUnit.MINUTES;
	    	 BlockingQueue<Runnable> arg4 = new  LinkedBlockingQueue<Runnable>(threads);
	    	 ThreadFactory arg5 = new MaxPriorityThreadFactory();
	    	 RejectedExecutionHandler arg6 = new ThreadPoolExecutor.AbortPolicy();
	    	 executor = new ThreadPoolExecutor(arg0, arg1, arg2, arg3, arg4,arg5, arg6);
	     }else{
	    	 executor = Executors.newFixedThreadPool(DEFAULT_THREAD_SIZE);
	     }
	}

    
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return executor.awaitTermination(timeout, unit);
	}

	public <T> List<Future<T>> invokeAll(
			Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		return executor.invokeAll(tasks, timeout, unit);
	}

	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
			throws InterruptedException {
		return executor.invokeAll(tasks);
	}

	public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
			long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return executor.invokeAny(tasks, timeout, unit);
	}

	public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
			throws InterruptedException, ExecutionException {
		return executor.invokeAny(tasks);
	}

	public boolean isShutdown() {
		return executor.isShutdown();
	}

	public boolean isTerminated() {
		return executor.isTerminated();
	}

	public void shutdown() {
		executor.shutdown();		
	}

	public List<Runnable> shutdownNow() {
		return executor.shutdownNow();
	}

	public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
	}

	public <T> Future<T> submit(Runnable task, T result) {
		return executor.submit(task, result);
	}

	public Future<?> submit(Runnable task) {
		return executor.submit(task);
	}

	public void execute(Runnable command) {
		executor.execute(command);	
	}	
}
