package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.Tzps;
import com.cking.phss.sqlite.SqliteField.TzpsField;
import com.cking.phss.sqlite.SqliteOperater;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.RadioGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.TestPageItem;

public class JktjTzbsTestPage extends LinearLayout implements OnClickListener {
	private JktjTzbsPage mParent = null;
	private Toast mToast = null;
	private Context mContext = null;
	TextView mTitleText = null;
	TextView mQuestionText = null;
//	RelativeGroup mRadioBarAnswer = null;
    private RadioGroupUtil radioGroup;
	RadioButton[] mRadioButtons = null;
	Button mLastButton = null;
	Button mNextButton = null;
	Button mOver = null;
	TestPageItem tzbsTestPageItem;
	//private Button mCancel = null;
	private int sexCd=0;//代表人物的性别

    private int[] radioBtnIds = new int[] { R.id.radio_tzps_answer1, R.id.radio_tzps_answer2,
            R.id.radio_tzps_answer3, R.id.radio_tzps_answer4, R.id.radio_tzps_answer5};
	private List<Topic> tList = new ArrayList<Topic>();
	private int mTopicIndex = 0;

	/**
	 * @param context
	 */
	public JktjTzbsTestPage(Context context, JktjTzbsPage parent,int sexCd) {
		super(context);
		this.mParent = parent;
		this.sexCd=sexCd;
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjTzbsTestPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_tzbs_2_layout, this);

		tzbsTestPageItem = (TestPageItem) findViewById(R.id.xlpgTestPageItem);
		mTitleText = (TextView) findViewById(R.id.title_text);
		mQuestionText = (TextView) findViewById(R.id.question_text);
//		mRadioBarAnswer = (RelativeGroup) findViewById(R.id.radio_bar_answer);
		mLastButton = (Button) findViewById(R.id.tzps_last_button);
		mNextButton = (Button) findViewById(R.id.tzps_next_button);
		mOver = (Button) findViewById(R.id.tzps_over_button);
		//mCancel = (Button) findViewById(R.id.tzps_cancel_button);
		mRadioButtons = new RadioButton[5];
		mRadioButtons[0] = (RadioButton) findViewById(R.id.radio_tzps_answer1);
		mRadioButtons[1] = (RadioButton) findViewById(R.id.radio_tzps_answer2);
		mRadioButtons[2] = (RadioButton) findViewById(R.id.radio_tzps_answer3);
		mRadioButtons[3] = (RadioButton) findViewById(R.id.radio_tzps_answer4);
		mRadioButtons[4] = (RadioButton) findViewById(R.id.radio_tzps_answer5);
        radioGroup = new RadioGroupUtil(radioBtnIds, this, null);
        radioGroup.setCheckedById(R.id.radio_tzps_answer1);

		mOver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			    if (mTopicIndex < tList.size() - 2) { // 如果没到最后则相当于取消
			        mParent.showTzpsPage01();
			        return;
			    }
                if (!hasAnswerChecked()) {// 表示没有答案被选中
                    mToast.setText("请选择一个答案!");
                    mToast.show();
                    return;
                }
				// 将数据保存或者更新到数据库
				if (MyApplication.getInstance().getSession()
						.getCurrentResident() != null) {
					String resident_uuid = MyApplication.getInstance()
							.getSession().getCurrentResident()
							.getResidentUUID();
					String valuesString = intToString(getRate());
					Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
							"SELECT * FROM " + TzpsField.TB_NAME + " WHERE "
									+ TzpsField.RESIDENT_UUID + "=?",
							new String[] { resident_uuid });
					if (cursor.moveToNext()) {// 说明有数据存在，那么更新
						SqliteOperater.getDbInstance().updateBySql(
								"UPDATE " + TzpsField.TB_NAME + " SET "
										+ TzpsField.VALUE + "=? WHERE "
										+ TzpsField.RESIDENT_UUID + "=?",
								new String[] { valuesString, resident_uuid });
					} else {// 否则说明没有数据存在，新增插入
						SqliteOperater.getDbInstance().insertBySql(
								"INSERT INTO " + TzpsField.TB_NAME + "("
										+ TzpsField.RESIDENT_UUID + ","
										+ TzpsField.VALUE + ") VALUES(?,?)",
								new String[] { resident_uuid, valuesString });
					}
				}
				mParent.showTzpsTzbg(getRate());
			}
		});

//		mCancel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// 重新加载心理测试首页
//				mParent.showTzpsPage01();
//			}
//		});

		mLastButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 如果当前是第一个则隐藏上一个按钮
				if (mTopicIndex == 1) {
					mLastButton.setVisibility(View.INVISIBLE);
					mNextButton.setVisibility(View.VISIBLE);
					mOver.setVisibility(View.VISIBLE);
				} else {
					mLastButton.setVisibility(View.VISIBLE);
					mNextButton.setVisibility(View.VISIBLE);
					mOver.setVisibility(View.VISIBLE);
				}

				mTopicIndex--;
				updateView(mTopicIndex);
			}

		});
		mNextButton.setOnClickListener(new OnClickListener() {
			@Override
			public  void onClick(View v) {
				if (!hasAnswerChecked()) {// 表示没有答案被选中
					mToast.setText("请选择一个答案!");
					mToast.show();
					return;
				}
				 mNextButton.setEnabled(false);
				// 如果当前是第一个则隐藏上一个按钮
				if (mTopicIndex == tList.size() - 2) {
					mLastButton.setVisibility(View.VISIBLE);
					mNextButton.setVisibility(View.INVISIBLE);
					mOver.setVisibility(View.VISIBLE);
				} else {
					mLastButton.setVisibility(View.VISIBLE);
					mNextButton.setVisibility(View.VISIBLE);
					mOver.setVisibility(View.VISIBLE);
				}
				mTopicIndex++;
				updateView(mTopicIndex);
				 mNextButton.setEnabled(true);
			}
		});
