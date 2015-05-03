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
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Xlcs;
import com.cking.phss.dto.BcxlhetzJ004;
import com.cking.phss.dto.IDto;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.SqliteField;
import com.cking.phss.sqlite.SqliteField.XlcsjgAndTzpsjg;
import com.cking.phss.sqlite.SqliteOperater;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class XlcsPage01 extends LinearLayout {
    private Context mContext = null;
    private Toast mToast = null;
    private JktjXlpgPage mParent;
    ImageView mYyButton = null;
    ImageView mJlButton = null;
    ImageView mPzButton = null;
    ImageView mZsButton = null;
    ImageView mUclaButton = null;
    ImageView mSsButton = null;
    ImageView mScl90Button = null;
    ImageView mQlscaButton = null;

    TextView mYyTextView = null;
    TextView mJlTextView = null;
    TextView mPzTextView = null;
    TextView mZsTextView = null;
    TextView mUclaTextView = null;
    TextView mSsTextView = null;
    TextView mScl90TextView = null;
    TextView mQlscaTextView = null;
    public TextView[] resultTextViews = null;
//    Button mUploadButton = null;
    private int mSelectIndex = 0;

    private Map<String, IBean> beanMap = null;

    public static int age=-1;//用于在计算少年儿童质量量表的数据
    public static int sex=-1;//用于在计算少年儿童质量量表的数据
    /**
     * @param context
     */
    public XlcsPage01(Context context, JktjXlpgPage parent) {
        super(context);
        mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XlcsPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_xlpg_1_layout, this);
        beanMap = new HashMap<String, IBean>();
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        
        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {

        mYyButton = (ImageView) findViewById(R.id.xlcs_yy_button);
        mJlButton = (ImageView) findViewById(R.id.xlcs_jl_button);
        mPzButton = (ImageView) findViewById(R.id.xlcs_pz_button);
        mZsButton = (ImageView) findViewById(R.id.xlcs_zs_button);
        mUclaButton = (ImageView) findViewById(R.id.xlcs_ucla_button);
        mSsButton = (ImageView) findViewById(R.id.xlcs_ss_button);
        mScl90Button = (ImageView) findViewById(R.id.xlcs_scl90_button);
        mQlscaButton = (ImageView) findViewById(R.id.xlcs_qlsca_button);

        mYyTextView = (TextView) findViewById(R.id.xlcs_yy_text);
        mJlTextView = (TextView) findViewById(R.id.xlcs_jl_text);
        mPzTextView = (TextView) findViewById(R.id.xlcs_pz_text);
        mZsTextView = (TextView) findViewById(R.id.xlcs_zs_text);
        mUclaTextView = (TextView) findViewById(R.id.xlcs_ucla_text);
        mSsTextView = (TextView) findViewById(R.id.xlcs_ss_text);
        mScl90TextView = (TextView) findViewById(R.id.xlcs_scl90_text);
        mQlscaTextView = (TextView) findViewById(R.id.xlcs_qlsca_text);
        resultTextViews = new TextView[] { mYyTextView, mJlTextView, mPzTextView, mZsTextView,
                mUclaTextView, mSsTextView, mScl90TextView, mQlscaTextView, };
//        mUploadButton = (Button) findViewById(R.id.uploadButton);

        mYyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mParent.showXlcsTestPage(Xlcs.YY);
            }
        });
        mJlButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mParent.showXlcsTestPage(Xlcs.JL);
            }
        });
        mPzButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mParent.showXlcsTestPage(Xlcs.PZ);
            }
        });
        mZsButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mParent.showXlcsTestPage(Xlcs.ZS);
            }
        });
        mUclaButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mParent.showXlcsTestPage(Xlcs.UCLA);
            }
        });
        mSsButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mParent.showXlcsTestPage(Xlcs.SS);
            }
        });

        mScl90Button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mParent.showXlcsTestPage(Xlcs.SCL90);
            }
        });
        
        mQlscaButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getJmjbxxFromDb(2);
            }
        });
        
        
        
