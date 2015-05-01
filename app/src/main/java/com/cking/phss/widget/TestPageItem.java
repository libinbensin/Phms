/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * HealthTzbsPageItem.java
 * classes : com.cking.phss.widget.HealthTzbsPageItem
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-20 下午4:54:18
 */
package com.cking.phss.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.util.DisplayUtil;

/**
 * com.cking.phss.widget.HealthTzbsPageItem
 * @author Administrator <br/>
 * create at 2014-6-20 下午4:54:18
 */
public class TestPageItem extends LinearLayout {
    private static final String TAG = "HealthTzbsPageItem";
    
    private float widthStep = 0;
    
    ImageView glxImageView; // 高亮线
    ImageView ptxImageView; // 普通线
    int pageCount = 0;
    int selectIndex = 0;
    
    TextView[] pageTextViews = new TextView[10];
    boolean attachedToWindow = false;
    
    /**
     * @param context
     * @param attrs
     */
    public TestPageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        widthStep = DisplayUtil.dip2px(126, DisplayUtil.getScale(context));
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.health_tzbs_page_item, this);
        
        ptxImageView = (ImageView) findViewById(R.id.ptxImageView);
        glxImageView = (ImageView) findViewById(R.id.glxImageView);
        pageTextViews[0] = (TextView) findViewById(R.id.page01TextView);
        pageTextViews[1] = (TextView) findViewById(R.id.page02TextView);
        pageTextViews[2] = (TextView) findViewById(R.id.page03TextView);
        pageTextViews[3] = (TextView) findViewById(R.id.page04TextView);
        pageTextViews[4] = (TextView) findViewById(R.id.page05TextView);
        pageTextViews[5] = (TextView) findViewById(R.id.page06TextView);
        pageTextViews[6] = (TextView) findViewById(R.id.page07TextView);
        pageTextViews[7] = (TextView) findViewById(R.id.page08TextView);
        pageTextViews[8] = (TextView) findViewById(R.id.page09TextView);
        pageTextViews[9] = (TextView) findViewById(R.id.page10TextView);
    }
    
    public void setPageCount(int count) {
        pageCount = count;
    }
    
    public void setSelectIndex(int index) {
        selectIndex = index;
        updateView();
    }
    
    private void updateView() {
        int times = selectIndex / 10; // 第几页
        int left = selectIndex % 10; // 显示当页第几个
        int currCount = (pageCount - (times * 10)) >= 10 ? 10 : pageCount % 10; // 当页最大数

        // 显示需要的按钮
        int need = 0;
        while (need < currCount) {
            pageTextViews[need].setVisibility(View.VISIBLE);
            pageTextViews[need].setBackgroundResource(R.drawable.sheet_key__normal);
            need++;
        }
        // 高亮按钮
        int highlight = 0;
        while (highlight <= left) {
            pageTextViews[highlight].setVisibility(View.VISIBLE);
            pageTextViews[highlight].setBackgroundResource(R.drawable.sheet_key_pressed);
            highlight++;
        }
        // 高亮线
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) glxImageView.getLayoutParams();
        params.width = getLineWidth(glxImageView, pageTextViews[left]);
        glxImageView.setLayoutParams(params);
        // 隐藏多余的按钮
        int rest = currCount;
        while (rest < 10) {
            pageTextViews[rest++].setVisibility(View.INVISIBLE);
        }
        // 设置普通线宽度
        params = (FrameLayout.LayoutParams) ptxImageView.getLayoutParams();
        params.width = getLineWidth(ptxImageView, pageTextViews[currCount - 1]);
        ptxImageView.setLayoutParams(params);
        // 标记页号
        int page = 0;
        while (page < currCount) {
            pageTextViews[page].setText((times * 10 + page + 1) + "");
            page++;
        }
    }
    
    private int getLineWidth(View beginView, View endView) {
        final int[] location0 = new int[2]; 
        beginView.getLocationOnScreen(location0);
        final int[] location1 = new int[2]; 
        endView.getLocationOnScreen(location1);
        
        return location1[0] - location0[0] + 5;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        int totalWidth = ptxImageView.getWidth();
//        Rect r = new Rect();
//        ptxImageView.getLocalVisibleRect(r);
//        ptxImageView.getWindowVisibleDisplayFrame(r);
//        ptxImageView.getDrawingRect(r);
//        widthStep = 126;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
     // 由于updateView在第一次调用时尚未加入到页面无法获取位置，所以在这里补调
        if (!attachedToWindow) {
            Handler handler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    
                    updateView();
                }
                
            };
            handler.sendEmptyMessage(0);
        }
        attachedToWindow = true;
    }
}
