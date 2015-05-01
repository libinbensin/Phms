package com.cking.phss.page;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.Xlcs;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.TestPageItem;

public class JktjXlpgTestPage extends LinearLayout implements OnClickListener {
	private Context mContext = null;
	private Toast mToast = null;

	TestPageItem xlpgTestPageItem;
	private JktjXlpgPage mParent;
	private TextView mCsmcText = null;
	private TextView mTitleText = null;
	private TextView mQuestionText = null;
	private RadioGroup mRadioBarAnswer = null;
	private RadioButton[] mRadioButtons = null;
	private Button mLastButton = null;
	private Button mNextButton = null;
	private Button mOver = null;
//    private Button mCancel = null;
	int mCsid = 0;

	private class Topic {
		public Topic(String title, String question, String[] selection,
				int[] rate, int answerIndex, String csmc) {
			this.title = title;
			this.question = question;
			this.answerIndex = answerIndex;
			this.selection = selection;
			this.rate = rate;
			this.csmc = csmc;
		}

		String csmc;// 测试名称
		String title;// 标题，显示是第几个题
		String question;// 问题
		String[] selection;// 名称
		int[] rate;// 对应答案的分数
		int answerIndex;// 答案序号
	}

	private List<Topic> tList = null;
	private int mTopicIndex = 0;

	/**
	 * @param context
	 */
	public JktjXlpgTestPage(Context context, JktjXlpgPage parent, int csid) {
		super(context);
		mParent = parent;
		init(context,csid);
	}

	/**
	 * @param context
	 */
	private void init(Context context,int csid) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_xlpg_2_layout, this);

		xlpgTestPageItem = (TestPageItem) findViewById(R.id.xlpgTestPageItem);
		mCsmcText = (TextView) findViewById(R.id.csmc_text);
		mTitleText = (TextView) findViewById(R.id.title_text);
		mQuestionText = (TextView) findViewById(R.id.question_text);
		mRadioBarAnswer = (RadioGroup)findViewById(R.id.radio_bar_answer);
		mLastButton = (Button) findViewById(R.id.xlce_last_button);
		mNextButton = (Button) findViewById(R.id.xlce_next_button);
		mOver = (Button) findViewById(R.id.xlce_over_button);
//		mCancel = (Button) findViewById(R.id.xlce_cancel_button);
		mRadioButtons = new RadioButton[7];
		mRadioButtons[0] = (RadioButton) findViewById(R.id.radio_answer_1);
		mRadioButtons[1] = (RadioButton) findViewById(R.id.radio_answer_2);
		mRadioButtons[2] = (RadioButton) findViewById(R.id.radio_answer_3);
		mRadioButtons[3] = (RadioButton) findViewById(R.id.radio_answer_4);
		mRadioButtons[4] = (RadioButton) findViewById(R.id.radio_answer_5);
		mRadioButtons[5] = (RadioButton) findViewById(R.id.radio_answer_6);
		mRadioButtons[6] = (RadioButton) findViewById(R.id.radio_answer_7);



		mOver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                if (mTopicIndex < tList.size() - 2) { // 如果没到最后则相当于取消
                    mParent.showXlcsPage01();
                    return;
                }
			    if(mCsid!=Xlcs.SCL90){
			        mParent.showXlcslasePage(getRank(), mCsid);
			    }else{
			        int[] r = new int[tList.size()];
			        int i = 0;
			        for (Topic t : tList) {
			            r[i++] = t.rate[t.answerIndex];
			        }
			        mParent.showSCL90XlcslasePage(r, mCsid);
			    }
			}
		});
		
