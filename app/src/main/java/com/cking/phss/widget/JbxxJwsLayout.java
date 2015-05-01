/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxJwsjbListView.java
 * classes : com.cking.phss.view.JbxxJwsjbListView
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午04:24:14
 */
package com.cking.phss.widget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.HistoryDisease;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.Dzjmjkjws;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite4address.Icd10;
import com.cking.phss.sqlite4address.SqliteDataController;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;

/**
 * 基本信息->既往史疾病->ListView
 * com.cking.phss.view.JbxxJwsjbListView
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午04:24:14
 */
public class JbxxJwsLayout extends ListView {
    private static final String TAG = "JbxxJwsjbLayout";
    private Context mContext = null;
    public List<ListItemJbxxJwsCommon> mListView = new ArrayList<ListItemJbxxJwsCommon>();
    private String titleString="";
    private String addString="";
    private Jmjbxx mJmjbxx;
    private int mHdType;//改既往史是哪个类型的 0/1/2/3 疾病/手术/外伤/输血 -->
    private int changePosition=1;

    ImageView closeImageView;
    Dialog selectDialog = null;
    TextView mcTextView;
    AutoCompleteTextView mcAutoCompleteTextView;
    TextView icd10zlTextView;
    SpinnerUtil icd10zlSpinnerUtil;
    TextView rqTextView;
    CalendarText rqCalendarText;
    CalendarText fbrqCalendarText;
    TextView msTextView;
    EditText msEditText;
    SpinnerUtil zljgSpinnerUtil;
    Button qdButton;
    Button qxButton;
    LinearLayout titleLinearLayout;
    List<Icd10> icd10List = null;
    static List<Icd10> icd10SsList = null;
    static List<Icd10> icd10JbList = null;
    SearchAdapter<String> adapter = null;
    static SearchAdapter<String> adapterSs = null;
    static SearchAdapter<String> adapterJb = null;

    private String typeString="";
    private int visibility=View.VISIBLE;

    private Toast mToast = null;
    int ywmcId = -1;

