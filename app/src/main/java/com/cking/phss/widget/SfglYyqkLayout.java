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

import net.xinhuaxing.util.ResourcesFactory;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.dto.Ddywgyfs39;
import com.cking.phss.dto.Ddywjldw37;
import com.cking.phss.dto.Ddywyf38;
import com.cking.phss.dto.Ddzlyw36;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.Medicine;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.sqlite4address.Icd10;
import com.cking.phss.sqlite4address.SqliteDataController;
import com.cking.pinyin.SearchAdapter;

/**
 * 随访管理->用药情况->ListView com.cking.phss.view.SfglYyqkLayout
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2012-9-18 下午04:24:14
 */
public class SfglYyqkLayout extends ListView {
	private static final String TAG = "SfglYyqkLayout";

	private Context mContext = null;
    ImageView closeImageView;
    Button qdButton;
    Button qxButton;
    LinearLayout titleLinearLayout;
    Dialog selectDialog = null;
    AutoCompleteTextView ywmcAutoCompleteTextView;
    SpinnerUtil ywlxSpinnerUtil;
    EditText ylEditText;
    SpinnerUtil ywdwSpinnerUtil;
    SpinnerUtil yfSpinnerUtil;
    EditText syzjlEditText;
    SpinnerUtil gyfsSpinnerUtil;
    
    String ywmcId = null;
    String[] valueString = null;
    String[] idString = null;
    
	private ArrayList<String> medicineIdList = null;
	private ArrayList<String> medicineNameList = null;
	private ArrayList<String> usageIdList = null;
	private ArrayList<String> usageNameList = null;
	private ArrayList<String> medicineUnitIdList = null;
	private ArrayList<String> medicineUnitNameList = null;
	private ArrayList<String> wayIdList = null;
	private ArrayList<String> wayNameList = null;
	private ArrayAdapter<String> medicineNameAdapter = null;
	private ArrayAdapter<String> medicineUnitNameAdapter = null;
	private ArrayAdapter<String> usageNameAdapter = null;
	private ArrayAdapter<String> wayNameAdapter = null;

	public List<ListItemSfglYyqk> mListView = new ArrayList<ListItemSfglYyqk>();

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
	public SfglYyqkLayout(Context context) {
		super(context);
		init(context);
		loadArrayToAdapter();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglYyqkLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		loadArrayToAdapter();
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		mContext = context;
        // removeAllViews();
        setAdapter(mListAdapter);

        // 药物名称
        List<Icd10> icd10List = SqliteDataController.getIcd10List("cdicdtenywmc");
        if (icd10List != null) {
            valueString = new String[icd10List.size()];
            idString = new String[icd10List.size()];
            int i = 0;
            for (Icd10 stringItem : icd10List) {
                valueString[i] = stringItem.getIcdTenName();
                idString[i] = stringItem.getIcdTenCode();
                i++;
            }
        } else {
            throw new RuntimeException("加载数据源'address'数据库资源出错,无法找到该资源,name=cdicdtenywmc");
        }
	}

	/**
	 * 添加标题栏
	 */
//	public void addTitle() {
//		ListItemSfglYyqk listItem = new ListItemSfglYyqk(mContext);
//		mListView.add(listItem);
//		listItem.setIndex("序号");
//		listItem.setName("药物名称");
//		listItem.setUsage("药物用法");
//		listItem.setDosage("用量");
//		listItem.setUnit("计量单位");
//		listItem.setWay("给药方式");
//		listItem.setTitleItem(true); // / 设置为标题项
//		addView(listItem);
//		// notifyDataSetChanged();
//		invalidate();
//	}

