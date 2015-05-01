package com.cking.phss.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.util.TispToastFactory;

public class JkjyPage02 extends LinearLayout implements IPage {
    protected static final String TAG = "JkjyPage02";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap;

    public List<Integer> valueList=new ArrayList<Integer>();//一共14种风险因素，按照下面顺序从0 到13
    public static String[] items=new String[]{
           "吸烟","饮酒","高血压","低血压","血糖偏高","血糖偏低","胆固醇偏高","甘油三酯偏高","高密度脂蛋白偏低","低密度脂蛋白偏高"
           ,"消瘦","超重","肥胖","腰部脂肪偏多"
    };
    // 控件
    ListView fxysListView;
    private WebView mWebView;
    private List<Map<String, String>> listItems = new ArrayList<Map<String, String>>();

    public JkjyPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public JkjyPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_jkjy_02_layout, this);
        fxysListView = (ListView) findViewById(R.id.fxysListView);
    }

    @Override
    public void setValue() {
        listItems.clear();
        valueList.clear();

        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            fxysListView.setVisibility(View.GONE);
            return;
        }

        calculateAllValues();

        for (int i = 0; i < valueList.size(); i++) {
            String item = items[valueList.get(i)];
            Map<String, String> map = new HashMap<String, String>();
            map.put("item", i + 1 + "、" + item);
            map.put("url", "file:///mnt/sdcard/phms/res/jkjy/风险因素/" + item + ".html");

            listItems.add(map);
        }

        if (valueList.size() > 0) {
            fxysListView.setVisibility(View.VISIBLE);
            final ListAdapter listAdapter = new ListAdapter();
            fxysListView.setAdapter(listAdapter);
            // fxysListView.setOnItemClickListener(new OnItemClickListener() {
            //
            // @Override
            // public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
            // long arg3) {
            // /*if (lastSelectedIndex == arg2) { // 点了已经点开的则关闭
            // View lastSelectedChild =
            // fxysListView.getChildAt(lastSelectedIndex);
            // WebView fxysWebView = (WebView)
            // lastSelectedChild.findViewById(R.id.fxysWebView);
            // fxysWebView.setVisibility(View.GONE);
            // lastSelectedIndex = -1;
            // } else*/ {
            // if (lastSelectedIndex >= 0) {
            // View lastSelectedChild =
            // fxysListView.getChildAt(lastSelectedIndex);
            // if (lastSelectedChild == null) {
            // listAdapter.notifyDataSetChanged();
            // return;
            // }
            // Log.i(TAG, "lastSelectedChild:" + lastSelectedChild);
            // WebView lfxysWebView = (WebView)
            // lastSelectedChild.findViewById(R.id.fxysWebView);
            // lfxysWebView.setVisibility(View.GONE);
            // listAdapter.notifyDataSetChanged();
            // }
            // WebView fxysWebView = (WebView)
            // arg1.findViewById(R.id.fxysWebView);
            // fxysWebView.setVisibility(View.VISIBLE);
            // lastSelectedIndex = arg2;
            // listAdapter.notifyDataSetChanged();
            // }
            // }
            // });
        }

    }

    class ListAdapter extends BaseAdapter {
        
        private int lastSelectedIndex = -1;
        
        @Override
        public int getCount() {
            return listItems.size();
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
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.item_fxys_layout, null);
                TextView fxysTextView = (TextView) convertView.findViewById(R.id.fxysTextView);
                LinearLayout fxysLinearLayout = (LinearLayout) convertView
                        .findViewById(R.id.fxysLinearLayout);
                final WebView fxysWebView = (WebView) convertView.findViewById(R.id.fxysWebView);
                if (position >= 0 && position < listItems.size()) {
                    String text = listItems.get(position).get("item");
                    fxysTextView.setText(text);
                    String url = listItems.get(position).get("url");
                    fxysWebView.loadUrl(url);
                    fxysLinearLayout.setTag(position);
                    fxysLinearLayout.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                            int position = (Integer) v.getTag();
                            if (lastSelectedIndex == position) { // 点了已经点开的则关闭
                                View lastSelectedChild = fxysListView.getChildAt(lastSelectedIndex);
                                WebView fxysWebView = (WebView) lastSelectedChild.findViewById(R.id.fxysWebView);
                                fxysWebView.setVisibility(View.GONE);
                                lastSelectedIndex = -1;
                            } else {
                                if (lastSelectedIndex >= 0) {
                                    View lastSelectedChild = fxysListView.getChildAt(lastSelectedIndex);
                                    if (lastSelectedChild == null) {
                                        notifyDataSetChanged();
                                        return;
                                    }
                                    Log.i(TAG, "lastSelectedChild:" + lastSelectedChild);
                                    WebView lfxysWebView = (WebView) lastSelectedChild.findViewById(R.id.fxysWebView);
                                    lfxysWebView.setVisibility(View.GONE);
                                    notifyDataSetChanged();
                                }
                                fxysWebView.setVisibility(View.VISIBLE);
                                lastSelectedIndex = position;
                                notifyDataSetChanged();
                            }
                        }
                    });
                }
            }

            return convertView;
        }
    }

    private void calculateAllValues() {
        Sfgljl_gxy sfgljl_gxy = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
        Sfgljl_tnb sfgljl_tnb = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        Jktj_kstj jktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());

        if (sfgljl_gxy != null && sfgljl_tnb != null) {
            // 吸烟
            if (sfgljl_gxy.getbCXYL() > 0 || sfgljl_tnb.getbCXYL() > 0) {
                valueList.add(0);
            }

            // 饮酒
            if (sfgljl_gxy.getbCYJ() > 0 || sfgljl_tnb.getbCYJ() > 0) {
                valueList.add(1);
            }
        }

        if (jktj_kstj == null)
            return;
        // 血压
        String xyjl = jktj_kstj.getxYJL();
        if (xyjl != null && xyjl.contains("高")) {// 是高血压的结论
            valueList.add(2);
        } else if (xyjl != null && xyjl.contains("低")) {// 是低血压的结论
            valueList.add(3);
        }

        // 血糖
        String xtjl = jktj_kstj.getxTJL();
        if (xtjl != null && xtjl.contains("高")) {// 是高血糖的结论
            valueList.add(4);
        } else if (xtjl != null && xtjl.contains("低")) {// 是低血糖的结论
            valueList.add(5);
        }

        // 胆固醇偏高
        String dgcjl = jktj_kstj.getdGCJL();
        if (xtjl != null && xtjl.contains("多")) {//
            valueList.add(6);
        }

        // 甘油三酯偏高
        String gyszjl = jktj_kstj.getgYSZJL();
        if (gyszjl != null && gyszjl.contains("多")) {//
            valueList.add(7);
        }

        // 高密度脂蛋白偏低
        String hdlr = jktj_kstj.getHDLR();
        if (hdlr != null && hdlr.contains("低")) {//
            valueList.add(8);
        }

        // 低密度脂蛋白偏高
        String ldlr = jktj_kstj.getLDLR();
        if (ldlr != null && ldlr.contains("高")) {//
            valueList.add(9);
        }

        // "消瘦","超重","肥胖",
        String bmi = jktj_kstj.getbMI();
        if (bmi != null && !bmi.equals("") && Float.parseFloat(bmi) > 0) {
            int level = getTzjl(Float.parseFloat(bmi));
            if (level == 0) {
                valueList.add(10);
            } else if (level == 2) {
                valueList.add(11);
            } else if (level == 3) {
                valueList.add(12);
            }
        }

        // "腰部脂肪偏多"
        String ytbjl = jktj_kstj.getYtbjl();
        if (ytbjl != null && ytbjl.contains("肥胖")) {//
            valueList.add(13);
        }
    }

    private int getTzjl(double bmiValue) {
        if (bmiValue > 0 && bmiValue < 18.5) {
            return 0;
        } else if (bmiValue < 25) {
            return 1;
        } else if (bmiValue < 28) {
            return 2;
        } else if (bmiValue < 100) {
            return 3;
        }
        return 3;
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

}
