/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Test_XmlFactoryActivity.java
 * classes : com.cking.phss.test.Test_XmlFactoryActivity
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-23 上午11:20:20
 */
package com.cking.phss.test;

import java.util.List;

import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.interfaces.ITag;
import net.xinhuaxing.util.xml.resources.ResourcesPullService;
import net.xinhuaxing.util.xml.resources.tags.BaseTag;
import net.xinhuaxing.util.xml.resources.tags.ItemTag;
import net.xinhuaxing.util.xml.resources.tags.ResourcesTag;
import net.xinhuaxing.util.xml.resources.tags.StringArrayTag;
import net.xinhuaxing.util.xml.resources.tags.constants.TagConstants;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cking.phss.R;
import com.cking.phss.xml.BodyPullService;
import com.cking.phss.xml.bean.Body;
import com.cking.phss.xml4jgxx.JgxxConfigPullService;
import com.cking.phss.xml4jgxx.tags.ConfigTag;
import com.cking.phss.xml4jgxx.tags.DeviceInfoTag;

/**
 * com.cking.phss.test.Test_XmlFactoryActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 上午11:20:20
 */
public class Test_XmlFactoryActivity extends Activity {
    private static final String TAG = "Test_XmlFactoryActivity";

    private static final String XML_DIR = Environment.getExternalStorageDirectory().getPath()/* + "/wltlib/xml/"*/;
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_xml_factory);
        //testBodyPullService();
        //testResourcesPullService();
        testJgxxConfigPullService();
    }
    
	/**
     * 
     */
    private void testJgxxConfigPullService() {
        try {
            final ConfigTag resourcesTag = (ConfigTag) JgxxConfigPullService
                    .read(Constants.PHSS_XML_DIR + "appconfig.xml");
            Button button1 = (Button) findViewById(R.id.button1);
            button1.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View arg0) {
                    String textWriter = "";
                    EditText editText1 = (EditText) findViewById(R.id.editText1);
                    List<com.cking.phss.xml4jgxx.tags.BaseTag> tagList = resourcesTag.findTagsByAttrValue(
                            new String[] { DeviceInfoTag.XML_TAG },
                            new String[] { com.cking.phss.xml4jgxx.tags.constants.TagConstants.XML_ATTR_ID }, editText1
                                    .getText().toString());
                    for (com.cking.phss.xml4jgxx.tags.BaseTag tag : tagList) {
                        DeviceInfoTag rootFolder = (DeviceInfoTag) tag;
                            textWriter += "序号：" + rootFolder.attrBean.getId() + ", 仪器名称："
                                    + rootFolder.attrBean.getName() + ", 仪器类型："
                                            + rootFolder.attrBean.getType() + ", 品牌："
                                                    + rootFolder.attrBean.getBrand() + ", 型号："
                                                            + rootFolder.attrBean.getModel() + ", 编号："
                                                                    + rootFolder.attrBean.getSerialNo() + "\r\n";
                    }
                    EditText editText2 = (EditText) findViewById(R.id.editText2);
                    editText2.setText(textWriter);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void testResourcesPullService() {
		try {
			final ResourcesTag resourcesTag = (ResourcesTag) ResourcesPullService
                    .read(Constants.PHSS_XML_DIR + "values.xml");
            Button button1 = (Button) findViewById(R.id.button1);
            button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					String textWriter = "";
					EditText editText1 = (EditText) findViewById(R.id.editText1);
					List<BaseTag> tagList = resourcesTag.findTagsByAttrValue(
							new String[] { StringArrayTag.XML_TAG },
							new String[] { TagConstants.XML_ATTR_NAME }, editText1
									.getText().toString());
					for (BaseTag tag : tagList) {
						StringArrayTag rootFolder = (StringArrayTag) tag;
						textWriter += "字段：" + rootFolder.attrBean.getName() + "\r\n";
						for (ITag subTag : rootFolder.list()) {
							ItemTag item = (ItemTag) subTag;
							textWriter += "ID：" + item.attrBean.getId() + ", Value："
									+ item.getValue() + "\r\n";
						}
					}
		            EditText editText2 = (EditText) findViewById(R.id.editText2);
					editText2.setText(textWriter);
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private void testBodyPullService() {
        try {
            Body yydlyz = BodyPullService.read(XML_DIR + "/用户登录验证.xml");
            EditText editText1 = (EditText) findViewById(R.id.editText1);
            String text = "Request: {OrgCode=" + yydlyz.mRequest.mOrgCode + 
            ", OperType=" + yydlyz.mRequest.mOperType + ", UserN=" + yydlyz.mRequest.mUserN.mUserN + 
            ", PassWord=" + yydlyz.mRequest.mPassWord.mPassWord + "}";
            editText1.setText(text);
            
            BodyPullService.write(yydlyz, XML_DIR + "/用户登录验证-new.xml");
            
            Body ddgjdm = BodyPullService.read(XML_DIR + "/得到国籍代码.xml");
            EditText editText2 = (EditText) findViewById(R.id.editText2);
            String text2 = "Request: {OrgCode=" + ddgjdm.mRequest.mOrgCode + 
            ", OperType=" + ddgjdm.mRequest.mOperType + ", Nationality=" + ddgjdm.mRequest.mNationality.mNationality + 
            ", NationalityCD=" + ddgjdm.mResponse.mNationalityCD.mNationalityCD + "}";
            editText2.setText(text2);
            
            BodyPullService.write(ddgjdm, XML_DIR + "/得到国籍代码-new.xml");
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
}
