/**
 * 
 */
package com.skymobi.android.bean.bytebean.codec;

import com.skymobi.android.bean.bytebean.core.ByteFieldCodec;

/**
 * @author isdom
 *
 */
public abstract class AbstractCategoryCodec implements ByteFieldCodec {

    /* (non-Javadoc)
     * @see com.sky.applist20.bytebean.core.ByteFieldCodec#getFieldType()
     */
    public Class<?>[] getFieldType() {
        return null;
    }
}
