/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.dto.Login1;
import com.cking.phss.sqlite.Account;
import com.cking.phss.sqlite.AccountBll;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.xml.util.XmlSerializerUtil;

/**
 * 用户管理
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class XtszGjszPage01 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage02";
    private Context mContext = null;
    Login1 currentUserLogin = null;
    String currentUserName = null;

    private List<Account> mListView = new ArrayList<Account>();
    /**
     * 第二页控件
     */

    ListView yhglListView;
    private Toast mToast = null;

    /**
     * @param context
     */
    public XtszGjszPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszGjszPage01(Context context, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_xtsz_gjsz_01_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        yhglListView = (ListView) findViewById(R.id.yhglListView);

        // 判断当前用户的权限
        currentUserLogin = MyApplication.getInstance().getSession().getLoginResult();
        currentUserName = currentUserLogin.request.userN;
        Account account = AccountBll.query(currentUserName);
        if (account.getUserGroup() == 0) {
            mListView.addAll(listAccount());
        } else {
            mListView.clear();
        }
        ListAdapter listAapter = new ListAdapter();
        yhglListView.setAdapter(listAapter);
    }

    private List<Account> listAccount() {
        List<Account> accouts = AccountBll.list();

        for (Account account : accouts) {
            if (account.getUsername().equals("admin")) { // 过滤admin账户
                accouts.remove(account);
                break;
            }
        }

        return accouts;
    }

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
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_yhgl_layout, null);
            if (position % 2 == 1) {
                convertView.setBackgroundResource(R.color.list_jsh_background_color);
            } else {
                convertView.setBackgroundResource(R.color.list_osh_background_color);
            }

            TextView xhTextView = (TextView) convertView.findViewById(R.id.xhTextView);
            TextView ysxmTextView = (TextView) convertView.findViewById(R.id.ysxmTextView);
            TextView yhmTextView = (TextView) convertView.findViewById(R.id.yhmTextView);
            TextView zjdlsjTextView = (TextView) convertView.findViewById(R.id.zjdlsjTextView);
            CheckBox qxCheckBox = (CheckBox) convertView.findViewById(R.id.qxCheckBox);
            CheckBox yhztCheckBox = (CheckBox) convertView.findViewById(R.id.yhztCheckBox);
            if (position >= 0 && position < mListView.size()) {
                Account account = mListView.get(position);
                xhTextView.setText((position + 1) + "");
                Login1 login1 = (Login1) XmlSerializerUtil.getInstance().beanFromXML(Login1.class,
                        account.getBean());
                ysxmTextView.setText(login1.response.doctorName);
                yhmTextView.setText(account.getUsername());
                Date lastLoginDateTime = new Date(account.getLastLoginDateTime());
                String timeStr = new SimpleDateFormat("yyyy-MM-dd").format(lastLoginDateTime);
                zjdlsjTextView.setText(timeStr);
                if (currentUserName != null && currentUserName.equals(account.getUsername())) {
                    qxCheckBox.setEnabled(false);
                    qxCheckBox.setBackgroundResource(R.drawable.xtsz_admin_invalid);
                    qxCheckBox.setVisibility(View.GONE);
                    yhztCheckBox.setEnabled(false);
                } else {
                    qxCheckBox.setEnabled(true);
                    if (account.getUserGroup() == Account.ADMIN) {
                        qxCheckBox.setChecked(true);
                    } else {
                        qxCheckBox.setChecked(false);
                    }
                    yhztCheckBox.setEnabled(true);
                }
                yhztCheckBox.setChecked(account.getStatus() == 1 ? true : false);
                // if (userInfo.userGroup.equals(Account.ADMIN)) {
                // adminButton.setTag(Account.ADMIN);
                // } else {
                // adminButton.setTag(Account.CUSTOMER);
                // }
                qxCheckBox.setTag(position);
                qxCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                        // 修改为管理员权限
                        int position = (Integer) arg0.getTag();
                        Account account = mListView.get(position);
                        boolean ret = false;
                        if (account.getUserGroup() == 0) {
                            AccountBll.updateUserGroup(account.getUsername(), Account.CUSTOMER);
                            account.setUserGroup(Account.CUSTOMER);
                        } else {
                            AccountBll.updateUserGroup(account.getUsername(), Account.ADMIN);
                            account.setUserGroup(Account.ADMIN);
                        }
                        if (account.getUserGroup() == Account.ADMIN) {
                            arg0.setChecked(true);
                        } else {
                            arg0.setChecked(false);
                        }
                        mToast.setText("用户权限切换成功");
                        mToast.show();
                    }
                });
                yhztCheckBox.setTag(position);
                yhztCheckBox
                        .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                                int position = (Integer) arg0.getTag();
                                Account account = mListView.get(position);
                                AccountBll.updateStatus(account.getUsername(), arg1);
                    }
                });
            }

            return convertView;
        }
    }

    public void deleteItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("您确定要删除项吗?").setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        removeItem(position);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void removeItem(int position) {

        mListView.remove(position);
        invalidate();
        // mAdapter.notifyDataSetChanged();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
