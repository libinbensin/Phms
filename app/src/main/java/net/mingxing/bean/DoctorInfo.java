package net.mingxing.bean;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;

import net.mingxing.xml.bean.ZoneID;

import java.util.List;

/**
 * Created by MingXing on 2015/5/3.
 * 封装当前医生信息
 */
public class DoctorInfo {

    public String username;

    public String userID = "";

    public String passWord = "";

    public String doctorID = "";

    public String doctorName = "";

    public BeanCD position = null;

    public BeanID employeeNo = null;

    public BeanCD station = null;

    public BeanCD healthService = null;

    public BeanCD country = null;

    public BeanID province = null;

    public BeanID city = null;

    public String roleCD;

    public String roleName;

    public BeanID district = null;

    public BeanID street = null;

    public BeanID community = null;

    public BeanID road = null;

    public String lane = "";

    public String group = "";

    public String room = "";

    public String other = "";

    public BeanCD communityJurisdiction = null;

    public List<ZoneID> Zones = null;

}
