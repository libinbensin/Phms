/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * MyButton.java
 * classes : com.adinnet.xd.medical.widget.MyButton
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-11 上午9:36:44
 */
package com.adinnet.xd.medical.widget;

import net.xinhuaxing.eshow.util.ImageUtil;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.Button;

/**
 * com.adinnet.xd.medical.widget.MyButton
 * 
 * @author Administrator <br/>
 *         create at 2014-6-11 上午9:36:44
 */
public class MyButton extends Button {
    private static final String TAG = "MyButton";
    private float mHeight;
    private float mWidgth;
    private boolean setBg = false;

    public MyButton(Context paramContext) {
        super(paramContext);
        initMyButton();
    }

    public MyButton(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        initMyButton();
    }

    public MyButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        initMyButton();
    }

    private Drawable changeContrast(Bitmap paramBitmap) {
        Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        ColorMatrix localColorMatrix = new ColorMatrix();
        float[] arrayOfFloat = new float[20];
        arrayOfFloat[0] = 0.8125F;
        arrayOfFloat[1] = 0.0F;
        arrayOfFloat[2] = 0.0F;
        arrayOfFloat[3] = 0.0F;
        arrayOfFloat[4] = 0.0F;
        arrayOfFloat[5] = 0.0F;
        arrayOfFloat[6] = 0.8125F;
        arrayOfFloat[7] = 0.0F;
        arrayOfFloat[8] = 0.0F;
        arrayOfFloat[9] = 0.0F;
        arrayOfFloat[10] = 0.0F;
        arrayOfFloat[11] = 0.0F;
        arrayOfFloat[12] = 0.8125F;
        arrayOfFloat[13] = 0.0F;
        arrayOfFloat[14] = 0.0F;
        arrayOfFloat[15] = 0.0F;
        arrayOfFloat[16] = 0.0F;
        arrayOfFloat[17] = 0.0F;
        arrayOfFloat[18] = 1.0F;
        arrayOfFloat[19] = 0.0F;
        localColorMatrix.set(arrayOfFloat);
        Paint localPaint = new Paint();
        localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
        new Canvas(localBitmap).drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
        return new BitmapDrawable(localBitmap);
    }

    private void initMyButton() {
        getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if (!MyButton.this.setBg) {
                            MyButton.this.mWidgth = MyButton.this.getWidth();
                            MyButton.this.mHeight = MyButton.this.getHeight();
                            MyButton.this.setBackgroundDrawable(MyButton.this.newSelector());
                            MyButton.this.setBg = true;
                        }
                    }
                });
    }

    private StateListDrawable newSelector() {
        StateListDrawable localStateListDrawable = new StateListDrawable();
        Drawable localDrawable1 = getBackground();
        Bitmap localBitmap = ImageUtil.drawable2Bitmap(localDrawable1);
        if (localBitmap != null) {
            Drawable localDrawable2 = changeContrast(localBitmap);
            if (localDrawable2 != null) {
                int[] arrayOfInt2 = new int[2];
                arrayOfInt2[0] = 16842919;
                arrayOfInt2[1] = 16842910;
                localStateListDrawable.addState(arrayOfInt2, localDrawable2);
            }
        }
        int[] arrayOfInt1 = new int[1];
        arrayOfInt1[0] = 16842910;
        localStateListDrawable.addState(arrayOfInt1, localDrawable1);
        localStateListDrawable.addState(new int[0], localDrawable1);
        return localStateListDrawable;
    }
}
