/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * DecimalsTextWatcherFactory.java
 * classes : com.cking.phss.util.DecimalsTextWatcherFactory
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-11 下午5:25:37
 */
package com.cking.phss.util;

import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

/**
 * com.cking.phss.util.DecimalsTextWatcherFactory
 * @author Administrator <br/>
 * create at 2014-7-11 下午5:25:37
 */
public abstract class DecimalsTextWatcherFactory {
    private static final String TAG = "DecimalsTextWatcherFactory";

    public abstract String getResult(String[] srcValues);

    public DecimalsTextWatcherFactory(final EditText[] srcControls, final TextView targetControl) {
        for (EditText et : srcControls) {
            if (et == null) {
                return;
            }
        }
        if (targetControl == null) {
            return;
        }

        DecimalsTextWatcher dtw = new DecimalsTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);

                String[] srcValues = new String[srcControls.length];
                int i = 0;
                for (EditText et : srcControls) {
                    srcValues[i++] = et.getText().toString();
                }
                String targetValue = getResult(srcValues);
                targetControl.setText(targetValue);
            }
        };

        // 添加事件
        for (EditText et : srcControls) {
            et.addTextChangedListener(dtw);
        }
    }
}
