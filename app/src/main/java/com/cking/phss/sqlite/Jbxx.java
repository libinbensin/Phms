package com.cking.phss.sqlite;

public class Jbxx {
	private String mResidentUUID;
	private String mJbxxUUID;
	private String mClassName;
	private String mBean;
    private long downloadDateTime;

    // private String dataSource;
    // private String operFlag;
    // private long uploadDateTime;
    // private long operDateTime;

    public long getDownloadDateTime() {
        return downloadDateTime;
    }

    public void setDownloadDateTime(long downloadDateTime) {
        this.downloadDateTime = downloadDateTime;
    }

    public String getResidentUUID() {
		return mResidentUUID;
	}
	public void setResidentUUID(String mResidentUUID) {
		this.mResidentUUID = mResidentUUID;
	}
	public String getJbxxUUID() {
		return mJbxxUUID;
	}
	public void setJbxxUUID(String mJbxxUUID) {
		this.mJbxxUUID = mJbxxUUID;
	}
	public String getClassName() {
		return mClassName;
	}
	public void setClassName(String mClassName) {
		this.mClassName = mClassName;
	}
	public String getBean() {
		return mBean;
	}
	public void setBean(String mBean) {
		this.mBean = mBean;
	}
	
	
}