//		mRadioBarAnswer
//				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RelativeGroup paramRelativeGroup, int paramInt) {
//                        for (int i = 0; i < mRadioButtons.length; i++) {
//                            if (mRadioButtons[i].getId() == paramInt) {
//                                if (mRadioButtons[i].isChecked()) {
//                                    Topic t = tList.get(mTopicIndex);
//                                    t.answerIndex = i + 1;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//				});

		initTopics();
	}

	protected void updateView(int mTopicIndex) {
		if(  ( mTopicIndex >= tList.size() )   ||
				 ( mTopicIndex < 0) )
				return;
		
		Topic t = tList.get(mTopicIndex);
		mTitleText.setText(t.title + "问卷：您感觉(" + (mTopicIndex + 1) + "/"
				+ tList.size() + ")");
		mQuestionText.setText(t.question);
		for (int i = 0; i < mRadioButtons.length; i++) {
			mRadioButtons[i].setText(t.selection[i]);
		}
		
		if(mTopicIndex>0)
		{
			t.answerIndex = tList.get(mTopicIndex-1).answerIndex;
		}

		// 更新顶部页提示项
        tzbsTestPageItem.setSelectIndex(mTopicIndex);

//        radioGroup.setCheckedById(R.id.radio_tzps_answer1);
        int len = t.selection.length;
        for (int i = 0; i < len; i++) {
            mRadioButtons[i].setText(t.selection[i]);
            if(i == t.answerIndex - 1){
                mRadioButtons[i].setChecked(true);
            }else{
                mRadioButtons[i].setChecked(false);
            }   
        }
//		if (t.answerIndex != 0) {// 如果这个题已经被做了，则加载上一次的答案
//			mRadioBarAnswer.check(mRadioButtons[t.answerIndex - 1].getId());
//		}else {
//		    mRadioBarAnswer.check(mRadioButtons[0].getId());//如果没有做，则默认选中第一个答案
//        }
	}

	// 加载题目和答案信息的list列表
    private List<Topic> getTopicList() {
        List<Topic> list = new ArrayList<Topic>();
        for (int i = 0; i < Tzps.TZBS_QUESTION.length; i++) {
            String question = Tzps.TZBS_QUESTION[i];// 问题
            list.add(new Topic(question, 0));
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

	// 将int[]数组转换为"1,2,3"类似的字符串
	private String intToString(int[] intArr) {
		String stringValue = "";
		for (int i = 0; i < intArr.length; i++) {
			if (i != intArr.length - 1) {
				stringValue += (intArr[i] + ",");
			} else {
				stringValue += intArr[i];
			}
		}
		return stringValue;
	}

	// 返回所有答案的分数的一维数组
	private int[] getRate() {
		int[] r = new int[tList.size()];
		for (int i = 0; i < tList.size(); i++) {
			Topic t = tList.get(i);
			r[i] = t.rate[t.answerIndex - 1];
		}
		return r;
	}

	private class Topic {
		String title;// 标题
		String question;// 问题
		String[] selection;// 答案
		int[] rate;// 对应答案的分数
		int answerIndex;// 答案序号

		public Topic(String question, int answerIndex) {
			this.title = "";
			this.question = question;
			this.selection = new String[] {
			        "没有","很少","有时","经常","总是"
			};
			this.answerIndex = answerIndex;
			this.rate = new int[] {1, 2, 3, 4, 5};
		}
	}

	public void initTopics() {
		mLastButton.setVisibility(View.INVISIBLE);
		mNextButton.setVisibility(View.VISIBLE);
		mOver.setVisibility(View.VISIBLE);

		mTopicIndex = 0;
		tList = getTopicList();
        showRadioButton(tList.get(0).selection.length);// 得到要显示的答案的个数
		tzbsTestPageItem.setPageCount(tList.size());
		updateView(mTopicIndex);
	}

	public boolean hasAnswerChecked() {
		for (int i = 0; i < mRadioButtons.length; i++) {
			if (mRadioButtons[i].isChecked()) {
				return true;
			} else {
				continue;
			}
		}
		return false;
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

    @Override
    public void onClick(View v) {
        Topic t = tList.get(mTopicIndex);
        for (int i = 0; i < mRadioButtons.length; i++) {
            if (mRadioButtons[i].getId() == v.getId()) {
                t.answerIndex = i + 1;
                mRadioButtons[i].setChecked(true);
            } else {
                mRadioButtons[i].setChecked(false);
            }
        }
    }
}
