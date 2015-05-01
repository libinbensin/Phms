package com.cking.phss.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * 选择题
 * com.cking.phss.widget.ChoiceQuestionWidget
 * @author Administrator <br/>
 * create at 2012-9-21 下午11:37:30
 */
public class ChoiceQuestionWidget extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "ChoiceQuestionWidget";

    /**
     * 侦听提交试题
     */
    public static interface OnSubmitListener {
        /**
         * 提交事件
         */
        public void onSubmit(char answer);
    }
    
    private OnSubmitListener mOnSubmitListener = null;

    /**
     * 设置
     *
     * @param listener.
     */
    public void setOnSubmitListener(OnSubmitListener listener) {
        mOnSubmitListener = listener;
    }

    private TextView mTipsText = null;
    private TextView mTitleText = null;
    private TextView mQuestionText = null;
    private RadioGroup mChoiceRadios = null;
    private Button mPreviousBtn = null;
    private Button mNextBtn = null;
    
	public ChoiceQuestionWidget(Context context) {

		super(context);

        init(context);

	}

	public ChoiceQuestionWidget(Context context, AttributeSet attrs) {

		super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.choice_question_item, this);
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Widget);

        int indexFrom1 = a.getInt(R.styleable.Widget_indexFrom1, 0);
        int maxQuestions = a.getInt(R.styleable.Widget_maxQuestions, 0);
        mPreviousBtn = (Button) findViewById(R.id.previous_question_btn);
        mNextBtn = (Button) findViewById(R.id.next_question_btn);
        if (indexFrom1 <= 1) {
            mPreviousBtn.setVisibility(View.GONE);
            mNextBtn.setVisibility(View.VISIBLE);
        }
        if (indexFrom1 >= maxQuestions) {
            mPreviousBtn.setVisibility(View.GONE);
            mNextBtn.setVisibility(View.VISIBLE);
        }

        mTipsText = (TextView) findViewById(R.id.tips_text);
        int tipsResId = a.getResourceId(R.styleable.Widget_tipsText, 0); 
        if (tipsResId == 0) {
            String tipsStr = a.getString(R.styleable.Widget_tipsText); 
            mTipsText.setText(tipsStr);
        } else {
            mTipsText.setText(tipsResId);
        }
        
        mTitleText = (TextView) findViewById(R.id.title_text);
        int titleResId = a.getResourceId(R.styleable.Widget_titleText, 0); 
        if (titleResId == 0) {
            String titleStr = a.getString(R.styleable.Widget_titleText); 
            mTitleText.setText(titleStr + "（" + indexFrom1 + "/" + maxQuestions + "）");
        } else {
            mTitleText.setText(titleResId);
            mTitleText.setText(mTitleText.getText() + "（" + indexFrom1 + "/" + maxQuestions + "）");
        }

        mQuestionText = (TextView) findViewById(R.id.title_text);
        int questionResId = a.getResourceId(R.styleable.Widget_questionText, 0); 
        if (titleResId == 0) {
            String titleStr = a.getString(R.styleable.Widget_questionText); 
            mQuestionText.setText("（" + indexFrom1 + "）" + titleStr);
        } else {
            mQuestionText.setText(titleResId);
            mQuestionText.setText("（" + indexFrom1 + "）" + mQuestionText.getText());
        }
        
        RadioButton rA = (RadioButton) findViewById(R.id.radio_choice_a);
        int choiceAResId = a.getResourceId(R.styleable.Widget_choiceAText, 0); 
        if (choiceAResId == 0) {
            String choiceAStr = a.getString(R.styleable.Widget_choiceAText); 
            rA.setText(choiceAStr);
        } else {
            rA.setText(choiceAResId);
        }
        RadioButton rB = (RadioButton) findViewById(R.id.radio_choice_b);
        int choiceBResId = a.getResourceId(R.styleable.Widget_choiceBText, 0); 
        if (choiceBResId == 0) {
            String choiceBStr = a.getString(R.styleable.Widget_choiceBText); 
            rB.setText(choiceBStr);
        } else {
            rB.setText(choiceBResId);
        }
        RadioButton rC = (RadioButton) findViewById(R.id.radio_choice_c);
        int choiceCResId = a.getResourceId(R.styleable.Widget_choiceCText, 0); 
        if (choiceCResId == 0) {
            String choiceCStr = a.getString(R.styleable.Widget_choiceCText); 
            rC.setText(choiceCStr);
        } else {
            rC.setText(choiceCResId);
        }
        RadioButton rD = (RadioButton) findViewById(R.id.radio_choice_d);
        int choiceDResId = a.getResourceId(R.styleable.Widget_choiceDText, 0); 
        if (choiceDResId == 0) {
            String choiceDStr = a.getString(R.styleable.Widget_choiceDText); 
            rD.setText(choiceDStr);
        } else {
            rD.setText(choiceDResId);
        }
        
        a.recycle();
        init(context);
	}

    /**
     * @param context
     */
    private void init(Context context) {
        mChoiceRadios = (RadioGroup) findViewById(R.id.choice_radios);
        mChoiceRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                char answer = 0;
                switch (checkedId) {
                    case R.id.radio_choice_a:
                        answer = 'A';
                        break;

                    case R.id.radio_choice_b:
                        answer = 'B';
                        break;
                        
                    case R.id.radio_choice_c:
                        answer = 'C';
                        break;

                    case R.id.radio_choice_d:
                        answer = 'D';
                        break;
                }
                
                if (mOnSubmitListener != null) {
                    mOnSubmitListener.onSubmit(answer);
                }
            }
        });
    }
}
