package com.cking.phss.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.cking.phss.sqlite.SqliteField.ResidentField;

public class ResidentDal {

    public static void add(Resident bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + ResidentField.TB_NAME + " ( " + ResidentField.RESIDENT_UUID
                            + ", " + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                                    + ", " + ResidentField.RESIDENT_NAME + ", "
                                    + ResidentField.PAPER_NUM + ", "
                                    + ResidentField.DOWNLOAD_DATETIME + " ) VALUES (?,?,?,?,?,?)",
                    new String[] { bean.getResidentUUID(), bean.getResidentID(), bean.getCardId(),
 bean.getResidentName(), bean.getPaperNum(),
                            String.valueOf(bean.getDownloadDateTime()) });
        }
    }

    public static void update(Resident bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().updateBySql(
                    "UPDATE " + ResidentField.TB_NAME + " SET " + ResidentField.RESIDENT_ID
                            + "=?, " + ResidentField.CARD_ID + "=?, " + ResidentField.RESIDENT_NAME
                            + "=?, " + ResidentField.DOWNLOAD_DATETIME
                            + "=?" + "  WHERE " + ResidentField.PAPER_NUM + "=? ",
                    new String[] { bean.getResidentID(), bean.getCardId(), bean.getResidentName(),
                            String.valueOf(bean.getDownloadDateTime()),
                            bean.getPaperNum() });
        }
    }

    public static Resident query(String paperNum) {
        Cursor cursor = null;
        try {
            Resident bean = new Resident();
            cursor = SqliteOperater.getDbInstance()
                    .queryBySql(
                            "SELECT " + ResidentField.RESIDENT_UUID + ", "
                                    + ResidentField.RESIDENT_NAME + ", "
                                    + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                                    + ", " + ResidentField.PAPER_NUM + ", "
                                    + ResidentField.DOWNLOAD_DATETIME + " FROM "
                                    + ResidentField.TB_NAME + " WHERE " + ResidentField.PAPER_NUM
                                    + "=?", new String[] { paperNum });
            if (cursor != null && cursor.moveToNext()) {
                bean.setResidentUUID(cursor.getString(0));
                bean.setResidentName(cursor.getString(1));
                bean.setResidentID(cursor.getString(2));
                bean.setCardId(cursor.getString(3));
                bean.setPaperNum(cursor.getString(4));
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

    public static boolean existByPaperNum(String paperNum) {
        Cursor cursor = null;
        try {
            Resident bean = new Resident();
            cursor = SqliteOperater.getDbInstance()
                    .queryBySql(
                            "SELECT " + ResidentField.RESIDENT_UUID + ", "
                                    + ResidentField.RESIDENT_NAME + ", "
                                    + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                                    + ", " + ResidentField.PAPER_NUM + ", "
                                    + ResidentField.DOWNLOAD_DATETIME + " FROM "
                                    + ResidentField.TB_NAME + " WHERE " + ResidentField.PAPER_NUM
                                    + "=?", new String[] { paperNum });
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

    public static boolean existByResidentName(String residentName) {
        Cursor cursor = null;
        try {
            Resident bean = new Resident();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + ResidentField.RESIDENT_UUID + ", " + ResidentField.RESIDENT_NAME
                            + ", " + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                            + ", " + ResidentField.PAPER_NUM + ", "
                            + ResidentField.DOWNLOAD_DATETIME + " FROM " + ResidentField.TB_NAME
                            + " WHERE " + ResidentField.RESIDENT_NAME + " LIKE '%" + residentName
                            + "%'", null);
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

    public static boolean existByCardId(String cardId) {
        Cursor cursor = null;
        try {
            Resident bean = new Resident();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + ResidentField.RESIDENT_UUID + ", " + ResidentField.RESIDENT_NAME
                            + ", " + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                            + ", " + ResidentField.PAPER_NUM + ", "
                            + ResidentField.DOWNLOAD_DATETIME + " FROM " + ResidentField.TB_NAME
                            + " WHERE " + ResidentField.CARD_ID + "=?", new String[] { cardId });
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
     * @param searchString
     * @return
     */
    public static List<Resident> queryByPaperNum(String paperNum) {
        Cursor cursor = null;
        List<Resident> beanList = new ArrayList<Resident>();
        try {
            cursor = SqliteOperater.getDbInstance()
                    .queryBySql(
                            "SELECT " + ResidentField.RESIDENT_UUID + ", "
                                    + ResidentField.RESIDENT_NAME + ", "
                                    + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                                    + ", " + ResidentField.PAPER_NUM + ", "
                                    + ResidentField.DOWNLOAD_DATETIME + " FROM "
                                    + ResidentField.TB_NAME + " WHERE " + ResidentField.PAPER_NUM
                                    + "=?", new String[] { paperNum });
            while (cursor != null && cursor.moveToNext()) {
                Resident bean = new Resident();
                bean.setResidentUUID(cursor.getString(0));
                bean.setResidentName(cursor.getString(1));
                bean.setResidentID(cursor.getString(2));
                bean.setCardId(cursor.getString(3));
                bean.setPaperNum(cursor.getString(4));
                bean.setDownloadDateTime(cursor.getLong(5));
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
     * @param searchString
     * @return
     */
    public static List<Resident> queryByResidentName(String residentName) {
        Cursor cursor = null;
        List<Resident> beanList = new ArrayList<Resident>();
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + ResidentField.RESIDENT_UUID + ", " + ResidentField.RESIDENT_NAME
                            + ", " + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                            + ", " + ResidentField.PAPER_NUM + ", "
                            + ResidentField.DOWNLOAD_DATETIME + " FROM " + ResidentField.TB_NAME
                            + " WHERE " + ResidentField.RESIDENT_NAME + " LIKE '%" + residentName
                            + "%'", null);
            if (cursor != null && cursor.moveToNext()) {
                Resident bean = new Resident();
                bean.setResidentUUID(cursor.getString(0));
                bean.setResidentName(cursor.getString(1));
                bean.setResidentID(cursor.getString(2));
                bean.setCardId(cursor.getString(3));
                bean.setPaperNum(cursor.getString(4));
                bean.setDownloadDateTime(cursor.getLong(5));
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
     * @param searchString
     * @return
     */
    public static List<Resident> querytByCardId(String cardId) {
        Cursor cursor = null;
        List<Resident> beanList = new ArrayList<Resident>();
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + ResidentField.RESIDENT_UUID + ", " + ResidentField.RESIDENT_NAME
                            + ", " + ResidentField.RESIDENT_ID + ", " + ResidentField.CARD_ID
                            + ", " + ResidentField.PAPER_NUM + ", "
                            + ResidentField.DOWNLOAD_DATETIME + " FROM " + ResidentField.TB_NAME
                            + " WHERE " + ResidentField.CARD_ID + "=?", new String[] { cardId });
            if (cursor != null && cursor.moveToNext()) {
                Resident bean = new Resident();
                bean.setResidentUUID(cursor.getString(0));
                bean.setResidentName(cursor.getString(1));
                bean.setResidentID(cursor.getString(2));
                bean.setCardId(cursor.getString(3));
                bean.setPaperNum(cursor.getString(4));
                bean.setDownloadDateTime(cursor.getLong(5));
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
