/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SettingBubble.java
 * classes : com.cking.phss.widget.SettingBubble
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-10-3 下午08:27:50
 */
package com.cking.phss.widget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.Constant;
import com.cking.phss.util.ContextUtil;

/**
 * com.cking.phss.widget.SettingBubble
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2012-10-3 下午08:27:50
 */
public class SettingBubble extends LinearLayout {
	private static final String TAG = "SettingBubble";
	private Context mContext = null;

	private LinearLayout mSettings = null;
	private LinearLayout mHelp = null;
	private LinearLayout mAbout = null;

	/**
	 * 侦听单击设置项
	 */
	public static interface OnClickSettingsItemListener {
		/**
		 * 单击设置项事件
		 */
		public void onClickSettingsItem();
	}

	private OnClickSettingsItemListener mOnClickSettingsItemListener = null;

	/**
	 * 设置
	 * 
	 * @param listener
	 *            .
	 */
	public void setOnClickSettingsItemListener(
			OnClickSettingsItemListener listener) {
		mOnClickSettingsItemListener = listener;
	}

	/**
	 * 侦听单击帮助项
	 */
	public static interface OnClickHelpItemListener {
		/**
		 * 单击帮助项事件
		 */
		public void onClickHelpItem();
	}

	private OnClickHelpItemListener mOnClickHelpItemListener = null;

	/**
	 * 设置
	 * 
	 * @param listener
	 *            .
	 */
	public void setOnClickHelpItemListener(OnClickHelpItemListener listener) {
		mOnClickHelpItemListener = listener;
	}

	/**
	 * 侦听单击关于项
	 */
	public static interface OnClickAboutItemListener {
		/**
		 * 单击关于项事件
		 */
		public void onClickAboutItem();
	}

	private OnClickAboutItemListener mOnClickAboutItemListener = null;

	/**
	 * 设置
	 * 
	 * @param listener
	 *            .
	 */
	public void setOnClickAboutItemListener(OnClickAboutItemListener listener) {
		mOnClickAboutItemListener = listener;
	}

	/**
	 * @param context
	 */
	public SettingBubble(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SettingBubble(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		mContext = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.setting_bubble, this);

		mSettings = (LinearLayout) findViewById(R.id.setting_bar_settings);
		mHelp = (LinearLayout) findViewById(R.id.setting_bar_help);
		mAbout = (LinearLayout) findViewById(R.id.setting_bar_about);

		mSettings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent();
				// intent.setClass(mContext, SettingsActivity.class);
				// mContext.startActivity(intent);
				if (mOnClickSettingsItemListener != null) {
					mOnClickSettingsItemListener.onClickSettingsItem();
				} else {
//					onClickSettingsItem();
				}

				// 隐藏
				setVisibility(View.GONE);
			}
		});

		mHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent();
				// intent.setClass(mContext, HelpActivity.class);
				// mContext.startActivity(intent);
//				if (mOnClickHelpItemListener != null) {
//					mOnClickHelpItemListener.onClickHelpItem();
//				} else {
//					onClickHelpItem();
//				}
			    File file=new File(Constant.exterNalPath+"/phms/移动随访包使用说明书-电子版 2.doc");
			    if(!file.exists()){
			        Toast.makeText(mContext, "帮助文档不存在", Toast.LENGTH_LONG).show();
			        return;
			    }
			    Intent intent = new Intent();
		        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        // 设置intent的Action属性
		        intent.setAction(Intent.ACTION_VIEW);
		        // 获取文件file的MIME类型
		        String type ="application/msword";
//		        Log.i(TAG, file + "  two  file````````````````");
//		        Log.i(TAG, type + "  two  type````````````````");
		        // 设置intent的data和Type属性。
		        intent.setDataAndType(/* uri */Uri.fromFile(file), type);
		        // 跳转
		        mContext.startActivity(intent); // 这里最好try一下，有可能会报错。

				// 隐藏
				setVisibility(View.GONE);
			}
		});

		mAbout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent();
				// intent.setClass(mContext, AboutActivity.class);
				// mContext.startActivity(intent);
				if (mOnClickAboutItemListener != null) {
					mOnClickAboutItemListener.onClickAboutItem();
				} else {
					onClickAboutItem();
				}

				// 隐藏
				setVisibility(View.GONE);
			}
		});
	}

	/*public void onClickSettingsItem() {
		String text = "开发人员没有向您开放任何设置:(";
		showTextAlertDialog(text, "用户设置");
	}*/

	/*public void onClickHelpItem() {
		String text = "开发人员没有提供任何帮助:(";
		showTextAlertDialog(text, "帮助");
	}*/
	
	public void onClickAboutItem() {
		/*String versionName = "";
		try {
			PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
			versionName = pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String text = "应用程序名:" + mContext.getString(R.string.app_name)
				+ versionName + "\r\n" + "版权所有：上海先德医疗设备有限公司\r\n"
				+ "技术支持：400-772-882";
		showTextAlertDialog(text, "关于");*/
	    /*关于这一部分换成图片*/
	    LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.common_alertdialog, null);

        ImageView about_img = (ImageView) view.findViewById(R.id.about_img);
        
        if (ContextUtil.sdCardCanRead()) {
            String externalPath = Environment.getExternalStorageDirectory() + "/phms/image/";
            String aboutPath = externalPath + "about.png";// 
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(aboutPath)));
                if(bitmap!=null){
                    about_img.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }else {
//                    about_img.setImageResource(R.drawable.about1);
                }
            } catch (FileNotFoundException e) {
                Log.e(TAG, e.toString());
            }
        }

        
        /*final Dialog dialog=new Dialog(mContext, R.style.Translucent_NoTitle);
        dialog.setContentView(view);
        dialog.show();
        Button about_cancelButton=(Button)view.findViewById(R.id.about_cancel);
        about_cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });*/
        
       final  PopupWindow mPopupWindow=new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        Button cancelBtn=(Button)view.findViewById(R.id.about_cancel);
        cancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPopupWindow!=null||mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }
            }
        });
        view.invalidate();
        
        mPopupWindow.showAtLocation((RelativeLayout)this.getParent(), Gravity.CENTER, 0, 0);
       /* final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setView(view);
        
        builder.setPositiveButton("返回", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        builder.show();*/
	}

	/*private void showTextAlertDialog(String text, String title) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		final View view = inflater.inflate(R.layout.common_alertdialog, null);

		TextView textView = (TextView) view.findViewById(R.id.text);
		textView.setText(text);

		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setCancelable(false);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle(title);
		builder.setView(view);
		builder.setPositiveButton("返回", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			}
		});
		builder.show();
	}*/
}
