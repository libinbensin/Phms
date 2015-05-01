/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SjglPage01.java
 * classes : com.cking.phss.page.SjglPage01
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午8:36:56
 */
package com.cking.phss.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dialog.SjglJmcxDetailDialog;
import com.cking.phss.dto.CxlihzylbJ014AndCxjkpgJ015;
import com.cking.phss.dto.CxlihzylbJ014AndCxjkpgJ015.Response.EvalList;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.innner.Record;
import com.cking.phss.dto.innner.TnbVisits;
import com.cking.phss.dto.sfgl.gxy.Dgxyhzsflb14;
import com.cking.phss.dto.sfgl.gxy.Dgxyhzsflb14.Response.Visits;
import com.cking.phss.dto.sfgl.tnb.Dtnbhzsflb21;
import com.cking.phss.dto.sjcx.Sjdnryzztjlucx;
import com.cking.phss.dto.sjgl.JmcxHdr01;
import com.cking.phss.net.ISoapRecv;
import com.cking.phss.net.WebService;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.StringManager;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.widget.TextViewSimpleAdapter;
import com.cking.phss.xml.util.XmlSerializerUtil;

/**
 * com.cking.phss.page.SjglPage01 显示数据查询列表的页面
 */
public class SjglJmcxPage01 extends LinearLayout implements OnItemClickListener {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private LinearLayout[] linearStyles = new LinearLayout[3];
    private int listType;// 1代表查询快速体检，2代表查询高血压随访，3代表查询糖尿病随访，4代表查询体质心理，5代表查询健康评估

    LinearLayout titleLinearLayout;
    LinearLayout bodyLinearLayout;
    SpinnerUtil xmlbSpinnerUtil;
    SpinnerUtil xmmcSpinnerUtil;
    CalendarText ksrqCalendarText;
    CalendarText jsrqCalendarText;
    Button cxButton;
    ListView jmcxListView;
    TextView zjTextView;
    private TextViewSimpleAdapter adapter = null;
    private ProgressDialog progressDialog = null;