//		mCancel.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // 重新加载心理测试首页
//                mParent.showXlcsPage01();
//            }
//        });

		mLastButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mTopicIndex--;
				updateButtonState(mTopicIndex);
				updateView(mTopicIndex);
			}
		});
		mNextButton.setOnClickListener(new OnClickListener() {

			@Override
			public  void onClick(View v) {
			    mNextButton.setEnabled(false);
				mTopicIndex++;
				updateButtonState(mTopicIndex);
				updateView(mTopicIndex);
				mNextButton.setEnabled(true);
			}
		});
		
		changeTestTopics(csid);

	}
	
	/**
	 * 切换测试题
	 * @param csid
	 */
	public void changeTestTopics(int csid) {
		mCsid = csid;
		tList = getTopicList(mCsid);//根据id值，获取某个测试的题目和答案信息的列表list
		mTopicIndex = 0;// 选择的题目
		showRadioButton(tList.get(0).selection.length);// 得到要显示的答案的个数
		mTopicIndex = 0;
		updateButtonState(mTopicIndex);
		xlpgTestPageItem.setPageCount(tList.size());
		updateView(mTopicIndex);
	}

	// 根据答案，计算得分，在计算等级
	private int getRank() {
		int[] r = new int[tList.size()];
		int i = 0;
		for (Topic t : tList) {
			r[i++] = t.rate[t.answerIndex];
		}
		int rank = 0;// 答案的得分，计算等级
		int rate = getRate(r);// 计算这个数组的分数总和
		switch (mCsid) {
		case Xlcs.YY:// 抑郁自评量表（SDS）的判断方式
			float lastRate1=(float)rate/80;//计算方式：分数总和 ，在除以80
			if (0 < lastRate1 && lastRate1 <= 0.5) {
				rank = 0;
			} else if (lastRate1 <= 0.6) {
				rank = 1;
			} else if (lastRate1 <= 0.7) {
				rank = 2;
			} else if (lastRate1 <= 1) {
				rank = 3;
			}
			break;
		case Xlcs.JL:// 焦虑自评量表（SAS）的判断方式 ，
		    int lastRate2=(int)(rate*1.25);
			if (25 < lastRate2 && lastRate2 <= 50) {
				rank = 0;
			} else if (lastRate2 <= 60) {
				rank = 1;
			} else if (lastRate2 <= 70) {
				rank = 2;
			}else if (lastRate2 <= 100) {
				rank = 3;
			}
			break;

			
		case Xlcs.PZ://匹兹堡睡眠质量指数（PSQI）
			if (0 < rate && rate <= 7) {
				rank = 0;
			} else if (rate <= 10) {
				rank = 1;
			} else if (rate <= 21) {
				rank = 2;
			}
			break;

		case Xlcs.ZS:// 自杀态度问卷（SAQ）
			//根据数组r的各个分数  分四个维度计算
			float weidu1=(r[0]+r[6]+r[11]+r[16]+r[18]+r[21]+r[22]+r[25]+r[28])/9.0f;//F1—对自杀行为的态度：1、7、12、17、19、22、23、26、29，共9项
			float weidu2=(r[1]+r[2]+r[7]+r[8]+r[12]+r[13]+r[17]+r[19]+r[23]+r[24])/10.0f;//F2—对自杀者的态度：2、3、8、9、13、14、18、20、24、25，共10项；
			float weidu3=(r[3]+r[5]+r[9]+r[14]+r[27])/5.0f;//F3—对自杀者家属的态度：4、6、10、15、28，共5项；
			float weidu4=(r[4]+r[10]+r[15]+r[20]+r[26])/5.0f;//F4—对安乐死的态度：5、11、16、21、27，共5项。
			///将各维度的总分（各条目实际得分之和）除以该维度的条目数即为该维度的维度分（平均维度分）。
			//维度分的最后分值在1～5分。以2.5和3.5分为两个分界值，将对自杀的态度划分为3个部分
			//≤2.5分被认为对自杀持肯定、认可、理解和宽容的态度；2.5～3.5分为矛盾或中立态度；≥3.5分被认为对自杀持反对、否定、排斥和歧视态度。
			float lastValue=(weidu1+weidu2+weidu3+weidu4)/4;
			if(lastValue>1&&lastValue<2.5){
				rank=0;
			}else if (lastValue<3.5) {
				rank=1;
			}else {
				rank=2;
			} 
			break;

		case Xlcs.UCLA:// UCLA孤独量表（第2版）
			if (20 < rate && rate <= 39) {
				rank = 0;
			} else if (rate <= 50) {
				rank = 1;
			} else if (rate <= 80) {
				rank = 2;
			}
			break;

		case Xlcs.SS:// 舒适状况量表（GCQ）
			if (30 < rate && rate <= 60) {
				rank = 0;
			} else if (rate <= 90) {
				rank = 1;
			} else if (rate <= 120) {
				rank = 2;
			} 
			break;
		case Xlcs.SCL90:
		    
		    break;
		case Xlcs.QLSCA:// 舒适状况量表（GCQ）
		    int age=XlcsPage01.age;
		    int sex=XlcsPage01.sex;
		    if(sex==0||sex==9)
		        sex=1;//如果性别未知，则默认为男
		    if(age<7||age>18||(sex!=1&&sex!=2)){
		        break;
		    }
		    double m=Xlcs.QLSCA_STANDARD[sex-1][age-7][0];
		    double sd=Xlcs.QLSCA_STANDARD[sex-1][age-7][1];
		    double lastV=50+(rate-m)/sd*10;
		    if(lastV<30){
		        rank = 0;
		    }else if(lastV<60){
                rank=1;
            }else if (lastV<70) {
                rank=2;
            }else {
                rank=3;
            }
		    
		    //还原两个数据
		    XlcsPage01.age=-1;
            XlcsPage01.sex=-1;
            break;
		default:
			throw new InvalidParameterException("量表ID有误");
		}
		return rank;
	}

	// 得到分数
	private int getRate(int[] rates) {
		int rate = 0;
		for (int i : rates) {
			rate += i;
		}
		return rate;
	}

	private void showRadioButton(int length) {
		for (RadioButton radioButton : mRadioButtons) {
			radioButton.setVisibility(View.INVISIBLE);
			radioButton.setOnClickListener(this);
		}
		for (int i = 0; i < length; i++) {
			mRadioButtons[i].setVisibility(View.VISIBLE);
		}
	}

	// 加载题目和答案信息的list列表
	private List<Topic> getTopicList(int csid) {
		List<Topic> list = new ArrayList<Topic>();
		String[] contents = Xlcs.CSLRS[csid];
		for (String content : contents) {
			String[] arr = content.split("\\|");
			list.add(new Topic(arr[1], arr[2], arr[3].split(";"),
					stringToInt(arr[4].split(";")), 0, arr[0]));
		}
		return list;
	}

	private int[] stringToInt(String[] strArr) {
		int[] intArr = new int[strArr.length];
		for (int i = 0; i < intArr.length; i++) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}

	protected void updateButtonState(int topicIndex) {
		if (topicIndex == 0) {
			mLastButton.setVisibility(View.INVISIBLE);
			mNextButton.setVisibility(View.VISIBLE);
			mOver.setVisibility(View.VISIBLE);
		} else if (topicIndex == tList.size() - 1) {
			mLastButton.setVisibility(View.VISIBLE);
			mNextButton.setVisibility(View.INVISIBLE);
			mOver.setVisibility(View.VISIBLE);
		} else {
			mLastButton.setVisibility(View.VISIBLE);
			mNextButton.setVisibility(View.VISIBLE);
			mOver.setVisibility(View.VISIBLE);
		}
	}

	protected void updateView(int mTopicIndex) {
		if(  ( mTopicIndex >= tList.size() )   ||
			 ( mTopicIndex < 0) )
			return;
		
		Topic t = tList.get(mTopicIndex);
		mCsmcText.setText(t.csmc);
		if (t.title != null && t.title.length() > 0) {
			mTitleText.setText("问卷:" + t.title + "(" + (mTopicIndex + 1) + "/"
					+ tList.size() + ")");
		} else {
			mTitleText.setText("问卷 " + "(" + (mTopicIndex + 1) + "/"
					+ tList.size() + ")");
		}
		
        xlpgTestPageItem.setSelectIndex(mTopicIndex);
        
		mQuestionText.setText("(" + (mTopicIndex + 1) + ")" + t.question);
		int len = t.selection.length;
		for (int i = 0; i < len; i++) {
			mRadioButtons[i].setText(t.selection[i]);
			if(i == t.answerIndex){
				mRadioButtons[i].setChecked(true);
			}else{
				mRadioButtons[i].setChecked(false);
			}	
		}
	}

	@Override
	public void onClick(View v) {
		Topic t = tList.get(mTopicIndex);
		for (int i = 0; i < mRadioButtons.length; i++) {
			if (mRadioButtons[i].getId() == v.getId()) {
				t.answerIndex = i;
				mRadioButtons[i].setChecked(true);
			} else {
				mRadioButtons[i].setChecked(false);
			}
		}
	}
}
