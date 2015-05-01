/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Serializable.java
 * classes : com.okis.happyguide.file.Serializable
 * @author 刘军鹏
 * V 1.0.0
 * Create at 2012-8-13 下午04:36:28
 */
package com.cking.phss.file;

import java.io.IOException;

/**
 * com.okis.happyguide.file.Serializable
 * 
 * @author 刘军鹏 <br/>
 *         create at 2012-8-13 下午04:36:28
 */
public interface Serializable {
    public void serialize(Output out) throws IOException;

    public void deserialize(Input in) throws IOException;

}
