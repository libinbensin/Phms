package com.cking.phss.bluetooth.lifesense.ble.ui;

import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.BROADCAST_TYPE_ALL;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.BROADCAST_TYPE_NORMAL;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.BROADCAST_TYPE_PAIR;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.CURRENT_BROADCAST_TYPE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.DEVICE_TYPE_FAT_SCALE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.DEVICE_TYPE_GLUCOSE_METER;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.DEVICE_TYPE_HEIGHT_RULER;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.DEVICE_TYPE_KITCHEN_SCALE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.DEVICE_TYPE_PEDOMETER;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.DEVICE_TYPE_SPHYGMOMETER;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.DEVICE_TYPE_WEIGHT_SCALE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PAIR_DEVICE_DIALOG_MESSAGE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PAIR_DEVICE_DIALOG_TITLE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_BLE_TURN_OFF;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_EXECUTE_ABNORMAL;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_LOW_ENERGY_NOT_SUPPORT;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_NO_SCAN_RESULTS;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_SCAN_DEFAULTS_MESSAGE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_TITLE_NORMAL;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_TITLE_WARNING;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_VERSION_NOT_SUPPORT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import lifesense.ble.bean.GlucoseDeviceInfo;
import lifesense.ble.bean.LSDeviceInfo;
import lifesense.ble.commom.BleDeviceManager;
import lifesense.ble.commom.BroadCastType;
import lifesense.ble.commom.DeviceManagerCallback;
import lifesense.ble.commom.DeviceType;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cking.phss.R;

