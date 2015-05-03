package com.cking.phss.util;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;

public class TispToastFactory {

	private static Context context = null;

	private static Toast toast = null;

	/**
	 * 
	 * @param context
	 *            使用时的上下文
	 * 
	 * @param hint
	 *            在提示框中需要显示的文本
	 * 
	 * @return 返回一个不会重复显示的toast
	 * 
	 * */
	public static Toast getToast(Context context, String hint) {
		if (TispToastFactory.context == context) {
			toast.cancel();
			toast.setText(hint);
		} else {
			TispToastFactory.context = context;
            toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
            LinearLayout layout = (LinearLayout) toast.getView();
            /*
             * layout.setLayoutParams(new WindowManager.LayoutParams(10000,
             * android.view.WindowManager.LayoutParams.WRAP_CONTENT,
             * WindowManager.LayoutParams.TYPE_TOAST,
             * WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
             * WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
             * WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
             * PixelFormat.TRANSLUCENT));
             */
            layout.setBackgroundResource(R.drawable.toast_bg);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER_VERTICAL);
            TextView tv = new TextView(context);
            tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setTextColor(Color.parseColor("#ffffffff"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setPadding(0, 0, 0, 0);
            tv.setText(hint);
            layout.addView(tv);
            toast.show();
		}
		return toast;

	}

}
