package com.cking.phss.sqlite;

import android.database.Cursor;

import com.cking.phss.sqlite.SqliteField.JktjField;

public class JktjDal {
    public static void add(Jktj bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + JktjField.TB_NAME + " ( " + JktjField.RESIDENT_UUID + ", "
                            + JktjField.JKTJ_UUID + ", " + JktjField.CLASS_NAME + ", "
                            + JktjField.DATETIME + ", " + JktjField.BEAN + ", "
                            + JktjField.DOWNLOAD_DATETIME + " ) VALUES (?,?,?,?,?,?)",
                    new String[] { bean.getResidentUUID(), bean.getJktjUUID(), bean.getClassName(),
                            String.valueOf(bean.getDateTime()), bean.getBean(),
                            String.valueOf(bean.getDownloadDateTime()) });
        }
    }

    public static void update(Jktj bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().updateBySql(
                    "UPDATE " + JktjField.TB_NAME + " SET " + JktjField.BEAN + "=?, "
                            + JktjField.DOWNLOAD_DATETIME + "=?  WHERE " + JktjField.RESIDENT_UUID
                            + "=? AND " + JktjField.CLASS_NAME + "=? AND " + JktjField.DATETIME
                            + "=? ",
                    new String[] { bean.getBean(), String.valueOf(bean.getDownloadDateTime()),
                            bean.getResidentUUID(), bean.getClassName(),
                            String.valueOf(bean.getDateTime()) });
        }
    }

    public static Jktj query(String residentUUID, String className, long dateTime) {
        Cursor cursor = null;
        try {
            Jktj bean = new Jktj();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + JktjField.RESIDENT_UUID + ", " + JktjField.JKTJ_UUID + ", "
                            + JktjField.CLASS_NAME + ", " + JktjField.DATETIME + ", "
                            + JktjField.BEAN + ", " + JktjField.DOWNLOAD_DATETIME + " FROM "
                            + JktjField.TB_NAME + " WHERE " + JktjField.RESIDENT_UUID + "=? AND "
                            + JktjField.CLASS_NAME + "=? AND " + JktjField.DATETIME + "=?",
                    new String[] { residentUUID, className, String.valueOf(dateTime) });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setJktjUUID(cursor.getString(1));
                bean.setClassName(cursor.getString(2));
                bean.setDateTime(cursor.getLong(3));
                bean.setBean(cursor.getString(4));
                bean.setDownloadDateTime(cursor.getLong(5));
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

    public static Jktj queryLast(String residentUUID, String className) {
        Cursor cursor = null;
        try {
            Jktj bean = new Jktj();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + JktjField.RESIDENT_UUID + ", " + JktjField.JKTJ_UUID + ", "
                            + JktjField.CLASS_NAME + ", " + JktjField.DATETIME + ", "
                            + JktjField.BEAN + ", " + JktjField.DOWNLOAD_DATETIME + " FROM "
                            + JktjField.TB_NAME + " WHERE " + JktjField.RESIDENT_UUID + "=? AND "
                            + JktjField.CLASS_NAME + "=?" + " order by " + JktjField.DATETIME
                            + " desc limit 1", new String[] { residentUUID, className });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setJktjUUID(cursor.getString(1));
                bean.setClassName(cursor.getString(2));
                bean.setDateTime(cursor.getLong(3));
                bean.setBean(cursor.getString(4));
                bean.setDownloadDateTime(cursor.getLong(5));
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

    public static boolean exist(String residentUUID, String className, long dateTime) {
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + JktjField.RESIDENT_UUID + " FROM " + JktjField.TB_NAME + " WHERE "
                            + JktjField.RESIDENT_UUID + "=? AND " + JktjField.CLASS_NAME
                            + "=? AND " + JktjField.DATETIME + "=?",
                    new String[] { residentUUID, className, String.valueOf(dateTime) });
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

    public static boolean exist(String sfglUUID) {
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + JktjField.RESIDENT_UUID + " FROM " + JktjField.TB_NAME + " WHERE "
                            + JktjField.JKTJ_UUID + "=? ", new String[] { sfglUUID });
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
    public static Jktj query(String className, String residentUUID, String projectUUID) {
        Cursor cursor = null;
        try {
            Jktj bean = new Jktj();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + JktjField.RESIDENT_UUID + ", " + JktjField.JKTJ_UUID + ", "
                            + JktjField.CLASS_NAME + ", " + JktjField.DATETIME + ", "
                            + JktjField.BEAN + ", " + JktjField.DOWNLOAD_DATETIME + " FROM "
                            + JktjField.TB_NAME + " WHERE " + JktjField.RESIDENT_UUID + "=? AND "
                            + JktjField.CLASS_NAME + "=? AND " + JktjField.JKTJ_UUID + "=?",
                    new String[] { residentUUID, className, projectUUID });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setJktjUUID(cursor.getString(1));
                bean.setClassName(cursor.getString(2));
                bean.setDateTime(cursor.getLong(3));
                bean.setBean(cursor.getString(4));
                bean.setDownloadDateTime(cursor.getLong(5));
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
     * @param projectUUID
     */
    public static void delete(String className, String residentUUID, String projectUUID) {
        SqliteOperater.getDbInstance().updateBySql(
                "DELETE FROM " + JktjField.TB_NAME + " WHERE " + JktjField.RESIDENT_UUID
                        + "=? AND " + JktjField.JKTJ_UUID + "=? AND " + JktjField.CLASS_NAME
                        + "=? ", new String[] { residentUUID, projectUUID, className });
    }

}