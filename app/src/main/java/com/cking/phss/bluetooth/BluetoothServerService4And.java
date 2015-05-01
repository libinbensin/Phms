/*
 * Copyright (C) 2009 The Android Open Source Project
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

      Derived from BluetoothClient Android example.

 */

package com.cking.phss.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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

import com.cking.phss.file.FileLog;

/**
 * This class does all the work for setting up and managing Bluetooth
 * connections with other devices. It has a thread that listens for
 * incoming connections, a thread for connecting with a device, and a
 * thread for performing data transmissions when connected.
 */
public class BluetoothServerService4And {
    // Debugging
    private static final String TAG = "BluetoothServerService4And";
    private static final boolean D = true;

    // Message types sent from the BluetoothServerService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_DISCONNECT = 6;

    // Name for the SDP record when creating server socket
    private static String NAME = "";
    
    // 最大端口数目
    private static int MAX_CHANNEL_SIZE = 1;

    // SPP UUID
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // Member fields
    private final BluetoothAdapter mAdapter;
    private final Handler mHandler;
    private List<AcceptThread> mAcceptThreadList = new ArrayList<AcceptThread>();
    //private AcceptThread mAcceptThread;
	private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private int mState;

    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device

    // Key names received from the BluetoothServerService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String DEVICE_PORT = "device_port";
    public static final String TOAST = "toast";
    
    private int mPort = -1;

    /**
     * Constructor. Prepares a new BluetoothClient session.
     * @param context  The UI Activity Context
     * @param handler  A Handler to send messages back to the UI Activity
     * @param port 
     */
    public BluetoothServerService4And(Context context, Handler handler, int port, String name,
            int maxChannelSize) {
        if (D) Log.d(TAG, "BluetoothServerService");
        FileLog.d(TAG, "BluetoothServerService");
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
        mHandler = handler;
        mPort = port;
        NAME = name;
        mAdapter.setName(NAME);
        MAX_CHANNEL_SIZE = maxChannelSize;
    }

    /**
     * Set the current state of the chat connection
     * @param state  An integer defining the current connection state
     */
    private synchronized void setState(int state) {
        if (D) Log.d(TAG, "setState() " + mState + " -> " + state);
        FileLog.d(TAG, "setState() " + mState + " -> " + state);
        mState = state;

        // Give the new state to the Handler so the UI Activity can update
        mHandler.obtainMessage(MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
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
        FileLog.d(TAG, "start");

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Start the thread to listen on a BluetoothServerSocket
        createAcceptThreadList();
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

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     * @param socket  The BluetoothSocket on which the connection was made
     * @param device  The BluetoothServer that has been connected
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device, int port) {
        if (D) Log.d(TAG, "connected");
        FileLog.d(TAG, "connected");

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Cancel the accept thread because we only want to connect to one device
        //if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}
        cancelAcceptThreadList();

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        // Send the name of the connected device back to the UI Activity
        Message msg = mHandler.obtainMessage(MESSAGE_DEVICE_NAME);
        Bundle bundle = new Bundle();
        bundle.putString(DEVICE_NAME, device.getName());
        bundle.putInt(DEVICE_PORT, port);
        msg.setData(bundle);
        mHandler.sendMessage(msg);

        setState(STATE_CONNECTED);
    }

    /**
    * 创建所有接受线程
    */
   private void createAcceptThreadList() {
       if (D) Log.d(TAG, "entry createAcceptThreadList...");
       
       mAcceptThreadList.clear();
       
       int i = 0;
       while (i++ < MAX_CHANNEL_SIZE) {
           AcceptThread mAcceptThread = new AcceptThread();
           mAcceptThread.start();
           mAcceptThreadList.add(mAcceptThread);
       }
   }
   
    /**
     * 取消所有接受线程
     */
    private void cancelAcceptThreadList() {
        for (AcceptThread mAcceptThread : mAcceptThreadList) {
            if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}
        }
        
        mAcceptThreadList.clear();
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {
        if (D) Log.d(TAG, "stop");
        FileLog.d(TAG, "stop");
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}
        //if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}
        cancelAcceptThreadList();
        setState(STATE_NONE);
    }