    private ListAdapter mListAdapter = new ListAdapter();
    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mListView.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return mListView.get(position);
        }
    }

    /**
     * @param context
     */
    public JbxxJwsLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxJwsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray a = context.obtainStyledAttributes(attrs,  
//                R.styleable.Jwslb);  
//        int jwslbid = a.getInteger(R.styleable.Jwslb_jwslbid, 0);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(mContext, "");
        // removeAllViews();
        setAdapter(mListAdapter);
    }
    
    public static void initData(Context context) {
        icd10SsList = SqliteDataController.getIcd10List("cdicdtenssmc");
        Collections.sort((List<Icd10>) icd10SsList, new Comparator<Icd10>() {
            public int compare(Icd10 arg0, Icd10 arg1) {
                String hzArg0 = arg0.getIcdTenMnemonics();
                String hzArg1 = arg1.getIcdTenMnemonics();

                return hzArg0.compareTo(hzArg1);
            }
        });
        adapterSs = new SearchAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, icd10SsList, SearchAdapter.ALL);// 速度优先
        icd10JbList = SqliteDataController.getIcd10List("cdicdtenjbmc");
        Collections.sort((List<Icd10>) icd10JbList, new Comparator<Icd10>() {
            public int compare(Icd10 arg0, Icd10 arg1) {
                String hzArg0 = arg0.getIcdTenMnemonics();
                String hzArg1 = arg1.getIcdTenMnemonics();

                return hzArg0.compareTo(hzArg1);
            }
        });
        adapterJb = new SearchAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, icd10JbList, SearchAdapter.ALL);// 速度优先
    }
    
    public void addItem(LinearLayout titleLinearLayout) {
        this.titleLinearLayout = titleLinearLayout;
        selectDialog = new Dialog(mContext, R.style.dialog);  
        selectDialog.setCancelable(true);  
        selectDialog.setContentView(R.layout.dialog_add_jws_common_layout); 
        closeImageView = (ImageView)selectDialog.findViewById(R.id.closeImageView);
        mcTextView = (TextView)selectDialog.findViewById(R.id.mcTextView);
        mcAutoCompleteTextView = (AutoCompleteTextView)selectDialog.findViewById(R.id.mcAutoCompleteTextView);
        icd10zlTextView = (TextView) selectDialog.findViewById(R.id.icd10zlTextView);
        icd10zlSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.icd10zlSpinnerUtil);
        rqTextView = (TextView)selectDialog.findViewById(R.id.rqTextView);
        rqCalendarText = (CalendarText)selectDialog.findViewById(R.id.rqCalendarText);
        fbrqCalendarText = (CalendarText)selectDialog.findViewById(R.id.fbrqCalendarText);
        msTextView = (TextView)selectDialog.findViewById(R.id.msTextView);
        msEditText = (EditText)selectDialog.findViewById(R.id.msEditText);
        zljgSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.zljgSpinnerUtil);
        qdButton = (Button)selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button)selectDialog.findViewById(R.id.qxButton);

        mcAutoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                Log.d(TAG, "onItemClick:"+position);
                // if (position > 0) { // 正常情况
                // selectedIcd10 = adapter.getItem(position);
                // } else {
                // selectedIcd10 = new Icd10();
                // selectedIcd10.setIcdTenName(mcAutoCompleteTextView.getText().toString());
                // selectedIcd10.setIcdTenCode("Other");
                // }
            }
            
        });

        icd10zlSpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg1 != null) {
                    mcAutoCompleteTextView.setText(icd10zlSpinnerUtil.getSelectedData());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mcAutoCompleteTextView.setThreshold(1);
        adapter = new SearchAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, icd10List, SearchAdapter.ALL);// 速度优先
        mcAutoCompleteTextView.setAdapter(adapter);//
        
        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();//隐藏对话框 
            }
        });
        
        mcTextView.setText(typeString + "名称：");
        rqTextView.setText(typeString + "日期：");
        msTextView.setText(typeString + "原因：");
        icd10zlTextView.setText("常见" + typeString + "：");
        setMsVisibility(visibility);
        
        qdButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                String icd10Id = "", icd10Name = "";
                /*if (icd10Id.equals("")||icd10Name.equals("")){
                    Toast.makeText(mContext, "ICD10不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } 
                else*/ 
                {
                    icd10Name = mcAutoCompleteTextView.getText().toString().trim();
                    icd10Id = findIcd10IdByName(icd10Name);
                }
                String mQzsjDate=rqCalendarText.getText().toString();
                String mFbsjDate=fbrqCalendarText.getText().toString();
                String mMs = msEditText.getText().toString();
                int zljgId = zljgSpinnerUtil.getSelectedValueInt();
                // 添加子项
                getJmjbxxFromDb((mListView.size() + 1) + "", icd10Id, icd10Name, mQzsjDate,
                        mFbsjDate, mMs, zljgId, 1);
                selectDialog.dismiss();
            }
        });

        qxButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();
            }
        });
        selectDialog.show();
    }
    
    /**
     * @param icd10Name
     * @return
     */
    protected String findIcd10IdByName(String icd10Name) {
        if (icd10List != null) {
            for (Icd10 icd10 : icd10List) {
                if (icd10.getIcdTenName().equals(icd10Name)) {
                    return icd10.getIcdTenCode();
                }
            }
        }

        return "Other";
    }

    public void editItem(final int position) {
        changePosition=position;
        selectDialog = new Dialog(mContext, R.style.dialog);  
        selectDialog.setCancelable(true);  
        selectDialog.setContentView(R.layout.dialog_add_jws_common_layout); 
        closeImageView = (ImageView)selectDialog.findViewById(R.id.closeImageView);
        mcTextView = (TextView)selectDialog.findViewById(R.id.mcTextView);
        mcAutoCompleteTextView = (AutoCompleteTextView)selectDialog.findViewById(R.id.mcAutoCompleteTextView);
        icd10zlTextView = (TextView) selectDialog.findViewById(R.id.icd10zlTextView);
        icd10zlSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.icd10zlSpinnerUtil);
        rqTextView = (TextView)selectDialog.findViewById(R.id.rqTextView);
        rqCalendarText = (CalendarText)selectDialog.findViewById(R.id.rqCalendarText);
        fbrqCalendarText = (CalendarText)selectDialog.findViewById(R.id.fbrqCalendarText);
        msTextView = (TextView)selectDialog.findViewById(R.id.msTextView);
        msEditText = (EditText)selectDialog.findViewById(R.id.msEditText);
        zljgSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.zljgSpinnerUtil);
        qdButton = (Button)selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button)selectDialog.findViewById(R.id.qxButton);

        mcAutoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Log.d(TAG, "onItemClick:" + position);
                // if (position > 0) { // 正常情况
                // selectedIcd10 = adapter.getItem(position);
                // } else {
                // selectedIcd10 = new Icd10();
                // selectedIcd10.setIcdTenName(mcAutoCompleteTextView.getText().toString());
                // selectedIcd10.setIcdTenCode("Other");
                // }
            }

        });

        icd10zlSpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg1 != null) {
                    mcAutoCompleteTextView.setText(icd10zlSpinnerUtil.getSelectedData());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();//隐藏对话框 
            }
        });
        
        mcTextView.setText(typeString + "名称：");
        rqTextView.setText(typeString + "日期：");
        msTextView.setText(typeString + "原因：");
        icd10zlTextView.setText("常见" + typeString + "：");
        setMsVisibility(visibility);
        
        ListItemJbxxJwsCommon listItem = mListView.get(position);
        mcAutoCompleteTextView.setText(listItem.getIcd10_name());
        icd10zlSpinnerUtil.setSelectedPositionByData(listItem.getIcd10_name());
        rqCalendarText.setText(listItem.getDate());
        fbrqCalendarText.setText(listItem.getHappenDate());
        msEditText.setText(listItem.getMs());
        zljgSpinnerUtil.setSelectedPositionByValue(listItem.getZljgId());

        qdButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                String icd10Id = "", icd10Name = "";
