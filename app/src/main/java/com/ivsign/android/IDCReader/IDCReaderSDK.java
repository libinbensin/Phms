/*
 * Copyright (C) 2011 Beijing Ivsign Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivsign.android.IDCReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * This class does all the work for setting up and managing Bluetooth
 * connections with other devices. It has a thread that listens for
 * incoming connections, a thread for connecting with a device, and a
 * thread for performing data transmissions when connected.
 */
public class IDCReaderSDK{
    // Debugging
    private static final String TAG = "IDCReaderSDK";
    private static final boolean D = true;

    // Name for the SDP record when creating server socket
    private static final String NAME = "IDCReaderSDK";

    // Unique UUID for this application
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");//"fa87c0d0-afac-11de-8a39-0800200c9a66");

    // Member fields
    private final BluetoothAdapter mAdapter;
    private final Handler mHandler;
    private AcceptThread mAcceptThread;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
  
    // Constants that indicate the current connection state   
    private int mState;
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device

    private int mProtocolState;
    public static final int PROTOCOLSTATE_NONE = 0;       // we're doing nothing
    public static final int PROTOCOLSTATE_SIMID = 1;     // now Reading SIMID
    public static final int PROTOCOLSTATE_READDATA = 3;  // now Reading Data
    
    private int mProtocolRet = 0;
    public static final int CVR_RETCODE_SUCCESS = 1;       // Success
    public static final int CVR_RETCODE_ERROR = 0;     // Error
    public static final int CVR_RETCODE_TIMEOUT = 2;  // TimeOut
    public static final int CVR_RETCODE_LICERROR = 3;  // License File Error
    
    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    
    private byte[] persionInfo = new byte[256];
    private byte[] byLicData = new byte[16];
    private String[] decodeInfo = new String[10];
    
    
    
    /**
     * Constructor. Prepares a new BluetoothChat session.
     * @param context  The UI Activity Context
     * @param handler  A Handler to send messages back to the UI Activity
     */
    public IDCReaderSDK(Context context, Handler handler, String wltPath) {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
        mHandler = handler;
        
        if( 0==wltInit(wltPath) )
        	Log.i(TAG,  "wltInit success");
    }

    public static String printHexString( byte[] b, int len) { 
    	String result="";
    	for (int i = 0; i < len; i++) { 
    		String hex = Integer.toHexString(b[i] & 0xFF); 
    		if (hex.length() == 1) { 
    			hex = '0' + hex; 
    		} 
    		result=result+hex.toUpperCase()+' '; 
	     } 
	     return result;
	 }
    
    /**
     * Set the current state of the chat connection
     * @param state  An integer defining the current connection state
     */
    private synchronized void setState(int state) {
        if (D) Log.d(TAG, "setState() " + mState + " -> " + state);
        mState = state;

        // Give the new state to the Handler so the UI Activity can update
        if(null!=mHandler)
        {
        	mHandler.obtainMessage(MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
        }
    }

    /**
     * Return the current connection state. */
    public synchronized int getState() {
        return mState;
    }

    /**
     * Start the chat service. Specifically start AcceptThread to begin a
     * session in listening (server) mode. Called by the Activity onResume() */
    public synchronized void start() {
        if (D) Log.d(TAG, "start");

        // Cancel any thread attempting to make a connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Start the thread to listen on a BluetoothServerSocket
        if (mAcceptThread == null) {
            mAcceptThread = new AcceptThread();
            mAcceptThread.start();
        }
        setState(STATE_LISTEN);
    }
 
    /**
     * Start the ConnectThread to initiate a connection to a remote device.
     * @param device  The BluetoothDevice to connect
     */
    public synchronized void connect(BluetoothDevice device) {
        if (D) Log.d(TAG, "connect to: " + device);

        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device);
        mConnectThread.start();
        setState(STATE_CONNECTING);
    }

