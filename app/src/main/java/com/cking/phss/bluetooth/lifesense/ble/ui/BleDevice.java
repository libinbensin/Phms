package com.cking.phss.bluetooth.lifesense.ble.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class BleDevice implements Parcelable {
	public String name;
	public String address;
	public int sensorType;
	public String pairFlags;
	private int key_id;
	private int rssi;
	private String scanRecord;
	private String modelNumber;
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public String getPairFlags() {
		return pairFlags;
	}
	public void setPairFlags(String pairFlags) {
		this.pairFlags = pairFlags;
	}
	public synchronized int getSensorType() {
		return sensorType;
	}
	public synchronized void setSensorType(int sensorType) {
		this.sensorType = sensorType;
	}
	public BleDevice(String mname, String maddress,int type,String modelNo) {
		name=mname;
		address=maddress;
		sensorType=type;
		setModelNumber(modelNo);
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized String getAddress() {
		return address;
	}
	public synchronized void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(name);
		out.writeString(address);
		out.writeInt(sensorType);
		out.writeString(pairFlags);
		out.writeInt(key_id);
		out.writeInt(rssi);
		out.writeString(scanRecord);
		out.writeString(modelNumber);
	}
	//this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
	public static final Parcelable.Creator<BleDevice> CREATOR = new Parcelable.Creator<BleDevice>() {
	    public BleDevice createFromParcel(Parcel in) {
	        return new BleDevice(in);
	    }

	    public BleDevice[] newArray(int size) {
	        return new BleDevice[size];
	    }
	};

	// example constructor that takes a Parcel and gives you an object populated with it's values
	private BleDevice(Parcel in) {
	   name = in.readString();
	   address=in.readString();
	   sensorType=in.readInt();
	   pairFlags=in.readString();
	   key_id=in.readInt();
	   rssi=in.readInt();
	   scanRecord=in.readString();
	   modelNumber=in.readString();
	}
	public int getKey_id() {
		return key_id;
	}
	public void setKey_id(int key_id) {
		this.key_id = key_id;
	}
	public String getScanRecord() {
		return scanRecord;
	}
	public void setScanRecord(String scanRecord) {
		this.scanRecord = scanRecord;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

}
