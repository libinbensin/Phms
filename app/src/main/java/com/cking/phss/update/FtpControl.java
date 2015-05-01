package com.cking.phss.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

import com.cking.net.ftp.FTPClient;
import com.cking.net.ftp.FTPReply;

/**
 * FTP Operate
 * 
 * @author taowencong
 * 
 */
public class FtpControl {
    private FTPClient ftpClient;
    private String hostName;
    private String port;
    private String filePath;

    private static final String USERNAME = "hande";
    private static final String PASSWORD = "hande";
    private static final String TAG = "FtpControl";

    public FtpControl(String ftpUrl) {
        this.ftpClient = new FTPClient();
        Log.i(TAG, ftpUrl);
        parserUrl(ftpUrl);
    }

    private void parserUrl(String ftpUrl) {
        int index = ftpUrl.indexOf(":");
        ftpUrl = ftpUrl.substring(index + 3, ftpUrl.length());
        index = ftpUrl.indexOf(":");
        if (index == -1) {
            index = ftpUrl.indexOf("/");
            hostName = ftpUrl.substring(0, index);
            filePath = ftpUrl.substring(index + 1, ftpUrl.length());
        } else {
            hostName = ftpUrl.substring(0, index);
            int a = ftpUrl.indexOf("/");
            port = (String) ftpUrl.substring(index + 1, a);
            filePath = ftpUrl.substring(a + 1, ftpUrl.length());
        }
    }

    /**
     * open ftp connect
     * 
     * @throws SocketException
     * 
     * @throws IOException
     * @return connect succeed or failed
     */
    public boolean openConnect() throws SocketException, IOException, Exception {
        ftpClient.setControlEncoding("GBK");
        ftpClient.setConnectTimeout(30*1000);//set the connect time out 
        ftpClient.setDataTimeout(30*1000);//set the data transfer time out
        int reply; // check return code
        // build the connect
        if (port == null) {
            ftpClient.connect(hostName, 21);
        } else {
            ftpClient.connect(hostName, Integer.parseInt(port));
        }
        // get the connect return code and check the connect status
        reply = ftpClient.getReplyCode();
        Log.i(TAG, ftpClient.getReplyString());
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            return false;
        }
        // login
        ftpClient.login(USERNAME, PASSWORD);
        // get the connect return code and check the login status
        reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            return false;
        } else {// login succeed
//            FTPClientConfig config = new FTPClientConfig();
//            config.setServerLanguageCode("zh");
//            ftpClient.configure(config);
            // Set the current data connection mode to
            // PASSIVE_LOCAL_DATA_CONNECTION_MODE
            ftpClient.enterLocalPassiveMode();
            // set transferred file type to binary file type
            ftpClient.setFileType(com.cking.net.ftp.FTP.BINARY_FILE_TYPE);
        }
        return true;
    }

    /**
     * close ftp connect
     * 
     * @throws IOException
     */
    public void closeConnect() throws IOException {
        if (ftpClient != null) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     * download the newest version
     * @return download succeed or failed
     */
    public boolean startDownload() throws IOException {
        // get the file name base on the filePath ,and use the fileName to
        // create a file on the sdCard
        int index = filePath.lastIndexOf("/");
        String fileName = filePath.substring(index + 1, filePath.length());

        File temp = new File(Environment.getExternalStorageDirectory(), "phms/" + fileName);
        while (!temp.exists()) {
            temp.createNewFile();
            SystemClock.sleep(100);
        }
        OutputStream out = new FileOutputStream(temp);
        InputStream in;
//        in = ftpClient.retrieveFileStream("PHMS20121215.apk");
        in = ftpClient.retrieveFileStream(filePath);
        if (in != null) { // if it is not null ,do file operate
            byte[] bytes = new byte[1024 * 8];
            int c;
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
            }
            in.close();
            out.close();
            boolean isDo = ftpClient.completePendingCommand();
            if (isDo) {// update finished and succeed
                UpdateInfo.getInstance().setLastCheckFile(fileName);
                return true;
            } else {// update finished but failed
                return false;
            }
        }
        return false;
    }

}
