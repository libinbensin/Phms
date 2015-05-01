package com.cking.phss.page;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.Tzps;

public class JktjTzbsTzbg extends LinearLayout {

	private GraphicalView mChartView;
	private JktjTzbsPage mParent;
	private Context mContext = null;
	private Button tzbgButton = null;
	private int[] rate = null;//各个题目的分数
    private Button mBackBtn = null;

	public JktjTzbsTzbg(Context context, JktjTzbsPage parent, int[] rate) {
		super(context);
		this.mParent = parent;
		this.rate = rate;
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjTzbsTzbg(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(final Context context) {
		mContext = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_tzbs_4_layout, this);
		tzbgButton=(Button)findViewById(R.id.tzps_tzbg_button);
        mBackBtn = (Button) findViewById(R.id.tzps_back_button);
		tzbgButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mParent.showTzpsjgxx(rate);
			}
		});

        mBackBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mParent.showTzpsPage01();
            }
        });
		/*
		 * tzbgButton =findViewById(R.id.tzps_tzbg_button);
		 * tzbgButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * } });
		 */
		if (mChartView == null) {
			LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
			mChartView = execute(context);
			mChartView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					SeriesSelection seriesSelection = mChartView
							.getCurrentSeriesAndPoint();
					if (seriesSelection == null) {
						Toast.makeText(mContext,
								"No chart element was clicked",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(
								mContext,
								"Chart element data point index "
										+ seriesSelection.getPointIndex()
										+ " was clicked" + " point value="
										+ seriesSelection.getValue(),
								Toast.LENGTH_SHORT).show();
					}
				}
			});
			mChartView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					SeriesSelection seriesSelection = mChartView
							.getCurrentSeriesAndPoint();
					if (seriesSelection == null) {
						Toast.makeText(mContext,
								"No chart element was long pressed",
								Toast.LENGTH_SHORT);
						return false; // no chart element was long pressed, so
										// let something
						// else handle the event
					} else {
						Toast.makeText(mContext,
								"Chart element data point index "
										+ seriesSelection.getPointIndex()
										+ " was long pressed",
								Toast.LENGTH_SHORT);
						return true; // the element was long pressed - the event
										// has been
						// handled
					}
				}
			});
			layout.addView(mChartView, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		} else {
			mChartView.repaint();
		}
	}

	// 开始渲染生成树状统计图
	public GraphicalView execute(Context context) {
		String[] titles = new String[] { "平和体质测试", "气虚质测试", "阳虚质测试", "阴虚质测试",
				"痰湿质测试", "湿热质测试", "血瘀质测试", "气郁质测试", "特禀质测试" };
		List<Float> values = Tzps.getGraphicalValues(rate);
		
		int[] colors = new int[] { Color.BLUE, Color.CYAN, Color.MAGENTA,
				Color.GREEN, Color.LTGRAY, Color.RED, Color.YELLOW,
				Color.DKGRAY, Color.GRAY };

		// 创建一个渲染器，渲染生成树状图
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		// 设置渲染的树状图的属性
		setChartSettings(renderer, "体质表统计图", "体质分类", "值", -10, 90);

		return ChartFactory.getBarChartView(context,
				buildBarDataset(titles, values), renderer, Type.DEFAULT);// 默认类型
	}

	/**
	 * Builds an XY multiple dataset using the provided values.
	 * 
	 * @param titles
	 *            the series titles
	 * @param xValues
	 *            the values for the X axis
	 * @param yValues
	 *            the values for the Y axis
	 * @return the XY multiple dataset
	 */
	// protected XYMultipleSeriesDataset buildDataset(String[] titles,
	// List<double[]> xValues,
	// List<double[]> yValues) {
	// XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	// addXYSeries(dataset, titles, xValues, yValues, 0);
	// return dataset;
	// }

	// public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
	// List<double[]> xValues,
	// List<double[]> yValues, int scale) {
	// int length = titles.length;
	// for (int i = 0; i < length; i++) {
	// XYSeries series = new XYSeries(titles[i], scale);
	// double[] xV = xValues.get(i);
	// double[] yV = yValues.get(i);
	// int seriesLength = xV.length;
	// for (int k = 0; k < seriesLength; k++) {
	// series.add(xV[k], yV[k]);
	// }
	// dataset.addSeries(series);
	// }
	// }

	/**
	 * Builds an XY multiple series renderer.
	 * 
	 * @param colors
	 *            the series rendering colors
	 * @param styles
	 *            the series point styles
	 * @return the XY multiple series renderers
	 */
	// protected XYMultipleSeriesRenderer buildRenderer(int[] colors,
	// PointStyle[] styles) {
	// XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	// setRenderer(renderer, colors, styles);
	// return renderer;
	// }

	// protected void setRenderer(XYMultipleSeriesRenderer renderer, int[]
	// colors, PointStyle[] styles) {
	// renderer.setAxisTitleTextSize(16);
	// renderer.setChartTitleTextSize(20);
	// renderer.setLabelsTextSize(15);
	// renderer.setLegendTextSize(15);
	// renderer.setPointSize(5f);
	// renderer.setMargins(new int[] { 20, 30, 15, 20 });
	// int length = colors.length;
	// for (int i = 0; i < length; i++) {
	// XYSeriesRenderer r = new XYSeriesRenderer();
	// r.setColor(colors[i]);
	// r.setPointStyle(styles[i]);
	// renderer.addSeriesRenderer(r);
	// }
	// }

	/**
	 * 设置渲染的树状图的属性
	 */
	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin, double xMax) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(100);
		renderer.setAxesColor(Color.RED);
		renderer.setLabelsColor(Color.RED);
		//设置bar的宽度，-1 < barSpace < 0 宽度增加
		renderer.setBarSpacing(-0.95);
		renderer.setMargins(new int[] { 12, 12, 0, 12 });
		renderer.setPanEnabled(false, false);
		renderer.setZoomEnabled(false);
		renderer.setShowLabels(false);
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setApplyBackgroundColor(true);
		renderer.setMarginsColor(Color.WHITE);
		//renderer.setYLabelsColor(1, Color.WHITE);
		// 设置要显示树状图的标签
		for (int i = 0; i < 9; i++) {
			renderer.getSeriesRendererAt(i).setDisplayChartValues(true);
		}
		renderer.setXLabels(9);
		renderer.setYLabels(5);

		renderer.setXLabelsAlign(Align.LEFT);
		renderer.setYLabelsAlign(Align.LEFT);
	}

	/**
	 * Builds an XY multiple time dataset using the provided values.
	 * 
	 * @param titles
	 *            the series titles
	 * @param xValues
	 *            the values for the X axis
	 * @param yValues
	 *            the values for the Y axis
	 * @return the XY multiple time dataset
	 */
	// protected XYMultipleSeriesDataset buildDateDataset(String[] titles,
	// List<Date[]> xValues,
	// List<double[]> yValues) {
	// XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	// int length = titles.length;
	// for (int i = 0; i < length; i++) {
	// TimeSeries series = new TimeSeries(titles[i]);
	// Date[] xV = xValues.get(i);
	// double[] yV = yValues.get(i);
	// int seriesLength = xV.length;
	// for (int k = 0; k < seriesLength; k++) {
	// series.add(xV[k], yV[k]);
	// }
	// dataset.addSeries(series);
	// }
	// return dataset;
	// }

	/**
	 * Builds a category series using the provided values.
	 * 
	 * @param titles
	 *            the series titles
	 * @param values
	 *            the values
	 * @return the category series
	 */
	// protected CategorySeries buildCategoryDataset(String title, String[]
	// keyNames, double[] values) {
	// CategorySeries series = new CategorySeries(title);
	// int k = 0;
	// for (double value : values) {
	// series.add(keyNames[k++], value);
	// }
	//
	// return series;
	// }

	/**
	 * Builds a multiple category series using the provided values.
	 * 
	 * @param titles
	 *            the series titles
	 * @param values
	 *            the values
	 * @return the category series
	 */
	// protected MultipleCategorySeries buildMultipleCategoryDataset(String
	// title,
	// List<String[]> titles, List<double[]> values) {
	// MultipleCategorySeries series = new MultipleCategorySeries(title);
	// int k = 0;
	// for (double[] value : values) {
	// series.add(2007 + k + "", titles.get(k), value);
	// k++;
	// }
	// return series;
	// }

	/**
	 * Builds a category renderer to use the provided colors.
	 * 
	 * @param colors
	 *            the colors
	 * @return the category renderer
	 */
	// protected DefaultRenderer buildCategoryRenderer(int[] colors) {
	// DefaultRenderer renderer = new DefaultRenderer();
	// renderer.setLabelsTextSize(15);
	// renderer.setLegendTextSize(15);
	// renderer.setMargins(new int[] { 20, 30, 15, 0 });
	// for (int color : colors) {
	// SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	// r.setColor(color);
	// renderer.addSeriesRenderer(r);
	// }
	// return renderer;
	// }

	/**
	 * Builds a bar multiple series dataset using the provided values.
	 * 
	 * @param titles
	 *            the series titles
	 * @param values
	 *            the values
	 * @return the XY multiple bar dataset
	 */
	protected XYMultipleSeriesDataset buildBarDataset(String[] titles,
			List<Float> values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    int length = titles.length;
		for (int i = 0; i < length; i++) {
		    XYSeries series = new XYSeries(titles[i]);
			double val = values.get(i);
			BigDecimal bigDecimal=new BigDecimal(val).round(new MathContext(3,RoundingMode.HALF_DOWN));
			double f=bigDecimal.doubleValue();
			series.add(i * 5 + 20, f);
			dataset.addSeries(series);
		}
		return dataset;
	}

	/**
	 * 画条状图
	 */
	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}
}
