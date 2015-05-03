package com.cking.phss.sqlite;

import android.database.Cursor;

import com.cking.phss.sqlite.SqliteField.AccountField;

import java.util.ArrayList;
import java.util.List;

public class AccountDal {
    public static void add(Account bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + AccountField.TB_NAME + " ( " + AccountField.ACCOUNT_UUID
                            + ", " + AccountField.USERNAME + ", " + AccountField.PASSWORD + ", "
                            + AccountField.USERGROUP + ", " + AccountField.REMEMBER + ", "
                            + AccountField.STATUS + ", " + AccountField.BEAN + ", "
                            + AccountField.LAST_LOGIN_DATETIME + " ) VALUES (?,?,?,?,?,?,?,?)",
                    new String[] { bean.getAccountUUID(), bean.getUsername(), bean.getPassword(),
                            String.valueOf(bean.getUserGroup()),
                            String.valueOf(bean.getRemember()), String.valueOf(bean.getStatus()),
                            bean.getBean(), String.valueOf(bean.getLastLoginDateTime()) });
        }
    }

    public static void update(Account bean) {
        if (bean != null) {
            SqliteOperater.getDbInstance().updateBySql(
                    "UPDATE " + AccountField.TB_NAME + " SET " + AccountField.USERGROUP + "=?, "
                            + AccountField.REMEMBER + "=?, " + AccountField.STATUS + "=?, "
                            + AccountField.BEAN + "=?, " + AccountField.LAST_LOGIN_DATETIME
                            + "=?  WHERE " + AccountField.USERNAME
                            + "=?",
                    new String[] { String.valueOf(bean.getUserGroup()),
                            String.valueOf(bean.getRemember()), String.valueOf(bean.getStatus()),
                            bean.getBean(), String.valueOf(bean.getLastLoginDateTime()),
                            bean.getUsername() });
        }
    }

    public static Account query(String userName) {
        Cursor cursor = null;
        try {
            Account bean = new Account();
            cursor = SqliteOperater.getDbInstance()
                    .queryBySql(
                    "SELECT " + AccountField.ACCOUNT_UUID + ", " + AccountField.USERNAME + ", "
                            + AccountField.PASSWORD + ", " + AccountField.USERGROUP + ", "
                            + AccountField.REMEMBER + ", " + AccountField.STATUS + ", "
                            + AccountField.BEAN + ", " + AccountField.LAST_LOGIN_DATETIME
                            + " FROM " + AccountField.TB_NAME + " WHERE " + AccountField.USERNAME
                            + "=?", new String[] { userName });
            if (cursor != null && cursor.moveToNext()) {
                bean.setAccountUUID(cursor.getString(0));
                bean.setUsername(cursor.getString(1));
                bean.setPassword(cursor.getString(2));
                bean.setUserGroup(cursor.getInt(3));
                bean.setRemember(cursor.getInt(4));
                bean.setStatus(cursor.getInt(5));
                bean.setBean(cursor.getString(6));
                bean.setLastLoginDateTime(cursor.getLong(7));
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

    public static Account query(String fieldName, String fieldValue) {
        Cursor cursor = null;
        try {
            Account bean = new Account();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + AccountField.ACCOUNT_UUID + ", " + AccountField.USERNAME + ", "
                            + AccountField.PASSWORD + ", " + AccountField.USERGROUP + ", "
                            + AccountField.REMEMBER + ", " + AccountField.STATUS + ", "
                            + AccountField.BEAN + ", " + AccountField.LAST_LOGIN_DATETIME
                            + " FROM " + AccountField.TB_NAME + " WHERE " + fieldName + "=?",
                    new String[] { fieldValue });
            if (cursor != null && cursor.moveToNext()) {
                bean.setAccountUUID(cursor.getString(0));
                bean.setUsername(cursor.getString(1));
                bean.setPassword(cursor.getString(2));
                bean.setUserGroup(cursor.getInt(3));
                bean.setRemember(cursor.getInt(4));
                bean.setStatus(cursor.getInt(5));
                bean.setBean(cursor.getString(6));
                bean.setLastLoginDateTime(cursor.getLong(7));
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
    public static boolean exist(String userName) {
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + AccountField.ACCOUNT_UUID + " FROM " + AccountField.TB_NAME
                            + " WHERE " + AccountField.USERNAME + "=?", new String[] { userName });
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

    public static void delete(String userName) {
        SqliteOperater.getDbInstance().updateBySql(
                "DELETE FROM " + AccountField.TB_NAME + " WHERE " + AccountField.USERNAME + "=? ",
                new String[] { userName });
    }

    /**
     * @return
     */
    public static List<Account> list() {
        List<Account> beanList = new ArrayList<Account>();
        Cursor cursor = null;
        try {
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + AccountField.ACCOUNT_UUID + ", " + AccountField.USERNAME + ", "
                            + AccountField.PASSWORD + ", " + AccountField.USERGROUP + ", "
                            + AccountField.REMEMBER + ", " + AccountField.STATUS + ", "
                            + AccountField.BEAN + ", " + AccountField.LAST_LOGIN_DATETIME
                            + " FROM " + AccountField.TB_NAME, null);
            while (cursor != null && cursor.moveToNext()) {
                Account bean = new Account();
                bean.setAccountUUID(cursor.getString(0));
                bean.setUsername(cursor.getString(1));
                bean.setPassword(cursor.getString(2));
                bean.setUserGroup(cursor.getInt(3));
                bean.setRemember(cursor.getInt(4));
                bean.setStatus(cursor.getInt(5));
                bean.setBean(cursor.getString(6));
                bean.setLastLoginDateTime(cursor.getLong(7));
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


    public static void update(String[] setFieldList, String[] setFalueList,
            String[] whereFieldList, String[] whereValueList) {
        String setFiedFragment = "";
        for (int i = 0; i < setFieldList.length; i++) {
            if (i != setFieldList.length - 1) {
                setFiedFragment += setFieldList[i] + "=?, ";
            } else {
                setFiedFragment += setFieldList[i];
            }
        }
        String whereFieldFragment = "";
        for (int i = 0; i < whereFieldList.length; i++) {
            if (i != whereFieldList.length - 1) {
                whereFieldFragment += whereFieldList[i] + "=? AND ";
            } else {
                whereFieldFragment += whereFieldList[i];
            }
        }
        String[] vlues = new String[setFalueList.length + whereValueList.length];
        int n = 0;
        for (String v : setFalueList) {
            vlues[n++] = v;
        }
        for (String v : whereValueList) {
            vlues[n++] = v;
        }
        SqliteOperater.getDbInstance().updateBySql(
                "UPDATE " + AccountField.TB_NAME + " SET " + setFiedFragment + "=?  WHERE "
                        + whereFieldFragment + "=?", vlues);
    }

    public static Account queryLast(String fieldName, String fieldValue) {
        Cursor cursor = null;
        try {
            Account bean = new Account();
            cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT " + AccountField.ACCOUNT_UUID + ", " + AccountField.USERNAME + ", "
                            + AccountField.PASSWORD + ", " + AccountField.USERGROUP + ", "
                            + AccountField.REMEMBER + ", " + AccountField.STATUS + ", "
                            + AccountField.BEAN + ", " + AccountField.LAST_LOGIN_DATETIME
                            + " FROM " + AccountField.TB_NAME + " WHERE " + fieldName + "=?"
                            + " order by " + AccountField.LAST_LOGIN_DATETIME + " desc limit 1",
                    new String[] { fieldValue });
            if (cursor != null && cursor.moveToNext()) {
                bean.setAccountUUID(cursor.getString(0));
                bean.setUsername(cursor.getString(1));
                bean.setPassword(cursor.getString(2));
                bean.setUserGroup(cursor.getInt(3));
                bean.setRemember(cursor.getInt(4));
                bean.setStatus(cursor.getInt(5));
                bean.setBean(cursor.getString(6));
                bean.setLastLoginDateTime(cursor.getLong(7));
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

}