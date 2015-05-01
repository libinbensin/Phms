package com.cking.phss.page;


public interface ISfglPage {
	/**
	 * 随访列表
	 * @return
	 */	
	public void onResultSflb(boolean canDoSf);
    /**
     * 随访列表
     * @return
     */ 
    public void onResultSftj(String should, String done, String notdo);
}


