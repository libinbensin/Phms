package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

public class SfglGxyPage02 extends MyPage  {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglGxyPage02";

    EditText xy01EditText;
    EditText xy02EditText;

    EditText tz01EditText;
    EditText tz02EditText;
    EditText sgEditText;
    EditText tzzs01EditText;
    EditText tzzs02EditText;

    EditText xlEditText;// 心率
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

    EditText syqk01EditText;// 摄盐情况
    EditText syqk02EditText;// 下次希望摄盐情况

    SpinnerUtil syqk01SpinnerUtil;
    SpinnerUtil syqk02SpinnerUtil;
    
    SpinnerUtil xltzSpinnerUtil;// 心理调整
    SpinnerUtil zyxwSpinnerUtil;// 遵医行为

    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    public SfglGxyPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglGxyPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_gxy_02_layout, this);

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
        syqk01EditText = (EditText) findViewById(R.id.syqk01EditText);
        syqk02EditText = (EditText) findViewById(R.id.syqk02EditText);
        syqk01SpinnerUtil = (SpinnerUtil) findViewById(R.id.syqk01SpinnerUtil);
        syqk02SpinnerUtil = (SpinnerUtil) findViewById(R.id.syqk02SpinnerUtil);
        xltzSpinnerUtil = (SpinnerUtil) findViewById(R.id.xltzSpinnerUtil);
        zyxwSpinnerUtil = (SpinnerUtil) findViewById(R.id.zyxwSpinnerUtil);
        
        syqk01EditText.addTextChangedListener(syqk01TextWatcher);
        syqk02EditText.addTextChangedListener(syqk02TextWatcher);

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
	
	private TextWatcher syqk01TextWatcher = new TextWatcher() {		
        int mCurVal = 0;
        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        }
       
        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        	String mStr = s.toString();
        	if( !mStr.isEmpty() )
        	{
        		mCurVal = Integer.parseInt(mStr);
        	}
        	else
        	{
        		mCurVal = 0;
        	}
        }
       
        @Override
        public void afterTextChanged(Editable s) {
        	
        	if( mCurVal >=0 && 
        	    mCurVal <6  )
        	{
        		syqk01SpinnerUtil.setSelectedPositionByValue(1);
        	}
        	else if( mCurVal >=6 && 
            	    mCurVal <=12  )
        	{
        		syqk01SpinnerUtil.setSelectedPositionByValue(2);
        	}
        	else
        	{        	
        		syqk01SpinnerUtil.setSelectedPositionByValue(3);
        	}
        }
    };
    
    private TextWatcher syqk02TextWatcher = new TextWatcher() {		
        int mCurVal = 0;
        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        }
       
        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        	String mStr = s.toString();
        	if( !mStr.isEmpty() )
        	{
        		mCurVal = Integer.parseInt(mStr);
        	}
        	else
        	{
        		mCurVal = 0;
        	}
        }
       
        @Override
        public void afterTextChanged(Editable s) {
        	
        	if( mCurVal >=0 && 
        	    mCurVal <6  )
        	{
        		syqk02SpinnerUtil.setSelectedPositionByValue(1);
        	}
        	else if( mCurVal >=6 && 
            	    mCurVal <=12  )
        	{
        		syqk02SpinnerUtil.setSelectedPositionByValue(2);
        	}
        	else
        	{        	
        		syqk02SpinnerUtil.setSelectedPositionByValue(3);
        	}
        }
    };

    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_gxy mSfgljl_gxy = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentName().equals("")) {
            return;
        }

         //随访高血压中保存的体检数据
         xy01EditText.setText(mSfgljl_gxy.getsBP()+""); // 收缩压
         xy02EditText.setText(mSfgljl_gxy.getdBP()+"");//舒张压
         tz01EditText.setText(mSfgljl_gxy.getbCTZ());//本次体重
         tz02EditText.setText(mSfgljl_gxy.getxCTZ());//下次希望体重
         tzzs01EditText.setText(mSfgljl_gxy.gettZZS());//本次bmi
         xlEditText.setText(mSfgljl_gxy.getbCXL() + "");//本次心率
         sgEditText.setText(mSfgljl_gxy.getBCSG());
         qtEditText.setText(mSfgljl_gxy.getqTTZ());

         Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class
                 .getName());
         if (mSfgljl == null) {
             return;
         }
         rxyl01EditText.setText(mSfgljl.getbCXYL() + "");
         rxyl02EditText.setText(mSfgljl.getxCXY() + "");
         ryjl01EditText.setText(mSfgljl.getbCYJ() + "");
         ryjl02EditText.setText(mSfgljl.getxCYJ() + "");
         ydl01EditText.setText(mSfgljl.getyDZC() + "");
         ydl02EditText.setText(mSfgljl.getyDCF() + "");
         ydl03EditText.setText(mSfgljl.getxCYDZC() + "");
         ydl04EditText.setText(mSfgljl.getxCYDCD() + "");

         syqk01EditText.setText(mSfgljl.getbCSYL() + "");
         syqk02EditText.setText(mSfgljl.getxCSYL() + "");
         
         xltzSpinnerUtil.setSelection(mSfgljl.getxLTZCD()-1);
         zyxwSpinnerUtil.setSelection(mSfgljl.getzYXWCD()-1);
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
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
        
        
        String xczs = tzzs02EditText.getText().toString().trim();// 下次指数
        if (!xczs.equals("")) {
            if (StringUtil.isDecimal(xczs)) {
                mSfgljl.setXCZS(xczs);
            } else {
                mToast.setText("下次指数输入数据不合法！");
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
                mSfgljl.setBCSG(bcsg);
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

        String bcsyl = syqk01EditText.getText().toString().trim();// 本次摄盐
        if (!bcsyl.equals(""))
            mSfgljl.setbCSYL(Integer.parseInt(bcsyl));
        
        String xcsyl = syqk02EditText.getText().toString().trim();// 下次希望摄盐
        if (!xcsyl.equals(""))
            mSfgljl.setxCSYL(Integer.parseInt(xcsyl));
        

        mSfgljl.setxLTZCD(xltzSpinnerUtil.getSelectedItemPosition() + 1);// 心理调整
        mSfgljl.setzYXWCD(zyxwSpinnerUtil.getSelectedItemPosition() + 1);// 遵医行为

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
