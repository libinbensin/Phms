
/**
 * Copyright (c) 2010-2100 AutoNavi Software Co., Ltd.  All rights reserved.
 */
/**
 * 
 */
package com.cking.phss.util;

import java.util.Iterator;
import java.util.LinkedList;

import android.app.Activity;

/**
 * 
 * @author zhouhuiming(mailto:huiming.zhou@autonavi.com)
 * @version 1.0, 2011-6-23
 */
/**
 * $Log$
 */
/**
 *
 */
public class ActivityStack {
    private static final String TAG = "ActivityStack";
    private LinkedList<Activity> linkedStack = new LinkedList<Activity>();
	private static ActivityStack activityStack = null;

	private ActivityStack(){}
	
	public static ActivityStack getInstance(){
		if(activityStack == null){
			synchronized (ActivityStack.class) {
				if(activityStack == null){
					activityStack = new ActivityStack();
				}
			}
		}
		return activityStack;
	}
	
	public void push(Activity activity){
		linkedStack.addFirst(activity);
	}
	
	public void cleanHistory(){
		Iterator<Activity> iter = linkedStack.iterator();
		while(iter.hasNext()){
			Activity act = iter.next();
			if(act == null){
				continue;
			}
			
			act.finish();
			iter.remove();
		}
	}
	
	public boolean isEmpty() {

        Iterator<Activity> iter = linkedStack.iterator();
        int count = 0;
        
        while(iter.hasNext()){
            Activity act = iter.next();
            if(act == null){
                continue;
            }
            
            count++;
        }
        
        return (boolean) (count == 0);
	}

    public int getCount() {

        Iterator<Activity> iter = linkedStack.iterator();
        int count = 0;

        while (iter.hasNext()) {
            Activity act = iter.next();
            if (act == null) {
                continue;
            }

            count++;
        }

        return count;
    }

	public void finishAll(){
		cleanHistory();
	}
	
	public Activity pop(){
		return linkedStack.poll();
	}
}
