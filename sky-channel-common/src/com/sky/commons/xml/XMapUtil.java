package com.sky.commons.xml;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.nuxeo.common.xmap.XMap;

/**
 * XMap工具类，用来解析xml文件 
 * @author 
  */
public class XMapUtil {
    private static final XMap xmap;

    static {
        xmap = new XMap();
    }

    /**
     * 注册Object。
     * 
     * @param clazz
     */
    public static void register(Class<?> clazz) {
        if (clazz != null) {
            xmap.register(clazz);
        }
    }

    /**
     * 解析xml到Object
     * @param is
     * @return
     * @throws Exception
     */
    public static Object load(InputStream is) throws Exception {
        Object obj = null;
        try {
            obj = xmap.load(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return obj;
    }
    public static Object load(String xml) throws Exception {
    	return load(xml,"UTF-8");
    }
    public static Object load(String xml,String ecoding) throws Exception {
    	if(xml==null){
    		return null;
    	}
    	return load(new ByteArrayInputStream(xml.getBytes(ecoding)));
    }

    /**
     * Object到XML。
     * 
     * @param obj
     * @param encoding
     * @param outputsFields
     * @return
     * @throws PaygwException 
     */
    public static String asXml(Object obj, String encoding, List<String> outputsFields)
                                                                                       throws Exception {

        return xmap.asXmlString(obj, encoding, outputsFields);
    }
}
