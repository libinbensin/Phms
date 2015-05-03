package net.mingxing.utils;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import net.mingxing.constant.Constant;
import net.mingxing.db.bean.UserInfo;


/**
 * Created by MingXing on 2015/4/26 0026.
 * 对数据进行操作
 */
public class DButils {

    /**
     * database config
     */
    public static final String DB_DIR = Constant.DB_DIR;
    public static final String PHMS_DB_NAME = Constant.PHMS_DB_NANME;
    public static final String ADDRESS_DB_NAME = Constant.ADDRESS_DB_NANME;
    public static String DB_NAME = null;

    /**
     * database type
     */
    public static int DB_TYPE = -1;
    public static final int ADDRESS_DB = 0;
    public static final int PHMS_DB = 1;

    public static Context mContext;

    public static DbUtils mDbUtils;

    private static DButils mDButils = new DButils();

    public static DButils getInstance(Context context, int dbType) {

        mContext = context;

        if (DB_TYPE != dbType) {

            DB_TYPE = dbType;

            switch (DB_TYPE) {

                case ADDRESS_DB:
                    DB_NAME = ADDRESS_DB_NAME;
                    mDbUtils = DbUtils.create(mContext, DB_DIR, DB_NAME);
                    break;

                case PHMS_DB:
                    DB_NAME = PHMS_DB_NAME;
                    mDbUtils = DbUtils.create(mContext, DB_DIR, DB_NAME);
                    break;
            }
        }
        return mDButils;
    }

    //    =====================================phms.db=============================================

    /**
     * 保存doctor账号信息
     * @param userinfo
     */
    public void saveUserOrUpdateInfo(UserInfo userinfo) {
        try {
            mDbUtils.saveOrUpdate(userinfo);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找记住密码
     */
    public UserInfo getRememberUser() {
        try {
            return mDbUtils.findFirst(Selector.from(UserInfo.class).where("remember", "=", "0").orderBy("id", true).limit(1));
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }


//    ====================================address.db============================================
//    /**
//     * 查询所有的 省
//     */
//    public List<Province> getAllProvinces() {
//
//        try {
//            return mDbUtils.findAll(Province.class);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * 根据省的code 查找 市
//     * @param provinceCode
//     * @return
//     */
//    public List<City> getCityByCode(String provinceCode) {
//        try {
//            return mDbUtils.findAll(Selector.from(City.class).where("ProvinceCode","=",provinceCode));
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 根据市的code 查找 县/区
//     * @return
//     */
//    public List<District> getDistrictByCode(String cityCode) {
//
//        try {
//            return mDbUtils.findAll(Selector.from(District.class).where("CityCode","=",cityCode));
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * 根据县/区code 获取所有的 街道
//     * @return
//     */
//    public List<Street> getStreetByCode(String districtCode) {
//
//        try {
//            return mDbUtils.findAll(Selector.from(Street.class).where("DistrictCode","=",districtCode));
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * 根据街道code 查找 社区
//     * @return
//     */
//    public List<Zone> getZoneByCode(String streetCode) {
//
//        try {
//            return mDbUtils.findAll(Selector.from(Zone.class).where("StreetCode","=",streetCode));
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * 根据社区code 查找 路
//     * @return
//     */
//    public List<Road> getRoadByCode(String zoneCode) {
//
//        try {
//            return mDbUtils.findAll(Selector.from(Road.class).where("ZoneCode","=",zoneCode));
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }


}
