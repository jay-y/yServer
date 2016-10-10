package org.yserver.utils.log4j;

import org.apache.log4j.Priority;

public class DailyRollingFileAppender extends org.apache.log4j.DailyRollingFileAppender
{
	 public boolean isAsSevereAsThreshold(Priority priority) 
	 {  
		 //只判断是否相等，而不判断优先级  
		 return this.getThreshold().equals(priority);
	 } 
}
