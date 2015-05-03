package net.mingxing.db.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by MingXing on 2015/5/3.
 * 封装userInfo表
 */
@Table(name = "t_userinfo")
public class UserInfo {

    @Column(column = "id")
    public String id;

    @Column(column = "username")
    public String username;

    @Column(column = "password")
    public String password;

    @Column(column = "remember")
    public String remember;

}
