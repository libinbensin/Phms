/* Cking Inc. (C) 2012. All rights reserved.
 *
 * AddressText.java
 * classes : com.cking.phss.widget.AddressText
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 下午12:56:59
 */
package com.cking.phss.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.xinhuaxing.util.StringUtil;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.sqlite4address.Address;
import com.cking.phss.sqlite4address.SqliteDataController;
import com.cking.phss.util.AddressTextFactory;

/**
 * com.cking.phss.widget.AddressText
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 下午12:56:59
 */
public class AddressText extends TextView {
    @SuppressWarnings("unused")
    private static final String TAG = "AddressText";
    private Context mContext = null;
    private Spinner mProvinceSpinner = null;
    private Spinner mCitySpinner = null;
    private Spinner mDistrictSpinner = null;
    private Spinner mStreetSpinner = null;
    private Spinner mZoneSpinner = null;
    private Spinner mRoadSpinner = null;
    private EditText mNEdit = null;
    private EditText mHEdit = null;
    private EditText mSEdit = null;
    EditText otherEditText;

    List<HashMap<String, Object>> globalList = new ArrayList<HashMap<String, Object>>();

    private ArrayAdapter<String> mProvinceAdapter = null;
    private ArrayAdapter<String> mCityAdapter = null;
    private ArrayAdapter<String> mDistrictAdapter = null;
    private ArrayAdapter<String> mStreetAdapter = null;
    private ArrayAdapter<String> mZoneAdapter = null;
    private ArrayAdapter<String> mRoadAdapter = null;

    /**
     * 保存下拉列表的数组
     */
    public int[] provinceValues = null;
    List<Address> provinceList = new ArrayList<Address>();
    List<Address> cityList = new ArrayList<Address>();
    List<Address> districtList = new ArrayList<Address>();
    List<Address> streetList = new ArrayList<Address>();
    List<Address> zoneList = new ArrayList<Address>();
    List<Address> roadList = new ArrayList<Address>();

    public ArrayList<String> provinceStrings = new ArrayList<String>();
    private ArrayList<String> cityStrings = new ArrayList<String>();
    private ArrayList<String> districtStrings = new ArrayList<String>();
    private ArrayList<String> streetStrings = new ArrayList<String>();
    private ArrayList<String> zoneStrings = new ArrayList<String>();
    private ArrayList<String> roadStrings = new ArrayList<String>();

    public String address = null;

    public static class MemAddress {
        public BeanID province = null;
        public BeanID city = null;
        public BeanID district = null;
        public BeanID street = null;
        public BeanID zone = null;
        public BeanID road = null;
        public String n = null;
        public String h = null;
        public String s = null;
        public String other = null;

        public BeanID getProvince() {
            return province;
        }

        public void setProvince(BeanID province) {
            this.province = province;
        }

        public BeanID getCity() {
            return city;
        }

        public void setCity(BeanID city) {
            this.city = city;
        }

        public BeanID getDistrict() {
            return district;
        }

        public void setDistrict(BeanID district) {
            this.district = district;
        }

        public BeanID getStreet() {
            return street;
        }

        public void setStreet(BeanID street) {
            this.street = street;
        }

        public BeanID getZone() {
            return zone;
        }

        public void setZone(BeanID zone) {
            this.zone = zone;
        }

        public BeanID getRoad() {
            return road;
        }

        public void setRoad(BeanID road) {
            this.road = road;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }
    }

    private MemAddress rawAddress = new MemAddress();
    private MemAddress tmpAddress = new MemAddress();

