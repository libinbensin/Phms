/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SqliteDataController.java
 * classes : com.iaxure.remotecontrol.sqlite.SqliteDataController
 * @author 刘军鹏
 * V 1.0.0
 * Create at 2012-8-25 上午09:44:26
 */
package com.cking.phss.sqlite4address;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

/**
 * com.iaxure.remotecontrol.sqlite.SqliteDataController
 * 
 * @author wation <br/>
 *         create at 2012-8-25 上午09:44:26
 */
public class SqliteDataController {
    private static final String TAG = "SqliteDataController";

    public static List<Address> getProvinceList(String countryId) {
        String findSql = "SELECT * FROM cdprovince WHERE CountryID = '" + countryId + "'";
        List<Address> list = new ArrayList<Address>();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(findSql, null);
        while (cursor != null && cursor.moveToNext()) {
            Address obj = new Address();
            obj.setSn(cursor.getInt(cursor.getColumnIndex("ProvinceSn")));
            obj.setForeignKey(cursor.getString(cursor.getColumnIndex("CountryID")));
            obj.setCode(cursor.getString(cursor.getColumnIndex("ProvinceCode")));
            obj.setValue(cursor.getString(cursor.getColumnIndex("ProvinceName")));
            list.add(obj);
        }
        cursor.close();
        if (SqliteOperater.getDbInstance().getQueryDatabase() != null) {
            SqliteOperater.getDbInstance().getQueryDatabase().close();
            SqliteOperater.getDbInstance().setQueryDatabase(null);
        }

        return list;
    }

    public static List<Address> getCityList(String provinceId) {
        String findSql = "SELECT * FROM cdcity WHERE ProvinceID = '" + provinceId + "'";
        List<Address> list = new ArrayList<Address>();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(findSql, null);
        while (cursor != null && cursor.moveToNext()) {
            Address obj = new Address();
            obj.setSn(cursor.getInt(cursor.getColumnIndex("CitySn")));
            obj.setForeignKey(cursor.getString(cursor.getColumnIndex("ProvinceID")));
            obj.setCode(cursor.getString(cursor.getColumnIndex("CityCode")));
            obj.setValue(cursor.getString(cursor.getColumnIndex("CityName")));
            list.add(obj);
        }
        cursor.close();
        if (SqliteOperater.getDbInstance().getQueryDatabase() != null) {
            SqliteOperater.getDbInstance().getQueryDatabase().close();
            SqliteOperater.getDbInstance().setQueryDatabase(null);
        }

        return list;
    }

    public static List<Address> getDistrictList(String cityId) {
        String findSql = "SELECT * FROM cddistrict WHERE CityID = '" + cityId + "'";
        List<Address> list = new ArrayList<Address>();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(findSql, null);
        while (cursor != null && cursor.moveToNext()) {
            Address obj = new Address();
            obj.setSn(cursor.getInt(cursor.getColumnIndex("DistrictSn")));
            obj.setForeignKey(cursor.getString(cursor.getColumnIndex("CityID")));
            obj.setCode(cursor.getString(cursor.getColumnIndex("DistrictCode")));
            obj.setValue(cursor.getString(cursor.getColumnIndex("DistrictName")));
            list.add(obj);
        }
        cursor.close();
        if (SqliteOperater.getDbInstance().getQueryDatabase() != null) {
            SqliteOperater.getDbInstance().getQueryDatabase().close();
            SqliteOperater.getDbInstance().setQueryDatabase(null);
        }

        return list;
    }
    
