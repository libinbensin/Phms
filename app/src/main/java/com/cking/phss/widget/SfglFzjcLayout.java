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
import java.util.Date;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cking.phss.R;

/**
 * 随访管理->辅助检查->ListView
 * com.cking.phss.view.SfglFzjcLayout
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午04:24:14
 */
public class SfglFzjcLayout extends ListView {
    private static final String TAG = "SfglFzjcLayout";
    
    private Context mContext = null;
    
    Dialog selectDialog = null;  
    ImageView closeImageView;
    Button qdButton;
    Button qxButton;
    //LinearLayout titleLinearLayout;
    
    EditText projectEdit = null;
    EditText resultEdit = null;
    EditText operatorEdit = null;
    DatePicker datePicker = null;
    
    /**
     * 临时数据，用于过渡
     */
    private String mTempProject = null;  // 辅助检查项目
    private String mTempResult = null; // 辅助检查结果
    private String mTempOperator = null;// 检查人
    private Date mTempDate = null;  // 检查日期
    LinearLayout titleLinearLayout;

    public List<ListItemSfglFzjc> mListView = new ArrayList<ListItemSfglFzjc>();

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
    public SfglFzjcLayout(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglFzjcLayout(Context context, AttributeSet attrs) {
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
		selectDialog.setContentView(R.layout.dialog_sfgl_fzjc); 
		
		projectEdit = (EditText)selectDialog.findViewById(R.id.project_edit);	
		resultEdit = (EditText)selectDialog.findViewById(R.id.result_edit);
		operatorEdit = (EditText)selectDialog.findViewById(R.id.operator_edit);
		datePicker = (DatePicker)selectDialog.findViewById(R.id.date_picker);
		
		qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
	    qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
	    closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
		
		closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
		    
		    @Override
		    public void onClick(View arg0) {
		        selectDialog.dismiss();//隐藏对话框 
		    }
		});
		
		qdButton.setOnClickListener(new OnClickListener() {
		    
		    @Override
		    public void onClick(View arg0) {
		    	mTempProject = projectEdit.getText().toString();
		        mTempResult = resultEdit.getText().toString();
		        mTempOperator = operatorEdit.getText().toString();
		        mTempDate = new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker
		                .getDayOfMonth());
		        // 添加子项
                addItem(mTempProject, mTempResult, mTempOperator, mTempDate);
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
		selectDialog.setContentView(R.layout.dialog_sfgl_fzjc); 
		
		projectEdit = (EditText)selectDialog.findViewById(R.id.project_edit);	
		resultEdit = (EditText)selectDialog.findViewById(R.id.result_edit);
		operatorEdit = (EditText)selectDialog.findViewById(R.id.operator_edit);
		datePicker = (DatePicker)selectDialog.findViewById(R.id.date_picker);
		
		qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
	    qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
	    closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
	    
		//把以前的信息加载进来现在出来
        final ListItemSfglFzjc listItem = mListView.get(position);
        projectEdit.setText( listItem.getProject() );
        resultEdit.setText( listItem.getResult() );
        operatorEdit.setText( listItem.getOperator() );
        Date mDate = listItem.getDate();        
        //datePicker.init(mDate.getYear()+1900,mDate.getMonth(),mDate.getDay(),null);
        Log.i(TAG,"mDate = " + mDate);
        Log.i(TAG,"Year = " + (mDate.getYear()+1900) );
        Log.i(TAG,"Month = " + mDate.getMonth());
        Log.i(TAG,"Day = " + mDate.getDate());
        datePicker.updateDate(mDate.getYear()+1900,mDate.getMonth(),mDate.getDate());
        
		closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
		    
		    @Override
		    public void onClick(View arg0) {
		        selectDialog.dismiss();//隐藏对话框 
		    }
		});
		
		qdButton.setOnClickListener(new OnClickListener() {
		    
		    @Override
		    public void onClick(View arg0) {
		    	mTempProject = projectEdit.getText().toString();
		        mTempResult = resultEdit.getText().toString();
		        mTempOperator = operatorEdit.getText().toString();
		        mTempDate = new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker
		                .getDayOfMonth());
                // 修改子项
                updateItem(position, mTempProject, mTempResult, mTempOperator, mTempDate);
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
        builder.setMessage("您确定要删除项吗?") 
               .setCancelable(false) 
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
    public void addItem(String project, String result, String operator, Date date) {
        ListItemSfglFzjc listItem = new ListItemSfglFzjc(mContext);
        mListView.add(listItem);
        //List<Integer> widths = new ArrayList<Integer>();
//      for (int i = 0; i<titleLinearLayout.getChildCount(); i++) {
//          View childView = titleLinearLayout.getChildAt(i);
//            if (childView instanceof TextView) {
//                int width = childView.getWidth();
//                widths.add(width);
//            }
//          
//      }
//      193, 193, 133, 227, 187
        //widths.add(193);
        //widths.add(193);
        //widths.add(133);
        //widths.add(227);
        //widths.add(187);
      //Log.i(TAG, "addItem: " + widths.toString());
      //listItem.setViewByWidths(widths);
        getView(mListView.size() - 1, listItem, SfglFzjcLayout.this);
        listItem.setIndex(mListView.size() - 1);
        listItem.setProject(project);
        listItem.setResult(result);
        listItem.setOperator(operator);
        listItem.setDate(date);
        // addView(listItem);
        mListAdapter.notifyDataSetChanged();
        // invalidate();
    }
    
    /**
     * 移除子项
     * @param position
     */
    public void removeItem(int position) {
        ListItemSfglFzjc listItem = mListView.get(position);
        // removeView(listItem);
        // invalidate();
        
        Log.e(TAG, "position:" + position);
        
        mListView.remove(position);
        // 更新索引
        for (int i=position; i<mListView.size(); i++) {
            listItem = mListView.get(i);
            listItem.setTag(i);
        }
        mListAdapter.notifyDataSetChanged();
    }
    
    /**
     * 更新子项
     */
    public void updateItem(int position, String project, String result, String operator, Date date) {
        ListItemSfglFzjc listItem = mListView.get(position);
        listItem.setProject(project);
        listItem.setResult(result);
        listItem.setOperator(operator);
        listItem.setDate(date);
        mListAdapter.notifyDataSetChanged();
    }

    public int getCount() {
        //Log.i(TAG, "getView, mViews.length:" + mViews.length);
        return mListView.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        //Log.i(TAG, "getView, position:" + position);
        if (position >= mListView.size()) {
            return null;
        }

        convertView = mListView.get(position);

        ImageView editBtn = (ImageView) convertView.findViewById(R.id.edit_button);
        editBtn.setTag(convertView);
        ImageView deleteBtn = (ImageView) convertView.findViewById(R.id.delete_button);
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
