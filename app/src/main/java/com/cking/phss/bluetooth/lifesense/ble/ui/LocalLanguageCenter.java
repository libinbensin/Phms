package com.cking.phss.bluetooth.lifesense.ble.ui;

import android.content.Context;
import android.content.res.Resources;

import com.cking.phss.R;

public class LocalLanguageCenter 
{

	/*Commom Attribute*/
	public static String MA_PROGRESS_DIALOG_TITLE;
	public static String MA_PROGRESS_DIALOG_MESSAGE;
	public static String MA_PROMPT_DIALOG_TITLE;
	public static String MA_PROMPT_DIALOG_MESSAGE;
	public static String DIALOD_POSITIVE_BUTTON_TEXT;
	
	public static String CURRENT_BROADCAST_TYPE;
	public static String BROADCAST_TYPE_NORMAL;
	public static String BROADCAST_TYPE_PAIR;
	public static String BROADCAST_TYPE_ALL;
	
	public static String CURRENT_DEVICE_TYPE;
	public static String DEVICE_TYPE_SPHYGMOMETER;
	public static String DEVICE_TYPE_WEIGHT_SCALE;
	public static String DEVICE_TYPE_HEIGHT_RULER;
	public static String DEVICE_TYPE_FAT_SCALE;
	public static String DEVICE_TYPE_PEDOMETER;
	public static String DEVICE_TYPE_KITCHEN_SCALE;
	public static String DEVICE_TYPE_GLUCOSE_METER;
	
	/*MainActivity Attribute*/
	public static String MA_ENABLED_STATE_TEXT_VIEW;
	public static String MA_DISABLED_STATE_TEXT_VIEW;	
	public static String MA_LOW_ENERGY_SUPPORT_TEXT_VIEW;
	public static String MA_LOW_ENERGY_NOT_SUPPORT_TEXT_VIEW;	
	public static String MA_SCAN_NORMAL_TEXT_VIEW;
	public static String MA_SCAN_ABNORMAL_TEXT_VIEW;
	public static String MA_CONNECT_NORMAL_TEXT_VIEW;
	public static String MA_CONNECT_ABNORMAL_TEXT_VIEW;
	public static String MA_TEST_RESULTS_SUCCESS_TEXT_VIEW;
	public static String MA_TEST_RESULTS_FAILURE_TEXT_VIEW;	

	/*AddDeviceActivty Attribute*/
	public static String PROMPT_INFO_TITLE_NORMAL;
	public static String PROMPT_INFO_TITLE_WARNING;
	public static String PROMPT_INFO_TITLE_ERROR;
	public static String PROMPT_INFO_BLE_TURN_OFF;
	public static String PROMPT_INFO_LOW_ENERGY_NOT_SUPPORT;
	public static String PROMPT_INFO_VERSION_NOT_SUPPORT;
	public static String PROMPT_INFO_EXECUTE_ABNORMAL;
	public static String PROMPT_INFO_NO_SCAN_RESULTS;
	public static String PROMPT_INFO_SCAN_DEFAULTS_MESSAGE;
	
	public static String PROMPT_INFO_PAIRED_STATE;
	public static String PROMPT_INFO_UNPAIR_STATE;
	
	/*TestMainActivity Attribute*/
	public static String PAIR_DEVICE_DIALOG_TITLE ;
	public static String PAIR_DEVICE_DIALOG_MESSAGE ;
	public static String GET_DATA_DIALOG_TITLE ;
	public static String GET_DATA_DIALOG_MESSAGE ;
	public static String INFO_DEVICE_NAME ;
	public static String INFO_DEVICE_ADDRESS ;
	public static String INFO_DEVICE_TYPE ;
	public static String INFO_DEVICE_PASSWORD ;
	public static String INFO_DEVICE_ID ;
	public static String INFO_DEVICE_SN ;
	public static String INFO_DEVICE_MANUFACTURE_NAME ;
	public static String INFO_DEVICE_MODEL_NUMBER ;
	public static String INFO_DEVICE_FIRMWARE_VERSION ;
	public static String INFO_DEVICE_SOFTMWARE_VERSION ;
	public static String INFO_DEVICE_HARDMWARE_VERSION ;
	public static String INFO_DEVICE_BROADCAST_ID ;
	public static String MEASURE_DATA ;
	public static String USER_NUMBER ;
	public static String MEASURE_UNIT ;
	public static String DEVICE_BATTERY ;
	public static String MEASURE_DATA_RECORD ;
	public static String MEASURE_DATA_UPLOAD_SUCCESS ;
	public static String MEASURE_DATA_UPLOAD_FAILURE ;
	public static String TMA_SHOW_PAIRED_DEVICE_INFO_TITLE ;
	public static String WSMA_WEIGHT_VALUE ;
	public static String WSMA_FAT_VALUE ;
	public static String WSMA_RESISTOR1_VALUE ;
	public static String WSMA_RESISTOR2_VALUE ;

