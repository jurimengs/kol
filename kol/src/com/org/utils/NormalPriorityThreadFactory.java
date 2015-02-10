package com.org.utils;

import java.util.concurrent.ThreadFactory;

public class NormalPriorityThreadFactory implements ThreadFactory {

	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}

}