    public SjglJmcxPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglJmcxPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param mContext2
     * @param beanMap2
     * @param jmcxTitleLinearLayout
     * @param jmcxBodyLinearLayout
     */
    public SjglJmcxPage01(Context context, Map<String, IBean> beanMap,
            LinearLayout titleLinearLayout, LinearLayout bodyLinearLayout) {
        super(context);
        this.beanMap = beanMap;
        this.titleLinearLayout = titleLinearLayout;
        this.bodyLinearLayout = bodyLinearLayout;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        View titleView = inflater.inflate(R.layout.fragment_sjgl_jmcx_01_title_layout,
                titleLinearLayout);
        xmlbSpinnerUtil = (SpinnerUtil) titleView.findViewById(R.id.xmlbSpinnerUtil);
        xmmcSpinnerUtil = (SpinnerUtil) titleView.findViewById(R.id.xmmcSpinnerUtil);
        ksrqCalendarText = (CalendarText) titleView.findViewById(R.id.ksrqCalendarText);
        jsrqCalendarText = (CalendarText) titleView.findViewById(R.id.jsrqCalendarText);
        cxButton = (Button) titleView.findViewById(R.id.cxButton);

        inflater = LayoutInflater.from(context);
        View bodyView = inflater.inflate(R.layout.fragment_sjgl_jmcx_01_body_layout,
                bodyLinearLayout);
        jmcxListView = (ListView) bodyView.findViewById(R.id.jmcxListView);
        zjTextView = (TextView) bodyView.findViewById(R.id.zjTextView);

        linearStyles[0] = (LinearLayout) bodyView.findViewById(R.id.include_item1);
        linearStyles[1] = (LinearLayout) bodyView.findViewById(R.id.include_item2);
        linearStyles[2] = (LinearLayout) bodyView.findViewById(R.id.include_item3);
        jmcxListView.setOnItemClickListener(this);
        // changeStyle(31);

        xmlbSpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String namePrefix = "lbcxyjxm";
                String name = "";
                int suffix = xmlbSpinnerUtil.getSelectedValueInt();
                if (suffix == 1) { // 所有项目
                    for (int i = 1; i < xmlbSpinnerUtil.getCount(); i++) {
                        String tmpName = "";
                        if (i > 1) {
                            tmpName += "+";
                        }
                        tmpName += namePrefix + (i + 1);
                        name += tmpName;
                    }
                    // name = "lbcxyjxm2+lbcxyjxm3+lbcxyjxm4"
                    xmmcSpinnerUtil.setName(name);
                } else {
                    name = namePrefix + suffix;
                    xmmcSpinnerUtil.setName(name);

                }
                // switch (arg2) {
                // case 0:
                // xmmcSpinnerUtil.setName("lbcxyjxm2" + "lbcxyjxm3" +
                // "lbcxyjxm4");
                // break;
                // case 1:
                // xmmcSpinnerUtil.setName("lbcxyjxm2");
                // break;
                // case 2:
                // xmmcSpinnerUtil.setName("lbcxyjxm3");
                // break;
                // case 3:
                // xmmcSpinnerUtil.setName("lbcxyjxm4");
                // break;
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        cxButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int xmmcId = xmmcSpinnerUtil.getSelectedValueInt();
                String ksrq = ksrqCalendarText.getText().toString();
                String jsrq = jsrqCalendarText.getText().toString();

                // require(ksrq, jsrq);
                require(xmmcId, ksrq, jsrq);
            }
        });
    }

    /**
     * @param xmmcId
     *            <item ID="21">档案信息</item>
     * 
     *            <item ID="22">签约情况</item>
     * 
     *            <item ID="31">快速体检</item>
     * 
     *            <item ID="32">体质辨识</item>
     * 
     *            <item ID="33">心理评估</item>
     * 
     *            <item ID="34">老年评估</item>
     * 
     *            <item ID="35">普通体检</item>
     * 
     *            <item ID="41">高血压</item>
     * 
     *            <item ID="42">糖尿病</item>
     * 
     *            <item ID="43">脑卒中</item>
     * 
     *            <item ID="44">精神病</item>
     * 
     *            <item ID="45">孕产访视</item>
     * 
     *            <item ID="46">儿童访视</item>
     * 
     *            <item ID="47">老年随访</item>
     * 
     *            <item ID="48">残疾随访</item>
     * @param startDate
     * @param endDate
     */
    public void require(int xmmcId, String startDate, String endDate) {
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        listType = xmmcId;
        changeStyle(xmmcId);
        if (xmmcId == 31) {// 查询快速体检
            requireKstjData(startDate, endDate);
        } else if (xmmcId == 41) {// 查询高血压随访
            requireSfglGxyData(startDate, endDate);
        } else if (xmmcId == 42) {// 查询糖尿病随访
            requireSfglTnbData(startDate, endDate);
        } else if (xmmcId == 32) {// 查询体质心理
            requireTzxlOrJkpgData(startDate, endDate, "J014");
        } else if (xmmcId == 33) {// 查询体质心理
            requireTzxlOrJkpgData(startDate, endDate, "J015");
        } else {// 查询健康评估
            mToast.setText("暂不支持该项目的查询");
            mToast.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            Map<String, String> map = (Map<String, String>) parent.getAdapter().getItem(position);
            // 需要体检编号
            String checkId = map.get("checkId");
            int listType = xmmcSpinnerUtil.getSelectedValueInt();
            requireDetail(listType, checkId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 改变列表的标题栏
    // 有5中查询，标题要是只有3中，有几种是同样的样式
    private void changeStyle(int index) {
        for (int i = 0; i < linearStyles.length; i++) {
            linearStyles[i].setVisibility(View.GONE);
        }
        if (index == 31) {
            linearStyles[0].setVisibility(View.VISIBLE);
        } else if (index == 41) {
            linearStyles[1].setVisibility(View.VISIBLE);
            ((TextView) linearStyles[1].findViewById(R.id.data_text)).setText("血压");
        } else if (index == 42) {
            linearStyles[1].setVisibility(View.VISIBLE);
            ((TextView) linearStyles[1].findViewById(R.id.data_text)).setText("空腹血糖");
        } else if (index == 32) {
            linearStyles[2].setVisibility(View.VISIBLE);
        } else if (index == 33) {
            linearStyles[2].setVisibility(View.VISIBLE);
        }
        jmcxListView.setAdapter(null);
    }

    private void require(String beginTime, String endTime) {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            mToast.setText("当前没有居民信息，请先登录！");
            mToast.show();
            return;
        }
        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());

        JmcxHdr01 jmcxHdr01 = new JmcxHdr01();
        jmcxHdr01.request = new JmcxHdr01.Request();

        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        int userID = Integer.parseInt(stringuserID);
        jmcxHdr01.request.UserID = userID;
        jmcxHdr01.request.ResidentID = jmjbxx.getResidentID();
        jmcxHdr01.request.FirstProject = xmlbSpinnerUtil.getCheckedBeanCD();
        jmcxHdr01.request.SecondProject = xmmcSpinnerUtil.getCheckedBeanCD();
        jmcxHdr01.request.SDate = beginTime;
        jmcxHdr01.request.EDate = endTime;
        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为
        beanList.add(jmcxHdr01); // 添加工作管理idto
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(mContext, "正在查询", "请稍等...", false, true);
        }
        progressDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                finishConnectionUi();
            }
        });
        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    finishConnectionUi();
                } else {
                    isSucc = false;
                }
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    JmcxHdr01 responseJmcxHdr01 = (JmcxHdr01) listBean.get(0);
                    if (responseJmcxHdr01 == null || responseJmcxHdr01.response == null) {
                        sb.append("【居民查询】服务器接口异常");
                    } else {
                        if (responseJmcxHdr01.response.errMsg.length() > 0) {
                            sb.append("【居民查询】" + responseJmcxHdr01.response.errMsg);
                        } else {
                            if (responseJmcxHdr01.response.Records == null) {
                                sb.append("【居民查询】列表没有更新");
                            } else {
                                sb.append("【居民查询】列表更新完成");
                                // 保存成功的话，更新视图
                                initWebListView(responseJmcxHdr01.response.Records);
                            }
                        }
                    }
                    mToast.setDuration(Toast.LENGTH_LONG);
                    mToast.setText(sb.toString());
                    mToast.show();

                } else {
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText("网络请求异常");
                    mToast.show();
                }

            }
        });
    }

    private void finishConnectionUi() {
        // 隐藏进度条
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        progressDialog = null;
    }

    /**
     * @param records
     */
    protected void initWebListView(List<Record> records) {
        List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i) == null)
                continue;
            Map<String, String> map = new HashMap<String, String>();
            map.put("checkId", records.get(i).SerialNumber);
            map.put("NAME", records.get(i).ResidentName);
            String xb = ResourcesFactory.findValue(mContext, "xb", records.get(i).Sex.getcD());
            map.put("SEX", xb);
            map.put("CHECKTYPE", records.get(i).ProjectType.getTagValue());
            map.put("ORGNAME", records.get(i).Organization.getTagValue());
            map.put("CHECKTIME", records.get(i).CheckDate);
            listMaps.add(map);
        }
        adapter = new TextViewSimpleAdapter(mContext, listMaps, R.layout.list_item_sjcx01,
                new String[] { "checkId", "NAME", "SEX", "CHECKTYPE", "ORGNAME", "CHECKTIME" },
                new int[] { R.id.id_text, R.id.name_text, R.id.sex_text, R.id.type_text,
                        R.id.org_text, R.id.check_time_text });
        jmcxListView.setAdapter(adapter);
        jmcxListView.invalidate();
        zjTextView.setText(records.size() + "");
    }

    // 查询快速体检数据列表
    private void requireKstjData(String startDate, String endDate) {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        Sjdnryzztjlucx sjdnryzztjlucx = new Sjdnryzztjlucx();
        sjdnryzztjlucx.condition = new Sjdnryzztjlucx.Condition();
        sjdnryzztjlucx.condition.STARTTIME = startDate;
        sjdnryzztjlucx.condition.ENDTIME = endDate;
        sjdnryzztjlucx.condition.IDCARD = jmjbxx.getPaperNum();
        String beanXmlString = XmlSerializerUtil.getInstance().beanToXml(sjdnryzztjlucx);
        beanXmlString = WebService.removeXmlString(beanXmlString);
        WebService.getInstance().excute("SearchCheck", "data", beanXmlString, new ISoapRecv() {
            @Override
            public void onRecvData(String xmlStr, boolean success) {
                if (success) {// 如果成功
                    xmlStr = WebService.addXmlString(xmlStr);
                    Sjdnryzztjlucx responseSjdnryzztjlucx = (Sjdnryzztjlucx) XmlSerializerUtil
                            .getInstance().beanFromXML(Sjdnryzztjlucx.class, xmlStr);
                    if (responseSjdnryzztjlucx == null || responseSjdnryzztjlucx.results == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }

                    changeStyle(31);
                    if (responseSjdnryzztjlucx.results.items == null
                            || responseSjdnryzztjlucx.results.items.size() <= 0) {
                        mToast.setText("查询没有数据！");
                        mToast.show();
                        return;
                    }
                    updateWithKstjData(responseSjdnryzztjlucx.results.items);
                }
            }
        });
    }

    // 查询随访管理数据列表
    private void requireSfglGxyData(String startDate, String endDate) {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        Dgxyhzsflb14 dgxyhzsflb14 = new Dgxyhzsflb14();
        dgxyhzsflb14.request = new Dgxyhzsflb14.Request();

        dgxyhzsflb14.request.employeeNo = login1.response.employeeNo;
        dgxyhzsflb14.request.residentID = jmjbxx.getResidentID();
        dgxyhzsflb14.request.visitSD = startDate;
        dgxyhzsflb14.request.visitED = endDate;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dgxyhzsflb14);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc || listBean != null) {
                    Dgxyhzsflb14 responseDgxyhzsflb14 = (Dgxyhzsflb14) listBean.get(0);
                    if (responseDgxyhzsflb14 == null || responseDgxyhzsflb14.response == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }
                    changeStyle(41);
                    if (responseDgxyhzsflb14.response.visitsList == null
                            || responseDgxyhzsflb14.response.visitsList.size() <= 0) {
                        mToast.setText("查询没有数据！");
                        mToast.show();
                        return;
                    }
                    // 更新数据
                    updateWithGxySfData(responseDgxyhzsflb14.response.visitsList);
                } else {
                    mToast.setText("查询失败！");
                    mToast.show();
                    return;
                }
            }
        });
    }

    // 查询糖尿病随访列表
    private void requireSfglTnbData(String startDate, String endDate) {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        Dtnbhzsflb21 dtnbhzsflb21 = new Dtnbhzsflb21();
        dtnbhzsflb21.request = new Dtnbhzsflb21.Request();

        dtnbhzsflb21.request.employeeNo = login1.response.employeeNo;
        dtnbhzsflb21.request.residentID = jmjbxx.getResidentID();
        dtnbhzsflb21.request.visitSD = startDate;
        dtnbhzsflb21.request.visitED = endDate;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dtnbhzsflb21);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc || listBean != null) {
                    Dtnbhzsflb21 responseDtnbhzsflb21 = (Dtnbhzsflb21) listBean.get(0);
                    if (responseDtnbhzsflb21 == null || responseDtnbhzsflb21.response == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }
                    changeStyle(42);
                    if (responseDtnbhzsflb21.response.visitsList == null
                            || responseDtnbhzsflb21.response.visitsList.size() <= 0) {
                        mToast.setText("查询没有数据！");
                        mToast.show();
                        return;
                    }
                    // 更新数据
                    updateWithTnbSfData(responseDtnbhzsflb21.response.visitsList);
                } else {
                    mToast.setText("查询失败！");
                    mToast.show();
                    return;
                }
            }
        });
    }

    // 查询体质心理的列表和健康评估列表，因为这两个接口格式一模一样，只需要改变下operType就可
    private void requireTzxlOrJkpgData(String startDate, String endDate, String operType) {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());

        CxlihzylbJ014AndCxjkpgJ015 cxlihzylbJ014AndCxjkpgJ015 = new CxlihzylbJ014AndCxjkpgJ015();
        cxlihzylbJ014AndCxjkpgJ015.request = new CxlihzylbJ014AndCxjkpgJ015.Request();

        cxlihzylbJ014AndCxjkpgJ015.request.operType = operType;
        cxlihzylbJ014AndCxjkpgJ015.request.residentID = jmjbxx.getResidentID();
        cxlihzylbJ014AndCxjkpgJ015.request.sdate = startDate;
        cxlihzylbJ014AndCxjkpgJ015.request.edate = endDate;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(cxlihzylbJ014AndCxjkpgJ015);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc || listBean != null) {
                    CxlihzylbJ014AndCxjkpgJ015 responseData = (CxlihzylbJ014AndCxjkpgJ015) listBean
                            .get(0);
                    if (responseData == null || responseData.response == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }
                    changeStyle(32);
                    if (responseData.response.evalList == null
                            || responseData.response.evalList.size() <= 0) {
                        mToast.setText("查询没有数据！");
                        mToast.show();
                        return;
                    }
                    // 更新数据
                    updateWithTzxlOrJkpgData(responseData.response.evalList);
                } else {
                    mToast.setText("查询失败！");
                    mToast.show();
                    return;
                }
            }
        });
    }

    // 用快速体检的数据更新列表
    @SuppressLint("ResourceAsColor")
    private void updateWithKstjData(List<Sjdnryzztjlucx.Item> visits) {
        List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
        for (int i = 0; i < visits.size(); i++) {
            if (visits.get(i) == null)
                continue;
            Map<String, String> map = new HashMap<String, String>();
            map.put("checkId", visits.get(i).CHECKINID);
            map.put("NAME", visits.get(i).NAME);
            map.put("SEX", visits.get(i).SEX);
            map.put("CHECKTYPE", visits.get(i).CHECKTYPE);
            map.put("ORGNAME", visits.get(i).ORGNAME);
            map.put("CHECKTIME", visits.get(i).CHECKTIME);
            listMaps.add(map);
        }
        adapter = new TextViewSimpleAdapter(mContext, listMaps, R.layout.list_item_sjcx01,
                new String[] { "checkId", "NAME", "SEX", "CHECKTYPE", "ORGNAME", "CHECKTIME" },
                new int[] { R.id.id_text, R.id.name_text, R.id.sex_text, R.id.type_text,
                        R.id.org_text, R.id.check_time_text });
        jmcxListView.setAdapter(adapter);
        jmcxListView.invalidate();
        zjTextView.setText(visits.size() + "");
    }

    // 用高血压的数据更新列表
    private void updateWithGxySfData(List<Visits> visits) {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
        for (int i = 0; i < visits.size(); i++) {
            if (visits.get(i) == null)
                continue;
            Map<String, String> map = new HashMap<String, String>();
            map.put("checkId", visits.get(i).visitID);
            map.put("name", jmjbxx.getResidentName());
            map.put("sex", StringManager.getSexbyCD(jmjbxx.getSexCD()));
            map.put("visitDate", visits.get(i).visitDate);
            map.put("doctorName", visits.get(i).doctorName);
            map.put("hBP", visits.get(i).hBP);
            map.put("nextVisitDate", visits.get(i).nextVisitDate);
            listMaps.add(map);
        }
        adapter = new TextViewSimpleAdapter(mContext, listMaps, R.layout.list_item_sjcx02,
                new String[] { "checkId", "name", "sex", "visitDate", "doctorName", "hBP",
                        "nextVisitDate" }, new int[] { R.id.id_text, R.id.name_text, R.id.sex_text,
                        R.id.visit_date_text, R.id.visit_doc_text, R.id.data_text,
                        R.id.next_time_text });
        jmcxListView.setAdapter(adapter);
        jmcxListView.invalidate();
        zjTextView.setText(visits.size() + "");
    }

    // 用糖尿病的数据更新列表
    private void updateWithTnbSfData(List<TnbVisits> visits) {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
        for (int i = 0; i < visits.size(); i++) {
            if (visits.get(i) == null)
                continue;
            Map<String, String> map = new HashMap<String, String>();
            map.put("checkId", visits.get(i).visitID);
            map.put("name", jmjbxx.getResidentName());
            map.put("sex", StringManager.getSexbyCD(jmjbxx.getSexCD()));
            map.put("visitDate", visits.get(i).visitDate);
            map.put("doctorName", visits.get(i).doctorName);
            map.put("fPG", visits.get(i).fPG);
            map.put("nextVisitDate", visits.get(i).nextVisitDate);
            listMaps.add(map);
        }
        adapter = new TextViewSimpleAdapter(mContext, listMaps, R.layout.list_item_sjcx02,
                new String[] { "checkId", "name", "sex", "visitDate", "doctorName", "fPG",
                        "nextVisitDate" }, new int[] { R.id.id_text, R.id.name_text, R.id.sex_text,
                        R.id.visit_date_text, R.id.visit_doc_text, R.id.data_text,
                        R.id.next_time_text });
        jmcxListView.setAdapter(adapter);
        jmcxListView.invalidate();
        zjTextView.setText(visits.size() + "");
    }

    // 用健康评估或者中医体质心理的数据刷新列表
    private void updateWithTzxlOrJkpgData(List<EvalList> visits) {
        List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
        for (int i = 0; i < visits.size(); i++) {
            if (visits.get(i) == null)
                continue;
            Map<String, String> map = new HashMap<String, String>();
            map.put("checkId", visits.get(i).evalSn);
            map.put("name", visits.get(i).residentName);
            map.put("sex", visits.get(i).sex);
            map.put("evalType", visits.get(i).evalType);
            map.put("orgName", visits.get(i).orgName);
            map.put("checkDate", visits.get(i).checkDate);
            listMaps.add(map);
        }
        adapter = new TextViewSimpleAdapter(mContext, listMaps, R.layout.list_item_sjcx03,
                new String[] { "checkId", "name", "sex", "evalType", "orgName", "checkDate" },
                new int[] { R.id.id_text, R.id.name_text, R.id.sex_text, R.id.check_type_text,
                        R.id.org_text, R.id.check_date_text });
        jmcxListView.setAdapter(adapter);
        jmcxListView.invalidate();
        zjTextView.setText(visits.size() + "");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (adapter != null) {
            jmcxListView.setAdapter(adapter);
        } else {
            jmcxListView.setAdapter(null);
        }
        jmcxListView.setOnItemClickListener(this);
    }

    /**
     * 进行列表查询
     * 
     * @param pageIndex
     *            要切换到哪个页面进行明细查询，因为明细查询有5种，页面都不一样
     * @param checkSv
     *            ，检查的序号，5种查询都需要序号来查询
     */
    public void requireDetail(int pageIndex, String checkSv) {
        SjglJmcxDetailDialog dialog = new SjglJmcxDetailDialog(mContext);
        dialog.setPage(pageIndex, checkSv);
        dialog.show();
    }
}
