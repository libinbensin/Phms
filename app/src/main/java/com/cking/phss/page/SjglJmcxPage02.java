/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SjglPage01.java
 * classes : com.cking.phss.page.SjglPage01
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午8:36:56
 */
package com.cking.phss.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sjcx.Sjdnryzbsjcx;
import com.cking.phss.dto.sjcx.Sjdnryzbsjcx.Item;
import com.cking.phss.net.ISoapRecv;
import com.cking.phss.net.WebService;
import com.cking.phss.util.AbstractDemoChart;
import com.cking.phss.util.CalendarUtil;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SetDatespanDialog;
import com.cking.phss.widget.SetDatespanDialog.OnResultListener;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;

/**
 * com.cking.phss.page.SjglPage01 显示数据查询列表的页面
 */
public class SjglJmcxPage02 extends LinearLayout {
    private static final String TAG = "SjglJmcxPage02";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private LinearLayout[] linearStyles = new LinearLayout[3];
    private ListView mListView = null;
    private int listType;// 1代表查询快速体检，2代表查询高血压随访，3代表查询糖尿病随访，4代表查询体质心理，5代表查询健康评估
    private com.cking.phss.util.IDemoChart[] mCharts = new com.cking.phss.util.IDemoChart[] {
            new BloodPressureChart(), new BloodSugarChart() };
    
    LinearLayout titleLinearLayout;
    LinearLayout bodyLinearLayout;
    SpinnerUtil xzlxSpinnerUtil;
    Button cxButton;
    RadioGroup jstRadioGroup;
    TextView zjTextView;
    TextView ckzTextView;
    TextView ckz01TextView;
    TextView ckz02TextView;
    TextView ckz03TextView;
    TextView ckz04TextView;
    LinearLayout chartLinearLayout;
    private int gzglSelectIndex = -1;
    private List<Map<String, String>> resultsList = new ArrayList<Map<String, String>>();
    private SimpleAdapter adapter=null;
    String beginTime = "";
    String endTime = "";
    TextView zdyButton;

