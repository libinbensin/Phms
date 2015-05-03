package com.cking.phss.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.adinnet.xd.medical.widget.MyButton;
import com.cking.phss.R;
import com.cking.phss.controller.JbxxMainController;
import com.cking.phss.controller.JkjyMainController;
import com.cking.phss.controller.JktjMainController;
import com.cking.phss.controller.SfglMainController;
import com.cking.phss.controller.SjglMainController;
import com.cking.phss.controller.XtszMainController;
import com.cking.phss.controller.YyzdMainController;
import com.cking.phss.global.Global;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.util.StringUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = "MainActivity";

    private RelativeLayout mBodyLayout = null;
    private static ProgressBar sGlobalProgBar = null;
    private Toast mToast = null;

    TextView textview_head_text = null;
    Button button_head_back = null;
    MyButton imageview_head_search = null;
    MyButton imageview_head_setting = null;

    private Context mContext = null;

    /**
     * 基本信息
     */
    private JbxxMainController mJbxxMainController = null;
    /**
     * 健康体检
     */
    private JktjMainController mJktjMainController = null;
    /**
     * 用药指导
     */
    private YyzdMainController mYyzdMainController = null;
    /**
     * 健康教育
     */
    private JkjyMainController mJkjyMainController = null;
    /**
     * 随访管理
     */
    private SfglMainController mSfglMainController = null;
    /**
     * 数据管理
     */
    private SjglMainController mSjglMainController = null;
    /**
     * 系统设置
     */
    private XtszMainController mXtszMainController = null;

    private RelativeLayout gnRelativeLayout;
    private SlidingDrawer sd;
    private ImageView iv;

    MyButton daxxMyButton;
    MyButton jktjMyButton;
    MyButton sfglMyButton;
    MyButton ycjhMyButton;
    MyButton jkjyMyButton;
    MyButton yyzdMyButton;

    public class Function {
        public int resId;
        public int selectedResId;
        public String functionName;
        int tag;

        public Function(int resId, int selectedResId, String functionName, int tag) {
            this.resId = resId;
            this.selectedResId = selectedResId;
            this.functionName = functionName;
            this.tag = tag;
        }
    }

    private List<Function> functions = new ArrayList<Function>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        mBodyLayout = (RelativeLayout) findViewById(R.id.body_layout);
        mToast = TispToastFactory.getToast(this, "");
        button_head_back = (Button) findViewById(R.id.button_head_back);
        textview_head_text = (TextView) findViewById(R.id.textview_head_text);
        imageview_head_search = (MyButton) findViewById(R.id.imageview_head_search);
        imageview_head_setting = (MyButton) findViewById(R.id.imageview_head_setting);

        Intent intent = getIntent();
        int tag = intent.getIntExtra("tag", Constants.PAGE_JBXX);
        Global.activePageId = tag;
        switch (tag) {
            case Constants.PAGE_JBXX:
                textview_head_text.setText(R.string.title_jbxx);
                if (mJbxxMainController == null) {
                    mJbxxMainController = new JbxxMainController(this);
                }
                mBodyLayout.removeAllViews();
                mBodyLayout.addView(mJbxxMainController.getView());
                String paperNum = intent.getStringExtra("paperNum");
                if (!StringUtil.isEmptyString(paperNum)) {
                    mJbxxMainController.autoSearchPaperNum(paperNum);
                }
                break;

            case Constants.PAGE_JKTJ:
                textview_head_text.setText(R.string.title_jktj);
                if (mJktjMainController == null) {
                    mJktjMainController = new JktjMainController(this);
                }
                mBodyLayout.removeAllViews();
                mBodyLayout.addView(mJktjMainController.getView());
                break;

            case Constants.PAGE_SFGL:
                textview_head_text.setText(R.string.title_sfgl);
                if (mSfglMainController == null) {
                    mSfglMainController = new SfglMainController(this);
                }
                mBodyLayout.removeAllViews();
                mBodyLayout.addView(mSfglMainController.getView());
                break;

            case Constants.PAGE_YCJH:
                return;

            case Constants.PAGE_JKJY:
                textview_head_text = (TextView) findViewById(R.id.textview_head_text);
                textview_head_text.setText(R.string.title_jkjy);
                if (mJkjyMainController == null) {
                    mJkjyMainController = new JkjyMainController(this);
                }
                mBodyLayout.removeAllViews();
                mBodyLayout.addView(mJkjyMainController.getView());
                break;

            case Constants.PAGE_YYZD:
                textview_head_text.setText(R.string.title_yyzd);
                if (mYyzdMainController == null) {
                    mYyzdMainController = new YyzdMainController(this);
                }
                mBodyLayout.removeAllViews();
                mBodyLayout.addView(mYyzdMainController.getView());
                break;

            case Constants.PAGE_SJGL:
                textview_head_text.setText(R.string.title_sjgl);
                imageview_head_search.setVisibility(View.INVISIBLE);
                imageview_head_setting.setVisibility(View.INVISIBLE);
                if (mSjglMainController == null) {
                    mSjglMainController = new SjglMainController(this);
                }
                mBodyLayout.removeAllViews();
                mBodyLayout.addView(mSjglMainController.getView());
                break;

            case Constants.PAGE_XTSZ:
                textview_head_text.setText(R.string.title_xtsz);
                imageview_head_search.setVisibility(View.INVISIBLE);
                imageview_head_setting.setVisibility(View.INVISIBLE);
                if (mXtszMainController == null) {
                    mXtszMainController = new XtszMainController(this);
                }
                mBodyLayout.removeAllViews();
                mBodyLayout.addView(mXtszMainController.getView());
                break;

            default:
                finish();
                break;
        }
        if (getIntent().hasExtra("doctorID")) {
            Global.doctorID = getIntent().getStringExtra("doctorID");
        }
        if (getIntent().hasExtra("doctorName")) {
            Global.doctorName = getIntent().getStringExtra("doctorName");
        }
        imageview_head_search.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.putExtra("tag", Constants.PAGE_SJGL);
                intent.putExtra("doctorID", Global.doctorID);
                intent.putExtra("doctorName", Global.doctorName);
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
                // 设置切换动画，从右边进入，左边退出,带动态效果
                overridePendingTransition(R.anim.dync_in_from_right, R.anim.dync_out_to_left);
            }
        });
        imageview_head_setting.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.putExtra("tag", Constants.PAGE_XTSZ);
                intent.putExtra("doctorID", Global.doctorID);
                intent.putExtra("doctorName", Global.doctorName);
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
                // 设置切换动画，从右边进入，左边退出,带动态效果
                overridePendingTransition(R.anim.dync_in_from_right, R.anim.dync_out_to_left);
            }
        });
        button_head_back.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                finish();
                overridePendingTransition(R.anim.dync_in_from_right, R.anim.dync_out_to_left);
            }
        });

        // 抽屉
        gnRelativeLayout = (RelativeLayout) findViewById(R.id.gnRelativeLayout);
        gnRelativeLayout.setVisibility(View.GONE);
        sd = (SlidingDrawer) findViewById(R.id.sliding);
        iv = (ImageView) findViewById(R.id.imageViewIcon);
        daxxMyButton = (MyButton) findViewById(R.id.daxxMyButton);
        jktjMyButton = (MyButton) findViewById(R.id.jktjMyButton);
        sfglMyButton = (MyButton) findViewById(R.id.sfglMyButton);
        ycjhMyButton = (MyButton) findViewById(R.id.ycjhMyButton);
        jkjyMyButton = (MyButton) findViewById(R.id.jkjyMyButton);
        yyzdMyButton = (MyButton) findViewById(R.id.yyzdMyButton);
        daxxMyButton.setOnClickListener(this);
        jktjMyButton.setOnClickListener(this);
        sfglMyButton.setOnClickListener(this);
        // ycjhMyButton.setOnClickListener(this);
        jkjyMyButton.setOnClickListener(this);
        yyzdMyButton.setOnClickListener(this);
        sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {// 开抽屉
            @Override
            public void onDrawerOpened() {
                iv.setBackgroundResource(R.drawable.chouti_down_bg);// 响应开抽屉事件
                // ，把图片设为向下的
                gnRelativeLayout.setVisibility(View.VISIBLE);
            }
        });
        sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                iv.setBackgroundResource(R.drawable.chouti_up_bg);// 响应关抽屉事件
                gnRelativeLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PAGE_JKTJ) {
            mJktjMainController.setValue();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.dync_in_from_right, R.anim.dync_out_to_left);
    }

    /**
     * 
     */
    private void loadApps() {

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        Function funtion = null;
        funtion = new Function(R.drawable.chouti_daxx, R.drawable.chouti_daxx_selected, "档案信息",
                Constants.PAGE_JBXX);
        functions.add(funtion);
        funtion = new Function(R.drawable.chouti_jktj, R.drawable.chouti_jktj_selected, "健康体检",
                Constants.PAGE_JKTJ);
        functions.add(funtion);
        funtion = new Function(R.drawable.chouti_sfgl, R.drawable.chouti_sfgl_selected, "随访管理",
                Constants.PAGE_SFGL);
        functions.add(funtion);
        funtion = new Function(R.drawable.chouti_ycjh, R.drawable.chouti_ycjh_selected, "远程监护",
                Constants.PAGE_YCJH);
        functions.add(funtion);
        funtion = new Function(R.drawable.chouti_jkjy, R.drawable.chouti_jkjy_selected, "健康教育",
                Constants.PAGE_JKJY);
        functions.add(funtion);
        funtion = new Function(R.drawable.chouti_yyzd, R.drawable.chouti_yyzd_selected, "用药指导",
                Constants.PAGE_YYZD);
        functions.add(funtion);
    }

    public class GridAdapter extends BaseAdapter {
        public GridAdapter() {

        }

        public int getCount() {
            // TODO Auto-generated method stub
            return functions.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return functions.get(position);
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.item_chouti_layout, null);
                MyButton gnMyButton = (MyButton) convertView.findViewById(R.id.gnMyButton);
                TextView gnTextView = (TextView) convertView.findViewById(R.id.gnTextView);
                Function function = functions.get(position);
                gnMyButton.setBackgroundResource(function.resId);
                gnMyButton.setTag(function.tag);
                gnTextView.setText(function.functionName);
                gnMyButton.setOnClickListener(new OnClickListener() {
                    
                    @Override
                    public void onClick(View arg0) {
                        Intent intent = new Intent();
                        intent.putExtra("tag", (Integer) arg0.getTag());
                        intent.putExtra("doctorID", Global.doctorID);
                        intent.putExtra("doctorName", Global.doctorName);
                        intent.setClass(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        sd.close();
                    }
                });
            }

            return convertView;
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean hasResidentInfo() {
        return MyApplication.getInstance().getSession().getCurrentResident() != null;
    }

    /**
     * 显示/隐藏进度条
     * 
     * @param v
     */
    public static void setProgressBarVisibility(int v) {
        sGlobalProgBar.setVisibility(v);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View arg0) {
        Intent intent = new Intent();
        int tag = Integer.parseInt((String) arg0.getTag());

        if (tag == Constants.PAGE_JKTJ
                && MyApplication.getInstance().getJktjFirstVisibleIndex() < 0) {
            return;
        }
        if (tag == Constants.PAGE_SFGL
                && MyApplication.getInstance().getSfglFirstVisibleIndex() < 0) {
            return;
        }
        if (Global.activePageId == tag) {
            mToast.setText("已经是当前页面了");
            mToast.show();
        } else {
            finish();
            intent.putExtra("tag", tag);
            intent.putExtra("doctorID", Global.doctorID);
            intent.putExtra("doctorName", Global.doctorName);
            intent.setClass(MainActivity.this, MainActivity.class);
            startActivity(intent);
            // 设置切换动画，从右边进入，左边退出,带动态效果
            overridePendingTransition(R.anim.dync_in_from_right, R.anim.dync_out_to_left);
        }
        sd.close();
    }
    
    //清空健康体检里面的数据
//    public void clearJktj(){
//        if(mJktjBodyView==null)
//            return;
//        mJktjBodyView.clear();
//    }
}
