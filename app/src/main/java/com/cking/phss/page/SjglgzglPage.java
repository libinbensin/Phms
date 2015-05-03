/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SjglPage01.java
 * classes : com.cking.phss.page.SjglPage01
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午8:36:56
 */
package com.cking.phss.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromDb;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.BeanUtil.OnResultSaveToDb;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.innner.Record;
import com.cking.phss.dto.sjgl.GzglHdw01;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.Resident;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.application.MyApplication;
import com.cking.phss.util.ProgressDialogUtil;
import com.cking.phss.util.Session;
import com.cking.phss.util.SjglGzglDownloader;
import com.cking.phss.util.SjglGzglDownloader.OnLoadUuidLisener;
import com.cking.phss.util.SjglGzglDownloader.OnSjglGzlbDownloadLisener;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.MultipleChoiceText;
import com.cking.phss.widget.MultipleChoiceText.OnSetFinishedListener;
import com.cking.phss.widget.SetDatespanDialog;
import com.cking.phss.widget.SetDatespanDialog.OnResultListener;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 数据库更新说明：<br/>
 * 操作时间及列表上显示的检查时间。<br/>
 * com.cking.phss.page.SjglgzglPage
 * 
 * @author Wation.Haliyoo <br/>
 *         create at 2014-8-8 上午11:11:50
 */
public class SjglgzglPage extends LinearLayout {
    private static final String TAG = "SjglgzglPage";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;
    private final static boolean DEBUG = false;

    SpinnerUtil xmlbSpinnerUtil;
    MultipleChoiceText xmmcMultipleChoiceText;
    RadioGroup gzglRadioGroup;
    ListView gzglListView;
    CheckBox listTotalCheckBox;
    TextView zjTextView;
    TextView ywcTextView;
    TextView wwcTextView;
    Button downloadButton;
    TextView zdyButton;

    private int gzglSelectIndex = -1;

    LinearLayout titleLinearLayout;
    View[] needResetViews = null;
    String beginTime = "";
    String endTime = "";
    private ProgressDialog progressDialog = null;

    private List<Record> mDbRecords = new ArrayList<Record>();
    private List<Record> mWebRecords = null;
    private List<Record> mRawRecords = null;
    private List<Record> mShownRecords = new ArrayList<Record>();
    private List<Record> mSelectedRecords = new ArrayList<Record>();
    private List<Record> mDownloadRecords = new ArrayList<Record>();
    ListAdapter adapter = new ListAdapter();

    private CheckBoxGroupUtil wcztCheckBoxGroupUtil;
    private int[] wcztViewIds = new int[] { R.id.wczt01CheckBox, R.id.wczt02CheckBox };
    private CheckBoxGroupUtil xzztCheckBoxGroupUtil;
    private int[] xzztViewIds = new int[] { R.id.xzzt01CheckBox, R.id.xzzt02CheckBox };

    public SjglgzglPage(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglgzglPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_gzgl_01_layout, this);

        loadPage(context, this);

