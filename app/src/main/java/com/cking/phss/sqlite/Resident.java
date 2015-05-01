package com.cking.phss.sqlite;

public class Resident {
	private String mResidentUUID;
	private String mResidentID;
	private String mResidentName;
	private String mPaperNum;
    private String mCardId;
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
	public String getResidentID() {
		return mResidentID;
	}
	public void setResidentID(String mResidentID) {
		this.mResidentID = mResidentID;
	}
	public String getResidentName() {
		return mResidentName;
	}
	public void setResidentName(String mResidentName) {
		this.mResidentName = mResidentName;
	}
	public String getPaperNum() {
		return mPaperNum;
	}
	public void setPaperNum(String mPaperNum) {
		this.mPaperNum = mPaperNum;
	}
    public String getCardId() {
        return mCardId;
    }
    public void setCardId(String mCardId) {
        this.mCardId = mCardId;
    }
	
	
	
}
