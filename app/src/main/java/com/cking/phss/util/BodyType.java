/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * TestResultUtil.java
 * classes : com.cking.phss.util.TestResultUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-19 下午9:34:10
 */
package com.cking.phss.util;

public class BodyType {
	int sn;
	int sex;
	int uage;
	int dage;
	int ufatrate;
	int dfatrate;
	int result;
	
	public BodyType(int sn,int sex,int uage,int dage,int ufatrate,int dfatrate,int result)
	{
		this.sn			= sn			;
		this.sex		= sex		;     	
		this.uage		= uage		;    
		this.dage		= dage		;    
		this.ufatrate	= ufatrate	;
		this.dfatrate	= dfatrate	;	
		this.result		= result		;  
	}
}
