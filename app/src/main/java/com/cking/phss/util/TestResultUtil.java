/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * TestResultUtil.java
 * classes : com.cking.phss.util.TestResultUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-19 下午9:34:10
 */
package com.cking.phss.util;

import android.util.Log;

/**
 * com.cking.phss.util.TestResultUtil
 * @author Administrator <br/>
 * create at 2014-6-19 下午9:34:10
 */
public class TestResultUtil {
    private static final String TAG = "TestResultUtil";

    private static final BodyType[] mBodyTypes = new BodyType[]{
			new BodyType(1,2,39,18,45,40,5),
			new BodyType(2,2,39,18,40,36,4),
			new BodyType(3,2,39,18,36,28,3),
			new BodyType(4,2,39,18,28,21,2),
			new BodyType(5,2,39,18,21,5,1),
			new BodyType(6,2,59,40,45,41,5),
			new BodyType(7,2,59,40,41,37,4),
			new BodyType(8,2,59,40,37,29,3),
			new BodyType(9,2,59,40,29,22,2),
			new BodyType(10,2,59,40,22,5,1),
			new BodyType(11,2,200,60,45,42,5),
			new BodyType(12,2,200,60,42,38,4),
			new BodyType(13,2,200,60,38,30,3),
			new BodyType(14,2,200,60,30,23,2),
			new BodyType(15,2,200,60,23,5,1),
			new BodyType(16,1,39,18,45,27,5),
			new BodyType(17,1,39,18,27,23,4),
			new BodyType(18,1,39,18,23,17,3),
			new BodyType(19,1,39,18,17,11,2),
			new BodyType(20,1,39,18,11,5,1),
			new BodyType(21,1,59,40,45,28,5),
			new BodyType(22,1,59,40,28,24,4),
			new BodyType(23,1,59,40,24,18,3),
			new BodyType(24,1,59,40,18,12,2),
			new BodyType(25,1,59,40,12,5,1),
			new BodyType(26,1,200,60,45,30,5),
			new BodyType(27,1,200,60,30,26,4),
			new BodyType(28,1,200,60,26,20,3),
			new BodyType(29,1,200,60,20,14,2),
			new BodyType(30,1,200,60,14,5,1),
			new BodyType(31,1,17,0,45,5,6),
			new BodyType(32,2,17,0,45,5,6),
			new BodyType(33,1,200,0,100,45,7),
			new BodyType(34,2,200,0,100,45,7),
			new BodyType(35,1,200,0,5,0,1),
			new BodyType(36,2,200,0,5,0,1)
	};

    /**
     * 尿酸
     * @param xb
     * @param ns
     * @return
     */
    public static String getNsResult(int xb, float ns) {
        if (xb == 1) { // 男
            if (ns > 0.416) {
                return "尿酸偏多";
            } else if (ns <= 0.416 && ns >= 0.149) {
                return "尿酸正常";
            } else if (ns < 0.149 && ns > 0) {
                return "尿酸偏少";
            } else {
                return "数据错误";
            }
        } else if (xb == 2) {
            if (ns > 0.357) {
                return "尿酸偏多";
            } else if (ns <= 0.357 && ns >= 0.089) {
                return "尿酸正常";
            } else if (ns < 0.089 && ns > 0) {
                return "尿酸偏少";
            } else {
                return "数据错误";
            }
        } else {
            return "此次检测结果需要有正确的性别";
        }
    }
    
    /**
     * 血酮体
     * @param xtt
     * @return
     */
    public static String getXttResult(float xtt) {
        if (xtt>0.50) {
            return "血酮体偏高";
        } else if (xtt >= 0.03 && xtt <= 0.50) {
            return "血酮体正常";
        } else if (xtt > 0 && xtt < 0.03) {
            return "血酮体偏低";
        }
        return "数据错误";
    }

