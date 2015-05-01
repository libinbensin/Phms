package com.cking.phss.bluetooth.lifesense.ble.ui;

import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.GET_DATA_DIALOG_MESSAGE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.GET_DATA_DIALOG_TITLE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PAIR_DEVICE_DIALOG_MESSAGE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PAIR_DEVICE_DIALOG_TITLE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_EXECUTE_ABNORMAL;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_TITLE_WARNING;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lifesense.ble.bean.BloodPressureData;
import lifesense.ble.bean.GlucoseData;
import lifesense.ble.bean.GlucoseDeviceInfo;
import lifesense.ble.bean.HeightData;
import lifesense.ble.bean.LSDeviceInfo;
import lifesense.ble.bean.PedometerData;
import lifesense.ble.bean.PedometerUserInfo;
import lifesense.ble.bean.WeightData;
import lifesense.ble.bean.WeightUserInfo;
import lifesense.ble.commom.BleDeviceManager;
import lifesense.ble.commom.DeviceManagerCallback;
import lifesense.ble.commom.DeviceType;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.cking.phss.R;
import com.cking.phss.global.Global;

public class TestMainActivity extends Activity {
    private String TAG = "TestMainActivity";
	private ProgressDialog ringProgressDialog;
	private Intent mIntent;
	private BleDevice intentDevice;
    // private TextView showTextView;
	private BleDeviceManager bleDeviceManager;
	private LSDeviceInfo mDevice;
	private GlucoseDeviceInfo glucoseDevice;
	private ContentResolver contentResolver;
	private ContentValues contentValues;
	private TypeConversion typeConversion;
	private LSDeviceInfo pairedDevice;
	private boolean pairedDeviceFlags=false;
	private int weightRecordNumber,heightRecordNumber;
	private int bpRecordNumber,pedometerRecordNumber;

	protected String selected;
	protected PedometerUserInfo pedometer;
	protected WeightUserInfo weight;
	private LSDeviceInfo currentDevice;

    List<WeightData> wDataList = new ArrayList<WeightData>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        mIntent = getIntent();
        if (mIntent.hasExtra("object")) {
            intentDevice = mIntent.getParcelableExtra("object");
            getDeviceInfoByName(intentDevice.getName());
            showDeviceInfo(intentDevice.getKey_id());
        } else if (mIntent.hasExtra("pairedDeviceInfo")) {
            mDevice = AddDeviceActivity.pairedDeviceInfo;
            showDeviceInfo(mDevice);
        }

        // 如果已配对
        // 隐藏窗口
        if (!pairedDeviceFlags) {
            setContentView(R.layout.activity_test_main);
        } else {
            setContentView(R.layout.activity_test_main_hidden);
        }

		LocalLanguageCenter.initTestMainActivityAttribute(getApplicationContext());
		
		typeConversion=new TypeConversion();

        // showTextView = (TextView) findViewById(R.id.textView);
        // // showTextView.setMovementMethod(new ScrollingMovementMethod());

        contentResolver = this.getContentResolver();
        contentValues = new ContentValues();

        bleDeviceManager = BleDeviceManager.getInstance();
        bleDeviceManager.setCallback(managerCallback);

        findViewById(R.id.tStartScanBtn).setEnabled(false);
        findViewById(R.id.tStopScanBtn).setEnabled(false);

