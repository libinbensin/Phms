package com.cking.phss.page;

import java.io.File;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.util.Constant;
import com.cking.phss.util.TispToastFactory;

public class JkjyPage04 extends LinearLayout implements IPage {
    public static final String TAG = "JkjyPage04";
    private Context mContext = null;
    private Toast mToast = null;

    private TextView mWeightText;
    private GridView mGridView;
    private RelativeLayout mLayout;
    public TextView mJkjyText;
    private String images[] = new String[] { "walking", "jogging", "bicycle", "swim", "mountain",
            "aerobic", "tabletennis", "tennis", "football", "orientalfencing", "gateball",
            "badminton", "racketball", "taekwondo", "squash", "basketball", "rope-jumping", "golf", };

    private String titles[] = new String[] { "步行：", "慢跑：", "自行车：", "游泳：", "爬山：", "有氧操：", "乒乓球：",
            "网球：", "足球：", "剑道：", "门球：", "羽毛球：", "壁球：", "跆拳道：", "回力球：", "篮球：", "跳绳：", "高尔夫：", };

    private int[] values;

    private Bitmap bitmaps[]=new Bitmap[images.length];
    private GridAdapter mGridAdapter = new GridAdapter();
    private Map<String, IBean> beanMap;

    public JkjyPage04(Context context, Map<String, IBean> beanMap) {
        super(context);
        init(context);
        this.beanMap = beanMap;
    }

    public JkjyPage04(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_jkjy_04_layout, this);

        mWeightText = (TextView) findViewById(R.id.weigth_text);
        mGridView = (GridView) findViewById(R.id.ydcf_grid);
        mGridView.setEnabled(false);
        mGridView.setSelected(false);
        
        mLayout = (RelativeLayout) findViewById(R.id.rLayout);
        mJkjyText = (TextView) findViewById(R.id.jkjy_text);
    }

    class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.ydcf_grid_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
            TextView textView = (TextView) view.findViewById(R.id.item_text);
            imageView.setBackgroundDrawable(new BitmapDrawable(getBitmap(position)));
            Log.i(TAG, values[position] + "");
            if (values!=null&&values[position] != 0)
                textView.setText(titles[position]+values[position]);
            return view;
        }
        
    }

    private Bitmap getBitmap(int position) {
        if (position > images.length)
            return null;
        if(bitmaps[position]!=null)
            return bitmaps[position];
        String path = Constant.exterNalPath + "/phms/res/jkjy/运动处方/" + images[position] + ".jpg";
        File file = new File(path);
        if (!file.exists())
            return null;
         bitmaps[position] = BitmapFactory.decodeFile(path);
        return bitmaps[position];
    }

    private void calculateValues(float weight) {
        values = new int[images.length];
        values[0]=Math.round(weight*4/2); //步行 
        values[1]=Math.round(weight*7/2);//慢跑  
        values[2]=Math.round(weight*6/2);//自行车
        values[3]=Math.round(weight*7/2);//游泳
        values[4]=(int) Math.round(weight*6.5/2);//爬山
        values[5]=Math.round(weight*7/2);//有氧操
        values[6]=(int) Math.round(weight*4.5/2);//乒乓球
        values[7]=Math.round(weight*6/2);//网球 
        values[8]=Math.round(weight*7/2);//足球
        values[9]=Math.round(weight*10/2);//剑道 
        values[10]=(int) Math.round(weight*3.8/2);//门球
        values[11]=(int) Math.round(weight*4.5/2);// 羽毛球      
        values[12]= Math.round(weight*10/2);//壁球 
        values[13]=Math.round(weight*10/2);//跆拳道 
        values[14]=Math.round(weight*10/2);//回力球
        values[15]=Math.round(weight*6/2);// 篮球  
        values[16]=Math.round(weight*7/2);// 跳绳
        values[17]=(int) Math.round(weight*3.5/2);// 高尔夫 
    }

    @Override
    public void setValue() {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mLayout.setVisibility(View.GONE);
            mJkjyText.setText(R.string.toast_info_none_resident);
            return;
        }

        Jktj_kstj jktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        String weight = jktj_kstj.getWeight().trim();
        String bmi = jktj_kstj.getbMI().trim();
        if (bmi.equals("") || Float.parseFloat(bmi) <= 0 || weight.equals("")
                || Float.parseFloat(weight) <= 0) {
            mLayout.setVisibility(View.GONE);
            mJkjyText.setText("您没有做体重相关检测，无法对您评估!");
            return;
        }

        mLayout.setVisibility(View.VISIBLE);
        mWeightText.setText(weight);
        calculateValues(Float.parseFloat(weight));
        mGridView.setAdapter(mGridAdapter);
        mJkjyText.setText(ydcfjl[getTzjl(Float.parseFloat(bmi))]);
    }

    @Override
    public boolean getValue() {
        return false;
    }

    
    private int getTzjl(double bmiValue){
        if(bmiValue>0&&bmiValue<18.5){
            return 0;
        }else if (bmiValue<25) {
            return 1;
        }else if (bmiValue<28) {
            return 2;
        }else if (bmiValue<100) {
            return 3;
        }
        return 3;
    }
    
    // 体重结论
    private String ydcfjl[] = new String[] {
            "鉴于您的体征信息，建议您每天运动消耗200卡路里。根据上图所示每项运动所消耗的能量值，请您选择合理的运动方式和运动时间，并加强平常的锻炼。",
            "鉴于您的体征信息，建议您每天运动消耗100-150卡路里。根据上图所示每项运动所消耗的能量值，请您选择合理的运动方式和运动时间，并加强平常的锻炼。",
            "鉴于您的体征信息，建议您每天运动消耗250-300卡路里。根据上图所示每项运动所消耗的能量值，请您选择合理的运动方式和运动时间，并加强平常的锻炼。",
            "鉴于您的体征信息，建议您每天运动消耗300卡路里以上。根据上图所示每项运动所消耗的能量值，请您选择合理的运动方式和运动时间，并加强平常的锻炼。",
    };

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