    private void memAddressCopy(MemAddress srcAddress, MemAddress desAddress) {
        try {
            desAddress.province = srcAddress.province == null ? null : (BeanID) srcAddress.province
                    .clone();
            desAddress.city = srcAddress.city == null ? null : (BeanID) srcAddress.city.clone();
            desAddress.district = srcAddress.district == null ? null : (BeanID) srcAddress.district
                    .clone();
            desAddress.street = srcAddress.street == null ? null : (BeanID) srcAddress.street
                    .clone();
            desAddress.zone = srcAddress.zone == null ? null : (BeanID) srcAddress.zone.clone();
            desAddress.road = srcAddress.road == null ? null : (BeanID) srcAddress.road.clone();
            desAddress.n = srcAddress.n == null ? null : new String(srcAddress.n);
            desAddress.h = srcAddress.h == null ? null : new String(srcAddress.h);
            desAddress.s = srcAddress.s == null ? null : new String(srcAddress.s);
            desAddress.other = srcAddress.other == null ? null : new String(srcAddress.other);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    
    Dialog selectDialog = null;

    public BeanID getProvince() {
        return rawAddress.province;
    }

    public void setProvince(BeanID province) {
        this.rawAddress.province = province;
    }

    public BeanID getCity() {
        return rawAddress.city;
    }

    public void setCity(BeanID city) {
        this.rawAddress.city = city;
    }

    public BeanID getDistrict() {
        return rawAddress.district;
    }

    public void setDistrict(BeanID district) {
        this.rawAddress.district = district;
    }

    public BeanID getStreet() {
        return rawAddress.street;
    }

    public void setStreet(BeanID street) {
        this.rawAddress.street = street;
    }

    public BeanID getZone() {
        return rawAddress.zone;
    }

    public void setZone(BeanID zone) {
        this.rawAddress.zone = zone;
    }

    public BeanID getRoad() {
        return rawAddress.road;
    }

    public void setRoad(BeanID road) {
        this.rawAddress.road = road;
    }

    public String getN() {
        return rawAddress.n;
    }

    public void setN(String n) {
        this.rawAddress.n = n;
    }

    public String getH() {
        return rawAddress.h;
    }

    public void setH(String h) {
        this.rawAddress.h = h;
    }

    public String getS() {
        return rawAddress.s;
    }

    public void setS(String s) {
        this.rawAddress.s = s;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param context
     */
    public AddressText(Context context) {
        super(context);

        init(context);
    }

    public AddressText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showAddressDialog();
            }
        });
    }

    private void showAddressDialog() {
        selectDialog = new Dialog(mContext, R.style.dialog);  
        selectDialog.setCancelable(true);  
        selectDialog.setContentView(R.layout.dialog_set_address_layout); 
        if (rawAddress == null) {
            address = getText().toString();
            rawAddress = AddressTextFactory.parseAddress(address);
        }
        memAddressCopy(rawAddress, tmpAddress);
        initDialogView(mContext);
        selectDialog.show();
    }

    /**
     * @param mContext2
     */
    private void initDialogView(Context context) {
        mProvinceSpinner = (Spinner) selectDialog.findViewById(R.id.province_spinner);
        mCitySpinner = (Spinner) selectDialog.findViewById(R.id.city_spinner);
        mDistrictSpinner = (Spinner) selectDialog.findViewById(R.id.district_spinner);
        mStreetSpinner = (Spinner) selectDialog.findViewById(R.id.street_spinner);
        mZoneSpinner = (Spinner) selectDialog.findViewById(R.id.zone_spinner);
        mRoadSpinner = (Spinner) selectDialog.findViewById(R.id.road_spinner);
        mNEdit = (EditText) selectDialog.findViewById(R.id.n_edit);
        mHEdit = (EditText) selectDialog.findViewById(R.id.h_edit);
        mSEdit = (EditText) selectDialog.findViewById(R.id.s_edit);
        otherEditText = (EditText) selectDialog.findViewById(R.id.otherEditText);
        if (tmpAddress.n != null) {
            mNEdit.setText(tmpAddress.n);
        }
        if (tmpAddress.h != null) {
            mHEdit.setText(tmpAddress.h);
        }
        if (tmpAddress.s != null) {
            mSEdit.setText(tmpAddress.s);
        }
        if (tmpAddress.other != null) {
            otherEditText.setText(tmpAddress.other);
        }

        // 从数据库获取省列表
        List<Address> tmpProvinceList = new ArrayList<Address>();
        tmpProvinceList = SqliteDataController.getProvinceList("01");
        provinceList.clear();
        provinceList.addAll(tmpProvinceList);
        provinceStrings.clear();
        for (Address address : provinceList) {
            provinceStrings.add(address.getValue());
        }

        /**
         * 所有下拉框赋值
         */
        // 省份、城市、区、街道、社区 根据联动选择，从服务器读取数据
        // 省份下拉列表
        mProvinceAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item, provinceStrings);
        mProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProvinceSpinner.setAdapter(mProvinceAdapter);
        //mProvinceAdapter.notifyDataSetChanged();
        mProvinceSpinner.setOnItemSelectedListener(new ProvinceSpinnerOnSelectedListener());
        mProvinceSpinner.setOnTouchListener(onTouchListener);

        mCityAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item, cityStrings);
        mCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCitySpinner.setAdapter(mCityAdapter);
        // mCitySpinner.setOnTouchListener(new CitySpinnerOnTouchListener());
        mCitySpinner.setOnItemSelectedListener(new CitySpinnerOnSelectedListener());
        mCitySpinner.setOnTouchListener(onTouchListener);

        mDistrictAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item,
                districtStrings);
        mDistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDistrictSpinner.setAdapter(mDistrictAdapter);
        // mDistrictSpinner.setOnTouchListener(new
        // DistrictSpinnerOnTouchListener());
        mDistrictSpinner.setOnItemSelectedListener(new DistrictSpinnerOnSelectedListener());
        mDistrictSpinner.setOnTouchListener(onTouchListener);

        mStreetAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item,
                streetStrings);
        mStreetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStreetSpinner.setAdapter(mStreetAdapter);
        // mStreetSpinner.setOnTouchListener(new
        // StreetSpinnerOnTouchListener());
        mStreetSpinner.setOnItemSelectedListener(new StreetSpinnerOnSelectedListener());
        mStreetSpinner.setOnTouchListener(onTouchListener);

        mZoneAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item, zoneStrings);
        mZoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mZoneSpinner.setAdapter(mZoneAdapter);
        // mZoneSpinner.setOnTouchListener(new ZoneSpinnerOnTouchListener());
        mZoneSpinner.setOnItemSelectedListener(new ZoneSpinnerOnSelectedListener());
        mZoneSpinner.setOnTouchListener(onTouchListener);

        mRoadAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item, roadStrings);
        mRoadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRoadSpinner.setAdapter(mRoadAdapter);
        mRoadSpinner.setOnTouchListener(onTouchListener);
        
        if (provinceStrings.size() > 0) {
            if (tmpAddress.province == null) {
                setSelection(mProvinceSpinner, provinceStrings, new BeanID(11, "浙江省"));// 默认
                                                                                       // 浙江
            } else {
                setSelection(mProvinceSpinner, provinceStrings, tmpAddress.province);
                //province = null;
            }
        }

        Button okButton = (Button) selectDialog.findViewById(R.id.ok_button);
        okButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                processConfirm();
            }
        });
        Button cancelButton = (Button) selectDialog.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();//隐藏对话框 
            }
        });

        // 重置global列表
        resetGlobalList();
    }

    /**
     * 
     */
    private void resetGlobalList() {

        // 初始化global列表
        globalList.clear();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("addressList", provinceList);
        hashMap.put("adapter", mProvinceAdapter);
        hashMap.put("initAddress", tmpAddress.province);
        hashMap.put("spinner", mProvinceSpinner);
        hashMap.put("valueList", provinceStrings);
        globalList.add(hashMap);
        hashMap = new HashMap<String, Object>();
        hashMap.put("addressList", cityList);
        hashMap.put("adapter", mCityAdapter);
        hashMap.put("initAddress", tmpAddress.city);
        hashMap.put("spinner", mCitySpinner);
        hashMap.put("valueList", cityStrings);
        globalList.add(hashMap);
        hashMap = new HashMap<String, Object>();
        hashMap.put("addressList", districtList);
        hashMap.put("adapter", mDistrictAdapter);
        hashMap.put("initAddress", tmpAddress.district);
        hashMap.put("spinner", mDistrictSpinner);
        hashMap.put("valueList", districtStrings);
        globalList.add(hashMap);
        hashMap = new HashMap<String, Object>();
        hashMap.put("addressList", streetList);
        hashMap.put("adapter", mStreetAdapter);
        hashMap.put("initAddress", tmpAddress.street);
        hashMap.put("spinner", mStreetSpinner);
        hashMap.put("valueList", streetStrings);
        globalList.add(hashMap);
        hashMap = new HashMap<String, Object>();
        hashMap.put("addressList", zoneList);
        hashMap.put("adapter", mZoneAdapter);
        hashMap.put("initAddress", tmpAddress.zone);
        hashMap.put("spinner", mZoneSpinner);
        hashMap.put("valueList", zoneStrings);
        globalList.add(hashMap);
        hashMap = new HashMap<String, Object>();
        hashMap.put("addressList", roadList);
        hashMap.put("adapter", mRoadAdapter);
        hashMap.put("initAddress", tmpAddress.road);
        hashMap.put("spinner", mRoadSpinner);
        hashMap.put("valueList", roadStrings);
        globalList.add(hashMap);
    }

    /**
     * 
     */
    protected void processConfirm() {
        if (provinceList.isEmpty()) {
            Toast.makeText(mContext, "地址未填写完整。", Toast.LENGTH_SHORT).show();
            return;
        }

        clearValue();

        int index = mProvinceSpinner.getSelectedItemPosition();
        tmpAddress.province = new BeanID(provinceList.get(index).getCode(), provinceList.get(index)
                .getValue());
        if (mCitySpinner.getChildCount() > 0) {
            index = mCitySpinner.getSelectedItemPosition();
            tmpAddress.city = new BeanID(cityList.get(index).getCode(), cityList.get(index)
                    .getValue());
        }
        if (mStreetSpinner.getChildCount() > 0) {
            index = mStreetSpinner.getSelectedItemPosition();
            tmpAddress.street = new BeanID(streetList.get(index).getCode(), streetList.get(index)
                    .getValue());
        }
        if (mDistrictSpinner.getChildCount() > 0) {
            index = mDistrictSpinner.getSelectedItemPosition();
            tmpAddress.district = new BeanID(districtList.get(index).getCode(), districtList.get(
                    index).getValue());
        }
        if (mZoneSpinner.getChildCount() > 0) {
            index = mZoneSpinner.getSelectedItemPosition();
            tmpAddress.zone = new BeanID(zoneList.get(index).getCode(), zoneList.get(index)
                    .getValue());
        }
        if (mRoadSpinner.getChildCount() > 0) {
            index = mRoadSpinner.getSelectedItemPosition();
            tmpAddress.road = new BeanID(roadList.get(index).getCode(), roadList.get(index)
                    .getValue());
        }
        tmpAddress.n = mNEdit.getText().toString();
        tmpAddress.h = mHEdit.getText().toString();
        tmpAddress.s = mSEdit.getText().toString();
        tmpAddress.other = otherEditText.getText().toString();
        address = AddressTextFactory.formatAddress(tmpAddress);
        memAddressCopy(tmpAddress, rawAddress); // 更新地址
        AddressText.this.setText(address); // 刷新显示

        selectDialog.dismiss();// 隐藏对话框
    }

    private OnTouchListener onTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            clearValue();
            resetGlobalList(); // 重置列表，主要是为了清空临时的地址变量
            return false;
        }
    };

    /**
     * @param i
     * @return
     */
    private void setOther(int index) {
        StringBuilder sb = new StringBuilder();
        switch (index) {
            case 1:
                if (tmpAddress.city != null
                        && !StringUtil.isEmptyString(tmpAddress.city.getTagValue())) {
                    sb.append(tmpAddress.city.getTagValue());
                }
            case 2:
                if (tmpAddress.district != null
                        && !StringUtil.isEmptyString(tmpAddress.district.getTagValue())) {
                    sb.append(tmpAddress.district.getTagValue());
                }
            case 3:
                if (tmpAddress.street != null
                        && !StringUtil.isEmptyString(tmpAddress.street.getTagValue())) {
                    sb.append(tmpAddress.street.getTagValue());
                }
            case 4:
                if (tmpAddress.zone != null
                        && !StringUtil.isEmptyString(tmpAddress.zone.getTagValue())) {
                    sb.append(tmpAddress.zone.getTagValue());
                }
            case 5:
                if (tmpAddress.road != null
                        && !StringUtil.isEmptyString(tmpAddress.road.getTagValue())) {
                    sb.append(tmpAddress.road.getTagValue());
                }
        }
        if (!StringUtil.isEmptyString(tmpAddress.n)) {
            sb.append(tmpAddress.n + "弄（幢）");
        }

        if (!StringUtil.isEmptyString(tmpAddress.h)) {
            sb.append(tmpAddress.h + "号");
        }

        if (!StringUtil.isEmptyString(tmpAddress.s)) {
            sb.append(tmpAddress.s + "室");
        }
        otherEditText.setText(sb.toString());
        mNEdit.setText("");
        mHEdit.setText("");
        mSEdit.setText("");
    }

    // 监听与省Spinner下拉列表
    class ProvinceSpinnerOnSelectedListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            Log.i(TAG, "ProvinceSpinnerOnSelectedListener - onItemSelected");
            Address selectAddress = provinceList.get(position);
            updateSpinnerList(selectAddress, 1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    // 监听与城市Spinner下拉列表
    class CitySpinnerOnSelectedListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            Log.i(TAG, "CitySpinnerOnSelectedListener - onItemSelected");
            Address selectAddress = cityList.get(position);
            updateSpinnerList(selectAddress, 2);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

    // 监听与社区Spinner下拉列表
    class DistrictSpinnerOnSelectedListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            Log.i(TAG, "DistrictSpinnerOnSelectedListener - onItemSelected");
            Address selectAddress = districtList.get(position);
            updateSpinnerList(selectAddress, 3);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

    // 监听与街道Spinner下拉列表
    class StreetSpinnerOnSelectedListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            Log.i(TAG, "StreetSpinnerOnSelectedListener - onItemSelected");
            Address selectAddress = streetList.get(position);
            updateSpinnerList(selectAddress, 4);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    // 监听与ZoneSpinner下拉列表
    class ZoneSpinnerOnSelectedListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            Log.i(TAG, "ZoneSpinnerOnSelectedListener - onItemSelected");
            Address selectAddress = zoneList.get(position);
            updateSpinnerList(selectAddress, 5);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    /**
     * @param otherName
     */
    public void setOther(String otherName) {
        rawAddress.other = otherName;
    }
    
    private void setSelection(Spinner spinner, List<String> list, BeanID bean) {
        if (bean == null) {
            spinner.setSelection(0);
        }
        for (int i = 0; i< list.size(); i++) {
            if (bean.getTagValue().equals(list.get(i))) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    public void clearValue() {
        tmpAddress.province = null;
        tmpAddress.city = null;
        tmpAddress.district = null;
        tmpAddress.street = null;
        tmpAddress.zone = null;
        tmpAddress.road = null;
        tmpAddress.n = null;
        tmpAddress.h = null;
        tmpAddress.s = null;
        tmpAddress.other = null;
    }

    // List<List<Address>> addressList = new ArrayList<List<Address>>();
    // List<List<String>> stringList = new ArrayList<List<String>>();
    // List<ArrayAdapter<String>> adapterList = new
    // ArrayList<ArrayAdapter<String>>();
    // List<String> valueList = new ArrayList<String>();

    private void updateSpinnerList(Address selectAddress, int index) {
        Log.i(TAG, "updateSpinnerList, index = " + index);

        if (index < 0 || index >= globalList.size()) {
            return;
        }

        HashMap<String, Object> hashMap = globalList.get(index);
        List<Address> addressList = (List<Address>) hashMap.get("addressList");
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) hashMap.get("adapter");
        BeanID initAddress = (BeanID) hashMap.get("initAddress");
        Spinner spinner = (Spinner) hashMap.get("spinner");
        List<String> valueList = (List<String>) hashMap.get("valueList");

        if (!StringUtil.isEmptyString(tmpAddress.other)
                && (initAddress == null || StringUtil.isEmptyString(initAddress.getTagValue()))) {
            return;
        }

        // 清空后面的数据
        int nextIndex = index;
        while (++nextIndex < globalList.size()) {
            HashMap<String, Object> nextHashMap = globalList.get(nextIndex);
            List<String> nextValueList = (List<String>) nextHashMap.get("valueList");
            ArrayAdapter<String> nextAdapter = (ArrayAdapter<String>) nextHashMap.get("adapter");
            nextValueList.clear();
            nextAdapter.notifyDataSetChanged();
        }

        // 从数据库获取列表
        setAddressNameList(valueList, index, selectAddress, addressList);
        adapter.notifyDataSetChanged();

        if (valueList.size() > 0) {
            int position = -1;
            if (initAddress == null || initAddress.equals("")) {
                position = 0;
            } else {
                position = StringUtil.indexOf(valueList, initAddress.getTagValue());
            }
            if (position >= 0) {
                spinner.setSelection(position);
                Address nextSelectAddress = addressList.get(position);
                updateSpinnerList(nextSelectAddress, index + 1);
            } else {
                valueList.clear();
                adapter.notifyDataSetChanged();
                setOther(index);
            }
        }
    }

    /**
     * @param valueList
     * @param index
     * @param selectAddress
     */
    private void setAddressNameList(List<String> valueList, int index, Address selectAddress,
            List<Address> addressList) {
        List<Address> tmpAddressList = AddressTextFactory.getAddressList(index,
                selectAddress.getCode());
        addressList.clear();
        addressList.addAll(tmpAddressList);
        valueList.clear();
        for (Address address : addressList) {
            valueList.add(address.getValue());
        }
    }

    /**
     * @param memAddress
     */
    public void setValue(MemAddress memAddress) {
        String address = AddressTextFactory.formatAddress(memAddress);
        setText(address);
        rawAddress = memAddress;
    }

    /**
     * @return
     */
    public MemAddress getValue() {
        if (rawAddress == null) {
            rawAddress = AddressTextFactory.parseAddress(address);
        }
        return rawAddress;
    }
}
