package com.sky.spirit.basic.struts;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:37:14
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class BaseActionServlet extends ActionServlet {
	
	public static final long serialVersionUID = 10000100000l;
	
	public static String DOMAIN = "";
	
    private Log log = LogFactory.getLog(BaseActionServlet.class);

    private void setContextClassLoader() {
        ClassLoader loader = this.getClass().getClassLoader();
        if ( (loader) != null && loader != Thread.currentThread().getContextClassLoader()) {
            log.debug("set ContextClassLoader to "+loader);

            Thread.currentThread().setContextClassLoader(loader);
        }
    }

    public BaseActionServlet() {
        setContextClassLoader();
    }

    public void init() throws ServletException {
    	BaseActionServlet.DOMAIN = this.getInitParameter("domain");    	
        ServletContext app = getServletContext();        
        java.util.Enumeration enums = app.getAttributeNames();
        java.util.List<String> list = new java.util.ArrayList<String>();
        if (enums != null) {
            String key;
            while (enums.hasMoreElements()) {
                key = (String) enums.nextElement();
                if (key.startsWith(Globals.REQUEST_PROCESSOR_KEY)) {
                    list.add(key);
                }                
            }
        }
        synchronized (app){
          for (int i = 0; i < list.size(); i++) {
            app.removeAttribute( (String) list.get(i));
          }
          log.debug(" all request processors removed");
        }
        super.init();
    }

    protected void process(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {
        setContextClassLoader();
        super.process(request, response);
    }

    public void destroy() {
        super.destroy();
        log.debug("CTSActionServlet destroied.");
    }


}
