package com.lock.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	//线程数
	private Integer n = 10;
	
    static class Outputer {
    	//加锁
    	Lock lock = new ReentrantLock();	
        public synchronized void output(String name) { //同步也无效果
        	//lock.lock();//加锁（感觉无效果）
        	synchronized (this){//加锁，同步
        		//try {
            		for(int i=0; i<name.length(); i++) {
                        System.out.print(name.charAt(i));
                    }
                    System.out.println();
    			//} finally {
    				//lock.unlock();//不管如何都会释放锁
    			//}
        	}
        	
            
        }
    }
    
    class LockThread implements Runnable{
		@Override
		public void run() {
			while(n>0){
				try {
					Thread.sleep(1000);
					new Outputer().output("xiaoguangqing");
					//System.out.println("第" + Thread.currentThread().getName() + "个线程！-------------" + n);
					n--;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    public static void main(String[] args) {
    	new Thread(new LockTest().new LockThread()).start();
    	new Thread(new LockTest().new LockThread()).start();
    	new Thread(new LockTest().new LockThread()).start();
	}
}