    public SjglJmcxPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param mContext2
     * @param beanMap2
     * @param jmcxTitleLinearLayout
     * @param jmcxBodyLinearLayout
     */
    public SjglJmcxPage02(Context context, Map<String, IBean> beanMap,
            LinearLayout titleLinearLayout, LinearLayout bodyLinearLayout) {
        super(context);
        this.beanMap = beanMap;
        this.titleLinearLayout = titleLinearLayout;
        this.bodyLinearLayout = bodyLinearLayout;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        View titleView = inflater.inflate(R.layout.fragment_sjgl_jmcx_02_title_layout, titleLinearLayout);
        xzlxSpinnerUtil = (SpinnerUtil) titleView.findViewById(R.id.xzlxSpinnerUtil);

        inflater = LayoutInflater.from(context);
        final View bodyView = inflater.inflate(R.layout.fragment_sjgl_jmcx_02_body_layout,
                bodyLinearLayout);
        jstRadioGroup = (RadioGroup) bodyView.findViewById(R.id.jstRadioGroup);
        zdyButton = (Button) bodyView.findViewById(R.id.zdyButton);

        ckzTextView = (TextView) bodyView.findViewById(R.id.ckzTextView);
        ckz01TextView = (TextView) bodyView.findViewById(R.id.ckz01TextView);
        ckz02TextView = (TextView) bodyView.findViewById(R.id.ckz02TextView);
        ckz03TextView = (TextView) bodyView.findViewById(R.id.ckz03TextView);
        ckz04TextView = (TextView) bodyView.findViewById(R.id.ckz04TextView);
        chartLinearLayout = (LinearLayout) bodyView.findViewById(R.id.chartLinearLayout);
        xzlxSpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String text = xzlxSpinnerUtil.getSelectedData();
                if (text.equals("血压")) {
                    setCkzText("正常血压参考值：", "收缩压", "90~130mmHg", "舒张压", "60~85mmHg");
                } else if (text.equals("血糖")) {
                    setCkzText("正常血糖参考值：", "空腹血糖", "3.9~6.2mmoL/L", "餐后血糖", "7.1~11.1mmoL/L");
                } else {
                    setCkzText("", "", "", "", "");
                }

                require(beginTime, endTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        jstRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) jstRadioGroup.findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                int tag = Integer.parseInt((String) rb.getTag());
                zdyButton.setVisibility(View.GONE);
                switch (tag) {
                    case 0: { // 最近一周
                        Date firstDay = CalendarUtil.getPreviousWeek(new Date());
                        Date lastDay = new Date();
                        beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        require(beginTime, endTime);
                        break;
                    }
                    case 1: {// 最近一月
                        Date firstDay = CalendarUtil.getPreviousMonth(new Date());
                        Date lastDay = new Date();
                        beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        require(beginTime, endTime);
                        break;
                    }
                    case 2: {// 最近三月
                        Date firstDay = CalendarUtil.getPreviousMonth(new Date());
                        firstDay = CalendarUtil.getPreviousMonth(firstDay);
                        firstDay = CalendarUtil.getPreviousMonth(firstDay);
                        Date lastDay = new Date();
                        beginTime = new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
                        endTime = new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
                        require(beginTime, endTime);
                        break;
                    }

                    case 3: {// 自定义
                        break;
                    }
                }
            }
        });
        // jstRadioGroup.check(R.id.jst01RadioButton);
        RadioButton jst04RadioButton = (RadioButton) bodyView.findViewById(R.id.jst04RadioButton);
        jst04RadioButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SetDatespanDialog dialog = new SetDatespanDialog(mContext);
                dialog.show();
                dialog.setOnResultListener(new OnResultListener() {

                    @Override
                    public void onConfirm(String beginTime, String endTime) {
                        SjglJmcxPage02.this.beginTime = beginTime;
                        SjglJmcxPage02.this.endTime = endTime;

                        zdyButton.setVisibility(View.VISIBLE);
                        zdyButton.setText(beginTime + " ~ " + endTime);
                        require(beginTime, endTime);
                    }

                    @Override
                    public void onCancel() {
                        // 都不选中
                        RadioButton jst04RadioButton = (RadioButton) bodyView
                                .findViewById(R.id.jst04RadioButton);
                        jst04RadioButton.setChecked(false);
                        resultsList.clear();
                    }
                });
            }

        });
    }

    // 查询时间段内人员指标数据查询格式
    public void require(String startDate, String endDate) {
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        requireData(xzlxSpinnerUtil.getSelectedValue(), xzlxSpinnerUtil.getSelectedData(),
                startDate, endDate);
    }

    private void requireData(final String targetId, String targetName, String startDate,
            String endDate) {
        // TODO需修改execute方法中的String[] titles,List<double[]> x, List<double[]>
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sjdnryzbsjcx sjdnryzbsjcx = new Sjdnryzbsjcx();
        sjdnryzbsjcx.condition = new Sjdnryzbsjcx.Condition();
        sjdnryzbsjcx.condition.NAME = jmjbxx.getResidentName();
        sjdnryzbsjcx.condition.IDCARD = jmjbxx.getPaperNum();
        sjdnryzbsjcx.condition.TARGETID = targetId;
        sjdnryzbsjcx.condition.TARGETNAME = targetName;
        sjdnryzbsjcx.condition.STARTTIME = startDate;
        sjdnryzbsjcx.condition.ENDTIME = endDate;
        String beanXmlString = XmlSerializerUtil.getInstance().beanToXml(sjdnryzbsjcx);
        beanXmlString = WebService.removeXmlString(beanXmlString);
        Log.i(TAG, beanXmlString);
        WebService.getInstance().excute("SearchResults", "data", beanXmlString, new ISoapRecv() {
            @Override
            public void onRecvData(String xmlStr, boolean success) {
                if (success) {// 如果成功
                    if (!xmlStr.startsWith("<")) {
                        mToast.setText(xmlStr);
                        mToast.show();
                        return;
                    }
                    xmlStr = WebService.addXmlString(xmlStr);
                    Sjdnryzbsjcx responseSjdnryzbsjcx = (Sjdnryzbsjcx) XmlSerializerUtil
                            .getInstance().beanFromXML(Sjdnryzbsjcx.class, xmlStr);
                    if (responseSjdnryzbsjcx == null || responseSjdnryzbsjcx.results == null) {
                        mToast.setText("查询失败！");
                        mToast.show();
                        return;
                    }

                    if (responseSjdnryzbsjcx.results.items == null
                            || responseSjdnryzbsjcx.results.items.size() <= 0) {
                        mToast.setText("查询没有数据！");
                        mToast.show();
                        return;
                    }
                    updateView(targetId, responseSjdnryzbsjcx.results.items);
                } else {
                    mToast.setText("查询没有数据！");
                    mToast.show();
                }

            }
        });

    }

    // 更新视图
    public void updateView(String targetId, List<Item> items) {
        // int selectIndex = xzlxSpinnerUtil.getSelectedValueInt();
        // 解析传回来的数据
        resultsList.clear();
        Map<String, String> map;
        if (targetId.equals("02")) {// 解析血压
            for (int i = 0; i < items.size(); i++) {
                Sjdnryzbsjcx.Item item = items.get(i);
                if (item == null || item.CHECKTIME.equals("") || item.RESULT.equals("")) {
                    continue;
                }
                try {
                    String[] valueStr = item.RESULT.split("/");
                    if (valueStr.length < 2) {
                        continue;
                    }
                    map = new HashMap<String, String>();
                    map.put("time", item.CHECKTIME);
                    map.put("sbp", valueStr[0]);
                    map.put("dbp", valueStr[1]);
                    resultsList.add(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (targetId.equals("03")) {// 解析血糖
            for (int i = 0; i < items.size(); i++) {
                Sjdnryzbsjcx.Item item = items.get(i);
                if (item == null || item.CHECKTIME.equals("") || item.RESULT.equals("")) {
                    continue;
                }
                try {
                    map = new HashMap<String, String>();
                    map.put("time", item.CHECKTIME);
                    map.put("kfxt", item.RESULT);
                    resultsList.add(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /*
         * //测试数据 if(targetId.equals("02")){ map=new HashMap<String, String>();
         * map.put("time", "2012-12-11"); map.put("sbp", "120"); map.put("dbp",
         * "130"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-12");
         * map.put("sbp", "120"); map.put("dbp", "130"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-13");
         * map.put("sbp", "125"); map.put("dbp", "112"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-14");
         * map.put("sbp", "120"); map.put("dbp", "130"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-15");
         * map.put("sbp", "90"); map.put("dbp", "100"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-16");
         * map.put("sbp", "112"); map.put("dbp", "140"); resultsList.add(map);
         * }else{ map=new HashMap<String, String>(); map.put("time",
         * "2012-12-10"); map.put("kfxt", "120"); map.put("chxt", "100");
         * resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-11");
         * map.put("kfxt", "111"); map.put("chxt", "111"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-12");
         * map.put("kfxt", "70"); map.put("chxt", "90"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-13");
         * map.put("kfxt", "120"); map.put("chxt", "100"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-14");
         * map.put("kfxt", "105"); map.put("chxt", "100"); resultsList.add(map);
         * 
         * map=new HashMap<String, String>(); map.put("time", "2012-12-15");
         * map.put("kfxt", "100"); map.put("chxt", "100"); resultsList.add(map);
         * }
         */

        if (resultsList == null || resultsList.size() <= 0)
            return;
        chartLinearLayout.removeAllViews();
        // values的值,改为在线获取值并绘制view
        View view = null;
        if (targetId.equals("02")) {
            view = mCharts[0].execute(mContext);
            setCkzText("正常血压参考值：", "收缩压", "90~130mmHg", "舒张压", "60~85mmHg");
        } else if (targetId.equals("03")) {
            view = mCharts[1].execute(mContext);
            setCkzText("正常血糖参考值：", "空腹血糖", "3.9~6.2mmoL/L", "餐后血糖", "7.1~11.1mmoL/L");
        }
        if (view != null)
            chartLinearLayout.addView(view);
    }

    /**
     * @param string
     * @param string2
     * @param string3
     * @param string4
     * @param string5
     */
    private void setCkzText(String ckzText, String ckz01Text, String ckz02Text, String ckz03Text,
            String ckz04Text) {
        ckzTextView.setText(ckzText);
        ckz01TextView.setText(ckz01Text);
        ckz02TextView.setText(ckz02Text);
        ckz03TextView.setText(ckz03Text);
        ckz04TextView.setText(ckz04Text);
    }

    // 血压折线图
    class BloodPressureChart extends AbstractDemoChart {
        String[] titles = new String[] { "收缩压", "舒张压", "收缩压标准", "舒张压标准", "收缩压预警", "舒张压预警" };// 两条数据线的名称
        int[] colors = new int[] { Color.BLUE, Color.YELLOW, Color.rgb(0, 0, 0xee),
                Color.rgb(0xee, 0xee, 00), Color.RED, Color.rgb(0x55, 0, 0) };
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
                PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT };
        double[] dataStandart = new double[] { 120, 80, 140, 90 };

        public String getName() {
            return "血压变化曲线图";
        }

        public String getDesc() {
            return "描述某时间段内血压变化的曲线图";
        }

        public View execute(Context context) {
            List<Date[]> dates = new ArrayList<Date[]>();// 两条线的每个时间点
            int length = titles.length;
            List<double[]> values = new ArrayList<double[]>();
            int size = resultsList.size();

            for (int i = 0; i < length; i++) {
                dates.add(new Date[size]);// 把每条线的x坐标，即时间确定
                double[] everyValues = new double[size]; // 一条线的值
                for (int j = 0; j < size; j++) {
                    try {
                        Log.i(TAG, CalendarUtil.parseDate(resultsList.get(j).get("time")) + "");
                        dates.get(i)[j] = CalendarUtil.parseDate(resultsList.get(j).get("time"));
                        if (i == 0) {
                            everyValues[j] = Double.parseDouble(resultsList.get(j).get("sbp"));
                        } else if (i == 1) {
                            everyValues[j] = Double.parseDouble(resultsList.get(j).get("dbp"));
                        } else {
                            everyValues[j] = dataStandart[i - 2];
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                values.add(everyValues);
            }

            // 渲染器
            XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
            setChartSettings(renderer, "血压变化曲线图", "检查日期", "检查结果(mmHg)", dates.get(0)[0].getTime(),
                    dates.get(0)[0].getTime() + 15L * 24 * 60 * 60 * 1000, 50, 190, Color.BLACK,
                    Color.BLACK);
            renderer.setXLabels(15);// 设置x坐标有几格
            renderer.setYLabels(10);// 设置y坐标有几格
            // 显示舒张压和收缩压的值
            length = renderer.getSeriesRendererCount();
            for (int i = 0; i < length; i++) {
                SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
                if (i == 0 || i == 1) {
                    seriesRenderer.setDisplayChartValues(true);
                    ((XYSeriesRenderer) seriesRenderer).setFillPoints(true);
                    BasicStroke basicStroke = new BasicStroke(Cap.ROUND, Join.ROUND, 10, null, 0);
                    seriesRenderer.setStroke(basicStroke);
                } else {
                    seriesRenderer.setStroke(BasicStroke.DASHED);
                }
            }
            renderer.setShowGridX(true);// 是否显示网格
            renderer.setXLabelsAlign(Align.RIGHT);// 刻度线与刻度标注之间的相对位置关系
            renderer.setYLabelsAlign(Align.LEFT);// 刻度线与刻度标注之间的相对位置关系
            renderer.setXLabelsAngle(-25);
            renderer.setZoomButtonsVisible(true);// 是否显示放大缩小按钮
            // 设置背景颜色
            renderer.setBackgroundColor(Color.rgb(249, 243, 231));
            renderer.setApplyBackgroundColor(true);
            renderer.setMarginsColor(Color.WHITE);
            renderer.setXLabelsColor(Color.DKGRAY);
            renderer.setYLabelsColor(0, Color.DKGRAY);
            // renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); //
            // 设置拖动时X轴Y轴允许的最大值最小值.
            // renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });//
            // 设置放大缩小时X轴Y轴允许的最大最小值.
            renderer.setMargins(new int[] { 30, 30, 40, 30 });// 坐标图位置调整{上边距,左边距,下边距,右边距}
            View chartView = ChartFactory.getTimeChartView(context,
                    buildDateDataset(titles, dates, values), renderer, "yy/MM/dd");
            return chartView;
        }
    }

    // 血糖折线图
    class BloodSugarChart extends AbstractDemoChart {
        String[] titles = new String[] { "血糖值", "空腹血糖标准", "餐后两小时血糖标准", "空腹血糖预警", "餐后两小时血糖预警" };//
        int[] colors = new int[] { Color.BLUE, Color.YELLOW, Color.rgb(0x55, 0x55, 00), Color.RED,
                Color.rgb(0x55, 0, 0) };
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.POINT,
                PointStyle.POINT, PointStyle.POINT, PointStyle.POINT };
        double[] dataStandart = new double[] { 3.89, 4.0, 6.11, 7 };

        public String getName() {
            return "血糖变化曲线图";
        }

        public String getDesc() {
            return "描述某时间段内血糖变化的曲线图";
        }

        public View execute(Context context) {
            List<Date[]> dates = new ArrayList<Date[]>();// 两条线的每个时间点
            int length = titles.length;
            List<double[]> values = new ArrayList<double[]>();
            int size = resultsList.size();

            for (int i = 0; i < length; i++) {
                dates.add(new Date[size]);// 把每条线的x坐标，即时间确定
                double[] everyValues = new double[size]; // 一条线的值
                for (int j = 0; j < size; j++) {
                    try {
                        dates.get(i)[j] = CalendarUtil.parseDate(resultsList.get(j).get("time"));
                        if (i == 0) {
                            everyValues[j] = Double.parseDouble(resultsList.get(j).get("kfxt"));
                        } else {
                            everyValues[j] = dataStandart[i - 1];
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                values.add(everyValues);
            }

            // 渲染器
            XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
            setChartSettings(renderer, "血糖变化曲线图", "检查日期", "检查结果(mmol/h)",
                    dates.get(0)[0].getTime(), dates.get(0)[0].getTime() + 15L * 24 * 60 * 60
                            * 1000, 0, 15, Color.BLACK, Color.BLACK);
            renderer.setXLabels(15);// 设置x坐标有几格
            renderer.setYLabels(15);// 设置y坐标有几格
            //
            length = renderer.getSeriesRendererCount();
            for (int i = 0; i < length; i++) {
                SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
                if (i == 0) {
                    seriesRenderer.setDisplayChartValues(true);
                    ((XYSeriesRenderer) seriesRenderer).setFillPoints(true);
                    BasicStroke basicStroke = new BasicStroke(Cap.ROUND, Join.ROUND, 10, null, 0);
                    seriesRenderer.setStroke(basicStroke);
                } else {
                    seriesRenderer.setStroke(BasicStroke.DASHED);
                }
            }
            renderer.setShowGridX(true);// 是否显示网格
            renderer.setXLabelsAlign(Align.RIGHT);// 刻度线与刻度标注之间的相对位置关系
            renderer.setYLabelsAlign(Align.LEFT);// 刻度线与刻度标注之间的相对位置关系
            renderer.setXLabelsAngle(-25);// 设置 X 轴标签倾斜角度 (clockwise degree)
            renderer.setZoomButtonsVisible(true);// 是否显示放大缩小按钮
            renderer.setXLabelsColor(Color.DKGRAY);
            renderer.setYLabelsColor(0, Color.DKGRAY);
            // 设置背景颜色
            renderer.setBackgroundColor(Color.rgb(249, 243, 231));
            renderer.setApplyBackgroundColor(true);
            renderer.setMarginsColor(Color.WHITE);
            // renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); //
            // 设置拖动时X轴Y轴允许的最大值最小值.
            // renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });//
            // 设置放大缩小时X轴Y轴允许的最大最小值.
            renderer.setMargins(new int[] { 30, 30, 40, 30 });// 坐标图位置调整{上边距,左边距,下边距,右边距}
            View chartView = ChartFactory.getTimeChartView(context,
                    buildDateDataset(titles, dates, values), renderer, "yy/MM/dd");
            return chartView;
        }
    }
}
