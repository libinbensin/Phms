/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * JkjyResultUtil.java
 * classes : com.cking.phss.util.JkjyResultUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-23 下午5:24:24
 */
package com.cking.phss.util;

import android.util.Log;

/**
 * com.cking.phss.util.JkjyResultUtil
 * @author Administrator <br/>
 * create at 2014-6-23 下午5:24:24
 */
public class JkjyResultUtil {
    private static final String TAG = "JkjyResultUtil";
    // 体脂类型
    private final static int[][] tzlxParams = new int[][] { { 2, 18, 39, 5, 21, 1 },
            { 2, 40, 59, 5, 22, 1 },
            { 2, 60, 200, 5, 23, 1 }, { 1, 18, 39, 5, 11, 1 }, { 1, 40, 59, 5, 12, 1 },
            { 1, 60, 200, 5, 14, 1 }, { 2, 18, 39, 21, 36, 2 }, { 2, 40, 59, 22, 37, 2 },
            { 2, 60, 200, 23, 38, 2 }, { 1, 18, 39, 11, 23, 2 }, { 1, 40, 59, 12, 24, 2 },
            { 1, 60, 200, 14, 26, 2 }, { 2, 18, 39, 36, 100, 3 }, { 2, 40, 59, 37, 100, 3 },
            { 2, 60, 200, 38, 100, 3 }, { 1, 18, 39, 23, 100, 3 }, { 1, 40, 59, 24, 100, 3 },
            { 1, 60, 200, 26, 100, 3 }, { 2, 0, 17, 0, 100, 6 }, { 1, 0, 17, 0, 100, 6 } };

    // 体重类型,BMI被乘10
    private final static int[][] tz2lxParams = new int[][] { { 2, 0, 185, 1 }, { 2, 185, 240, 2 },
            { 2, 240, 1000, 3 }, { 1, 0, 185, 1 }, { 1, 185, 240, 2 }, { 1, 240, 1000, 3 } };

    // 腰臀比类型,BMI被乘100
    private final static int[][] ytblxParams = new int[][] { { 2, 0, 80, 1 }, { 2, 80, 90, 2 },
            { 2, 90, 1000, 3 }, { 1, 0, 75, 1 }, { 1, 75, 85, 2 }, { 1, 85, 1000, 3 }, };

    // 肥胖分析结论
    private final static int[][] fpfxjlParams = new int[][] { { 2, 0, 80, 1 }, { 2, 80, 90, 2 },
            { 1, 1, 1, 1 },
            { 1, 1, 2, 1 }, { 1, 1, 3, 1 }, { 1, 2, 1, 1 }, { 1, 2, 2, 1 }, { 1, 2, 3, 1 },
            { 1, 3, 1, 1 }, { 1, 3, 2, 1 }, { 1, 3, 3, 1 }, { 1, 6, 1, 6 }, { 1, 6, 2, 6 },
            { 1, 6, 3, 6 }, { 2, 1, 1, 2 }, { 2, 1, 2, 2 }, { 2, 1, 3, 2 }, { 2, 2, 1, 2 },
            { 2, 2, 2, 2 }, { 2, 2, 3, 4 }, { 2, 3, 1, 5 }, { 2, 3, 2, 2 }, { 2, 3, 3, 4 },
            { 2, 6, 1, 6 }, { 2, 6, 2, 6 }, { 2, 6, 3, 6 }, { 3, 1, 1, 7 }, { 3, 1, 2, 7 },
            { 3, 1, 3, 3 }, { 3, 2, 1, 3 }, { 3, 2, 2, 3 }, { 3, 2, 3, 4 }, { 3, 3, 1, 5 },
            { 3, 3, 2, 3 }, { 3, 3, 3, 4 }, { 3, 6, 1, 6 }, { 3, 6, 2, 6 }, { 3, 6, 3, 6 } };
    
    /**
     * 体重类型编号 1:男,2:女
     * 
     * @param bmi
     * @return
     */
    public static int getTzlxbhResult(boolean isMale, float bmi) {
        int sex = isMale ? 1 : 2;
        int bmi10 = (int) (bmi * 10);
        Log.i(TAG, "sex = " + sex);
        Log.i(TAG, "bmi10 = " + bmi10);
        for (int[] cell : tz2lxParams) {
            if (cell[0] == sex && bmi10 >= cell[1] && bmi10 < cell[2]) {
                return cell[3];
            }
        }

        return 1;
    }

    /**
     * 腰臀比编号
     * @param isMale
     * @param fyw
     * @param ftw
     * @return
     */
    public static int getYtbhResult(boolean isMale, float fyw, float ftw) {
        float result=fyw/ftw;
        int sex = isMale ? 1 : 2;
        int ytb100 = (int) (result * 100);
        Log.i(TAG, "sex = " + sex);
        Log.i(TAG, "ytb100 = " + ytb100);
        for (int[] cell : ytblxParams) {
            if (cell[0] == sex && ytb100 >= cell[1] && ytb100 < cell[2]) {
                return cell[3];
            }
        }

        return 1;
    }

    /**
     * 体脂类型
     * 
     * @param isMale
     * @param age
     * @param zfl
     * @return
     */
    public static int getTzlxbhResult(boolean isMale, int age, float zfl) {
        int sex = isMale ? 1 : 2;
        int zfl0 = (int) zfl;
        Log.i(TAG, "sex = " + sex);
        Log.i(TAG, "age = " + age);
        Log.i(TAG, "zfl0 = " + zfl0);
        for (int[] cell : tzlxParams) {
            if (cell[0] == sex && age >= cell[1] && age <= cell[2] && zfl0 >= cell[3]
                    && zfl0 < cell[4]) {
                return cell[5];
            }
        }

        return 1;
    }

    public static int getPdjlbhResult(int tzlxbh, int tz2lxbh, int ytblxbh) {
        Log.i(TAG, "tzlxbh = " + tzlxbh);
        Log.i(TAG, "tz2lxbh = " + tz2lxbh);
        Log.i(TAG, "ytblxbh = " + ytblxbh);
        for (int[] cell : fpfxjlParams) {
            if (cell[0] == tzlxbh && tz2lxbh == cell[1] && ytblxbh == cell[2]) {
                return cell[3];
            }
        }

        return 1;
    }
}
