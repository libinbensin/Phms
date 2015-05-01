package com.cking.phss.page;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;

/**
 * 
 */
public class JktjLnpgPage extends LinearLayout implements IPage {

    private Toast mToast = null;
	private ViewGroup mTzpsPage = null;
	private Context mContext = null;
	private JktjTzbsPage01 mTzpsPage01 = null;
    private JktjTzpsJgxx mTzpsjgxx = null;
	private JktjTzbsTzbg mTzpsTzbg = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();

    RadioGroup radiogroup_lnpg;
    LinearLayout layout_content;
    
    private JktjLnpgPage0101 mJktjLnpgPage0101 = null;
    private JktjLnpgPage0201 mJktjLnpgPage0201 = null;
    private JktjLnpgPage0301 mJktjLnpgPage0301 = null;

    
	public JktjLnpgPage(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjLnpgPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_lnpg_layout, this);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);

        loadPage(context, this);

//		mTzpsPage = (ViewGroup) findViewById(R.id.layout_content);
//		showTzpsPage01();
	}

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        // 添加页面
        radiogroup_lnpg = (RadioGroup) findViewById(R.id.radiogroup_lnpg);
        layout_content = (LinearLayout) findViewById(R.id.layout_content);
        
        radiogroup_lnpg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                int tag = Integer.parseInt((String) rb.getTag());
                switch (tag) {
                    case 0:
                        mJktjLnpgPage0101 = new JktjLnpgPage0101(mContext, JktjLnpgPage.this,
                                beanMap);
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mJktjLnpgPage0101);
                        break;

                    case 1:
                        mJktjLnpgPage0201 = new JktjLnpgPage0201(mContext, JktjLnpgPage.this,
                                beanMap);
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mJktjLnpgPage0201);
                        break;
                    case 2:
                        mJktjLnpgPage0301 = new JktjLnpgPage0301(mContext, JktjLnpgPage.this,
                                beanMap);
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mJktjLnpgPage0301);
                        break;
                }
            }
        });
        radiogroup_lnpg.check(R.id.radio_zilipg);
    }
    public void getBeanFromDB() {
        if (beanMap == null)
            return;
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean,
                new BeanUtil.OnResultFromDb() {
                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        if(listBean==null||listBean.size()<0)
                            return;
//                        if (isSucc) {
                            beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                            setValue();
//                        }
                    }
                });

    }
	private void setView(ViewGroup viewGroup) {
	    try {
    		mTzpsPage.removeAllViews();
    		mTzpsPage.addView(viewGroup);
	    } catch (NullPointerException e) {
	        e.printStackTrace();
	    }
	}

    public void showLnpgPage0101() {
        mJktjLnpgPage0101 = new JktjLnpgPage0101(mContext, JktjLnpgPage.this, beanMap);
        layout_content.removeAllViewsInLayout();
        layout_content.addView(mJktjLnpgPage0101);
	}

    /**
     * 
     */
    public void showLnpgPage0201() {
        mJktjLnpgPage0201 = new JktjLnpgPage0201(mContext, JktjLnpgPage.this, beanMap);
        layout_content.removeAllViewsInLayout();
        layout_content.addView(mJktjLnpgPage0201);
    }

    /**
     * 
     */
    public void showLnpgPage0301() {
        mJktjLnpgPage0301 = new JktjLnpgPage0301(mContext, JktjLnpgPage.this, beanMap);
        layout_content.removeAllViewsInLayout();
        layout_content.addView(mJktjLnpgPage0301);
    }

    /* (non-Javadoc)
     * @see android.view.View#onAttachedToWindow()
     */
