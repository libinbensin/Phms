package com.cking.phss.page;

import com.cking.phss.bean.IBean;

public interface IPage {
	/**
	 * 从bean中取值，并为控件赋值
	 * @param bean
	 */
	public void setValue();
	/**
	 * 读取控件中的值到bean
	 * @return
	 */	
	public boolean getValue();

    /**
     * 清空
     * 
     * @return
     */
    public void clear();

	public static interface ISaveable{
		public void saveToWeb(IBean bean);
		public void saveToDb(IBean bean);
		
	}
}


