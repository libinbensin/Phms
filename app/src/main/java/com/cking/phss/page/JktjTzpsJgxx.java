package com.cking.phss.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Tzps;
import com.cking.phss.dto.BcxlhetzJ004;
import com.cking.phss.dto.IDto;
import com.cking.phss.sqlite.SqliteField.XlcsjgAndTzpsjg;
import com.cking.phss.sqlite.SqliteOperater;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class JktjTzpsJgxx extends LinearLayout {

    private JktjTzbsPage mParent;
    private Context mContext = null;
    private TextView mTextView = null;
    private TextView mTitleTextView = null;
    private Button mBackChart = null;
    private int[] mRate;
    private Map<String, IBean> beanMap = null;
    // 下面表示要存入数据库的两种体质
    private int tz1 = -1;
    private int tz2 = -1;

    private String tzTitle = "";// 体质结果的标题
    private String tzContent = "";// 体质结果的内容
    private Toast mToast=null;

    public JktjTzpsJgxx(Context context, JktjTzbsPage parent, int[] rate, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        mParent = parent;
        mRate = rate;
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mToast=TispToastFactory.getToast(mContext, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_tzbs_3_layout, this);
        mTextView = (TextView) findViewById(R.id.tzps_jgxx_text);
        mBackChart = (Button) findViewById(R.id.tzps_jgxx_backbutton);
        mTitleTextView = (TextView) findViewById(R.id.jgxx_title_text);
        mBackChart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mParent.showTzpsPage01();
            }
        });
        // 根据传进来的mRate分数数组，计算体质的结论
        setRate();

    }

    // 根据计算的值 ，判断这个人的体质
    // >=60是 60~40 基本是 《40是 平和体质的分
    // 转化分≥40分（是）、转化分30～39分（偏向是）、转化分＜30分（否）其他体质的分
    private void getRank(List<Float> values) {
        int gank = 0;
        tzTitle = Tzps.TZPS_GANK[0];
        tzContent = Tzps.TZPS_JGXX[0];
        // 将其他8中体质分数 分三个段保存
        Map<Integer, Float> valueMap1 = new HashMap<Integer, Float>(); // <30分的段
        Map<Integer, Float> valueMap2 = new HashMap<Integer, Float>(); // 30《分数《39分的段
        Map<Integer, Float> valueMap3 = new HashMap<Integer, Float>(); // 》40分的段
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) < 30) {
                valueMap1.put(i, values.get(i));
            } else if (values.get(i) < 40) {
                valueMap2.put(i, values.get(i));
            } else {
                valueMap3.put(i, values.get(i));
            }
        }

        if (values.get(0) >= 60) {// 平和体质大于60分
            if (valueMap1.size() == 8) {// 其他8种体质都<30，那么就是平和体质
                tzTitle = Tzps.TZPS_GANK[0];
                tzContent = Tzps.TZPS_JGXX[0];
                tz1 = 0;// 平和质
                return;
            }

            // 0 ;1 2 3 4 5 6 7 8
            if (valueMap3.size() == 0 && valueMap1.size() != 8) {// 其他8种体质都<40,并且不是都<30，那么就是基本平和体质，偏向某种体质
                float maxValue = 0;
                for (int i = 1; i < values.size(); i++) {// 判断哪个 偏向体质的分数最高
                    if (valueMap2.containsKey(i)) {
                        if (maxValue < valueMap2.get(i)) {
                            maxValue = valueMap2.get(i);
                            gank = i;
                        }
                    }
                }
                tzTitle = Tzps.JB + Tzps.TZPS_GANK[0] + Tzps.PX + Tzps.TZPS_GANK[gank];
                tzContent = Tzps.TZPS_JGXX[0] + "\n" + Tzps.TZPS_JGXX[gank];
                tz1 = 0;
                tz2 = gank;
                return;// 几 就代表偏向几
            }
        }

        // 如果其他的体质有》40分的情况
        if (valueMap3.size() != 0) {
            if (valueMap3.size() == 1) {// 只有一个偏颇的
                for (int i = 1; i < values.size(); i++) {// 判断哪个 偏向体质的分数最高
                    if (valueMap3.containsKey(i)) {
                        tzTitle = Tzps.TZPS_GANK[i];
                        tzContent = Tzps.TZPS_JGXX[i];
                        tz1 = i;
                        break;
                    }
                }
            } else {// 如果有多个偏颇体质，那么就比较 是什么体质，偏向什么
                    // 现找出最大的值
                float maxValue = 0;
                for (int i = 1; i < values.size(); i++) {// 判断哪个 偏向体质的分数最高
                    if (valueMap3.containsKey(i)) {
                        if (maxValue < valueMap3.get(i)) {
                            maxValue = valueMap3.get(i);
                            gank = i;
                        }
                    }
                }
                tzTitle = Tzps.TZPS_GANK[gank];
                tzContent = Tzps.TZPS_JGXX[gank];
                tz1 = gank;
                // 在找出第二大的值
                valueMap3.remove(gank);
                maxValue = 0;
                for (int i = 1; i < values.size(); i++) {// 判断哪个 偏向体质的分数最高
                    if (valueMap3.containsKey(i)) {
                        if (maxValue < valueMap3.get(i)) {
                            maxValue = valueMap3.get(i);
                            gank = i;
                        }
                    }
                }
                tzTitle += Tzps.PX + Tzps.TZPS_GANK[gank];
                tzContent += Tzps.TZPS_JGXX[gank];
                tz2 = gank;
            }
        }

    }

    public void setRate() {
        List<Float> vaList = Tzps.getGraphicalValues(mRate);
        getRank(vaList);
        // rank=0;//测试
        // getJmjbxxFromDb();//先得到居民基本信息，在把体质信息上传，根据返回结果，在保存到数据库
        showJgxx();
    }

    public void showJgxx() {
        mTitleTextView.setText(tzTitle);
        mTextView.setText(Html.fromHtml(tzContent));
    }

    //将随访序列号和上传时间更新到数据库
    public void saveJgxxToDB(String evalSn,Date lastDate) {
        // 将数据保存或者更新到数据库
        if (MyApplication.getInstance().getSession().getCurrentResident() == null)
            return;
        String resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();

        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE " + XlcsjgAndTzpsjg.RESIDENT_UUID
                        + "=?", new String[] { resident_uuid });
        
        // 在新增插入
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String date = format.format(lastDate);
        if (cursor.moveToNext()) {// 说明有数据存在，那么更新
            // 
            SqliteOperater.getDbInstance().updateBySql("UPDATE "+XlcsjgAndTzpsjg.TB_NAME+" SET "+
                    XlcsjgAndTzpsjg.TZJG1 + "=?," + XlcsjgAndTzpsjg.TZJG2 + "=?,"
                    + XlcsjgAndTzpsjg.UPDATE_DATE + "=?,"+XlcsjgAndTzpsjg.EVALSN+"=?  WHERE " + XlcsjgAndTzpsjg.RESIDENT_UUID + " = ?",
                    new String[]{ tz1 + "", tz2 + "", date,evalSn,resident_uuid});
        }else {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + XlcsjgAndTzpsjg.TB_NAME + "(" + XlcsjgAndTzpsjg.RESIDENT_UUID + ","
                            + XlcsjgAndTzpsjg.TZJG1 + "," + XlcsjgAndTzpsjg.TZJG2 + ","
                            + XlcsjgAndTzpsjg.UPDATE_DATE + ","+XlcsjgAndTzpsjg.EVALSN+ ") VALUES(?,?,?,?,?)",
                    new String[] { resident_uuid, tz1 + "", tz2 + "", date,evalSn });
        }
    }

    public void saveJgxxToWeb(String strCHECKINID) throws ParseException {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null)
            return;

        // 取出随访编号，判断其有效性
        String resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();

        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE " + XlcsjgAndTzpsjg.RESIDENT_UUID
                        + "=?", new String[] { resident_uuid });
        String evalSn = "";// 随访编号的获取
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String dateNow = format.format(new Date());
        String previousDataTime = "";
        if (cursor.moveToNext()) {// 说明有数据存在
            previousDataTime = cursor.getString(cursor.getColumnIndex(XlcsjgAndTzpsjg.UPDATE_DATE));
            if (previousDataTime != null && !previousDataTime.trim().equals("")) {
                int gapTime = 10;
                if (XlcsPage01.dateEnable(gapTime * 60L * 1000, format.parse(dateNow),
                        format.parse(previousDataTime))) {
                    evalSn = cursor.getString(cursor.getColumnIndex(XlcsjgAndTzpsjg.EVALSN));// 如果时间有效，那么取出随访编号
                }
            }
        } 

        // 然后上传数据

        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        BcxlhetzJ004 bcxlhetzJ004 = new BcxlhetzJ004();
        bcxlhetzJ004.request = new BcxlhetzJ004.Request();
        // 判断随访序号的有效性
        if (evalSn.equals("")) {// 新增
            bcxlhetzJ004.request.type = 1;
            bcxlhetzJ004.request.evalSn = strCHECKINID;
        } else {// 编辑
            bcxlhetzJ004.request.type = 2;
            bcxlhetzJ004.request.evalSn = strCHECKINID;
        }
        bcxlhetzJ004.request.residentID = mJmjbxx.getResidentID();
        bcxlhetzJ004.request.userId = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        bcxlhetzJ004.request.evalEmpId = MyApplication.getInstance().getSession().getLoginResult().response.employeeNo
                .getiD();

        bcxlhetzJ004.request.checkDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // 体质辨识的数据查询
        if (tz1 != -1) {
            bcxlhetzJ004.request.tcmType1 = Tzps.TZPS_XXMS[tz1][0];
            bcxlhetzJ004.request.tcmDescribe1 = Tzps.TZPS_XXMS[tz1][1];
            bcxlhetzJ004.request.tcmSuggest1 = Tzps.TZPS_XXMS[tz1][2];
            bcxlhetzJ004.request.tcmKind1 = Tzps.TZPS_XXMS[tz1][3];
        }
        if (tz2 != -1) {
            bcxlhetzJ004.request.tcmType2 = Tzps.TZPS_XXMS[tz2][0];
            bcxlhetzJ004.request.tcmDescribe2 = Tzps.TZPS_XXMS[tz2][1];
            bcxlhetzJ004.request.tcmSuggest2 = Tzps.TZPS_XXMS[tz2][2];
            bcxlhetzJ004.request.tcmKind2 = Tzps.TZPS_XXMS[tz2][3];
        }

        // 开始上传
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(bcxlhetzJ004);
        BeanUtil.getInstance().saveBeanToWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    BcxlhetzJ004 responseBcxlhetzJ004 = (BcxlhetzJ004) listBean.get(0);
                    if (responseBcxlhetzJ004 == null || responseBcxlhetzJ004.response == null) {
                        mToast.setText("【体质辨识】服务器接口异常");
                        mToast.show();
                    } else {
                        if (responseBcxlhetzJ004.response.errMsg.length() > 0) {
                            mToast.setText("【体质辨识】" + responseBcxlhetzJ004.response.errMsg);
                            mToast.show();
                        } else {
                            String evalSn = responseBcxlhetzJ004.response.evalSn;// 更新序列号
                            Date  lastDate = new Date();// 更新最后的得到序列号的时间
                            saveJgxxToDB(evalSn,lastDate);
                            mToast.setText("【体质辨识】上传成功");
                            mToast.show();
                        }
                    }
                }
            }
        });

    }
}