//    @Override
//    protected void onAttachedToWindow() {
//        showTzpsPage01();
//        super.onAttachedToWindow();
//    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
        mTzpsPage01.setValue();
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    // 将随访序列号和上传时间更新到数据库
    public void saveValueToDB(String evalSn, Date lastDate) {
        // // 将数据保存或者更新到数据库
        // if (MyApplication.getInstance().getSession().getCurrentResident() ==
        // null)
        // return;
        // String resident_uuid =
        // MyApplication.getInstance().getSession().getCurrentResident()
        // .getResidentUUID();
        //
        // Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
        // "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE "
        // + XlcsjgAndTzpsjg.RESIDENT_UUID + "=?", new String[] { resident_uuid
        // });
        //
        // // 在新增插入
        // SimpleDateFormat format = new
        // SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        // String date = format.format(lastDate);
        // if (cursor.moveToNext()) {// 说明有数据存在，那么更新
        // //
        // SqliteOperater.getDbInstance().updateBySql(
        // "UPDATE " + XlcsjgAndTzpsjg.TB_NAME + " SET " + XlcsjgAndTzpsjg.TZJG1
        // + "=?,"
        // + XlcsjgAndTzpsjg.TZJG2 + "=?," + XlcsjgAndTzpsjg.UPDATE_DATE + "=?,"
        // + XlcsjgAndTzpsjg.EVALSN + "=?  WHERE " +
        // XlcsjgAndTzpsjg.RESIDENT_UUID
        // + " = ?",
        // new String[] { tz1 + "", tz2 + "", date, evalSn, resident_uuid });
        // } else {
        // SqliteOperater.getDbInstance().insertBySql(
        // "INSERT INTO " + XlcsjgAndTzpsjg.TB_NAME + "(" +
        // XlcsjgAndTzpsjg.RESIDENT_UUID
        // + "," + XlcsjgAndTzpsjg.TZJG1 + "," + XlcsjgAndTzpsjg.TZJG2 + ","
        // + XlcsjgAndTzpsjg.UPDATE_DATE + "," + XlcsjgAndTzpsjg.EVALSN
        // + ") VALUES(?,?,?,?,?)",
        // new String[] { resident_uuid, tz1 + "", tz2 + "", date, evalSn });
        // }
    }

    public void saveValueToWeb(String strCHECKINID) throws ParseException {
        // if (MyApplication.getInstance().getSession().getCurrentResident() ==
        // null)
        // return;
        //
        // // 取出随访编号，判断其有效性
        // String resident_uuid =
        // MyApplication.getInstance().getSession().getCurrentResident()
        // .getResidentUUID();
        //
        // Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
        // "SELECT * FROM " + LnpgField.TB_NAME + " WHERE " +
        // XlcsjgAndTzpsjg.RESIDENT_UUID
        // + "=?", new String[] { resident_uuid });
        // String evalSn = "";// 随访编号的获取
        // SimpleDateFormat format = new
        // SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        // String dateNow = format.format(new Date());
        // String previousDataTime = "";
        // if (cursor.moveToNext()) {// 说明有数据存在
        // previousDataTime =
        // cursor.getString(cursor.getColumnIndex(XlcsjgAndTzpsjg.UPDATE_DATE));
        // if (previousDataTime != null && !previousDataTime.trim().equals(""))
        // {
        // int gapTime = 10;
        // if (XlcsPage01.dateEnable(gapTime * 60L * 1000,
        // format.parse(dateNow),
        // format.parse(previousDataTime))) {
        // evalSn =
        // cursor.getString(cursor.getColumnIndex(XlcsjgAndTzpsjg.EVALSN));//
        // 如果时间有效，那么取出随访编号
        // }
        // }
        // }
        //
        // // 然后上传数据
        //
        // Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        // if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
        // mToast.setText(R.string.toast_info_none_resident);
        // mToast.show();
        // return;
        // }
        //
        // BcxlhetzJ004 bcxlhetzJ004 = new BcxlhetzJ004();
        // bcxlhetzJ004.request = new BcxlhetzJ004.Request();
        // // 判断随访序号的有效性
        // if (evalSn.equals("")) {// 新增
        // bcxlhetzJ004.request.type = 1;
        // bcxlhetzJ004.request.evalSn = strCHECKINID;
        // } else {// 编辑
        // bcxlhetzJ004.request.type = 2;
        // bcxlhetzJ004.request.evalSn = strCHECKINID;
        // }
        // bcxlhetzJ004.request.residentID = mJmjbxx.getResidentID();
        // bcxlhetzJ004.request.userId =
        // MyApplication.getInstance().getSession().getLoginResult().response.userID;
        // bcxlhetzJ004.request.evalEmpId =
        // MyApplication.getInstance().getSession().getLoginResult().response.employeeNo
        // .getiD();
        //
        // bcxlhetzJ004.request.checkDate = new
        // SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //
        // // 体质辨识的数据查询
        // if (tz1 != -1) {
        // bcxlhetzJ004.request.tcmType1 = Tzps.TZPS_XXMS[tz1][0];
        // bcxlhetzJ004.request.tcmDescribe1 = Tzps.TZPS_XXMS[tz1][1];
        // bcxlhetzJ004.request.tcmSuggest1 = Tzps.TZPS_XXMS[tz1][2];
        // bcxlhetzJ004.request.tcmKind1 = Tzps.TZPS_XXMS[tz1][3];
        // }
        // if (tz2 != -1) {
        // bcxlhetzJ004.request.tcmType2 = Tzps.TZPS_XXMS[tz2][0];
        // bcxlhetzJ004.request.tcmDescribe2 = Tzps.TZPS_XXMS[tz2][1];
        // bcxlhetzJ004.request.tcmSuggest2 = Tzps.TZPS_XXMS[tz2][2];
        // bcxlhetzJ004.request.tcmKind2 = Tzps.TZPS_XXMS[tz2][3];
        // }
        //
        // // 开始上传
        // List<IDto> beanList = new ArrayList<IDto>();
        // beanList.add(bcxlhetzJ004);
        // BeanUtil.getInstance().saveBeanToWeb(beanList, new
        // BeanUtil.OnResultFromWeb() {
        // @Override
        // public void onResult(List<IDto> listBean, boolean isSucc) {
        // if (isSucc) {
        // BcxlhetzJ004 responseBcxlhetzJ004 = (BcxlhetzJ004) listBean.get(0);
        // if (responseBcxlhetzJ004 == null || responseBcxlhetzJ004.response ==
        // null) {
        // mToast.setText("【体质辨识】服务器接口异常");
        // mToast.show();
        // } else {
        // if (responseBcxlhetzJ004.response.errMsg.length() > 0) {
        // mToast.setText("【体质辨识】" + responseBcxlhetzJ004.response.errMsg);
        // mToast.show();
        // } else {
        // String evalSn = responseBcxlhetzJ004.response.evalSn;// 更新序列号
        // Date lastDate = new Date();// 更新最后的得到序列号的时间
        // saveJgxxToDB(evalSn, lastDate);
        // mToast.setText("【体质辨识】上传成功");
        // mToast.show();
        // }
        // }
        // }
        // }
        // });

    }
}
