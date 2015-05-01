package com.cking.phss.bluetooth.lifesense.ble.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceActivity;

import com.cking.phss.R;

@SuppressLint("NewApi")
public class ConfigPreferenceActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener
{
	public static String BROADCAST_TYPE_NORMAL;
	public static String BROADCAST_TYPE_PAIR;
	public static String BROADCAST_TYPE_ALL;
	
	ListPreference listpref;
	MultiSelectListPreference multipref;
	public static Integer currentSet=-1;
    public final static String[] optionText = getOptionText();
    public final static String[] optionValues = getOptionValues();
    public final static String[] device_type=new String[]
    		{
    			"血压计",
    			"体重秤",
    			"计步器",
    			"脂肪秤",
    			"厨房秤",
    			"身高尺",
    			"血糖仪"
    		};
   public final static String[] type_value=new String[]{"0","1","2","3","4","5","6"};
 
    static String[] getOptionText() {
    	return new String[] {
    			LocalLanguageCenter.BROADCAST_TYPE_NORMAL, 
    			LocalLanguageCenter.BROADCAST_TYPE_PAIR, 
    			LocalLanguageCenter.BROADCAST_TYPE_ALL
    			};
    }

    static String[] getOptionValues() {
    	return new String[] {"0", "1", "2"};
    }

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LocalLanguageCenter.initAddActivityAttribute(getApplicationContext());
		addPreferencesFromResource(R.xml.setting);   
		
		 listpref = (ListPreference)findPreference("brodcast_type");
//		 listpref.setEntryValues(optionValues);
//		 listpref.setEntries(optionText);
		
	
		 multipref=(MultiSelectListPreference) findPreference("device_type");
//		 multipref.setEntries(device_type);
//		 multipref.setEntryValues(type_value);
	}
	
	@Override
    protected void onResume() {
    	super.onResume();
    	getPreferenceScreen().getSharedPreferences()
            .registerOnSharedPreferenceChangeListener(this);
    	LocalLanguageCenter.initAddActivityAttribute(getApplicationContext());
    	setSummary();
    	setDeviceTypeSummary();
    }
	
    private void setSummary() 
    {
    	System.out.println("my choose:"+listpref.getValue());
    	if(listpref!=null && listpref.getValue()!=null)
    	{
    		System.out.println("单选:"+listpref.getEntryValues());
    		listpref.setSummary(optionText[Integer.valueOf(listpref.getValue())]);
//        	resultIntent.putExtra("choose", optionText[Integer.valueOf(listpref.getValue())]);
        	currentSet=Integer.valueOf(listpref.getValue());
    	}
    	
	}
   
   
	private void setDeviceTypeSummary()
    {
    	if(multipref!=null && multipref.getEntryValues()!=null)
    	{
    		for(CharSequence str:multipref.getEntries())
        	{
        		System.out.println("多选："+str.toString());
        	}
    	}
    	
    	
    }    	
    	
    

	@Override
    protected void onPause() {
    	super.onPause();
    	
    	getPreferenceScreen().getSharedPreferences()
           .unregisterOnSharedPreferenceChangeListener(this);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if("brodcast_type".equals(key)) 
		{
			setSummary();
		}
//		if("device_type".equals(key))
//		{
//			System.out.println("多选："+device_type[multipref.findIndexOfValue(key)]);
//			
//		}

		
	}


	
}
