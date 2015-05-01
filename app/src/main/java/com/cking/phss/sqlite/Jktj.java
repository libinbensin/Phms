package com.cking.phss.sqlite;

public class Jktj {
	private String mResidentUUID;
	private long mDateTime;
	private String mJktjUUID;
	private String mClassName;
	private String mBean;
    private long downloadDateTime;

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
	public long getDateTime() {
		return mDateTime;
	}
	public void setDateTime(long mDateTime) {
		this.mDateTime = mDateTime;
	}
	public String getJktjUUID() {
		return mJktjUUID;
	}
	public void setJktjUUID(String mJktjUUID) {
		this.mJktjUUID = mJktjUUID;
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
