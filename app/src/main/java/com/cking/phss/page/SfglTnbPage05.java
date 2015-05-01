package com.cking.phss.page;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

public class SfglTnbPage05 extends MyPage {

	RadioButton ywblfy01RadioButton = null;
	RadioButton ywblfy02RadioButton = null;
	RadioGroup ywblfyRadioGroup;

	EditText ywblfyEditText = null;
	SpinnerUtil dxtfySpinnerUtil = null;
	SpinnerUtil ccsfflSpinnerUtil = null;

	RadioGroup zzRadioGroup;
	RadioButton zz01RadioButton = null;
	RadioButton zz02RadioButton = null;

	EditText yyEditText = null;
	EditText jgjksEditText = null;

	private Map<String, IBean> beanMap = null;
	
	private Toast mToast;

	public SfglTnbPage05(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglTnbPage05(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	protected void init(Context context) {
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_05_layout, this);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
		ywblfy01RadioButton = (RadioButton) findViewById(R.id.ywblfy01RadioButton);
		ywblfy02RadioButton = (RadioButton) findViewById(R.id.ywblfy02RadioButton);
		ywblfyRadioGroup = (RadioGroup) findViewById(R.id.ywblfyRadioGroup);

		ywblfyEditText = (EditText) findViewById(R.id.ywblfyEditText);
		dxtfySpinnerUtil = (SpinnerUtil) findViewById(R.id.dxtfySpinnerUtil);
		ccsfflSpinnerUtil = (SpinnerUtil) findViewById(R.id.ccsfflSpinnerUtil);

		zzRadioGroup = (RadioGroup) findViewById(R.id.zzRadioGroup);
		zz01RadioButton = (RadioButton) findViewById(R.id.zz01RadioButton);
		zz02RadioButton = (RadioButton) findViewById(R.id.zz02RadioButton);
		
		yyEditText = (EditText) findViewById(R.id.yyEditText);
		jgjksEditText = (EditText) findViewById(R.id.jgjksEditText);

        // radio的初始状态和 相应控件的状态
        ywblfyRadioGroup.check(R.id.ywblfy01RadioButton);// 默认没有不良的反应
        ywblfyEditText.setEnabled(false);
        ywblfyRadioGroup
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == ywblfy01RadioButton.getId()) {// 如果点击了无
                            ywblfyEditText.setText("");
                            ywblfyEditText.setEnabled(false);
                        } else {// 如果点击了有
                            ywblfyEditText.setEnabled(true);
                        }
                    }
                });

        // 默认的情况下的状态
        zzRadioGroup.check(R.id.zz01RadioButton);
        yyEditText.setEnabled(false);
        jgjksEditText.setEnabled(false);
        yyEditText.setText("");
        jgjksEditText.setText("");
        
        zzRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.zz01RadioButton) {
                    yyEditText.setEnabled(false);
                    jgjksEditText.setEnabled(false);
                    yyEditText.setText("");
                    jgjksEditText.setText("");
                } else {
                    yyEditText.setEnabled(true);
                    jgjksEditText.setEnabled(true);
                }
            }
        });
	}

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() { if (!hasInit) {return;}
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        if (mSfgljl == null)
            return;

        //不良反应的设置
        if(mSfgljl.getbLFY()==0){
            ywblfy01RadioButton.setChecked(true);
            ywblfyEditText.setEnabled(false);
        }else {
            ywblfy02RadioButton.setChecked(true);
            ywblfyEditText.setEnabled(true);
            ywblfyEditText.setText(mSfgljl.getfYQK());
        }

        // 低血糖反应
        dxtfySpinnerUtil.setSelectedPositionByValue(mSfgljl.getdXTFYCD());

        // 此次访问分类
        ccsfflSpinnerUtil.setSelectedPositionByValue(mSfgljl.getsFFLCD());

		//原因
		String yy = mSfgljl.getzZYY();
		
		//机构及科室
		String jgjks = mSfgljl.getzZKB();
		
		if( StringUtil.isEmptyString(yy) && 
			StringUtil.isEmptyString(jgjks) )
		{
			zzRadioGroup.check(R.id.zz01RadioButton);
		}
		else
		{
			zzRadioGroup.check(R.id.zz02RadioButton);
			yyEditText.setText(yy);
			jgjksEditText.setText(jgjks);
		}
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        if (mSfgljl == null)
            return false;
        
        // 获取药物不良反应
        if (ywblfy01RadioButton.isChecked())
            mSfgljl.setbLFY(0);
        else {
            mSfgljl.setbLFY(1);
            String blfy = ywblfyEditText.getText().toString().trim();
            if (!blfy.equals(""))
                mSfgljl.setfYQK(blfy);
        }

      // 低血糖反应
      mSfgljl.setdXTFYCD(dxtfySpinnerUtil.getSelectedValueInt());

      // 此次访问分类
      mSfgljl.setsFFLCD(ccsfflSpinnerUtil.getSelectedValueInt());

		String zzyy = yyEditText.getText().toString().trim();// 转诊原因
		String jgjks = jgjksEditText.getText().toString().trim();// 机构和科别	
		mSfgljl.setzZYY(zzyy);
		mSfgljl.setzZKB(jgjks);
        
        return true;
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
