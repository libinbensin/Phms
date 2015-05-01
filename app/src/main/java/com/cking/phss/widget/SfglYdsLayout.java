/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxJwsjbListView.java
 * classes : com.cking.phss.view.JbxxJwsjbListView
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午04:24:14
 */
package com.cking.phss.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cking.phss.R;

/**
 * 随访管理->胰岛素->ListView com.cking.phss.view.SfglYyqkLayout
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2012-9-18 下午04:24:14
 */
public class SfglYdsLayout extends ListView {
	private static final String TAG = "SfglYdsLayout";

	private Context mContext = null;
	Dialog selectDialog = null;
	ImageView closeImageView;
	Button qdButton;
	Button qxButton;
	LinearLayout titleLinearLayout;

	EditText typeEdit = null;
	EditText frequencyEdit = null;
	EditText usageEdit = null;
	/**
	 * 临时数据，用于过渡
	 */
	private String mTempUsage = null; // 用法
	private String mTempFrequency = null;// 频率
	private String mTempType = null; // 药物类型

	public List<ListItemSfglYds> mListView = new ArrayList<ListItemSfglYds>();

    private ListAdapter mListAdapter = new ListAdapter();

    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mListView.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return mListView.get(position);
        }
    }
	/**
	 * @param context
	 */
	public SfglYdsLayout(Context context) {
		super(context);

		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglYdsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		mContext = context;
        // removeAllViews();
        setAdapter(mListAdapter);
	}

	public void addItem(LinearLayout titleLinearLayout) {
		this.titleLinearLayout = titleLinearLayout;

		selectDialog = new Dialog(mContext, R.style.dialog);
		selectDialog.setCancelable(true);
		selectDialog.setContentView(R.layout.dialog_sfgl_yds);

		typeEdit = (EditText) selectDialog.findViewById(R.id.type_edit);
		frequencyEdit = (EditText) selectDialog
				.findViewById(R.id.frequency_edit);
		usageEdit = (EditText) selectDialog.findViewById(R.id.usage_edit);
		qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
		qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
		closeImageView = (ImageView) selectDialog
				.findViewById(R.id.closeImageView);

		closeImageView
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						selectDialog.dismiss();// 隐藏对话框
					}
				});

		qdButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mTempType = typeEdit.getText().toString();
				mTempFrequency = frequencyEdit.getText().toString();
				mTempUsage = usageEdit.getText().toString();
				// 添加子项
				addItem(mTempType, mTempFrequency, mTempUsage);
				selectDialog.dismiss();

			}
		});

		qxButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				selectDialog.dismiss();
			}
		});

		selectDialog.show();

	}

	public void editItem(final int position) {
		this.titleLinearLayout = titleLinearLayout;

		selectDialog = new Dialog(mContext, R.style.dialog);
		selectDialog.setCancelable(true);
		selectDialog.setContentView(R.layout.dialog_sfgl_yds);

		typeEdit = (EditText) selectDialog.findViewById(R.id.type_edit);
		frequencyEdit = (EditText) selectDialog
				.findViewById(R.id.frequency_edit);
		usageEdit = (EditText) selectDialog.findViewById(R.id.usage_edit);
		qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
		qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
		closeImageView = (ImageView) selectDialog
				.findViewById(R.id.closeImageView);

		// 把以前的信息加载进来现在出来
		final ListItemSfglYds listItem = mListView.get(position);
		typeEdit.setText(listItem.getType());
		frequencyEdit.setText(listItem.getFrequency());
		usageEdit.setText(listItem.getUsage());

		closeImageView
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						selectDialog.dismiss();// 隐藏对话框
					}
				});

		qdButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mTempType = typeEdit.getText().toString();
				mTempFrequency = frequencyEdit.getText().toString();
				mTempUsage = usageEdit.getText().toString();
				// 修改子项
				updateItem(position, mTempType, mTempFrequency, mTempUsage);
				selectDialog.dismiss();

			}
		});

		qxButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				selectDialog.dismiss();
			}
		});

		selectDialog.show();

	}

	public void deleteItem(final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage("您确定要删除项吗?").setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						removeItem(position);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void clear() {
		mListView.clear();
        mListAdapter.notifyDataSetChanged();
        // removeAllViews();
        // invalidate();
	}

	/**
	 * 添加子项
	 */
	public void addItem(String type, String frequency, String usage) {
		ListItemSfglYds listItem = new ListItemSfglYds(mContext);
		mListView.add(listItem);
        listItem.setIndex(mListView.size() - 1);
		getView(mListView.size() - 1, listItem, SfglYdsLayout.this);
		listItem.setUsage(usage);
		listItem.setFrequency(frequency);
		listItem.setType(type);
        // addView(listItem);
        mListAdapter.notifyDataSetChanged();
        // invalidate();
	}

	/**
	 * 移除子项
	 * 
	 * @param position
	 */
	public void removeItem(int position) {
		ListItemSfglYds listItem = mListView.get(position);
        // removeView(listItem);
        // invalidate();

		Log.e(TAG, "position:" + position);

		mListView.remove(position);
		// 更新索引
		for (int i = position; i < mListView.size(); i++) {
			listItem = mListView.get(i);
			listItem.setTag(i);
		}
        mListAdapter.notifyDataSetChanged();
	}

	/**
	 * 更新子项
	 */
	public void updateItem(int position, String type, String frequency,
			String usage) {
		ListItemSfglYds listItem = mListView.get(position);
		listItem.setUsage(usage);
		listItem.setFrequency(frequency);
		listItem.setType(type);
        mListAdapter.notifyDataSetChanged();
	}

	public int getCount() {
		// Log.i(TAG, "getView, mViews.length:" + mViews.length);
		return mListView.size();
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Log.i(TAG, "getView, position:" + position);
		if (position >= mListView.size()) {
			return null;
		}

		convertView = mListView.get(position);

		ImageView editBtn = (ImageView) convertView
				.findViewById(R.id.edit_button);
		editBtn.setTag(convertView);
		ImageView deleteBtn = (ImageView) convertView
				.findViewById(R.id.delete_button);
		deleteBtn.setTag(convertView);
		editBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				View parent = (View) arg0.getTag();
				int index = (Integer) parent.getTag();
				editItem(index);
			}
		});
		deleteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				View parent = (View) arg0.getTag();
				int index = (Integer) parent.getTag();
				deleteItem(index);
			}
		});

		return convertView;
	}

}
