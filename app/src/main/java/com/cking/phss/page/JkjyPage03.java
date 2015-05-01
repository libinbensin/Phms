package com.cking.phss.page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.Constant;
import com.cking.phss.util.TispToastFactory;

public class JkjyPage03 extends LinearLayout implements OnItemClickListener {
    private Context mContext = null;
    private Toast mToast = null;

    private int mIndex = 0;// 列表当前选项ID
    private ListView mListView;
    private ImageView imageView;
    private Bitmap currentBitmap;

    private ListAdapter mListAdapter;
    private View tagView;
    public JkjyPage03(Context context) {
        super(context);
        init(context);
    }

    public JkjyPage03(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_jkjy_03_layout, this);

        mListView = (ListView) findViewById(R.id.listView);
        imageView = (ImageView) findViewById(R.id.image_info);
        changeImage(0);
    }

    @Override
    protected void onAttachedToWindow() {
        mListAdapter=new ListAdapter();
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(this);
        super.onAttachedToWindow();
    }

    private String[] titles = new String[] { "0-6个月婴儿", "6-12个月婴儿", "1-3岁幼儿", "学龄前儿童", "孕前期",
            "孕早期", "孕中、晚期", "哺乳期妇女", "成人", };

    public void changeImage(int index) {
        final String path = Constant.exterNalPath + "/phms/res/jkjy/膳食指导/" + titles[index] + ".png";
        new Thread() {
            public void run() {
                if (currentBitmap != null)
                    currentBitmap=null;
                currentBitmap = BitmapFactory.decodeFile(path);
                handler.sendEmptyMessage(0);
            };
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
        // listview选择项高亮保持
        if(tagView!=null){
            tagView.setBackgroundDrawable(null);
            tagView=null;
        }
        arg1.setBackgroundResource(R.drawable.ydcg_bg);
        if (((ListView) arg0).getTag() != null) {
            if (mIndex != index) {
                // 判断是否选择不为同一选择项，不是则清空上次选择项背景，并记录本次选择项背景
                ((View) ((ListView) arg0).getTag()).setBackgroundDrawable(null);
                mIndex = index;
                ((ListView) arg0).setTag(arg1);
                arg1.setBackgroundResource(R.drawable.ydcg_bg);
                changeImage(index);
            } else {
                ((ListView) arg0).setTag(arg1);
            }

        } else {
            ((ListView) arg0).setTag(arg1);
            // 初次点击设这当前选择项背景图片
            arg1.setBackgroundResource(R.drawable.ydcg_bg);
            mIndex = index;
            changeImage(index);
        }
        // 设置点击加载,建议用handle做
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            BitmapDrawable bitmapDrawable=(BitmapDrawable)imageView.getBackground();
            if(bitmapDrawable!=null){
                Bitmap bitmap=bitmapDrawable.getBitmap();
                if(bitmap!=null){
                    imageView.setBackgroundDrawable(null);
                    bitmap.recycle();
                }
            }
            imageView.setBackgroundDrawable(new BitmapDrawable(currentBitmap));
        };
    };
    
    class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return titles.length;
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
            LayoutInflater inflater=LayoutInflater.from(mContext);
            View view=inflater.inflate(R.layout.liveitem, null);
            TextView textView=(TextView)view.findViewById(R.id.itemlive);
            if(position>=0&&position<titles.length)
            textView.setText(titles[position]);
            if(position==mIndex){
                view.setBackgroundResource(R.drawable.ydcg_bg);
                tagView=view;
            }
            return view;
        }
        
    }
}
