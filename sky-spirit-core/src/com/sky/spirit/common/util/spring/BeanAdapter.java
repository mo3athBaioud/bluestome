package com.sky.spirit.common.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:54:12
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
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