@SuppressLint("NewApi")
public class AddDeviceActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
	private SharedPreferences prefs ;
	private ProgressDialog scanProgressDialog;		
	private ListView listview;
	private ArrayList<LSDeviceInfo> mDeviceInfoList;
	private BleDeviceManager bleDeviceManager;
    private String TAG = "AddDeviceActivity";
	private BleDeviceAdapter deviceListAdapter;
	protected LSDeviceInfo mDevice;
    public static LSDeviceInfo pairedDeviceInfo;
	private BroadCastType broadCastType;//默认情况下是全部类型
	private String broadCast;
	private StringBuilder typeBuilder;
	private Handler scanHandler;
	public static ArrayList<LSDeviceInfo> pairDeviceList;
	private Handler changeHanlder;
	private boolean scanResult=false;
	private TypeConversion typeConversion;
	private HashMap<String,Integer> broadcastIDMap;
	private ArrayList<LSDeviceInfo> tempList;

    private ContentResolver contentResolver;
    private ContentValues contentValues;

	private DeviceManagerCallback managerCallback=new DeviceManagerCallback()
	{
        @Override
        public void onPairedResults(final LSDeviceInfo lsDevice, final int state) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (lsDevice != null && state == 0) {
                        pairedDeviceInfo = lsDevice;

                        savePairedDeviceToDatabase(lsDevice);
                        ringProgressDialog.dismiss();

                        Intent intent = new Intent(AddDeviceActivity.this, TestMainActivity.class);
                        intent.putExtra("pairedDeviceInfo", 1);
                        startActivity(intent);

                        finish();
                    } else {
                        ringProgressDialog.dismiss();

                        Toast.makeText(AddDeviceActivity.this, "配对失败", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        }

		@Override
		public void onDiscoverDevice(final LSDeviceInfo lsDevice)
		{
			mDevice = lsDevice;
			runOnUiThread(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() 
				{   	   
					if(lsDevice!=null&&!deviceExists(lsDevice.getDeviceName()))
					{
						scanResult=true;
						scanProgressDialog.dismiss();
						tempList.add(lsDevice);
						deviceListAdapter.add(lsDevice);
						deviceListAdapter.notifyDataSetChanged();
						
					}
					else
					{
						updateListViewBackground(lsDevice.getDeviceName()); 
					}
				}           
			});
		}

		@Override
		public void onDiscoverBeneCheckDevice(final GlucoseDeviceInfo gDevice)
		{
		}

	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_display_devices);
		LocalLanguageCenter.initAddActivityAttribute(getApplicationContext());
		broadCast=BROADCAST_TYPE_ALL;
		listview = (ListView) findViewById(R.id.listview);
		mDeviceInfoList=new ArrayList<LSDeviceInfo>();
		typeConversion=new TypeConversion();
		changeHanlder=new Handler();
		typeBuilder= new StringBuilder();
		pairDeviceList=new ArrayList<LSDeviceInfo>();
		broadcastIDMap=new HashMap<String,Integer>();
		tempList=new ArrayList<LSDeviceInfo>();
		scanHandler=new Handler();
		typeBuilder.append(PROMPT_INFO_SCAN_DEFAULTS_MESSAGE);
		initListView();

        contentResolver = this.getContentResolver();
        contentValues = new ContentValues();

		PreferenceManager.setDefaultValues(this, R.xml.setting, false);
		prefs= PreferenceManager.getDefaultSharedPreferences(this);

		bleDeviceManager=BleDeviceManager.getInstance(); 
		boolean initFlags=  bleDeviceManager.initialize(getApplicationContext(),managerCallback);
		if(!initFlags)
		{
			showPromptDialog(PROMPT_INFO_TITLE_WARNING, PROMPT_INFO_VERSION_NOT_SUPPORT);
		}
		//初始化Loader
		this.getLoaderManager().initLoader(0, null, this);

	}
	
	@Override
    protected void onDestroy() {
        // bleDeviceManager.stopScanning();
        super.onDestroy();
    }

    @Override
	public void onResume()
	{
		super.onResume();
		bleDeviceManager.setCallback(managerCallback);
		LocalLanguageCenter.initAddActivityAttribute(getApplicationContext());
		this.getLoaderManager().restartLoader(0, null, this);
		
	}
	
	@Override
    protected void onPause() {
        bleDeviceManager.stopScanning();
        super.onPause();
    }

    @Override
	public void onBackPressed() {
	    super.onBackPressed();
        returnMainActivity();
	}
	
	@Override
	public void onActivityResult(int reqCode, int resCode, Intent data)
	{
		super.onActivityResult(reqCode, resCode, data);
		getScanConditionsSite();	    	
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		CursorLoader loader=new CursorLoader(this,DeviceContentProvider.CONTENT_URI,null,null,null,null);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) 
	{
		initDatabaseWithCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
	
	}
	
	public void doClick(View view) 
	{
		switch(view.getId()) 
		{
            case R.id.addActivity_backBtn: {
				returnMainActivity();}break;
			case R.id.scanBtn:
			{

			deviceListAdapter.clear();
			tempList.clear();
			if(bleDeviceManager!=null&&bleDeviceManager.isOpenBluetooth())
			{
				if(bleDeviceManager.isSupportBluetooth())
				{   

					if(!bleDeviceManager.startScanning())
					{
						showPromptDialog(PROMPT_INFO_TITLE_WARNING,PROMPT_INFO_EXECUTE_ABNORMAL);
					}
					else{
						showScanDialog();
					}
				}
				else{showPromptDialog(PROMPT_INFO_TITLE_NORMAL,PROMPT_INFO_LOW_ENERGY_NOT_SUPPORT);
				}

			}
			else
			{
				showPromptDialog(PROMPT_INFO_TITLE_NORMAL,PROMPT_INFO_BLE_TURN_OFF);
			}

		} break;
		case R.id.stopBtn:
		{	
			if(bleDeviceManager!=null)
			{
				bleDeviceManager.stopScanning();
			}
		}break;

		case R.id.settingBtn:
		{
			Intent intent = new Intent()
			.setClass(AddDeviceActivity.this, ConfigPreferenceActivity.class);
			startActivityForResult(intent,0);
			if(bleDeviceManager!=null)
			{
				bleDeviceManager.stopScanning();
			}
		}
		}
	}
	
	private void initListView()
	{
		deviceListAdapter=new BleDeviceAdapter(this.getApplicationContext(),mDeviceInfoList);
		listview.setAdapter(deviceListAdapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) 
			{

				if(bleDeviceManager!=null)
				{
					bleDeviceManager.stopScanning();
				}

                if (bleDeviceManager != null) {
                    showProgressDialog(PAIR_DEVICE_DIALOG_TITLE, PAIR_DEVICE_DIALOG_MESSAGE);
                    if (!bleDeviceManager.toPairDevice(mDevice)) {
                        ringProgressDialog.dismiss();
                        showPromptDialog(PROMPT_INFO_TITLE_WARNING, PROMPT_INFO_EXECUTE_ABNORMAL);
                    }

                }

			}
		});
		
	}

	private boolean deviceExists(String name) 
	{
		boolean found=false;
		for (int i = 0; i < tempList.size(); i++) 
		{
			if (tempList.get(i).getDeviceName().equals(name)) 
			{
				return found=true;
			}
		}
		return found;
	}

	

	private void showPromptDialog(String title,String message) 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(AddDeviceActivity.this)
		.setTitle(title)
		.setPositiveButton("OK",  new DialogInterface.OnClickListener()
		{	
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{  
				
			}

		})
		.setMessage(message);
		builder.create().show(); 
	}

	private void showScanDialog() 
	{
		String title=CURRENT_BROADCAST_TYPE+broadCast;
		scanProgressDialog = ProgressDialog.show(
				AddDeviceActivity.this, title, typeBuilder, true);
		scanProgressDialog.setCancelable(true);
		scanHandler.postDelayed(new Runnable() 
		{
			@Override
			public void run() 
			{
				scanProgressDialog.dismiss();
				if(!scanResult)
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(AddDeviceActivity.this)
					.setTitle(PROMPT_INFO_TITLE_NORMAL)
					.setPositiveButton("OK",  new DialogInterface.OnClickListener()
					{	
						@Override
						public void onClick(DialogInterface dialog, int which) 
						{
							bleDeviceManager.stopScanning();
							Intent intent = new Intent()
							.setClass(AddDeviceActivity.this, ConfigPreferenceActivity.class);
							startActivityForResult(intent,0);

						}

					})
					.setMessage(PROMPT_INFO_NO_SCAN_RESULTS);

					builder.create().show();            
				}         	  
			}
		}, 15000);
		scanResult=false;
	}

	private void returnMainActivity() 
	{
        finish();
        // Intent intent = new Intent(AddDeviceActivity.this,
        // MainActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // startActivity(intent);
		
	}

	

	
	private void getScanConditionsSite()
	{
		typeBuilder.delete(0, typeBuilder.length());//清空StringBuilder
		Set<String>options = prefs.getStringSet("device_Type", null);
		System.out.println("Multi Select: " + options);
		deleteDeviceTypeByNoChoose(options);
		setDeviceTypeByChoose(options);
		setBroadcastTypeByChoose(options);	
	}

	private void setBroadcastTypeByChoose(Set<String> options) 
	{
		//设置扫描的广播类型
		switch(ConfigPreferenceActivity.currentSet)
		{
		case 0:
		{
			broadCast = BROADCAST_TYPE_NORMAL;
			broadCastType=BroadCastType.NORMAL;
			bleDeviceManager.setScanBroadCastType(broadCastType);

		};break;
		case 1:
		{
			broadCast =BROADCAST_TYPE_PAIR;
			broadCastType=BroadCastType.PAIR;
			bleDeviceManager.setScanBroadCastType(broadCastType);

		};break;
		case 2:
		{
			broadCast = BROADCAST_TYPE_ALL;
			broadCastType=BroadCastType.ALL;
			bleDeviceManager.setScanBroadCastType(broadCastType);

		};break;
		} 		    	
	}

	private void setDeviceTypeByChoose(Set<String> options)
	{
		//添加选中的设备
		if(options != null )
		{
			for(String f: options)
			{ 
				String type=ConfigPreferenceActivity.device_type[Integer.parseInt(f)];
				if(type.equals("计步器"))
				{	
					typeBuilder.append(DEVICE_TYPE_PEDOMETER+",  ");
					bleDeviceManager.addScanDeviceType(DeviceType.PEDOMETER);
				}
				if(type.equals("体重秤"))
				{  		
					typeBuilder.append(DEVICE_TYPE_WEIGHT_SCALE+",  ");
					bleDeviceManager.addScanDeviceType(DeviceType.WEIGHT_SCALE);
				}
				if(type.equals("血压计"))
				{	
					typeBuilder.append(DEVICE_TYPE_SPHYGMOMETER+",  ");
					bleDeviceManager.addScanDeviceType(DeviceType.BLOOD_PRESSURE);
				}
				if(type.equals("厨房秤"))
				{
					typeBuilder.append(DEVICE_TYPE_KITCHEN_SCALE+",  ");
					bleDeviceManager.addScanDeviceType(DeviceType.KITCHEN_SCALE);
				}
				if(type.equals("脂肪秤"))
				{
					typeBuilder.append(DEVICE_TYPE_FAT_SCALE+",  ");	
					bleDeviceManager.addScanDeviceType(DeviceType.BODY_FAT_SCALE);
				}
				if(type.equals("身高尺"))
				{
					typeBuilder.append(DEVICE_TYPE_HEIGHT_RULER+",  ");    
					bleDeviceManager.addScanDeviceType(DeviceType.HEIGHT_SCALE);
				}
				if(type.equals("血糖仪"))
				{
					typeBuilder.append(DEVICE_TYPE_GLUCOSE_METER+",  ");    
					bleDeviceManager.addScanDeviceType(DeviceType.GLUCOSE_METER);
				}
			}
		} 
		if(options!=null && options.isEmpty())
		{
			typeBuilder.delete(0, typeBuilder.length());
			typeBuilder.append(PROMPT_INFO_SCAN_DEFAULTS_MESSAGE);
		}
	}

	private void deleteDeviceTypeByNoChoose(Set<String> options) 
	{
		if(options!=null && bleDeviceManager!=null)
		{
			for(String str:ConfigPreferenceActivity.type_value)
			{
				if(!options.contains(str))
				{
					switch(Integer.parseInt(str))
					{
					case 0:
					{			        			
						bleDeviceManager.deleteScanDeviceType(DeviceType.BLOOD_PRESSURE);
					};break;
					case 1:
					{
						bleDeviceManager.deleteScanDeviceType(DeviceType.WEIGHT_SCALE);
					};break;
					case 2:
					{
						bleDeviceManager.deleteScanDeviceType(DeviceType.PEDOMETER);
					};break;
					case 3:
					{
						bleDeviceManager.deleteScanDeviceType(DeviceType.BODY_FAT_SCALE);
					};break;
					case 4:
					{
						bleDeviceManager.deleteScanDeviceType(DeviceType.KITCHEN_SCALE);
					};break;
					case 5:
					{
						bleDeviceManager.deleteScanDeviceType(DeviceType.HEIGHT_SCALE);
					};break;
					case 6:
					{
						bleDeviceManager.deleteScanDeviceType(DeviceType.GLUCOSE_METER);
					};break;
					}
				}
			}
		}	
	}

	
	
	private void setListViewBackgroundColor(final View view)
	{
		view.setBackgroundColor(Color.GREEN);
		changeHanlder.postDelayed(new Runnable()
		{
			@Override
			public void run() 
			{
				view.setBackgroundColor(Color.WHITE);
			}		
		}, 1000);
	}

	@SuppressWarnings("unchecked")
	private void updateListViewBackground(String name) 
	{
		scanResult=true;
		if(!tempList.isEmpty())
		{	
			for(LSDeviceInfo dev:tempList)
			{
				if(dev!=null&&dev.getDeviceName().equals(name))
				{ 
					final View view= listview.getChildAt(deviceListAdapter.getPosition(dev));
					if(view!=null)
					{
						setListViewBackgroundColor (view);
					}        					 
				}
			}	 
		}		
	}

	private BleDevice getBleDevice(String name,String address,
			DeviceType deviceType,String modelNumber)
	{
		int type=typeConversion.enumToInteger(deviceType);
		BleDevice device=new BleDevice(
				name,
				address,
				type,
				modelNumber
				);
//		device.setPairFlags(PROMPT_INFO_UNPAIR_STATE);
		if(isPaired(name))
		{
			String broadcastID;
			if(name.length()>=14){
				broadcastID=name.substring(6,14);
			}
			else {broadcastID=name.substring(6);}
			
			device.setKey_id(broadcastIDMap.get(broadcastID));
		}
		else
		{
			device.setKey_id(-1);
		}
		return device;
	}

	private boolean isPaired(String deviceName) 
	{
		if(pairDeviceList.size()>0)
		{
			for(LSDeviceInfo device:pairDeviceList)
			{
				String broadcastID;
				if(deviceName.length()>=14){
					broadcastID=deviceName.substring(6,14);
				}
				else {broadcastID=deviceName.substring(6);}

				if(device.getBroadcastID().equals(broadcastID))
				{
			
					//	key_id=broadcastIDMap.get(device.getBroadcastID());
					return true;
				}
			}
		}
		return false;
	}


	
	
	//初始化数据库
    private void initDatabaseWithCursor(Cursor cursor) {
		int key_id=cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_ID);	
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

		pairDeviceList.clear();

        while (cursor.moveToNext()) {
			LSDeviceInfo tempSensor = new LSDeviceInfo();
			//tempUser.setId(cursor.getString(0));
			String id=cursor.getString(cursor.getColumnIndexOrThrow(DeviceContentProvider.KEY_ID));
			
			tempSensor.setBroadcastID(cursor.getString(brocastIDIndex));
			tempSensor.setDeviceId(cursor.getString(deviceIDIndex));
			tempSensor.setDeviceSn(cursor.getString(deviceSNIndex));
			tempSensor.setFirmwareVersion(cursor.getString(firmwareIndex));

			tempSensor.setHardwareVersion(cursor.getString(hardwareIndex));
			tempSensor.setManufactureName(cursor.getString(manufactureNameIndex));
			tempSensor.setModelNumber(cursor.getString(modelNumberIndex));
			tempSensor.setPassword(cursor.getBlob(passwordIndex));
			tempSensor.setDeviceAddress(cursor.getString(addressIndex));
			tempSensor.setDeviceName(cursor.getString(nameIndex));
			tempSensor.setDeviceType(typeConversion.integerToEnum(cursor.getInt(deviceTypeIndex)));
			tempSensor.setSoftwareVersion(cursor.getString(softwareIndex));
			tempSensor.setSystemId(cursor.getString(systemIDIndex));

            String logText = "pairDevice:{" + "BroadcastID:" + tempSensor.getBroadcastID() + ","
                    + "DeviceId:" + tempSensor.getDeviceId() + "," + "DeviceSn:"
                    + tempSensor.getDeviceSn() + "," + "FirmwareVersion:"
                    + tempSensor.getFirmwareVersion() + "," + "HardwareVersion:"
                    + tempSensor.getHardwareVersion() + "," + "ManufactureName:"
                    + tempSensor.getManufactureName() + "," + "ModelNumber:"
                    + tempSensor.getModelNumber() + "," + "Password:" + tempSensor.getPassword()
                    + "," + "DeviceAddress:" + tempSensor.getDeviceAddress() + "," + "DeviceName:"
                    + tempSensor.getDeviceName() + "," + "DeviceType:" + tempSensor.getDeviceType()
                    + "," + "SoftwareVersion:" + tempSensor.getSoftwareVersion() + ","
                    + "SystemId:" + tempSensor.getSystemId() + "}";
            Log.i(TAG, logText);

			broadcastIDMap.put(cursor.getString(brocastIDIndex), cursor.getInt(key_id));
			pairDeviceList.add(tempSensor);
        }


        if (pairDeviceList != null && pairDeviceList.size() > 0) {
            // 直接跳转
            pairedDeviceInfo = pairDeviceList.get(pairDeviceList.size() - 1);
            Intent intent = new Intent(AddDeviceActivity.this, TestMainActivity.class);
            intent.putExtra("pairedDeviceInfo", 1);
            startActivity(intent);

            finish();
        }
	}

    private ProgressDialog ringProgressDialog;

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

    private void savePairedDeviceToDatabase(LSDeviceInfo device) {
        contentValues.put(DeviceContentProvider.KEY_DEVICE_ADDRESS, device.getDeviceAddress());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_NAME, device.getDeviceName());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_BROCASTID, device.getBroadcastID());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_FIRMWARE_VERSION,
                device.getFirmwareVersion());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_HARDWARE_VERSION,
                device.getHardwareVersion());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_ID, device.getDeviceId());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_MANUFACTURENAME,
                device.getManufactureName());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_MODELNUMBER, device.getModelNumber());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_PASSWORD, device.getPassword());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_SN, device.getDeviceSn());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_SOFTWARE_VERSION,
                device.getSoftwareVersion());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_SYSTEMID, device.getSystemId());
        contentValues.put(DeviceContentProvider.KEY_DEVICE_TYPE,
                typeConversion.enumToInteger(device.getDeviceType()));

        // 向数据库表插入新记录，指定一个访问该数据库的URI与插入对象实例
        contentResolver.insert(DeviceContentProvider.CONTENT_URI, contentValues);

    }
}
