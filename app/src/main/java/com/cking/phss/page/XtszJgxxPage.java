package com.cking.phss.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.xml4jgxx.beans.AttrBean;
import com.cking.phss.xml4jgxx.tags.DeviceInfoTag;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 
 * @author tao
 * 
 */
public class XtszJgxxPage extends LinearLayout implements IPage {
	private static final String TAG = "XtszJgxxPage";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();

    TextView jgdmTextView;
    TextView jgmcTextView;
    ListView jgxxListView;
    LinearLayout titleLinearLayout;
    View[] needResetViews = null;

    String[] listNames = new String[] { new String(TagConstants.XML_VALUE_NAME_PBDN),
            new String(TagConstants.XML_VALUE_NAME_SFZYDQ),
            new String(TagConstants.XML_VALUE_NAME_SPDKQ),
            new String(TagConstants.XML_VALUE_NAME_XYJ),
            new String(TagConstants.XML_VALUE_NAME_XTY),
            new String(TagConstants.XML_VALUE_NAME_XZY),
            new String(TagConstants.XML_VALUE_NAME_DZYWC),
            new String(TagConstants.XML_VALUE_NAME_XYY),
            new String(TagConstants.XML_VALUE_NAME_RTCFY),
            new String(TagConstants.XML_VALUE_NAME_XDTJ),
            new String(TagConstants.XML_VALUE_NAME_RMDYJ) };

    private List<DeviceInfoTag> listItems = new ArrayList<DeviceInfoTag>();
    /**
     * @param context
     */
    public XtszJgxxPage(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszJgxxPage(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
        super(context, attrs);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_xtsz_jgxx_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        jgdmTextView = (TextView) findViewById(R.id.jgdmTextView);
        jgmcTextView = (TextView) findViewById(R.id.jgmcTextView);
        jgxxListView = (ListView) findViewById(R.id.jgxxListView);
        titleLinearLayout = (LinearLayout) findViewById(R.id.titleLinearLayout);

        if (!StringUtil.isEmptyString(Global.orgCode)) {
            jgdmTextView.setText(Global.orgCode);
        }
        if (!StringUtil.isEmptyString(Global.orgName)) {
            jgmcTextView.setText(Global.orgName);
        }
        DeviceInfoTag deviceInfoTag = null;
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_PBDN);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_SFZYDQ);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_SPDKQ);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYJ);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XTY);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XZY);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_DZYWC);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYY);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_RTCFY);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XDTJ);
        listItems.add(deviceInfoTag);
        deviceInfoTag = JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_RMDYJ);
        listItems.add(deviceInfoTag);
        ListAdapter listAapter = new ListAdapter();
        jgxxListView.setAdapter(listAapter);
    }


    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listItems.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.item_jgxx_layout, null);
                if (position % 2 == 1) {
                    convertView.setBackgroundResource(R.color.list_jsh_background_color);
                } else {
                    convertView.setBackgroundResource(R.color.list_osh_background_color);
                }

                TextView xhTextView = (TextView) convertView.findViewById(R.id.xhTextView);
                TextView yqmcTextView = (TextView) convertView.findViewById(R.id.yqmcTextView);
                TextView yqlxTextView = (TextView) convertView.findViewById(R.id.yqlxTextView);
                TextView ppTextView = (TextView) convertView.findViewById(R.id.ppTextView);
                TextView xh2TextView = (TextView) convertView.findViewById(R.id.xh2TextView);
                TextView bhTextView = (TextView) convertView.findViewById(R.id.bhTextView);
                if (position >= 0 && position < listItems.size()) {

/*                    List<Integer> widths = new ArrayList<Integer>();
                    for (int i = 0; i<titleLinearLayout.getChildCount(); i++) {
                        View childView = titleLinearLayout.getChildAt(i);
                        if (childView instanceof TextView) {
                            int width = childView.getWidth();
                            widths.add(width);
                        }
                    }
                    
                    Log.i(TAG, "widths=" + widths);*/
                    
                    //widths=[176, 148, 148, 176, 176, 177]
                    
                    needResetViews = new View[] { xhTextView, yqmcTextView, yqlxTextView, ppTextView,
                            xh2TextView, bhTextView };
                    //setViewByWidths(widths);
                    
                    DeviceInfoTag deviceInfoTag = null;
                    for (DeviceInfoTag tag : listItems) {
                        if (tag == null)
                            continue;
                        AttrBean attrBean = tag.attrBean;
                        if (listNames[position].equals(attrBean.getName())) {
                            deviceInfoTag = tag;
                            break;
                        }
                    }
                    xhTextView.setText((position + 1) + "");
                    yqmcTextView.setText(listNames[position]);
                    if (deviceInfoTag != null) {
                        AttrBean attrBean = deviceInfoTag.attrBean;
                        yqlxTextView.setText(attrBean.getType());
                        ppTextView.setText(attrBean.getBrand());
                        xh2TextView.setText(attrBean.getModel());
                        bhTextView.setText(attrBean.getSerialNo());
                    }
                }
            }

            return convertView;
        }

        /**
         * @param widths
         */
        public void setViewByWidths(List<Integer> widths) {
            for (int i = 0; i<needResetViews.length; i++) {
                View childView = needResetViews[i];
                if (childView instanceof TextView) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childView.getLayoutParams(); 
                    params.width = widths.get(i);
                    childView.setLayoutParams(params);
                }
                
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

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
