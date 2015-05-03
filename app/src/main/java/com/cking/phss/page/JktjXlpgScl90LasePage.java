package com.cking.phss.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Xlcs;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.SqliteField.XlcsjgAndTzpsjg;
import com.cking.phss.sqlite.SqliteOperater;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class JktjXlpgScl90LasePage extends LinearLayout{

    private Context mContext;
    private Toast mToast;
    private JktjXlpgPage mParent;

    // 个人基本信息
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    
    private TextView mResidentName;
    private TextView mFlokCD;
    private TextView mBirthDay;
    private TextView mNowProvince;
    private TextView mSexCD;
    private TextView mNowCity;

    private TextView mJkjyText = null;
    private Button mBackBtn = null;
    // 心里测试结果表面
    private String resident_uuid = null;
    private String jieguo = "";
    private int[] mGrade=null;//90到题目的得分
    private String[][] adapterValue=null;//listView 的数据
    private int[] itemResults=new int[9];//10个因子项，前面9项的结论
    public JktjXlpgScl90LasePage(Context context, JktjXlpgPage parent, int[] grade) {
        super(context);
        this.mParent = parent;
        this.mGrade=grade;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_health_xlpg_3_scl90_layout, this);
        calculateAdapterValue();
        // 个人基本信息
        mResidentName = (TextView) findViewById(R.id.username_text);
        mFlokCD = (TextView) findViewById(R.id.minzu_text);
        mBirthDay = (TextView) findViewById(R.id.birthDay_text);
        mNowProvince = (TextView) findViewById(R.id.shenfen_text);
        mSexCD = (TextView) findViewById(R.id.sex_text);
        mNowCity = (TextView) findViewById(R.id.city_text);
        
        ListView listView=(ListView)findViewById(R.id.xlcs_jl_list);
        listView.setEnabled(false);
        listView.setAdapter(new ListAdapter(adapterValue));
        
        
        mBackBtn = (Button) findViewById(R.id.xlce_back_button);
        mBackBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重新加载心理测试首页
                mParent.showXlcsPage01();
            }
        });
        
        mJkjyText=(TextView)findViewById(R.id.xlcs_scl90_jy);
    }
    
    class ListAdapter extends BaseAdapter{
        private String[][] values=null;
        public ListAdapter(String[][] values) {
            this.values=values;
        }
        @Override
        public int getCount() {
            return values.length;
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
            if(values==null||values[position]==null||values[position].length!=5)
                return null;
            View view=LayoutInflater.from(mContext).inflate(R.layout.scl90_list_item, null);
            TextView zb_text=(TextView)view.findViewById(R.id.zb_text);
            TextView ysf_text=(TextView)view.findViewById(R.id.ysf_text);
            TextView pjf_text=(TextView)view.findViewById(R.id.pjf_text);
            TextView ckzd_text=(TextView)view.findViewById(R.id.ckzd_text);
            TextView jf_bzc_text=(TextView)view.findViewById(R.id.jf_bzc_text);
            
            zb_text.setText(values[position][0]);
            ysf_text.setText(values[position][1]);
            pjf_text.setText(values[position][2]);
            ckzd_text.setText(values[position][3]);
            jf_bzc_text.setText(values[position][4]);
            return view;
        }
        
    }
    
    private void calculateAdapterValue(){
        adapterValue=new String[16][5];
        adapterValue[0]=new String[]{"指标","原始分","平均分","参考诊断","均分±标准差"};
        if(mGrade==null||mGrade.length<90){
            return;
        }
        
       //前10个因子项目的计算
       for(int i=0;i<10;i++){
           adapterValue[i+1]=calculateItem1To10(i);
       }
       
       //阴性项目数，阳性项目数，阳性项目平均分，总分，总均分
       int yxxms=0;//阴性项目数
       int yxxmTotal=0;//阳性项目总分
       int total=0;//总分
       for(int i=0;i<mGrade.length;i++){
           if(mGrade[i]==1){
               yxxms++;
           }else{
               yxxmTotal+=mGrade[i];
           }
           total+=mGrade[i];
       }
       
       adapterValue[11]=new String[]{SCL90_ITEM[10],yxxms+"","","",SCL90_STANDARD[10]};
       adapterValue[12]=new String[]{SCL90_ITEM[11],(90-yxxms)+"","","",SCL90_STANDARD[11]};
       if(yxxms==90){
           adapterValue[13]=new String[]{SCL90_ITEM[12],"0","","",SCL90_STANDARD[12]};
       }else{
            adapterValue[13] = new String[] {
                    SCL90_ITEM[12],
                    DecimalsTextWatcher.getInstance().parsePercision(
                            (double) yxxmTotal / (90 - yxxms))
                            + "", "", "", SCL90_STANDARD[12] };
       }
       adapterValue[14]=new String[]{SCL90_ITEM[13],total+"","","",SCL90_STANDARD[13]};
        adapterValue[15] = new String[] { SCL90_ITEM[14],
                DecimalsTextWatcher.getInstance().parsePercision((double) total / 90) + "", "", "",
                SCL90_STANDARD[14] };
    }
    
    //躯体化的计算： 1,4,12,27,40,42,48,49,52,53,56和58,共12项
    private String[] calculateItem1To10(int item){
        //zb 指标
        //ysf  原始分
        //pjf  平均分
        //ckzd  参考诊断
        //jf_bzc  均分加减标准差
        String zb=SCL90_ITEM[item];
        int ysf=0;
        for(int i=0;i<valueCatagory[item].length;i++){
            ysf+=mGrade[valueCatagory[item][i]-1];//某个因子项的分数累加，得到原始分
        }
        double pjf=(double)ysf/valueCatagory[item].length;
        pjf = DecimalsTextWatcher.getInstance().parsePercision(pjf);
        String ckzd=getCkzd(pjf,item);
        String jf_bzc=SCL90_STANDARD[item];
        return new String[]{zb,ysf+"",pjf+"",ckzd,jf_bzc};
    }
    
    
    //根据平均分获得参考诊断
    private String getCkzd(double value,int itemIndex){
        if(value>0&&value<0.5){
            if(itemIndex<9)
                itemResults[itemIndex]=0;
            return "无";
        }else if (value<1.5) {
            if(itemIndex<9)
                itemResults[itemIndex]=1;
           return "轻度"; 
        }else if (value<2.5) {
            if(itemIndex<9)
                itemResults[itemIndex]=2;
            return "中度";
        }else if (value<3.5) {
            if(itemIndex<9)
                itemResults[itemIndex]=3;
            return "重度";
        }else {
            if(itemIndex<9)
                itemResults[itemIndex]=4;
            return "极重";
        }
    }
    
    
    //90到题目的分类
    private int[][] valueCatagory=new int[][]{
            {1,4,12,27,40,42,48,49,52,53,56,58},
            {3,9,10,28,38,45,46,51,55,65},
            {6,21,34,36,37,41,61,69,73},
            {5,14,15,20,22,26,29,30,31,32,54,71,79},
            {2,17,23,33,39,57,72,78,80,86},
            {11,24,63,67,74,81},
            {13,25,47,50,70,75,82},
            {8,18,43,68,76,83},
            {7,16,35,62,77,84,85,87,88,90},
            {19,44,59,60,64,66,89}
    };
    
    //SCL90表的16项标题
    public static final  String[] SCL90_ITEM=new String[]{
        "躯体化：","强迫症状：","人际关系敏感：","抑郁：","焦虑：",
        "敌对：","恐怖：","偏执：","精神病性：","其他项目：","阴性项目数：",
        "阳性项目数：","阳性项目平均分：","总分：","总均分："
    };
    private static final String TAG = "JktjXlpgScl90LasePage";
    
    //SCL90表均分和标准差的值
    private   String[] SCL90_STANDARD=new String[]{
        "1.37±0.48",
        "1.62±0.58",
        "1.65±0.51",
        "1.50±0.59",
        "1.39±0.43",
        "1.48±0.56",
        "1.23±0.41",
        "1.43±0.57",
        "1.29±0.42",
        "",
        "24.92±18.41",
        "65.08±18.33",
        "2.60±0.59",
        "129.96±38.76",
        "1.44±0.43",
    };
    
    
    
    
    private void setValue() {
        String resultAll="";
        for(int i=0;i<9;i++){//结论只有9项，最后一个因子项没有结论
            resultAll+=Xlcs.SCL90_CSJG[i][itemResults[i]];
            resultAll+="\n\n";
        }
        mJkjyText.setText(resultAll);
        Global.xlpgResults[6] = "已测试";
        saveXlcsjgToDB();
    }

    public void saveXlcsjgToDB() {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null)
            return;
        resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();

        //因为有9项因子，所以要全部加起来，每个结论和建议之间用"_"分隔，每个因子用";"分隔
        for(int i=0;i<9;i++){
            jieguo+=(adapterValue[i+1][3]+"_"+Xlcs.SCL90_CSJG[i][itemResults[i]]);
            if(i!=8)
                jieguo+=";";
        }
        Log.i(TAG, jieguo);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String date=format.format(new Date());
        
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + XlcsjgAndTzpsjg.TB_NAME + " WHERE " + XlcsjgAndTzpsjg.RESIDENT_UUID + "=?",
                new String[] { resident_uuid });
         
        if (!cursor.moveToNext()) {//insert
           String sql1="INSERT INTO "+XlcsjgAndTzpsjg.TB_NAME + "("+XlcsjgAndTzpsjg.RESIDENT_UUID + ",";
           String sql2=XlcsjgAndTzpsjg.XLCSJG_SCL90 +","+XlcsjgAndTzpsjg.DATE7;
           String sql3=") VALUES(?,?,?)";  
           SqliteOperater.getDbInstance().insertBySql(sql1+sql2+sql3,new String[] { resident_uuid, jieguo ,date});  
        } else {//update
            String sql1="UPDATE " + XlcsjgAndTzpsjg.TB_NAME + " SET ";
            String sql2=XlcsjgAndTzpsjg.XLCSJG_SCL90 +"=?,"+XlcsjgAndTzpsjg.DATE7+"=?";
            String sql3=" WHERE " + XlcsjgAndTzpsjg.RESIDENT_UUID + " = ?";
            SqliteOperater.getDbInstance().updateBySql(sql1+sql2+sql3,
                  new String[] { jieguo,date, resident_uuid });
        }
    }


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
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentName().equals("")) {
            mResidentName.setText("");
            mFlokCD.setText("");
            mBirthDay.setText("");
            mNowProvince.setText("");
            mNowCity.setText("");
            mSexCD.setText("");
            return;
        }
        
        mResidentName.setText(mJmjbxx.getResidentName());
        mFlokCD.setText(mJmjbxx.getFlokCD().getTagValue());
        mBirthDay.setText(mJmjbxx.getBirthDay());
        mNowProvince.setText(mJmjbxx.getNowProvince().getTagValue());
        mNowCity.setText(mJmjbxx.getNowCity().getTagValue());
        int sexCd = mJmjbxx.getSexCD();
        if (sexCd == 1) {
            mSexCD.setText("男");
        } else if (sexCd == 2) {
            mSexCD.setText("女");
        } else {
            mSexCD.setText("未知");
        }
        setValue();
    }
}
