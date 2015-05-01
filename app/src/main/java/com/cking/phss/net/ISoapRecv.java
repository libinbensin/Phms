package com.cking.phss.net;

public interface ISoapRecv {
	/**
	 * 读取数据
	 * @param bean
	 */
	public void onRecvData(String xmlStr, boolean success);
}