    /**
     * 血氧
     * @param averSpo2
     * @return
     */
    public static String getXyResult(float averSpo2) {
//      0≤xybhd＜90  疑似低氧血症
//      90≤xybhd＜94 供氧不足
//      94≤xybhd≤100    正常

      if (averSpo2 >= 0 && averSpo2 < 90) {
          return "疑似低氧血症";
      } else if (averSpo2 >= 90 && averSpo2 < 94) {
          return "供氧不足";
      } else if (averSpo2 >= 94 && averSpo2 <= 100) {
          return "血氧正常";
      } else {
          return "数据错误";
      }
  }
    
    /**
     * 身高体重
     * @param bmi
     * @return
     */
    public static String getSgtzResult(double bmi) {
        if (bmi >= 0 && bmi < 18.5) {
            return "消瘦";
        } else if (bmi >= 18.5 && bmi < 25.0) {
            return "正常";
        } else if (bmi >= 25.0 && bmi < 28.0) {
            return "超重";
        } else if (bmi >= 28.0 && bmi < 100) {
            return "肥胖";
        }

        return "数据错误";
    }

    /**
     * 腰臀比
     * @param isMale
     * @param fyw
     * @param ftw
     * @return
     */
    public static String getYtbResult(boolean isMale, float fyw, float ftw) {
        float result=fyw/ftw;
        if (isMale) {
            if (result > 0 && result < 0.80) {
                return "消瘦";
            } else if (result < 0.90) {
                return "正常";
            } else if (result < 0.95) {
                return "肥胖";
            }else if ( result < 10) {
                return "重度肥胖";
            }
        } else {
            if (result > 0 && result < 0.75) {
                return "消瘦";
            } else if (result < 0.85) {
                return "正常";
            } else if (result < 0.95) {
                return "肥胖";
            } else if (result < 10) {
                return "重度肥胖";
            }
        }

        return "数据错误";
    }

    /**
     * 甘油三酯
     * @param ftg
     * @return
     */
    public static String getGyszResult(float inData) {
    	String ret = "数据错误";
    	
    	String low = "甘油三酯偏少";
    	String normal = "甘油三酯正常";
    	String high = "甘油三酯偏多";
    	
    	ret = inData < 0?ret:(inData < 0.56 ? low:(inData <=1.70?normal:high));
    	
    	return ret;
    }        

    /**
     * 胆固醇
     * @param fchol
     * @return
     */
    public static String getDgcResult(float inData) {
    	String ret = "数据错误";
    	
    	String low = "胆固醇偏少";
    	String normal = "胆固醇正常";
    	String high = "胆固醇偏多";
    	
    	ret = inData < 0?ret:(inData < 2.82 ? low:(inData <=5.95?normal:high));
    	
    	return ret;
    }
        
    /**
     * @param highZdbValue 高密度脂蛋白的值
     * @return 结论
     */
    public static String getGmdzdbResult(float inData) {
    	String ret = "数据错误";
    	
    	String low = "高密度脂蛋白偏低";
    	String normal = "高密度脂蛋白正常";
    	String high = "高密度脂蛋白偏高";
    	
    	ret = inData < 0?ret:(inData < 1.04 ? low:(inData <=1.55?normal:high));
    	
    	return ret;
    }


    /**
     * @param lowZdbValue 低密度脂蛋白的值
     * @return 结论
     */
    public static String getDmdzdbResult(float inData) {
    	String ret = "数据错误";
    	
    	String low = "低密度脂蛋白偏低";
    	String normal = "低密度脂蛋白正常";
    	String high = "低密度脂蛋白偏高";
    	
    	ret = inData < 0?ret:(inData < 0.90 ? low:(inData <=3.12?normal:high));
    	
    	return ret;
    }
    
    /**
     * 白细胞
     * @param inData
     * @return
     */
    public static int get_BaiXiBao_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	int ret4 = 4;
    	int ret5 = 5;
    	
    	ret = inData < 0?ret:(inData < 15.0 ? ret1:(inData <70.0?ret2:(inData <125.0?ret3:(inData <500.0?ret4:ret5))));
    	
