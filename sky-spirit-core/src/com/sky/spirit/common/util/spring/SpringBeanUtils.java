package com.sky.spirit.common.util.spring;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:54:20
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class SpringBeanUtils {
	private static BeanAdapter beanAdapter;

	public static Object getBean(String beanName) {
		return beanAdapter.getBean(beanName);
	}

	public void setBeanAdapter(BeanAdapter beanAdapter) {
		SpringBeanUtils.beanAdapter = beanAdapter;
	}
}
