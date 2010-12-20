package com.sky.spirit.common.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:54:12
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class BeanAdapter implements BeanFactoryAware {
	protected BeanFactory beanFactory;

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public Object getBean(String beanName) {
		try {
			return beanFactory.getBean(beanName);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
