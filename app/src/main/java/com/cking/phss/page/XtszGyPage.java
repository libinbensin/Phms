/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.application.MyApplication;
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
public class XtszGyPage extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage";
    private static final boolean D = true;
    private Context mContext = null;

    private GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
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
    
    Button ckgyxxButton;

    /**
     * @param context
     */
    public XtszGyPage(Context context) {
        super(context);
        mContext = context;
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszGyPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(final Context context) {
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_xtsz_gy_layout, this);

        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        ImageView gyImageView = (ImageView) findViewById(R.id.gyImageView);
        String externalPath = Environment.getExternalStorageDirectory() + "/phms/image/";
        String aboutPath = externalPath + "about.png";//
        gyImageView.setImageURI(Uri.parse(aboutPath));
//        ckgyxxButton = (Button) findViewById(R.id.ckgyxxButton);
//        ckgyxxButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                /*String versionName = "";
//                try {
//                    PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
//                    versionName = pi.versionName;
//                } catch (NameNotFoundException e) {
//                    e.printStackTrace();
//                }
//                String text = "应用程序名:" + mContext.getString(R.string.app_name)
//                        + versionName + "\r\n" + "版权所有：上海先德医疗设备有限公司\r\n"
//                        + "技术支持：400-772-882";
//                showTextAlertDialog(text, "关于");*/
//                /*关于这一部分换成图片*/
//                LayoutInflater inflater = LayoutInflater.from(mContext);
//                final View view = inflater.inflate(R.layout.common_alertdialog, null);
//
//                ImageView about_img = (ImageView) view.findViewById(R.id.about_img);
//                
//                if (ContextUtil.sdCardCanRead()) {
//                    String externalPath = Environment.getExternalStorageDirectory() + "/phms/image/";
//                    String aboutPath = externalPath + "about.png";// 
//                    try {
//                        Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(aboutPath)));
//                        if(bitmap!=null){
//                            about_img.setBackgroundDrawable(new BitmapDrawable(bitmap));
//                        }else {
//                            about_img.setImageResource(R.drawable.about1);
//                        }
//                    } catch (FileNotFoundException e) {
//                        Log.e(TAG, e.toString());
//                    }
//                }
//
//                
//                /*final Dialog dialog=new Dialog(mContext, R.style.Translucent_NoTitle);
//                dialog.setContentView(view);
//                dialog.show();
//                Button about_cancelButton=(Button)view.findViewById(R.id.about_cancel);
//                about_cancelButton.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.cancel();
//                    }
//                });*/
//                
//               final  PopupWindow mPopupWindow=new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
//                mPopupWindow.setFocusable(true);
//                mPopupWindow.setOutsideTouchable(true);
//                mPopupWindow.setTouchable(true);
//                mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
//                Button cancelBtn=(Button)view.findViewById(R.id.about_cancel);
//                cancelBtn.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(mPopupWindow!=null||mPopupWindow.isShowing()){
//                            mPopupWindow.dismiss();
//                        }
//                    }
//                });
//                view.invalidate();
//                
//                mPopupWindow.showAtLocation((LinearLayout)XtszGyPage.this.getParent(), Gravity.CENTER, 0, 0);
//               /* final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                builder.setCancelable(false);
//                builder.setView(view);
//                
//                builder.setPositiveButton("返回", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        dialog.cancel();
//                    }
//                });
//                builder.show();*/
//            }
//        });
    }
    
    /**
     * @param strCHECKINID
     * @return
     */
    public String assemblePacket_SaveResults(String strCHECKINID) {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        String result = "";
        String CHECKINID = strCHECKINID;
        String DRIVERID = "";
        String PROJECTCODE = "";
        String PROJECTNAME = "";
        String RESULT = "";
        String UNIT = "";
        String REFERENCE = "";
        String EXCEPTIONTIPS = "";
        String TRUN = "";
        String CHECKDATE = "";
        String DOC_ID = "";
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return null;
        }
        DOC_ID = login1.response.doctorID;

        String head = "<Results>";
        result += head;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CHECKINID = strCHECKINID;
        // 血压
        {
            DRIVERID = "02";
            // 收缩压
            if (!mJktj_kstj.getsBP().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getsBP().trim()) > 0) {
                PROJECTCODE = "020001";
                PROJECTNAME = "收缩压";
                RESULT = String.valueOf(mJktj_kstj.getsBP());
                UNIT = "mmHg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 舒张压
            if (!mJktj_kstj.getdBP().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getdBP().trim()) > 0) {
                PROJECTCODE = "020002";
                PROJECTNAME = "舒张压";
                RESULT = String.valueOf(mJktj_kstj.getdBP());
                UNIT = "mmHg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 脉搏
            if (!mJktj_kstj.getxL().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getxL().trim()) > 0) {
                PROJECTCODE = "020003";
                PROJECTNAME = "脉搏";
                RESULT = String.valueOf(mJktj_kstj.getxL());
                UNIT = "次/min";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 血压结论
            if (mJktj_kstj.getxYJL() != null && !mJktj_kstj.getxYJL().trim().equals("")) {
                PROJECTCODE = "020005";
                PROJECTNAME = "血压结论";
                RESULT = mJktj_kstj.getxYJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 胆固醇
        {
            DRIVERID = "17";
            // 胆固醇值
            if (!mJktj_kstj.getdGC().equals("") && Float.parseFloat(mJktj_kstj.getdGC()) > 0) {
                PROJECTCODE = "170001";
                PROJECTNAME = "胆固醇值";
                RESULT = String.valueOf(mJktj_kstj.getdGC());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 胆固醇结论
            if (mJktj_kstj.getdGCJL() != null && !mJktj_kstj.getdGCJL().equals("")) {
                PROJECTCODE = "170002";
                PROJECTNAME = "胆固醇结论";
                RESULT = mJktj_kstj.getdGCJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 甘油三酯
        {
            DRIVERID = "16";
            // 甘油三酯值
            if (!mJktj_kstj.getgYSZ().equals("") && Float.parseFloat(mJktj_kstj.getgYSZ()) > 0) {
                PROJECTCODE = "160001";
                PROJECTNAME = "甘油三酯值";
                RESULT = String.valueOf(mJktj_kstj.getgYSZ());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 甘油三酯结论
            if (mJktj_kstj.getgYSZJL() != null && !mJktj_kstj.getgYSZJL().equals("")) {
                PROJECTCODE = "160002";
                PROJECTNAME = "甘油三酯结论";
                RESULT = mJktj_kstj.getgYSZJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        // 高密度脂蛋白
        {
            DRIVERID = "20";
            // 高密度脂蛋白值
            if (!mJktj_kstj.getHDL().equals("") && Float.parseFloat(mJktj_kstj.getHDL()) > 0) {
                PROJECTCODE = "200001";
                PROJECTNAME = "高密度脂蛋白值";
                RESULT = String.valueOf(mJktj_kstj.getHDL());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 高密度脂蛋白结论
            if (mJktj_kstj.getHDLR() != null && !mJktj_kstj.getHDLR().equals("")) {
                PROJECTCODE = "200002";
                PROJECTNAME = "高密度脂蛋白结论";
                RESULT = mJktj_kstj.getHDLR();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        // 低密度脂蛋白
        {
            DRIVERID = "21";
            // 低密度脂蛋白值
            if (!mJktj_kstj.getLDL().equals("") && Float.parseFloat(mJktj_kstj.getLDL()) > 0) {
                PROJECTCODE = "210001";
                PROJECTNAME = "低密度脂蛋白值";
                RESULT = String.valueOf(mJktj_kstj.getLDL());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 低密度脂蛋白结论
            if (mJktj_kstj.getLDLR() != null && !mJktj_kstj.getLDLR().equals("")) {
                PROJECTCODE = "210002";
                PROJECTNAME = "低密度脂蛋白结论";
                RESULT = mJktj_kstj.getLDLR();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        // 血糖
        {
            DRIVERID = "03";
            // 血糖值
            if (!mJktj_kstj.getxTValue().equals("")
                    && Float.parseFloat(mJktj_kstj.getxTValue()) > 0) {
                PROJECTCODE = "030001";
                PROJECTNAME = "血糖值";
                RESULT = String.valueOf(mJktj_kstj.getxTValue());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 血糖结论
            if (mJktj_kstj.getxTJL() != null && !mJktj_kstj.getxTJL().equals("")) {
                PROJECTCODE = "030002";
                PROJECTNAME = "血糖结论";
                RESULT = mJktj_kstj.getxTJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 腰围
        {
            DRIVERID = "18";
            // 胸围
            if (!mJktj_kstj.getBust().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getBust().trim()) > 0) {
                PROJECTCODE = "180001";
                PROJECTNAME = "胸围";
                RESULT = String.valueOf(mJktj_kstj.getBust());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 腰围
            if (!mJktj_kstj.getWaist().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.getWaist().trim()) > 0) {
                PROJECTCODE = "180002";
                PROJECTNAME = "腰围";
                RESULT = String.valueOf(mJktj_kstj.getWaist());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 臀围
            if (!mJktj_kstj.gethIP().trim().equals("")
                    && Integer.parseInt(mJktj_kstj.gethIP().trim()) > 0) {
                PROJECTCODE = "180003";
                PROJECTNAME = "臀围";
                RESULT = String.valueOf(mJktj_kstj.gethIP());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
            // 腰臀比结论
            if (mJktj_kstj.getYtbjl() != null && !mJktj_kstj.getYtbjl().trim().equals("")) {
                PROJECTCODE = "180004";
                PROJECTNAME = "结论";
                RESULT = mJktj_kstj.getYtbjl();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }
        // 人体成分
        {
            DRIVERID = "08";
            // 体脂肪率
            if (!mJktj_kstj.getFat().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getFat().trim()) > 0) {
                PROJECTCODE = "080004";
                PROJECTNAME = "体脂肪率";
                RESULT = String.valueOf(mJktj_kstj.getFat());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 基础代谢
            if (!mJktj_kstj.getbMR().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getbMR().trim()) > 0) {
                PROJECTCODE = "080006";
                PROJECTNAME = "基础代谢";
                RESULT = String.valueOf(mJktj_kstj.getbMR());
                UNIT = "Kcal";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 相对基础代谢
            if (!mJktj_kstj.getrBMR().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getrBMR().trim()) > 0) {
                PROJECTCODE = "080007";
                PROJECTNAME = "相对基础代谢";
                RESULT = String.valueOf(mJktj_kstj.getrBMR());
                UNIT = "Kcal";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 肌肉含量
            if (!mJktj_kstj.getMus().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getMus().trim()) > 0) {
                PROJECTCODE = "080011";
                PROJECTNAME = "肌肉含量";
                RESULT = String.valueOf(mJktj_kstj.getMus());
                UNIT = "Kg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 水分含量
            if (!mJktj_kstj.gettBW().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.gettBW().trim()) > 0) {
                PROJECTCODE = "080012";
                PROJECTNAME = "水分含量";
                RESULT = String.valueOf(mJktj_kstj.gettBW());
                UNIT = "Kg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 阻抗
            if (!mJktj_kstj.getiMP().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getiMP().trim()) > 0) {
                PROJECTCODE = "080013";
                PROJECTNAME = "阻抗";
                RESULT = String.valueOf(mJktj_kstj.getiMP());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // bmi
            if (!mJktj_kstj.getbMI().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getbMI().trim()) > 0) {
                PROJECTCODE = "080016";
                PROJECTNAME = "BMI";
                RESULT = String.valueOf(mJktj_kstj.getbMI());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 结论
            if (!mJktj_kstj.getCname().trim().equals("")) {
                PROJECTCODE = "080017";
                PROJECTNAME = "结论建议";
                RESULT = String.valueOf(mJktj_kstj.getCname());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 内脏脂肪
            if (!mJktj_kstj.getVisceralFat().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getVisceralFat().trim()) > 0) {
                PROJECTCODE = "080018";
                PROJECTNAME = "BMI";
                RESULT = String.valueOf(mJktj_kstj.getVisceralFat());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 骨含量
            if (!mJktj_kstj.getBone().trim().equals("")
                    && Float.parseFloat(mJktj_kstj.getBone().trim()) > 0) {
                PROJECTCODE = "080019";
                PROJECTNAME = "Kg";
                RESULT = String.valueOf(mJktj_kstj.getBone());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }

            // 身体类型
            if (!mJktj_kstj.getCtype().trim().equals("")) {
                PROJECTCODE = "080020";
                PROJECTNAME = "";
                RESULT = String.valueOf(mJktj_kstj.getCtype());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = "<Item>" + "<CHECKINID>" + CHECKINID + "</CHECKINID>" + "<DRIVERID>"
                        + DRIVERID + "</DRIVERID>" + "<PROJECTCODE>" + PROJECTCODE
                        + "</PROJECTCODE>" + "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>"
                        + "<RESULT>" + RESULT + "</RESULT>" + "<UNIT>" + UNIT + "</UNIT>"
                        + "<REFERENCE>" + REFERENCE + "</REFERENCE>" + "<EXCEPTIONTIPS>"
                        + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" + "<TRUN>" + TRUN + "</TRUN>"
                        + "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID
                        + "</DOC_ID>" + "</Item>";
                result += item;
            }
        }

        String tail = "</Results>";
        result += tail;
        Log.i(TAG, result);
        return result;
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

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