    public static List<Address> getStreetList(String districtId) {
        String findSql = "SELECT * FROM cdstreet WHERE DistrictID = '" + districtId + "'";
        List<Address> list = new ArrayList<Address>();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(findSql, null);
        while (cursor != null && cursor.moveToNext()) {
            Address obj = new Address();
            obj.setSn(cursor.getInt(cursor.getColumnIndex("StreetSn")));
            obj.setForeignKey(cursor.getString(cursor.getColumnIndex("DistrictID")));
            obj.setCode(cursor.getString(cursor.getColumnIndex("StreetCode")));
            obj.setValue(cursor.getString(cursor.getColumnIndex("StreetName")));
            list.add(obj);
        }
        cursor.close();
        if (SqliteOperater.getDbInstance().getQueryDatabase() != null) {
            SqliteOperater.getDbInstance().getQueryDatabase().close();
            SqliteOperater.getDbInstance().setQueryDatabase(null);
        }

        return list;
    }
    
    public static List<Address> getCommunityList(String streetId) {
        String findSql = "SELECT * FROM cdcommunity WHERE StreetID = '" + streetId + "'";
        List<Address> list = new ArrayList<Address>();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(findSql, null);
        while (cursor != null && cursor.moveToNext()) {
            Address obj = new Address();
            obj.setSn(cursor.getInt(cursor.getColumnIndex("CommunitySn")));
            obj.setForeignKey(cursor.getString(cursor.getColumnIndex("StreetId")));
            obj.setCode(cursor.getString(cursor.getColumnIndex("CommunityCode")));
            obj.setValue(cursor.getString(cursor.getColumnIndex("CommunityName")));
            list.add(obj);
        }
        cursor.close();
        if (SqliteOperater.getDbInstance().getQueryDatabase() != null) {
            SqliteOperater.getDbInstance().getQueryDatabase().close();
            SqliteOperater.getDbInstance().setQueryDatabase(null);
        }

        return list;
    }
    
    public static List<Address> getRoadList(String communityId) {
        String findSql = "SELECT * FROM cdroad WHERE CommunityID = '" + communityId + "'";
        List<Address> list = new ArrayList<Address>();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(findSql, null);
        while (cursor != null && cursor.moveToNext()) {
            Address obj = new Address();
            obj.setSn(cursor.getInt(cursor.getColumnIndex("RoadSn")));
            obj.setForeignKey(cursor.getString(cursor.getColumnIndex("CommunityId")));
            obj.setCode(cursor.getString(cursor.getColumnIndex("RoadCode")));
            obj.setValue(cursor.getString(cursor.getColumnIndex("RoadName")));
            list.add(obj);
        }
        cursor.close();
        if (SqliteOperater.getDbInstance().getQueryDatabase() != null) {
            SqliteOperater.getDbInstance().getQueryDatabase().close();
            SqliteOperater.getDbInstance().setQueryDatabase(null);
        }

        return list;
    }

    public static List<Icd10> getIcd10List(String tbName) {
        String findSql = "SELECT * FROM " + tbName;
        List<Icd10> list = new ArrayList<Icd10>();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(findSql, null);
//        FileOutputStream fout = null;
//        try {
//            fout = new FileOutputStream(new File("/sdcard/" + tbName + ".xml"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
            while (cursor != null && cursor.moveToNext()) {
                Icd10 obj = new Icd10();
                obj.setIcdTenSn(cursor.getInt(cursor.getColumnIndex("ICDTenSn")));
                obj.setIcdTenCode(cursor.getString(cursor.getColumnIndex("ICDTenCode")));
                obj.setIcdTenName(cursor.getString(cursor.getColumnIndex("ICDTenName")));
                obj.setIcdTenMnemonics(cursor.getString(cursor.getColumnIndex("ICDTenMnemonics")));
                list.add(obj);

//                // 测试代码，写xml
//                {
//                    String text = "<item ID=\"" + obj.getIcdTenCode() + "\" keyword=\""
//                            + obj.getIcdTenMnemonics() + "\">" + obj.getIcdTenName() + "</item>";
//                    fout.write(text.getBytes());
//                }
            }
//            fout.flush();
//            fout.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        cursor.close();
        if (SqliteOperater.getDbInstance().getQueryDatabase() != null) {
            SqliteOperater.getDbInstance().getQueryDatabase().close();
            SqliteOperater.getDbInstance().setQueryDatabase(null);
        }

        return list;
    }
}
