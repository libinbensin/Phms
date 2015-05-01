/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SjcxPage02.java
 * classes : com.cking.phss.page.SjcxPage02
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午8:40:27
 */
package com.cking.phss.page;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.ResourcesFactory.StringItem;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.sjcx.Ryzbsjcx;
import com.cking.phss.dto.sjcx.Ryzbsjcx.Item;
import com.cking.phss.file.FileLog;
import com.cking.phss.net.ISoapRecv;
import com.cking.phss.net.WebService;
import com.cking.phss.util.StringManager;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.xml.util.XmlSerializerUtil;

/**
 * com.cking.phss.page.SjcxPage02
 * 
 */
public class SjglJmcxDetailPage01 extends LinearLayout implements IDetailPage {
    private static final String TAG = "SjcxPage02";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    // 控件
    private Button mBackBtn = null;
    private TextView mNameText;
    private TextView mSexText;
    private TextView mPaperText;

    private TextView contentTextView;

    private boolean[] viesIsShow = new boolean[5];

    class Result {
        public String code = "";
        public String id = "";
        public TextView result = null;

        public Result(String code, String id, TextView result) {
            this.code = code;
            this.id = id;
            this.result = result;
        }
    }

    private Result[] results = null;

    public SjglJmcxDetailPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglJmcxDetailPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_jmcx_detail_01_layout, this);
        mNameText = (TextView) findViewById(R.id.jbxx_name_text);
        mSexText = (TextView) findViewById(R.id.jbxx_sex_text);
        mPaperText = (TextView) findViewById(R.id.jbxx_paper_text);

        contentTextView = (TextView) findViewById(R.id.contentTextView);
    }

    /**
     * 
     * @param checkSv检查序号
     */
    public void require(String checkSv) {
        setJbxx();
        Log.i(TAG, checkSv);
        WebService.getInstance().excute("SearchResultsByCheckinId", "checkinid", checkSv,
                new ISoapRecv() {
                    @Override
                    public void onRecvData(String xmlStr, boolean success) {
                        if (success) {// 如果成功
                            FileLog.i(TAG, xmlStr);
                            xmlStr = WebService.addXmlString(xmlStr);
                            Ryzbsjcx responseRyzbsjcx = (Ryzbsjcx) XmlSerializerUtil.getInstance()
                                    .beanFromXML(Ryzbsjcx.class, xmlStr);
                            if (responseRyzbsjcx == null || responseRyzbsjcx.results == null) {
                                mToast.setText("查询失败！");
                                mToast.show();
                                return;
                            }
                            for (int i = 0; i < viesIsShow.length; i++) {
                                viesIsShow[i] = false;
                            }
                            if (responseRyzbsjcx.results.items == null
                                    || responseRyzbsjcx.results.items.size() <= 0) {
                                mToast.setText("查询没有数据！");
                                mToast.show();
                                return;
                            }
                            updateView(responseRyzbsjcx.results.items);
                        }

                    }
                });
    }

    private void setJbxx() {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        mNameText.setText("姓名：" + jmjbxx.getResidentName());
        mSexText.setText("性别：" + StringManager.getSexbyCD(jmjbxx.getSexCD()));
        mPaperText.setText("身份证号：" + jmjbxx.getPaperNum());
    }

    private void updateView(List<Item> items) {
        String[][] kstjResults = {
 { "01", "010001", "身高", "", "Cm" },
                { "01", "010002", "体重", "", "Kg" }, { "01", "010003", "BMI", "", "" },
                { "01", "010004", "身高体重结论", "", "" }, { "02", "020001", "收缩压", "", "mmHg" },
                { "02", "020002", "舒张压", "", "mmHg" }, { "02", "020003", "脉搏", "", "次/min" },
                { "02", "020004", "平均压", "", "mmHg" }, { "02", "020005", "血压结论", "", "" },
                { "03", "030001", "血糖值", "", "mmol/L" }, { "03", "030002", "血糖结论", "", "" },
                { "03", "030003", "检测类型", "", "" }, { "04", "040001", "SOST(SOST值)", "", "" },
                { "04", "040002", "SOS(声速)", "", "m/s" }, { "04", "040003", "骨密度结论", "", "" },
                { "04", "040004", "TI(骨折风险系数)", "", "" }, { "04", "040005", "OSI(骨强度)", "", "" },
                { "04", "040006", "SOSZ(SOSZ值)", "", "" }, { "04", "040007", "OSIZ值", "", "" },
                { "04", "040008", "OSIT值", "", "" }, { "04", "040009", "TIT值", "", "" },
                { "04", "040010", "TIZ值", "", "" }, { "05", "050001", "心血管功能结论", "", "" },
                { "05", "050002", "每搏心搏量", "", "" }, { "05", "050003", "每分钟心输出量", "", "" },
                { "05", "050004", "心搏指数", "", "" }, { "05", "050005", "心脏指数", "", "" },
                { "05", "050006", "左心室有效泵力", "", "" }, { "05", "050007", "左心能量有效利用率", "", "" },
                { "05", "050008", "心肌耗氧指数", "", "" }, { "05", "050009", "心肌耗氧量", "", "" },
                { "05", "050010", "左心搏功指数", "", "" }, { "05", "050011", "心肌血液功耗率", "", "" },
                { "05", "050012", "心肌血液灌注量", "", "" }, { "05", "050013", "心肌血液需要量", "", "" },
                { "05", "050014", "体外反搏增搏量", "", "" }, { "05", "050015", "体外反搏增搏率", "", "" },
                { "05", "050016", "动脉压差", "", "" }, { "05", "050017", "平均收缩压", "", "" },
                { "05", "050018", "平均舒张压", "", "" }, { "05", "050019", "平均动脉压", "", "" },
                { "05", "050020", "血管顺应度", "", "" }, { "05", "050021", "冠状动脉灌注压", "", "" },
                { "05", "050022", "主动脉排空系数", "", "" }, { "05", "050023", "血管弹性扩张系数", "", "" },
                { "05", "050024", "总周阻", "", "" }, { "05", "050025", "标准周阻", "", "" },
                { "05", "050026", "左室喷血阻抗", "", "" }, { "05", "050027", "肺动脉楔压", "", "" },
                { "05", "050028", "肺血管阻力", "", "" }, { "05", "050029", "肺动脉压", "", "" },
                { "05", "050030", "有效血容量", "", "" }, { "05", "050031", "全血粘度", "", "" },
                { "05", "050032", "还原全血粘度", "", "" }, { "05", "050033", "微循环半更新率", "", "" },
                { "05", "050034", "微循环半更新时间", "", "" }, { "05", "050035", "微循环平均滞留时间", "", "" },
                { "05", "050036", "图片数据", "", "" }, { "06", "060001", "肺活量", "", "L" },
                { "06", "060002", "用力肺活量", "", "L" }, { "06", "060003", "肺功能结论", "", "" },
                { "06", "060004", "一分钟最大呼气量", "", "L" }, { "06", "060005", "气压", "", "hpa" },
                { "06", "060006", "温度", "", "℃" }, { "06", "060007", "湿度", "", "" },
                { "06", "060008", "体表面积", "", "㎡" }, { "06", "060009", "0.5秒用力呼气量", "", "" },
                { "06", "060010", "1秒用力呼气量", "", "" }, { "06", "060011", "3秒用力呼气量", "", "" },
                { "07", "070001", "微循环结论", "", "" }, { "07", "070002", "红细胞聚集", "", "" },
                { "07", "070003", "白细胞数", "", "" }, { "07", "070004", "血管运动性", "", "" },
                { "07", "070005", "白微栓个数", "", "" }, { "07", "070006", "汗腺导管", "", "" },
                { "07", "070007", "形态积分", "", "" }, { "07", "070008", "流态积分", "", "" },
                { "07", "070009", "袢周积分", "", "" }, { "07", "070010", "总积分", "", "" },
                { "07", "070011", "交叉管袢数", "", "" }, { "07", "070012", "清晰度", "", "" },
                { "07", "070013", "管袢数", "", "" }, { "07", "070014", "输入枝管径", "", "" },
                { "07", "070015", "输出枝管径", "", "" }, { "07", "070016", "袢顶直径", "", "" },
                { "07", "070017", "管袢长", "", "" }, { "07", "070018", "畸形管袢数", "", "" },
                { "08", "080001", "健康评估", "", "分" }, { "08", "080002", "健康类型(结论)", "", "" },
                { "08", "080003", "体脂肪量", "", "Kg" }, { "08", "080004", "体脂肪率", "", "%" },
                { "08", "080005", "体水分率", "", "%" }, { "08", "080006", "基础代谢", "", "Kg" },
                { "08", "080007", "脂肪控制", "", "Kg" }, { "08", "080008", "肌肉控制", "", "Kg" },
                { "08", "080009", "体水分含量", "", "Kg" }, { "08", "080010", "蛋白质", "", "Kg" },
                { "08", "080011", "无机盐", "", "Kg" }, { "08", "080012", "腰臀百分比", "", "" },
                { "08", "080013", "体重控制", "", "Kg" }, { "08", "080014", "建议", "", "" },
                { "08", "080015", "目标体重", "", "Kg" }, { "08", "080016", "身高", "", "cm" },
                { "08", "080017", "体重", "", "Kg" }, { "08", "080018", "BMI", "", "" },
                { "08", "080019", "阻抗", "", "Ω" }, { "08", "080020", "相对基础代谢", "", "Kcal" },
                { "08", "080021", "内脏脂肪", "", "级" }, { "08", "080022", "肌肉含量", "", "Kg" },
                { "08", "080023", "骨含量", "", "Kg" }, { "09", "090001", "baPWV(右)", "", "" },
                { "09", "090002", "baPWV(左)", "", "" }, { "09", "090003", "ABI(右)", "", "" },
                { "09", "090004", "ABI(左)", "", "" }, { "09", "090005", "动脉硬化结论", "", "" },
                { "10", "100001", "心电图结论", "", "" }, { "10", "100002", "心率", "", "次/分" },
                { "10", "100003", "P时限", "", "ms" }, { "10", "100004", "PR间期", "", "ms" },
                { "10", "100005", "QRS时限", "", "ms" }, { "10", "100006", "QR间期", "", "ms" },
                { "10", "100007", "Qtc间期", "", "ms" }, { "10", "100008", "RV5振幅", "", "μV" },
                { "10", "100009", "RV6振幅", "", "μV" }, { "10", "100010", "SV1振幅", "", "μV" },
                { "10", "100011", "SV2振幅", "", "μV" }, { "10", "100012", "P电轴", "", "°" },
                { "10", "100013", "T电轴", "", "°" }, { "10", "100014", "P电轴", "", "°" },
                { "11", "110001", "视力结论", "", "" }, { "12", "120001", "体质类型", "", "" },
                { "12", "120002", "体质偏向", "", "" }, { "13", "130001", "舌像仪结论", "", "" },
                { "14", "140001", "得分", "", "" }, { "14", "140002", "测评结果", "", "" },
                { "14", "140003", "建议", "", "" }, { "16", "160001", "甘油三酯值", "", "mmol/L" },
                { "16", "160002", "甘油三酯结论", "", "" }, { "17", "170001", "胆固醇值", "", "mmol/L" },
                { "17", "170002", "胆固醇结论", "", "" }, { "18", "180001", "胸围", "", "cm" },
                { "18", "180002", "腰围", "", "cm" }, { "18", "180003", "臀围", "", "cm" },
                { "18", "180004", "腰臀比", "", "" }, { "18", "180005", "腰围结论", "", "" },
                { "18", "180006", "头围", "", "cm" }, { "18", "180007", "腹围", "", "cm" },
                { "18", "180008", "颈围", "", "cm" }, { "19", "190001", "体温", "", "℃" },
                { "19", "190002", "呼吸频率", "", "" }, { "19", "190003", "体温结论", "", "" },
                { "20", "200001", "高密度值", "", "mmol/L" }, { "20", "200002", "高密度结论", "", "" },
                { "21", "210001", "低密度值", "", "mmol/L" }, { "21", "210002", "低密度结论", "", "" },
                { "22", "220001", "左眼视力(E)", "", "" }, { "22", "220002", "左眼视力结论(E)", "", "" },
                { "22", "220003", "右眼视力(E)", "", "" }, { "22", "220004", "右眼视力结论(E)", "", "" },
                { "22", "220005", "双眼视力(E)", "", "" }, { "22", "220006", "双眼视力结论(E)", "", "" },
                { "22", "220007", "左眼视力(C)", "", "" }, { "22", "220008", "左眼视力结论(C)", "", "" },
                { "22", "220009", "右眼视力(C)", "", "" }, { "22", "220010", "右眼视力结论(C)", "", "" },
                { "22", "220011", "双眼视力(C)", "", "" }, { "22", "220012", "双眼视力结论(C)", "", "" },
                { "22", "220013", "色盲", "", "" }, { "23", "230001", "血清胆红素浓度", "", "μmol/L" },
                { "24", "240001", "尿酸值", "", "mmol/L" }, { "24", "240098", "尿酸结论", "", "" },
                { "24", "240099", "尿酸建议", "", "" }, { "25", "250001", "饱和度", "", "%" },
                { "25", "250002", "脉率", "", "次/分" }, { "25", "250003", "血氧结论", "", "" },
                { "26", "260001", "亚硝酸盐半定量结果", "", "" }, { "26", "260002", "亚硝酸盐结论", "", "" },
                { "26", "260003", "白细胞值", "", "cells/ul" }, { "26", "260004", "白细胞半定量结果", "", "" },
                { "26", "260005", "白细胞结论", "", "" }, { "26", "260006", "尿胆原值", "", "umol/L" },
                { "26", "260007", "尿胆原结论", "", "" }, { "26", "260008", "尿蛋白值", "", "g/L" },
                { "26", "260009", "尿蛋白半定量结果", "", "" }, { "26", "260010", "尿蛋白结论", "", "" },
                { "26", "260011", "潜血值", "", "cells/ul" }, { "26", "260012", "潜血半定量结果", "", "" },
                { "26", "260013", "潜血结论", "", "" }, { "26", "260014", "尿酮体值", "", "mmol/L" },
                { "26", "260015", "尿酮体半定量结果", "", "" }, { "26", "260016", "尿酮体结论", "", "" },
                { "26", "260017", "胆红素值", "", "mg/L" }, { "26", "260018", "胆红素半定量结果", "", "mg/L" },
                { "26", "260019", "胆红素结论", "", "" }, { "26", "260020", "尿糖值", "", "mmol/L" },
                { "26", "260021", "尿糖半定量结果", "", "" }, { "26", "260022", "尿糖结论", "", "" },
                { "26", "260023", "维生素C值", "", "mmol/L" }, { "26", "260024", "维生素半定量结果", "", "" },
                { "26", "260025", "维生素C结论", "", "" }, { "26", "260026", "PH值", "", "" },
                { "26", "260027", "PH结论", "", "" }, { "26", "260028", "尿比重", "", "" },
                { "26", "260029", "尿比重结论", "", "" }, };
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item == null || item.PROJECTCODE.equals(""))
                continue;
            String code = item.PROJECTCODE;
            String id = item.TARGETID;
            String result = item.RESULT;
            String unit = item.UNIT;

            for (int j = 0; j < kstjResults.length; j++) {
                if (code.equals("XDSC" + kstjResults[j][0])
                        && id.equals("XDSC" + kstjResults[j][1])) {// 血压
                    kstjResults[j][3] = result;
                    kstjResults[j][4] = unit;
                }
            }
        }

        List<StringItem> titles = ResourcesFactory.findStringArrayByName(mContext, "tjxm");
        // String[][] titles = new String[][] { { "01", "身高体重" }, { "02", "血压"
        // }, { "03", "血糖" },
        // { "04", "骨密度" }, { "05", "心血管" }, { "06", "肺功能" }, { "07", "微循环" },
        // { "08", "人体成份" }, { "09", "动脉硬化" }, { "10", "心电图" }, { "11", "视力" },
        // { "12", "中医体质" }, { "13", "舌像仪" }, { "14", "心理咨询" }, { "16", "甘油三酯"
        // },
        // { "17", "胆固醇" }, { "18", "三围" }, { "19", "常规检查" }, { "20", "高密度脂蛋白"
        // },
        // { "21", "低密度脂蛋白" }, { "22", "视力" }, { "23", "其他检查" }, { "24", "尿酸" },
        // { "25", "血氧" }, { "26", "体液" } };
        String content = "";
        boolean[] founds = new boolean[titles.size()];
        for (int i = 0; i < founds.length; i++) {
            founds[i] = false;
        }
        Log.i(TAG, "length:" + kstjResults.length);
        String otherStr = ""; // 其他字段；
        for (int i = 0; i < kstjResults.length; i++) {
            if (!StringUtil.isEmptyString(kstjResults[i][3])) {
                String title = getTitle(i, kstjResults, titles, founds);
                if (!StringUtil.isEmptyString(title)) {
                    if (title.equals("其他")) { // 其他放最后统一处理
                        Log.i(TAG, "other string:" + kstjResults[i][2] + ":" + kstjResults[i][3]
                                + kstjResults[i][4]);
                        otherStr += kstjResults[i][2] + ":" + kstjResults[i][3] + kstjResults[i][4];
                        otherStr += getEndChar(otherStr);
                        continue;
                    } else {
                        if (StringUtil.isEmptyString(content) || content.endsWith("\n")) {
                            content += "\n" + title + "\n";
                        } else {
                            content += "\n\n" + title + "\n";
                        }
                    }
                }
                Log.i(TAG, "i:" + i);
                content += kstjResults[i][2] + ":" + kstjResults[i][3] + kstjResults[i][4];
                content += getEndChar(content);
            }
        }
        // 追加其他
        if (!StringUtil.isEmptyString(otherStr)) {
            if (StringUtil.isEmptyString(content) || content.endsWith("\n")) {
                content += "\n" + "其他" + "\n" + otherStr;
            } else {
                content += "\n\n" + "其他" + "\n" + otherStr;
            }
        }
        contentTextView.setText(content);
    }

    /**
     * @param titles
     * @param founds
     * @param string
     * @return
     */
    private String getTitle(int i, String[][] kstjResults, List<StringItem> titles, boolean[] founds) {
        for (int j = 0; j < titles.size(); j++) {
            // if (i == 0) {
            // if (kstjResults[i][0].equals(titles[j][0])) {
            // return titles[j][1];
            // }
            // } else {
            // if (kstjResults[i][0].equals(titles[j][0])
            // && (!kstjResults[i - 1][0].equals(titles[j][0]) && !StringUtil
            // .isEmptyString(kstjResults[i - 1][3]))) {
            // return titles[j][1];
            // }
            // }
            if (kstjResults[i][0].equals(titles.get(j).id)) { // 存在
                if (!founds[j]) { // 第一次找到
                    founds[j] = true;
                    return titles.get(j).value;
                } else {
                    return null;
                }
            }
        }
        return "其他";
    }

    /**
     * @param content
     * @return
     */
    private String getEndChar(String content) {
        String lineText = content.substring(content.lastIndexOf('\n') + 1, content.length());
        String ret = "";
        try {
            int size = lineText.getBytes("GB2312").length;
            if (size < 30) {
                int count = 30 - size;
                while (count-- > 0) {
                    ret += " ";
                }
            } else if (size < 60) {
                int count = 60 - size;
                while (count-- > 0) {
                    ret += " ";
                }
            } else if (size < 90) {
                int count = 90 - size;
                while (count-- > 0) {
                    ret += " ";
                }
            } else {
                ret += "\n";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
