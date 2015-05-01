package com.cking.phss.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cking.phss.util.MyApplication;

/**
 * SQLite 辅助工具类
 * 
 * 
 */
public class SqliteOperater {
    private static SqliteOperater dbInstance = null;
    private static final String DATABASE_PATH = "/mnt/sdcard/phms/record/";///data/data/com.cking.phss/databases/";//数据库在手机里的路径
    private static final String DATABASE_FILE_PATH = DATABASE_PATH + "phss.db";
    /// 数据库版本
    private static int DB_VERSION = 1;
    private SQLiteDatabase queryDatabase = null;
    private SqliteHelper dbHelper;
    public static SqliteOperater getDbInstance() {
        if (dbInstance == null) {
            dbInstance = new SqliteOperater();
        }
        return dbInstance;
    }

    private SqliteOperater() {
        dbHelper = new SqliteHelper(MyApplication.getInstance().getApplicationContext(),
                DATABASE_FILE_PATH, null, DB_VERSION);
        //queryDatabase.execSQL("DROP TABLE IF EXISTS " + AccountField.TB_NAME);
    }

    /**
     * 插入数据
     * 
     * @param tableName
     *            表名
     * @param values
     *            值
     */
    public void insert(String tableName, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.insert(tableName, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据SQL语句插入
     * 
     * @param sql
     *            SQL语句
     */
    public void insertBySql(String sql) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据SQL语句插入
     * 
     * @param sql
     *            SQL语句
     * @param values
     *            值
     */
    public void insertBySql(String sql, String values[]) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try { 
            db.execSQL(sql, values); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新
     * 
     * @param tableName
     *            表名
     * @param values
     *            值
     * @param whereClause
     *            where语句
     * @param whereArgs
     *            where条件值，和？号一一对应
     */
    public void update(String tableName, ContentValues values, String whereClause,
            String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
       
        try {
            db.update(tableName, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据SQL语句来更新
     * 
     * @param sql
     *            SQL语句
     */
    public void updateBySql(String sql) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } 
      
    }

    /**
     * 根据SQL语句来更新
     * 
     * @param sql
     *            SQL语句
     * @param bindArgs
     *            where条件值，和？号一一对应
     */
    public void updateBySql(String sql, String bindArgs[]) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        try {
            db.execSQL(sql, bindArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }

    /**
     * 根据条件查询
     * 
     * @param tableName
     *            表名，必传
     * @param columns
     *            要查的栏目
     * @param selection
     *            where语句 ="?"
     * @param selectionArgs
     *            where语句值，个？号一一对应
     * @param groupBy
     *            分组，如果不用分组则传null
     * @param having
     *            相当于SQL中的having
     * @param orderBy
     *            排序，如果不用排序则传null，例"id desc"
     * @return name number
     */
    public Cursor query(String tableName, String[] columns, String selection,
            String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor c = db.query(tableName, columns, selection, selectionArgs, groupBy, having,
                    orderBy);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据SQL语句查询
     * 
     * @param sql
     *            SQL语句，这里的where条件要以？号占位，例where id=? and name=?
     * @param selectionArgs
     *            where条件后面的类容，替换？号的，跟？号一一对应
     * @return
     */
    public Cursor queryBySql(String sql, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, selectionArgs);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 删除
     * 
     * @param tableName
     *            表名
     * @param where
     *            where语句
     * @param whereArgs
     *            where条件，替换？号，和？号一一对应
     */
    public void delete(String tableName, String where, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(tableName, where, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    /**
     * 根据SQL删除
     * 
     * @param sql
     *            删除的SQL语句
     */
    public void deleteBySql(String sql) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    /**
     * 根据SQL删除
     * 
     * @param sql
     *            删除的SQL语句
     * @param bindArgs
     *            约束条件,和？号一一对应
     */
    public void deleteBySql(String sql, String[] bindArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(sql, bindArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }

    public SQLiteDatabase getQueryDatabase() {
        return this.queryDatabase;
    }

    public void setQueryDatabase(SQLiteDatabase queryDatabase) {
        this.queryDatabase = queryDatabase;
    }
}
