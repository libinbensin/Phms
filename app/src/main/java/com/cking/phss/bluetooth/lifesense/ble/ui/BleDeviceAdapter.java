package com.cking.phss.bluetooth.lifesense.ble.ui;

import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_PAIRED_STATE;
import static com.cking.phss.bluetooth.lifesense.ble.ui.LocalLanguageCenter.PROMPT_INFO_UNPAIR_STATE;

import java.util.ArrayList;

import lifesense.ble.bean.LSDeviceInfo;
import lifesense.ble.commom.DeviceType;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cking.phss.R;

public class BleDeviceAdapter extends ArrayAdapter {
	

    private ArrayList<LSDeviceInfo> modelsArrayList;
	  private Context context;
	  private TypeConversion typeConversion=new TypeConversion();
	private String broadcastID;

	  
    public BleDeviceAdapter(Context context, ArrayList<LSDeviceInfo> modelsArrayList) {
		
	         super(context, R.layout.list_item, modelsArrayList);
	        
	         this.context = context;
	         this.modelsArrayList = modelsArrayList;
	     }
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView=null;
        if(modelsArrayList.size()!=0)
        {
        	  rowView =inflater.inflate(R.layout.list_item, parent, false);
              TextView nameView = (TextView) rowView.findViewById(R.id.nameTextView);
              TextView addressView=(TextView) rowView.findViewById(R.id.addressTextView);
              TextView typeView=(TextView)rowView.findViewById(R.id.typeTextView);
              TextView pairView=(TextView)rowView.findViewById(R.id.pairTextView);
              
            String sensorName = modelsArrayList.get(position).getDeviceName();
            DeviceType sensorType = modelsArrayList.get(position).getDeviceType();
              
              nameView.setText("Name: "+sensorName);
            addressView.setText("Address: " + modelsArrayList.get(position).getDeviceAddress());
            typeView.setText("Model: " + sensorType);
             
              if(sensorName.length()>=14){
            	  broadcastID=sensorName.substring(6,14);
              }
              else {broadcastID=sensorName.substring(6);}
            	 
              if(AddDeviceActivity.pairDeviceList.size()>0)
             {
            	 for(LSDeviceInfo device:AddDeviceActivity.pairDeviceList)
                 {
               	  if(broadcastID.equals(device.getBroadcastID()))
               	  {
               		  pairView.setBackgroundColor(Color.RED);
               		  pairView.setText(PROMPT_INFO_PAIRED_STATE);
               	  	}
               	  else {
               		 
               	  }
                         
                 } 
             }
             else pairView.setText(PROMPT_INFO_UNPAIR_STATE);
             
            
        }
        else{
        	System.out.println("is empty no data");
        	
        }
      
        // 5. retrn rowView
        return rowView;
    }
	
}