	private void loadArrayToAdapter() {
		medicineIdList = new ArrayList<String>();
		medicineNameList = new ArrayList<String>();
		usageIdList = new ArrayList<String>();
		usageNameList = new ArrayList<String>();
		medicineUnitIdList = new ArrayList<String>();
		medicineUnitNameList = new ArrayList<String>();
		wayIdList = new ArrayList<String>();
		wayNameList = new ArrayList<String>();
		medicineNameAdapter = new ArrayAdapter<String>(mContext,
				R.layout.simple_spinner_item, medicineNameList);
		medicineNameAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		medicineUnitNameAdapter = new ArrayAdapter<String>(mContext,
				R.layout.simple_spinner_item, medicineUnitNameList);
		medicineUnitNameAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		usageNameAdapter = new ArrayAdapter<String>(mContext,
				R.layout.simple_spinner_item, usageNameList);
		usageNameAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		wayNameAdapter = new ArrayAdapter<String>(mContext,
				R.layout.simple_spinner_item, wayNameList);
		wayNameAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	public void addItem(final List<Integer> widths, LinearLayout titleLinearLayout) {
        this.titleLinearLayout = titleLinearLayout;
        selectDialog = new Dialog(mContext, R.style.dialog);  
        selectDialog.setCancelable(true);  
        selectDialog.setContentView(R.layout.dialog_add_yyqk_layout); 
        closeImageView = (ImageView)selectDialog.findViewById(R.id.closeImageView);
        ywmcAutoCompleteTextView = (AutoCompleteTextView)selectDialog.findViewById(R.id.ywmcAutoCompleteTextView);
        ywlxSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.ywlxSpinnerUtil);
        ylEditText = (EditText)selectDialog.findViewById(R.id.ylEditText);
        ywdwSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.ywdwSpinnerUtil);
        yfSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.yfSpinnerUtil);
        syzjlEditText = (EditText)selectDialog.findViewById(R.id.syzjlEditText);
        gyfsSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.gyfsSpinnerUtil);
        qdButton = (Button)selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button)selectDialog.findViewById(R.id.qxButton);

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();//隐藏对话框 
            }
        });
        
        ywmcId = null;
        ywmcAutoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                ywmcId = idString[position];
                Log.d(TAG, "onItemClick:"+position);
            }
            
        });
        
        ywmcAutoCompleteTextView.setThreshold(1);
        SearchAdapter<String> adapter = new SearchAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, valueString,SearchAdapter.ALL);//速度优先
        ywmcAutoCompleteTextView.setAdapter(adapter);//

        qdButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                if (ywmcId == null) {
					Toast.makeText(mContext, "请选择一种药物！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (ylEditText.getText().toString().trim().equals("")) {
					Toast.makeText(mContext, "请填写用量！", Toast.LENGTH_LONG)
							.show();
					return;
				}
                // if (syzjlEditText.getText().toString().trim().equals("")) {
                // Toast.makeText(mContext, "请填写使用总剂量！", Toast.LENGTH_LONG)
                // .show();
                // return;
                // }
                MedicineUse medicineUse = new MedicineUse();
                medicineUse.medicine = new BeanID(ywmcId + "", ywmcAutoCompleteTextView.getText().toString());
                medicineUse.medicineType = new BeanCD(ywlxSpinnerUtil.getSelectedValue(),
                        ywlxSpinnerUtil.getSelectedData());
                medicineUse.dosage = ylEditText.getText().toString().trim();
                medicineUse.medicineUnit = new BeanID(ywdwSpinnerUtil.getSelectedValue(), ywdwSpinnerUtil.getSelectedData());
                medicineUse.usage = new BeanID(yfSpinnerUtil.getSelectedValue(), yfSpinnerUtil.getSelectedData());
                medicineUse.integralDose = syzjlEditText.getText().toString().trim();
                medicineUse.way = new BeanID(gyfsSpinnerUtil.getSelectedValue(), gyfsSpinnerUtil.getSelectedData());
				addItem(null, medicineUse);
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
        selectDialog = new Dialog(mContext, R.style.dialog);  
        selectDialog.setCancelable(true);  
        selectDialog.setContentView(R.layout.dialog_add_yyqk_layout); 
        closeImageView = (ImageView)selectDialog.findViewById(R.id.closeImageView);
        ywmcAutoCompleteTextView = (AutoCompleteTextView)selectDialog.findViewById(R.id.ywmcAutoCompleteTextView);
        ywlxSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.ywlxSpinnerUtil);
        ylEditText = (EditText)selectDialog.findViewById(R.id.ylEditText);
        ywdwSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.ywdwSpinnerUtil);
        yfSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.yfSpinnerUtil);
        syzjlEditText = (EditText)selectDialog.findViewById(R.id.syzjlEditText);
        gyfsSpinnerUtil = (SpinnerUtil)selectDialog.findViewById(R.id.gyfsSpinnerUtil);
        qdButton = (Button)selectDialog.findViewById(R.id.qdButton);
        qxButton = (Button)selectDialog.findViewById(R.id.qxButton);

        ListItemSfglYyqk listItem = mListView.get(position);
        ywmcAutoCompleteTextView.setText(listItem.getName());
        for (int i = 0; i < valueString.length; i++) {
            if (valueString[i].equals(listItem.getName())) {
                ywmcId = idString[i];
                break;
            }
        }
        String id = ResourcesFactory.findId(mContext, "ywlx", listItem.getType());
        ywlxSpinnerUtil.setSelectedPositionByValue(id);
        ylEditText.setText(listItem.getDosage());
        id = ResourcesFactory.findId(mContext, "ywdw", listItem.getUnit());
        ywdwSpinnerUtil.setSelectedPositionByValue(id);
        id = ResourcesFactory.findId(mContext, "yf", listItem.getUsage());
        yfSpinnerUtil.setSelectedPositionByValue(id);
        syzjlEditText.setText(listItem.getTotalDosage());
        id = ResourcesFactory.findId(mContext, "gyfs", listItem.getWay());
        gyfsSpinnerUtil.setSelectedPositionByValue(id);
        
        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();//隐藏对话框 
            }
        });
        
        ywmcAutoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                ywmcId = idString[position];
                Log.d(TAG, "onItemClick:"+position);
            }
            
        });
        
        ywmcAutoCompleteTextView.setThreshold(1);
        SearchAdapter<String> adapter = new SearchAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, valueString,SearchAdapter.ALL);//速度优先
        ywmcAutoCompleteTextView.setAdapter(adapter);//

        qdButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                if (ywmcId == null) {
                    Toast.makeText(mContext, "请选择一种药物！", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if (ylEditText.getText().toString().trim().equals("")) {
                    Toast.makeText(mContext, "请填写用量！", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                // if (syzjlEditText.getText().toString().trim().equals("")) {
                // Toast.makeText(mContext, "请填写使用总剂量！", Toast.LENGTH_LONG)
                // .show();
                // return;
                // }
                MedicineUse medicineUse = new MedicineUse();
                medicineUse.medicine = new BeanID(ywmcId + "", ywmcAutoCompleteTextView.getText().toString());
                medicineUse.medicineType = new BeanCD(ywlxSpinnerUtil.getSelectedValue(),
                        ywlxSpinnerUtil.getSelectedData());
                medicineUse.dosage = ylEditText.getText().toString().trim();
                medicineUse.medicineUnit = new BeanID(ywdwSpinnerUtil.getSelectedValue(), ywdwSpinnerUtil.getSelectedData());
                medicineUse.usage = new BeanID(yfSpinnerUtil.getSelectedValue(), yfSpinnerUtil.getSelectedData());
                medicineUse.integralDose = syzjlEditText.getText().toString().trim();
                medicineUse.way = new BeanID(gyfsSpinnerUtil.getSelectedValue(), gyfsSpinnerUtil.getSelectedData());
                updateItem(position, medicineUse);
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
	public void addItem(List<Integer> widths, MedicineUse medicineUse) {
		ListItemSfglYyqk listItem = new ListItemSfglYyqk(mContext);
		mListView.add(listItem);
//        List<Integer> widths = new ArrayList<Integer>();
//        for (int i = 0; i < titleLinearLayout.getChildCount(); i++) {
//            View childView = titleLinearLayout.getChildAt(i);
//            if (childView instanceof TextView) {
//                int width = childView.getWidth();
//                widths.add(width);
//            }
//        }
//[64, 105, 105, 90, 130, 65, 150, 130, 90]
//[59, 99, 99, 78, 118, 59, 138, 118, 78]

//      widths.add(66);
//      widths.add(67);
//      widths.add(87);
//      widths.add(134);
//      widths.add(134);
//      widths.add(134);
//      widths.add(154);
//      widths.add(134);
//      widths.add(148);
      //Log.i(TAG, "addItem: " + widths.toString());
      //listItem.setViewByWidths(widths);
		getView(mListView.size() - 1, listItem, SfglYyqkLayout.this);
		listItem.setMedicineUse(medicineUse);
        listItem.setIndex(mListView.size() - 1);
		listItem.setName(medicineUse.medicine.getTagValue());
        listItem.setType(medicineUse.medicineType.getTagValue());
		listItem.setUsage(medicineUse.usage.getTagValue());
		listItem.setDosage(medicineUse.dosage);
		listItem.setUnit(medicineUse.medicineUnit.getTagValue());
		listItem.setWay(medicineUse.way.getTagValue());
        listItem.setTotalDosage(medicineUse.integralDose);
//		listItem.setTitleItem(false); // / 设置为内容项
		listItem.setTag(mListView.size() - 1);
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
		ListItemSfglYyqk listItem = mListView.get(position);
        // removeView(listItem);
        // invalidate();

		Log.e(TAG, "position:" + position);

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
	public void updateItem(int position, MedicineUse medicineUse) {
		ListItemSfglYyqk listItem = mListView.get(position);
		listItem.setMedicineUse(medicineUse);
		listItem.setName(medicineUse.medicine.getTagValue());
        listItem.setType(medicineUse.medicineType.getTagValue());
		listItem.setUsage(medicineUse.usage.getTagValue());
		listItem.setDosage(medicineUse.dosage);
		listItem.setUnit(medicineUse.medicineUnit.getTagValue());
		listItem.setWay(medicineUse.way.getTagValue());
        listItem.setTotalDosage(medicineUse.integralDose);
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

		ImageView editBtn = (ImageView) convertView.findViewById(R.id.edit_button);
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

	// 获取药物的请求
	private void getMedicine(String medicineName) {
		// 根据药物代码 ，查询药物
		Ddzlyw36 ddzlyw36 = new Ddzlyw36();
		ddzlyw36.request = new Ddzlyw36.Request();
		ddzlyw36.request.inputCode = medicineName;
		List<IDto> beanList = new ArrayList<IDto>();
		beanList.add(ddzlyw36);
		BeanUtil.getInstance().getBeanFromWeb(beanList,
				new BeanUtil.OnResultFromWeb() {
					@Override
					public void onResult(List<IDto> listBean, boolean isSucc) {
						if (isSucc) {
							if (listBean != null && listBean.size() > 0) {
								Ddzlyw36 ddzlyw36 = (Ddzlyw36) listBean.get(0);
								if (ddzlyw36 == null
										|| ddzlyw36.response == null
										|| ddzlyw36.response.medicines == null
										|| ddzlyw36.response.medicines.size() <= 0) {
									Toast.makeText(mContext,
											"药物代码可能有误，药物列表获取失败，请重新获取！",
											Toast.LENGTH_LONG).show();
									return;
								}
								medicineIdList.clear();
								medicineNameList.clear();
								// 设置药物名称的值
                        for (Medicine medicine : ddzlyw36.response.medicines) {
									medicineIdList.add(medicine.medicineID);
									medicineNameList.add(medicine.medicineName);
									medicineNameAdapter.notifyDataSetChanged();
								}
							}
						} else {
							Toast.makeText(mContext,
									"网络连接可能错误，药物列表获取失败，请重新获取！",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}

	// 得到药物的计量单位
	private void getMedicineUnit() {
		Ddywjldw37 ddywjldw37 = new Ddywjldw37();
		ddywjldw37.request = new Ddywjldw37.Request();
		ddywjldw37.request.medicineUnit = "";

		List<IDto> beanList = new ArrayList<IDto>();
		beanList.add(ddywjldw37);
		BeanUtil.getInstance().getBeanFromWeb(beanList,
				new BeanUtil.OnResultFromWeb() {
					@Override
					public void onResult(List<IDto> listBean, boolean isSucc) {
						if (isSucc) {
							if (listBean != null && listBean.size() > 0) {
								Ddywjldw37 ddywjldw37 = (Ddywjldw37) listBean
										.get(0);
								if (ddywjldw37 == null
										|| ddywjldw37.response == null
										|| ddywjldw37.response.medicineUnits == null
										|| ddywjldw37.response.medicineUnits
												.size() <= 0) {
									Toast.makeText(mContext, "计量单位获取失败，请重新获取！",
											Toast.LENGTH_LONG).show();
									return;
								}
								medicineUnitIdList.clear();
								medicineUnitNameList.clear();
								// 设置药物名称的值
                        for (BeanID medicineUnit : ddywjldw37.response.medicineUnits) {
									medicineUnitIdList
.add(medicineUnit.getiD());
									medicineUnitNameList
											.add(medicineUnit.getTagValue());
									medicineUnitNameAdapter
											.notifyDataSetChanged();
								}
							}
						} else {
							Toast.makeText(mContext,
									"网络连接错误，药物计量单位获取失败，请重新获取！",
									Toast.LENGTH_LONG).show();
						}
					}
				});

	}

	// 得到药物的用法
	private void getUsage() {
		Ddywyf38 ddywyf38 = new Ddywyf38();
		ddywyf38.request = new Ddywyf38.Request();
		ddywyf38.request.usage = "";

		List<IDto> beanList = new ArrayList<IDto>();
		beanList.add(ddywyf38);
		BeanUtil.getInstance().getBeanFromWeb(beanList,
				new BeanUtil.OnResultFromWeb() {
					@Override
					public void onResult(List<IDto> listBean, boolean isSucc) {
						if (isSucc) {
							if (listBean != null && listBean.size() > 0) {
								Ddywyf38 ddywyf38 = (Ddywyf38) listBean.get(0);
								if (ddywyf38 == null
										|| ddywyf38.response == null
										|| ddywyf38.response.usages == null
										|| ddywyf38.response.usages.size() <= 0) {
									Toast.makeText(mContext, "药物用法获取失败，请重新获取！",
											Toast.LENGTH_LONG).show();
									return;
								}
								usageIdList.clear();
								usageNameList.clear();
								// 设置药物名称的值
                        for (BeanID usage : ddywyf38.response.usages) {
                            usageIdList.add(usage.getiD());
									usageNameList.add(usage.getTagValue());
									usageNameAdapter.notifyDataSetChanged();
								}
							}
						} else {
							Toast.makeText(mContext, "网络连接错误，药物用法获取失败，请重新获取！",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}

	// 得到药物的给药方式
	private void getWay() {
		Ddywgyfs39 ddywgyfs39 = new Ddywgyfs39();
		ddywgyfs39.request = new Ddywgyfs39.Request();
		ddywgyfs39.request.way = "";

		List<IDto> beanList = new ArrayList<IDto>();
		beanList.add(ddywgyfs39);
		BeanUtil.getInstance().getBeanFromWeb(beanList,
				new BeanUtil.OnResultFromWeb() {
					@Override
					public void onResult(List<IDto> listBean, boolean isSucc) {
						if (isSucc) {
							if (listBean != null && listBean.size() > 0) {
								Ddywgyfs39 ddywgyfs39 = (Ddywgyfs39) listBean
										.get(0);
								if (ddywgyfs39 == null
										|| ddywgyfs39.response == null
										|| ddywgyfs39.response.ways == null
										|| ddywgyfs39.response.ways.size() <= 0) {
									Toast.makeText(mContext, "给药方式获取失败，请重新获取！",
											Toast.LENGTH_LONG).show();
									return;
								}
								wayIdList.clear();
								wayNameList.clear();
								// 设置药物名称的值
                        for (BeanID way : ddywgyfs39.response.ways) {
                            wayIdList.add(way.getiD());
									wayNameList.add(way.getTagValue());
									wayNameAdapter.notifyDataSetChanged();
								}
							}
						} else {
							Toast.makeText(mContext, "网络连接错误，给药方式获取失败，请重新获取！",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
}