        // TODO 测试代码
        if (DEBUG) {
            List<Record> records = new ArrayList<Record>();
            Record record = new Record();
            record.ResidentName = "hello";
            record.SerialNumber = "21";
            record.Finish = new BeanCD("1", "已完成");
            records.add(record);

            record = new Record();
            record.ResidentName = "hello2";
            record.SerialNumber = "32";
            record.Finish = new BeanCD("0", "未完成");
            records.add(record);

            record = new Record();
            record.ResidentName = "tt";
            record.SerialNumber = "22";
            record.Finish = new BeanCD("0", "未完成");
            records.add(record);

            record = new Record();
            record.ResidentName = "t23t";
            record.SerialNumber = "32";
            record.Finish = new BeanCD("1", "已完成");
            records.add(record);

            record = new Record();
            record.ResidentName = "wation";
            record.SerialNumber = "21";
            record.Finish = new BeanCD("0", "未完成");
            records.add(record);
            initWebListView(records);
        }
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xmlbSpinnerUtil = (SpinnerUtil) findViewById(R.id.xmlbSpinnerUtil);
        xmmcMultipleChoiceText = (MultipleChoiceText) findViewById(R.id.xmmcMultipleChoiceText);
        gzglRadioGroup = (RadioGroup) findViewById(R.id.gzglRadioGroup);
        gzglListView = (ListView) findViewById(R.id.gzglListView);
        titleLinearLayout = (LinearLayout) findViewById(R.id.titleLinearLayout);
        gzglListView.setAdapter(adapter);
        String[] ids = ResourcesFactory.listId(mContext, "wczt");
        wcztCheckBoxGroupUtil = new CheckBoxGroupUtil(wcztViewIds, viewGroup, ids);
        ids = ResourcesFactory.listId(mContext, "xzzt");
        xzztCheckBoxGroupUtil = new CheckBoxGroupUtil(xzztViewIds, viewGroup, ids);
        listTotalCheckBox = (CheckBox) findViewById(R.id.listTotalCheckBox);
        zjTextView = (TextView) findViewById(R.id.zjTextView);
        ywcTextView = (TextView) findViewById(R.id.ywcTextView);
        wwcTextView = (TextView) findViewById(R.id.wwcTextView);
        downloadButton = (Button) findViewById(R.id.downloadButton);
        zdyButton = (Button) findViewById(R.id.zdyButton);
        if (Global.isLocalLogin) {
            downloadButton.setEnabled(false);
        } else {
            downloadButton.setEnabled(true);
        }
        downloadButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mSelectedRecords.size() == 0) {
                    mToast.setText("您没有选中任何项目，请至少选择一个项目进行下载");
                    mToast.show();
                    return;
                }

                mDownloadRecords.clear();
                mDownloadRecords.addAll(mSelectedRecords);
                processDownloadQueue();
            }
        });

        listTotalCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setListViewCheckedAll(gzglListView, isChecked); // 全部选中
            }
        });

        xmlbSpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                xmmcMultipleChoiceText.setText("");
                String namePrefix = "gzglyjxm";
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
                    xmmcMultipleChoiceText.setName(name);
                    xmmcMultipleChoiceText.setCheckedAll(true);
                } else {
                    name = namePrefix + suffix;
                    xmmcMultipleChoiceText.setName(name);
                    xmmcMultipleChoiceText.setCheckedAll(true);

                }
                // switch (arg2) {
                // case 0:
                // xmmcMultipleChoiceText.setName("lbcxyjxm2+lbcxyjxm3+lbcxyjxm4");
                // xmmcMultipleChoiceText.setCheckedAll(true);
                // break;
                // case 1:
                // xmmcMultipleChoiceText.setName("gzglyjxm2");
                // xmmcMultipleChoiceText.setCheckedAll(true);
                // break;
                // case 2:
                // xmmcMultipleChoiceText.setName("gzglyjxm3");
                // xmmcMultipleChoiceText.setCheckedAll(true);
                // break;
                // case 3:
                // xmmcMultipleChoiceText.setName("gzglyjxm4");
                // xmmcMultipleChoiceText.setCheckedAll(true);
                // break;
                // case 4:
                // xmmcMultipleChoiceText.setName("gzglyjxm4");
                // xmmcMultipleChoiceText.setCheckedAll(true);
                // break;
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        xmmcMultipleChoiceText.setOnSetFinishedListener(new OnSetFinishedListener() {

            @Override
            public void onSetFinished(String[] dataSource, String[] valueSource) {
                updateListView();
            }
        });
        for (int i = 0; i < wcztCheckBoxGroupUtil.size(); i++) {
            CheckBox checkBox = wcztCheckBoxGroupUtil.getCheckBox(i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    updateListView();
                }
            });
        }
        for (int i = 0; i < xzztCheckBoxGroupUtil.size(); i++) {
            CheckBox checkBox = xzztCheckBoxGroupUtil.getCheckBox(i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    updateListView();
                }
            });
        }

        gzglRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                int tag = Integer.parseInt((String) rb.getTag());
                gzglSelectIndex = tag;
                zdyButton.setVisibility(View.GONE);
                switch (tag) {
                    case 0: { // 本月
                        Date firstDay = CalendarUtil.getMonthStart(new Date());
                        Date lastDay = CalendarUtil.getMonthEnd(new Date());
                        String beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        initDbListView(firstDay, lastDay);
                        require(beginTime, endTime);
                        break;
                    }
                    case 1: {// 下月
                        Date nextMonth = CalendarUtil.getNextMonth(new Date());
                        Date firstDay = CalendarUtil.getMonthStart(nextMonth);
                        Date lastDay = CalendarUtil.getMonthEnd(nextMonth);
                        String beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        initDbListView(firstDay, lastDay);
                        require(beginTime, endTime);
                        break;
                    }
                    case 2: {// 本年
                        String beginTime = new SimpleDateFormat("yyyy-01-01").format(new Date());
                        String endTime = new SimpleDateFormat("yyyy-12-31").format(new Date());
                        try {
                            Date firstDay = new SimpleDateFormat("yyyy-MM-dd").parse(beginTime);
                            Date lastDay = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
                            initDbListView(firstDay, lastDay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        require(beginTime, endTime);
                        break;
                    }

                    case 3: // 自定义
                        break;
                }
            }
        });
        // RadioButton gzgl01RadioButton = (RadioButton)
        // findViewById(R.id.gzgl01RadioButton);
        // gzgl01RadioButton.setChecked(true);

        RadioButton gzgl04RadioButton = (RadioButton) findViewById(R.id.gzgl04RadioButton);
        gzgl04RadioButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SetDatespanDialog dialog = new SetDatespanDialog(mContext);
                dialog.show();
                dialog.setOnResultListener(new OnResultListener() {

                    @Override
                    public void onConfirm(String startDate, String stopDate) {

                        try {
                            Date firstDay = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                            Date lastDay = new SimpleDateFormat("yyyy-MM-dd").parse(stopDate);
                            initDbListView(firstDay, lastDay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        zdyButton.setVisibility(View.VISIBLE);
                        zdyButton.setText(startDate + " ~ " + stopDate);
                        require(startDate, stopDate);
                    }

                    @Override
                    public void onCancel() {
                        // 都不选中
                        gzglSelectIndex = -1;
                        RadioButton gzgl04RadioButton = (RadioButton) findViewById(R.id.gzgl04RadioButton);
                        gzgl04RadioButton.setChecked(false);
                        updateListView();
                    }
                });
            }
        });
    }

    /**
     * 
     */
    protected void processDownloadQueue() {

        // 获取下来信息
        for (final Record record : mDownloadRecords) {
            BeanCD projectType = record.ProjectType;
            final String residentName = record.ResidentName;
            final String residentID = record.ResidentID;
            if (record.Download.getcD().equals("0")) { // 未下载
                if (projectType.getcD().equals("41")) { // 高血压随访
                    // 下载档案信息，报卡信息，最后一次随访记录
                    downloadGxyData(residentName, residentID, record);
                    break;// 跳出循环，等下载完再进入这个函数
                } else if (projectType.getcD().equals("42")) { // 糖尿病随访
                    // 下载档案信息，报卡信息，最后一次随访记录
                    downloadTnbData(residentName, residentID, record);
                    break;// 跳出循环，等下载完再进入这个函数
                }
            }
        }
    }

    /**
     * 
     */
    protected void downloadGxyData(final String residentName, final String residentID,
            final Record record) {
        final Session session = MyApplication.getInstance().getSession();
        final Resident lastResident = session.getCurrentResident(); // 保留现场

        final Map<String, IBean> beanMap = new HashMap<String, IBean>();
        beanMap.put(Jmjbxx.class.getName(), new Jmjbxx());
        beanMap.put(Jmjtxx.class.getName(), new Jmjtxx());
        beanMap.put(Jmjkxx.class.getName(), new Jmjkxx());
        beanMap.put(Jmxwxg.class.getName(), new Jmxwxg());
        beanMap.put(Sfgljl_gxy.class.getName(), new Sfgljl_gxy());
        // 下载档案信息，报卡信息，最后一次随访记录
        SjglGzglDownloader downloader = new SjglGzglDownloader();
        downloader.setOnSjglGzlbDownloadLisener(new OnSjglGzlbDownloadLisener() {

            @Override
            public void onDownloadFinished(boolean isSuccessful, String tipText) {
                mToast.setText("下载  - 用户 ‘" + residentName + "’ - " + tipText);
                mToast.show();
                if (isSuccessful) { // 下载随访记录
                    SjglGzglDownloader downloader = new SjglGzglDownloader();
                    downloader.setOnSjglGzlbDownloadLisener(new OnSjglGzlbDownloadLisener() {

                        @Override
                        public void onDownloadFinished(boolean isSuccessful, String tipText) {
                            mToast.setText("下载  - 用户 ‘" + residentName + "’ - " + tipText);
                            mToast.show();
                            ProgressDialogUtil.hideProgressDialog();
                            session.setCurrentResident(lastResident); // 恢复现场

                            updateListView();

                            // 继续下载
                            processDownloadQueue();
                        }
                    });
                    downloader.setOnLoadUuidLisener(new OnLoadUuidLisener() {

                        @Override
                        public void onLoadUuid(String residentUUID, String projectUUID) {
                            // 保存下载信息
                            record.Download = new BeanCD("1", "已下载");

                            Log.i(TAG, "downloadGxyData - onLoadUuid");
                            saveRecordToDb(residentUUID, projectUUID, record);
                        }
                    });
                    downloader.getLastGxySfxxAndSaveGxyjlxxxxToDb(mContext, beanMap, residentID);
                } else {
                    ProgressDialogUtil.hideProgressDialog();
                    session.setCurrentResident(lastResident); // 恢复现场
                }
            }

        });
        ProgressDialogUtil.showProgressDialog(mContext, "正在下载", "请稍等...");
        downloader.getJbxxFromWebAndSaveToDb(mContext, beanMap, residentID);
    }

    /**
     * 
     */
    protected void downloadTnbData(final String residentName, final String residentID,
            final Record record) {
        final Session session = MyApplication.getInstance().getSession();
        final Resident lastResident = session.getCurrentResident(); // 保留现场

        final Map<String, IBean> beanMap = new HashMap<String, IBean>();
        beanMap.put(Jmjbxx.class.getName(), new Jmjbxx());
        beanMap.put(Jmjtxx.class.getName(), new Jmjtxx());
        beanMap.put(Jmjkxx.class.getName(), new Jmjkxx());
        beanMap.put(Jmxwxg.class.getName(), new Jmxwxg());
        beanMap.put(Sfgljl_tnb.class.getName(), new Sfgljl_tnb());
        // 下载档案信息，报卡信息，最后一次随访记录
        SjglGzglDownloader downloader = new SjglGzglDownloader();
        downloader.setOnSjglGzlbDownloadLisener(new OnSjglGzlbDownloadLisener() {

            @Override
            public void onDownloadFinished(boolean isSuccessful, String tipText) {
                mToast.setText("下载  - 用户 ‘" + residentName + "’ - " + tipText);
                mToast.show();
                if (isSuccessful) { // 下载随访记录
                    SjglGzglDownloader downloader = new SjglGzglDownloader();
                    downloader.setOnSjglGzlbDownloadLisener(new OnSjglGzlbDownloadLisener() {

                        @Override
                        public void onDownloadFinished(boolean isSuccessful, String tipText) {
                            mToast.setText("下载  - 用户 ‘" + residentName + "’ - " + tipText);
                            mToast.show();
                            ProgressDialogUtil.hideProgressDialog();
                            session.setCurrentResident(lastResident); // 恢复现场

                            updateListView();

                            // 继续下载
                            processDownloadQueue();
                        }
                    });
                    downloader.setOnLoadUuidLisener(new OnLoadUuidLisener() {

                        @Override
                        public void onLoadUuid(String residentUUID, String projectUUID) {
                            // 保存下载信息
                            record.Download = new BeanCD("1", "已下载");

                            Log.i(TAG, "downloadTnbData - onLoadUuid");
                            saveRecordToDb(residentUUID, projectUUID, record);
                        }
                    });
                    downloader.getLastTnbSfxxAndSaveTnbjlxxxxToDb(mContext, beanMap, residentID);
                } else {
                    ProgressDialogUtil.hideProgressDialog();
                    session.setCurrentResident(lastResident); // 恢复现场
                }
            }

        });
        ProgressDialogUtil.showProgressDialog(mContext, "正在查询", "请稍等...");
        downloader.getJbxxFromWebAndSaveToDb(mContext, beanMap, residentID);
    }

    /**
     * @param gzglListView2
     * @param isChecked
     */
    protected void setListViewCheckedAll(ListView listView, boolean isChecked) {
        for (int i = 0; i < listView.getChildCount(); i++) {
            View view = listView.getChildAt(i);
            CheckBox gxCheckBox = (CheckBox) view.findViewById(R.id.gxCheckBox);
            if (gxCheckBox.isEnabled()) { // 只对有效按钮才可选中
                gxCheckBox.setChecked(isChecked);
            }
        }
    }

    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mShownRecords.size();
        }

        @Override
        public Object getItem(int position) {
            return mShownRecords.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.item_gzgl_layout, null);
                if (position % 2 == 1) {
                    convertView.setBackgroundResource(R.color.list_jsh_background_color);
                } else {
                    convertView.setBackgroundResource(R.color.list_osh_background_color);
                }

                CheckBox gxCheckBox = (CheckBox) convertView.findViewById(R.id.gxCheckBox);
                TextView xhTextView = (TextView) convertView.findViewById(R.id.xhTextView);
                TextView xmTextView = (TextView) convertView.findViewById(R.id.xmTextView);
                TextView xbTextView = (TextView) convertView.findViewById(R.id.xbTextView);
                TextView xmmcTextView = (TextView) convertView.findViewById(R.id.xmmcTextView);
                TextView jgTextView = (TextView) convertView.findViewById(R.id.jgTextView);
                TextView zrysTextView = (TextView) convertView.findViewById(R.id.zrysTextView);
                TextView wcztTextView = (TextView) convertView.findViewById(R.id.wcztTextView);
                TextView xzztTextView = (TextView) convertView.findViewById(R.id.xzztTextView);
                TextView wcrqTextView = (TextView) convertView.findViewById(R.id.wcrqTextView);
            if (position >= 0 && position < mShownRecords.size()) {

                /*
                 * List<Integer> widths = new ArrayList<Integer>(); for (int i =
                 * 0; i < titleLinearLayout.getChildCount(); i++) { View
                 * childView = titleLinearLayout.getChildAt(i); if (childView
                 * instanceof TextView) { int width = childView.getWidth();
                 * widths.add(width); } }
                 */
                // width:[40, 141, 90, 90, 182, 142, 131, 131, 131, 131]

                // Log.i(TAG, "width:" + widths);
                needResetViews = new View[] { xhTextView, xmTextView, xbTextView, xmmcTextView,
                        jgTextView, zrysTextView, wcztTextView, wcrqTextView };
                // setViewByWidths(widths);

                // TODO 完成数据加载
                Record record = mShownRecords.get(position);
                if (record.Download.getcD().equals("1")) { // 已下载
                    gxCheckBox.setEnabled(false);
                } else {
                    gxCheckBox.setEnabled(true);
                }
                gxCheckBox.setTag(record);
                gxCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                        Record record = (Record) arg0.getTag();
                        if (arg1) {
                            mSelectedRecords.add(record);
                        } else {
                            mSelectedRecords.remove(record);
                        }
                    }
                });
                xhTextView.setText((position + 1) + "");
                xmTextView.setText(record.ResidentName);
                if (record.Sex != null) {
                    String xb = ResourcesFactory.findValue(mContext, "xb", record.Sex.getcD());
                    xbTextView.setText(xb);
                }
                if (record.ProjectType != null) {
                    xmmcTextView.setText(record.ProjectType.getTagValue());
                }
                if (record.Organization != null) {
                    jgTextView.setText(record.Organization.getTagValue());
                }
                zrysTextView.setText(record.Doctor);
                if (record.Finish == null) { // 如果状态没有值则赋值未完成
                    record.Finish = new BeanCD("0", "未完成");
                }
                wcztTextView.setText(record.Finish.getTagValue());
                xzztTextView.setText(record.Download.getTagValue());
                wcrqTextView.setText(record.CheckDate);
            }
            // }

            return convertView;
        }

        /**
         * @param widths
         */
        public void setViewByWidths(List<Integer> widths) {
            for (int i = 0; i < needResetViews.length; i++) {
                View childView = needResetViews[i];
                if (childView instanceof TextView) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childView
                            .getLayoutParams();
                    params.width = widths.get(i);
                    childView.setLayoutParams(params);
                }

            }
        }
    }

    private void require(String beginTime, String endTime) {
        if (Global.isLocalLogin) {
            return;
        }

        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }

        GzglHdw01 gzglHdw01 = new GzglHdw01();
        gzglHdw01.request = new GzglHdw01.Request();

        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        int userID = Integer.parseInt(stringuserID);
        gzglHdw01.request.UserID = userID;
        gzglHdw01.request.DoctorName = Global.doctorName;
        gzglHdw01.request.FirstProject = new BeanCD("1", "所有项目");
        String ids = "";
        String values = "";
        for (int i = 2; i <= 4; i++) {
            String[] lbcxyjxmId = ResourcesFactory.listId(mContext, "lbcxyjxm" + i);
            for (String item : lbcxyjxmId) {
                if (ids == "") {
                    ids = item;
                } else {
                    ids = ids + "|" + item;
                }
            }
            String[] lbcxyjxmValue = ResourcesFactory.listValue(mContext, "lbcxyjxm" + i);
            for (String item : lbcxyjxmValue) {
                if (values == "") {
                    values = item;
                } else {
                    values = values + "|" + item;
                }
            }
        }
        gzglHdw01.request.SecondProject = new BeanCD(ids, values);
        gzglHdw01.request.SDate = beginTime;
        gzglHdw01.request.EDate = endTime;
        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为
        beanList.add(gzglHdw01); // 添加工作管理idto
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
                    GzglHdw01 responseGzglHdw01 = (GzglHdw01) listBean.get(0);
                    if (responseGzglHdw01 == null || responseGzglHdw01.response == null) {
                        sb.append("【工作管理】服务器接口异常");
                    } else {
                        if (responseGzglHdw01.response.errMsg.length() > 0) {
                            sb.append("【工作管理】" + responseGzglHdw01.response.errMsg);
                        } else {
                            if (responseGzglHdw01.response.Records == null) {
                                sb.append("【工作管理】列表没有更新");
                            } else {
                                sb.append("【工作管理】列表更新完成");
                                // 保存成功的话，更新视图
                                initWebListView(responseGzglHdw01.response.Records);
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

    protected void initWebListView(List<Record> records) { // 初始化列表
        mWebRecords = records;
        // 过滤相同的
        if (mRawRecords != null && mRawRecords.size() > 0) {
            List<Record> tmpRecordList = new ArrayList<Record>();
            for (Record record : mWebRecords) {
                boolean theSame = false;
                for (Record record2 : mRawRecords) {
                    if (theSameRecord(record, record2)) {
                        if (!record2.Finish.sameValue(record.Finish)) {
                            record2.Finish = record.Finish; // 修改完成状态
                            record.Download = record2.Download;
                            Log.i(TAG, "initWebListView");
                            // 更新数据库
                            saveRecordToDb(record2.ResidentUUID, record2.ProjectUUID, record);
                        }
                        theSame = true;
                        break;
                    }
                }
                if (!theSame) {
                    tmpRecordList.add(record);
                }
            }
            mRawRecords.addAll(tmpRecordList);
        } else {
            mRawRecords = mWebRecords;
        }
        updateListView();
    }

    /**
     * @param record
     * @param record2
     * @return
     */
    private boolean theSameRecord(Record record, Record record2) {
        if (StringUtil.isEmptyString(record.ResidentID)
                || StringUtil.isEmptyString(record2.ResidentID)) {
            return false;
        }

        if (record.ProjectType == null || record2.ProjectType == null) {
            return false;
        }
        if (record.ResidentID.equals(record2.ResidentID)
                && record.SerialNumber.equals(record2.SerialNumber)
                && record.ProjectType.getcD().equals(record2.ProjectType.getcD())) {
            return true;
        }

        return false;
    }

    protected void initDbListView(final Date firstDay, final Date lastDay) { // 初始化列表
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Record.class);

        mRawRecords = null;
        BeanUtil.getInstance().getSjglListFromDb(listBean, firstDay.getTime(), lastDay.getTime(),
                new OnResultFromDb() {

                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        mDbRecords.clear();
                        if (isSucc) {
                            for (IBean bean : listBean) {
                                mDbRecords.add((Record) bean);
                            }
                            // 覆盖相同的
                            if (mRawRecords != null && mRawRecords.size() > 0) {
                                List<Record> tmpRecordList = new ArrayList<Record>();
                                for (Record record : mDbRecords) {
                                    boolean theSame = false;
                                    for (Record record2 : mRawRecords) {
                                        if (theSameRecord(record, record2)) {
                                            theSame = true;
                                            break;
                                        }
                                        if (theSame) {
                                            record2.Download = new BeanCD("1", "已下载");
                                            if (!record2.Finish.sameValue(record.Finish)) {
                                                record2.Finish = record.Finish; // 修改完成状态
                                                Log.i(TAG, "initDbListView");
                                                // 更新数据库
                                                saveRecordToDb(record.ResidentUUID,
                                                        record.ProjectUUID, record);
                                            }
                                        } else {
                                            tmpRecordList.add(record);
                                        }
                                    }
                                }
                                mRawRecords.addAll(tmpRecordList);
                            } else {
                                mRawRecords = mDbRecords;
                            }
                            updateListView();
                        }
                    }

                });
    }

    protected void updateListView() { // 更新列表
        int zj = 0;
        int ywc = 0;
        int wwc = 0;
        mShownRecords.clear();
        if (mRawRecords != null) {
            // 根据条件显示

            String xmmcIds = xmmcMultipleChoiceText.getCheckedValues(",");
            String wcztIds = wcztCheckBoxGroupUtil.getCheckValues(",");
            String xzztIds = xzztCheckBoxGroupUtil.getCheckValues(",");
            for (Record record : mRawRecords) {
                if (record.Finish == null) { // 如果状态没有值则赋值未完成
                    record.Finish = new BeanCD("0", "未完成");
                }
                // 说明：下载状态初始化为未下载，下载后更新到数据库是为已下载，所已只要对未设置数据进行初始化即可
                if (record.Download == null) { // 如果状态没有值则赋值未完成
                    record.Download = new BeanCD("0", "未下载");
                }
                if (StringUtil.contains(xmmcIds, record.ProjectType.getcD(), ",")
                        && StringUtil.contains(wcztIds, record.Finish.getcD(), ",")
                        && StringUtil.contains(xzztIds, record.Download.getcD(), ",")) {
                    if (record.Finish.getcD().equals("0")) {
                        wwc++;
                    } else {
                        ywc++;
                    }
                    mShownRecords.add(record);
                }
            }
        }
        adapter.notifyDataSetChanged();
        listTotalCheckBox.setChecked(false);

        // 设置底部提示数目
        zj = wwc + ywc;
        zjTextView.setText(zj + "");
        ywcTextView.setText(ywc + "");
        wwcTextView.setText(wwc + "");
    }

    //
    // /**
    // * @param record
    // * @return
    // * @throws ParseException
    // */
    // private BeanCD getDownloadStatus(Record record) throws ParseException {
    // BeanCD projectType = record.ProjectType;
    // final String residentID = record.ResidentID;
    // final String serialNumber = record.SerialNumber;
    // long downloadTime = SjglBll
    // .queryOperTime(residentID, serialNumber, projectType.getcD());
    // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    // Date beginTime = format.parse(this.beginTime);
    // Date endTime = format.parse(this.endTime);
    // if (downloadTime >= beginTime.getTime() && downloadTime <=
    // endTime.getTime()) {
    // return new BeanCD("1", "已下载");
    // } else {
    // return new BeanCD("0", "未下载");
    // }
    // }

    private void saveRecordToDb(String residentUUID, String projectUUID, Record record) {
        Log.i(TAG, "saveRecordToDb, residentUUID:" + residentUUID + ", projectUUID:" + projectUUID
                + ", checkDate:" + record.CheckDate);
        List<IBean> listBean = new ArrayList<IBean>();
        listBean.add(record);
        record.ResidentUUID = residentUUID;
        record.ProjectUUID = projectUUID;
        record.ClassName = Record.class.getName();
        // 数据库操作时间等于检查时间
        long downloadTime = CalendarUtil.parseDate(record.CheckDate).getTime();
        BeanUtil.getInstance().saveSjglToDb(listBean, residentUUID, projectUUID,
                record.ProjectType.getcD(), downloadTime, new OnResultSaveToDb() {
                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                    }
                });
    }
    // /**
    // * @param record
    // * @return
    // * @throws ParseException
    // */
    // private BeanCD getDownloadStatus(Record record) throws ParseException {
    // BeanCD projectType = record.ProjectType;
    // final String residentName = record.ResidentName;
    // final String residentID = record.SerialNumber;
    // if (projectType.getcD().equals("41")) { // 高血压随访
    // Sfgl sfgl = SfglBll.queryLastDownload(Sfgl.class.getName(), residentID);
    //
    // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    // Date beginTime = format.parse(this.beginTime);
    // Date endTime = format.parse(this.endTime);
    // if (sfgl.getDownloadDateTime() >= beginTime.getTime()
    // && sfgl.getDownloadDateTime() <= endTime.getTime()) {
    // return new BeanCD("1", "已下载");
    // } else {
    // return new BeanCD("0", "未下载");
    // }
    // } else if (projectType.getcD().equals("42")) { // 糖尿病随访
    // Sfgl sfgl = SfglBll.queryLastDownload(Sfgl.class.getName(), residentID);
    //
    // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    // Date beginTime = format.parse(this.beginTime);
    // Date endTime = format.parse(this.endTime);
    // if (sfgl.getDownloadDateTime() >= beginTime.getTime()
    // && sfgl.getDownloadDateTime() <= endTime.getTime()) {
    // return new BeanCD("1", "已下载");
    // } else {
    // return new BeanCD("0", "未下载");
    // }
    // } else {
    // return new BeanCD("0", "未下载");
    // }
    // }
}