	public static String PMA_WALK_STEPS ;
	public static String PMA_RUNNING_STEPS ;
	public static String PMA_CALORIES ;
	public static String PMA_EXERCISE_TIME ;
	public static String PMA_DISTANCE ;
	public static String PMA_BATTERY ;
	public static String PMA_SLEEP_STATE ;
	public static String PMA_SHAKE_LEVEL ;
	public static String SMA_SBP ;
	public static String SMA_DIASTOLIC_BLOOD_PRESSURE ;
	public static String SMA_HEART_RATE ;
	public static String SMA_MEAN_BLOOD_PRESSURE ;
	public static String SMA_ARRHYTHMIA ;
	public static String HMA_HEIGHT_VALUE ;

	public static String TMA_SHOW_PAIR_RESULTS_SUCCESS_TITLE;
	public static String TMA_SHOW_PAIR_RESULTS_FAILURE_TITLE;

	public static void initMainActivityAttribute(Context appContext) 
	{
		if(appContext!=null)
		{
			Resources appResources=appContext.getResources();
			MA_PROGRESS_DIALOG_TITLE=appResources.getString(R.string.ma_progress_dialog_title);
			MA_PROGRESS_DIALOG_MESSAGE=appResources.getString(R.string.ma_progress_dialog_message);

			MA_ENABLED_STATE_TEXT_VIEW=appResources.getString(R.string.ma_bluetooth_enabled_tv);
			MA_DISABLED_STATE_TEXT_VIEW=appResources.getString(R.string.ma_bluetooth_disabled_tv);

			MA_LOW_ENERGY_SUPPORT_TEXT_VIEW=appResources.getString(R.string.ma_low_energy_support_tv);
			MA_LOW_ENERGY_NOT_SUPPORT_TEXT_VIEW=appResources.getString(R.string.ma_low_energy_not_support_tv);

			MA_SCAN_NORMAL_TEXT_VIEW=appResources.getString(R.string.ma_scan_normal_tv);
			MA_SCAN_ABNORMAL_TEXT_VIEW=appResources.getString(R.string.ma_scan_abnormal_tv);

			MA_CONNECT_NORMAL_TEXT_VIEW=appResources.getString(R.string.ma_connect_normal_tv);
			MA_CONNECT_ABNORMAL_TEXT_VIEW=appResources.getString(R.string.ma_connect_abnormal_tv);

			MA_TEST_RESULTS_SUCCESS_TEXT_VIEW=appResources.getString(R.string.ma_test_results_success_tv);
			MA_TEST_RESULTS_FAILURE_TEXT_VIEW=appResources.getString(R.string.ma_test_results_failure_tv);

			MA_PROMPT_DIALOG_TITLE=appResources.getString(R.string.prompt_info_title_normal);
			MA_PROMPT_DIALOG_MESSAGE=appResources.getString(R.string.ma_prompt_dialog_message);

			DIALOD_POSITIVE_BUTTON_TEXT=appResources.getString(R.string.dialog_positive_btn);
		}	
	}
	