		initMeasureRecordSign();
	}


	private void initMeasureRecordSign() 
	{
		weightRecordNumber=0;
		heightRecordNumber=0;
		bpRecordNumber=0;
		pedometerRecordNumber=0;
        Global.lxReturnHashMap.clear();
	}


	@Override
	public void onResume()
	{
		super.onResume();
		initMeasureRecordSign();
		LocalLanguageCenter.initTestMainActivityAttribute(getApplicationContext());

        if (pairedDeviceFlags) { // 如果已配对
            // 直接读数据
            if (bleDeviceManager != null && mDevice != null) {
                getLifesenseDeviceData();
            } else if (bleDeviceManager != null && glucoseDevice != null) {
                getGlucoseDeviceData();
            }
        }
	}
	
	public void doClick(View view){
        switch (view.getId()) {
            case R.id.backBtn: {
                finish();
                break;
            }
            case R.id.setTypeBtn: {
                setUserInfo();
                break;
            }
            case R.id.tPairedBtn: {
                if (bleDeviceManager != null) {
                    showProgressDialog(PAIR_DEVICE_DIALOG_TITLE, PAIR_DEVICE_DIALOG_MESSAGE);
                    if (!bleDeviceManager.toPairDevice(mDevice)) {
                        ringProgressDialog.dismiss();
                        showPromptDialog(PROMPT_INFO_TITLE_WARNING, PROMPT_INFO_EXECUTE_ABNORMAL);
                    }

                }
                break;
            }

            case R.id.tGetDataBtn: {
                if (bleDeviceManager != null && mDevice != null) {
                    getLifesenseDeviceData();
                } else if (bleDeviceManager != null && glucoseDevice != null) {
                    getGlucoseDeviceData();
                }
                break;
            }
        }
	}

	private void getDeviceInfoByName(String name) {
		if(name.length()>=9 && name.substring(0, 9).equals("BeneCheck"))
		{
			glucoseDevice=new GlucoseDeviceInfo();
			glucoseDevice.setDeviceName(intentDevice.getName());
			//			glucoseDevice.setDeviceType(DeviceType.GLUCOSE_METER);
			glucoseDevice.setDeviceAddress(intentDevice.getAddress());
		}
		else
		{
			mDevice=new LSDeviceInfo();
			mDevice.setDeviceName(intentDevice.getName());
			mDevice.setDeviceType(typeConversion.integerToEnum(intentDevice.getSensorType()));
			mDevice.setDeviceAddress(intentDevice.getAddress());
			mDevice.setModelNumber(intentDevice.getModelNumber());
		}

	}

    private void showProgressDialog(String title, String message) {
        ringProgressDialog = ProgressDialog.show(this, title, message, true);
        ringProgressDialog.setCancelable(true);
        ringProgressDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                bleDeviceManager.interruptCurrentTask();
                ringProgressDialog.dismiss();
            }
        });
    }



	private void getGlucoseDeviceData() 
	{
		showProgressDialog(GET_DATA_DIALOG_TITLE,GET_DATA_DIALOG_MESSAGE);
		if(!bleDeviceManager.getDeviceMeasurementData(glucoseDevice))
		{
			ringProgressDialog.dismiss();
			showPromptDialog(PROMPT_INFO_TITLE_WARNING,PROMPT_INFO_EXECUTE_ABNORMAL);
		}
	}


	private void getLifesenseDeviceData() 
	{
		showProgressDialog(GET_DATA_DIALOG_TITLE,GET_DATA_DIALOG_MESSAGE);
		if(pairedDeviceFlags)
		{
            weight = new WeightUserInfo();
            weight.setAge(Byte.valueOf("50"));
            weight.setAthleteActivityLevel(Short.valueOf("3"));
            weight.setGoalWeight(Float.valueOf("56"));
            weight.setHeight(Float.valueOf("185"));
            weight.setSex(Byte.valueOf("1"));
            weight.setWaistline(Float.valueOf("38"));

            bleDeviceManager.setUserInfo(weight);
			boolean flag=bleDeviceManager.getDeviceMeasurementData(currentDevice);
			if(!flag)
			{
				ringProgressDialog.dismiss();
				showPromptDialog(PROMPT_INFO_TITLE_WARNING,PROMPT_INFO_EXECUTE_ABNORMAL);
			}
		}
		else
		{
			boolean flag=bleDeviceManager.getDeviceMeasurementData(mDevice);
			if(!flag)
			{
				ringProgressDialog.dismiss();
				showPromptDialog(PROMPT_INFO_TITLE_WARNING,PROMPT_INFO_EXECUTE_ABNORMAL);
			}
		}

	}


	private void showPromptDialog(String title,String message)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(TestMainActivity.this)
		.setTitle(title)
		.setPositiveButton("OK", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub					
			}

		})
		.setMessage(message);
		builder.create().show();  
	}

    private void setUserInfo() {
        if (mDevice != null && mDevice.getDeviceType().equals(DeviceType.PEDOMETER)
                || mDevice.getDeviceType().equals(DeviceType.WEIGHT_SCALE)) {
            showUserInfoDialog();
        } else {
            showPromptDialog("Prompt", "Device type is not supported");
        }

    }

    private void showUserInfoDialog() {
        final CharSequence[] array = { "Weight Scale", "Pedometer" };
        AlertDialog.Builder builder = new AlertDialog.Builder(TestMainActivity.this)
                .setTitle("Please select the type of device")
                .setSingleChoiceItems(array, 2, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        selected = array[arg1].toString();
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selected != null && selected.equals("Weight Scale")
                                && bleDeviceManager != null) {
                            System.out.println("is selected:" + selected);
                            weight = new WeightUserInfo();
                            weight.setAge(Byte.valueOf("50"));
                            weight.setAthleteActivityLevel(Short.valueOf("3"));
                            weight.setGoalWeight(Float.valueOf("56"));
                            weight.setHeight(Float.valueOf("185"));
                            weight.setSex(Byte.valueOf("1"));
                            weight.setWaistline(Float.valueOf("38"));

                            bleDeviceManager.setUserInfo(weight);
                        } else if (selected != null && selected.equals("Pedometer")
                                && bleDeviceManager != null) {
                            System.out.println("is selected:" + selected);
                            pedometer = new PedometerUserInfo();
                            pedometer.setHeight(0);
                            pedometer.setWeekStart(0);
                            pedometer.setWeekTargetCalories(0);
                            pedometer.setWeekTargetDistance(0);
                            pedometer.setWeekTargetExerciseAmount(0);
                            pedometer.setWeekTargetSteps(0);
                            pedometer.setWeight(0);
                            pedometer.setWeightUnit("kg");
                            pedometer.setStride(0);

                            bleDeviceManager.serUerInfo(pedometer);

                        }
                    }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
        builder.create().show();

	}




	//从数据库中读取数据
	private void showDeviceInfo(int key_id) 
	{	
		if(key_id!=-1)
		{
			currentDevice = new LSDeviceInfo();
            Uri uri = Uri.parse("content://com.cking.phss.dataBase/deviceitems/" + key_id);
			final String[] projection = new String[] 
					{ 	
					"_id",
					"device_name", 
					"device_address",
					"device_type",
					"device_id",
					"device_sn",
					"device_pairflags",
					"device_modelnumber",
					"device_password",
					"device_brocastID",
					"software_version",
					"hardware_version",
					"firmware_version",
					"manufacture_name",
					"device_systemID"};

			Cursor cursor= contentResolver.query(uri, projection, null, null, null);

			//		int key_id=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_ID);	
			int addressIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_ADDRESS);	
			int nameIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_NAME);
			int brocastIDIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_BROCASTID);
			int firmwareIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_FIRMWARE_VERSION);
			int hardwareIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_HARDWARE_VERSION);
			int deviceIDIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_ID);
			int manufactureNameIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_MANUFACTURENAME);
			int modelNumberIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_MODELNUMBER);
			int pairFlagsIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_PAIRFLAGS);
			int passwordIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_PASSWORD);
			int deviceSNIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_SN);
			int softwareIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_SOFTWARE_VERSION);
			int systemIDIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_SYSTEMID);
			int deviceTypeIndex=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_DEVICE_TYPE);

			while(cursor.moveToNext())
			{

				//tempUser.setId(cursor.getString(0));
				String id=cursor.getString(cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_ID));
				currentDevice.setDeviceName(cursor.getString(nameIndex));
				currentDevice.setDeviceAddress(cursor.getString(addressIndex));
				currentDevice.setDeviceType(typeConversion.integerToEnum(cursor.getInt(deviceTypeIndex)));
				currentDevice.setBroadcastID(cursor.getString(brocastIDIndex));
				currentDevice.setPassword(cursor.getBlob(passwordIndex));
				currentDevice.setManufactureName(cursor.getString(manufactureNameIndex));
				currentDevice.setModelNumber(cursor.getString(modelNumberIndex));
				currentDevice.setDeviceId(cursor.getString(deviceIDIndex));
				currentDevice.setDeviceSn(cursor.getString(deviceSNIndex));
				currentDevice.setSystemId(cursor.getString(systemIDIndex));
				currentDevice.setFirmwareVersion(cursor.getString(firmwareIndex));
				currentDevice.setHardwareVersion(cursor.getString(hardwareIndex)); 
				currentDevice.setSoftwareVersion(cursor.getString(softwareIndex));


			}
			if(currentDevice!=null)
			{
				pairedDeviceFlags=true;

			}

		}

	}

    private void showDeviceInfo(LSDeviceInfo deviceInfo) {
        currentDevice = deviceInfo;
        if (currentDevice != null) {

            pairedDeviceFlags = true;

        }
    }

	private String byte2hex(byte[] data) {
		String   hs="";
		String   stmp="";
		for (int n=0;n<data.length;n++) 
		{
			stmp=(Integer.toHexString(data[n]&0XFF));
			if(stmp.length()==1) 
			{
				hs=hs+"0"+stmp;
			}
			else  
			{
				hs=hs+stmp;
			}

		}
		return   hs.toUpperCase();	 
	}

    Timer closeTimer = null;
	private void savePairedDeviceToDatabase(LSDeviceInfo device) 
	{
		contentValues.put(DeviceContentProvider.KEY_DEVICE_ADDRESS, device.getDeviceAddress());	
        contentValues.put(DeviceContentProvider.KEY_DEVICE_NAME, device.getDeviceName());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_BROCASTID, device.getBroadcastID());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_FIRMWARE_VERSION, device.getFirmwareVersion());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_HARDWARE_VERSION, device.getHardwareVersion());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_ID, device.getDeviceId());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_MANUFACTURENAME, device.getManufactureName());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_MODELNUMBER, device.getModelNumber());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_PASSWORD, device.getPassword());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_SN, device.getDeviceSn());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_SOFTWARE_VERSION, device.getSoftwareVersion());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_SYSTEMID, device.getSystemId());
		contentValues.put(DeviceContentProvider.KEY_DEVICE_TYPE,typeConversion.enumToInteger(device.getDeviceType()));

		//向数据库表插入新记录，指定一个访问该数据库的URI与插入对象实例
		contentResolver.insert(DeviceContentProvider.CONTENT_URI, contentValues);

	}

	private DeviceManagerCallback managerCallback=new DeviceManagerCallback()
	{
		@Override
		public void onPairedResults(final LSDeviceInfo lsDevice, final int state) {
			runOnUiThread(new Runnable() {


				@Override
				public void run() {

					if(lsDevice!=null&&state==0)
					{       
						pairedDevice=lsDevice;
                        String sensorName = lsDevice.getDeviceName();

                        // //
                        // showTextView.append("----------"+TMA_SHOW_PAIR_RESULTS_SUCCESS_TITLE+"------------"+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_NAME+": "+sensorName+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_ADDRESS+": "+lsDevice.getDeviceAddress()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_BROADCAST_ID+": "+lsDevice.getBroadcastID()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_TYPE+": "+lsDevice.getDeviceType().toString()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_PASSWORD+": "+lsDevice.getPassword()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_ID+": "+lsDevice.getDeviceId()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_SN+": "+lsDevice.getDeviceSn()+"\n");
                        //
                        // //
                        // showTextView.append(INFO_DEVICE_MANUFACTURE_NAME+": "+lsDevice.getManufactureName()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_MODEL_NUMBER+": "+lsDevice.getModelNumber()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_FIRMWARE_VERSION+": "+lsDevice.getFirmwareVersion()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_HARDMWARE_VERSION+": "+lsDevice.getHardwareVersion()+"\n");
                        // //
                        // showTextView.append(INFO_DEVICE_SOFTMWARE_VERSION+": "+lsDevice.getSoftwareVersion()+"\n");

						savePairedDeviceToDatabase(lsDevice);
						ringProgressDialog.dismiss();        		    
					}
					else
					{
						ringProgressDialog.dismiss();
                        // //
                        // showTextView.append(TMA_SHOW_PAIR_RESULTS_FAILURE_TITLE+"\n");
					}	 	        	   
				}


			});

		}

		@Override
		public void onReceiveWeightMeasurementData(final WeightData wData) 
		{
            if (wData != null) {
                weightRecordNumber++;
                // showTextView.append("\n");
                // showTextView.append("------------------------------"+"\n");
                // showTextView.append(MEASURE_DATA_RECORD+" : "+weightRecordNumber+"\n");
                // showTextView.append(MEASURE_DATA+"："+
                // wData.getDate()+"\n");
                // showTextView.append(INFO_DEVICE_ID+"："+
                // wData.getDeviceId()+"\n");
                // showTextView.append(USER_NUMBER+"："+
                // wData.getUserNo()+"\n");
                // showTextView.append(WSMA_WEIGHT_VALUE+"："+
                // wData.getWeight()+"\n");
                // showTextView.append(WSMA_FAT_VALUE+" ："+
                // wData.getPbf()+"\n");
                // showTextView.append(WSMA_RESISTOR1_VALUE +"："+
                // wData.getResistance_1()+"\n");
                // showTextView.append(WSMA_RESISTOR1_VALUE +"："+
                // wData.getResistance_2()+"\n");
                // showTextView.append(MEASURE_UNIT +"："+
                // wData.getDeviceSelectedUnit()+"\n");
                wDataList.add(wData); // 添加到列表
            } else {
                // ringProgressDialog.dismiss();
                // showTextView.append("No data, please try again"+"\n");
            }

            // 启动3秒监听器，3秒内无数据则关闭
            // 3秒内有数据则重启定时器
            if (closeTimer != null) {
                closeTimer.cancel();
                closeTimer = null;
            }
            closeTimer = new Timer();
            closeTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ringProgressDialog.dismiss();
                        }
                    });
                    Log.i(TAG, "closeTimer - run");
                    // 添加到哈希表
                    Global.lxReturnHashMap.put("WeightDataList", wDataList);
                    finish();
                }
            }, 3000);
		}

		@Override
		public void onReceivePedometerMeasurementData(final PedometerData pData) 
		{		
			runOnUiThread(new Runnable() 
			{

				@Override
				public void run() 
				{
					if(pData!=null)
					{
						pedometerRecordNumber++;
						ringProgressDialog.dismiss();
                        // showTextView.append("\n");
                        // showTextView.append("------------------------------"+"\n");
                        // showTextView.append(MEASURE_DATA_RECORD+" : "+pedometerRecordNumber+"\n");
					
                        // showTextView.append(MEASURE_DATA+"："+
                        // pData.getDate()+"\n");
                        // showTextView.append(INFO_DEVICE_ID+"："+
                        // pData.getDeviceId()+"\n");
                        // showTextView.append(USER_NUMBER+"："+
                        // pData.getUserNo()+"\n");
                        // showTextView.append(PMA_WALK_STEPS+"："+
                        // pData.getWalkSteps()+"\n");
                        // showTextView.append(PMA_RUNNING_STEPS+"："+
                        // pData.getRunSteps()+"\n");
                        // showTextView.append(PMA_CALORIES+"："+
                        // pData.getCalories()+"\n");
                        // showTextView.append(PMA_EXERCISE_TIME +"："+
                        // pData.getExerciseTime()+"\n");
                        // showTextView.append(PMA_DISTANCE +"："+
                        // pData.getDistance()+"\n");
                        // showTextView.append(PMA_BATTERY+"："+
                        // pData.getBattery()+"\n");
                        // showTextView.append(PMA_SLEEP_STATE +"："+
                        // pData.getSleepStatus()+"\n");
                        // showTextView.append(PMA_SHAKE_LEVEL+"："+
                        // pData.getIntensityLevel()+"\n");
					}
					else
					{
						ringProgressDialog.dismiss();
                        // showTextView.append("No data, please try again"+"\n");
					}
				}

			});

		}

		@Override
		public void onReceiveBloodPressureMeasurementData(
				final BloodPressureData bData) {
			runOnUiThread(new Runnable() 
			{		
				@Override
				public void run() 
				{
					if(bData!=null)
					{	 
						bpRecordNumber++;
						ringProgressDialog.dismiss();
                        // showTextView.append("\n");
                        // showTextView.append("------------------------------"+"\n");
						
                        // showTextView.append(MEASURE_DATA_RECORD+" : "+bpRecordNumber+"\n");
                        // showTextView.append(MEASURE_DATA+"："+
                        // bData.getDate()+"\n");
                        // showTextView.append(INFO_DEVICE_ID+"："+
                        // bData.getDeviceId()+"\n");
                        // showTextView.append(USER_NUMBER+"："+
                        // bData.getUserNo()+"\n");
                        // showTextView.append(SMA_SBP+"："+
                        // bData.getSystolic()+"\n");
                        // showTextView.append(SMA_DIASTOLIC_BLOOD_PRESSURE+"："+
                        // bData.getDiastolic()+"\n");
                        // showTextView.append(SMA_HEART_RATE+"："+
                        // bData.getPluseRate()+"\n");
                        // showTextView.append(SMA_MEAN_BLOOD_PRESSURE+"："+
                        // bData.getMeanArterialPressure()+"\n");
                        // showTextView.append(SMA_ARRHYTHMIA+"："+
                        // bData.isIrregularPulse()+"\n");
                        // showTextView.append(MEASURE_UNIT+"："+
                        // bData.getDeviceSelectedUnit()+"\n");
                        // showTextView.append(DEVICE_BATTERY+"："+
                        // bData.getBattery()+"\n");

					}
					else
					{
						ringProgressDialog.dismiss();
                        // showTextView.append("No data, please try again"+"\n");
					}
				}

			});

		}

		

		@Override
		public void onReceiveHeightMeasurementData(final HeightData hData) 
		{
			runOnUiThread(new Runnable() 
			{		
				@Override
				public void run() 
				{
					if(hData!=null)
					{	    
						heightRecordNumber++;
						ringProgressDialog.dismiss();
                        // showTextView.append("\n");
                        // showTextView.append("------------------------------"+"\n");
						
                        // showTextView.append(MEASURE_DATA_RECORD+" : "+heightRecordNumber+"\n");
                        // showTextView.append(MEASURE_DATA+"："+
                        // hData.getDate()+"\n");
                        // showTextView.append(INFO_DEVICE_ID+"："+
                        // hData.getDeviceId()+"\n");
                        // showTextView.append(USER_NUMBER+"："+
                        // hData.getUserNo()+"\n");
                        // showTextView.append(HMA_HEIGHT_VALUE+"："+
                        // hData.getHeight()+"\n");
                        // showTextView.append(MEASURE_UNIT+"："+
                        // hData.getUnit()+"\n");
                        // showTextView.append(DEVICE_BATTERY+"："+
                        // hData.getBattery()+"\n");

					}
					else
					{
						ringProgressDialog.dismiss();
                        // showTextView.append("No data, please try again"+"\n");
					}
				}

			});

		}

		@Override
		public void onReceiveDataFinish(final int state) 
		{
			System.out.println("reveive: "+state);
			runOnUiThread(new Runnable()
			{

				@Override
				public void run() 
				{
					if(state==0)
					{
						ringProgressDialog.dismiss();
                        // showTextView.append("\n");
                        // showTextView.append("***"+MEASURE_DATA_UPLOAD_SUCCESS+"***"+"\n");

                        // 添加到哈希表
                        Global.lxReturnHashMap.put("WeightDataList", wDataList);
					}
					else if(state==-1)
					{

						ringProgressDialog.dismiss();
                        // showTextView.append("\n");
                        // showTextView.append("***"+MEASURE_DATA_UPLOAD_FAILURE+"***"+"\n");
					}

                    // 返回到测量界面
                    finish();
				}

			});


		}
		public void onGlucoseDeviceInfo(final GlucoseDeviceInfo device) 
		{
			runOnUiThread(new Runnable() 
			{

				@Override
				public void run() {
					ringProgressDialog.dismiss();
                    // showTextView.append("Blood glucose meter device information"+"\n");
                    // showTextView.append("Device Name: "+device.getDeviceName()+"\n");
                    // showTextView.append("Device Address: "+device.getDeviceAddress()+"\n");
                    // //
                    // showTextView.append("Device BroadcastID: "+device.getBroadcastID()+"\n");
                    // showTextView.append("Device Type: "+device.getDeviceType()+"\n");
                    // //
                    // showTextView.append("Device Password: "+byte2hex(device.password)+"\n");
                    // showTextView.append("Device ID: "+device.getDeviceId()+"\n");
                    // showTextView.append("Device SN: "+device.getDeviceSn()+"\n");
                    // showTextView.append("Device SystemId: "+device.getSystemId()+"\n");
                    // showTextView.append("Device ManufactureName: "+device.getManufactureName()+"\n");
                    // showTextView.append("Device ModelNumber: "+device.getModelNumber()+"\n");
                    // showTextView.append("Device FirmwareVersion: "+device.getFirmwareVersion()+"\n");
                    // showTextView.append("Device HardwareVersion: "+device.getHardwareVersion()+"\n");
                    // showTextView.append("Device SoftwareVersion: "+device.getSoftwareVersion()+"\n");

				}

			});


		}

		@Override
		public void onReceiveGlucoseMeasurementData(final GlucoseData gData) 
		{
			runOnUiThread(new Runnable() 
			{
				@Override
				public void run() {
					ringProgressDialog.dismiss();
                    // showTextView.append("Measurement data uploaded successfully"+"\n");
                    // showTextView.append(gData.toString());

				}

			});
		}


	};
}

