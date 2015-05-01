package com.cking.phss.page;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.file.FileHelper;
import com.cking.phss.util.Constant;
import com.cking.phss.util.TispToastFactory;

public class JkjyPage05 extends LinearLayout implements IPage {
    private Context mContext = null;
    private Toast mToast = null;

    // 控件
    private RelativeLayout mLayout1;
    private RelativeLayout mLayout2;
    private RelativeLayout mLayout3;
    private RelativeLayout mLayout4;
    private TextView mResult01Text;
    private TextView mResult02Text;
    private TextView mResult03Text;
    private TextView mResult04Text;

    private Map<String, Object> maps;

    private HashMap<String, SoftReference<String>> textCache;

    public JkjyPage05(Context context) {
        super(context);
        textCache = new HashMap<String, SoftReference<String>>();
        init(context);
    }

    public JkjyPage05(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_jkjy_05_layout, this);
        mLayout1 = (RelativeLayout) findViewById(R.id.layout1);
        mLayout2 = (RelativeLayout) findViewById(R.id.layout2);
        mLayout3 = (RelativeLayout) findViewById(R.id.layout3);
        mLayout4 = (RelativeLayout) findViewById(R.id.layout4);
        mResult01Text = (TextView) findViewById(R.id.result1_text);
        mResult02Text = (TextView) findViewById(R.id.result2_text);
        mResult03Text = (TextView) findViewById(R.id.result3_text);
        mResult04Text = (TextView) findViewById(R.id.result4_text);
    }

    public void setValue(Map<String, Object> maps) {
        this.maps = maps;
        if (maps == null) {
            return;
        }
        setValue();
    }

    @Override
    public void setValue() {
        String page01Result = "";
        List<Integer> page02Result = null;
        String page04Result = "";
        mLayout1.setVisibility(View.GONE);
        mLayout2.setVisibility(View.GONE);
        mLayout3.setVisibility(View.GONE);
        mLayout4.setVisibility(View.GONE);
        try {
            page01Result = (String) maps.get("page01Result");
            page02Result = (List<Integer>) maps.get("page02Result");
//            page04Result = (String) maps.get("page04Result");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        // 营养评估结论的显示
        if (page01Result == null || page01Result.equals("")) {
            mLayout1.setVisibility(View.GONE);
        } else {
            mLayout1.setVisibility(View.VISIBLE);
            mResult01Text.setText(page01Result);
        }

        // 风险因素
        if (page02Result == null || page02Result.size() <= 0) {
            mLayout2.setVisibility(View.GONE);
        } else {
            mLayout2.setVisibility(View.VISIBLE);
            String parentPath = Constant.RES_JKJY_PATH + "风险因素/";
            String fileText = "";
            for (int i = 0; i < page02Result.size(); i++) {
                int index = page02Result.get(i);
                fileText += (indexTitle[i]);
                if ((index >= 0 && index <= 5) || index == 10 || index == 13) {
                    fileText += loadTextFile(parentPath + JkjyPage02.items[index] + ".txt");
                } else if (index == 11 || index == 12) {
                    fileText += loadTextFile(parentPath + "超重、肥胖.txt");
                } else {
                    fileText += loadTextFile(parentPath + "高血脂.txt");
                }
                if(i!=page02Result.size()-1)
                  fileText += "\n\n";
            }
            mResult02Text.setText(fileText);
        }

        // 运动处方
        if (page04Result == null || page04Result.equals("")) {
            mLayout3.setVisibility(View.GONE);
        } else {
            mLayout3.setVisibility(View.VISIBLE);
            mResult03Text.setText(page04Result);
        }
    }

    @Override
    public boolean getValue() {
        return false;
    }

    private String loadTextFile(String filePath) {
//        if (textCache.containsKey(filePath)) {
//            SoftReference<String> softReference = textCache.get(filePath);
//            if (softReference.get() != null) {
//                return softReference.get();
//            }
//        }

        File file = new File(filePath);
        if (!file.exists())
            return "";
        String text = FileHelper.getInstance().readFileToGBKString(file);
//        textCache.put(filePath, new SoftReference<String>(text));
//        text=null;
        return text;
    }

    private String[] indexTitle = new String[] { "（一）、", "（二）、", "（三）、", "（四）、", "（五）、", "（六）、",
            "（七、", "（八）、", "（九）、", "（十）、", "（十一）、", "（十二）、", "（十三）、", "（十四）、", };

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
