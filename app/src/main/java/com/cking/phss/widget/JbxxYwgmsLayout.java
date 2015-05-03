package com.cking.phss.widget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.xinhuaxing.util.ResourcesFactory;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.Dzjmjkgms;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class JbxxYwgmsLayout extends ListView {

	private static final String TAG = "JbxxYwgmsLayout";
    private Context mContext = null;
	public List<ListItemYwgmsCommon> mListView = new ArrayList<ListItemYwgmsCommon>();
	private Jmjbxx mJmjbxx;
	private int changePosition=1;
    Dialog selectDialog = null;
    SpinnerUtil gmsSpinnerUtil;
    SpinnerUtil gmySpinnerUtil;
    CalendarText fbsjCalendarText;
    EditText fbyyEditText;
    EditText zlmsEditText;
    ImageView closeImageView;
    Button qdButton;
    Button qxButton;
    LinearLayout titleLinearLayout;
    private Toast mToast = null;

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
    
	public JbxxYwgmsLayout(Context context) {
		super(context);
		init(context);
	}

	public JbxxYwgmsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
        mToast = TispToastFactory.getToast(mContext, "");
        // removeAllViews();
        setAdapter(mListAdapter);
	}

	public void addItem(LinearLayout titleLinearLayout) {
	    this.titleLinearLayout = titleLinearLayout;
        selectDialog = new Dialog(mContext, R.style.dialog);  
        selectDialog.setCancelable(true);  
        selectDialog.setContentView(R.layout.dialog_add_gms_layout); 
//		LayoutInflater inflater = LayoutInflater.from(mContext);
//		final View textEntryView = inflater.inflate(
//				R.layout.jbxx_ywgms_alertdialog, null);
		gmsSpinnerUtil = (SpinnerUtil) selectDialog
				.findViewById(R.id.gmsSpinner);
		gmySpinnerUtil = (SpinnerUtil) selectDialog
				.findViewById(R.id.gmySpinner);
		fbsjCalendarText = (CalendarText) selectDialog
				.findViewById(R.id.fbsjCalendarText);
		fbyyEditText = (EditText) selectDialog
				.findViewById(R.id.fbyyEditText);
		zlmsEditText = (EditText) selectDialog
				.findViewById(R.id.zlmsEditText);
		qdButton = (Button) selectDialog
                .findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog
                .findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog
                .findViewById(R.id.closeImageView);

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();//隐藏对话框 
            }
        });
        gmsSpinnerUtil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setGmySpinnerUtil(gmsSpinnerUtil.getSelectedValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

		qdButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                String gmsId = gmsSpinnerUtil.getSelectedValue();// 过敏史的种类
                String gmyId = "", gmyName = "";// 过敏原
                if (gmySpinnerUtil.getCount() > 0) {
                    gmyId = gmySpinnerUtil.getSelectedValue();
                    gmyName = gmySpinnerUtil.getSelectedData();
                }
                                
                if (gmyId.equals("") || gmyName.equals("")) {
                    Toast.makeText(mContext, "过敏源不能为空", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                String fbyyString = fbyyEditText.getText().toString();
                String mFbsjDate = fbsjCalendarText.getText().toString();
                if (fbsjCalendarText.equals("")) {
                    Toast.makeText(mContext, "发病时间不能为空", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                String zlms = zlmsEditText.getText().toString();
                // 添加子项
                getJmjbxxFromDb((mListView.size() + 1) + "", gmsId, gmyId, gmyName,
                        mFbsjDate,
                        zlms, fbyyString, 1);
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
        selectDialog.setContentView(R.layout.dialog_add_gms_layout); 
//		LayoutInflater inflater = LayoutInflater.from(mContext);
//		final View textEntryView = inflater.inflate(
//				R.layout.jbxx_ywgms_alertdialog, null);
        gmsSpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.gmsSpinner);
        gmySpinnerUtil = (SpinnerUtil) selectDialog
                .findViewById(R.id.gmySpinner);
        fbsjCalendarText = (CalendarText) selectDialog
                .findViewById(R.id.fbsjCalendarText);
        fbyyEditText = (EditText) selectDialog
                .findViewById(R.id.fbyyEditText);
        zlmsEditText = (EditText) selectDialog
                .findViewById(R.id.zlmsEditText);
        qdButton = (Button) selectDialog
                .findViewById(R.id.qdButton);
        qxButton = (Button) selectDialog
                .findViewById(R.id.qxButton);
        closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });
		
//		ArrayAdapter<String> adapter = null;
//		adapter = new ArrayAdapter<String>(mContext,
//				R.layout.simple_spinner_item, getResources().getStringArray(
//						R.array.gmszl_conditions));
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		gmsSpinner.setAdapter(adapter);
		//把以前的信息加载进来现在出来
        final ListItemYwgmsCommon listItem = mListView.get(position);
//		final ArrayList<String> gmyNameList = new ArrayList<String>();// 存储过敏原名称的list
//		final ArrayList<String> gmyIdList = new ArrayList<String>();// 存储过敏原id的list
//		final ArrayAdapter<String> mGmyAdapter = new ArrayAdapter<String>(
//				mContext, R.layout.simple_spinner_item, gmyNameList);
//		mGmyAdapter
//				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		gmySpinner.setAdapter(mGmyAdapter);

        gmsSpinnerUtil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setGmySpinnerUtil(gmsSpinnerUtil.getSelectedValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 过敏史种类
        gmsSpinnerUtil.setSelectedPositionByData(listItem.getGmsName());

        // 初始化过敏原列表
        setGmySpinnerUtil(gmsSpinnerUtil.getSelectedValue());
        gmySpinnerUtil.setSelectedPositionByData(listItem.getmGmyName());

        //发病时间
        fbsjCalendarText.setText(listItem.getDate());
        fbyyEditText.setText(listItem.getFbyy());
        zlmsEditText.setText(listItem.getZlms());

        qdButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                String gmsId = gmsSpinnerUtil.getSelectedValue();// 过敏史的种类
                String gmyId = "", gmyName = "";// 过敏原
                if (gmySpinnerUtil.getCount() > 0) {
                    gmyId = gmySpinnerUtil.getSelectedValue();
                    gmyName = gmySpinnerUtil.getSelectedData();
                }
                if (gmyId.equals("") || gmyName.equals("")) {
                    Toast.makeText(mContext, "过敏源不能为空", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                String fbyyString = fbyyEditText.getText().toString();
                String mFbsjDate = fbsjCalendarText.getText().toString();
                if (fbsjCalendarText.equals("")) {
                    Toast.makeText(mContext, "发病时间不能为空", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                String zlms = zlmsEditText.getText().toString();
                // 添加子项
                ListItemYwgmsCommon listItem = mListView.get(position);
                getJmjbxxFromDb(listItem.getDisSn(), gmsId, gmyId, gmyName, mFbsjDate,
                        zlms, fbyyString, 2);
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

	/**
     * @param position
     */
    protected void setGmySpinnerUtil(String position) {
        String namePrefix = "gmzl";
        String name = "";
        String suffix = position;
        name = namePrefix + suffix;
        gmySpinnerUtil.setName(name);
    }

    public void deleteItem(final int position) {
	    changePosition=position;
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage("您确定要删除项吗?").setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();

                        final ListItemYwgmsCommon listItem = mListView.get(changePosition);
                        String gmsId = listItem.getGmsId();// 过敏史的种类
                        String gmyId = listItem.getGmyId(), gmyName = listItem.getmGmyName();// 过敏原

                        String fbyyString = listItem.getFbyy();
                        String mFbsjDate = listItem.getmDate();
                        String zlms = listItem.getZlms();
                        getJmjbxxFromDb(listItem.getDisSn(), gmsId, gmyId, gmyName, mFbsjDate,
                                zlms, fbyyString, 3);
		                
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
	
	public void setListViewTitleLayout(LinearLayout titleLinearLayout) {
	    this.titleLinearLayout = titleLinearLayout;
	}

	/**
	 * 添加子项
	 */
    public void addItem(String disSn, String gmsId, String gmyId, String gmyName,
            String mFbsjDate,
            String zlms,
			String fbyyString) {
		final ListItemYwgmsCommon listItem = new ListItemYwgmsCommon(mContext);
		//List<Integer> widths = new ArrayList<Integer>();

		//widths.add(177);
        //widths.add(137);
        //widths.add(158);
        //widths.add(236);
        //widths.add(236);
        //widths.add(118);
        //Log.i(TAG, "addItem: " + widths.toString());
        //listItem.setViewByWidths(widths);
		mListView.add(listItem);
		getView(mListView.size() - 1, listItem, JbxxYwgmsLayout.this);
        listItem.setIndex(mListView.size() - 1);
        String gmsName = ResourcesFactory.findValue(mContext, "gmzl", gmsId);
        listItem.setDisSn(disSn);
        listItem.setGmsId(gmsId);
        listItem.setGmsName(gmsName);
        listItem.setGmyId(gmyId);
        listItem.setmGmyName(gmyName);
		listItem.setmDate(mFbsjDate);		
		listItem.setFbyy(fbyyString);
		listItem.setZlms(zlms);
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
		ListItemYwgmsCommon listItem = mListView.get(position);
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
    public void updateItem(int position, String gmsId, String gmyId,
            String gmyName, String mFbsjDate, String zlms, String fbyyString) {
		final ListItemYwgmsCommon listItem = mListView.get(position);

        String gmsName = ResourcesFactory.findValue(mContext, "gmzl", gmsId);
        listItem.setGmsId(gmsId);
        listItem.setGmsName(gmsName);
        listItem.setGmyId(gmyId);
        listItem.setmGmyName(gmyName);
        listItem.setmDate(mFbsjDate);
        listItem.setFbyy(fbyyString);
        listItem.setZlms(zlms);
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

		ImageView editImageView = (ImageView) convertView.findViewById(R.id.editImageView);
		editImageView.setTag(convertView);
		ImageView deleteImageView = (ImageView) convertView
				.findViewById(R.id.deleteImageView);
		deleteImageView.setTag(convertView);
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
    
    private void getJmjbxxFromDb(final String disSn, final String gmsId,
            final String gmyId,
            final String gmyName, final String mFbsjDate, final String zlms,
            final String fbyyString, final int operType) {
        // 先得到居民基本信息
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                mJmjbxx = Global.jmjbxx;
                if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
                    if (operType == 1) {// 新增
                        addItem(disSn, gmsId, gmyId, gmyName, mFbsjDate, zlms, fbyyString);
                    } else if (operType == 2) {// 修改
                        updateItem(changePosition, gmsId, gmyId, gmyName, mFbsjDate, zlms,
                                fbyyString);
                    } else if (operType == 3) {// 删除
                        removeItem(changePosition);
                    }
                    return;
                }
                changeHistoryHyperToWeb(gmsId, disSn, gmyId, gmyName, mFbsjDate, zlms, fbyyString,
                        operType);
            }
        });

    }
    
    
    private void changeHistoryHyperToWeb(final String gmsId, final String disSn,
            final String gmyId,
            final String gmyName, final String mFbsjDate, final String zlms,
            final String fbyyString, final int operType) {
        Dzjmjkgms dzjmjkgms = new Dzjmjkgms();
        dzjmjkgms.request = new Dzjmjkgms.Request();

        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            Toast.makeText(mContext, "当前没有医生登录，操作失败！", Toast.LENGTH_SHORT).show();
            return;
        }
        dzjmjkgms.request.userID = login1.response.userID;
        dzjmjkgms.request.residentID = mJmjbxx.getResidentID();
        dzjmjkgms.request.hyperTypeCD = gmsId;
        dzjmjkgms.request.hyperSn = disSn;
        dzjmjkgms.request.hyperOperType = operType;
        dzjmjkgms.request.hyperSource = new BeanID(gmyId, gmyName);
        dzjmjkgms.request.happenDate = mFbsjDate;
        dzjmjkgms.request.cureDes = zlms;
        dzjmjkgms.request.hyperReason = fbyyString;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dzjmjkgms);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc && listBean != null) {
                    Dzjmjkgms responseDzjmjkgms = (Dzjmjkgms) listBean.get(0);
                    if (responseDzjmjkgms != null && responseDzjmjkgms.response != null) {
                        if (operType == 1) {// 新增
                            addItem(disSn, gmsId, gmyId, gmyName, mFbsjDate, zlms, fbyyString);
                        } else if (operType == 2) {// 修改
                            updateItem(changePosition, gmsId, gmyId, gmyName, mFbsjDate, zlms,
                                    fbyyString);
                        } else if (operType == 3) {// 删除
                            removeItem(changePosition);
                        }

                    }
                }
            }
        });
    }
}
