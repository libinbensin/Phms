package com.cking.phss.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cking.phss.sqlite.SqliteField.AccountField;
import com.cking.phss.sqlite.SqliteField.JbxxField;
import com.cking.phss.sqlite.SqliteField.JktjField;
import com.cking.phss.sqlite.SqliteField.LnpgField;
import com.cking.phss.sqlite.SqliteField.ResidentField;
import com.cking.phss.sqlite.SqliteField.SfglField;
import com.cking.phss.sqlite.SqliteField.SjglField;
import com.cking.phss.sqlite.SqliteField.TzpsField;
import com.cking.phss.sqlite.SqliteField.XlcsjgAndTzpsjg;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SqliteHelper";
    public SqliteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + AccountField.TB_NAME + "("
                + AccountField.ACCOUNT_UUID + " char(36) primary key," + AccountField.USERNAME
                + " char(32)," + AccountField.PASSWORD + " char(32)," + AccountField.USERGROUP
                + " short," + AccountField.REMEMBER + " short," + AccountField.STATUS + " short,"
                + JbxxField.BEAN + " text," + AccountField.LAST_LOGIN_DATETIME + " char(255))");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ResidentField.TB_NAME + "("
                + ResidentField.RESIDENT_UUID + " char(36) primary key,"
                + ResidentField.RESIDENT_ID + " char(32)," + ResidentField.CARD_ID + " char(32),"
                + ResidentField.RESIDENT_NAME + " char(32)," + ResidentField.PAPER_NUM
                + " char(18)," + ResidentField.DOWNLOAD_DATETIME + " char(255))");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + JbxxField.TB_NAME + "(" + JbxxField.JBXX_UUID
                + " char(36) primary key," + JbxxField.RESIDENT_UUID + " char(36),"
                + JbxxField.CLASS_NAME + " char(255)," + JbxxField.BEAN + " text,"
                + JbxxField.DOWNLOAD_DATETIME + " char(255))");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + SfglField.TB_NAME + "(" + SfglField.SFGL_UUID
                + " char(36) primary key," + SfglField.RESIDENT_UUID + " char(36),"
                + SfglField.DATETIME + " char(255)," + SfglField.CLASS_NAME + " char(255),"
                + SfglField.BEAN + " text," + SfglField.DOWNLOAD_DATETIME + " char(255))");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + SjglField.TB_NAME + "(" + SjglField.PROJECT_UUID
                + " char(36)," + SjglField.RESIDENT_UUID + " char(36),"
                + SjglField.PROJECT_TYPE + " char(255)," + SjglField.CLASS_NAME + " char(255),"
                + SjglField.BEAN + " text," + SjglField.OPER_DATETIME + " char(255))");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + JktjField.TB_NAME + "(" + JktjField.JKTJ_UUID
                + " char(36) primary key," + JktjField.RESIDENT_UUID + " char(36),"
                + JktjField.DATETIME + " char(255)," + JktjField.CLASS_NAME + " char(255),"
                + JktjField.BEAN + " text," + JktjField.DOWNLOAD_DATETIME + " char(255))");
        /**
         * 心里测试和体质判识的信息
         */
        db.execSQL("CREATE TABLE IF NOT EXISTS " + XlcsjgAndTzpsjg.TB_NAME + "("
                + XlcsjgAndTzpsjg.RESIDENT_UUID + " char(50) primary key,"
                + XlcsjgAndTzpsjg.XLCSJG_SDS + " char(255)," + XlcsjgAndTzpsjg.XLCSJG_SAS
                + " char(255)," + XlcsjgAndTzpsjg.XLCSJG_PSQI + " char(255),"
                + XlcsjgAndTzpsjg.XLCSJG_SAQ + " char(255)," + XlcsjgAndTzpsjg.XLCSJG_UCLA
                + " char(255)," + XlcsjgAndTzpsjg.XLCSJG_GCQ + " char(255),"
                + XlcsjgAndTzpsjg.XLCSJG_SCL90 + " char(255)," + XlcsjgAndTzpsjg.XLCSJG_QLSCA
                + " char(255)," + XlcsjgAndTzpsjg.DATE1 + " char(50)," + XlcsjgAndTzpsjg.DATE2
                + " char(50)," + XlcsjgAndTzpsjg.DATE3 + " char(50)," + XlcsjgAndTzpsjg.DATE4
                + " char(50)," + XlcsjgAndTzpsjg.DATE5 + " char(50)," + XlcsjgAndTzpsjg.DATE6
                + " char(50)," + XlcsjgAndTzpsjg.DATE7 + " char(50)," + XlcsjgAndTzpsjg.DATE8
                + " char(50)," + XlcsjgAndTzpsjg.TZJG1 + " integer," + XlcsjgAndTzpsjg.TZJG2
                + " integer," + XlcsjgAndTzpsjg.UPDATE_DATE + " char(100),"
                + XlcsjgAndTzpsjg.EVALSN + " char(100)" + ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TzpsField.TB_NAME + "("
                + TzpsField.RESIDENT_UUID + " char(36) primary key," + TzpsField.VALUE
                + " char(255))");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + LnpgField.TB_NAME + "("
                + LnpgField.RESIDENT_UUID + " char(36) primary key," + LnpgField.ZILIPG
                + " char(255)," + LnpgField.YIYUPG + " char(255)," + LnpgField.ZHILIPG
                + " char(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AccountField.TB_NAME);
        onCreate(db);
        Log.i(TAG, "onUpgrade");
    }

    public void updateColumn(SQLiteDatabase db, String tableName, String oldColumn,
            String newColumn, String typeColumn) {
        try {
            db.execSQL("ALTER TABLE " + tableName + " CHANGE " + oldColumn + " " + newColumn + " "
                    + typeColumn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
