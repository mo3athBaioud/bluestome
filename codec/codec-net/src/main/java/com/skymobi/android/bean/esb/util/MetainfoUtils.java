/**
 * 
 */
package com.skymobi.android.bean.esb.util;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.util.meta.BeanMetainfoUtils;
import com.skymobi.android.bean.util.meta.Int2TypeMetainfo;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.commons.collections.Transformer;

import java.util.Collection;

/**
 * @author hp
 *
 */
public class MetainfoUtils {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MetainfoUtils.class);
    
    private static final Transformer CLS2INT = new Transformer(){

		public Object transform(Object input) {
			Class<?> cls = (Class<?>)input;
			EsbSignal attr = cls.getAnnotation(EsbSignal.class);
			return null != attr ? attr.messageCode() : null;
		}};
		
    static public Int2TypeMetainfo createEsbTypeMetainfo(Collection<String> packages) {
    	return	BeanMetainfoUtils.createTypeMetainfo(packages, CLS2INT );
	}
    
    static public Int2TypeMetainfo createEsbTypeMetainfoByClasses(Collection<Class<?>> clazzes) {
    	return	BeanMetainfoUtils.createTypeMetainfoByClasses(clazzes, CLS2INT );
	}

}
