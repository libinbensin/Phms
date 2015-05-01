package com.cking.phss.sqlite;

public class Sjgl {
	private String mResidentUUID;
    private String mProjectUUID;
    private String projectType;
	private String mClassName;
	private String mBean;
    private long operDateTime;

    public long getOperDateTime() {
        return operDateTime;
    }

    public void setOperDateTime(long downloadDateTime) {
        this.operDateTime = downloadDateTime;
    }
	
	public String getResidentUUID() {
		return mResidentUUID;
	}
	public void setResidentUUID(String mResidentUUID) {
		this.mResidentUUID = mResidentUUID;
	}
    public String getProjectUUID() {
        return mProjectUUID;
	}

    public void setProjectUUID(String mProjectUUID) {
        this.mProjectUUID = mProjectUUID;
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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
	
	
	
}
