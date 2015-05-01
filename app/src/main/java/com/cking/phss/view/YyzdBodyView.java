/* Cking Inc. (C) 2012. All rights reserved.
 *
 * YyzdBodyView.java
 * classes : com.cking.phss.view.YyzdBodyView
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午7:57:21
 */
package com.cking.phss.view;

import java.util.Arrays;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.Yyzd;
import com.cking.phss.file.FileHelper;
import com.cking.phss.page.YyzdDetailPage;
import com.cking.phss.util.Constant;
import com.cking.phss.util.TispToastFactory;
import com.cking.pinyin.SearchAdapter;

/**
 * com.cking.phss.view.YyzdBodyView
 * 
 * @author zhaoyupeng <br/>
 *         create at 2012-9-24 上午7:57:21
 */
public class YyzdBodyView extends LinearLayout {

	private static final boolean D = true;

	/**
	 * 全局控件
	 */
	private RadioGroup mTabRadios = null;

	private AutoCompleteTextView mYpmcEdit = null;

	private Toast mToast = null;

	private Button search_imgView;
	
	/**
	 * 选择的TAB页编号
	 */
	private static int sTabRadioId = 0;

	/**
	 * 药品名称列表
	 */
	private static String[] mYpmcs = Yyzd.YPMCS;
	/**
	 * 当前药品名称
	 */
	private static String mCurrentYpmc = "";

	/**
	 * 内容子页
	 */
	private ViewGroup mDetailPage = null;

	/**
	 * @param context
	 */
	public YyzdBodyView(Context context) {
		super(context);

		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public YyzdBodyView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}

	private void init(final Context context) {
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_yyzd_body_layout, this);

		mTabRadios = (RadioGroup) findViewById(R.id.tab_radio);
		mDetailPage = (ViewGroup) findViewById(R.id.data_yyzd_detail);
		search_imgView=(Button)findViewById(R.id.yyzd_search_img);
		mDetailPage.setFocusable(true);

		// 获取用药指导文件夹列表
		mYpmcs = FileHelper.getInstance().getFolderList(Constant.RES_YYZD_PATH);
		// List<String> list = Arrays.asList(mYpmcs);
		// Collections.sort(list, new Comparator<String>() {
		// public int compare(String arg0, String arg1) {
		// PinYin4j pinyin = new PinYin4j();
		// Set<String> hzArg0 = pinyin.getPinyin(arg0);
		// Set<String> hzArg1 = pinyin.getPinyin(arg1);
		//
		// return hzArg0.toString().compareTo(hzArg1.toString());
		// }
		// });
		// mYpmcs = list.toArray(new String[list.size()]);
		// if (mYpmcs == null) {
		if (mYpmcs == null || mYpmcs.length <= 0) {
			mYpmcs = Yyzd.YPMCS;
		}
		mCurrentYpmc = mYpmcs[0];
		Log.i(TAG, Arrays.toString(mYpmcs));
		Log.i(TAG, Arrays.toString(mYpmcs));

		mYpmcEdit = (AutoCompleteTextView) findViewById(R.id.yyzd_ypmc_edit);
        
//		String myIME="com.android.inputmethod.pinyin/.PinyinIME";
//        StringBuilder builder = new StringBuilder(Settings.Secure.getString(context.getContentResolver(),
//                Settings.Secure.ENABLED_INPUT_METHODS));
//        if (builder.length() > 0) builder.append(':');
//        builder.append(myIME);
//        Settings.Secure.putString(context.getContentResolver(),
//                Settings.Secure.ENABLED_INPUT_METHODS, builder.toString());
//        
//        if (myIME != null && myIME.length() > 0) {
//            Settings.Secure.putString(context.getContentResolver(),
//                    Settings.Secure.DEFAULT_INPUT_METHOD, myIME);
//        }
		
		mYpmcEdit.setText("");
		SearchAdapter<String> adapter = new SearchAdapter<String>(context,
				android.R.layout.simple_dropdown_item_1line, mYpmcs,
				SearchAdapter.ALL);// 配置Adaptor
		mYpmcEdit.setAdapter(adapter);
//		mYpmcEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
//
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//
//				if (hasFocus) {
//					mYpmcEdit.setText("");
//
//				}
//			}
//		});

		mYpmcEdit.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentYpmc = (String) parent.getAdapter().getItem(position);
				if (mTabRadios.getCheckedRadioButtonId() == R.id.radio_ylx) {
					mDetailPage.removeAllViews();
					mDetailPage.addView(new YyzdDetailPage(context,
							mCurrentYpmc, Yyzd.YLX01));
				} else {
					mTabRadios.check(R.id.radio_ylx);
				}
			}
		});

		mTabRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				YyzdBodyView.sTabRadioId = checkedId;

				switch (checkedId) {
				case R.id.radio_ylx:
					mDetailPage.removeAllViews();
					mDetailPage.addView(new YyzdDetailPage(context,
							mCurrentYpmc, Yyzd.YLX01));
					break;
				case R.id.radio_tnb:
					mDetailPage.removeAllViews();
					mDetailPage.addView(new YyzdDetailPage(context,
							mCurrentYpmc, Yyzd.TNB02));
					break;
				case R.id.radio_jsb:
					mDetailPage.removeAllViews();
					mDetailPage.addView(new YyzdDetailPage(context,
							mCurrentYpmc, Yyzd.JSB03));
					break;
				case R.id.radio_cjsf:
					mDetailPage.removeAllViews();
					mDetailPage.addView(new YyzdDetailPage(context,
							mCurrentYpmc, Yyzd.CJSF04));
					break;
				case R.id.radio_lnsf:
					mDetailPage.removeAllViews();
					mDetailPage.addView(new YyzdDetailPage(context,
							mCurrentYpmc, Yyzd.LNSF05));
					break;
				case R.id.radio_etsf:
					mDetailPage.removeAllViews();
					mDetailPage.addView(new YyzdDetailPage(context,
							mCurrentYpmc, Yyzd.ETSF06));
					break;
				}
			}
		});
		mTabRadios.check(R.id.radio_ylx);
		
		search_imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mYpmcEdit.onCommitCompletion(new CompletionInfo(id, index, text))
                mYpmcEdit.showDropDown();
            }
        });
	}

	private static final String TAG = "YyzdBodyView";
}
