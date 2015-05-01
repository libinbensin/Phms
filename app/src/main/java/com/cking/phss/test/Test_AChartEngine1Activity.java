/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Test_AChartEngine1Activity.java
 * classes : com.cking.phss.test.Test_AChartEngine1Activity
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-20 下午10:29:28
 */
package com.cking.phss.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalActivity;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;

/**
 * com.cking.phss.test.Test_AChartEngine1Activity
 * @author Administrator <br/>
 * create at 2012-9-20 下午10:29:28
 */
public class Test_AChartEngine1Activity extends GraphicalActivity {
    private static final String TAG = "Test_AChartEngine1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /// 这句话必须放在super.onCreate(savedInstanceState)之前，因为父类需要调用getIntent()方法
        setIntent(execute(this));
        super.onCreate(savedInstanceState);
    }

    public Intent execute(Context context) {
        String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
        List<double[]> x = new ArrayList<double[]>();
        for (int i = 0; i < titles.length; i++) {
          x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
        }
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
            13.9 });
        values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
        values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
        values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
            PointStyle.TRIANGLE, PointStyle.SQUARE };
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
          ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
        }
        setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, -10, 40,
            Color.LTGRAY, Color.LTGRAY);
        renderer.setXLabels(12);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.RIGHT);
        renderer.setZoomButtonsVisible(true);
        renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
        renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
        Intent intent = ChartFactory.getLineChartIntent(context, buildDataset(titles, x, values),
            renderer, "Average temperature");
        return intent;
      }
    /**
     * Builds an XY multiple dataset using the provided values.
     * 
     * @param titles the series titles
     * @param xValues the values for the X axis
     * @param yValues the values for the Y axis
     * @return the XY multiple dataset
     */
    protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
        List<double[]> yValues) {
      XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
      addXYSeries(dataset, titles, xValues, yValues, 0);
      return dataset;
    }

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
        List<double[]> yValues, int scale) {
      int length = titles.length;
      for (int i = 0; i < length; i++) {
        XYSeries series = new XYSeries(titles[i], scale);
        double[] xV = xValues.get(i);
        double[] yV = yValues.get(i);
        int seriesLength = xV.length;
        for (int k = 0; k < seriesLength; k++) {
          series.add(xV[k], yV[k]);
        }
        dataset.addSeries(series);
      }
    }

    /**
     * Builds an XY multiple series renderer.
     * 
     * @param colors the series rendering colors
     * @param styles the series point styles
     * @return the XY multiple series renderers
     */
    protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
      XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
      setRenderer(renderer, colors, styles);
      return renderer;
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
      renderer.setAxisTitleTextSize(16);
      renderer.setChartTitleTextSize(20);
      renderer.setLabelsTextSize(15);
      renderer.setLegendTextSize(15);
      renderer.setPointSize(5f);
      renderer.setMargins(new int[] { 20, 30, 15, 20 });
      int length = colors.length;
      for (int i = 0; i < length; i++) {
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(colors[i]);
        r.setPointStyle(styles[i]);
        renderer.addSeriesRenderer(r);
      }
    }

    /**
     * Sets a few of the series renderer settings.
     * 
     * @param renderer the renderer to set the properties to
     * @param title the chart title
     * @param xTitle the title for the X axis
     * @param yTitle the title for the Y axis
     * @param xMin the minimum value on the X axis
     * @param xMax the maximum value on the X axis
     * @param yMin the minimum value on the Y axis
     * @param yMax the maximum value on the Y axis
     * @param axesColor the axes color
     * @param labelsColor the labels color
     */
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
        String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
        int labelsColor) {
      renderer.setChartTitle(title);
      renderer.setXTitle(xTitle);
      renderer.setYTitle(yTitle);
      renderer.setXAxisMin(xMin);
      renderer.setXAxisMax(xMax);
      renderer.setYAxisMin(yMin);
      renderer.setYAxisMax(yMax);
      renderer.setAxesColor(axesColor);
      renderer.setLabelsColor(labelsColor);
    }

    /**
     * Builds an XY multiple time dataset using the provided values.
     * 
     * @param titles the series titles
     * @param xValues the values for the X axis
     * @param yValues the values for the Y axis
     * @return the XY multiple time dataset
     */
    protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<Date[]> xValues,
        List<double[]> yValues) {
      XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
      int length = titles.length;
      for (int i = 0; i < length; i++) {
        TimeSeries series = new TimeSeries(titles[i]);
        Date[] xV = xValues.get(i);
        double[] yV = yValues.get(i);
        int seriesLength = xV.length;
        for (int k = 0; k < seriesLength; k++) {
          series.add(xV[k], yV[k]);
        }
        dataset.addSeries(series);
      }
      return dataset;
    }

    /**
     * Builds a category series using the provided values.
     * 
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, String[] keyNames, double[] values) {
      CategorySeries series = new CategorySeries(title);
      int k = 0;
      for (double value : values) {
        series.add(keyNames[k++], value);
      }

      return series;
    }

    /**
     * Builds a multiple category series using the provided values.
     * 
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected MultipleCategorySeries buildMultipleCategoryDataset(String title,
        List<String[]> titles, List<double[]> values) {
      MultipleCategorySeries series = new MultipleCategorySeries(title);
      int k = 0;
      for (double[] value : values) {
        series.add(2007 + k + "", titles.get(k), value);
        k++;
      }
      return series;
    }

    /**
     * Builds a category renderer to use the provided colors.
     * 
     * @param colors the colors
     * @return the category renderer
     */
    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
      DefaultRenderer renderer = new DefaultRenderer();
      renderer.setLabelsTextSize(15);
      renderer.setLegendTextSize(15);
      renderer.setMargins(new int[] { 20, 30, 15, 0 });
      for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
      }
      return renderer;
    }

    /**
     * Builds a bar multiple series dataset using the provided values.
     * 
     * @param titles the series titles
     * @param values the values
     * @return the XY multiple bar dataset
     */
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
      XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
      int length = titles.length;
      for (int i = 0; i < length; i++) {
        CategorySeries series = new CategorySeries(titles[i]);
        double[] v = values.get(i);
        int seriesLength = v.length;
        for (int k = 0; k < seriesLength; k++) {
          series.add(v[k]);
        }
        dataset.addSeries(series.toXYSeries());
      }
      return dataset;
    }

    /**
     * Builds a bar multiple series renderer to use the provided colors.
     * 
     * @param colors the series renderers colors
     * @return the bar multiple series renderer
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