    /**
     * Write to the ConnectedThread in an unsynchronized manner
     * @param out The bytes to write
     * @see ConnectedThread#write(byte[])
     */
    public void write(byte[] out) {
        if (D) Log.d(TAG, "write");
        FileLog.d(TAG, "write");
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
        // Send a failure message back to the Activity
        Message msg = mHandler.obtainMessage(MESSAGE_DISCONNECT);
        Bundle bundle = new Bundle();
        bundle.putString(TOAST, "Unable to connect device");
        msg.setData(bundle);
        mHandler.sendMessage(msg);

        setState(STATE_LISTEN);
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    private void connectionLost() {
        if (D) Log.d(TAG, "connectionLost");
        FileLog.d(TAG, "connectionLost");
        setState(STATE_LISTEN);

        // Send a failure message back to the Activity
        Message msg = mHandler.obtainMessage(MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(TOAST, "Device connection was lost");
        msg.setData(bundle);
        mHandler.sendMessage(msg);

        // Restart the thread to listen on a BluetoothServerSocket
//        if (mAcceptThread == null) {
//            mAcceptThread = new AcceptThread();
//            mAcceptThread.start();
//        }
        createAcceptThreadList();
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * (or until cancelled).
     */
    private class AcceptThread extends Thread {
        // The local server socket
        private BluetoothServerSocket mmServerSocket;

      private int getPortNr(BluetoothServerSocket serverSocket) {
          try {
              // Retrieve the port number. For A&D devices you need this.
              Field mSocketField = BluetoothServerSocket.class.getDeclaredField("mSocket");
              mSocketField.setAccessible(true);
              BluetoothSocket socket = (BluetoothSocket) mSocketField.get(serverSocket);
              mSocketField.setAccessible(false);

              Field mPortField = BluetoothSocket.class.getDeclaredField("mPort");
              mPortField.setAccessible(true);
              int port = (Integer) mPortField.get(socket);
              mPortField.setAccessible(false);

              if (D)
                  Log.d(TAG, "BluetoothListenThread:getPortNr: Listening Port: " + port);

              return port;
          } catch (Exception e) {
              Log.e(TAG, "SensorService: getPortNr ", e);
              return -1;
          }
      }
      
      private BluetoothServerSocket getServerSocket(int port) {
          
          Log.i(TAG, "getServerSocket-port:" + port);

          Method m = null;
          try {
              m = mAdapter.getClass().getMethod("listenUsingRfcommOn", new Class[] { int.class });
          } catch (SecurityException e) {
              e.printStackTrace();
          } catch (NoSuchMethodException e) {
              e.printStackTrace();
          }

          try {
              return (BluetoothServerSocket) m.invoke(mAdapter, port);
          } catch (IllegalArgumentException e) {
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          } catch (InvocationTargetException e) {
              e.printStackTrace();
          }
          
          if (D) Log.d(TAG, "last ServerSocket mPort: " + port + ", mmServerSocket:" + mmServerSocket);
          FileLog.d(TAG, "last ServerSocket mPort: " + port + ", mmServerSocket:" + mmServerSocket);
          
          return null;
      }
      
        public void run() {
            if (D) Log.d(TAG, "BEGIN mAcceptThread" + this);
            FileLog.d(TAG, "BEGIN mAcceptThread" + this);
            
            try {
                if (D) Log.d(TAG, "begin listenUsingInsecureRfcommWithServiceRecord.");
                
                if (mPort != -1) {
                    int tmpPort = mPort; // 因为getServerSocket执行时间较久
                    mPort = -1;
                    mmServerSocket = getServerSocket(tmpPort);
                } else {
                    mmServerSocket = mAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID);
                }
                
                if (D) Log.d(TAG, "end listenUsingInsecureRfcommWithServiceRecord.");
            } catch (IOException e) {
                //e.printStackTrace();
            }
            
            if (mmServerSocket == null) {
                return;
            }
            
            setName("AcceptThread-" + mmServerSocket);
            
            BluetoothSocket socket = null;
            
            if (D) Log.d(TAG, "mAcceptThread - mState：" + mState);

            // Listen to the server socket if we're not connected
            while (mState != STATE_CONNECTED) {
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                    if (D) Log.d(TAG, "mAcceptThread - accept");
                    
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "accept() failed", e);
                    break;
                }

                if (D) Log.d(TAG, "mAcceptThread - socket：" + socket);

                // If a connection was accepted
                if (socket != null) {

                	// Close the server socket so no one else can connect
                    cancel();

                    synchronized (BluetoothServerService4And.this) {
                        switch (mState) {
                        case STATE_LISTEN:
                            Log.d(TAG, "AcceptThread - STATE_LISTEN");
                            FileLog.d(TAG, "AcceptThread - STATE_LISTEN");
                        case STATE_CONNECTING:
                            Log.d(TAG, "AcceptThread - STATE_CONNECTING");
                            FileLog.d(TAG, "AcceptThread - STATE_CONNECTING");
                            // Situation normal. Start the connected thread.
                            int port = getPortNr(mmServerSocket);
                            connected(socket, socket.getRemoteDevice(), port);
                            break;
                        case STATE_NONE:
                            Log.d(TAG, "AcceptThread - STATE_NONE");
                            FileLog.d(TAG, "AcceptThread - STATE_NONE");
                        case STATE_CONNECTED:
                            Log.d(TAG, "AcceptThread - STATE_CONNECTED");
                            FileLog.d(TAG, "AcceptThread - STATE_CONNECTED");
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
            if (D) Log.d(TAG, "END mAcceptThread");
            FileLog.d(TAG, "END mAcceptThread");
        }

        public void cancel() {
            if (D) Log.d(TAG, "cancel " + this);
            FileLog.d(TAG, "cancel " + this);
            try {
                if (mmServerSocket != null) {
                    mmServerSocket.close();
                }
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
                BluetoothServerService4And.this.start();
                return;
            }

            // Reset the ConnectThread because we're done
            synchronized (BluetoothServerService4And.this) {
                mConnectThread = null;
            }

            // Start the connected thread
            connected(mmSocket, mmDevice, mPort);
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

        public ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "create ConnectedThread");
            FileLog.d(TAG, "create ConnectedThread");
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

        public void run() {
            Log.d(TAG, "BEGIN mConnectedThread");
            FileLog.d(TAG, "BEGIN mConnectedThread");

            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    byte[] buffer = new byte[1024];
                    int bytes;

                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    Log.d(TAG, "Received "+bytes+" bytes");
                    FileLog.d(TAG, "Received "+bytes+" bytes");

                    // Send the obtained bytes to the UI Activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    if (mState == STATE_NONE) {
                        connectionFailed();
                    } else {
                        connectionLost();
                    }
                    break;
                }
            }
        }

        /**
         * Write to the connected OutStream.
         * @param buffer  The bytes to write
         */
        public void write(byte[] buffer) {
            if (D) Log.d(TAG, "ConnectedThread, write");
            FileLog.d(TAG, "ConnectedThread, write");
            try {
                mmOutStream.write(buffer);

                // Share the sent message back to the UI Activity
                mHandler.obtainMessage(MESSAGE_WRITE, -1, -1, buffer)
                        .sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Exception during write", e);
            }
        }

        public void cancel() {
            if (D) Log.d(TAG, "ConnectedThread, cancel");
            FileLog.d(TAG, "ConnectedThread, cancel");
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
}
