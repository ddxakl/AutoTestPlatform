package com.agree.util;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UniqueUtil {
	private static  long ONE_STEP = 10;
	private static  long BASE = 1129703383453l;

	private static  Lock LOCK ;

	private static long lastTime = System.currentTimeMillis();
	private static short lastCount = 0;
	private static UniqueUtil uniqueInstance;
	private String primaryKey="";

	/**
	* a time (as returned by {@link System#currentTimeMillis()}) at which
	* the VM that this UID was generated in was alive
	* @serial
	*/
	private  long time;

	/**
	* 16-bit number to distinguish UID instances created
	* in the same VM with the same time value
	* @serial
	*/
	private  short count;

	/**
	* Generates a UID that is unique over time with
	* respect to the host that it was generated on.
	*/
	public static UniqueUtil getInstance(){
		if(uniqueInstance==null)
			uniqueInstance= new UniqueUtil();
		return uniqueInstance;
		
	}
	public UniqueUtil() {
		
		 LOCK = new ReentrantLock();
	}
	
	
	public String getPrimaryKey(String tableNo){
		LOCK.lock();
		try {
					boolean done = false;
					while (!done) {
						long now = System.currentTimeMillis();
						if (now == lastTime) {
						// pause for a second to wait for time to change
							try {
							Thread.currentThread().sleep(1);
							}catch (java.lang.InterruptedException e) {
							} // ignore exception
						continue;
							}else {
							lastTime = now;
							lastCount = 0;
							done = true;
						}
					}
				
			time = lastTime;
			count = lastCount++;
			if(tableNo!=null){
				primaryKey=tableNo+time;
			}else{
				primaryKey=time+"";
			}
		}finally {
		LOCK.unlock();
		}
		return primaryKey;
	}
	
	public static String getRcid(String shortName){
		return UniqueUtil.getInstance().getPrimaryKey(shortName);
	}
	public static void main(String args[]){
		for(int i=0;i<100;i++)
		//System.out.println(new UniqueUtil().lastTime);
		System.out.println(UniqueUtil.getInstance().getPrimaryKey("dd"));
		System.out.println(UUID.randomUUID());
	}
}
