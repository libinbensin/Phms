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
import com.cking.phss.util.AddressTextFactory;

public class JbxxJtcyLayout extends ListView {

	private static final String TAG = "JbxxJtcyLayout";
    private Context mContext = null;
	public List<ListItemJbxxJtcy> mListView = new ArrayList<ListItemJbxxJtcy>();
	private Jmjbxx mJmjbxx;
	private int changePosition=1;
    Dialog selectDialog = null;
    SpinnerUtil yhzgxSpinnerUtil;
    SpinnerUtil whcdSpinnerUtil;
    CalendarText csrqCalendarText;
    EditText xmEditText;
    SpinnerUtil zySpinnerUtil;
    SpinnerUtil xbSpinnerUtil;
    SpinnerUtil hyzkSpinnerUtil;
    SpinnerUtil grzkSpinnerUtil;
    EditText sfzhmEditText;
    EditText bzEditText;
    AddressText jzdzAddressText;
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

	public JbxxJtcyLayout(Context context) {
		super(context);
		init(context);
	}

	public JbxxJtcyLayout(Context context, AttributeSet attrs) {
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
        selectDialog.setContentView(R.layout.dialog_add_jtcy_layout); 
//		LayoutInflater inflater = LayoutInflater.from(mContext);
//		final View textEntryView = inflater.inflate(
//				R.layout.jbxx_ywgms_alertdialog, null);
		yhzgxSpinnerUtil = (SpinnerUtil) selectDialog
				.findViewById(R.id.yhzgxSpinnerUtil);
		whcdSpinnerUtil = (SpinnerUtil) selectDialog
				.findViewById(R.id.whcdSpinnerUtil);
		csrqCalendarText = (CalendarText) selectDialog
				.findViewById(R.id.csrqCalendarText);
		xmEditText = (EditText) selectDialog
				.findViewById(R.id.xmEditText);
		zySpinnerUtil = (SpinnerUtil) selectDialog
				.findViewById(R.id.zySpinnerUtil);
		qdButton = (Button) selectDialog
                .findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog
                .findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog
                .findViewById(R.id.closeImageView);
        xbSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.xbSpinnerUtil);
        hyzkSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.hyzkSpinnerUtil);
        grzkSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.grzkSpinnerUtil);
        sfzhmEditText = (EditText) selectDialog
                .findViewById(R.id.sfzhmEditText);
        bzEditText = (EditText) selectDialog
                .findViewById(R.id.bzEditText);
        jzdzAddressText = (AddressText) selectDialog
                .findViewById(R.id.jzdzAddressText);

        Jmjbxx mJmjbxx = Global.jmjbxx;
        if (mJmjbxx != null) {
            AddressText.MemAddress memAddress = new AddressText.MemAddress();
            memAddress.setProvince(mJmjbxx.getNowProvince());
            memAddress.setCity(mJmjbxx.getNowCity());
            memAddress.setDistrict(mJmjbxx.getNowDistrict());
            memAddress.setStreet(mJmjbxx.getNowStreet());
            memAddress.setZone(mJmjbxx.getNowZone());
            memAddress.setRoad(mJmjbxx.getNowRoad());
            memAddress.setN(mJmjbxx.getNowN());
            memAddress.setH(mJmjbxx.getNowH());
            memAddress.setS(mJmjbxx.getNowS());
            memAddress.setOther(mJmjbxx.getNowOther());
            jzdzAddressText.setValue(memAddress);
        }

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });

        qdButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (StringUtil.isEmptyString(xmEditText.getText().toString())) {
                    Toast.makeText(mContext, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtil.isEmptyString(sfzhmEditText.getText().toString())) {
                    Toast.makeText(mContext, "身份证号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 添加子项
                final String yhzgx = yhzgxSpinnerUtil.getSelectedValue();
                final String xb = xbSpinnerUtil.getSelectedValue();
                final String whcd = whcdSpinnerUtil.getSelectedValue();
                final String zy = zySpinnerUtil.getSelectedValue();
                final String hyzk = hyzkSpinnerUtil.getSelectedValue();
                final String grzk = grzkSpinnerUtil.getSelectedValue();
                getJmjbxxFromDb("0", yhzgx, (mListView.size() + 1) + "",
                        xmEditText.getText().toString(), xb, csrqCalendarText.getText().toString(),
                        whcd, zy, hyzk, grzk, sfzhmEditText.getText().toString(), bzEditText
                                .getText().toString(), jzdzAddressText.getText().toString(), 1);
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
	    changePosition=position;
        selectDialog = new Dialog(mContext, R.style.dialog);  
        selectDialog.setCancelable(true);  
        selectDialog.setContentView(R.layout.dialog_add_jtcy_layout); 
//		LayoutInflater inflater = LayoutInflater.from(mContext);
//		final View textEntryView = inflater.inflate(
//				R.layout.jbxx_ywgms_alertdialog, null);
        yhzgxSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.yhzgxSpinnerUtil);
        whcdSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.whcdSpinnerUtil);
        csrqCalendarText = (CalendarText) selectDialog
                .findViewById(R.id.csrqCalendarText);
        xmEditText = (EditText) selectDialog
                .findViewById(R.id.xmEditText);
        zySpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.zySpinnerUtil);
        qdButton = (Button) selectDialog
                .findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog
                .findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog
                .findViewById(R.id.closeImageView);
        xbSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.xbSpinnerUtil);
        hyzkSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.hyzkSpinnerUtil);
        grzkSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.grzkSpinnerUtil);
        sfzhmEditText = (EditText) selectDialog
                .findViewById(R.id.sfzhmEditText);
        bzEditText = (EditText) selectDialog
                .findViewById(R.id.bzEditText);
        jzdzAddressText = (AddressText) selectDialog
                .findViewById(R.id.jzdzAddressText);

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });

		//把以前的信息加载进来现在出来
        final ListItemJbxxJtcy listItem = mListView.get(position);
        yhzgxSpinnerUtil.setSelectedPositionByData(listItem.getYhzgx());
        whcdSpinnerUtil.setSelectedPositionByData(listItem.getWhcd());
        csrqCalendarText.setText(listItem.getCsrq());
        xmEditText.setText(listItem.getXm());
        zySpinnerUtil.setSelectedPositionByData(listItem.getZy());
        xbSpinnerUtil.setSelectedPositionByData(listItem.getXb());
        hyzkSpinnerUtil.setSelectedPositionByData(listItem.getHyzk());
        grzkSpinnerUtil.setSelectedPositionByData(listItem.getGrzk());
        sfzhmEditText.setText(listItem.getSfzhm());
        bzEditText.setText(listItem.getBz());
        jzdzAddressText.setValue(AddressTextFactory.parseAddress(listItem.getJzdz()));

        qdButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                if (StringUtil.isEmptyString(xmEditText.getText().toString())) {
                    Toast.makeText(mContext, "姓名不能为空", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (StringUtil.isEmptyString(sfzhmEditText.getText().toString())) {
                    Toast.makeText(mContext, "身份证号码不能为空", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                // 添加子项
                ListItemJbxxJtcy listItem = mListView.get(position);
                final String yhzgx = yhzgxSpinnerUtil.getSelectedValue();
                final String xb = xbSpinnerUtil.getSelectedValue();
                final String whcd = whcdSpinnerUtil.getSelectedValue();
                final String zy = zySpinnerUtil.getSelectedValue();
                final String hyzk = hyzkSpinnerUtil.getSelectedValue();
                final String grzk = grzkSpinnerUtil.getSelectedValue();
                getJmjbxxFromDb(listItem.getMyId(), yhzgx, (mListView.size() + 1) + "",
                        xmEditText.getText().toString(), xb, csrqCalendarText
                        .getText().toString(), whcd, zy, hyzk, grzk, sfzhmEditText.getText()
                        .toString(), bzEditText.getText().toString(), jzdzAddressText.getText()
                        .toString(), 2);
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
        selectDialog.setContentView(R.layout.dialog_add_jtcy_layout);
        // LayoutInflater inflater = LayoutInflater.from(mContext);
        // final View textEntryView = inflater.inflate(
        // R.layout.jbxx_ywgms_alertdialog, null);
        yhzgxSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.yhzgxSpinnerUtil);
        whcdSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.whcdSpinnerUtil);
        csrqCalendarText = (CalendarText) selectDialog.findViewById(R.id.csrqCalendarText);
        xmEditText = (EditText) selectDialog.findViewById(R.id.xmEditText);
        zySpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.zySpinnerUtil);
        qdButton = (Button) selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog.findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
        xbSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.xbSpinnerUtil);
        hyzkSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.hyzkSpinnerUtil);
        grzkSpinnerUtil = (SpinnerUtil) selectDialog.findViewById(R.id.grzkSpinnerUtil);
        sfzhmEditText = (EditText) selectDialog.findViewById(R.id.sfzhmEditText);
        bzEditText = (EditText) selectDialog.findViewById(R.id.bzEditText);
        jzdzAddressText = (AddressText) selectDialog.findViewById(R.id.jzdzAddressText);

        yhzgxSpinnerUtil.setEnabled(false);
        whcdSpinnerUtil.setEnabled(false);
        csrqCalendarText.setEnabled(false);
        xmEditText.setEnabled(false);
        zySpinnerUtil.setEnabled(false);
        xbSpinnerUtil.setEnabled(false);
        hyzkSpinnerUtil.setEnabled(false);
        grzkSpinnerUtil.setEnabled(false);
        sfzhmEditText.setEnabled(false);
        bzEditText.setEnabled(false);
        jzdzAddressText.setEnabled(false);

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });

        // 把以前的信息加载进来现在出来
        final ListItemJbxxJtcy listItem = mListView.get(position);
        yhzgxSpinnerUtil.setSelectedPositionByData(listItem.getYhzgx());
        whcdSpinnerUtil.setSelectedPositionByData(listItem.getWhcd());
        csrqCalendarText.setText(listItem.getCsrq());
        xmEditText.setText(listItem.getXm());
        zySpinnerUtil.setSelectedPositionByData(listItem.getZy());
        xbSpinnerUtil.setSelectedPositionByData(listItem.getXb());
        hyzkSpinnerUtil.setSelectedPositionByData(listItem.getHyzk());
        grzkSpinnerUtil.setSelectedPositionByData(listItem.getGrzk());
        sfzhmEditText.setText(listItem.getSfzhm());
        bzEditText.setText(listItem.getBz());
        jzdzAddressText.setValue(AddressTextFactory.parseAddress(listItem.getJzdz()));

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
	    changePosition=position;
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage("您确定要删除项吗?").setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
                        getJmjbxxFromDb(null, null, null, null, null, null, null, null, null, null, null,
                                null, null, 3);

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
	 * @param jzdz 
	 * @param bz 
	 * @param sfzhm 
	 */
    public void addItem(String myId, String yhzgx, String disSn, String xm, String xb, String csrq, String whcd,
            String zy, String hyzk, String grzk, String sfzhm, String bz, String jzdz) {
		final ListItemJbxxJtcy listItem = new ListItemJbxxJtcy(mContext);
		mListView.add(listItem);
//        List<Integer> widths = new ArrayList<Integer>();
//        for (int i = 0; i<titleLinearLayout.getChildCount(); i++) {
//            View childView = titleLinearLayout.getChildAt(i);
//            if (childView instanceof TextView) {
//                int width = childView.getWidth();
//                widths.add(width);
//            }
//            
//        }
//        widths.add(159);
//        widths.add(69);
//        widths.add(70);
//        widths.add(140);
//        widths.add(140);
//        widths.add(70);
//        widths.add(140);
//        widths.add(140);
//        widths.add(130);
//        Log.i(TAG, "addItem: " + widths.toString());
//        listItem.setViewByWidths(widths);
		getView(mListView.size() - 1, listItem, JbxxJtcyLayout.this);
        listItem.setIndex(mListView.size() - 1);

        listItem.setMyId(myId);
		listItem.setYhzgx(ResourcesFactory.findValue(mContext, "yhzgx", yhzgx));
        listItem.setXm(xm);
        listItem.setXb(ResourcesFactory.findValue(mContext, "xb", xb));
        listItem.setCsrq(csrq);
        listItem.setWhcd(ResourcesFactory.findValue(mContext, "whcd", whcd));
        listItem.setZy(ResourcesFactory.findValue(mContext, "zy", zy));
        listItem.setHyzk(ResourcesFactory.findValue(mContext, "hyzk", hyzk));
        listItem.setGrzk(ResourcesFactory.findValue(mContext, "grzt", grzk));
		listItem.setDisSn(disSn);
        listItem.setSfzhm(sfzhm);
        listItem.setBz(bz);
        listItem.setJzdz(jzdz);
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
		ListItemJbxxJtcy listItem = mListView.get(position);
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
    public void updateItem(int position, String myId, String yhzgx, String disSn, String xm, String xb,
            String csrq, String whcd, String zy, String hyzk, String grzk, String sfzhm, String bz,
            String jzdz) {
		final ListItemJbxxJtcy listItem = mListView.get(position);
        listItem.setYhzgx(ResourcesFactory.findValue(mContext, "yhzgx", yhzgx));
        listItem.setXm(xm);
        listItem.setMyId(myId);
        listItem.setXb(ResourcesFactory.findValue(mContext, "xb", xb));
        listItem.setCsrq(csrq);
        listItem.setWhcd(ResourcesFactory.findValue(mContext, "whcd", whcd));
        listItem.setZy(ResourcesFactory.findValue(mContext, "zy", zy));
        listItem.setHyzk(ResourcesFactory.findValue(mContext, "hyzk", hyzk));
        listItem.setGrzk(ResourcesFactory.findValue(mContext, "grzt", grzk));
        listItem.setDisSn(disSn);
        listItem.setSfzhm(sfzhm);
        listItem.setBz(bz);
        listItem.setJzdz(jzdz);
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
		ImageView deleteImageView = (ImageView) convertView
				.findViewById(R.id.delete_button);
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
	
	//根据日期排序
    public List<HistoryHyper> dateSort(List<HistoryHyper> lists){
        long[] longsDate=new long[lists.size()];
        if(lists==null)
            return null;
        for(int i=0;i<lists.size();i++){
            String disDate=lists.get(i).getHappenDate();
            if(disDate.equals("")){
                lists.remove(i);
                  i--;
                continue;
            }
           SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date=format.parse(disDate);
                longsDate[i]=date.getTime();
            } catch (ParseException e) {
            }
        }
        
        HistoryHyper hdTemp=null;
        //12,3,156,9,123,9
        for (int i = 0; i < longsDate.length; i++) {
           for(int j=i+1;j<longsDate.length;j++){
               if(longsDate[i]<longsDate[j]){
                   long temp=longsDate[i];
                   longsDate[i]=longsDate[j];
                   longsDate[j]=temp;
                   //并且，把list也换位
                   hdTemp=lists.get(i);
                   lists.set(i, lists.get(j));
                   lists.set(j, hdTemp);
                   hdTemp=null;
               }
           }
        }
        return lists;
    }
    
    private void getJmjbxxFromDb(final String myId, final String yhzgx, final String disSn, final String xm,
            final String xb, final String csrq, final String whcd, final String zy,
            final String hyzk, final String grzk, final String sfzhm, final String bz,
            final String jzdz, final int operType) {
        // 先得到居民基本信息
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                mJmjbxx = (Jmjbxx) listBean.get(0);
                /*if (mJmjbxx == null || mJmjbxx.getResidentID().equals(""))*/ {
                    if (operType == 1) {// 新增
                        addItem(myId, yhzgx, disSn, xm,
                                xb, csrq, whcd, zy, hyzk, grzk, sfzhm, bz, jzdz);
                    } else if (operType == 2) {// 修改
                        updateItem(changePosition, myId, yhzgx, disSn, xm,
                                xb, csrq, whcd, zy, hyzk, grzk, sfzhm, bz, jzdz);
                    } else if (operType == 3) {// 删除
                        removeItem(changePosition);
                    } else if (operType == 4) {// 详细
                        showItem(changePosition, yhzgx, disSn, xm,
                                xb, csrq, whcd, zy, hyzk, grzk, sfzhm, bz, jzdz);
                    }
                    return;
                }
//                changeHistoryHyperToWeb(yhzgx, disSn, xm,
//                        xb, csrq, whcd, zy, hyzk, grzk,
//                        operType);
            }
        });

    }
    
    
    /**
     * @param jzdz 
     * @param bz 
     * @param sfzhm 
     * @param changePosition2
     */
    protected void showItem(int position, String yhzgx, String disSn, String xm, String xb,
            String csrq, String whcd, String zy, String hyzk, String grzk, String sfzhm, String bz,
            String jzdz) {
    }

    private void changeHistoryHyperToWeb(final int yhzgx, final String disSn, 
            final String xm, final int xb, final String csrq, final int whcd, final int zy,
            final int hyzk, final int grzk, final int operType){
//        Dzjmjkgms dzjmjkgms=new Dzjmjkgms();
//        dzjmjkgms.request=new Dzjmjkgms.Request();
//        
//        Login1 login1=MyApplication.getInstance().getSession().getLoginResult();
//        if(login1==null||login1.response==null){
//            Toast.makeText(mContext, "当前没有医生登录，操作失败！", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        dzjmjkgms.request.userID=login1.response.userID;
//        dzjmjkgms.request.residentID=mJmjbxx.getResidentID();
//        dzjmjkgms.request.hyperTypeCD=gmszl;
//        dzjmjkgms.request.hyperSn=disSn;
//        dzjmjkgms.request.hyperOperType=operType;
//        dzjmjkgms.request.hyperSource=new InnerClass(gmyId, gmyName);
//        dzjmjkgms.request.happenDate=mFbsjDate;
//        dzjmjkgms.request.cureDes=zlms;
//        dzjmjkgms.request.hyperReason=fbyyString;
//        
//        List<IDto> beanList=new ArrayList<IDto>();
//        beanList.add(dzjmjkgms);
//        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
//            @Override
//            public void onResult(List<IDto> listBean, boolean isSucc) {
//                if(isSucc&&listBean!=null){
//                    Dzjmjkgms responseDzjmjkgms=(Dzjmjkgms)listBean.get(0);
//                    if(responseDzjmjkgms!=null&&responseDzjmjkgms.response!=null){
//                        if(operType==1){//新增
//                            addItem(gmszl, responseDzjmjkgms.response.hyperSn, gmyId, gmyName, mFbsjDate, zlms, fbyyString);
//                        }else if(operType==2){//修改
//                            updateItem(changePosition,gmszl, gmyId, gmyName, mFbsjDate, zlms, fbyyString);
//                        }else if(operType==3){//删除
//                            removeItem(changePosition);
//                        }
//                        
//                    }
//                }
//            }
//        });
    }
}
