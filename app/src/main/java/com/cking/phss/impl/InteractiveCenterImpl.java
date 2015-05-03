/* Cking Inc. (C) 2012. All rights reserved.
 *
 * InteractiveCenterImpl.java
 * classes : com.okis.happyguide.impl.InteractiveCenterImpl
 * @author 刘军鹏
 * V 1.0.0
 * Create at 2012-8-21 下午02:37:07
 */
package com.cking.phss.impl;

import java.io.IOException;
import java.util.HashMap;

import android.content.Context;

import com.cking.phss.global.Global;
import com.cking.phss.http.IServerRev;
import com.cking.phss.http.SocketModelImpl;
import com.cking.phss.util.Constant;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.xml.BodyPullService;
import com.cking.phss.xml.bean.Body;

/**发送协议和数据解析
 * com.okis.happyguide.impl.InteractiveCenterImpl
 * @author 刘军鹏 <br/>
 * create at 2012-8-21 下午02:37:07
 */
public class InteractiveCenterImpl {
    private static final String TAG = "InteractiveCenterImpl";
    private static InteractiveCenterImpl single=null;
    private Context context;
    private InteractiveCenterImpl(){
        context=MyApplication.getInstance().getApplicationContext();
    }
    public static InteractiveCenterImpl getInstance(){
        if(single==null){
            single=new InteractiveCenterImpl();
        }
        return single;
    }
    /**
     * 请求登陆
     * @param rev 回调接口
     * @throws IOException 
     * @throws IllegalStateException 
     * @throws IllegalArgumentException 
     */
    public void sendRequestLogin(Body xml, IServerRev rev) throws IllegalArgumentException,
            IllegalStateException, IOException {
        String date = BodyPullService.print(xml);
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        hashmap.put("XmlString", date);

        SocketModelImpl.socketPostByString(Global.webserviceUrl, hashmap,
                Constant.HTTP_ID_LOGIN, rev);
    }

    public synchronized void onRecv(IServerRev rev,int requestMark, int socketMark,Object obj){
        if(socketMark==IServerRev.HTTP_SUCCESS){
            switch(requestMark){
                case Constant.HTTP_ID_LOGIN:
                    rev.onRecv(requestMark, obj, true);
                    break;
                
                // TODO 其他网络通讯回调入口
            }
        } else {
            TispToastFactory.getToast(context, "请求失败，请检查下网络。").show();
        }
    }
}
