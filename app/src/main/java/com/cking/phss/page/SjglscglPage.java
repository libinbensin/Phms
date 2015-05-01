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
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.cking.phss.bean.BeanUtil.OnResultSaveToDb;
import com.cking.phss.bean.BeanUtil.OnResultTableQury;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.BeanUtil.TableQuryCondition;
import com.cking.phss.bean.BeanUtil.TableQuryResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.innner.LocalRecord;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.Resident;
import com.cking.phss.sqlite.SjglBll;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.ProgressDialogUtil;
import com.cking.phss.util.Session;
import com.cking.phss.util.SjglScglUploader;
import com.cking.phss.util.SjglScglUploader.OnSjglScglUploadLisener;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.MultipleChoiceText;
import com.cking.phss.widget.SetDatespanDialog;
import com.cking.phss.widget.SetDatespanDialog.OnResultListener;

/**
 * com.cking.phss.page.SjglPage01 显示数据查询列表的页面
 */
public class SjglscglPage extends LinearLayout {
    private static final String TAG = "SjglscglPage";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;
    private final static boolean DEBUG = false;

    RadioGroup scglRadioGroup;
    Button deleteButton;
    Button uploadButton;
    ListView scglListView;

    MultipleChoiceText xmmcMultipleChoiceText;
    CheckBox listTotalCheckBox;
    TextView zjTextView;
    TextView yscTextView;
    TextView wscTextView;
    CheckBox xmsx01CheckBox;
    TextView zdyButton;

    LinearLayout titleLinearLayout;
    View[] needResetViews = null;
    Date firstDay;
    Date lastDay;
    private int scglSelectIndex = -1;

//    List<Sjgl> mDbFullDatas = null;
    private List<LocalRecord> mDbRecords = new ArrayList<LocalRecord>();
    private List<LocalRecord> mRawRecords = null;
    private List<LocalRecord> mShownRecords = new ArrayList<LocalRecord>();
    private List<LocalRecord> mSelectedRecords = new ArrayList<LocalRecord>();
    ListAdapter adapter = new ListAdapter();

    private CheckBoxGroupUtil scztCheckBoxGroupUtil;
    private int[] scztViewIds = new int[] { R.id.sczt01CheckBox, R.id.sczt02CheckBox };

    private CheckBoxGroupUtil xmsxCheckBoxGroupUtil;
    private int[] xmsxViewIds = new int[] { R.id.xmsx02CheckBox, R.id.xmsx03CheckBox,
            R.id.xmsx04CheckBox, R.id.xmsx05CheckBox };

    public SjglscglPage(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglscglPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_scgl_01_layout, this);

        loadPage(context, this);