    	return ret;
    }
    
    public static String get_BaiXiBao_Result(int in) {
    	String[] retStr = {
    			"尿白细胞结果正常",	
    			"微量尿白细胞",	
    			"少量尿白细胞",	
    			"中量尿白细胞",	
    			"大量尿白细胞"
    			};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }

    /**
     * 尿胆原
     * @param inData
     * @return
     */
    public static int get_NiaoDanYuan_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	int ret4 = 4;
    	int ret5 = 5;
		int ret6 = 6;
    	
    	ret = inData < 0?ret:(inData < 3.2 ? ret1:(inData <16.0?ret2:(inData <33.0?ret3:(inData <66.0?ret4:(inData <131.0?ret6:ret5)))));
    	
    	return ret;
    }
    
    public static String get_NiaoDanYuan_Result(int in) {
    	String[] retStr = {
		"尿胆原正常",
		"微量尿胆原",
		"少量尿胆原",
		"中量尿胆原",
		"大量尿胆原"};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }

    /**
     * 尿蛋白
     * @param inData
     * @return
     */
    public static int get_NiaoDanBai_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	int ret4 = 4;
    	int ret5 = 5;
    	
    	ret = inData < 0?ret:(inData < 0.15 ? ret1:(inData <0.3?ret2:(inData <1.0?ret3:(inData <3.0?ret4:ret5))));
    	
    	return ret;
    }
    
    public static String get_NiaoDanBai_Result(int in) {
    	String[] retStr = {
			"尿蛋白结果正常",
			"微量尿蛋白",
			"少量尿蛋白",
			"中量尿蛋白",
			"大量尿蛋白"
			};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }

    /**
     * 潜血
     * @param inData
     * @return
     */
    public static int get_QianXue_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	int ret4 = 4;
    	int ret5 = 5;
    	
    	ret = inData < 0?ret:(inData < 10.0 ? ret1:(inData <25.0?ret2:(inData <80.0?ret3:(inData <200.0?ret4:ret5))));
    	
    	return ret;
    }
    
    public static String get_QianXue_Result(int in) {
    	String[] retStr = {
			"尿潜血正常",
			"微量尿潜血",
			"少量尿潜血",
			"中量尿潜血",
			"大量尿潜血"
			};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }

    /**
     * 尿酮体
     * @param inData
     * @return
     */
    public static int get_NiaoTongTi_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	int ret4 = 4;
    	int ret5 = 5;
    	
    	ret = inData < 0?ret:(inData < 0.5 ? ret1:(inData <1.5?ret2:(inData <3.9?ret3:(inData <7.8?ret4:ret5))));
    	
    	return ret;
    }
    
    public static String get_NiaoTongTi_Result(int in) {
    	String[] retStr = {
			"尿酮体正常",
			"微量尿酮体",
			"少量尿酮体",
			"中量尿酮体",
			"大量尿酮体"
			};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }
        
    /**
     * 尿糖
     * @param inData
     * @return
     */
    public static int get_NiaoTang_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	int ret4 = 4;
    	int ret5 = 5;
    	
    	ret = inData < 0?ret:(inData < 5.5 ? ret1:(inData <14.0?ret2:(inData <28.0?ret3:(inData <55.0?ret4:ret5))));
    	
    	return ret;
    }
    
    public static String get_NiaoTang_Result(int in) {
    	String[] retStr = {
			"尿糖正常",
			"微量尿糖",
			"少量尿糖",
			"中量尿糖",
			"大量尿糖"
			};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }

    /**
     * 维生素C
     * @param inData
     * @return
     */
    public static int get_WeiShengSuC_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	int ret4 = 4;
    	int ret5 = 5;
    	
    	ret = inData < 0?ret:(inData < 0.6 ? ret1:(inData <1.4?ret2:(inData <2.8?ret3:(inData <5.0?ret4:ret5))));
    	
    	return ret;
    }
    
    public static String get_WeiShengSuC_Result(int in) {
    	String[] retStr = {
		"维生素C正常",
		"微量维生素C",
		"少量维生素C",
		"中量维生素C",
		"大量维生素C"
		};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }

    /**
     * PH
     * @param inData
     * @return
     */
    public static int get_PH_Data(double inData) {
    	int ret = -1;
    	
    	int ret1 = 1;
    	int ret2 = 2;
    	int ret3 = 3;
    	
    	ret = inData < 0?ret:(inData < 5.0 ? ret1:(inData <=8.5?ret2:ret3));
    	
    	return ret;
    }
    
    public static String get_PH_Result(int in) {
    	String[] retStr = {
		"尿液偏酸性",
		"尿液酸碱性正常",
		"尿液偏碱性",
		};
    	
    	if( in <= retStr.length  &&
    			in >= 1 )
    	{
    		return retStr[in-1];
    	}
    	
    	return "数据错误";
    }


    /**
     * 血糖
     * @param condition
     * @param fxt
     * @return
     */
    public static String getXtResult(long condition, float fxt) {
        switch ((int) condition) {
            case 1: // 随机血糖
                if (fxt <= 0) {
                    return "数据错误";
                } else if (fxt > 0 && fxt <= 11.1) {
                    return "血糖正常";
                }else if (fxt >11.1) {
                    return "血糖偏高";
                }
                break;
        case 2: // 空腹血糖
            if (fxt <= 0) {
                return "数据错误";
            } else if (fxt > 0 && fxt <= 3.89) {
                return "低血糖";
            } else if (fxt > 3.89 && fxt <= 6.11) {
                return "正常";
            } else if (fxt > 6.11 && fxt <= 7) {
                return "血糖偏高";
            } else if (fxt > 7) {
                return "高血糖";
            }
            break;
        case 3: // 餐后2小时
            if (fxt <= 0) {
                return "数据错误";
            } else if (fxt > 0 && fxt <= 3.89) {
                return "低血糖";
            } else if (fxt > 3.89 && fxt <= 7.8) {
                return "正常";
            } else if (fxt > 7.8&& fxt <= 11.1) {
                return "血糖偏高";
            } else if (fxt > 11.1) {
                return "高血糖";
            }
            break;

        default:
            break;
        }
        return "数据错误";
    }

    /**
     * 血氧
     * @param nSystolic
     * @param nDiatolic
     * @return
     */
    public static String getXyResult(int nSystolic, int nDiatolic) {
        if (nSystolic > 0 && nSystolic <= 89) {
            // 0-89 0-59 1 低血压
            // 0-89 60-79 1 低血压
            // 0-89 80-89 1 低血压
            // 0-89 90-99 1 低血压
            // 0-89 100-109 1 低血压
            // 0-89 110-200 1 低血压
            if (nDiatolic > 0 && nDiatolic <= 59) {
                return "低血压";
            } else if (nDiatolic > 59 && nDiatolic <= 79) {
                return "低血压";
            } else if (nDiatolic > 79 && nDiatolic <= 89) {
                return "低血压";
            } else if (nDiatolic > 89 && nDiatolic <= 99) {
                return "低血压";
            } else if (nDiatolic > 99 && nDiatolic <= 109) {
                return "低血压";
            } else if (nDiatolic > 109 && nDiatolic <= 200) {
                return "低血压";
            }
        } else if (nSystolic > 89 && nSystolic <= 119) {
            // 90-119 0-59 1 低血压
            // 90-119 60-79 2 正常血压
            // 90-119 80-89 3 正常高值
            // 90-119 90-99 4 1级高血压【轻度】
            // 90-119 100-109 5 2级高血压【中度】
            // 90-119 110-200 6 3级高血压【高度】
            if (nDiatolic > 0 && nDiatolic <= 59) {
                return "低血压";
            } else if (nDiatolic > 59 && nDiatolic <= 79) {
                return "正常血压";
            } else if (nDiatolic > 79 && nDiatolic <= 89) {
                return "正常高值";
            } else if (nDiatolic > 89 && nDiatolic <= 99) {
                return "1级高血压【轻度】";
            } else if (nDiatolic > 99 && nDiatolic <= 109) {
                return "2级高血压【中度】";
            } else if (nDiatolic > 109 && nDiatolic <= 200) {
                return "3级高血压【高度】";
            }
        } else if (nSystolic > 119 && nSystolic <= 139) {
            // 120-139 0-59 1 低血压
            // 120-139 60-79 3 正常高值
            // 120-139 80-89 3 正常高值
            // 120-139 90-99 4 1级高血压【轻度】
            // 120-139 100-109 5 2级高血压【中度】
            // 120-139 110-200 6 3级高血压【高度】
            if (nDiatolic > 0 && nDiatolic <= 59) {
                return "低血压";
            } else if (nDiatolic > 59 && nDiatolic <= 79) {
                return "正常高值";
            } else if (nDiatolic > 79 && nDiatolic <= 89) {
                return "正常高值";
            } else if (nDiatolic > 89 && nDiatolic <= 99) {
                return "1级高血压【轻度】";
            } else if (nDiatolic > 99 && nDiatolic <= 109) {
                return "2级高血压【中度】";
            } else if (nDiatolic > 109 && nDiatolic <= 200) {
                return "3级高血压【高度】";
            }
        } else if (nSystolic > 139 && nSystolic <= 159) {
            // 140-159 0-59 7 单纯收缩期高血压
            // 140-159 60-79 7 单纯收缩期高血压
            // 140-159 80-89 7 单纯收缩期高血压
            // 140-159 90-99 4 1级高血压【轻度】
            // 140-159 100-109 5 2级高血压【中度】
            // 140-159 110-200 6 3级高血压【高度】
            if (nDiatolic > 0 && nDiatolic <= 59) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 59 && nDiatolic <= 79) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 79 && nDiatolic <= 89) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 89 && nDiatolic <= 99) {
                return "1级高血压【轻度】";
            } else if (nDiatolic > 99 && nDiatolic <= 109) {
                return "2级高血压【中度】";
            } else if (nDiatolic > 109 && nDiatolic <= 200) {
                return "3级高血压【高度】";
            }
        } else if (nSystolic > 159 && nSystolic <= 179) {
            // 160-179 0-59 7 单纯收缩期高血压
            // 160-179 60-79 7 单纯收缩期高血压
            // 160-179 80-89 7 单纯收缩期高血压
            // 160-179 90-99 5 2级高血压【中度】
            // 160-179 100-109 5 2级高血压【中度】
            // 160-179 110-200 6 3级高血压【高度】
            if (nDiatolic > 0 && nDiatolic <= 59) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 59 && nDiatolic <= 79) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 79 && nDiatolic <= 89) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 89 && nDiatolic <= 99) {
                return "2级高血压【中度】";
            } else if (nDiatolic > 99 && nDiatolic <= 109) {
                return "2级高血压【中度】";
            } else if (nDiatolic > 109 && nDiatolic <= 200) {
                return "3级高血压【高度】";
            }
        } else if (nSystolic > 179 && nSystolic <= 300) {
            // 180-300 0-59 7 单纯收缩期高血压
            // 180-300 60-79 7 单纯收缩期高血压
            // 180-300 80-89 7 单纯收缩期高血压
            // 180-300 90-99 6 3级高血压【高度】
            // 180-300 100-109 6 3级高血压【高度】
            // 180-300 110-200 6 3级高血压【高度】
            if (nDiatolic > 0 && nDiatolic <= 59) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 59 && nDiatolic <= 79) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 79 && nDiatolic <= 89) {
                return "单纯收缩期高血压";
            } else if (nDiatolic > 89 && nDiatolic <= 99) {
                return "3级高血压【高度】";
            } else if (nDiatolic > 99 && nDiatolic <= 109) {
                return "3级高血压【高度】";
            } else if (nDiatolic > 109 && nDiatolic <= 200) {
                return "3级高血压【高度】";
            }
        }
        return "数据错误";
    }

    /**
     * @param dgc
     * @return
     */
    public static String getTwResult(float dgc) {
//        [0,35.8)    体温过低
//        [35.8,37.0) 正常体温
//        [37.0,37.6) 体温升高
//        [37.6,38.0) 轻度发烧
//        [38.0,38.6) 中度发烧
//        [38.6,39.5) 重度发烧
//        [39.5,45.0] 极重度发烧
        if (dgc >= 0 && dgc < 35.8) {
            return "体温过低";
        } else if (dgc >= 35.8 && dgc < 37.0) {
            return "正常体温";
        } else if (dgc >= 37.0 && dgc < 37.6) {
            return "体温升高";
        }
        if (dgc >= 37.6 && dgc < 38.0) {
            return "轻度发烧";
        }
        if (dgc >= 38.0 && dgc < 38.6) {
            return "中度发烧";
        }
        if (dgc >= 38.6 && dgc < 39.5) {
            return "重度发烧";
        }
        if (dgc >= 39.5 && dgc < 45.0) {
            return "极重度发烧";
        } else {
            return "数据错误";
        }
    }

    /**
     * @param dgc
     * @return
     */
    public static double getBmiResult(double sg/*m*/, double tz) {
        return tz / (sg * sg);
    }
    
    //K值表
    public static double getK(int sex,double BMI, double IMP) {
    	double K = 0.0;
    	//男性
        if( 1 ==  sex )
        {
        	if( 18.0>BMI )
        	{
        		K = (IMP < 550.0)?1.5:((IMP < 600.0)?2.0:((IMP < 860.0)?2.3:0.0)); 
        	}else if ( 25.0>BMI &&
        			   18.0<=BMI )
        	{
        		K = (IMP < 430.0)?1.5:((IMP < 580.0)?2.0:((IMP < 860.0)?2.3:0.0));
        	}else if ( 25.0<=BMI )
        	{        		
        		K = (IMP < 400.0)?1.5:((IMP < 500.0)?2.0:((IMP < 860.0)?2.3:0.0));
        	}
        }
        //女性
        else if ( 2 == sex )
        {
        	if( 18.0>BMI )
        	{
        		K = (IMP < 500.0)?1.5:((IMP < 700.0)?2.0:((IMP < 860.0)?2.3:0.0)); 
        	}else if ( 25.0>BMI &&
        			   18.0<=BMI )
        	{
        		K = (IMP < 480.0)?1.5:((IMP < 650.0)?2.0:((IMP < 860.0)?2.3:0.0));
        	}else if ( 25.0<=BMI )
        	{        		
        		K = (IMP < 450.0)?1.5:((IMP < 550.0)?2.0:((IMP < 860.0)?2.3:0.0));
        	}        	
        }
        else
        {
        	return 0.0;
        }
        
        return K;
    }
    
    //补偿系数S
    public static double getS(int sex,double BMI, double IMP) {
    	double S = 0.0;
    	
    	Log.i(TAG,"sex = " + sex );
    	Log.i(TAG,"BMI = " + BMI );
    	Log.i(TAG,"IMP = " + IMP );
    	Log.i(TAG,"getK(sex,BMI,IMP) = " + getK(sex,BMI,IMP) );    	
    	
    	if( 860.0 <= IMP )
    	{
    		S = 1.96;
    	}
    	else
    	{
    		S = IMP/500.0*getK(sex,BMI,IMP)-2.0;
    	}
    	
    	Log.i(TAG,"S = " + S );
    	
    	return S;
    }
    
    //T值表
    public static double getT(int sex,double weight) {
        double[][] weights = { { 10, 15 }, { 15, 30 }, { 30, 60 }, { 60, 80 }, { 80, 1000000 } };
        double[][] Ts = { { 83, 79 }, { 77, 74 }, { 68, 65 }, { 64, 55 }, { 64, 55 } };
    	
        if (sex == 0) {
            return 83;
        }
        int i = 0;
        for (double[] ws : weights) {
            if (weight >= ws[0] && weight < ws[1]) {
                return Ts[i][sex - 1];
            }
            i++;
        }
        return Ts[4][sex - 1];
        // double T = 0.0;
        // //男性
        // if( 1 == sex )
        // {
        // T = (weight < 10.0)?0.0:((weight < 16.0)?83.0:((weight <
        // 31.0)?77.0:((weight < 61.0)?68.0:((weight < 80.0)?64.0:0.0))));
        // }
        // //女性
        // else if( 2 == sex )
        // {
        // T = (weight < 10.0)?0.0:((weight < 16.0)?79.0:((weight <
        // 31.0)?74.0:((weight < 61.0)?65.0:((weight < 80.0)?55.0:0.0))));
        // }
        //
        // return T;
    }
    
    //K1
    public static double getK1(int sex,int age) {
    	double mRet = 0.0;
    	
    	//男性
        if( 1 ==  sex )
        {
       		mRet = (age < 24)?2333.0:2333.0; 
        }
        //女性
        else if( 2 ==  sex )
        {
       		mRet = (age < 24)?2300.0:2333.0; 
        }
    	
    	return mRet;
    }    
    
    //K2
    public static double getK2(int sex,int age) {
    	double mRet = 0.0;
    	
    	//男性
        if( 1 ==  sex )
        {
       		mRet = (age < 24)?6142.0:5142.0; 
        }
        //女性
        else if( 2 ==  sex )
        {
       		mRet = (age < 24)?3340.0:1660.0; 
        }
    	
    	return mRet;
    }
    
    //K3
    public static double getK3(int sex,int age) {
    	double mRet = 0.0;
    	
    	//男性
        if( 1 ==  sex )
        {
       		mRet = (age < 24)?232.0:242.0; 
        }
        //女性
        else if( 2 ==  sex )
        {
       		mRet = (age < 24)?220.0:242.0; 
        }
    	
    	return mRet;
    }    
    
    //K4
    public static double getK4(int sex,int age) {
    	double mRet = 0.0;
    	
    	//男性
        if( 1 ==  sex )
        {
       		mRet = (age < 24)?150.0:150.0; 
        }
        //女性
        else if( 2 ==  sex )
        {
       		mRet = (age < 24)?150.0:150.0; 
        }
    	
    	return mRet;
    }
    
    //T1
    public static double getT1(int sex) {
    	double mRet = 0.0;
    	
    	//男性
        if( 1 ==  sex )
        {
       		mRet = 0.052; 
        }
        //女性
        else if( 2 ==  sex )
        {
       		mRet = 0.092; 
        }
    	
    	return mRet;
    }
    
    //T2
    public static double getT2(int sex) {
    	double mRet = 0.0;
    	
    	//男性
        if( 1 ==  sex )
        {
       		mRet = 1.343; 
        }
        //女性
        else if( 2 ==  sex )
        {
       		mRet = 0.57; 
        }
    	
    	return mRet;
    }
    
    //体脂肪率
    public static double getFat(int sex,int age,double height,double weight, double waistline,double IMP) {
    	double mRet = 0.0;
    	double BMI = getBmiResult(height,weight);
    	
    	Log.i(TAG,"sex = " + sex );
    	Log.i(TAG,"age = " + age );
    	Log.i(TAG,"IMP = " + IMP );
    	Log.i(TAG,"getK(sex,BMI,IMP) = " + getK(sex,BMI,IMP) );
  
    	//男性
        if( 1 ==  sex )
        {
       		mRet = -21.954 + 
       				0.375*waistline + 
       				0.422*BMI-
       				0.004937*age+
       				getS(sex,BMI,IMP); 
        }
        //女性
        else if( 2 ==  sex )
        {
        	mRet = -3.082 + 
       				0.119*waistline + 
       				0.933*BMI-
       				0.009328*age+
       				getS(sex,BMI,IMP); 
        }
    	
    	return mRet;
    }
    
    //内脏脂肪
    public static double getVisceralFat(int sex,int age,double BMI,double IMP) {
    	double mRet = 0.0;
      
    	//男性
        if( 1 ==  sex )
        {
       		mRet =  0.758*BMI -
       				105.877*BMI/IMP +
       				0.15*age -
       				9.486;       				 
        }
        //女性
        else if( 2 ==  sex )
        {
       		mRet =  0.533*BMI -
       				50.883*BMI/IMP +
       				0.05*age -
       				6.819; 
        }
    	
    	return mRet;
    }    
    
    //基础代谢
    public static double getBMR(int sex,int age,double height,double weight) {
    	double mRet = 0.0;
      
    	//男性
        if( 1 ==  sex )
        {
       		mRet =  66.0 + 
       				6.23*2.2046226*weight + 
       				12.7*height*100.0*0.39370078740157 -
       				6.8*age;       				       				 
        }
        //女性
        else if( 2 ==  sex )
        {
        	mRet =  655.0 + 
       				4.35*2.2046226*weight + 
       				4.7*height*100.0*0.39370078740157 -
       				4.7*age;
        }
    	
    	return mRet;
    }
    
    //相对基础代谢 RBMR
    public static double getRBMR(double BMR) {
    	double mRet = 0.0;
      
    	mRet = BMR*1.55;
    	
    	return mRet;
    }    
    
    //身体水分含量 TBW
    public static double getTBW(int sex,int age,double height,double weight, double waistline,double IMP) {
    	double mRet = 0.0;

    	mRet = (1000.0 - 10.0*getFat(sex,age,height,weight, waistline,IMP))*getT(sex,weight)/1000.0;
    	
    	return mRet;
    }
    
    //肌肉含量 Mus
    public static double getMus(int sex,int age,double height,double weight, double IMP) {
    	double mRet = 0.0;

/*		temp3 = 10*IMP+补偿值(800);
		temp2 = K1*Height*Height*10000/(temp3)+K2;
		temp1 = (temp2)*10/(10*Weight)+K3-K4*Age/100;
		Mus=(temp1)/10;*/

		double temp3 = 10.0*IMP + 800.0;
		double temp2 = getK1(sex,age) * height * height * 10000.0/temp3 + getK2(sex,age);
		double temp1 = temp2 * 10.0/(10.0*weight) + getK3(sex,age) - getK4(sex,age)*age/100.0;
    	mRet = temp1/10.0;
    	
    	return mRet;
    }    
    
    //骨含量 Bone
    public static double getBone(int sex,int age,double height,double weight, double IMP) {
    	double mRet = 0.0;

    	mRet = getT1(sex) * getMus(sex,age,height,weight,IMP) * weight /100.0 + getT2(sex);
    	
    	return mRet;
    }
    
    public static final String[] mTypesString = new String[] {
		"瘦小型",
		"标准(健康型)",
		"标准(警戒型)",
		"轻度肥胖",
		"重度肥胖",
		"参考型",
		"高度肥胖"
	};
    
    //身体类型
    public static String getBodyType(int sex,int age,double fat) {
    	
    	Log.i(TAG,"sex = " + sex );
    	Log.i(TAG,"age = " + age );
    	Log.i(TAG,"fat = " + fat );
    	
    	Log.i(TAG,"mBodyTypes.length = " + mBodyTypes.length );

    	for(int i = 0;i<mBodyTypes.length;i++)
    	{
    		BodyType tmp = mBodyTypes[i];
    		
    		Log.i(TAG,"1 = " + 1 );
    		
    		if( sex == tmp.sex )
    		{
    			Log.i(TAG,"2 = " + 2 );
    			
    			if( age >= tmp.dage && 
    				age <= tmp.uage )
    			{
    				Log.i(TAG,"3 = " + 3 );
    				
    				if( (fat >= tmp.dfatrate*1.0) &&
    					(fat <= tmp.ufatrate*1.0) )
    				{
    					Log.i(TAG,"4 = " + 4 );
    					
    					return mTypesString[tmp.result-1];
    				}    				
    			}   		
    		}    	
    	}
    	
    	return null;
    }
}
