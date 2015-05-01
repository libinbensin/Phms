package com.cking.phss.sqlite;

/**
 * 记录登陆用户的账户信息
 * com.cking.phss.bean.Account
 * @author taowencong <br/>
 * create at 2012-11-14 上午9:45:41
 */
public class Account {
    public final static int ADMIN = 0;
    public final static int CUSTOMER = 1;
    //ID
    private String mAccountUUID = "";
    // 用户名
    private String mUsername = null;
    
    //密码
    private String mPassword = null;

    //是否记住密码
    private int mRemember= 0;

    int userGroup;
    int status;
    private String bean;
    private long lastLoginDateTime;

    public void setAccountUUID(String accountUUID) {
        mAccountUUID = accountUUID;
    }
    
    public void setUsername(String username) {
        mUsername = username;
    }
    
    public void setPassword(String password) {
        mPassword = password;
    }

    public void setRemember(int remember) {
        mRemember = remember;
    }
    
    public String getAccountUUID() {
        return mAccountUUID;
    }
    
    public String getUsername() {
        return mUsername;
    }
    
    public String getPassword() {
        return mPassword;
    }
    
    public int getRemember() {
        return mRemember;
    }

    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public long getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(long lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }
}
