/**
 * 
 */
package com.skymobi.android.util;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.WrapDynaBean;

import java.lang.reflect.InvocationTargetException;

/**
 * @author hp
 *
 */
public class DynaBeanUtils {
	
    private static final Logger logger = LoggerFactory.getLogger(DynaBeanUtils.class);
    
    private DynaBeanUtils() {}
    
    public static LazyDynaBean wrapLazyDynaBean(Object bean) {
        try {
            if ( !(bean instanceof LazyDynaBean) ) {
                if ( !(bean instanceof WrapDynaBean) ) {
                    bean = new WrapDynaBean(bean);
                }
                LazyDynaBean lazy = new LazyDynaBean( ((WrapDynaBean)bean).getDynaClass() );
                PropertyUtils.copyProperties(lazy, bean);
                bean = lazy;
            }
            return  (LazyDynaBean)bean;
        } catch (IllegalAccessException e) {
        	logger.error("wrapLazyDynaBean:", e);
        } catch (InvocationTargetException e) {
        	logger.error("wrapLazyDynaBean:", e);
        } catch (NoSuchMethodException e) {
        	logger.error("wrapLazyDynaBean:", e);
        }
        return  null;
    }
    
    public static LazyDynaBean cloneLazyDynaBean(Object bean) {
        try {
        	LazyDynaBean lazy = null;
        	
            if ( !(bean instanceof LazyDynaBean) ) {
                if ( !(bean instanceof WrapDynaBean) ) {
                    bean = new WrapDynaBean(bean);
                }
                lazy = new LazyDynaBean( ((WrapDynaBean)bean).getDynaClass() );
            }
            else {
                lazy = new LazyDynaBean( ((LazyDynaBean)bean).getDynaClass() );
            }
            PropertyUtils.copyProperties(lazy, bean);
            return  lazy;
        } catch (IllegalAccessException e) {
        	logger.error("wrapLazyDynaBean:", e);
        } catch (InvocationTargetException e) {
        	logger.error("wrapLazyDynaBean:", e);
        } catch (NoSuchMethodException e) {
        	logger.error("wrapLazyDynaBean:", e);
        }
        return  null;
    }
}
