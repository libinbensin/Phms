/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * AddressTextFactory.java
 * classes : com.cking.phss.util.AddressTextFactory
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-17 下午4:20:49
 */
package com.cking.phss.util;

import java.util.List;

import net.xinhuaxing.util.StringUtil;

import com.cking.phss.bean.BeanID;
import com.cking.phss.sqlite4address.Address;
import com.cking.phss.sqlite4address.SqliteDataController;
import com.cking.phss.widget.AddressText.MemAddress;

/**
 * com.cking.phss.util.AddressTextFactory
 * 
 * @author Administrator <br/>
 *         create at 2014-7-17 下午4:20:49
 */
public class AddressTextFactory {
    private static final String TAG = "AddressTextFactory";

    public static String formatAddress(MemAddress memAdress) {
        StringBuilder sb = new StringBuilder();
        if (memAdress.province != null && memAdress.province.getTagValue() != null) {
            sb.append(memAdress.province.getTagValue());
        }

        if (memAdress.city != null && memAdress.city.getTagValue() != null) {
            sb.append(memAdress.city.getTagValue());
        }

        if (memAdress.district != null && memAdress.district.getTagValue() != null) {
            sb.append(memAdress.district.getTagValue());
        }

        if (memAdress.street != null && memAdress.street.getTagValue() != null) {
            sb.append(memAdress.street.getTagValue());
        }

        if (memAdress.zone != null && memAdress.zone.getTagValue() != null) {
            sb.append(memAdress.zone.getTagValue());
        }

        if (memAdress.road != null && memAdress.road.getTagValue() != null) {
            sb.append(memAdress.road.getTagValue());
        }

        if (memAdress.n != null && !memAdress.n.equals("")) {
            sb.append(memAdress.n + "弄（幢）");
        }

        if (memAdress.h != null && !memAdress.h.equals("")) {
            sb.append(memAdress.h + "号");
        }

        if (memAdress.s != null && !memAdress.s.equals("")) {
            sb.append(memAdress.s + "室");
        }

        if (memAdress.other != null && !memAdress.other.equals("")) {
            sb.append(memAdress.other);
        }
        return sb.toString();
    }

    public static MemAddress parseAddress(String address) {
        MemAddress memAddress = new MemAddress();

        BeanID[] beanIdList = new BeanID[6];

        String restAddress = parseBeanIdAddress(beanIdList, address, 0, "01");
        memAddress.province = beanIdList[0];
        memAddress.city = beanIdList[1];
        memAddress.district = beanIdList[2];
        memAddress.street = beanIdList[3];
        memAddress.zone = beanIdList[4];
        memAddress.road = beanIdList[5];

        // 如果有没有解析的字段则剩余地址放到other中
        for (BeanID bean : beanIdList) {
            if (bean == null) { // 找到
                memAddress.other = restAddress;
                break;
            }
        }

        // 否则解析弄号室
        // 形如 乱七八糟3号bac => 全部到other
        if (StringUtil.isEmptyString(memAddress.other)) {
            String split = "弄（幢）";
            String[] items = restAddress.split(split);
            if (items != null && items.length > 1) {
                memAddress.n = items[0];
                restAddress = items[1];
            } else {
                memAddress.other = restAddress;
            }
        }
        if (StringUtil.isEmptyString(memAddress.other)) {
            String split = "号";
            String[] items = restAddress.split(split);
            if (items != null && items.length > 1) {
                memAddress.h = items[0];
                restAddress = items[1];
            } else {
                memAddress.other = restAddress;
            }
        }
        if (StringUtil.isEmptyString(memAddress.other)) {
            String split = "室";
            String[] items = restAddress.split(split);
            if (items != null && items.length > 0 && restAddress.endsWith("室")) {
                memAddress.s = items[0];
            } else {
                memAddress.other = restAddress;
            }
        }

        return memAddress;
    }

    public static String parseBeanIdAddress(BeanID[] addressBeans, String address, int index,
            String foreignKey) {
        if (index >= 6) { // 全部解析完毕
            return address;
        }
        List<Address> addressList = null;
        addressList = getAddressList(index, foreignKey);

        for (Address addr : addressList) {
            if (address.startsWith(addr.getValue())) { // 找到
                addressBeans[index] = new BeanID(addr.getCode(), addr.getValue());
                foreignKey = addr.getCode();
                address = address.substring(addr.getValue().length());
                return parseBeanIdAddress(addressBeans, address, index + 1, foreignKey);
            }
        }

        return address;
    }

    /**
     * 获取地址列表
     * 
     * @param index
     *            级别索引号，0 - 省， 1 - 市 以此类推
     * @return
     */
    public static List<Address> getAddressList(int index, String foreignKey) {
        List<Address> tmpAddressList = null;
        switch (index) {
            case 0:
                tmpAddressList = SqliteDataController.getProvinceList(foreignKey);
                break;
            case 1:
                tmpAddressList = SqliteDataController.getCityList(foreignKey);
                break;
            case 2:
                tmpAddressList = SqliteDataController.getDistrictList(foreignKey);
                break;
            case 3:
                tmpAddressList = SqliteDataController.getStreetList(foreignKey);
                break;
            case 4:
                tmpAddressList = SqliteDataController.getCommunityList(foreignKey);
                break;
            case 5:
                tmpAddressList = SqliteDataController.getRoadList(foreignKey);
                break;
        }
        return tmpAddressList;
    }
}
