package com.cking.phss.widget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;

public class JbxxJtwtLayout extends ListView {

    private static final String TAG = "JbxxJtwtLayout";
    private Context mContext = null;
    public List<ListItemJbxxJtwt> mListView = new ArrayList<ListItemJbxxJtwt>();
    private Jmjbxx mJmjbxx;
    private int changePosition = 1;
    Dialog selectDialog = null;
    SpinnerUtil jdSpinnerUtil;
    CalendarText fsrqCalendarText;
    EditText fsrEditText;
    EditText wtpgEditText;
    MultipleChoiceText zywtMultipleChoiceText;
    EditText cljjgEditText;
    EditText zgzlEditText;
    EditText kgzlEditText;
    CalendarText jlrqCalendarText;
    EditText qtEditText;
    EditText gljhEditText;
    EditText jlysEditText;
    EditText bzEditText;
    ImageView closeImageView;
    Button qdButton;
    Button qxButton;
    LinearLayout titleLinearLayout;

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

    public JbxxJtwtLayout(Context context) {
        super(context);
        init(context);
    }

    public JbxxJtwtLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // removeAllViews();
        setAdapter(mListAdapter);
    }

    public void addItem(LinearLayout titleLinearLayout) {
        this.titleLinearLayout = titleLinearLayout;
        selectDialog = new Dialog(mContext, R.style.dialog);
        selectDialog.setCancelable(true);
        selectDialog.setContentView(R.layout.dialog_add_jtwt_layout);
        // LayoutInflater inflater = LayoutInflater.from(mContext);
        // final View textEntryView = inflater.inflate(
        // R.layout.jbxx_ywgms_alertdialog, null);
        jdSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.jdSpinnerUtil);
        fsrqCalendarText = (CalendarText) selectDialog.findViewById(R.id.fsrqCalendarText);
        fsrEditText = (EditText) selectDialog.findViewById(R.id.fsrEditText);
        qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
        wtpgEditText = (EditText) selectDialog.findViewById(R.id.wtpgEditText);
        zywtMultipleChoiceText = (MultipleChoiceText) selectDialog
                .findViewById(R.id.zywtMultipleChoiceText);
        cljjgEditText = (EditText) selectDialog.findViewById(R.id.cljjgEditText);
        zgzlEditText = (EditText) selectDialog.findViewById(R.id.zgzlEditText);
        kgzlEditText = (EditText) selectDialog.findViewById(R.id.kgzlEditText);
        jlrqCalendarText = (CalendarText) selectDialog.findViewById(R.id.jlrqCalendarText);
        qtEditText = (EditText) selectDialog.findViewById(R.id.qtEditText);
        gljhEditText = (EditText) selectDialog.findViewById(R.id.gljhEditText);
        jlysEditText = (EditText) selectDialog.findViewById(R.id.jlysEditText);
        bzEditText = (EditText) selectDialog.findViewById(R.id.bzEditText);

        jlysEditText.setText(Global.doctorName);
        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });

        qdButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (StringUtil.isEmptyString(fsrEditText.getText().toString())) {
                    Toast.makeText(mContext, "发生人不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 添加子项
                final int jd = Integer.parseInt(jdSpinnerUtil.getSelectedValue());
                getJmjbxxFromDb(jd, (mListView.size() + 1) + "", fsrEditText.getText().toString(),
                        fsrqCalendarText.getText().toString(), wtpgEditText.getText().toString(),
                        zywtMultipleChoiceText.getCheckedDatas(","), cljjgEditText.getText()
                                .toString(), zgzlEditText.getText().toString(), kgzlEditText
                                .getText().toString(), qtEditText.getText().toString(),
                        gljhEditText.getText().toString(), jlysEditText.getText().toString(),
                        jlrqCalendarText.getText().toString(), bzEditText.getText().toString(), 1);
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
        changePosition = position;
        selectDialog = new Dialog(mContext, R.style.dialog);
        selectDialog.setCancelable(true);
        selectDialog.setContentView(R.layout.dialog_add_jtwt_layout);
        // LayoutInflater inflater = LayoutInflater.from(mContext);
        // final View textEntryView = inflater.inflate(
        // R.layout.jbxx_ywgms_alertdialog, null);
        jdSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.jdSpinnerUtil);
        fsrqCalendarText = (CalendarText) selectDialog.findViewById(R.id.fsrqCalendarText);
        fsrEditText = (EditText) selectDialog.findViewById(R.id.fsrEditText);
        qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
        wtpgEditText = (EditText) selectDialog.findViewById(R.id.wtpgEditText);
        zywtMultipleChoiceText = (MultipleChoiceText) selectDialog
                .findViewById(R.id.zywtMultipleChoiceText);
        cljjgEditText = (EditText) selectDialog.findViewById(R.id.cljjgEditText);
        zgzlEditText = (EditText) selectDialog.findViewById(R.id.zgzlEditText);
        kgzlEditText = (EditText) selectDialog.findViewById(R.id.kgzlEditText);
        jlrqCalendarText = (CalendarText) selectDialog.findViewById(R.id.jlrqCalendarText);
        qtEditText = (EditText) selectDialog.findViewById(R.id.qtEditText);
        gljhEditText = (EditText) selectDialog.findViewById(R.id.gljhEditText);
        jlysEditText = (EditText) selectDialog.findViewById(R.id.jlysEditText);
        bzEditText = (EditText) selectDialog.findViewById(R.id.bzEditText);

        // 把以前的信息加载进来现在出来
        if (position >= mListView.size())
            return;

        final ListItemJbxxJtwt listItem = mListView.get(position);
        jdSpinnerUtil.setSelectedPositionByData(listItem.getJd());
        fsrqCalendarText.setText(listItem.getFsrq());
        fsrEditText.setText(listItem.getFsr());
        wtpgEditText.setText(listItem.getWtpg());
        zywtMultipleChoiceText.setCheckedByDatas(listItem.getZywt());
        cljjgEditText.setText(listItem.getCljjg());
        zgzlEditText.setText(listItem.getZgzl());
        kgzlEditText.setText(listItem.getKgzl());
        jlrqCalendarText.setText(listItem.getJlrq());
        qtEditText.setText(listItem.getQt());
        gljhEditText.setText(listItem.getGljh());
        jlysEditText.setText(listItem.getJlys());
        bzEditText.setText(listItem.getBz());

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });

        qdButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (StringUtil.isEmptyString(fsrEditText.getText().toString())) {
                    Toast.makeText(mContext, "发生人不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 添加子项
                ListItemJbxxJtwt listItem = mListView.get(position);
                final int jd = Integer.parseInt(jdSpinnerUtil.getSelectedValue());
                getJmjbxxFromDb(jd, listItem.getDisSn(), fsrEditText.getText().toString(),
                        fsrqCalendarText.getText().toString(), wtpgEditText.getText().toString(),
                        zywtMultipleChoiceText.getCheckedDatas(","), cljjgEditText.getText()
                                .toString(), zgzlEditText.getText().toString(), kgzlEditText
                                .getText().toString(), qtEditText.getText().toString(),
                        gljhEditText.getText().toString(), jlysEditText.getText().toString(),
                        jlrqCalendarText.getText().toString(), bzEditText.getText().toString(), 2);
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

    public void showItem(final int position) {
        changePosition = position;
        selectDialog = new Dialog(mContext, R.style.dialog);
        selectDialog.setCancelable(true);
        selectDialog.setContentView(R.layout.dialog_add_jtwt_layout);
        // LayoutInflater inflater = LayoutInflater.from(mContext);
        // final View textEntryView = inflater.inflate(
        // R.layout.jbxx_ywgms_alertdialog, null);
        jdSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.jdSpinnerUtil);
        fsrqCalendarText = (CalendarText) selectDialog.findViewById(R.id.fsrqCalendarText);
        fsrEditText = (EditText) selectDialog.findViewById(R.id.fsrEditText);
        qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
        wtpgEditText = (EditText) selectDialog.findViewById(R.id.wtpgEditText);
        zywtMultipleChoiceText = (MultipleChoiceText) selectDialog
                .findViewById(R.id.zywtMultipleChoiceText);
        cljjgEditText = (EditText) selectDialog.findViewById(R.id.cljjgEditText);
        zgzlEditText = (EditText) selectDialog.findViewById(R.id.zgzlEditText);
        kgzlEditText = (EditText) selectDialog.findViewById(R.id.kgzlEditText);
        jlrqCalendarText = (CalendarText) selectDialog.findViewById(R.id.jlrqCalendarText);
        qtEditText = (EditText) selectDialog.findViewById(R.id.qtEditText);
        gljhEditText = (EditText) selectDialog.findViewById(R.id.gljhEditText);
        jlysEditText = (EditText) selectDialog.findViewById(R.id.jlysEditText);
        bzEditText = (EditText) selectDialog.findViewById(R.id.bzEditText);

        jdSpinnerUtil.setEnabled(false);
        fsrqCalendarText.setEnabled(false);
        fsrEditText.setEnabled(false);
        wtpgEditText.setEnabled(false);
        zywtMultipleChoiceText.setEnabled(false);
        cljjgEditText.setEnabled(false);
        zgzlEditText.setEnabled(false);
        kgzlEditText.setEnabled(false);
        jlrqCalendarText.setEnabled(false);
        qtEditText.setEnabled(false);
        gljhEditText.setEnabled(false);
        jlysEditText.setEnabled(false);
        bzEditText.setEnabled(false);

        // 把以前的信息加载进来现在出来
        if (position >= mListView.size())
            return;

        final ListItemJbxxJtwt listItem = mListView.get(position);
        jdSpinnerUtil.setSelectedPositionByData(listItem.getJd());
        fsrqCalendarText.setText(listItem.getFsrq());
        fsrEditText.setText(listItem.getFsr());
        wtpgEditText.setText(listItem.getWtpg());
        zywtMultipleChoiceText.setCheckedByDatas(listItem.getZywt());
        cljjgEditText.setText(listItem.getCljjg());
        zgzlEditText.setText(listItem.getZgzl());
        kgzlEditText.setText(listItem.getKgzl());
        jlrqCalendarText.setText(listItem.getJlrq());
        qtEditText.setText(listItem.getQt());
        gljhEditText.setText(listItem.getGljh());
        jlysEditText.setText(listItem.getJlys());
        bzEditText.setText(listItem.getBz());

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });

        qdButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
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
        changePosition = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("您确定要删除项吗?").setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getJmjbxxFromDb(0, null, null, null, null, null, null, null, null, null,
                                null, null, null, null, 3);

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
    public void addItem(final int jd, final String sn, final String fsr, final String fsrq,
            final String wtpg, final String zywt, final String cljjg, final String zgzl,
            final String kgzl, final String qt, final String gljh, final String jlys,
            final String jlrq, final String bz) {
        final ListItemJbxxJtwt listItem = new ListItemJbxxJtwt(mContext);
        mListView.add(listItem);
        // List<Integer> widths = new ArrayList<Integer>();
        // for (int i = 0; i<titleLinearLayout.getChildCount(); i++) {
        // View childView = titleLinearLayout.getChildAt(i);
        // if (childView instanceof TextView) {
        // int width = childView.getWidth();
        // widths.add(width);
        // }
        //
        // }
        // widths.add(66);
        // widths.add(67);
        // widths.add(87);
        // widths.add(134);
        // widths.add(134);
        // widths.add(134);
        // widths.add(154);
        // widths.add(134);
        // widths.add(148);
        // Log.i(TAG, "addItem: " + widths.toString());
        // listItem.setViewByWidths(widths);
        getView(mListView.size() - 1, listItem, JbxxJtwtLayout.this);
        listItem.setIndex(mListView.size() - 1);

        listItem.setJd(ResourcesFactory.findValue(mContext, "jd", jd));
        listItem.setFsr(fsr);
        listItem.setFsrq(fsrq);
        listItem.setWtpg(wtpg);
        listItem.setZywt(zywt);
        listItem.setCljjg(cljjg);
        listItem.setZgzl(zgzl);
        listItem.setKgzl(kgzl);
        listItem.setQt(qt);
        listItem.setGljh(gljh);
        listItem.setJlys(jlys);
        listItem.setJlrq(jlrq);
        listItem.setBz(bz);
        listItem.setDisSn(sn);
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
        ListItemJbxxJtwt listItem = mListView.get(position);
        // removeView(listItem);
        // invalidate();

        mListView.remove(position);
        // 更新索引
        for (int i = position; i < mListView.size(); i++) {
            listItem = mListView.get(i);
            listItem.setIndex(i + 1);
            listItem.setTag(i);
        }
        mListAdapter.notifyDataSetChanged();
    }

    /**
     * 更新子项
     */
    public void updateItem(int position, final int jd, final String sn, final String fsr,
            final String fsrq, final String wtpg, final String zywt, final String cljjg,
            final String zgzl, final String kgzl, final String qt, final String gljh,
            final String jlys, final String jlrq, final String bz) {
        final ListItemJbxxJtwt listItem = mListView.get(position);
        listItem.setJd(ResourcesFactory.findValue(mContext, "jd", jd));
        listItem.setFsr(fsr);
        listItem.setFsrq(fsrq);
        listItem.setWtpg(wtpg);
        listItem.setZywt(zywt);
        listItem.setCljjg(cljjg);
        listItem.setZgzl(zgzl);
        listItem.setKgzl(kgzl);
        listItem.setQt(qt);
        listItem.setGljh(gljh);
        listItem.setJlys(jlys);
        listItem.setJlrq(jlrq);
        listItem.setBz(bz);
        listItem.setDisSn(sn);
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

        ImageView detailImageView = (ImageView) convertView.findViewById(R.id.detail_button);
        detailImageView.setTag(convertView);
        ImageView editImageView = (ImageView) convertView.findViewById(R.id.edit_button);
        editImageView.setTag(convertView);
        ImageView deleteImageView = (ImageView) convertView.findViewById(R.id.delete_button);
        deleteImageView.setTag(convertView);
        detailImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                View parent = (View) arg0.getTag();
                int index = (Integer) parent.getTag();
                showItem(index);
            }
        });
        editImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                View parent = (View) arg0.getTag();
                int index = (Integer) parent.getTag();
                editItem(index);
            }
        });
        deleteImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                View parent = (View) arg0.getTag();
                int index = (Integer) parent.getTag();
                deleteItem(index);
            }
        });

        return convertView;
    }

    // 根据日期排序
    public List<HistoryHyper> dateSort(List<HistoryHyper> lists) {
        long[] longsDate = new long[lists.size()];
        if (lists == null)
            return null;
        for (int i = 0; i < lists.size(); i++) {
            String disDate = lists.get(i).getHappenDate();
            if (disDate.equals("")) {
                lists.remove(i);
                i--;
                continue;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(disDate);
                longsDate[i] = date.getTime();
            } catch (ParseException e) {
            }
        }

        HistoryHyper hdTemp = null;
        // 12,3,156,9,123,9
        for (int i = 0; i < longsDate.length; i++) {
            for (int j = i + 1; j < longsDate.length; j++) {
                if (longsDate[i] < longsDate[j]) {
                    long temp = longsDate[i];
                    longsDate[i] = longsDate[j];
                    longsDate[j] = temp;
                    // 并且，把list也换位
                    hdTemp = lists.get(i);
                    lists.set(i, lists.get(j));
                    lists.set(j, hdTemp);
                    hdTemp = null;
                }
            }
        }
        return lists;
    }

    protected void getJmjbxxFromDb(final int jd, final String sn, final String fsr,
            final String fsrq, final String wtpg, final String zywt, final String cljjg,
            final String zgzl, final String kgzl, final String qt, final String gljh,
            final String jlys, final String jlrq, final String bz, final int operType) {
        // 先得到居民基本信息
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                mJmjbxx = (Jmjbxx) listBean.get(0);
                /* if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) */{
                    if (operType == 1) {// 新增
                        addItem(jd, sn, fsr, fsrq, wtpg, zywt, cljjg, zgzl, kgzl, qt, gljh, jlys,
                                jlrq, bz);
                    } else if (operType == 2) {// 修改
                        updateItem(changePosition, jd, sn, fsr, fsrq, wtpg, zywt, cljjg, zgzl,
                                kgzl, qt, gljh, jlys, jlrq, bz);
                    } else if (operType == 3) {// 删除
                        removeItem(changePosition);
                    } else if (operType == 4) {// 详细
                        showItem(changePosition, jd, sn, fsr, fsrq, wtpg, zywt, cljjg, zgzl, kgzl,
                                qt, gljh, jlys, jlrq, bz);
                    }
                    return;
                }
                // changeHistoryHyperToWeb(jd, sn, fsr, fsrq, wtpg,
                // zywt, cljjg, zgzl, kgzl, qt,
                // gljh, jlys, jlrq, bz,
                // operType);
            }
        });

    }

    /**
     * @param changePosition2
     */
    protected void showItem(int position, final int jd, final String sn, final String fsr,
            final String fsrq, final String wtpg, final String zywt, final String cljjg,
            final String zgzl, final String kgzl, final String qt, final String gljh,
            final String jlys, final String jlrq, final String bz) {
    }

    private void changeHistoryHyperToWeb(final int jd, final String sn, final String fsr,
            final String fsrq, final String wtpg, final String zywt, final String cljjg,
            final String zgzl, final String kgzl, final String qt, final String gljh,
            final String jlys, final String jlrq, final String bz, final int operType) {
        // Dzjmjkgms dzjmjkgms=new Dzjmjkgms();
        // dzjmjkgms.request=new Dzjmjkgms.Request();
        //
        // Login1
        // login1=MyApplication.getInstance().getSession().getLoginResult();
        // if(login1==null||login1.response==null){
        // Toast.makeText(mContext, "当前没有医生登录，操作失败！",
        // Toast.LENGTH_SHORT).show();
        // return;
        // }
        // dzjmjkgms.request.userID=login1.response.userID;
        // dzjmjkgms.request.residentID=mJmjbxx.getResidentID();
        // dzjmjkgms.request.hyperTypeCD=gmszl;
        // dzjmjkgms.request.hyperSn=disSn;
        // dzjmjkgms.request.hyperOperType=operType;
        // dzjmjkgms.request.hyperSource=new InnerClass(gmyId, gmyName);
        // dzjmjkgms.request.happenDate=mFbsjDate;
        // dzjmjkgms.request.cureDes=zlms;
        // dzjmjkgms.request.hyperReason=fbyyString;
        //
        // List<IDto> beanList=new ArrayList<IDto>();
        // beanList.add(dzjmjkgms);
        // BeanUtil.getInstance().getBeanFromWeb(beanList, new
        // BeanUtil.OnResultFromWeb() {
        // @Override
        // public void onResult(List<IDto> listBean, boolean isSucc) {
        // if(isSucc&&listBean!=null){
        // Dzjmjkgms responseDzjmjkgms=(Dzjmjkgms)listBean.get(0);
        // if(responseDzjmjkgms!=null&&responseDzjmjkgms.response!=null){
        // if(operType==1){//新增
        // addItem(gmszl, responseDzjmjkgms.response.hyperSn, gmyId, gmyName,
        // mFbsjDate, zlms, fbyyString);
        // }else if(operType==2){//修改
        // updateItem(changePosition,gmszl, gmyId, gmyName, mFbsjDate, zlms,
        // fbyyString);
        // }else if(operType==3){//删除
        // removeItem(changePosition);
        // }
        //
        // }
        // }
        // }
        // });
    }
}