    private BluetoothAdapter mBtAdapter;
    public synchronized void connectpaired(String deviceName)
    {
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            //findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
            	if( deviceName.equals(device.getName()) )
            	{
                    this.connect(device);
                    break;
            	}
            }
        }
    }
    
    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     * @param socket  The BluetoothSocket on which the connection was made
     * @param device  The BluetoothDevice that has been connected
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
        if (D) Log.d(TAG, "connected");

        // Cancel the thread that completed the connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Cancel the accept thread because we only want to connect to one device
        if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        // Send the name of the connected device back to the UI Activity
        if(null!=mHandler)
        {
	        Message msg = mHandler.obtainMessage(MESSAGE_DEVICE_NAME);
	        Bundle bundle = new Bundle();
	        bundle.putString(DEVICE_NAME, device.getName());
	        msg.setData(bundle);
	        mHandler.sendMessage(msg);
        }

        setState(STATE_CONNECTED);
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {
        if (D) Log.d(TAG, "stop");
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}
        if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}
        setState(STATE_NONE);
    }

    /**
     * Write to the ConnectedThread in an unsynchronized manner
     * @param out The bytes to write
     * @see ConnectedThread#write(byte[])
     */
    public void sendCmd(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    private void connectionFailed() {
        setState(STATE_LISTEN);

        // Send a failure message back to the Activity
        if(null!=mHandler)
        {
	        Message msg = mHandler.obtainMessage(MESSAGE_TOAST);
	        Bundle bundle = new Bundle();
	        bundle.putString(TOAST, "Unable to connect device");
	        msg.setData(bundle);
	        mHandler.sendMessage(msg);
        }   
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    private void connectionLost() {
        setState(STATE_LISTEN);

        // Send a failure message back to the Activity
        if(null!=mHandler)
        {
	        Message msg = mHandler.obtainMessage(MESSAGE_TOAST);
	        Bundle bundle = new Bundle();
	        bundle.putString(TOAST, "Device connection was lost");
	        msg.setData(bundle);
	        mHandler.sendMessage(msg);
        }
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * (or until cancelled).
     */
    private class AcceptThread extends Thread {
        // The local server socket
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;

            // Create a new listening server socket
            try {
                tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "listen() failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            if (D) Log.d(TAG, "BEGIN mAcceptThread" + this);
            setName("AcceptThread");
            BluetoothSocket socket = null;

            if (mmServerSocket == null) {
                return;
            }
            // Listen to the server socket if we're not connected
            while (mState != STATE_CONNECTED) {
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "accept() failed", e);
                    break;
                }

                // If a connection was accepted
                if (socket != null) {
                    synchronized (IDCReaderSDK.this) {
                        switch (mState) {
                        case STATE_LISTEN:
                        case STATE_CONNECTING:
                            // Situation normal. Start the connected thread.
                            connected(socket, socket.getRemoteDevice());
                            break;
                        case STATE_NONE:
                        case STATE_CONNECTED:
                            // Either not ready or already connected. Terminate new socket.
                            try {
                                socket.close();
                            } catch (IOException e) {
                                Log.e(TAG, "Could not close unwanted socket", e);
                            }
                            break;
                        }
                    }
                }
            }
            if (D) Log.i(TAG, "END mAcceptThread");
        }

        public void cancel() {
            if (D) Log.d(TAG, "cancel " + this);
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of server failed", e);
            }
        }
    }


    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either
     * succeeds or fails.
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            mmDevice = device;
            BluetoothSocket tmp = null;

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            Log.i(TAG, "BEGIN mConnectThread");
            setName("ConnectThread");

            // Always cancel discovery because it will slow down a connection
            mAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket.connect();
            } catch (IOException e) {
                connectionFailed();
                // Close the socket
                try {
                    mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
                // Start the service over to restart listening mode
                IDCReaderSDK.this.start();
                return;
            }

            // Reset the ConnectThread because we're done
            synchronized (IDCReaderSDK.this) {
                mConnectThread = null;
            }

            // Start the connected thread
            connected(mmSocket, mmDevice);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    /**
     * This thread runs during a connection with a remote device.
     * It handles all incoming and outgoing transmissions.
     */
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        private int cmState = 0;
        private char cmd = 'n';
        
        public ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "create ConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            
            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        
        public void sendProcess(char c)
        {
        	byte[] cmd_init0 = {0x02, 0x00, 0x11, 0x03, (byte) 0xAA, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, (byte) 0xB9, 0x03 };        	
        	byte[] cmd_init1 = {(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x11, (byte) 0xFF, (byte) 0xED };
        	byte[] cmd_init2 = {(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x12, (byte) 0xFF, (byte) 0xEE  };
        	byte[] cmd_find  = {(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x01, 0x22  };
        	byte[] cmd_selt  = {(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x02, 0x21  };
        	byte[] cmd_read  = {(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x30, 0x01, 0x32 };
        	//byte[] cmd_get_key = {(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x12, (byte) 0xFF, (byte) 0xEE };
        	byte[] needsend = null;

    		switch(c)
    		{
    		case '0':
            {
            	needsend = cmd_init0;
            	cmState = 10;
            }   
            break;
    		case '1':
            {
            	needsend = (cmd_init1); 
            	cmState = 11;
            }
            break;
    		case '2':
            {
            	needsend = (cmd_init2);
            	cmState = 12;
            }
            break;
    		case 'f':
            {
            	needsend = (cmd_find);
            	cmState = 21;
            }
            break;	
    		case 's':
            {
            	needsend = (cmd_selt);
            	cmState = 22;
            }
            break;
    		case 'r':
            {
            	needsend = (cmd_read);
            	cmState = 31;
            }		            
            break;			            
    		} 
	    	
    		if(needsend!=null)
    		{
		        try {
			        mmOutStream.write(needsend); 	        	
		        } catch (IOException e) {
		            Log.e(TAG, "Exception during write", e);
		        }
	
		        Log.i(TAG, "SEND: "+printHexString(needsend, needsend.length));
    		}
        }
        
        public int recvProcess(byte[] rcData, int len)
        {
        	int validlen = 0;
        	int vaildoffset = 7;
        
        	Log.i(TAG, "recvProcess: "+len);
        	
        	if(len<7)
        		return 0;
        	
        	if( (byte)0x96 == rcData[3] && 0x69 == rcData[4] )
        		vaildoffset = 7;
        	else if( (byte)0x96 == rcData[2] && 0x69 == rcData[3] )
        		vaildoffset = 6;
        	else if( (byte)0x96 == rcData[1] && 0x69 == rcData[2] )
        		vaildoffset = 5;
        	else if( (byte)0x96 == rcData[0] && 0x69 == rcData[1] )
        		vaildoffset = 5;        	
        	else
        	{
        		Log.i(TAG, "error: "+rcData[2]+" "+rcData[3]+" "+rcData[4]);
        		return 0;
        	}
        	
        	int wsize = rcData[vaildoffset-1] + rcData[vaildoffset-2] *256;
        	if( wsize<0 || wsize > 1288)
        		return 0;
        	
        	switch(cmState)
        	{
    		case 10:
        		if( wsize>0 && wsize <32 )
        		{
	    			validlen = wsize+vaildoffset;
	    			cmd = '1'; //sendProcess('1');   
	    		}
    		break;
        	case 11:	// cmd_init1
        		if( wsize>0 && wsize <32 )
        		{
	        		validlen = wsize+vaildoffset;
	        		cmd = '2'; //sendProcess('2');
	    		}
	    		else
	    		{
	    			cmd = 'n'; //sendProcess('1'); 
	    			mProtocolRet = 0;
	    		} 	
        	break;
        	case 12:	// cmd_init2
        		if( wsize>0 && wsize <32 )
        		{
	        		validlen = wsize+vaildoffset;
	        		cmd = 'f'; //sendProcess('f');
	    		}
	    		else
	    		{
	    			cmd = '1'; //sendProcess('1'); 
	    		}       		
        	break;
        	case 21:	// cmd_find
        		if( wsize>0 && wsize <32 )
        		{       		
	        		validlen = wsize+vaildoffset;
	        		cmd = 's'; //sendProcess('s');
        		}
        		else
        		{
        			cmd = 'n'; //sendProcess('f'); 
        			mProtocolRet = 0;
        		}
        	break;		
        	case 22:	// cmd_selt
        		if( wsize>0 && wsize <32 )
        		{
	        		validlen = wsize+vaildoffset;
	        		cmd = 'n'; //sendProcess('r');
	        		mProtocolRet = 1;
        		}
        		else
        		{
        			cmd = 'f'; //sendProcess('f'); 
        		}
        	break;
        	case 31:	// cmd_read
        		if( wsize == 1288)
        		{
        			validlen = wsize+vaildoffset;
        			cmd = 'e';
        			mProtocolRet = 1;
        		}
        		else if( wsize == 4 )
            	{
            		validlen = wsize+vaildoffset;
            		cmd = 'e';
            		mProtocolRet = 0;
        		}
        		else
            	{
            		cmd = 'n';
            		mProtocolRet = 0;
        		}
        	break;
        	}
        	
        	Log.i(TAG, "RECV: (need"+validlen+"byte)"+printHexString(rcData, len));
        	
        	return validlen;
        }
        
        public void run() {
            Log.i(TAG, "BEGIN mConnectedThread");
            byte[] buffer = new byte[1128+256];
            int bytes;

            // Keep listening to the InputStream while connected
            while (true) {
                try {
                	
                	try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    
                    while(11>bytes)  // more then 11 bytes
                    {
                    	int curRecv = mmInStream.read(buffer, bytes, 11-bytes);
                    	bytes += curRecv;
                    	Log.i(TAG, "RECV(coutinue): " +printHexString(buffer, 32));
                    }                   
                    
                    //addby zwcai 20110726
                    int validlen = recvProcess(buffer, bytes);
                    	
                    while(validlen>bytes)
                    {
                    	int curRecv = mmInStream.read(buffer, bytes, validlen-bytes);
                    	bytes += curRecv;
                    	Log.i(TAG, "RECV(coutinue): " +printHexString(buffer, 32));
                    }
                    
                    if(cmd!='n' && cmd!='e')
                    {
                    	sendProcess(cmd);
                    	cmd = 'n';
                    }
                    else
                    {
                    	mProtocolState = PROTOCOLSTATE_NONE;
                    }
                    
                    if( 27 == bytes)
                    {
                    	Log.i(TAG, "RECV OK(SAMID)");
                    	for(int i=0; i<16; i++)
                    		byLicData[i] = buffer[10+i];
                    	
                        // Send the obtained bytes to the UI Activity
                        if(null!=mHandler)
                        {
                        	mHandler.obtainMessage(MESSAGE_READ, byLicData.length, 0, byLicData)
                            	.sendToTarget();
                        }
                    }
                    else if(bytes < 1288+5)
                    {                   
                    	// Send the obtained bytes to the UI Activity
                        if(null!=mHandler)
                        {
	                    	mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
	                            .sendToTarget();
                        }
                    }
                    else if(bytes >= 1288+5)
                    {              
                    	Log.i(TAG, "RECV OK(DATA)");
                    	
                    	if(bytes < 1288+7)
                    	{
                    	int lost = 1288+7 - bytes;
                    		for(int i=bytes-1; i>=0; i--)
                    			buffer[i+lost] = buffer[i]; 
                    	}
                    	
                    	for(int i=0; i<256;i++)
                    		persionInfo[i] = buffer[i+14];
                    	
                        decodePersionInfo(persionInfo); 
                        Log.i(TAG, "decodePersionInfo");
                        
                        //设置固定死的byLicData
                        byLicData = new byte[]{0x05,0x00,0x01,0x00,0x25,0x02,0x33,0x01,0x40,0x42,0x0F,0x00};
                        int ret = wltGetBMP(buffer, byLicData);
                        Log.i(TAG, "wltGetBMP ret = "+ret);
                        if( 0 == ret )
                        	mProtocolRet = 0;  // Error
                        else if( 2 == ret )
                        	mProtocolRet = 3;  // license问题 
                        
                        // Send the obtained bytes to the UI Activity
                        if(null!=mHandler)
                        {
                        	mHandler.obtainMessage(MESSAGE_READ, 256, 1, persionInfo)
                                .sendToTarget();  
                        }
                    }
                    
                    if(cmd=='e' && validlen<=11)
                    { // no IDCard 
                        if(null!=mHandler)
                        {
	                        mHandler.obtainMessage(MESSAGE_READ, 0, 2, buffer)
	                        .sendToTarget();
                        }
                    }
                    

                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    connectionLost();
                    break;
                }
            }
        }
 
        /**
         * Write to the connected OutStream.
         * @param buffer  The bytes to write
         */
        public void write(byte[] buffer) {
            try {
            	if( 1 == buffer.length )
            	{
            	char c = (char)buffer[0];
            		sendProcess(c);
            	}
            	else
            	{
            		mmOutStream.write(buffer);
            	}
            	
                // Share the sent message back to the UI Activity
            	if(null!=mHandler)
            		mHandler.obtainMessage(MESSAGE_WRITE, -1, -1, buffer)
                        .sendToTarget();
            	
            } catch (IOException e) {
                Log.e(TAG, "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
  
    // SDK interface
    public int CVR_InitComm(String deviceName, int timeOut)
    {
    	int ret = 0;
    	int i = 0;
    	
    	connectpaired(deviceName);
       
	   	for(i=timeOut/100; i>0; i--)
		{	
			if(STATE_CONNECTING != mState)
				break;
		
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	   	
	   	if( STATE_CONNECTED == mState)
	   		ret = 1;
	   	else if(0==i)
	   		ret = 2;
	   	
        return ret;
    }
    
    // SDK interface
    public int CVR_InitComm(BluetoothDevice device, int timeOut)
    {
    	int ret = 0;
    	int i = 0;
    	
    	this.connect(device);
       
	   	for(i=timeOut/100; i>0; i--)
		{	
			if(STATE_CONNECTING != mState)
				break;
		
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	   	
	   	if( STATE_CONNECTED == mState)
	   		ret = 1;
	   	else if(0==i)
	   		ret = 2;
	   	
        return ret;
    }
    
    public int CVR_CloseComm()
    {
    	int ret = 1;
    	
        try {
        	if(null!=mConnectedThread)
        		mConnectedThread.mmSocket.close();
        	mState = STATE_NONE;
        } catch (IOException e) {
        	ret = 0;
            Log.e(TAG, "close() of connect socket failed", e);
        }
        
    	return ret;
    }
    
    public int CVR_Authenticate(int timeOut)
    {
    	byte[] command = {(byte)'1'};
    
    	mProtocolState = PROTOCOLSTATE_SIMID;
    	mProtocolRet = 2;
    	for(int i=byLicData.length-1;i>=0;i--)
    		byLicData[i]=0;
    	
    	sendCmd(command);
    	
    	for(int i=timeOut/100; i>0; i--)
    	{	
    		if(PROTOCOLSTATE_NONE == mProtocolState)
    			break;
    	
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	return mProtocolRet;
    }
    
    public int CVR_Read_Content(int nType, int timeOut)
    {
    byte[] command = {(byte)'r'};	
    
    	mProtocolState = PROTOCOLSTATE_READDATA;
    	mProtocolRet = 2;
    	for(int i=persionInfo.length-1;i>=0;i--)
    		persionInfo[i]=0;
    	
    	sendCmd(command);
    	
    	for(int i=timeOut/100; i>0; i--)
    	{	
    		if(PROTOCOLSTATE_NONE == mProtocolState)
    			break;
    	
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	return mProtocolRet;
    } 
    
    public byte[]   CVR_GetInfo()
    {
    	return persionInfo.clone();
    }
  
    public byte[] CVR_GetSAMID()
    {
    	return byLicData.clone();
    }
    
    public String GetPeopleName()
    {
    	return decodeInfo[0];
    }
    
    public String GetPeopleSex()
    {
    	return decodeInfo[1];
    }
    
    public String GetPeopleNation()
    {
    	return decodeInfo[2];
    }
    
    public String GetPeopleBirthday()
    {
    	return decodeInfo[3];
    }
    
    public String GetPeopleAddress()
    {
    	return decodeInfo[4];
    }
    
    public String GetPeopleIDCode()
    {
    	return decodeInfo[5];
    }
    
    public String GetDepartment()
    {
    	return decodeInfo[6];
    }
    
    public String GetStartDate()
    {
    	return decodeInfo[7];
    }
 
    public String GetEndDate()
    {
    	return decodeInfo[8];
    }
    
    public int GetManuID()
    {
    	int data  = (byLicData[8]&0xFF) + (byLicData[9]&0xFF)*256 
    				+ (byLicData[10]&0xFF)*256*256 + (byLicData[11]&0xFF)*256*256*256;
    	return data;
    }
/*
    public void testMyDecode()
    {
    	byte[] testedBuf = {(byte)0x21,(byte)0x85,(byte)0xD7,(byte)0x5F,(byte)0x87,(byte)0x65,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x39,(byte)0x00,(byte)0x38,(byte)0x00,
    						(byte)0x33,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x37,(byte)0x00,(byte)0x89,(byte)0x5B,(byte)0xBD,(byte)0x5F,(byte)0x01,(byte)0x77,(byte)0x08,(byte)0x54,(byte)0xA5,(byte)0x80,(byte)0x02,(byte)0x5E,(byte)0x05,(byte)0x53,(byte)0xB3,(byte)0x6C,(byte)0x3A,(byte)0x53,(byte)0x6F,(byte)0x5C,(byte)0xAA,(byte)0x6E,(byte)0xEF,(byte)0x8D,(byte)0x11,(byte)0xFF,(byte)0x19,(byte)0xFF,(byte)0x13,(byte)0xFF,(byte)0xF7,(byte)0x53,
    						(byte)0x10,(byte)0xFF,(byte)0x16,(byte)0xFF,(byte)0x14,(byte)0x78,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x33,(byte)0x00,(byte)0x33,(byte)0x00,
    						(byte)0x30,(byte)0x00,(byte)0x33,(byte)0x00,(byte)0x38,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x39,(byte)0x00,(byte)0x38,(byte)0x00,(byte)0x33,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x37,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x37,(byte)0x00,(byte)0x33,(byte)0x00,(byte)0x36,(byte)0x00,(byte)0x08,(byte)0x54,(byte)0xA5,(byte)0x80,(byte)0x02,(byte)0x5E,(byte)0x6C,(byte)0x51,(byte)0x89,(byte)0x5B,
    						(byte)0x40,(byte)0x5C,(byte)0x05,(byte)0x53,(byte)0xB3,(byte)0x6C,(byte)0x06,(byte)0x52,(byte)0x40,(byte)0x5C,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x32,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x36,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x32,(byte)0x00,(byte)0x32,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x32,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x31,(byte)0x00,
    						(byte)0x36,(byte)0x00,(byte)0x31,(byte)0x00,(byte)0x32,(byte)0x00,(byte)0x32,(byte)0x00,(byte)0x30,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00,
    						(byte)0x20,(byte)0x00,(byte)0x20,(byte)0x00};
    	
    	decodePersionInfo(testedBuf);
    	
    }
 */   
    private void decodePersionInfo(byte[] dataBuf)
    {
        try {
            String TmpStr = new String( dataBuf,"UTF16-LE");
            TmpStr = new String(TmpStr.getBytes("UTF-8"));
            
            decodeInfo[0] = TmpStr.substring(0, 15);	//姓名
            decodeInfo[1] = TmpStr.substring(15, 16);	//性别 
            decodeInfo[2] = TmpStr.substring(16, 18);	//民族
            decodeInfo[3] = TmpStr.substring(18, 26);	//出生
            decodeInfo[4] = TmpStr.substring(26, 61);	//住址
            decodeInfo[5] = TmpStr.substring(61, 79);	//号码
            decodeInfo[6] = TmpStr.substring(79, 94);	//机关
            decodeInfo[7] = TmpStr.substring(94, 102);	//有效期起始
            decodeInfo[8] = TmpStr.substring(102, 110);	//有效期截止
            decodeInfo[9] = TmpStr.substring(110, 128);	//最新住址
            
            if(decodeInfo[1].equals("1"))
            	decodeInfo[1] = "男";
            else
            	decodeInfo[1] = "女";
            
            try{
            	int code =Integer.parseInt(decodeInfo[2].toString());
            	decodeInfo[2] = decodeNation(code); 
        	}catch(Exception e){
        		decodeInfo[2] = "";
        	}
       
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
    }

    private String decodeNation(int code)
    {
    	
    	String nation;
    	
    	switch(code){
        case 1:  nation =("汉");break;  
        case 2:  nation =("蒙古");break;
        case 3:  nation =("回");break;
        case 4:  nation =("藏");break;
        case 5:  nation =("维吾尔");break;
        case 6:  nation =("苗");break;
        case 7:  nation =("彝");break;
        case 8:   nation =("壮");break;
        case 9:   nation =("布依");break;
        case 10:  nation =("朝鲜");break;
        case 11:  nation =("满");break;
        case 12:  nation =("侗");break;
        case 13:  nation =("瑶");break;
        case 14:  nation =("白");break;
        case 15:  nation =("土家");break;
        case 16:  nation =("哈尼");break;
        case 17:  nation =("哈萨克");break;
        case 18:  nation =("傣");break;
        case 19:  nation =("黎");break;
        case 20:  nation =("傈僳");break;
        case 21:  nation =("佤");break;
        case 22:  nation =("畲");break;
        case 23:  nation =("高山");break;
        case 24:  nation =("拉祜");break;
        case 25:  nation =("水");break;
        case 26:  nation =("东乡");break;
        case 27:  nation =("纳西");break;
        case 28:  nation =("景颇");break;
        case 29:  nation =("柯尔克孜");break;
        case 30:  nation =("土");break;
        case 31:  nation =("达斡尔");break;
        case 32:  nation =("仫佬");break;
        case 33:  nation =("羌");break;
        case 34:  nation =("布朗");break;
        case 35:  nation =("撒拉");break;
        case 36:  nation =("毛南");break;
        case 37:  nation =("仡佬");break;
        case 38:  nation =("锡伯");break;
        case 39:  nation =("阿昌");break;
        case 40:  nation =("普米");break;
        case 41:  nation =("塔吉克");break;
        case 42:  nation =("怒");break;
        case 43:  nation =("乌孜别克");break;
        case 44:  nation =("俄罗斯");break;
        case 45:  nation =("鄂温克");break;
        case 46:  nation =("德昂");break;
        case 47:  nation =("保安");break;
        case 48:  nation =("裕固");break;
        case 49:  nation =("京");break;
        case 50:  nation =("塔塔尔");break;
        case 51:  nation =("独龙");break;
        case 52:  nation =("鄂伦春");break;
        case 53:  nation =("赫哲");break;
        case 54:  nation =("门巴");break;
        case 55:  nation =("珞巴");break;
        case 56:  nation =("基诺");break;
        case 97:  nation =("其他");break;
        case 98:  nation =("外国血统中国籍人士");break;
        default : nation =("");
    	}

    	return nation;
    }
        
    // native functin interface
    public static native int wltInit(String workPath);
    
    public static native int wltGetBMP(byte[] wltdata, byte[] licdata);
    
    /* this is used to load the 'wltdecode' library on application
     */
    static {
        System.loadLibrary("wltdecode");
    }
}
