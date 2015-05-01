package com.cking.phss.sqlite;

import android.database.Cursor;

import com.cking.phss.sqlite.SqliteField.SfglField;

public class SfglDal {
    public static void add(Sfgl bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + SfglField.TB_NAME + " ( " + SfglField.RESIDENT_UUID + ", "
                            + SfglField.SFGL_UUID + ", " + SfglField.CLASS_NAME + ", "
                            + SfglField.DATETIME + ", " + SfglField.BEAN + ", "
                            + SfglField.DOWNLOAD_DATETIME + " ) VALUES (?,?,?,?,?,?)",
                    new String[] { bean.getResidentUUID(), bean.getSfglUUID(), bean.getClassName(),
                            String.valueOf(bean.getDateTime()), bean.getBean(),
                            String.valueOf(bean.getDownloadDateTime()) });
        }
    }

    public static void update(Sfgl bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().updateBySql(
                    "UPDATE " + SfglField.TB_NAME + " SET " + SfglField.BEAN + "=?, "
                            + SfglField.DOWNLOAD_DATETIME + "=?  WHERE " + SfglField.RESIDENT_UUID
                            + "=? AND " + SfglField.CLASS_NAME + "=? AND " + SfglField.DATETIME
                            + "=? ",
                    new String[] { bean.getBean(), String.valueOf(bean.getDownloadDateTime()),
                            bean.getResidentUUID(), bean.getClassName(),
                            String.valueOf(bean.getDateTime()) });
        }
    }

    public static Sfgl query(String residentUUID, String className, long dateTime) {
        Cursor cursor = null;
        try {
            Sfgl bean = new Sfgl();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SfglField.RESIDENT_UUID + ", " + SfglField.SFGL_UUID + ", "
                            + SfglField.CLASS_NAME + ", " + SfglField.DATETIME + ", "
                            + SfglField.BEAN + ", " + SfglField.DOWNLOAD_DATETIME + " FROM "
                            + SfglField.TB_NAME + " WHERE " + SfglField.RESIDENT_UUID + "=? AND "
                            + SfglField.CLASS_NAME + "=? AND " + SfglField.DATETIME + "=?",
                    new String[] { residentUUID, className, String.valueOf(dateTime) });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setSfglUUID(cursor.getString(1));
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

    public static Sfgl queryLast(String residentUUID, String className) {
        Cursor cursor = null;
        try {
            Sfgl bean = new Sfgl();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SfglField.RESIDENT_UUID + ", " + SfglField.SFGL_UUID + ", "
                            + SfglField.CLASS_NAME + ", " + SfglField.DATETIME + ", "
                            + SfglField.BEAN + ", " + SfglField.DOWNLOAD_DATETIME + " FROM "
                            + SfglField.TB_NAME + " WHERE " + SfglField.RESIDENT_UUID + "=? AND "
                            + SfglField.CLASS_NAME + "=?" + " order by " + SfglField.DATETIME
                            + " desc limit 1", new String[] { residentUUID, className });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setSfglUUID(cursor.getString(1));
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

    public static Sfgl queryLastDownload(String residentUUID, String className) {
        Cursor cursor = null;
        try {
            Sfgl bean = new Sfgl();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SfglField.RESIDENT_UUID + ", " + SfglField.SFGL_UUID + ", "
                            + SfglField.CLASS_NAME + ", " + SfglField.DATETIME + ", "
                            + SfglField.BEAN + ", " + SfglField.DOWNLOAD_DATETIME + " FROM "
                            + SfglField.TB_NAME + " WHERE " + SfglField.RESIDENT_UUID + "=? AND "
                            + SfglField.CLASS_NAME + "=?" + " order by "
                            + SfglField.DOWNLOAD_DATETIME + " desc limit 1",
                    new String[] { residentUUID, className });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setSfglUUID(cursor.getString(1));
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
                    "SELECT " + SfglField.RESIDENT_UUID + " FROM " + SfglField.TB_NAME + " WHERE "
                            + SfglField.RESIDENT_UUID + "=? AND " + SfglField.CLASS_NAME
                            + "=? AND " + SfglField.DATETIME + "=?",
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
                    "SELECT " + SfglField.RESIDENT_UUID + " FROM " + SfglField.TB_NAME + " WHERE "
                            + SfglField.SFGL_UUID + "=? ", new String[] { sfglUUID });
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
    public static Sfgl query(String className, String residentUUID, String projectUUID) {
        Cursor cursor = null;
        try {
            Sfgl bean = new Sfgl();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + SfglField.RESIDENT_UUID + ", " + SfglField.SFGL_UUID + ", "
                            + SfglField.CLASS_NAME + ", " + SfglField.DATETIME + ", "
                            + SfglField.BEAN + ", " + SfglField.DOWNLOAD_DATETIME + " FROM "
                            + SfglField.TB_NAME + " WHERE " + SfglField.RESIDENT_UUID + "=? AND "
                            + SfglField.CLASS_NAME + "=? AND " + SfglField.SFGL_UUID + "=?",
                    new String[] { residentUUID, className, projectUUID });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setSfglUUID(cursor.getString(1));
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
                "DELETE FROM " + SfglField.TB_NAME + " WHERE " + SfglField.RESIDENT_UUID
                        + "=? AND " + SfglField.SFGL_UUID + "=? AND " + SfglField.CLASS_NAME
                        + "=? ", new String[] { residentUUID, projectUUID, className });
    }

}