//        mUploadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getJmjbxxFromDb(1);
//            }
//        });
    }
    
    // 上传体质辨识和信息测试的数据
    public void uploadXlcsToWeb() throws ParseException {
        // 判断居民存不存在
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            mToast.setText("居民信息不存在，请先登记");
            mToast.show();
            return;
        }

        // 取出随访编号，判断其有效性
        String resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE "
                        + XlcsjgAndTzpsjg.RESIDENT_UUID + "=?", new String[] { resident_uuid });
        String evalSn = "";// 随访编号的获取
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String dateNow = format.format(new Date());
        String previousDataTime = "";
        if (cursor.moveToNext()) {// 说明有数据存在
            previousDataTime = cursor.getString(cursor.getColumnIndex(XlcsjgAndTzpsjg.UPDATE_DATE));
            if (previousDataTime != null && !previousDataTime.trim().equals("")) {
                int gapTime = 10;// 得到允许的间隔时间
                if (XlcsPage01.dateEnable(gapTime * 60L * 1000, format.parse(dateNow),
                        format.parse(previousDataTime))) {
                    evalSn = cursor.getString(cursor.getColumnIndex(XlcsjgAndTzpsjg.EVALSN));// 如果时间有效，那么取出随访编号
                }
            }
        }

        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            return;
        }

        BcxlhetzJ004 bcxlhetzJ004 = new BcxlhetzJ004();
        bcxlhetzJ004.request = new BcxlhetzJ004.Request();
        // 判断随访序号的有效性
        if (evalSn.equals("")) {// 新增
            bcxlhetzJ004.request.type = 1;
        } else {// 编辑
            bcxlhetzJ004.request.type = 2;
            bcxlhetzJ004.request.evalSn = evalSn;
        }
        bcxlhetzJ004.request.residentID = mJmjbxx.getResidentID();
        bcxlhetzJ004.request.userId = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        bcxlhetzJ004.request.evalEmpId = MyApplication.getInstance().getSession().getLoginResult().response.employeeNo
                .getiD();
        bcxlhetzJ004.request.checkDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String xlcsSummary = "";
        String xlcsSuggestion = "";

        // 心理测试的数据查询
        Cursor xlcsCursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE "
                        + XlcsjgAndTzpsjg.RESIDENT_UUID + "=?", new String[] { resident_uuid });
        if (xlcsCursor.moveToNext()) {// 说明有数据存在，那么更新
            // 检索数据集
            /*// 第一个数据 "抑郁自评量表(SDS)", //抑郁自评量表
            String date1String = xlcsCursor.getString(xlcsCursor
                    .getColumnIndex(XlcsjgAndTzpsjg.DATE1));
            if (date1String != null && !date1String.equals("")) {
                Date date1 = format.parse(date1String);
                if (dateEnable(30 * 60 * 1000, format.parse(dateNow), date1)) {
                    String XLCSJG_SAS = xlcsCursor.getString(xlcsCursor
                            .getColumnIndex(XlcsjgAndTzpsjg.XLCSJG_SDS));
                    String[] jgxx = XLCSJG_SAS.split("_");
                    xlcsSummary += (Xlcs.CSMCS[0] + ":" + jgxx[0] + ";");// 结论
                    xlcsSuggestion += (Xlcs.CSMCS[0] + ":" + jgxx[1] + ";");// 建议
                }
            }
            // 第二个数据 "焦虑自评量表(SAS)",//焦虑自评量表
            String date2String = xlcsCursor.getString(xlcsCursor
                    .getColumnIndex(XlcsjgAndTzpsjg.DATE2));
            if (date2String != null && !date2String.equals("")) {
                Date date2 = format.parse(date2String);
                if (dateEnable(30 * 60 * 1000, format.parse(dateNow), date2)) {
                    String XLCSJG_SAS = xlcsCursor.getString(xlcsCursor
                            .getColumnIndex(XlcsjgAndTzpsjg.XLCSJG_SAS));
                    String[] jgxx = XLCSJG_SAS.split("_");
                    xlcsSummary += (Xlcs.CSMCS[1] + ":" + jgxx[0] + ";");// 结论
                    xlcsSuggestion += (Xlcs.CSMCS[1] + ":" + jgxx[1] + ";");// 建议
                }
            }

            // 第三个数据 "匹兹堡睡眠质量指数(PSQI)",//医院焦虑抑郁量表(HAD)
            String date3String = xlcsCursor.getString(xlcsCursor
                    .getColumnIndex(XlcsjgAndTzpsjg.DATE3));
            if (date3String != null && !date3String.equals("")) {
                Date date3 = format.parse(date3String);
                if (dateEnable(30 * 60 * 1000, format.parse(dateNow), date3)) {
                    String XLCSJG_PSQI = xlcsCursor.getString(xlcsCursor
                            .getColumnIndex(XlcsjgAndTzpsjg.XLCSJG_PSQI));
                    String[] jgxx = XLCSJG_PSQI.split("_");
                    xlcsSummary += (Xlcs.CSMCS[2] + ":" + jgxx[0] + ";");// 结论
                    xlcsSuggestion += (Xlcs.CSMCS[2] + ":" + jgxx[1] + ";");// 建议
                }
            }

            // 第四个数据 "自杀态度问卷(SAQ)",//抑郁体验问卷(DEQ)
            String date4String = xlcsCursor.getString(xlcsCursor
                    .getColumnIndex(XlcsjgAndTzpsjg.DATE4));
            if (date4String != null && !date4String.equals("")) {
                Date date4 = format.parse(date4String);
                if (dateEnable(30 * 60 * 1000, format.parse(dateNow), date4)) {
                    String XLCSJG_SAQ = xlcsCursor.getString(xlcsCursor
                            .getColumnIndex(XlcsjgAndTzpsjg.XLCSJG_SAQ));
                    String[] jgxx = XLCSJG_SAQ.split("_");
                    xlcsSummary += (Xlcs.CSMCS[3] + ":" + jgxx[0] + ";");// 结论
                    xlcsSuggestion += (Xlcs.CSMCS[3] + ":" + jgxx[1] + ";");// 建议
                }
            }

            // 第五个数据 "UCLA孤独量表",//流调用抑郁自评量表(CES-D)
            String date5String = xlcsCursor.getString(xlcsCursor
                    .getColumnIndex(XlcsjgAndTzpsjg.DATE5));
            if (date5String != null && !date5String.equals("")) {
                Date date5 = format.parse(date5String);
                if (dateEnable(30 * 60 * 1000, format.parse(dateNow), date5)) {
                    String XLCSJG_UCLA = xlcsCursor.getString(xlcsCursor
                            .getColumnIndex(XlcsjgAndTzpsjg.XLCSJG_UCLA));
                    String[] jgxx = XLCSJG_UCLA.split("_");
                    xlcsSummary += (Xlcs.CSMCS[4] + ":" + jgxx[0] + ";");// 结论
                    xlcsSuggestion += (Xlcs.CSMCS[4] + ":" + jgxx[1] + ";");// 建议
                }
            }

            // 第六个数据 "舒适状况量表(GCQ)"//贝克抑郁自评量表(BDI)
            String date6String = xlcsCursor.getString(xlcsCursor
                    .getColumnIndex(XlcsjgAndTzpsjg.DATE6));
            if (date6String != null && !date6String.equals("")) {
                Date date6 = format.parse(date6String);
                if (dateEnable(30 * 60 * 1000, format.parse(dateNow), date6)) {
                    String XLCSJG_GCQ = xlcsCursor.getString(xlcsCursor
                            .getColumnIndex(XlcsjgAndTzpsjg.XLCSJG_GCQ));
                    String[] jgxx = XLCSJG_GCQ.split("_");
                    xlcsSummary += (Xlcs.CSMCS[5] + ":" + jgxx[0] + ";");// 结论
                    xlcsSuggestion += (Xlcs.CSMCS[5] + ":" + jgxx[1] + ";");// 建议
                }
            }*/

            for(int i=0;i<SqliteField.XlcsjgAndTzpsjgFields.length;i++){
                String dateString = xlcsCursor.getString(xlcsCursor
                        .getColumnIndex(SqliteField.XlcsjgAndTzpsjgFields[i][0]));
                if (dateString != null && !dateString.equals("")) {
                    Date date = format.parse(dateString);
                    if (dateEnable(30 * 60 * 1000, format.parse(dateNow), date)) {
                        String xlcsjg = xlcsCursor.getString(xlcsCursor
                                .getColumnIndex(SqliteField.XlcsjgAndTzpsjgFields[i][1]));
                        
                        //因为SCL90结论和建议保存的格式和其他的不一样，所以单独判断处理
                        if(i!=6){
                            String[] jgxx = xlcsjg.split("_");
                            xlcsSummary += (Xlcs.CSMCS[i] + ":" + jgxx[0] + ";");// 结论
                            xlcsSuggestion += (Xlcs.CSMCS[i] + ":" + jgxx[1] + ";");// 建议
                        }else{
                            //SCL90的保存
                            String[] jgxx = xlcsjg.split(";");
                            xlcsSummary+=Xlcs.CSMCS[i]+":";
                            xlcsSuggestion+=Xlcs.CSMCS[i]+":";
                            for(int j=0;j<jgxx.length;j++){
                                if(jgxx[j]==null)
                                    continue;
                                String[] jgxxCatagory= jgxx[j].split("_");
                                xlcsSummary += (JktjXlpgScl90LasePage.SCL90_ITEM[j] + jgxxCatagory[0] + ";");// 结论
                                xlcsSuggestion += (jgxxCatagory[1] + ";");// 建议
                            }
                        }
                    }
                }
            }
            
        }

        bcxlhetzJ004.request.summury = xlcsSummary;
        bcxlhetzJ004.request.suggest = xlcsSuggestion;

        // 开始上传
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(bcxlhetzJ004);
        BeanUtil.getInstance().saveBeanToWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    BcxlhetzJ004 responseBcxlhetzJ004 = (BcxlhetzJ004) listBean.get(0);
                    if (responseBcxlhetzJ004 == null || responseBcxlhetzJ004.response == null) {
                        mToast.setText("【保存心理和体质】服务器接口异常");
                        mToast.show();
                    } else {
                        if (responseBcxlhetzJ004.response.errMsg.length() > 0) {
                            mToast.setText("【保存心理和体质】" + responseBcxlhetzJ004.response.errMsg);
                            mToast.show();
                        } else {
                            String evalSn = responseBcxlhetzJ004.response.evalSn;// 更新序列号
                            Date lastDate = new Date();// 更新最后的得到序列号的时间
                            saveJgxxToDB(evalSn, lastDate);
                            mToast.setText("【保存心理和体质】上传成功");
                            mToast.show();
                        }
                    }
                }
            }
        });

    }

    public static boolean dateEnable(long gapTime, Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        if (time1 - time2 >= gapTime)
            return false;
        return true;
    }

    // 将随访序列号和上传时间更新到数据库
    public void saveJgxxToDB(String evalSn, Date lastDate) {
        // 将数据保存或者更新到数据库
        if (MyApplication.getInstance().getSession().getCurrentResident() == null)
            return;
        String resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();

        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE "
                        + XlcsjgAndTzpsjg.RESIDENT_UUID + "=?", new String[] { resident_uuid });

        // 在新增插入
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String date = format.format(lastDate);
        if (cursor.moveToNext()) {// 说明有数据存在，那么更新
            //
            SqliteOperater.getDbInstance().updateBySql(
                    "UPDATE " + XlcsjgAndTzpsjg.TB_NAME + " SET " + XlcsjgAndTzpsjg.UPDATE_DATE
                            + "=?," + XlcsjgAndTzpsjg.EVALSN + "=? WHERE "
                            + XlcsjgAndTzpsjg.RESIDENT_UUID + " = ?",
                    new String[] { date, evalSn, resident_uuid });
        } else {
            SqliteOperater.getDbInstance().insertBySql(
                    "INSERT INTO " + XlcsjgAndTzpsjg.TB_NAME + "(" + XlcsjgAndTzpsjg.RESIDENT_UUID
                            + "," + XlcsjgAndTzpsjg.UPDATE_DATE + "," + XlcsjgAndTzpsjg.EVALSN
                            + ") VALUES(?,?,?)", new String[] { resident_uuid, date, evalSn });
        }
    }

    public void getJmjbxxFromDb(final int index) {
        // 首先取到Jmjbxx，放入map中
        List<Class<? extends IBean>> jbxxListBean = new ArrayList<Class<? extends IBean>>();
        jbxxListBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(jbxxListBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                if(index==1){
                    try {
                        uploadXlcsToWeb();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } 
                }else if (index==2) {
                    doQlscaTest();
                }
                
            }
        });
    }
    
    public void doQlscaTest(){
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx != null && !mJmjbxx.getResidentID().equals("")) {
            String birthdayStr=mJmjbxx.getBirthDay();
            if(!birthdayStr.equals("")){
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date birthday=format.parse(birthdayStr);
                    Date now=new Date();
                    age=now.getYear()-birthday.getYear();
                    if(age<7||age>18){
                        age=-1;
                        mToast.setText("此项测试只适用于7-18岁年龄段的儿童，您不适合做此项测试！");
                        mToast.show();
                        return;
                    }
                    sex=mJmjbxx.getSexCD();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        mParent.showXlcsTestPage(Xlcs.QLSCA);
    }
}
