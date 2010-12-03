package com.ssi.common.template.velocity;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.DataSourceResourceLoader;

import com.ssi.common.template.model.DBTemplate;

public class VelocityTemplateDBLoader extends DataSourceResourceLoader {

	final Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	public synchronized InputStream getResourceStream(String name)
			throws ResourceNotFoundException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String mcc = null;
		String lang = null;
		log.debug(">> mcc :" +mcc);
		log.debug(">> lang :" +lang);
		
		String il_name = "";
		if(mcc != null && lang != null) {
			il_name = "/" + mcc + "_" + lang + name;
		}
		
		//TODO 从数据库中获取模板对象
//		template = this.getTemplateDao().getTemplateByPath(il_name);
		DBTemplate template = null;
		if (template != null) {
			String content = template.getTemplate();
			ByteArrayInputStream stream = null;
			try {
				stream = new ByteArrayInputStream(content
						.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.warn(">> get template from db error:"+e);
			}

			return new BufferedInputStream(stream);
		} else {
			//TODO 获取没有国家，语言属性的模板
//			template = this.getTemplateDao().getTemplateByPath(name);
			template = null;
			if(template != null) {
				String content = template.getTemplate();
				ByteArrayInputStream stream = null;
				try {
					stream = new ByteArrayInputStream(content
							.getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					log.warn(">> get template from filesystem error:"+e);
				}

				return new BufferedInputStream(stream);
			}else {
				//增加默认页面，即未找到定义的页面，则提示正在建设的页面。
				log.warn(">> no template["+name+"] found db,use default[/view/main/default.vm] template to show page");
				//TODO 从数据库中获取默认页面
//				template = this.getTemplateDao().getTemplateByPath("/view/main/default.vm");
				template = null;
				if(template != null) {
					String content = template.getTemplate();
					ByteArrayInputStream stream = null;
					try {
						stream = new ByteArrayInputStream(content
								.getBytes("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						log.warn(">> get template from filesystem error:"+e);
					}

					return new BufferedInputStream(stream);
				}else{
					log.warn(">> no resource found in filesystem!!!");
					throw new ResourceNotFoundException("VelocityTemplateDBLoader: "
							+ "could not find resource '" + name + "'");
				}
			}
		}
	}

	@Override
	public long getLastModified(Resource resource) {
		return 0;
	}

	@Override
	public boolean isSourceModified(Resource resource) {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Checking if the source had been modified");
		}
		return (resource.getLastModified() != getLastModified(resource));
	}
	
}
