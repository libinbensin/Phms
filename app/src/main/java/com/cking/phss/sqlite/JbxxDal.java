package com.cking.phss.sqlite;

import android.database.Cursor;

import com.cking.phss.sqlite.SqliteField.JbxxField;

public class JbxxDal {
    public static void add(Jbxx bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + JbxxField.TB_NAME + " ( " + JbxxField.RESIDENT_UUID + ", "
                            + JbxxField.JBXX_UUID + ", " + JbxxField.CLASS_NAME + ", "
                            + JbxxField.BEAN + ", " + JbxxField.DOWNLOAD_DATETIME
                            + " ) VALUES (?,?,?,?,?)",
                    new String[] { bean.getResidentUUID(), bean.getJbxxUUID(), bean.getClassName(),
                            bean.getBean(), String.valueOf(bean.getDownloadDateTime()) });
        }
    }

    public static void update(Jbxx bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().updateBySql(
                    "UPDATE " + JbxxField.TB_NAME + " SET " + JbxxField.BEAN + "=?, "
                            + JbxxField.DOWNLOAD_DATETIME + "=?  WHERE " + JbxxField.RESIDENT_UUID
                            + "=? AND " + JbxxField.CLASS_NAME + "=?",
                    new String[] { bean.getBean(), String.valueOf(bean.getDownloadDateTime()),
                            bean.getResidentUUID(), bean.getClassName() });
        }
    }

    public static Jbxx query(String residentUUID, String className) {
        Cursor cursor = null;
        try {
            Jbxx bean = new Jbxx();
            cursor = SqliteOperater.getDbInstance()
                    .queryBySql(
                            "SELECT " + JbxxField.RESIDENT_UUID + ", " + JbxxField.JBXX_UUID + ", "
                                    + JbxxField.CLASS_NAME + ", " + JbxxField.BEAN + ", "
                                    + JbxxField.DOWNLOAD_DATETIME + " FROM " + JbxxField.TB_NAME
                                    + " WHERE " + JbxxField.RESIDENT_UUID + "=? AND "
                                    + JbxxField.CLASS_NAME + "=?",
                            new String[] { residentUUID, className });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setJbxxUUID(cursor.getString(1));
                bean.setClassName(cursor.getString(2));
                bean.setBean(cursor.getString(3));
                bean.setDownloadDateTime(cursor.getLong(4));
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

    public static boolean exist(String residentUUID, String className) {
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + JbxxField.RESIDENT_UUID + " FROM " + JbxxField.TB_NAME + " WHERE "
                            + JbxxField.RESIDENT_UUID + "=? AND " + JbxxField.CLASS_NAME + "=?",
                    new String[] { residentUUID, className });
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
     * @param residentUUID
     * @param projectUUID
     * @return
     */
    public static Jbxx query(String className, String residentUUID, String projectUUID) {
        Cursor cursor = null;
        try {
            Jbxx bean = new Jbxx();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + JbxxField.RESIDENT_UUID + ", " + JbxxField.JBXX_UUID + ", "
                            + JbxxField.CLASS_NAME + ", " + JbxxField.BEAN + ", "
                            + JbxxField.DOWNLOAD_DATETIME + " FROM " + JbxxField.TB_NAME
                            + " WHERE " + JbxxField.RESIDENT_UUID + "=? AND "
                            + JbxxField.CLASS_NAME + "=? AND " + JbxxField.JBXX_UUID + "=?",
                    new String[] { residentUUID, className, projectUUID });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setJbxxUUID(cursor.getString(1));
                bean.setClassName(cursor.getString(2));
                bean.setBean(cursor.getString(3));
                bean.setDownloadDateTime(cursor.getLong(4));
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

    /**
     * @param className
     * @param residentUUID
     */
    public static void delete(String className, String residentUUID) {
        SqliteOperater.getDbInstance().updateBySql(
                "DELETE FROM " + JbxxField.TB_NAME + " WHERE " + JbxxField.RESIDENT_UUID
                        + "=? AND " + JbxxField.CLASS_NAME + "=? ",
                new String[] { residentUUID, className });
    }

}