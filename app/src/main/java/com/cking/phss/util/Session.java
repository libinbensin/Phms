package com.cking.phss.util;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.Login1;
import com.cking.phss.sqlite.Resident;

public class Session {
    Login1 mLoginResult = null;// 记录当前登陆的医生的信息
    Resident mCurrentResident = null;// 记录当前操作的居民的信息
    BeanID manageOrg;// 管理机构
    BeanID station;// 服务
    BeanID buildOrg;

    public BeanID getManageOrg() {
        return manageOrg;
    }

    public void setManageOrg(BeanID manageOrg) {
        this.manageOrg = manageOrg;
    }

    public BeanID getStation() {
        return station;
    }

    public void setStation(BeanID station) {
        this.station = station;
    }

    public BeanID getBuildOrg() {
        return buildOrg;
    }

    public void setBuildOrg(BeanID buildOrg) {
        this.buildOrg = buildOrg;
    }

    public Login1 getLoginResult() {
        return mLoginResult;
    }

    public void setLoginResult(Login1 mLoginResult) {
        this.mLoginResult = mLoginResult;
    }

    public Resident getCurrentResident() {
        return mCurrentResident;
    }

    public void setCurrentResident(Resident mCurrentResident) {
        this.mCurrentResident = mCurrentResident;
    }

}
