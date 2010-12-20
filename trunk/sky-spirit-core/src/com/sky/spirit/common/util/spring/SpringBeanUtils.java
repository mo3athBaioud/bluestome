package com.sky.spirit.common.util.spring;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:54:20
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
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