        // TODO 测试代码
        if (DEBUG) {
            List<LocalRecord> records = new ArrayList<LocalRecord>();
            LocalRecord record = new LocalRecord();
            record.ResidentName = "hello";
            record.ProjectType = new BeanCD("21", "档案");
            record.DataSource = new BeanCD("1", "档案平台");
            record.OperType = new BeanCD("1", "原始");
            record.Upload = new BeanCD("1", "已上传");
            records.add(record);

            record = new LocalRecord();
            record.ResidentName = "hello2";
            record.ProjectType = new BeanCD("21", "档案");
            record.DataSource = new BeanCD("1", "档案平台");
            record.OperType = new BeanCD("1", "原始");
            record.Upload = new BeanCD("0", "未上传");
            records.add(record);

            record = new LocalRecord();
            record.ResidentName = "tt";
            record.ProjectType = new BeanCD("21", "档案");
            record.DataSource = new BeanCD("2", "非档案平台");
            record.OperType = new BeanCD("2", "修改");
            record.Upload = new BeanCD("1", "已上传");
            records.add(record);

            record = new LocalRecord();
            record.ResidentName = "t23t";
            record.ProjectType = new BeanCD("31", "快速体检");
            record.DataSource = new BeanCD("1", "档案平台");
            record.OperType = new BeanCD("2", "修改");
            record.Upload = new BeanCD("0", "未上传");
            records.add(record);

            record = new LocalRecord();
            record.ResidentName = "wation";
            record.ProjectType = new BeanCD("21", "档案");
            record.DataSource = new BeanCD("1", "档案平台");
            record.OperType = new BeanCD("3", "新建");
            record.Upload = new BeanCD("1", "已上传");
            records.add(record);
            initListView(records);
        }
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        scglRadioGroup = (RadioGroup) findViewById(R.id.scglRadioGroup);
        uploadButton = (Button) findViewById(R.id.uploadButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        scglListView = (ListView) findViewById(R.id.scglListView);
        titleLinearLayout = (LinearLayout) findViewById(R.id.titleLinearLayout);
        scglListView.setAdapter(adapter);
        String[] ids = ResourcesFactory.listId(mContext, "sczt");
        scztCheckBoxGroupUtil = new CheckBoxGroupUtil(scztViewIds, viewGroup, ids);
        ids = ResourcesFactory.listId(mContext, "scglxmsx");
        xmsxCheckBoxGroupUtil = new CheckBoxGroupUtil(xmsxViewIds, viewGroup, ids);
        listTotalCheckBox = (CheckBox) findViewById(R.id.listTotalCheckBox);
        zjTextView = (TextView) findViewById(R.id.zjTextView);
        yscTextView = (TextView) findViewById(R.id.yscTextView);
        wscTextView = (TextView) findViewById(R.id.wscTextView);
        xmsx01CheckBox = (CheckBox) findViewById(R.id.xmsx01CheckBox);
        zdyButton = (Button) findViewById(R.id.zdyButton);
        if (Global.isLocalLogin) {
            uploadButton.setEnabled(false);
        } else {
            uploadButton.setEnabled(true);
        }
        xmsx01CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                xmsxCheckBoxGroupUtil.setCheckedAll(arg1);
            }
        });

        deleteButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 获取下来信息
                List<TableQuryCondition> beanList = new ArrayList<TableQuryCondition>();
                for (final LocalRecord record : mSelectedRecords) {
                    deleteRecordFromDb(record); // 删除数据管理中的数据

                    // 删除对应表数据
                    BeanCD projectType = record.ProjectType;
                    final String residentName = record.ResidentName;
                    final String residentID = record.ResidentID;
                    final String residentUUID = record.ResidentUUID;
                    String projectUUID = record.ProjectUUID;
                    String className = record.ClassName;
                    if (projectType.getcD().equals("41")) { // 高血压随访
                        beanList.add(new TableQuryCondition(residentUUID, projectUUID,
                                Sfgljl_gxy.class, record));
                    } else if (projectType.getcD().equals("42")) { // 糖尿病随访
                        beanList.add(new TableQuryCondition(residentUUID, projectUUID,
                                Sfgljl_tnb.class, record));
                    } else if (projectType.getcD().equals("21")) { // 档案
                        beanList.add(new TableQuryCondition(residentUUID, projectUUID,
                                Jmjbxx.class, record));
                        beanList.add(new TableQuryCondition(residentUUID, projectUUID,
                                Jmjtxx.class, record));
                        beanList.add(new TableQuryCondition(residentUUID, projectUUID,
                                Jmjkxx.class, record));
                        beanList.add(new TableQuryCondition(residentUUID, projectUUID,
                                Jmxwxg.class, record));
                    } else if (projectType.getcD().equals("31")) { // 快速体检
                        beanList.add(new TableQuryCondition(residentUUID, projectUUID,
                                Jktj_kstj.class, record));
                    }
                }

                ProgressDialogUtil.showProgressDialog(mContext, "正在删除", "请稍等...");
                BeanUtil.getInstance().beanDeleteFromDb(beanList, new OnResultTableQury() {

                    @Override
                    public void onResult(List<TableQuryResult> listBean, boolean isSucc) {
                        ProgressDialogUtil.hideProgressDialog();
                        initDbListView(firstDay, lastDay);
                    }
                });
            }
        });
        // uploadButton.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // // 获取下来信息
        // List<TableQuryCondition> beanList = new
        // ArrayList<TableQuryCondition>();
        // for (final LocalRecord record : mSelectedRecords) {
        // BeanCD projectType = record.ProjectType;
        // final String residentName = record.ResidentName;
        // final String residentID = record.ResidentID;
        // final String residentUUID = record.ResidentUUID;
        // String projectUUID = record.ProjectUUID;
        // String className = record.ClassName;
        // if (projectType.getcD().equals("41")) { // 高血压随访
        // beanList.add(new TableQuryCondition(residentUUID, projectUUID,
        // Sfgljl_gxy.class, record));
        // } else if (projectType.getcD().equals("42")) { // 糖尿病随访
        // beanList.add(new TableQuryCondition(residentUUID, projectUUID,
        // Sfgljl_tnb.class, record));
        // } else if (projectType.getcD().equals("21")) { // 档案
        // beanList.add(new TableQuryCondition(residentUUID, projectUUID,
        // Jmjbxx.class, record));
        // beanList.add(new TableQuryCondition(residentUUID, projectUUID,
        // Jmjtxx.class, record));
        // beanList.add(new TableQuryCondition(residentUUID, projectUUID,
        // Jmjkxx.class, record));
        // beanList.add(new TableQuryCondition(residentUUID, projectUUID,
        // Jmxwxg.class, record));
        // } else if (projectType.getcD().equals("31")) { // 快速体检
        // beanList.add(new TableQuryCondition(residentUUID, projectUUID,
        // Jktj_kstj.class, record));
        // }
        // }
        //
        // ProgressDialogUtil.showProgressDialog(mContext, "正在上传", "请稍等...");
        // BeanUtil.getInstance().beanDbToWeb(beanList, new OnResultTableQury()
        // {
        //
        // @Override
        // public void onResult(List<TableQuryResult> listBean, boolean isSucc)
        // {
        // ProgressDialogUtil.hideProgressDialog();
        // for (TableQuryResult tqr : listBean) {
        // if (tqr.isSucc) {
        // LocalRecord record = ((LocalRecord) tqr.callbackBean);
        // record.Upload = new BeanCD("1", "已上传");
        // saveRecordToDb(record);
        // }
        // }
        // mToast.setText("上传完成，具体请查看列表中的“上传标志”");
        // mToast.show();
        // initDbListView(firstDay, lastDay);
        // }
        // });
        //
        // }
        // });
        uploadButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mSelectedRecords.size() == 0) {
                    mToast.setText("您没有选中任何项目，请至少选择一个项目进行上传");
                    mToast.show();
                    return;
                }

                // 获取下来信息
                List<TableQuryCondition> beanList = new ArrayList<TableQuryCondition>();
                for (final LocalRecord record : mSelectedRecords) {
                    BeanCD projectType = record.ProjectType;
                    final String residentName = record.ResidentName;
                    final String residentID = record.ResidentID;
                    final String residentUUID = record.ResidentUUID;
                    String projectUUID = record.ProjectUUID;
                    String className = record.ClassName;
                    if (projectType.getcD().equals("41")) { // 高血压随访
                        uploadGxyData(residentName, residentID, record);
                    } else if (projectType.getcD().equals("42")) { // 糖尿病随访
                        uploadTnbData(residentName, residentID, record);
                    } else if (projectType.getcD().equals("21")) { // 档案
                        uploadJbxxData(residentName, residentID, record);
                    } else if (projectType.getcD().equals("31")) { // 快速体检
                        uploadKstjData(residentName, residentID, record);
                    }
                }
            }
        });
        scglRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                int tag = Integer.parseInt((String) rb.getTag());
                zdyButton.setVisibility(View.GONE);
                switch (tag) {
                    case 0: { // 最近一周
                        Date firstDay = CalendarUtil.getPreviousWeek(new Date());
                        Date lastDay = new Date();
                        String beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        initDbListView(firstDay, lastDay);
                        require(beginTime, endTime);
                        break;
                    }
                    case 1: {// 最近一月
                        Date firstDay = CalendarUtil.getPreviousMonth(new Date());
                        Date lastDay = new Date();
                        String beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        initDbListView(firstDay, lastDay);
                        require(beginTime, endTime);
                        break;
                    }
                    case 2: {// 最近三月
                        Date firstDay = CalendarUtil.getPreviousMonth(new Date());
                        firstDay = CalendarUtil.getPreviousMonth(firstDay);
                        firstDay = CalendarUtil.getPreviousMonth(firstDay);
                        Date lastDay = new Date();
                        String beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        initDbListView(firstDay, lastDay);
                        require(beginTime, endTime);
                        break;
                    }

                    case 3: {// 自定义
                        break;
                    }
                }
            }
        });
        // RadioButton scgl01RadioButton = (RadioButton)
        // findViewById(R.id.scgl01RadioButton);
        // scgl01RadioButton.setChecked(true);
        RadioButton scgl04RadioButton = (RadioButton) findViewById(R.id.scgl04RadioButton);
        scgl04RadioButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SetDatespanDialog dialog = new SetDatespanDialog(mContext);
                dialog.show();
                dialog.setOnResultListener(new OnResultListener() {

                    @Override
                    public void onConfirm(String beginTime, String endTime) {
                        try {
                            Date firstDay = new SimpleDateFormat("yyyy-MM-dd").parse(beginTime);
                            Date lastDay = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
                            initDbListView(firstDay, lastDay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        zdyButton.setVisibility(View.VISIBLE);
                        zdyButton.setText(beginTime + " ~ " + endTime);
                        require(beginTime, endTime);
                    }

                    @Override
                    public void onCancel() {
                        // 都不选中
                        scglSelectIndex = -1;
                        RadioButton scgl04RadioButton = (RadioButton) findViewById(R.id.scgl04RadioButton);
                        scgl04RadioButton.setChecked(false);
                        updateListView();
                    }
                });
            }
        });

        listTotalCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setListViewCheckedAll(scglListView, isChecked); // 全部选中
            }
        });
        for (int i = 0; i < scztCheckBoxGroupUtil.size(); i++) {
            CheckBox checkBox = scztCheckBoxGroupUtil.getCheckBox(i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    updateListView();
                }
            });
        }

        for (int i = 0; i < xmsxCheckBoxGroupUtil.size(); i++) {
            CheckBox checkBox = xmsxCheckBoxGroupUtil.getCheckBox(i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    updateListView();
                }
            });
        }
    }

    /**
     * @param residentName
     * @param residentID
     * @param record
     */
    protected void uploadKstjData(final String residentName, String residentID,
            final LocalRecord record) {
        Log.i(TAG, "enter uploadKstjData ...");
        final Session session = MyApplication.getInstance().getSession();
        final Resident lastResident = session.getCurrentResident(); // 保留现场

        final Map<String, IBean> beanMap = new HashMap<String, IBean>();
        beanMap.put(Jmjbxx.class.getName(), new Jmjbxx());
        beanMap.put(Jmjtxx.class.getName(), new Jmjtxx());
        beanMap.put(Jmjkxx.class.getName(), new Jmjkxx());
        beanMap.put(Jmxwxg.class.getName(), new Jmxwxg());
        SjglScglUploader uploader = new SjglScglUploader();
        uploader.setOnSjglScglUploadLisener(new OnSjglScglUploadLisener() {

            @Override
            public void onUploadFinished(boolean isSuccessful, String tipText) {
                Log.i(TAG, "enter uploadKstjData - onUploadFinished ...");
                ProgressDialogUtil.hideProgressDialog();
                session.setCurrentResident(lastResident); // 恢复现场
                mToast.setText("上传  - 用户 ‘" + residentName + "’ - " + tipText);
                mToast.show();

                if (isSuccessful) { // 下载随访记录
                    // 保存下载信息
                    record.Upload = new BeanCD("1", "已上传");
                    saveRecordToDb(record);

                    updateListView();
                }
            }
        });
        ProgressDialogUtil.showProgressDialog(mContext, "正在上传", "请稍等...");
        uploader.getJktjFromDbAndSaveToWeb(mContext, beanMap,
                record.ResidentUUID, record.ProjectUUID, record.ResidentID);
    }

    /**
     * @param residentName
     * @param residentID
     * @param record
     */
    protected void uploadJbxxData(final String residentName, final String residentID,
            final LocalRecord record) {
        Log.i(TAG, "enter uploadJbxxData ...");
        final Session session = MyApplication.getInstance().getSession();
        final Resident lastResident = session.getCurrentResident(); // 保留现场

        final Map<String, IBean> beanMap = new HashMap<String, IBean>();
        beanMap.put(Jmjbxx.class.getName(), new Jmjbxx());
        beanMap.put(Jmjtxx.class.getName(), new Jmjtxx());
        beanMap.put(Jmjkxx.class.getName(), new Jmjkxx());
        beanMap.put(Jmxwxg.class.getName(), new Jmxwxg());
        SjglScglUploader uploader = new SjglScglUploader();
        uploader.setOnSjglScglUploadLisener(new OnSjglScglUploadLisener() {

            @Override
            public void onUploadFinished(boolean isSuccessful, String tipText) {
                Log.i(TAG, "enter uploadJbxxData - onUploadFinished ...");
                ProgressDialogUtil.hideProgressDialog();
                session.setCurrentResident(lastResident); // 恢复现场
                mToast.setText("上传  - 用户 ‘" + residentName + "’ - " + tipText);
                mToast.show();

                if (isSuccessful) { // 下载随访记录
                    // 保存下载信息
                    record.Upload = new BeanCD("1", "已上传");
                    saveRecordToDb(record);

                    updateListView();
                }
            }
        });
        ProgressDialogUtil.showProgressDialog(mContext, "正在上传", "请稍等...");
        uploader.getJbxxFromDbAndSaveToWeb(mContext, beanMap,
                record.CredentialsNo);
    }

    /**
     * @param residentName
     * @param residentID
     * @param record
     */
    protected void uploadGxyData(final String residentName, final String residentID,
            final LocalRecord record) {
        Log.i(TAG, "enter uploadGxyData ...");
        final Session session = MyApplication.getInstance().getSession();
        final Resident lastResident = session.getCurrentResident(); // 保留现场

        final Map<String, IBean> beanMap = new HashMap<String, IBean>();
        beanMap.put(Jmjbxx.class.getName(), new Jmjbxx());
        beanMap.put(Jmjtxx.class.getName(), new Jmjtxx());
        beanMap.put(Jmjkxx.class.getName(), new Jmjkxx());
        beanMap.put(Jmxwxg.class.getName(), new Jmxwxg());
        beanMap.put(Sfgljl_gxy.class.getName(), new Sfgljl_gxy());
        SjglScglUploader uploader = new SjglScglUploader();
        uploader.setOnSjglScglUploadLisener(new OnSjglScglUploadLisener() {

            @Override
            public void onUploadFinished(boolean isSuccessful, String tipText) {
                Log.i(TAG, "enter uploadGxyData - onUploadFinished ...");
                ProgressDialogUtil.hideProgressDialog();
                session.setCurrentResident(lastResident); // 恢复现场
                mToast.setText("上传  - 用户 ‘" + residentName + "’ - " + tipText);
                mToast.show();

                if (isSuccessful) { // 下载随访记录
                    // 保存下载信息
                    record.Upload = new BeanCD("1", "已上传");
                    saveRecordToDb(record);

                    updateListView();
                }
            }
        });
        ProgressDialogUtil.showProgressDialog(mContext, "正在上传", "请稍等...");
        uploader.getGxyFromDbAndSaveToWeb(mContext, beanMap,
                record.ResidentUUID, record.ProjectUUID, record.ResidentID);
    }

    /**
     * @param residentName
     * @param residentID
     * @param record
     */
    protected void uploadTnbData(final String residentName, final String residentID,
            final LocalRecord record) {
        Log.i(TAG, "enter uploadTnbData ...");
        final Session session = MyApplication.getInstance().getSession();
        final Resident lastResident = session.getCurrentResident(); // 保留现场

        final Map<String, IBean> beanMap = new HashMap<String, IBean>();
        beanMap.put(Jmjbxx.class.getName(), new Jmjbxx());
        beanMap.put(Jmjtxx.class.getName(), new Jmjtxx());
        beanMap.put(Jmjkxx.class.getName(), new Jmjkxx());
        beanMap.put(Jmxwxg.class.getName(), new Jmxwxg());
        beanMap.put(Sfgljl_tnb.class.getName(), new Sfgljl_tnb());
        SjglScglUploader uploader = new SjglScglUploader();
        uploader.setOnSjglScglUploadLisener(new OnSjglScglUploadLisener() {

            @Override
            public void onUploadFinished(boolean isSuccessful, String tipText) {
                Log.i(TAG, "enter uploadTnbData - onUploadFinished ...");
                ProgressDialogUtil.hideProgressDialog();
                session.setCurrentResident(lastResident); // 恢复现场
                mToast.setText("上传  - 用户 ‘" + residentName + "’ - " + tipText);
                mToast.show();

                if (isSuccessful) { // 下载随访记录
                    // 保存下载信息
                    record.Upload = new BeanCD("1", "已上传");
                    saveRecordToDb(record);

                    updateListView();
                }
            }
        });
        ProgressDialogUtil.showProgressDialog(mContext, "正在上传", "请稍等...");
        uploader.getTnbFromDbAndSaveToWeb(mContext, beanMap,
                record.ResidentUUID, record.ProjectUUID, record.ResidentID);
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
            // Log.i(TAG, "getView 1, position = " + position);
            // if (convertView == null) {
            // Log.i(TAG, "getView 2, position = " + position);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_scgl_layout, null);
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
            TextView sjlyTextView = (TextView) convertView.findViewById(R.id.sjlyTextView);
            TextView czbzTextView = (TextView) convertView.findViewById(R.id.czbzTextView);
            TextView scbzTextView = (TextView) convertView.findViewById(R.id.scbzTextView);
            TextView czsjTextView = (TextView) convertView.findViewById(R.id.czsjTextView);
            if (position >= 0 && position < mShownRecords.size()) {

                /*
                 * List<Integer> widths = new ArrayList<Integer>(); for (int i =
                 * 0; i < titleLinearLayout.getChildCount(); i++) { View
                 * childView = titleLinearLayout.getChildAt(i); if (childView
                 * instanceof TextView) { int width = childView.getWidth();
                 * widths.add(width); } }
                 */
                // width:[45, 157, 98, 98, 197, 198, 139, 139, 139]

                // Log.i(TAG, "width:" + widths);
                needResetViews = new View[] { xhTextView, xmTextView, xbTextView, xmmcTextView,
                        sjlyTextView, czbzTextView, scbzTextView, czsjTextView };
                // setViewByWidths(widths);

                // TODO 完成数据加载
                LocalRecord record = mShownRecords.get(position);
                if (record.Upload.getcD().equals("1")) { // 已下载
                    gxCheckBox.setEnabled(false);
                } else {
                    gxCheckBox.setEnabled(true);
                }
                gxCheckBox.setTag(record);
                gxCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                        LocalRecord record = (LocalRecord) arg0.getTag();
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
                if (record.DataSource != null) {
                    sjlyTextView.setText(record.DataSource.getTagValue());
                }
                if (record.OperType != null) {
                    czbzTextView.setText(record.OperType.getTagValue());
                }
                if (record.Upload == null) { // 如果状态没有值则赋值未完成
                    record.Upload = new BeanCD("0", "未上传");
                }
                scbzTextView.setText(record.Upload.getTagValue());
                czsjTextView.setText(record.CheckDate);
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

    }

    protected void initDbListView(final Date firstDay, final Date lastDay) { // 初始化列表
        this.firstDay = firstDay;
        this.lastDay = firstDay;
    // mDbFullDatas = SjglBll.query(LocalRecord.class.getName(),
    // firstDay.getTime(),
    // lastDay.getTime());
    // mDbRecords.clear();
    // if (mDbFullDatas != null) {
    // for (Sjgl s : mDbFullDatas) {
    // IBean bean =
    // XmlSerializerUtil.getInstance().beanFromXML(LocalRecord.class,
    // s.getBean());
    // mDbRecords.add((LocalRecord) bean);
    // }
    // }
    // mRawRecords = mDbRecords;
    //
    // updateListView();

        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(LocalRecord.class);

        BeanUtil.getInstance().getSjglListFromDb(listBean, firstDay.getTime(), lastDay.getTime(),
                new OnResultFromDb() {

                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        mDbRecords.clear();
                        if (isSucc) {
                            for (IBean bean : listBean) {
                                mDbRecords.add((LocalRecord) bean);
                            }
                            mRawRecords = mDbRecords;

                            updateListView();
                        }
                    }

                });
    }

    protected void initListView(List<LocalRecord> records) { // 初始化列表
        mRawRecords = records;
        updateListView();
    }

    protected void updateListView() { // 更新列表
        int zj = 0;
        int ysc = 0;
        int wsc = 0;
        mShownRecords.clear();
        if (mRawRecords != null) {
            // 根据条件显示

            String xmsxIds = xmsxCheckBoxGroupUtil.getCheckValues(",");
            String scztIds = scztCheckBoxGroupUtil.getCheckValues(",");
            for (LocalRecord record : mRawRecords) {
                if (record.Upload == null) { // 如果状态没有值则赋值未完成
                    record.Upload = new BeanCD("0", "未上传");
                }
                if (StringUtil.contains(xmsxIds, record.ProjectType.getcD(), ",")
                        && StringUtil.contains(scztIds, record.Upload.getcD(), ",")) {
                    if (record.Upload.getcD().equals("0")) {
                        wsc++;
                    } else {
                        ysc++;
                    }
                    mShownRecords.add(record);
                }
            }
        }
        adapter.notifyDataSetChanged();
        listTotalCheckBox.setChecked(false);

        // 设置底部提示数目
        zj = wsc + ysc;
        zjTextView.setText(zj + "");
        yscTextView.setText(ysc + "");
        wscTextView.setText(wsc + "");
    }

    private void saveRecordToDb(LocalRecord record) {
        List<IBean> listBean = new ArrayList<IBean>();
        listBean.add(record);
        BeanUtil.getInstance().saveSjglToDb(listBean, record.ResidentUUID, record.ProjectUUID,
                record.ProjectType.getcD(), new Date().getTime(), new OnResultSaveToDb() {
                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                    }
                });
    }

    private void deleteRecordFromDb(LocalRecord record) {
        SjglBll.delete(record.ResidentUUID, record.ProjectUUID, record.ProjectType.getcD(),
                LocalRecord.class.getName());
    }
}
