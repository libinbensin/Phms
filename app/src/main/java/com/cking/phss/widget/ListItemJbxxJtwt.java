package com.cking.phss.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * 家庭成员的Item
 * 
 * @author AUS
 * 
 */
public class ListItemJbxxJtwt extends RelativeLayout {
//	private TextView mIndexText = null;// 序号
	 
	private TextView xhTextView = null;// 序号
	private TextView jdTextView = null;// 阶段
	private TextView fsrTextView = null;// 发生人
    private TextView fsrqTextView = null;// 发生日期
    private TextView zywtTextView = null;// 主要问题
    private TextView wtpgTextView = null;// 问题评估
    private TextView cljjgTextView = null;// 处理及结果
    private TextView zgzlTextView = null;// 主观资料
	private String disSn="";//序号
    private String jd = "";
    private String fsr = "";
    private String fsrq = "";
    private String wtpg = "";
    private String zywt = "";
    private String cljjg="";
    private String zgzl = "";
    private String kgzl = "";
	private String qt = "";
	private String gljh="";
    private String jlys="";
    private String jlrq="";
    private String bz="";
    View[] needResetViews = null;
	public ListItemJbxxJtwt(Context context) {
		super(context);
		init(context);
	}

	public ListItemJbxxJtwt(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_jtwt_layout, this);

//		mIndexText = (TextView) findViewById(R.id.index_text);
		xhTextView = (TextView) findViewById(R.id.xhTextView);
		jdTextView = (TextView) findViewById(R.id.jdTextView);
		fsrTextView = (TextView) findViewById(R.id.fsrTextView);
		fsrqTextView = (TextView) findViewById(R.id.fsrqTextView);
		zywtTextView = (TextView) findViewById(R.id.zywtTextView);
        wtpgTextView = (TextView) findViewById(R.id.wtpgTextView);
        cljjgTextView = (TextView) findViewById(R.id.cljjgTextView);
        zgzlTextView = (TextView) findViewById(R.id.zgzlTextView);
        LinearLayout czLinearLayout = (LinearLayout) findViewById(R.id.czLinearLayout);
        needResetViews = new View[] { xhTextView, jdTextView, fsrTextView, fsrqTextView,
                zywtTextView, wtpgTextView, cljjgTextView, zgzlTextView,
                czLinearLayout };
	}

	public void setIndex(int index) {
//		mIndexText.setText(index + ".");
		setTag(index);
        if (index % 2 == 1) {
            setBackgroundResource(R.color.list_jsh_background_color);
        } else {
            setBackgroundResource(R.color.list_osh_background_color);
        }
	}

	public int getIndex() {
		return (Integer) getTag();
	}

    public String getDisSn() {
        return disSn;
    }

    public void setDisSn(String disSn) {
        this.disSn = disSn;
        xhTextView.setText(disSn);
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
        jdTextView.setText(jd);
    }

    public String getFsr() {
        return fsr;
    }

    public void setFsr(String fsr) {
        this.fsr = fsr;
        fsrTextView.setText(fsr);
    }

    public String getFsrq() {
        return fsrq;
    }

    public void setFsrq(String fsrq) {
        this.fsrq = fsrq;
        fsrqTextView.setText(fsrq);
    }

    public String getWtpg() {
        return wtpg;
    }

    public void setWtpg(String wtpg) {
        this.wtpg = wtpg;
        wtpgTextView.setText(wtpg);
    }

    public String getZywt() {
        return zywt;
    }

    public void setZywt(String zywt) {
        this.zywt = zywt;
        zywtTextView.setText(zywt);
    }

    public String getCljjg() {
        return cljjg;
    }

    public void setCljjg(String cljjg) {
        this.cljjg = cljjg;
        cljjgTextView.setText(cljjg);
    }

    public String getZgzl() {
        return zgzl;
    }

    public void setZgzl(String zgzl) {
        this.zgzl = zgzl;
        zgzlTextView.setText(zgzl);
    }

    public String getKgzl() {
        return kgzl;
    }

    public void setKgzl(String kgzl) {
        this.kgzl = kgzl;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getGljh() {
        return gljh;
    }

    public void setGljh(String gljh) {
        this.gljh = gljh;
    }

    public String getJlys() {
        return jlys;
    }

    public void setJlys(String jlys) {
        this.jlys = jlys;
    }

    public String getJlrq() {
        return jlrq;
    }

    public void setJlrq(String jlrq) {
        this.jlrq = jlrq;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @param widths
     */
    public void setViewByWidths(List<Integer> widths) {
        for (int i = 0; i<needResetViews.length; i++) {
            View childView = needResetViews[i];
            if (childView instanceof TextView) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childView.getLayoutParams(); 
                params.width = widths.get(i);
                childView.setLayoutParams(params);
            }
            
        }
    }

}
