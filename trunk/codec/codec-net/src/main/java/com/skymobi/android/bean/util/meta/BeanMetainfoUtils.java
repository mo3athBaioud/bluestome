/**
 * 
 */
package com.skymobi.android.bean.util.meta;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.util.PackageUtils;

import org.apache.commons.collections.Transformer;

import java.io.IOException;
import java.util.Collection;

/**
 * @author hp
 *
 */
public class BeanMetainfoUtils {
	
    private static final Logger logger = 
    	LoggerFactory.getLogger(BeanMetainfoUtils.class);
	
    static public Int2TypeMetainfo createTypeMetainfo(Collection<String> packages, 
    		Transformer transformer) {
    	DefaultInt2TypeMetainfo typeMetainfo = new DefaultInt2TypeMetainfo();
		
		if (null != packages) {
			for (String pkgName : packages) {
				try {
					String[] clsNames = PackageUtils.findClassesInPackage(
							pkgName, null, null);
					for (String clsName : clsNames) {
						try {
							ClassLoader cl = Thread.currentThread().getContextClassLoader();
							if ( logger.isDebugEnabled() ) {
								logger.debug("using ClassLoader "+cl.getClass().getSimpleName()+" to load Class "+clsName);
							}
							Class<?> cls =  cl.loadClass(clsName);
							Integer value = (Integer)transformer.transform(cls);
							if ( null != value ) {
								typeMetainfo.add(value, cls);
								logger.info("metainfo: add " + value + ":=>" + cls);
							}
						} catch (ClassNotFoundException e) {
							logger.error("createTypeMetainfo: ", e);
						}
					}
				} catch (IOException e) {
					logger.error("createTypeMetainfo: ", e);
				}
			}
		}
		
		return	typeMetainfo;
	}
    
    static public Int2TypeMetainfo createTypeMetainfoByClasses(Collection<Class<?>> clazzes, 
    		Transformer transformer) {
    	DefaultInt2TypeMetainfo typeMetainfo = new DefaultInt2TypeMetainfo();
		
		for (Class<?> cls : clazzes) {
			Integer value = (Integer)transformer.transform(cls);
//			logger.debug(" > int2type:value|{},cls|{}",value,cls);
			if ( null != value ) {
				typeMetainfo.add(value, cls);
			}
		}
		
		return	typeMetainfo;
	}
}