/*                if (icd10Id.equals("") || icd10Name.equals("")) {
                    Toast.makeText(mContext, "ICD10不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } 
                else */
                {
                    icd10Name = mcAutoCompleteTextView.getText().toString().trim();
                    icd10Id = findIcd10IdByName(icd10Name);
                }
                String mQzsjDate=rqCalendarText.getText().toString();
                String mFbsjDate=fbrqCalendarText.getText().toString();
                String mMs = msEditText.getText().toString();
                int zljgId = zljgSpinnerUtil.getSelectedValueInt();
                // 添加子项
                ListItemJbxxJwsCommon listItem = mListView.get(position);
                getJmjbxxFromDb(listItem.getDisSn(), icd10Id, icd10Name, mQzsjDate, mFbsjDate, mMs,
                        zljgId, 2);
                selectDialog.dismiss();
            }
        });

        qxButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();
            }
        });
        selectDialog.show();
    }
    
    public void deleteItem(final int position) {
        changePosition=position;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext); 
        builder.setMessage("您确定要删除项吗?") 
               .setCancelable(false) 
               .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
                   public void onClick(DialogInterface dialog, int id) { 
                       dialog.cancel(); 
                       // 添加子项
                       ListItemJbxxJwsCommon listItem = mListView.get(position);
                       String icd10Id = "", icd10Name = "";
                       icd10Name = listItem.getIcd10_name();
                       icd10Id = listItem.getIcd10_id();
                       String mQzsjDate=listItem.getDate();
                       String mFbsjDate=listItem.getHappenDate();
                       String mMs = listItem.getMs();
                       int zljgId = listItem.getZljgId();
                       getJmjbxxFromDb(listItem.getDisSn(), icd10Id, icd10Name, mQzsjDate, mFbsjDate, mMs,
                               zljgId, 3);
                   } 
               }) 
               .setNegativeButton("取消", new DialogInterface.OnClickListener() { 
                   public void onClick(DialogInterface dialog, int id) { 
                        dialog.cancel(); 
                   } 
               }); 
        AlertDialog alert = builder.create(); 
        alert.show();
    }
    
    public void clear() {
        mListView.clear();
        mListAdapter.notifyDataSetChanged();
        // removeAllViews();
        // invalidate();
    }

    /**
     * 添加子项
     * 
     * @param string
     */ 
    public void addItem(String string, String disSn, String icd10Id, String icd10Name,
            String mQzsjDate, String mFbsjDate, String mMs, int zljgId) {
        try {
            final ListItemJbxxJwsCommon listItem = new ListItemJbxxJwsCommon(mContext);
            mListView.add(listItem);
            // List<Integer> widths = new ArrayList<Integer>();
            // for (int i = 0; i<titleLinearLayout.getChildCount(); i++) {
            // View childView = titleLinearLayout.getChildAt(i);
            // if (childView instanceof TextView) {
            // int width = childView.getWidth();
            // widths.add(width);
            // }
            //
            // }
            // widths.add(170);
            // widths.add(171);
            // widths.add(131);
            // Log.i(TAG, "addItem: " + widths.toString() + "mListView.size:" +
            // mListView.size());
            // listItem.setViewByWidths(widths);
            getView(mListView.size() - 1, listItem, JbxxJwsLayout.this);
            listItem.setIndex(mListView.size() - 1);
            // listItem.setDateName("确诊时间：");
            listItem.setDisSn(disSn);
            listItem.setIcd10_id(icd10Id);
            listItem.setIcd10_name(icd10Name);
            listItem.setDate(mQzsjDate);
            listItem.setHappenDate(mFbsjDate);
            listItem.setMs(mMs);
            listItem.setZljgId(zljgId);
            // addView(listItem);
            mListAdapter.notifyDataSetChanged();
            // invalidate();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 移除子项
     * @param position
     */
    public void removeItem(int position) {
        ListItemJbxxJwsCommon listItem = mListView.get(position);
        // removeView(listItem);
        // invalidate();
        
        Log.e(TAG, "position:" + position);
        
        mListView.remove(position);
        // 更新索引
        for (int i=position; i<mListView.size(); i++) {
            listItem = mListView.get(i);
            listItem.setIndex(i+1);
            listItem.setTag(i);
        }
        mListAdapter.notifyDataSetChanged();
    }
    
    /**
     * 更新子项
     */
    public void updateItem(int position, String icd10Id, String icd10Name, String mQzsjDate,
            String mFbsjDate, String mMs, int zljgId) {
        ListItemJbxxJwsCommon listItem = mListView.get(position);
        listItem.setIcd10_id(icd10Id);
        listItem.setIcd10_name(icd10Name);
        listItem.setDate(mQzsjDate);
        listItem.setHappenDate(mFbsjDate);
        listItem.setMs(mMs);
        listItem.setZljgId(zljgId);
        mListAdapter.notifyDataSetChanged();
    }

    public int getCount() {
        //Log.i(TAG, "getView, mViews.length:" + mViews.length);
        return mListView.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        //Log.i(TAG, "getView, position:" + position);
        if (position >= mListView.size()) {
            return null;
        }

        convertView = mListView.get(position);

        ImageView editBtn = (ImageView) convertView.findViewById(R.id.edit_button);
        editBtn.setTag(convertView);
        ImageView deleteBtn = (ImageView) convertView.findViewById(R.id.delete_button);
        deleteBtn.setTag(convertView);
        editBtn.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View arg0) {
                View parent = (View) arg0.getTag();
                int index = (Integer) parent.getTag();
                editItem(index);   
            }  
        }); 
        deleteBtn.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View arg0) {  
                View parent = (View) arg0.getTag();
                int index = (Integer) parent.getTag();
                deleteItem(index);
            }  
        }); 

        return convertView;
    }

    //设置标题
    public void setTitleString(String titleString,String addString){
    	this.titleString=titleString;
    	this.addString=addString;
    }
    
    //根据日期排序
    public List<HistoryDisease> dateSort(List<HistoryDisease> lists){
        long[] longsDate=new long[lists.size()];
        if(lists==null)
            return null;
        for(int i=0;i<lists.size();i++){
            String disDate=lists.get(i).getDiagnoseDate();
            if(disDate.equals("")){
                lists.remove(i);
                  i--;
                continue;
            }
           SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date=format.parse(disDate);
                longsDate[i]=date.getTime();
            } catch (ParseException e) {
                Log.e(TAG,e.toString());
            }
        }
        
        HistoryDisease hdTemp=null;
        //12,3,156,9,123,9
        for (int i = 0; i < longsDate.length; i++) {
           for(int j=i+1;j<longsDate.length;j++){
               if(longsDate[i]<longsDate[j]){
                   long temp=longsDate[i];
                   longsDate[i]=longsDate[j];
                   longsDate[j]=temp;
                   //并且，把list也换位
                   hdTemp=lists.get(i);
                   lists.set(i, lists.get(j));
                   lists.set(j, hdTemp);
                   hdTemp=null;
               }
           }
        }
        return lists;
    }
    
    
    private void getJmjbxxFromDb(final String disSn, final String icd10Id, final String icd10Name,
            final String mQzsjDate, final String mFbsjDate, final String mMs, final int zljgId,
            final int operType) {
        //先得到居民基本信息
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                mJmjbxx = Global.jmjbxx;
                if (mJmjbxx == null || mJmjbxx.getResidentID().equals(""))
                 {
                    if (operType == 1) {// 新增
                        addItem("", (mListView.size() + 1) + "", icd10Id, icd10Name, mQzsjDate,
                                mFbsjDate, mMs, zljgId);
                    } else if (operType == 2) {// 修改
                        updateItem(changePosition, icd10Id, icd10Name, mQzsjDate, mFbsjDate, mMs,
                                zljgId);
                    } else if (operType == 3) {// 删除
                        removeItem(changePosition);
                    }
                    return;
                }
                changeHistoryHyperToWeb(disSn, icd10Id, icd10Name, mQzsjDate, mFbsjDate, mMs,
                        zljgId, operType);
            }
        });
    }
    
    private void changeHistoryHyperToWeb(final String disSn,
            final String icd10Id, final String icd10Name, final String mQzsjDate,
            final String mFbsjDate, final String mMs, final int zljgId, final int operType) {
        Dzjmjkjws dzjmjkjws=new Dzjmjkjws();
        dzjmjkjws.request=new Dzjmjkjws.Request();
        
        Login1 login1=MyApplication.getInstance().getSession().getLoginResult();
        if(login1==null||login1.response==null){
            Toast.makeText(mContext, "当前没有医生登录，操作失败！", Toast.LENGTH_SHORT).show();
            return;
        }
        dzjmjkjws.request.userID=login1.response.userID;
        dzjmjkjws.request.residentID=mJmjbxx.getResidentID();
        //hdType
        dzjmjkjws.request.hDType=mHdType;
        dzjmjkjws.request.disSn=disSn;
        dzjmjkjws.request.disOperType=operType;
        dzjmjkjws.request.iCD10=new BeanID(icd10Id, icd10Name);
        dzjmjkjws.request.disease = icd10Name;
        dzjmjkjws.request.diagnoseDate=mQzsjDate;
        dzjmjkjws.request.happenDate=mFbsjDate;
        dzjmjkjws.request.happenDate=mFbsjDate;
        dzjmjkjws.request.happenDate=mFbsjDate;
        dzjmjkjws.request.resultCD=zljgId;
        
        List<IDto> beanList=new ArrayList<IDto>();
        beanList.add(dzjmjkjws);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if(isSucc&&listBean!=null){
                    Dzjmjkjws responseDzjmjkjws=(Dzjmjkjws)listBean.get(0);
                    if(responseDzjmjkjws!=null&&responseDzjmjkjws.response!=null){
                        if(operType==1){//新增
                            addItem("", responseDzjmjkjws.response.disSn, icd10Id, icd10Name,
                                    mQzsjDate, mFbsjDate, mMs, zljgId);
                        }else if(operType==2){//修改
                            updateItem(changePosition, icd10Id, icd10Name, mQzsjDate, mFbsjDate,
                                    mMs,
                                    zljgId);
                        }else if(operType==3){//删除
                            removeItem(changePosition);
                        }
                    }
                }
            }
        });
    }

    public void setHdType(int mHdType) {
        this.mHdType = mHdType;
    }
    
    public void setMsVisibility(int visibility) {
        msTextView.setVisibility(visibility);
        msEditText.setVisibility(visibility);
    }

    /**
     * @param string
     * @param string2
     * @param string3
     * @param visible
     */
    public void setConfig(String typeString, int visibility) {
        this.typeString = typeString;
        this.visibility = visibility;
//        既往史（疾病、输血、外伤）的名称都是疾病名称：jbmc
//                    既往史（手术）的名称是手术名称：ssmc
        // 药物名称
        if (typeString.equals("手术")) {
            icd10List = icd10SsList;
            adapter = adapterSs;
        } else {
            icd10List = icd10JbList;
            adapter = adapterJb;
        }
    }

    /**
     * @param titleLinearLayout2
     */
    public void setListViewTitleLayout(LinearLayout titleLinearLayout) {
        this.titleLinearLayout = titleLinearLayout;
    }

    public static class SearchAdapter<T> extends BaseAdapter implements Filterable {
        private List<Icd10> mObjects;

        private List<Icd10> pinyinList;// 支持多音类似:{{z,c},{j},{z},{q,x}}的集

        private final Object mLock = new Object();

        private int mResource;

        private int mFieldId = 0;

        private Context mContext;

        private ArrayList<Icd10> mOriginalValues;
        private TireTreeFilter mFilter;

        private LayoutInflater mInflater;

        public static final int ALL = -1;// 全部
        private int maxMatch = 5;// 显示多少个可能

        public SearchAdapter(Context context, int textViewResourceId, List<Icd10> objects, int maxMatch) {
            // TODO Auto-generated constructor stub
            init(context, textViewResourceId, 0, objects);
            this.pinyinList = (List<Icd10>) objects;
            this.maxMatch = maxMatch;
        }

        private void init(Context context, int resource, int textViewResourceId, List<Icd10> objects) {
            mContext = context;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mResource = resource;
            mObjects = objects;
            mFieldId = textViewResourceId;
        }

        public int getCount() {
            return mObjects.size();
        }

        public T getItem(int position) {
            return (T) mObjects.get(position).getIcdTenName();
        }

        public int getPosition(T item) {
            return mObjects.indexOf(item);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return createViewFromResource(position, convertView, parent, mResource);
        }

        private View createViewFromResource(int position, View convertView, ViewGroup parent,
                int resource) {
            View view;
            TextView text;

            if (convertView == null) {
                view = mInflater.inflate(resource, parent, false);
            } else {
                view = convertView;
            }

            try {
                if (mFieldId == 0) {
                    text = (TextView) view;
                } else {
                    text = (TextView) view.findViewById(mFieldId);
                }
            } catch (ClassCastException e) {
                Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
                throw new IllegalStateException(
                        "ArrayAdapter requires the resource ID to be a TextView", e);
            }

            text.setText(getItem(position).toString());

            return view;
        }

        @Override
        public Filter getFilter() {
            if (mFilter == null) {
                mFilter = new TireTreeFilter();
            }
            return mFilter;
        }

        private class TireTreeFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence prefix) {
                FilterResults results = new FilterResults();

                if (mOriginalValues == null) {
                    synchronized (mLock) {
                        mOriginalValues = new ArrayList<Icd10>(mObjects);//
                    }
                }

                if (prefix == null || prefix.length() == 0) {
                    synchronized (mLock) {
                        // ArrayList<T> list = new ArrayList<T>();//
                        ArrayList<Icd10> list = new ArrayList<Icd10>(mOriginalValues);// List<T>
                        results.values = list;
                        results.count = list.size();
                    }
                } else {
                    String prefixString = prefix.toString().toLowerCase();

                    final ArrayList<Icd10> hanzi = mOriginalValues;// 汉字String
                    final int count = hanzi.size();

                    final List<Icd10> newValues = new ArrayList<Icd10>(count);// 支持多音不重

                    for (int i = 0; i < count; i++) {
                        final Icd10 value = hanzi.get(i);// 汉字String
                        final String valueText = value.toString().toLowerCase();// 汉字String
                        final Icd10 pinyinSet = pinyinList.get(i);// 支持多音类似:{z,c}
                        final String pinyin = pinyinSet.getIcdTenMnemonics().toLowerCase();// 取出多音字里的一个字
                        if (pinyin.indexOf(prefixString) == 0) {// 从0开始匹配
                            newValues.add(value);
                        } else if (valueText.indexOf(prefixString) == 0) {// 如果是汉字则直接添加
                            newValues.add(value);
                        }
                        if (maxMatch > 0) {// 有数量限
                            if (newValues.size() > maxMatch - 1) {// 不要太多
                                break;
                            }
                        }

                    }
                    results.values = newValues;
                    results.count = newValues.size();
                }
                return results;
            }

            protected void publishResults(CharSequence constraint, FilterResults results) {

                mObjects = (List<Icd10>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        }
    }
}
