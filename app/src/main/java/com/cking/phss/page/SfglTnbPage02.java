package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

public class SfglTnbPage02 extends MyPage {

    private static final String TAG = "SfglTnbPage02";
    EditText xy01EditText;
    EditText xy02EditText;
    
    EditText xlEditText;// 心率
    EditText sgEditText;

    EditText tz01EditText;
    EditText tz02EditText;
    
    EditText tzzs01EditText;
    EditText tzzs02EditText;
    
    SpinnerUtil zbdmbdSpinnerUtil = null;
    
    EditText yw01EditText = null;
    EditText yw02EditText = null;
    
    EditText qtEditText;// 体征 其他

    Button tjsjButton;// 获取体检数据的按钮，布局中没有

    EditText rxyl01EditText;// 日吸烟量
    EditText rxyl02EditText;// 下次希望日吸烟量
    EditText ryjl01EditText;// 日饮酒量
    EditText ryjl02EditText;// 下次希望日饮酒量
    EditText ydl01EditText;// 每周运动几次
    EditText ydl02EditText;// 每次运动时间
    EditText ydl03EditText;// 下次希望每周运动几次
    EditText ydl04EditText;// 下次希望每次运动时间
	
	EditText zs01EditText	= null;
	EditText zs02EditText	= null;
    
    SpinnerUtil xltzSpinnerUtil;// 心理调整
    SpinnerUtil zyxwSpinnerUtil;// 遵医行为

    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    public SfglTnbPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglTnbPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_02_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xy01EditText = (EditText) findViewById(R.id.xy01EditText);
        xy02EditText = (EditText) findViewById(R.id.xy02EditText);
        tz01EditText = (EditText) findViewById(R.id.tz01EditText);
        tz02EditText = (EditText) findViewById(R.id.tz02EditText);
        sgEditText = (EditText) findViewById(R.id.sgEditText);
        tzzs01EditText = (EditText) findViewById(R.id.tzzs01EditText);
        tzzs02EditText = (EditText) findViewById(R.id.tzzs02EditText);
        
        zbdmbdSpinnerUtil = (SpinnerUtil) findViewById(R.id.zbdmbdSpinnerUtil);
        
        yw01EditText = (EditText) findViewById(R.id.yw01EditText);
        yw02EditText = (EditText) findViewById(R.id.yw02EditText);
        
        xlEditText = (EditText) findViewById(R.id.xlEditText);
        qtEditText = (EditText) findViewById(R.id.qtEditText);
        tjsjButton = (Button) findViewById(R.id.tjsjButton);
        rxyl01EditText = (EditText) findViewById(R.id.rxyl01EditText);
        rxyl02EditText = (EditText) findViewById(R.id.rxyl02EditText);
        ryjl01EditText = (EditText) findViewById(R.id.ryjl01EditText);
        ryjl02EditText = (EditText) findViewById(R.id.ryjl02EditText);
        ydl01EditText = (EditText) findViewById(R.id.ydl01EditText);
        ydl02EditText = (EditText) findViewById(R.id.ydl02EditText);
        ydl03EditText = (EditText) findViewById(R.id.ydl03EditText);
        ydl04EditText = (EditText) findViewById(R.id.ydl04EditText);
		zs01EditText	= (EditText) findViewById(R.id.zs01EditText	);
		zs02EditText	= (EditText) findViewById(R.id.zs02EditText	);
        xltzSpinnerUtil = (SpinnerUtil) findViewById(R.id.xltzSpinnerUtil);
        zyxwSpinnerUtil = (SpinnerUtil) findViewById(R.id.zyxwSpinnerUtil);

        tjsjButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
                if (mJktj_kstj == null)
                    return;
                setTjsj(mJktj_kstj);// 设置体检数据到界面上来
            }
        });

        sgEditText.addTextChangedListener(new BmiAndMbtzWatcher(true));
        tz01EditText.addTextChangedListener(new BmiAndMbtzWatcher(false));
        tz02EditText.addTextChangedListener(new MbBmiWatcher());
        tzzs01EditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        tzzs02EditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentName().equals("")) {
            return;
        }

         //随访高血压中保存的体检数据
         xy01EditText.setText(mSfgljl.getsBP()+""); // 收缩压
         xy02EditText.setText(mSfgljl.getdBP()+"");//舒张压
         tz01EditText.setText(mSfgljl.getbCTZ());//本次体重
         tz02EditText.setText(mSfgljl.getxCTZ());//下次希望体重
         tzzs01EditText.setText(mSfgljl.gettZZS());//本次bmi
         xlEditText.setText(mSfgljl.getbCXL() + "");//本次心率
        yw01EditText.setText(mSfgljl.getWaistNow() + "");// 本次腰围
        yw02EditText.setText(mSfgljl.getWaistTarget() + "");// 下次腰围
         qtEditText.setText(mSfgljl.getqTTZ());
         zbdmbdSpinnerUtil.setCheckedByBeanCD(new BeanCD(mSfgljl.getdMBDCD(), ""));

		 sgEditText.setText(mSfgljl.getbCSG());
		
         rxyl01EditText.setText(mSfgljl.getbCXYL() + "");
         rxyl02EditText.setText(mSfgljl.getxCXY() + "");
         ryjl01EditText.setText(mSfgljl.getbCYJ() + "");
         ryjl02EditText.setText(mSfgljl.getxCYJ() + "");
         ydl01EditText.setText(mSfgljl.getyDZC() + "");
         ydl02EditText.setText(mSfgljl.getyDCF() + "");
         ydl03EditText.setText(mSfgljl.getxCYDZC() + "");
         ydl04EditText.setText(mSfgljl.getxCYDCD() + "");
         
         zs01EditText.setText(mSfgljl.getbCZSL() + "");
         zs02EditText.setText(mSfgljl.getxCZSL() + "");
         
         xltzSpinnerUtil.setCheckedByBeanCD(new BeanCD(mSfgljl.getxLTZCD(), ""));
         zyxwSpinnerUtil.setCheckedByBeanCD(new BeanCD(mSfgljl.getzYXWCD(), ""));
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        if (mSfgljl == null) {
            Log.e(TAG, "mSfgljl is null");
            return false;
        }

        String sbp = xy01EditText.getText().toString().trim();// 收缩压
        if (sbp.equals("")) {
            mToast.setText("收缩压不能为空，请获取数据！");
            mToast.show();
            return false;
        }
        mSfgljl.setsBP(Integer.parseInt(sbp));

        String dbp = xy02EditText.getText().toString().trim();// 舒张压
        if (dbp.equals("")) {
            mToast.setText("舒张压不能为空，请获取数据！");
            mToast.show();
            return false;
        }
        mSfgljl.setdBP(Integer.parseInt(dbp));
        mSfgljl.setdMBDCD(zbdmbdSpinnerUtil.getSelectedValueInt());

        String bctz = tz01EditText.getText().toString().trim();// 本次体重
        if (bctz.equals("")) {
            mToast.setText("本次体重不能为空，请获取数据！");
            mToast.show();
            return false;
        } else {
            if (StringUtil.isDecimal(bctz)) {
                mSfgljl.setbCTZ(bctz);
            } else {
                mToast.setText("本次体重输入数据不合法！");
                mToast.show();
                return false;
            }
        }

        String qwtz = tz02EditText.getText().toString().trim();// 期望体重
        if (!qwtz.equals("")) {
            if (StringUtil.isDecimal(qwtz)) {
                mSfgljl.setxCTZ(qwtz);// 设置下次期望体重，即期望体重
            } else {
                mToast.setText("下次体重输入数据不合法！");
                mToast.show();
                return false;
            }
        }

        String tzzs = tzzs01EditText.getText().toString().trim();// 体质指数
        if (!tzzs.equals("")) {
            if (StringUtil.isDecimal(tzzs)) {
                mSfgljl.settZZS(tzzs);
            } else {
                mToast.setText("体质指数输入数据不合法！");
                mToast.show();
                return false;
            }

        }
        String xctzzs = tzzs02EditText.getText().toString().trim();// 期望的下次体质指数
                                                                   // 不用上传
        if (!xctzzs.equals("")) {
            if (StringUtil.isDecimal(xctzzs)) {
                mSfgljl.setxCZS(xctzzs);
            } else {
                mToast.setText("体质指数输入数据不合法！");
                mToast.show();
                return false;
            }
        }

        String bcsg = sgEditText.getText().toString().trim();// 本次身高
        if (bcsg.equals("")) {
            mToast.setText("本次身高不能为空，请获取数据！");
            mToast.show();
            return false;
        } else {
            if (StringUtil.isDecimal(bcsg)) {
                mSfgljl.setbCSG(bcsg);
            } else {
                mToast.setText("本次身高数据输入不合法！");
                mToast.show();
                return false;
            }
        }

        String bcxl = xlEditText.getText().toString().trim();// 本次心率
        if (bcxl.equals("")) {
            mToast.setText("心率不能为空！");
            mToast.show();
            Log.e(TAG, "心率不能为空！");
            return false;
        }
        mSfgljl.setbCXL(Integer.parseInt(bcxl));
        mSfgljl.setWaistNow(yw01EditText.getText().toString());// 本次腰围
        mSfgljl.setWaistTarget(yw02EditText.getText().toString());// 下次腰围

        String qttz = qtEditText.getText().toString().trim();
        if (!qttz.equals(""))
            mSfgljl.setqTTZ(qttz);


        String rxyl = rxyl01EditText.getText().toString().trim();// 本次日吸烟量
        if (!rxyl.equals(""))
            mSfgljl.setbCXYL(Integer.parseInt(rxyl));

        String xcrxyl = rxyl02EditText.getText().toString().trim();// 期望下次吸烟量
        if (!xcrxyl.equals(""))
            mSfgljl.setxCXY(Integer.parseInt(xcrxyl));

        String ryjl = ryjl01EditText.getText().toString().trim();// 本次饮酒量
        if (!ryjl.equals(""))
            mSfgljl.setbCYJ(Integer.parseInt(ryjl));

        String xcryjl = ryjl02EditText.getText().toString().trim();// 下次期望饮酒量
        if (!xcryjl.equals(""))
            mSfgljl.setxCYJ(Integer.parseInt(xcryjl));

        String mzydjc = ydl01EditText.getText().toString().trim();// 每周运动几次
        if (!mzydjc.equals(""))
            mSfgljl.setyDZC(Integer.parseInt(mzydjc));

        String mcydsj = ydl02EditText.getText().toString().trim();// 每次运动时间
        if (!mcydsj.equals(""))
            mSfgljl.setyDCF(Integer.parseInt(mcydsj));

        String xcmzydjc = ydl03EditText.getText().toString().trim();// 下次期望每周运动几次
        if (!xcmzydjc.equals(""))
            mSfgljl.setxCYDZC(Integer.parseInt(xcmzydjc));

        String xcmcydsj = ydl04EditText.getText().toString().trim();// 下次期望每次运动时间
        if (!xcmcydsj.equals(""))
            mSfgljl.setxCYDCD(Integer.parseInt(xcmcydsj));
        
        String bczs = zs01EditText.getText().toString().trim();// 本次主食
        if (!bczs.equals(""))
            mSfgljl.setbCZSL(Integer.parseInt(bczs));
        
        String xczs = zs02EditText.getText().toString().trim();// 下次主食
        if (!xczs.equals(""))
            mSfgljl.setxCZSL(Integer.parseInt(xczs));

        mSfgljl.setxLTZCD(xltzSpinnerUtil.getSelectedValueInt());// 心理调整
        mSfgljl.setzYXWCD(zyxwSpinnerUtil.getSelectedValueInt());// 遵医行为

        return true;
    }

    private void setTjsj(Jktj_kstj mJktj_kstj) {
        if (mJktj_kstj == null)
            return;
        xy01EditText.setText(mJktj_kstj.getsBP() + ""); // 收缩压
        xy02EditText.setText(mJktj_kstj.getdBP() + "");// 舒张压
        tz01EditText.setText(mJktj_kstj.getWeight());// 本次体重
        sgEditText.setText(mJktj_kstj.getHeight());
        tzzs01EditText.setText(mJktj_kstj.getbMI());// 本次bmi
        xlEditText.setText(mJktj_kstj.getxL() + "");// 本次心率
        yw01EditText.setText(mJktj_kstj.getWaist() + "");// 本次腰围
    }

    class BmiAndMbtzWatcher extends DecimalsTextWatcher{
        private boolean heigth_edit;//当前的输入框，是身高的还是体重的
        public BmiAndMbtzWatcher(boolean heigth_edit) {
            this.heigth_edit=heigth_edit;
        }
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            
            if(s.length()>2)
            {
            	int pos = s.length() -1 ;
            	
            	char c = s.charAt(pos-2);
            	
            	if(c=='.')
            	{
            		s.delete(pos, pos+1);
            	}
            }
            
            String heightStr=sgEditText.getText().toString();
            String weigthStr=tz01EditText.getText().toString();
            try {
                if(heightStr==null||heightStr.trim().equals("")||Float.parseFloat(heightStr.trim())<=0){
                    tzzs01EditText.setText("");
                    return;
                }
                float height=Float.parseFloat(heightStr.trim());
                if(heigth_edit){
                      tz02EditText.setText(height-105+"");
                }
                if(weigthStr==null||weigthStr.trim().equals("")||Float.parseFloat(weigthStr.trim())<=0){
                    tzzs01EditText.setText("");
                    return;
                }
                height=height/100;
                float weigth=Float.parseFloat(weigthStr.trim());
                //取小数点几位
                double bmi = DecimalsTextWatcher.getInstance().parsePercision(
                        weigth / height / height);
                tzzs01EditText.setText(bmi+"");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    
    class MbBmiWatcher extends DecimalsTextWatcher{
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            
            if(s.length()>2)
            {
            	int pos = s.length() -1 ;
            	
            	char c = s.charAt(pos-2);
            	
            	if(c=='.')
            	{
            		s.delete(pos, pos+1);
            	}
            }  
            
            String heightStr=sgEditText.getText().toString();
            String weigthStr=s.toString();
            try {
                if(heightStr==null||heightStr.trim().equals("")||Float.parseFloat(heightStr.trim())<=0){
                    tzzs02EditText.setText("");
                    return;
                }
                float height=Float.parseFloat(heightStr.trim());
                if(weigthStr==null||weigthStr.trim().equals("")||Float.parseFloat(weigthStr.trim())<=0){
                    tzzs02EditText.setText("");
                    return;
                }
                height=height/100;
                float weigth=Float.parseFloat(weigthStr.trim());
                //取小数点几位
                double bmi = DecimalsTextWatcher.getInstance().parsePercision(
                        weigth / height / height);
                tzzs02EditText.setText(bmi+"");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
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
