package com.cking.phss.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
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
import com.cking.phss.bean.Xlcs;
import com.cking.phss.dto.Ddjmjbxx7;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.SqliteField.XlcsjgAndTzpsjg;
import com.cking.phss.sqlite.SqliteOperater;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class JktjXlpgLasePage extends LinearLayout {
    private static final String TAG = "XlcslasePage";
    private Context mContext;
    private Toast mToast;
    private JktjXlpgPage mParent;
    private int mRank = 0;
    private int mCsid = 0;

    // 个人基本信息
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private Ddjmjbxx7 ddjmjbxx7;
    
    private TextView mCsmcText = null;
    private TextView mCsjgTitleText = null;
    private TextView mCsjgDetailText = null;
    private TextView mJkjyEdit = null;
    private Button mBackBtn = null;
    // 心里测试结果表面
    private String resident_uuid = null;
    private String jieguo = null;

    public JktjXlpgLasePage(Context context, JktjXlpgPage parent, int rank, int csid) {
        super(context);
        this.mParent = parent;
        init(context, rank, csid);
    }

    private void init(Context context, int rank, int csid) {
        // 创建表、添加或者查询数据
        // saveXlcsjgToDB();
        // readXlcsjgFromDB();

        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_health_xlpg_3_layout, this);

        mCsmcText = (TextView) findViewById(R.id.xlcs_csmc_text);
        mCsjgTitleText = (TextView) findViewById(R.id.xlcs_csjg_title_text);
        // 暂不使用 20121017 苏
        // mCsjgDetailText = (TextView)
        // findViewById(R.id.xlcs_csjg_detail_text);
        // 结果显示内容、需要存如本地数据库中
        mJkjyEdit = (TextView) findViewById(R.id.xlcs_csjg_jkjy_edit);
        mBackBtn = (Button) findViewById(R.id.xlce_back_button);

        changeTopics(rank, csid);

        mBackBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 重新加载心理测试首页
                mParent.showXlcsPage01();
            }
        });
    }

    private void setValue() {
        mCsmcText.setText(Xlcs.CSMCS[mCsid] + " - 测试结果：");
        mCsjgTitleText.setText(Xlcs.CSJGS[mCsid][mRank][2]);
        Global.xlpgResults[mCsid] = Xlcs.CSJGS[mCsid][mRank][2];
        // 暂不使用 20121017 苏
        // mCsjgDetailText.setText(Xlcs.CSJGS[mCsid][mRank][3]);
        mJkjyEdit.setText(Xlcs.CSJGS[mCsid][mRank][4]);

        saveXlcsjgToDB();
    }

    public void saveXlcsjgToDB() {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null)
            return;
        resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();

        jieguo = mCsjgTitleText.getText().toString() + "_" + mJkjyEdit.getText().toString();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String date=format.format(new Date());
        
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE " + XlcsjgAndTzpsjg.RESIDENT_UUID + "=?",
                new String[] { resident_uuid });
         
        if (!cursor.moveToNext()) {//insert
           String sql1="INSERT INTO "+XlcsjgAndTzpsjg.TB_NAME + "("+XlcsjgAndTzpsjg.RESIDENT_UUID + ",";
           String sql2="";
           switch (mCsid) {
            case Xlcs.YY://1
                sql2=XlcsjgAndTzpsjg.XLCSJG_SDS +","+XlcsjgAndTzpsjg.DATE1;
                break;
            case  Xlcs.JL://2
                sql2=XlcsjgAndTzpsjg.XLCSJG_SAS +","+XlcsjgAndTzpsjg.DATE2;
                break;
            case Xlcs.PZ://3
                sql2=XlcsjgAndTzpsjg.XLCSJG_PSQI +","+XlcsjgAndTzpsjg.DATE3;
                break;
            case Xlcs.ZS://4
                sql2=XlcsjgAndTzpsjg.XLCSJG_SAQ +","+XlcsjgAndTzpsjg.DATE4;
                break;
            case Xlcs.UCLA://5
                sql2=XlcsjgAndTzpsjg.XLCSJG_UCLA +","+XlcsjgAndTzpsjg.DATE5;
                break;
            case Xlcs.SS://6
                sql2=XlcsjgAndTzpsjg.XLCSJG_GCQ +","+XlcsjgAndTzpsjg.DATE6;
                break;
            case Xlcs.SCL90://7
                sql2=XlcsjgAndTzpsjg.XLCSJG_SCL90 +","+XlcsjgAndTzpsjg.DATE7;
                break;
            case Xlcs.QLSCA://8
                sql2=XlcsjgAndTzpsjg.XLCSJG_QLSCA+","+XlcsjgAndTzpsjg.DATE8;
                break;
                }
         String sql3=") VALUES(?,?,?)";  
         SqliteOperater.getDbInstance().insertBySql(sql1+sql2+sql3,new String[] { resident_uuid, jieguo ,date});  
        } else {//update
            String sql1="UPDATE " + XlcsjgAndTzpsjg.TB_NAME + " SET ";
            String sql2="";
            switch (mCsid) {
             case Xlcs.YY://1
                 sql2=XlcsjgAndTzpsjg.XLCSJG_SDS+"=?,"+XlcsjgAndTzpsjg.DATE1+"=?";
                 break;
             case  Xlcs.JL://2
                 sql2= XlcsjgAndTzpsjg.XLCSJG_SAS+"=?,"+XlcsjgAndTzpsjg.DATE2+"=?";
                 break;
             case Xlcs.PZ://3
                 sql2= XlcsjgAndTzpsjg.XLCSJG_PSQI+"=?,"+XlcsjgAndTzpsjg.DATE3+"=?";
                 break;
             case Xlcs.ZS://4
                 sql2= XlcsjgAndTzpsjg.XLCSJG_SAQ+"=?,"+XlcsjgAndTzpsjg.DATE4+"=?";
                 break;
             case Xlcs.UCLA://5
                 sql2= XlcsjgAndTzpsjg.XLCSJG_UCLA+"=?,"+XlcsjgAndTzpsjg.DATE5+"=?";
                 break;
             case Xlcs.SS://6
                 sql2= XlcsjgAndTzpsjg.XLCSJG_GCQ+"=?,"+XlcsjgAndTzpsjg.DATE6+"=?";
                 break;
             case Xlcs.SCL90://7
                 sql2=XlcsjgAndTzpsjg.XLCSJG_SCL90 +"=?,"+XlcsjgAndTzpsjg.DATE7+"=?";
                 break;
             case Xlcs.QLSCA://8
                 sql2=XlcsjgAndTzpsjg.XLCSJG_QLSCA+"=?,"+XlcsjgAndTzpsjg.DATE8+"=?";
                 break;
                 }
          String sql3=" WHERE " + XlcsjgAndTzpsjg.RESIDENT_UUID + " = ?";
          SqliteOperater.getDbInstance().updateBySql(sql1+sql2+sql3,
                  new String[] { jieguo,date, resident_uuid });

        }
        
        /*// 抑郁自评量表(SDS)1
        if (mCsid == Xlcs.YY) {
            Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT * FROM " + Xlcsjg.TB_NAME + " WHERE " + Xlcsjg.XLCSJG_UUID + "=?",
                    new String[] { xlcsjg_uuid });
            if (!cursor.moveToNext()) {
                SqliteOperater.getDbInstance().insertBySql(
                        " INSERT INTO " + Xlcsjg.TB_NAME + "(" + Xlcsjg.XLCSJG_UUID + ","
                                + Xlcsjg.XLCSJG_SDS +","+Xlcsjg.DATE1+ ") VALUES(?,?,?)",
                        new String[] { xlcsjg_uuid, jieguo ,date});
            } else {
                SqliteOperater.getDbInstance()
                        .updateBySql(
                                "UPDATE " + Xlcsjg.TB_NAME + " SET " + Xlcsjg.XLCSJG_SDS
                                        + " = ?, " +Xlcsjg.DATE1+"=?"+ " WHERE " + Xlcsjg.XLCSJG_UUID + " = ?",
                                new String[] { jieguo,date, xlcsjg_uuid });

            }
        }
        // 焦虑自评量表(SAS)2
        if (mCsid == Xlcs.JL) {
            Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT * FROM " + Xlcsjg.TB_NAME + " WHERE " + Xlcsjg.XLCSJG_UUID + "=?",
                    new String[] { xlcsjg_uuid });
            if (!cursor.moveToNext()) {
                SqliteOperater.getDbInstance().insertBySql(
                        " INSERT INTO " + Xlcsjg.TB_NAME + "(" + Xlcsjg.XLCSJG_UUID + ","
                                + Xlcsjg.XLCSJG_SAS +","+Xlcsjg.DATE2+ ") VALUES(?,?,?)",
                        new String[] { xlcsjg_uuid, jieguo ,date});
            } else {
                SqliteOperater.getDbInstance()
                        .updateBySql(
                                "UPDATE " + Xlcsjg.TB_NAME + " SET " + Xlcsjg.XLCSJG_SAS
                                        + " = ?,"+Xlcsjg.DATE2+"=?"+ " WHERE " + Xlcsjg.XLCSJG_UUID + " = ?",
                                new String[] { jieguo,date, xlcsjg_uuid });

            }
        }
        // 匹兹堡睡眠质量指数(PSQI)3
        if (mCsid == Xlcs.PZ) {
            Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT * FROM " + Xlcsjg.TB_NAME + " WHERE " + Xlcsjg.XLCSJG_UUID + "=?",
                    new String[] { xlcsjg_uuid });
            if (!cursor.moveToNext()) {
                SqliteOperater.getDbInstance().insertBySql(
                        " INSERT INTO " + Xlcsjg.TB_NAME + "(" + Xlcsjg.XLCSJG_UUID + ","
                                + Xlcsjg.XLCSJG_PSQI + ") VALUES(?,?)",
                        new String[] { xlcsjg_uuid, jieguo });
            } else {
                SqliteOperater.getDbInstance()
                        .updateBySql(
                                "UPDATE " + Xlcsjg.TB_NAME + " SET " + Xlcsjg.XLCSJG_PSQI
                                        + " = ? WHERE " + Xlcsjg.XLCSJG_UUID + " = ?",
                                new String[] { jieguo, xlcsjg_uuid });

            }
        }
        // 自杀态度问卷(SAQ)
        if (mCsid == Xlcs.ZS) {
            Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT * FROM " + Xlcsjg.TB_NAME + " WHERE " + Xlcsjg.XLCSJG_UUID + "=?",
                    new String[] { xlcsjg_uuid });
            if (!cursor.moveToNext()) {
                SqliteOperater.getDbInstance().insertBySql(
                        " INSERT INTO " + Xlcsjg.TB_NAME + "(" + Xlcsjg.XLCSJG_UUID + ","
                                + Xlcsjg.XLCSJG_SAQ + ") VALUES(?,?)",
                        new String[] { xlcsjg_uuid, jieguo });
            } else {
                SqliteOperater.getDbInstance()
                        .updateBySql(
                                "UPDATE " + Xlcsjg.TB_NAME + " SET " + Xlcsjg.XLCSJG_SAQ
                                        + " = ? WHERE " + Xlcsjg.XLCSJG_UUID + " = ?",
                                new String[] { jieguo, xlcsjg_uuid });

            }
        }
        // UCLA孤独量表
        if (mCsid == Xlcs.UCLA) {
            Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT * FROM " + Xlcsjg.TB_NAME + " WHERE " + Xlcsjg.XLCSJG_UUID + "=?",
                    new String[] { xlcsjg_uuid });
            if (!cursor.moveToNext()) {
                SqliteOperater.getDbInstance().insertBySql(
                        " INSERT INTO " + Xlcsjg.TB_NAME + "(" + Xlcsjg.XLCSJG_UUID + ","
                                + Xlcsjg.XLCSJG_UCLA + ") VALUES(?,?)",
                        new String[] { xlcsjg_uuid, jieguo });
            } else {
                SqliteOperater.getDbInstance()
                        .updateBySql(
                                "UPDATE " + Xlcsjg.TB_NAME + " SET " + Xlcsjg.XLCSJG_UCLA
                                        + " = ? WHERE " + Xlcsjg.XLCSJG_UUID + " = ?",
                                new String[] { jieguo, xlcsjg_uuid });

            }
        }
        // 舒适状况量表(GCQ)
        if (mCsid == Xlcs.SS) {
            Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                    "SELECT * FROM " + Xlcsjg.TB_NAME + " WHERE " + Xlcsjg.XLCSJG_UUID + "=?",
                    new String[] { xlcsjg_uuid });
            if (!cursor.moveToNext()) {
                SqliteOperater.getDbInstance().insertBySql(
                        "INSERT INTO " + Xlcsjg.TB_NAME + "(" + Xlcsjg.XLCSJG_UUID + ","
                                + Xlcsjg.XLCSJG_GCQ + ") VALUES(?,?)",
                        new String[] { xlcsjg_uuid, jieguo });
            } else {
                SqliteOperater.getDbInstance().updateBySql(
                        "UPDATE " + Xlcsjg.TB_NAME + " SET " + Xlcsjg.XLCSJG_GCQ + "=? WHERE "
                                + Xlcsjg.XLCSJG_UUID + "=?", new String[] { jieguo, xlcsjg_uuid });

            }
        }*/
    }

    public void readXlcsjgFromDB() {
        // SqliteOperater.getDbInstance().query(tableName, columns, selection,
        // selectionArgs, groupBy, having, orderBy)
        SqliteOperater.getDbInstance().query("select * from tb_xlcsjg",
                new String[] { "resident_uuid", "xlcs_jl", "xlcs_xq" }, "resident_uuid=?",
                new String[] { resident_uuid }, null, null, null);
    }

    public void changeTopics(int rank, int csid) {
        mCsid = csid;
        mRank = rank;
    }

    //
    public void getJbxxFromDB() {
        if (beanMap == null)
            return;
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            return;
        }
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                setJmjbxxValue();
            }
        });

    }

    private void setJmjbxxValue() {      
        setValue();
    }
}
