package com.cking.phss.util;



import java.math.RoundingMode;
import java.text.DecimalFormat;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
/**
 * 小数的textWatcher
 * @author taowencong
 */
public class DecimalsTextWatcher implements TextWatcher{
    
    private static DecimalsTextWatcher instance=new DecimalsTextWatcher();
    private InputFilter[] inputFilters=new InputFilter[]{DigitsKeyListener.getInstance(false, true)};
    private int fractionDigits = 2; // 默认两位
    private static  DecimalFormat formater ;//用于小数取小数点后两位
    //使用默认的方式，不需要精度
    public DecimalsTextWatcher() {
        this.fractionDigits = 2;
    }

    // 带精度
    public DecimalsTextWatcher(int fractionDigits) {
        this.fractionDigits = fractionDigits;
    }
    public static DecimalsTextWatcher getInstance(){
        return instance;
    }
    
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        
    }

    @Override
    public void afterTextChanged(Editable s) {
        s.setFilters(inputFilters);
        
        
        if (s.length() > fractionDigits)
        {
        	int pos = s.length() -1 ;
        	
            char c = s.charAt(pos - fractionDigits);
        	
        	if(c=='.')
        	{
        		s.delete(pos, pos+1);
        	}
        }        
    }

    public double parsePercision(double value) {
        if(formater==null){
            formater = new DecimalFormat();//用于小数取小数点后两位
            formater.setMaximumFractionDigits(fractionDigits);
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.HALF_DOWN);
        }
        return Double.parseDouble(formater.format(value));
    }
}
