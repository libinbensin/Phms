/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;

import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.util.FileFactory;
import net.xinhuaxing.util.ZipUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.activity.UpdateActivity;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.http.HttpDownloader;
import com.cking.phss.update.UpdateInfo;
import com.cking.phss.update.VersionChecked;
import com.cking.phss.update.VersionInfo;
import com.cking.phss.util.CommonUtil;
import com.cking.phss.util.ProgressDialogUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.GuidePager;

/**
 * 高级设置
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
/**
 * @author Administrator
 * 
 */
public class XtszBbgxPage extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage";
    private static final boolean D = true;
    private Context mContext = null;

    private GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private UpdateHandler updateHandler = new UpdateHandler();
    /**
     * 全局控件
     */
    private RadioGroup mTabRadios = null;
//    private Button mSaveButton = null;
//    private Button mResultButton = null;
//    private Button mUploadButton = null;
//    private Button mRegisterButton = null;
//    public static TextView mRegisterIdText = null;

    private Toast mToast = null;

    public static boolean sHasData = false;

    /**
     * 选择的TAB页编号
     */
    private static int sTabRadioId = 0;

    RadioGroup radiogroup_gjsz;
    LinearLayout layout_content;

    TextView bbxxTextView;
    Button qrButton;
    int status;
    Button zxButton;
    TextView dataTextView;
    UpdateStatusHandler updateStatusHandler = new UpdateStatusHandler();

    /**
     * @param context
     */
    public XtszBbgxPage(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszBbgxPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(final Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_xtsz_bbgx_layout, this);

        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        // 添加页面
        bbxxTextView = (TextView) findViewById(R.id.bbxxTextView);
        qrButton = (Button) findViewById(R.id.qrButton);
        zxButton = (Button) findViewById(R.id.zxButton);
        dataTextView = (TextView) findViewById(R.id.dataTextView);
        setDataVersion();
        if (Global.isLocalLogin) {
            String text = "当前版本号：" + CommonUtil.getVersion(mContext) + "\n" + "当前是本地登录，无法判断是否需要更新。";
            bbxxTextView.setText(text);
            qrButton.setEnabled(false);
            zxButton.setEnabled(false);
        } else {
            String text = "当前版本号：" + CommonUtil.getVersion(mContext) + "\n" + "当前已经是最新版本，无需更新。";
            bbxxTextView.setText(text);
            qrButton.setEnabled(false);
            zxButton.setEnabled(true);
            // 开启检查版本更新的线程
            new Thread(new CheckVersionThread()).start();
            zxButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    ProgressDialogUtil.showProgressDialog(mContext, "正在更新数据包", "请稍后...");
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            VersionInfo versionInfo = VersionChecked.getServiceVersion(Global.orgCode, "DATAFILE");
                            HttpDownloader downloader = new HttpDownloader();
                            int result = downloader.downFile(versionInfo.url, "phms/",
                                    "phms.zip");
                            if (result >= 0) { // 下载成功，解压
                                try {
                                    ZipUtils.upZipFile(new File(Constants.PHSS_ROOT_DIR
                                            + "phms.zip"), Constants.PHSS_ROOT_DIR);
                                    setDataVersion();
                                    mToast.setText("更新数据包成功");
                                    mToast.show();
                                } catch (ZipException e) {
                                    e.printStackTrace();
                                    mToast.setText("更新数据包失败");
                                    mToast.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    mToast.setText("更新数据包失败");
                                    mToast.show();
                                }
                            } else {
                                mToast.setText("下载数据包失败");
                                mToast.show();
                            }
                            ProgressDialogUtil.hideProgressDialog();
                        }

                    }).start();
                }
            });
        }
    }

    /**
     * 
     */
    private void setDataVersion() {
        ((Activity) mContext).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                byte[] data = FileFactory.read(new File(Constants.PHSS_ROOT_DIR + "version.txt"));
                if (data != null) {
                    String lastUpdateTime = new String(data);
                    dataTextView.setText("当前数据包版本：" + lastUpdateTime + "\r\n点击安装最新数据包：");
                } else {
                    dataTextView.setText("点击安装最新数据包：");
                }
            }

        });
    }

    private class CheckVersionThread implements Runnable {
        @Override
        public void run() {
            final VersionChecked versionUpdate = new VersionChecked(mContext);
            int checkStatus = versionUpdate.check();
            Log.i(TAG, "checkStatus=" + checkStatus);
            if (checkStatus == VersionChecked.CHECK_NO_UPDATE) {// not
                updateHandler.sendEmptyMessage(0x4);
                return;
            }
            String state = Environment.getExternalStorageState();
            if (!Environment.MEDIA_MOUNTED.equals(state)) {
                updateHandler.sendEmptyMessage(0x1);// there is no
                                                    // sd card ,so
                                                    // can not do
                                                    // update
                                                    // operate
                return;
            }
            if (checkStatus == VersionChecked.CHECK_TO_INSTALL) {
                updateHandler.sendEmptyMessage(0x2);
            } else if (checkStatus == VersionChecked.CHECK_TO_UPDATE) {
                Message msg = updateHandler.obtainMessage();
                msg.what = 0x3;
                if (versionUpdate.versionInfo != null) {
                    String url = versionUpdate.versionInfo.url;
                    if (url != null && !url.equals("")) {
                        msg.obj = url;
                        updateHandler.sendMessage(msg);
                    }
                }
            }
        }
    }

    private class UpdateHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x1) {
                // popup a dialog to remind user this is no sd card ,so can not
                // do update
                UpdateInfo.getInstance().setLastCheckStatus(UpdateInfo.LAST_CHECK_STATUS_FAILED);
                Builder b = new AlertDialog.Builder(mContext).setTitle("更新失败").setMessage(
                        "由于目前没有插入SD卡，或SD卡不能读写，更新失败");
                b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
            } else if (msg.what == 0x2) {
                String text = "当前版本号：" + CommonUtil.getVersion(mContext) + "\n" + "已下载最新版本，请安装。";
                bbxxTextView.setText(text);
                qrButton.setEnabled(true);
                qrButton.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        Intent intent = new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClass(mContext, UpdateActivity.class);
                        intent.putExtra("status", UpdateInfo.LAST_CHECK_STATUS_NO_INSTALL);
                        mContext.startActivity(intent);
                    }
                });
            } else if (msg.what == 0x3) {
                final String url = (String) msg.obj;
                String text = "当前版本号：" + CommonUtil.getVersion(mContext) + "\n" + "当前不是最新版本，请点击更新。";
                bbxxTextView.setText(text);
                qrButton.setEnabled(true);
                qrButton.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        Builder b = new AlertDialog.Builder(mContext).setTitle("更新信息").setMessage(
                                "您当前的版本不是最新版本，是否需要更新");
                        b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent();
//                                intent.putExtra("url", url); 
//                                mContext.startService(intent);
                                ProgressDialogUtil.showProgressDialog(mContext, "正在更新程序", "请稍后...");
                                UpdateInfo.getInstance().setLastCheckFile("phms.apk");
                                new Thread(new Runnable() {

                                    @Override
                                    public void run() {
                                        Looper.prepare();
                                        HttpDownloader downloader = new HttpDownloader();
                                        String lastCheckFile=UpdateInfo.getInstance().getLastCheckFile();
                                        int result = downloader.downFile(url, "phms/",
                                                lastCheckFile);
                                        if (result >= 0) { // 下载成功，解压
                                            updateStatusHandler.sendEmptyMessage(0x1);
                                            mToast.setText("更新程序成功");
                                            mToast.show();
                                        } else {
                                            mToast.setText("下载程序失败");
                                            mToast.show();
                                        }
                                        ProgressDialogUtil.hideProgressDialog();
                                    }

                                }).start();
                            }
                        });
                        b.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        b.create().show();
                    }
                });
            } else if (msg.what == 0x4) {
                String text = "当前版本号：" + CommonUtil.getVersion(mContext) + "\n" + "当前已经是最新版本，无需更新。";
                bbxxTextView.setText(text);
                qrButton.setEnabled(false);
            }
        }
    }

    @Override
    public void setValue() {
    }

    @Override
    public boolean getValue() {
        return true;
    }

    public void getBeanFromDB() {
        if (beanMap == null)
            return;
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                // if (isSucc) {
                beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                setValue();
                // }
            }
        });

    }


    private class UpdateStatusHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
//            Intent intent=new Intent();
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setClass(mContext, UpdateActivity.class);
//            if (msg.what == 0x1) {// update succeed
//                UpdateInfo.getInstance().setLastCheckStatus(UpdateInfo.LAST_CHECK_STATUS_SUCCEED);
//                intent.putExtra("status", UpdateInfo.LAST_CHECK_STATUS_SUCCEED);
//            } else {
//                UpdateInfo.getInstance().setLastCheckStatus(UpdateInfo.LAST_CHECK_STATUS_FAILED);
//                intent.putExtra("status", UpdateInfo.LAST_CHECK_STATUS_FAILED);
//            }
//            mContext.startActivity(intent);

            Intent intent = new Intent();
            String fileName=UpdateInfo.getInstance().getLastCheckFile();
            File file=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/phms/"+fileName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),
                                              "application/vnd.android.package-archive");
            mContext.startActivity(intent);
        }
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
