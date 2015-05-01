package com.cking.phss.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.cking.phss.sqlite.SqliteField.SjglField;

public class SjglDal {
    public static void add(Sjgl bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + SjglField.TB_NAME + " ( " + SjglField.RESIDENT_UUID + ", "
                            + SjglField.PROJECT_UUID + ", " + SjglField.PROJECT_TYPE + ", "
                            + SjglField.CLASS_NAME + ", " + SjglField.BEAN + ", "
                            + SjglField.OPER_DATETIME + " ) VALUES (?,?,?,?,?,?)",
                    new String[] { bean.getResidentUUID(), bean.getProjectUUID(),
                            bean.getProjectType(), bean.getClassName(), bean.getBean(),
                            String.valueOf(bean.getOperDateTime()) });
        }
    }

    public static void update(Sjgl bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().updateBySql(
                    "UPDATE " + SjglField.TB_NAME + " SET " + SjglField.BEAN + "=?, "
                            + SjglField.OPER_DATETIME + "=?  WHERE " + SjglField.RESIDENT_UUID
                            + "=? AND " + SjglField.PROJECT_UUID
                            + "=? AND " + SjglField.CLASS_NAME + "=? AND " + SjglField.PROJECT_TYPE
                            + "=? ",
                    new String[] { bean.getBean(), String.valueOf(bean.getOperDateTime()),
                            bean.getResidentUUID(), bean.getProjectUUID(), bean.getClassName(),
                            bean.getProjectType() });
        }
    }

    public static void delete(String residentUUID, String projectUUID, String projectType,
            String className) {
            SqliteOperater.getDbInstance().updateBySql(
                    "DELETE FROM " + SjglField.TB_NAME + " WHERE " + SjglField.RESIDENT_UUID
                            + "=? AND " + SjglField.PROJECT_UUID + "=? AND " + SjglField.CLASS_NAME
                            + "=? AND " + SjglField.PROJECT_TYPE + "=? ",
                new String[] { residentUUID, projectUUID, className, projectType });
    }

    public static Sjgl query(String residentUUID, String projectType, String className) {
        Cursor cursor = null;
        try {
            Sjgl bean = new Sjgl();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SjglField.RESIDENT_UUID + ", " + SjglField.PROJECT_UUID + ", "
                            + SjglField.PROJECT_TYPE + ", " + SjglField.CLASS_NAME + ", "
                            + SjglField.BEAN + ", " + SjglField.OPER_DATETIME + " FROM "
                            + SjglField.TB_NAME + " WHERE " + SjglField.RESIDENT_UUID + "=? AND "
                            + SjglField.PROJECT_TYPE + "=? AND "
                            + SjglField.CLASS_NAME + "=?",
                    new String[] { residentUUID, projectType, className });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setProjectUUID(cursor.getString(1));
                bean.setProjectType(cursor.getString(2));
                bean.setClassName(cursor.getString(3));
                bean.setBean(cursor.getString(4));
                bean.setOperDateTime(cursor.getLong(5));
                return bean;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return null;
    }

    public static Sjgl query(String residentUUID, String projectUUID, String projectType,
            String className) {
        Cursor cursor = null;
        try {
            Sjgl bean = new Sjgl();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SjglField.RESIDENT_UUID + ", " + SjglField.PROJECT_UUID + ", "
                            + SjglField.PROJECT_TYPE + ", " + SjglField.CLASS_NAME + ", "
                            + SjglField.BEAN + ", " + SjglField.OPER_DATETIME + " FROM "
                            + SjglField.TB_NAME + " WHERE " + SjglField.RESIDENT_UUID + "=? AND "
                            + SjglField.PROJECT_UUID + "=? AND " + SjglField.PROJECT_TYPE
                            + "=? AND " + SjglField.CLASS_NAME + "=?",
                    new String[] { residentUUID, projectUUID, projectType, className });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setProjectUUID(cursor.getString(1));
                bean.setProjectType(cursor.getString(2));
                bean.setClassName(cursor.getString(3));
                bean.setBean(cursor.getString(4));
                bean.setOperDateTime(cursor.getLong(5));
                return bean;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return null;
    }

    public static boolean exist(String residentUUID, String projectUUID, String className) {
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SjglField.RESIDENT_UUID + " FROM " + SjglField.TB_NAME + " WHERE "
                            + SjglField.RESIDENT_UUID + "=? AND " + SjglField.PROJECT_UUID
                            + "=? AND " + SjglField.CLASS_NAME + "=?",
                    new String[] { residentUUID, projectUUID, className });
            if (cursor != null && cursor.moveToNext()) {
                return true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return false;

    }

    public static boolean exist(String shglUUID, String projectType) {
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SjglField.RESIDENT_UUID + " FROM " + SjglField.TB_NAME + " WHERE "
                            + SjglField.PROJECT_UUID + "=? AND " + SjglField.PROJECT_TYPE + "=? ",
                    new String[] { shglUUID, projectType });
            if (cursor != null && cursor.moveToNext()) {
                return true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return false;

    }

    /**
     * @param className
     * @return
     */
    public static List<Sjgl> query(String className) {
        List<Sjgl> beanList = new ArrayList<Sjgl>();
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SjglField.RESIDENT_UUID + ", " + SjglField.PROJECT_UUID + ", "
                            + SjglField.PROJECT_TYPE + ", " + SjglField.CLASS_NAME + ", "
                            + SjglField.BEAN + ", " + SjglField.OPER_DATETIME + " FROM "
                            + SjglField.TB_NAME + " WHERE " + SjglField.CLASS_NAME + "=?",
                    new String[] { className });
            while (cursor != null && cursor.moveToNext()) {
                Sjgl bean = new Sjgl();
                bean.setResidentUUID(cursor.getString(0));
                bean.setProjectUUID(cursor.getString(1));
                bean.setProjectType(cursor.getString(2));
                bean.setClassName(cursor.getString(3));
                bean.setBean(cursor.getString(4));
                bean.setOperDateTime(cursor.getLong(5));
                beanList.add(bean);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return beanList;
    }

    /**
     * @param className
     * @return
     */
    public static List<Sjgl> query(String className, long beginTime, long endTime) {
        List<Sjgl> beanList = new ArrayList<Sjgl>();
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SjglField.RESIDENT_UUID + ", " + SjglField.PROJECT_UUID + ", "
                            + SjglField.PROJECT_TYPE + ", " + SjglField.CLASS_NAME + ", "
                            + SjglField.BEAN + ", " + SjglField.OPER_DATETIME + " FROM "
                            + SjglField.TB_NAME + " WHERE " + SjglField.CLASS_NAME + "='"
                            + className + "' AND " + SjglField.OPER_DATETIME + " >= '" + beginTime
                            + "' AND " + SjglField.OPER_DATETIME + "<= '" + endTime + "'", null);
            while (cursor != null && cursor.moveToNext()) {
                Sjgl bean = new Sjgl();
                bean.setResidentUUID(cursor.getString(0));
                bean.setProjectUUID(cursor.getString(1));
                bean.setProjectType(cursor.getString(2));
                bean.setClassName(cursor.getString(3));
                bean.setBean(cursor.getString(4));
                bean.setOperDateTime(cursor.getLong(5));
                beanList.add(bean);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return beanList;
    }
}