	public static void initAddActivityAttribute(Context appContext)
	{
		if(appContext!=null)
		{
			Resources resources=appContext.getResources();
            PAIR_DEVICE_DIALOG_TITLE = resources.getString(R.string.pair_device_dialog_title);
            PAIR_DEVICE_DIALOG_MESSAGE = resources.getString(R.string.pair_device_dialog_message);
			PROMPT_INFO_TITLE_NORMAL = resources.getString(R.string.prompt_info_title_normal);
			PROMPT_INFO_TITLE_WARNING = resources.getString(R.string.prompt_info_title_warning);
			PROMPT_INFO_TITLE_ERROR = resources.getString(R.string.prompt_info_title_error);
			PROMPT_INFO_BLE_TURN_OFF = resources.getString(R.string.prompt_info_ble_turn_off);
			PROMPT_INFO_LOW_ENERGY_NOT_SUPPORT = resources.getString(R.string.prompt_info_low_energy_not_support);
			PROMPT_INFO_VERSION_NOT_SUPPORT = resources.getString(R.string.prompt_info_version_not_support);
			PROMPT_INFO_EXECUTE_ABNORMAL = resources.getString(R.string.prompt_info_execute_abnormal);
			PROMPT_INFO_NO_SCAN_RESULTS = resources.getString(R.string.prompt_info_no_scan_results);
			PROMPT_INFO_SCAN_DEFAULTS_MESSAGE = resources.getString(R.string.prompt_info_scan_defaults_message);
		
			BROADCAST_TYPE_NORMAL = resources.getString(R.string.broadcast_type_normal);
			BROADCAST_TYPE_PAIR = resources.getString(R.string.broadcast_type_pair);
			BROADCAST_TYPE_ALL = resources.getString(R.string.broadcast_type_all);
			DEVICE_TYPE_SPHYGMOMETER = resources.getString(R.string.device_type_sphygmomter);
			DEVICE_TYPE_WEIGHT_SCALE = resources.getString(R.string.device_type_weight_scale);
			DEVICE_TYPE_HEIGHT_RULER = resources.getString(R.string.device_type_height_ruler);
			DEVICE_TYPE_FAT_SCALE = resources.getString(R.string.device_type_fat_scale);
			DEVICE_TYPE_PEDOMETER = resources.getString(R.string.device_type_pedometer);
			DEVICE_TYPE_KITCHEN_SCALE = resources.getString(R.string.device_type_kitchen_scale);
			DEVICE_TYPE_GLUCOSE_METER = resources.getString(R.string.device_type_glucose_meter);
			CURRENT_BROADCAST_TYPE = resources.getString(R.string.ada_scan_dialog_title);
			CURRENT_DEVICE_TYPE = resources.getString(R.string.ada_scan_dialog_content);
			PROMPT_INFO_UNPAIR_STATE = resources.getString(R.string.ada_prompt_info_unpair_state);
			PROMPT_INFO_PAIRED_STATE = resources.getString(R.string.ada_prompt_info_pair_state);
		} 
	}
	public static void initTestMainActivityAttribute(Context appContext)
	{
		if(appContext!=null)
		{
			Resources resources=appContext.getResources();
			PAIR_DEVICE_DIALOG_TITLE = resources.getString(R.string.pair_device_dialog_title);
			PAIR_DEVICE_DIALOG_MESSAGE = resources.getString(R.string.pair_device_dialog_message);
			GET_DATA_DIALOG_TITLE = resources.getString(R.string.get_data_dialog_title);
			GET_DATA_DIALOG_MESSAGE = resources.getString(R.string.get_data_dialog_message);
			
			INFO_DEVICE_NAME = resources.getString(R.string.info_device_name);
			INFO_DEVICE_ADDRESS = resources.getString(R.string.info_device_address);
			INFO_DEVICE_TYPE = resources.getString(R.string.info_device_type);
			INFO_DEVICE_PASSWORD = resources.getString(R.string.info_device_password);
			INFO_DEVICE_ID = resources.getString(R.string.info_device_id);
			INFO_DEVICE_SN = resources.getString(R.string.info_device_sn);
			INFO_DEVICE_MANUFACTURE_NAME = resources.getString(R.string.info_device_manufacture_name);
			INFO_DEVICE_MODEL_NUMBER = resources.getString(R.string.info_device_model_number);
			INFO_DEVICE_FIRMWARE_VERSION = resources.getString(R.string.info_device_firmware_version);
			INFO_DEVICE_SOFTMWARE_VERSION = resources.getString(R.string.info_device_softmware_version);
			INFO_DEVICE_HARDMWARE_VERSION = resources.getString(R.string.info_device_hardmware_version);
			INFO_DEVICE_BROADCAST_ID = resources.getString(R.string.info_device_broadcast_id);
			
			MEASURE_DATA = resources.getString(R.string.measure_data);
			USER_NUMBER = resources.getString(R.string.user_number);
			MEASURE_UNIT = resources.getString(R.string.measure_unit);
			DEVICE_BATTERY = resources.getString(R.string.device_battery);
			MEASURE_DATA_RECORD = resources.getString(R.string.measure_data_record);
			MEASURE_DATA_UPLOAD_SUCCESS = resources.getString(R.string.measure_data_upload_success);
			MEASURE_DATA_UPLOAD_FAILURE = resources.getString(R.string.measure_data_upload_failure);
			TMA_SHOW_PAIRED_DEVICE_INFO_TITLE = resources.getString(R.string.tma_show_paired_device_info_title);
			
			WSMA_WEIGHT_VALUE = resources.getString(R.string.wsma_weight_value);
			WSMA_FAT_VALUE = resources.getString(R.string.wsma_fat_value);
			WSMA_RESISTOR1_VALUE = resources.getString(R.string.wsma_resistor1_value);
			WSMA_RESISTOR2_VALUE = resources.getString(R.string.wsma_resistor2_value);
			
			
			PMA_WALK_STEPS = resources.getString(R.string.pma_walk_steps);
			PMA_RUNNING_STEPS = resources.getString(R.string.pma_running_steps);
			PMA_CALORIES = resources.getString(R.string.pma_calories);
			PMA_EXERCISE_TIME = resources.getString(R.string.pma_exercise_time);
			PMA_DISTANCE = resources.getString(R.string.pma_distance);
			PMA_BATTERY = resources.getString(R.string.pma_battery);
			PMA_SLEEP_STATE = resources.getString(R.string.pma_sleep_state);
			PMA_SHAKE_LEVEL = resources.getString(R.string.pma_shake_level);
			
			SMA_SBP = resources.getString(R.string.sma_sbp);
			SMA_DIASTOLIC_BLOOD_PRESSURE = resources.getString(R.string.sma_diastolic_blood_pressure);
			SMA_HEART_RATE = resources.getString(R.string.sma_heart_rate);
			SMA_MEAN_BLOOD_PRESSURE = resources.getString(R.string.sma_mean_blood_pressure);
			SMA_ARRHYTHMIA = resources.getString(R.string.sma_arrhythmia);
			
			HMA_HEIGHT_VALUE = resources.getString(R.string.hma_height_value);
			TMA_SHOW_PAIR_RESULTS_SUCCESS_TITLE = resources.getString(R.string.tma_show_pair_results_success_title);
			TMA_SHOW_PAIR_RESULTS_FAILURE_TITLE = resources.getString(R.string.tma_show_pair_results_failure_title);
		}
	}
}
