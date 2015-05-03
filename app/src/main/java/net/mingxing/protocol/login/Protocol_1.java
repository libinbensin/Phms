package net.mingxing.protocol.login;


import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import net.mingxing.global.Global;
import net.mingxing.protocol.IProtocol;
import net.mingxing.xml.bean.ZoneID;

import java.util.List;

/**
 * Created by Administrator on 2015/4/21.
 * 登录协议
 */
@XStreamAlias("Body")
public class Protocol_1 implements IProtocol {

    @XStreamAlias("Request")
    public Request request;

    @XStreamAlias("Response")
    public Response response;

    @XStreamOmitField
    private static Protocol_1 mProtocol_1 = new Protocol_1();

    public static Protocol_1 getInstance() {
        return mProtocol_1;
    }

    public static class Request {

        @XStreamAsAttribute
        @XStreamAlias("OrgCode")
        public String orgCode = Global.mHospitalTag.getSerialno();

        @XStreamAsAttribute
        @XStreamAlias("OperType")
        public String operType = "1";

        @XStreamAlias("UserN")
        public String userN = "";

        @XStreamAlias("PassWord")
        public String password = "";
    }

    public static class Response {

        @XStreamAsAttribute()
        @XStreamAlias("ErrMsg")
        public String errMsg = "";

        @XStreamAlias("UserName")
        public String username;

        @XStreamAlias("UserID")
        public String userID = "";

        @XStreamAlias("PassWord")
        public String passWord = "";

        @XStreamAlias("DoctorID")
        public String doctorID = "";

        @XStreamAlias("DoctorName")
        public String doctorName = "";

        @XStreamAlias("Position")
        public BeanCD position = null;

        @XStreamAlias("EmployeeNo")
        public BeanID employeeNo = null;

        @XStreamAlias("Station")
        public BeanCD station = null;

        @XStreamAlias("HealthService")
        public BeanCD healthService = null;

        @XStreamAlias("Country")
        public BeanCD country = null;

        @XStreamAlias("Province")
        public BeanID province = null;

        @XStreamAlias("City")
        public BeanID city = null;

        @XStreamAlias("RoleCD")
        public String roleCD;

        @XStreamAlias("RoleName")
        public String roleName;

        @XStreamAlias("District")
        public BeanID district = null;

        @XStreamAlias("Street")
        public BeanID street = null;

        @XStreamAlias("Community")
        public BeanID community = null;

        @XStreamAlias("Road")
        public BeanID road = null;

        @XStreamAlias("Lane")
        public String lane = "";

        @XStreamAlias("Group")
        public String group = "";

        @XStreamAlias("Room")
        public String room = "";

        @XStreamAlias("Other")
        public String other = "";

        @XStreamAlias("CommunityJurisdiction")
        public BeanCD communityJurisdiction = null;

        @XStreamAlias("Zones")
        public List<ZoneID> Zones = null;

    }

}
