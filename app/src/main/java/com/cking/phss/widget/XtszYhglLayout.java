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
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;

import com.cking.phss.R;

/**
 * 系统设置->用户管理->ListView
 * com.cking.phss.view.XtszYhglLayout
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午04:24:14
 */
public class XtszYhglLayout extends ListView {
    private static final String TAG = "XtszYhglLayout";
    
    private Context mContext = null;
    
    /**
     * 临时数据，用于过渡
     */
    private String mTempUser = null;  // 用户名
    private String mTempName = null; // 真实姓名
    private String mTempPassword = null;// 密码

    private List<ListItemXtszYhgl> mListView = new ArrayList<ListItemXtszYhgl>();

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
    public XtszYhglLayout(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszYhglLayout(Context context, AttributeSet attrs) {
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

    public void addItem() {
        LayoutInflater inflater = LayoutInflater.from(mContext);  
        final View textEntryView = inflater.inflate(  
                R.layout.xtsz_yhgl_alertdialog, null);  
        final EditText userEdit=(EditText)textEntryView.findViewById(R.id.user_edit);
        final EditText nameEdit=(EditText)textEntryView.findViewById(R.id.name_edit);
        final EditText passwordEdit=(EditText)textEntryView.findViewById(R.id.password_edit);

        final CheckBox showPasswordBox = (CheckBox) textEntryView.findViewById(R.id.check_show_password);
        
        showPasswordBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //文本正常显示
                    passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = passwordEdit.getText();
                    Selection.setSelection(etable, etable.length());
                } else {
                    //文本以密码形式显示
                    passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //下面两行代码实现: 输入框光标一直在输入文本后面
                    Editable etable = passwordEdit.getText();
                    Selection.setSelection(etable, etable.length());

                }
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);  
        builder.setCancelable(false);  
        builder.setIcon(R.drawable.ic_launcher);  
        builder.setTitle("新增用户帐号");  
        builder.setView(textEntryView);  
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mTempUser = userEdit.getText().toString();
                mTempName = nameEdit.getText().toString();
                mTempPassword = passwordEdit.getText().toString();
                // 添加子项
                addItem(mTempUser, mTempName, mTempPassword);
                dialog.cancel();
            }
        }); 
        builder.setNegativeButton("取消",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                        dialog.cancel(); 
                    }  
                });  
        builder.show();
    }
    
    public void editItem(final int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);  
        final View textEntryView = inflater.inflate(  
                R.layout.xtsz_yhgl_alertdialog, null);  
        final EditText userEdit=(EditText)textEntryView.findViewById(R.id.user_edit);
        final EditText nameEdit=(EditText)textEntryView.findViewById(R.id.name_edit);
        final EditText passwordEdit=(EditText)textEntryView.findViewById(R.id.password_edit);
        final CheckBox showPasswordBox = (CheckBox) textEntryView.findViewById(R.id.check_show_password);
        
        showPasswordBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //文本正常显示
                    passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = passwordEdit.getText();
                    Selection.setSelection(etable, etable.length());
                } else {
                    //文本以密码形式显示
                    passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //下面两行代码实现: 输入框光标一直在输入文本后面
                    Editable etable = passwordEdit.getText();
                    Selection.setSelection(etable, etable.length());

                }
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);  
        builder.setCancelable(false);  
        builder.setIcon(R.drawable.ic_launcher);  
        builder.setTitle("修改用户帐号");  
        builder.setView(textEntryView);  
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mTempUser = userEdit.getText().toString();
                mTempName = nameEdit.getText().toString();
                mTempPassword = passwordEdit.getText().toString();
                // 修改子项
                updateItem(position, mTempUser, mTempName, mTempPassword);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        builder.show();
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
    public void addItem(String user, String name, String password) {
        ListItemXtszYhgl listItem = new ListItemXtszYhgl(mContext);
        mListView.add(listItem);
        getView(mListView.size() - 1, listItem, XtszYhglLayout.this);
        listItem.setIndex(mListView.size() - 1);
        listItem.setUser(user);
        listItem.setName(name);
        listItem.setPassword(password);
        listItem.setTitleItem(false); /// 设置为内容项
        // addView(listItem);
        mListAdapter.notifyDataSetChanged();
        // invalidate();
    }
    
    /**
     * 移除子项
     * @param position
     */
    public void removeItem(int position) {
        ListItemXtszYhgl listItem = mListView.get(position);
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
    public void updateItem(int position, String user, String name, String password) {
        ListItemXtszYhgl listItem = mListView.get(position);
        listItem.setUser(user);
        listItem.setName(name);
        listItem.setPassword(password);
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

        Button editBtn = (Button) convertView.findViewById(R.id.edit_button);
        editBtn.setTag(convertView);
        Button deleteBtn = (Button) convertView.findViewById(R.id.delete_button);
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
